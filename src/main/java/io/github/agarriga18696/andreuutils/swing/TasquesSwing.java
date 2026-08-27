package io.github.agarriga18696.andreuutils.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;

import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;

/**
 * Classe d'utilitat per executar tasques en segon pla amb Swing.
 * <p>
 * Encapsula el patró {@link SwingWorker} per simplificar l'execució de
 * tasques de llarga durada sense bloquejar el fil d'esdeveniments de Swing (EDT).
 * Tots els callbacks ({@code alAcabar}, {@code alError}) s'executen sempre a l'EDT,
 * de manera que és segur modificar components Swing des d'ells.
 *
 * @author Andreu
 * @version 1.0
 */
public final class TasquesSwing {

	//-------------------------------
	// ATRIBUTS ESTÀTICS
	//-------------------------------

	private static final Logger LOGGER = Logger.getLogger(TasquesSwing.class.getName());

	private TasquesSwing() {
		/*
		 * Classe d'utilitat no instanciable.
		 */
	}

	//-------------------------------
	// EXECUCIÓ EN SEGON PLA
	//-------------------------------

	/**
	 * Executa una tasca en un fil de segon pla i, quan acaba correctament,
	 * crida {@code alAcabar} amb el resultat a l'EDT.
	 * <p>
	 * Si la tasca llença una excepció, aquesta es registra al log però no es
	 * propaga. Per gestionar errors, usa
	 * {@link #executarEnFons(Supplier, Consumer, Consumer)}.
	 *
	 * @param <T> Tipus del resultat de la tasca.
	 * @param tasca Tasca a executar en segon pla.
	 * @param alAcabar Callback cridat a l'EDT amb el resultat quan la tasca finalitza.
	 */
	public static <T> void executarEnFons(Supplier<T> tasca, Consumer<T> alAcabar) {
		executarEnFons(tasca, alAcabar, null);
	}

	/**
	 * Executa una tasca en un fil de segon pla i, quan acaba, crida {@code alAcabar}
	 * o {@code alError} a l'EDT segons si ha tingut èxit o ha fallat.
	 *
	 * @param <T> Tipus del resultat de la tasca.
	 * @param tasca Tasca a executar en segon pla.
	 * @param alAcabar Callback cridat a l'EDT amb el resultat quan la tasca finalitza correctament.
	 * @param alError Callback cridat a l'EDT amb la causa quan la tasca llença una excepció.
	 *                Pot ser {@code null} si no interessa gestionar errors explícitament.
	 */
	public static <T> void executarEnFons(Supplier<T> tasca, Consumer<T> alAcabar,
			Consumer<Throwable> alError) {

		Objects.requireNonNull(tasca, "La tasca no pot ser null.");
		Objects.requireNonNull(alAcabar, "El callback d'acabament no pot ser null.");

		new SwingWorker<T, Void>() {

			@Override
			protected T doInBackground() {
				return tasca.get();
			}

			@Override
			protected void done() {
				gestionarResultat(this, alAcabar, alError);
			}

		}.execute();
	}

	//-------------------------------
	// EXECUCIÓ AMB BARRA DE PROGRÉS
	//-------------------------------

	/**
	 * Executa una tasca en segon pla mostrant un diàleg modal amb una barra de
	 * progrés indeterminada. Quan la tasca acaba, el diàleg es tanca i es crida
	 * {@code alAcabar} a l'EDT.
	 * <p>
	 * Si la tasca llença una excepció, aquesta es registra al log però no es
	 * propaga. Per gestionar errors, usa
	 * {@link #executarAmbBarraProgres(Component, String, Supplier, Consumer, Consumer)}.
	 *
	 * @param <T> Tipus del resultat de la tasca.
	 * @param pare Component pare del diàleg de progrés. Pot ser {@code null}.
	 * @param titol Títol del diàleg de progrés.
	 * @param tasca Tasca a executar en segon pla.
	 * @param alAcabar Callback cridat a l'EDT amb el resultat quan la tasca finalitza.
	 */
	public static <T> void executarAmbBarraProgres(Component pare, String titol,
			Supplier<T> tasca, Consumer<T> alAcabar) {
		executarAmbBarraProgres(pare, titol, tasca, alAcabar, null);
	}

	/**
	 * Executa una tasca en segon pla mostrant un diàleg modal amb una barra de
	 * progrés indeterminada. Quan la tasca acaba, el diàleg es tanca i es crida
	 * {@code alAcabar} o {@code alError} a l'EDT.
	 * <p>
	 * Tot el cicle (creació del diàleg, execució del worker i visualització) es
	 * gestiona a l'EDT gràcies a {@link EdtSwing#executarMesTard(Runnable)}.
	 * El worker s'inicia <em>abans</em> de mostrar el diàleg per evitar la condició
	 * de cursa en la qual la tasca acabés abans que el diàleg fos visible.
	 *
	 * @param <T> Tipus del resultat de la tasca.
	 * @param pare Component pare del diàleg de progrés. Pot ser {@code null}.
	 * @param titol Títol del diàleg de progrés.
	 * @param tasca Tasca a executar en segon pla.
	 * @param alAcabar Callback cridat a l'EDT amb el resultat quan la tasca finalitza correctament.
	 * @param alError Callback cridat a l'EDT amb la causa quan la tasca llença una excepció.
	 *                Pot ser {@code null} si no interessa gestionar errors explícitament.
	 */
	public static <T> void executarAmbBarraProgres(Component pare, String titol,
			Supplier<T> tasca, Consumer<T> alAcabar, Consumer<Throwable> alError) {

		Objects.requireNonNull(tasca, "La tasca no pot ser null.");
		Objects.requireNonNull(alAcabar, "El callback d'acabament no pot ser null.");

		/*
		 * Tot s'agenda a l'EDT amb invokeLater. Quan el diàleg es mostra modalment,
		 * Swing obre un bucle d'esdeveniments niuat: done() es processa dins d'aquest
		 * bucle i pot tancar el diàleg, desblocant la crida a setVisible(true).
		 */
		EdtSwing.executarMesTard(() -> {

			JDialog dialeg = crearDialegProgres(pare, titol);

			SwingWorker<T, Void> worker = new SwingWorker<>() {

				@Override
				protected T doInBackground() {
					return tasca.get();
				}

				@Override
				protected void done() {
					// Tancar el diàleg primer per alliberar el bloqueig modal.
					dialeg.dispose();
					gestionarResultat(this, alAcabar, alError);
				}
			};

			// Iniciar el worker abans de setVisible per evitar la condició de cursa.
			worker.execute();
			dialeg.setVisible(true);
		});
	}

	//-------------------------------
	// MÈTODES PRIVATS
	//-------------------------------

	/**
	 * Obté el resultat d'un {@link SwingWorker} finalitzat i el propaga als callbacks.
	 * <p>
	 * Ha de cridar-se sempre des del mètode {@code done()} del worker, és a dir,
	 * des de l'EDT. Si la tasca ha llençat una {@link ExecutionException}, es
	 * desempaqueta per propagar la causa original al callback {@code alError}.
	 *
	 * @param <T> Tipus del resultat.
	 * @param worker Worker del qual obtenir el resultat.
	 * @param alAcabar Callback cridat amb el resultat si la tasca ha acabat correctament.
	 * @param alError Callback cridat amb la causa si la tasca ha fallat. Pot ser {@code null}.
	 */
	private static <T> void gestionarResultat(SwingWorker<T, Void> worker,
			Consumer<T> alAcabar, Consumer<Throwable> alError) {
		try {
			alAcabar.accept(worker.get());

		} catch(InterruptedException e) {
			Thread.currentThread().interrupt();
			LOGGER.log(Level.WARNING, e, () -> "Tasca en segon pla interrompuda.");
			if(alError != null) {
				alError.accept(e);
			}

		} catch(ExecutionException e) {
			/* 
			 * ExecutionException embolcalla l'excepció original llençada per doInBackground();
			 * la desempaquetem per expedir la causa real al callback.
			 */
			Throwable causa = e.getCause();
			LOGGER.log(Level.SEVERE, causa, () -> "Error en la tasca executada en segon pla.");
			if(alError != null) {
				alError.accept(causa);
			}
		}
	}

	/**
	 * Crea un diàleg modal amb una barra de progrés indeterminada.
	 * El diàleg no es pot tancar manualment ({@code DO_NOTHING_ON_CLOSE}).
	 *
	 * @param pare Component pare. Pot ser {@code null}.
	 * @param titol Títol del diàleg.
	 * @return Diàleg creat, però encara no visible.
	 */
	private static JDialog crearDialegProgres(Component pare, String titol) {

		// Cercar la finestra arrel per associar-hi el diàleg correctament.
		Window finestra = pare != null ? SwingUtilities.getWindowAncestor(pare) : null;

		JDialog dialeg = finestra != null
				? new JDialog(finestra, titol, Dialog.ModalityType.APPLICATION_MODAL)
				: new JDialog((Frame) null, titol, true);

		JLabel etiqueta = new JLabel(titol, SwingConstants.CENTER);
		JProgressBar barra = new JProgressBar();
		barra.setIndeterminate(true);

		dialeg.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		dialeg.setLayout(new BorderLayout(10, 10));
		dialeg.add(etiqueta, BorderLayout.NORTH);
		dialeg.add(barra, BorderLayout.CENTER);
		dialeg.setSize(320, 90);
		dialeg.setLocationRelativeTo(pare);
		dialeg.setResizable(false);

		return dialeg;
	}

}
