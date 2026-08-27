package io.github.agarriga18696.andreuutils.swing;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

/**
 * Classe d'utilitat per executar codi al fil d'esdeveniments de Swing (EDT).
 * <p>
 * Tot el codi que modifica components Swing o l'estat de {@code UIManager} ha
 * d'executar-se a l'EDT. Aquesta classe simplifica el patró habitual de comprovar
 * si ja s'està a l'EDT i, en cas contrari, delegar a {@link SwingUtilities}.
 *
 * @author Andreu
 * @version 1.0
 */
public final class EdtSwing {

	//-------------------------------
	// ATRIBUTS ESTÀTICS
	//-------------------------------

	private static final Logger LOGGER = Logger.getLogger(EdtSwing.class.getName());

	private EdtSwing() {
		/*
		 * Classe d'utilitat no instanciable.
		 */
	}

	//-------------------------------
	// EXECUCIÓ A L'EDT
	//-------------------------------

	/**
	 * Executa una tasca a l'EDT de manera síncrona.
	 * <p>
	 * Si la crida ja és a l'EDT, la tasca s'executa directament. En cas contrari,
	 * es delega a {@link SwingUtilities#invokeAndWait(Runnable)} i el fil cridant
	 * queda bloquejat fins que la tasca finalitza.
	 * <p>
	 * Aquesta variant és apropiada quan el cridant necessita que l'efecte de la
	 * tasca estigui visible abans de continuar (per exemple, aplicar un Look and
	 * Feel i confiar en el seu valor de retorn).
	 *
	 * @param tasca Tasca a executar a l'EDT.
	 */
	public static void executar(Runnable tasca) {

		if(tasca == null) {
			return;
		}

		if(SwingUtilities.isEventDispatchThread()) {
			tasca.run();
			return;
		}

		try {
			SwingUtilities.invokeAndWait(tasca);

		} catch(InterruptedException e) {
			Thread.currentThread().interrupt();
			LOGGER.log(Level.WARNING, e, () -> "Fil interromput esperant l'EDT.");

		} catch(InvocationTargetException e) {
			LOGGER.log(Level.WARNING, e.getCause(), () -> "Excepció dins la tasca executada a l'EDT.");
		}
	}

	/**
	 * Encua una tasca per a la seva execució asíncrona a l'EDT.
	 * <p>
	 * Equivalent a {@link SwingUtilities#invokeLater(Runnable)}: la tasca s'executarà
	 * en algun moment futur i el fil cridant continua immediatament. Útil quan no
	 * necessites esperar el resultat.
	 *
	 * @param tasca Tasca a encuar.
	 */
	public static void executarMesTard(Runnable tasca) {

		if(tasca == null) {
			return;
		}

		SwingUtilities.invokeLater(tasca);
	}

	//-------------------------------
	// CONSULTES
	//-------------------------------

	/**
	 * Indica si el fil actual és l'EDT.
	 *
	 * @return {@code true} si el fil actual és el d'esdeveniments de Swing.
	 */
	public static boolean esEdt() {
		return SwingUtilities.isEventDispatchThread();
	}

}
