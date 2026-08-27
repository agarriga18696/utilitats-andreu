package io.github.agarriga18696.andreuutils.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Window;
import java.awt.Dialog;
import java.awt.FlowLayout;

import java.io.File;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import io.github.agarriga18696.andreuutils.core.ArrayUtils;

/**
 * Classe d'utilitat per mostrar diàlegs Swing.
 * <p>
 * Tots els mètodes són segurs per cridar des de qualsevol fil: si la crida no
 * es fa des de l'EDT, es redirigeix automàticament amb {@link EdtSwing#runAndWait(Runnable)}.
 *
 * @author Andreu
 * @version 1.7
 */
public final class DialegsSwing {

	private DialegsSwing() {
		/*
		 * Classe d'utilitat no instanciable.
		 */
	}

	//-------------------------------
	// DIÀLEGS SIMPLES
	//-------------------------------

	/**
	 * Mostra un missatge informatiu.
	 *
	 * @param pare Component pare del diàleg.
	 * @param titol Títol del diàleg.
	 * @param missatge Missatge a mostrar.
	 */
	public static void info(Component pare, String titol, String missatge) {

		/*
		 * Executar l'acció en un fil paralel.
		 */
		EdtSwing.runAndWait(() -> JOptionPane.showMessageDialog(
				pare,
				missatge,
				titol,
				JOptionPane.INFORMATION_MESSAGE
				));
	}

	/**
	 * Mostra un missatge d'avís.
	 *
	 * @param pare Component pare del diàleg.
	 * @param titol Títol del diàleg.
	 * @param missatge Missatge a mostrar.
	 */
	public static void avis(Component pare, String titol, String missatge) {

		/*
		 * Executar l'acció en un fil paralel.
		 */
		EdtSwing.runAndWait(() -> JOptionPane.showMessageDialog(
				pare,
				missatge,
				titol,
				JOptionPane.WARNING_MESSAGE
				));
	}

	/**
	 * Mostra un missatge d'error.
	 *
	 * @param pare Component pare del diàleg.
	 * @param titol Títol del diàleg.
	 * @param missatge Missatge a mostrar.
	 */
	public static void error(Component pare, String titol, String missatge) {

		/*
		 * Executar l'acció en un fil paralel.
		 */
		EdtSwing.runAndWait(() -> JOptionPane.showMessageDialog(
				pare,
				missatge,
				titol,
				JOptionPane.ERROR_MESSAGE
				));
	}

	/**
	 * Mostra un diàleg de confirmació amb les opcions "Sí" i "No".
	 *
	 * @param pare Component pare del diàleg.
	 * @param titol Títol del diàleg.
	 * @param missatge Missatge de confirmació.
	 * @return {@code true} si l'usuari prem Sí.
	 */
	public static boolean confirmar(Component pare, String titol, String missatge) {

		AtomicBoolean resultat = new AtomicBoolean(false);

		/*
		 * Executar l'acció en un fil paralel.
		 */
		EdtSwing.runAndWait(() -> {

			/*
			 * Canviar el nom de les opcions.
			 */
			Object[] opcions = {
					"Sí",
					"No"
			};

			int resposta = JOptionPane.showOptionDialog(
					pare,
					missatge,
					titol,
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					opcions,
					opcions[0]
					);

			resultat.set(resposta == JOptionPane.YES_OPTION);
		});

		return resultat.get();
	}

	//-------------------------------
	// DIÀLEGS AMB TEXT LLARG
	//-------------------------------

	/**
	 * Mostra una finestra modal amb text llarg i scroll.
	 * <p>
	 * La construcció i la visualització del diàleg es fan a l'EDT. Si la crida
	 * es fa des d'un altre fil, es redirigeix automàticament amb
	 * {@link EdtSwing#runAndWait(Runnable)}. Com que el diàleg és modal,
	 * {@code setVisible(true)} bloqueja l'EDT fins que l'usuari el tanca,
	 * i el fil cridant (si és diferent de l'EDT) també queda bloquejat.
	 *
	 * @param pare Component pare del diàleg.
	 * @param titol Títol del diàleg.
	 * @param contingut Text a mostrar.
	 * @param amplada Amplada del diàleg.
	 * @param altura Altura del diàleg.
	 */
	public static void textLlarg(Component pare, String titol, String contingut, int amplada, int altura) {

		/*
		 * Executar l'acció en un fil paralel.
		 */
		EdtSwing.runAndWait(() -> mostrarTextLlarg(
				pare, 
				titol, 
				contingut, 
				amplada, 
				altura)
				);
	}

	/**
	 * Construcció i visualització real del diàleg, sempre invocada a l'EDT.
	 */
	private static void mostrarTextLlarg(Component pare, String titol, String contingut, int amplada, int altura) {

		Window finestraPare = null;

		if(pare instanceof Window window) {
			finestraPare = window;
		} else if(pare != null) {
			finestraPare = SwingUtilities.getWindowAncestor(pare);
		}

		JDialog dialeg = new JDialog(
				finestraPare,
				titol,
				Dialog.ModalityType.APPLICATION_MODAL
				);

		dialeg.setLayout(new BorderLayout());
		dialeg.setSize(amplada, altura);
		dialeg.setLocationRelativeTo(pare);

		JTextArea txtContingut = new JTextArea(contingut);
		txtContingut.setEditable(false);
		txtContingut.setLineWrap(true);
		txtContingut.setWrapStyleWord(true);
		txtContingut.setCaretPosition(0);

		dialeg.add(new JScrollPane(txtContingut), BorderLayout.CENTER);

		JButton btnTancar = new JButton("Tancar");
		btnTancar.addActionListener(_ -> dialeg.dispose());

		JPanel pnlBotons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pnlBotons.add(btnTancar);

		dialeg.add(pnlBotons, BorderLayout.SOUTH);
		dialeg.setVisible(true);
	}

	//-------------------------------
	// DIÀLEGS D'ENTRADA
	//-------------------------------

	/**
	 * Mostra un diàleg d'entrada de text.
	 *
	 * @param pare Component pare del diàleg.
	 * @param titol Títol del diàleg.
	 * @param missatge Missatge a mostrar.
	 * @return Text introduït per l'usuari, o {@link Optional#empty()} si cancel·la.
	 */
	public static Optional<String> input(Component pare, String titol, String missatge) {

		AtomicReference<String> resultat = new AtomicReference<>();

		/*
		 * Executar l'acció en un fil paralel.
		 */
		EdtSwing.runAndWait(() -> {
			String resposta = JOptionPane.showInputDialog(
					pare,
					missatge,
					titol,
					JOptionPane.QUESTION_MESSAGE
					);
			resultat.set(resposta);
		});

		return Optional.ofNullable(resultat.get());
	}

	/**
	 * Mostra un diàleg d'entrada de text amb un valor per defecte preescrit.
	 *
	 * @param pare Component pare del diàleg.
	 * @param titol Títol del diàleg.
	 * @param missatge Missatge a mostrar.
	 * @param valorDefecte Text preescrit al camp d'entrada.
	 * @return Text introduït per l'usuari, o {@link Optional#empty()} si cancel·la.
	 */
	public static Optional<String> input(Component pare, String titol, String missatge, String valorDefecte) {

		AtomicReference<String> resultat = new AtomicReference<>();

		/*
		 * Executar l'acció en un fil paralel.
		 */
		EdtSwing.runAndWait(() -> {
			String resposta = (String) JOptionPane.showInputDialog(
					pare,
					missatge,
					titol,
					JOptionPane.QUESTION_MESSAGE,
					null,
					null,
					valorDefecte
					);
			resultat.set(resposta);
		});

		return Optional.ofNullable(resultat.get());
	}

	//-------------------------------
	// SELECTOR D'OPCIONS
	//-------------------------------

	/**
	 * Mostra un diàleg de selecció amb un desplegable d'opcions.
	 * 
	 * @param pare Component pare del diàleg.
	 * @param titol Títol del diàleg.
	 * @param missatge Missatge a mostrar.
	 * @param opcions Array d'opcions a mostrar al desplegable.
	 * @return Opció seleccionada per l'usuari, o {@link Optional#empty()} si cancel·la o l'array és buit.
	 */
	public static Optional<String> seleccionar(Component pare, String titol, String missatge, String[] opcions) {

		AtomicReference<String> resultat = new AtomicReference<>();

		if(ArrayUtils.isEmpty(opcions)) {
			return Optional.empty();
		}

		/*
		 * Executar l'acció en un fil paralel.
		 */
		EdtSwing.runAndWait(() -> {
			String resposta = (String) JOptionPane.showInputDialog(
					pare,
					missatge,
					titol,
					JOptionPane.QUESTION_MESSAGE,
					null,
					opcions, // array d'opcions (desplegable)
					opcions[0] // opció seleccionada per defecte
					);
			resultat.set(resposta);
		});

		return Optional.ofNullable(resultat.get());
	}

	//-------------------------------
	// SELECTORS DE FITXER
	//-------------------------------

	/**
	 * Mostra un diàleg per triar on guardar un fitxer (sense filtre d'extensió).
	 *
	 * @param pare Component pare del diàleg.
	 * @return Fitxer triat, o {@link Optional#empty()} si l'usuari cancel·la.
	 */
	public static Optional<File> triarFitxerGuardar(Component pare) {
		return triarFitxerGuardar(pare, null, null, null);
	}

	/**
	 * Mostra un diàleg per triar on guardar un fitxer amb filtre per extensió.
	 * La descripció del filtre es genera automàticament a partir de l'extensió.
	 *
	 * @param pare Component pare del diàleg.
	 * @param extensio Extensió del fitxer sense punt (p. ex. {@code "txt"}).
	 * @return Fitxer triat, o {@link Optional#empty()} si l'usuari cancel·la.
	 */
	public static Optional<File> triarFitxerGuardar(Component pare, String extensio) {
		return triarFitxerGuardar(pare, generarDescripcio(extensio), extensio, null);
	}

	/**
	 * Mostra un diàleg per triar on guardar un fitxer amb filtre per extensió i descripció.
	 *
	 * @param pare Component pare del diàleg.
	 * @param descripcio Descripció del filtre (p. ex. {@code "Fitxers de text"}).
	 * @param extensio Extensió del fitxer sense punt (p. ex. {@code "txt"}).
	 * @return Fitxer triat, o {@link Optional#empty()} si l'usuari cancel·la.
	 */
	public static Optional<File> triarFitxerGuardar(Component pare, String descripcio, String extensio) {
		return triarFitxerGuardar(pare, descripcio, extensio, null);
	}

	/**
	 * Mostra un diàleg per triar on guardar un fitxer.
	 * <p>
	 * Si {@code extensio} no és nul·la i el nom de fitxer triat no acaba amb ella,
	 * s'afegeix automàticament.
	 * <p>
	 * Si el fitxer ja existeix, es demana confirmació abans de sobreescriure'l.
	 *
	 * @param pare Component pare del diàleg.
	 * @param descripcio Descripció del filtre, o {@code null} per a cap filtre.
	 * @param extensio Extensió del fitxer sense punt (p. ex. {@code "txt"}), o {@code null} per a cap filtre.
	 * @param nomDefecte Nom de fitxer preseleccionat, o {@code null} per a cap.
	 * @return Fitxer triat, o {@link Optional#empty()} si l'usuari cancel·la.
	 */
	public static Optional<File> triarFitxerGuardar(
			Component pare,
			String descripcio,
			String extensio,
			String nomDefecte
			) {
		return triarFitxerGuardar(pare, descripcio, extensio, nomDefecte, null);
	}

	/**
	 * Mostra un diàleg per triar on guardar un fitxer, amb directori inicial.
	 * <p>
	 * Si {@code extensio} no és nul·la i el nom de fitxer triat no acaba amb ella,
	 * s'afegeix automàticament.
	 * <p>
	 * Si el fitxer ja existeix, es demana confirmació abans de sobreescriure'l.
	 *
	 * @param pare Component pare del diàleg.
	 * @param descripcio Descripció del filtre, o {@code null} per a cap filtre.
	 * @param extensio Extensió del fitxer sense punt (p. ex. {@code "txt"}), o {@code null} per a cap filtre.
	 * @param nomDefecte Nom de fitxer preseleccionat, o {@code null} per a cap.
	 * @param directoriInici Directori on s'obre el selector, o {@code null} per al directori per defecte.
	 * @return Fitxer triat, o {@link Optional#empty()} si l'usuari cancel·la.
	 */
	public static Optional<File> triarFitxerGuardar(
			Component pare,
			String descripcio,
			String extensio,
			String nomDefecte,
			File directoriInici
			) {

		AtomicReference<File> resultat = new AtomicReference<>();

		EdtSwing.runAndWait(() -> {

			JFileChooser selector = crearSelector(descripcio, extensio, nomDefecte);
			selector.setDialogTitle("Guardar fitxer");

			if(directoriInici != null && directoriInici.exists()) {
				selector.setCurrentDirectory(directoriInici);
			}

			int opcio = selector.showSaveDialog(pare);

			if(opcio == JFileChooser.APPROVE_OPTION) {
				File fitxer = selector.getSelectedFile();
				if(extensio != null && !extensio.isBlank()) {
					fitxer = assegurarExtensio(fitxer, extensio);
				}

				/*
				 * Confirmar sobreescriptura.
				 */
				if(fitxer.exists()) {
					int resposta = JOptionPane.showConfirmDialog(
							pare,
							"El fitxer \"" + fitxer.getName() + "\" ja existeix.\nVols sobreescriure'l?",
							"Confirmar sobreescriptura",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.WARNING_MESSAGE
							);

					if(resposta != JOptionPane.YES_OPTION) {
						return;
					}
				}

				resultat.set(fitxer);
			}
		});

		return Optional.ofNullable(resultat.get());
	}

	/**
	 * Mostra un diàleg per triar un fitxer a carregar (sense filtre d'extensió).
	 *
	 * @param pare Component pare del diàleg.
	 * @return Fitxer triat, o {@link Optional#empty()} si l'usuari cancel·la.
	 */
	public static Optional<File> triarFitxerCarregar(Component pare) {
		return triarFitxerCarregar(pare, null, null);
	}

	/**
	 * Mostra un diàleg per triar un fitxer a carregar amb filtre per extensió.
	 * La descripció del filtre es genera automàticament a partir de l'extensió.
	 *
	 * @param pare Component pare del diàleg.
	 * @param extensio Extensió del fitxer sense punt (p. ex. {@code "txt"}).
	 * @return Fitxer triat, o {@link Optional#empty()} si l'usuari cancel·la.
	 */
	public static Optional<File> triarFitxerCarregar(Component pare, String extensio) {
		return triarFitxerCarregar(pare, generarDescripcio(extensio), extensio);
	}

	/**
	 * Mostra un diàleg per triar un fitxer a carregar amb filtre per extensió i descripció.
	 *
	 * @param pare Component pare del diàleg.
	 * @param descripcio Descripció del filtre (p. ex. {@code "Fitxers de text"}).
	 * @param extensio Extensió del fitxer sense punt (p. ex. {@code "txt"}).
	 * @return Fitxer triat, o {@link Optional#empty()} si l'usuari cancel·la.
	 */
	public static Optional<File> triarFitxerCarregar(Component pare, String descripcio, String extensio) {
		return triarFitxerCarregar(pare, descripcio, extensio, null);
	}

	/**
	 * Mostra un diàleg per triar un fitxer a carregar amb filtre per extensió, descripció i directori inicial.
	 *
	 * @param pare Component pare del diàleg.
	 * @param descripcio Descripció del filtre (p. ex. {@code "Fitxers de text"}), o {@code null} per a cap filtre.
	 * @param extensio Extensió del fitxer sense punt (p. ex. {@code "txt"}), o {@code null} per a cap filtre.
	 * @param directoriInici Directori on s'obre el selector, o {@code null} per al directori per defecte.
	 * @return Fitxer triat, o {@link Optional#empty()} si l'usuari cancel·la.
	 */
	public static Optional<File> triarFitxerCarregar(
			Component pare,
			String descripcio,
			String extensio,
			File directoriInici
			) {

		AtomicReference<File> resultat = new AtomicReference<>();

		EdtSwing.runAndWait(() -> {

			JFileChooser selector = crearSelector(descripcio, extensio, null);
			selector.setDialogTitle("Obrir fitxer");

			if(directoriInici != null && directoriInici.exists()) {
				selector.setCurrentDirectory(directoriInici);
			}

			int opcio = selector.showOpenDialog(pare);

			if(opcio == JFileChooser.APPROVE_OPTION) {
				resultat.set(selector.getSelectedFile());
			}
		});

		return Optional.ofNullable(resultat.get());
	}

	//-------------------------------
	// SELECTORS DE DIRECTORI
	//-------------------------------

	/**
	 * Mostra un diàleg per triar un directori.
	 *
	 * @param pare Component pare del diàleg.
	 * @return Directori triat, o {@link Optional#empty()} si l'usuari cancel·la.
	 */
	public static Optional<File> triarDirectori(Component pare) {
		return triarDirectori(pare, null);
	}

	/**
	 * Mostra un diàleg per triar un directori amb un directori inicial preseleccionat.
	 *
	 * @param pare Component pare del diàleg.
	 * @param directoriInici Directori on s'obre el selector, o {@code null} per al directori per defecte.
	 * @return Directori triat, o {@link Optional#empty()} si l'usuari cancel·la.
	 */
	public static Optional<File> triarDirectori(Component pare, File directoriInici) {

		AtomicReference<File> resultat = new AtomicReference<>();

		/*
		 * Executar l'acció en un fil paralel.
		 */
		EdtSwing.runAndWait(() -> {

			JFileChooser selector = new JFileChooser();

			/*
			 * Només permetre directoris.
			 */
			selector.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			/*
			 * Definir el directori inicial, en cas que s'hagi especificat.
			 */
			if(directoriInici != null && directoriInici.exists()) {
				selector.setCurrentDirectory(directoriInici);
			}

			int opcio = selector.showOpenDialog(pare);

			if(opcio == JFileChooser.APPROVE_OPTION) {
				resultat.set(selector.getSelectedFile());
			}
		});

		return Optional.ofNullable(resultat.get());
	}

	//-------------------------------
	// MÈTODES PRIVATS
	//-------------------------------

	/**
	 * Crea un {@link JFileChooser} configurat amb el filtre i el nom de fitxer indicats.
	 *
	 * @param descripcio Descripció del filtre, o {@code null} per a cap filtre.
	 * @param extensio Extensió del fitxer sense punt, o {@code null} per a cap filtre.
	 * @param nomDefecte Nom de fitxer preseleccionat, o {@code null} per a cap.
	 * @return {@link JFileChooser} configurat.
	 */
	private static JFileChooser crearSelector(String descripcio, String extensio, String nomDefecte) {

		JFileChooser selector = new JFileChooser();

		if(descripcio != null && !descripcio.isBlank()
				&& extensio != null && !extensio.isBlank()) {
			/*
			 * Deshabilitar la llista per mostrar més extensions d'arxius
			 * que no sigui l'especificada per paràmetre.
			 */
			selector.removeChoosableFileFilter(selector.getAcceptAllFileFilter());
			selector.setFileFilter(new FileNameExtensionFilter(descripcio, extensio));
		}

		/*
		 * Aplicar l'extensió indicada per paràmetre.
		 */
		if(nomDefecte != null && !nomDefecte.isBlank()) {
			String nomAmbExtensio = (extensio != null && !extensio.isBlank())
					? nomDefecte + "." + extensio
							: nomDefecte;
			selector.setSelectedFile(new File(nomAmbExtensio));
		}

		return selector;
	}

	/**
	 * Assegura que el fitxer acabarà amb l'extensió indicada.
	 * Si el nom del fitxer ja acaba amb l'extensió (insensible a majúscules), es retorna tal qual.
	 *
	 * @param fitxer Fitxer a comprovar.
	 * @param extensio Extensió sense punt (p. ex. {@code "txt"}).
	 * @return Fitxer amb l'extensió afegida si cal.
	 */
	private static File assegurarExtensio(File fitxer, String extensio) {

		String nom = fitxer.getName();

		if(!nom.toLowerCase().endsWith("." + extensio.toLowerCase())) {
			return new File(fitxer.getParentFile(), nom + "." + extensio);
		}

		return fitxer;
	}

	/**
	 * Genera la descripció automàtica del filtre a partir de l'extensió.
	 *
	 * @param extensio Extensió sense punt, o {@code null}.
	 * @return Descripció del filtre, o {@code null} si l'extensió és nul·la o buida.
	 */
	private static String generarDescripcio(String extensio) {
		return (extensio != null && !extensio.isBlank())
				? "Fitxers *." + extensio.toLowerCase()
				: null;
	}

}
