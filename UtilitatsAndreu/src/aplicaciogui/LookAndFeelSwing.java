package aplicaciogui;

import java.awt.Component;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Classe d'utilitat per aplicar Look and Feel en aplicacions Swing.
 * 
 * @author Andreu
 * @version 1.3
 */
public final class LookAndFeelSwing {

	private LookAndFeelSwing() {
		/*
		 * Classe d'utilitat no instanciable.
		 */
	}

	//-------------------------------
	// ATRIBUTS ESTÀTICS
	//-------------------------------

	private static final Logger LOGGER = Logger.getLogger(LookAndFeelSwing.class.getName());
	
	private static final Map<String, Boolean> CACHE_COMPATIBILITAT = new ConcurrentHashMap<>();

	//-------------------------------
	// CONSTANTS LOOK AND FEEL
	//-------------------------------

	public static final String SISTEMA = UIManager.getSystemLookAndFeelClassName();
	public static final String METAL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public static final String NIMBUS = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
	public static final String MOTIF = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
	public static final String WINDOWS = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public static final String WINDOWS_CLASSIC = "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel";
	public static final String GTK = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
	public static final String MAC_OS = "com.apple.laf.AquaLookAndFeel";

	//-------------------------------
	// LOOK AND FEEL
	//-------------------------------

	/**
	 * Aplica el Look and Feel del sistema operatiu.
	 * 
	 * @return {@code true} si s'ha aplicat correctament.
	 */
	public static boolean aplicarSistema() {
		return aplicar(SISTEMA);
	}

	/**
	 * Aplica el Look and Feel de Java (Metal).
	 * 
	 * @return {@code true} si s'ha aplicat correctament.
	 */
	public static boolean aplicarMetal() {
		return aplicar(METAL);
	}

	/**
	 * Aplica el Look and Feel Nimbus.
	 * 
	 * @return {@code true} si s'ha aplicat correctament.
	 */
	public static boolean aplicarNimbus() {
		return aplicar(NIMBUS);
	}

	/**
	 * Aplica el Look and Feel Motif.
	 * 
	 * @return {@code true} si s'ha aplicat correctament.
	 */
	public static boolean aplicarMotif() {
		return aplicar(MOTIF);
	}

	/**
	 * Aplica el Look and Feel Windows.
	 * 
	 * @return {@code true} si s'ha aplicat correctament.
	 */
	public static boolean aplicarWindows() {
		return aplicar(WINDOWS);
	}

	/**
	 * Aplica el Look and Feel Windows Classic.
	 * 
	 * @return {@code true} si s'ha aplicat correctament.
	 */
	public static boolean aplicarWindowsClassic() {
		return aplicar(WINDOWS_CLASSIC);
	}

	/**
	 * Aplica un Look and Feel a partir del nom complet de la classe.
	 * <p>
	 * Aquesta operació muta {@link UIManager} i, segons el contracte de Swing,
	 * ha d'executar-se al fil d'esdeveniments (EDT). Si la crida es fa des d'un
	 * altre fil, es redirigeix automàticament a l'EDT amb {@link SwingUtilities#invokeAndWait}
	 * perquè el cridant pugui confiar en el valor retornat.
	 *
	 * @param nomClasse Nom complet de la classe del Look and Feel.
	 * @return {@code true} si s'ha aplicat correctament.
	 */
	public static boolean aplicar(String nomClasse) {

		if(nomClasse == null || nomClasse.isBlank()) {
			return false;
		}

		AtomicBoolean resultat = new AtomicBoolean(false);

		executarEnEdt(() -> {
			try {
				UIManager.setLookAndFeel(nomClasse);
				resultat.set(true);

			} catch(Exception e) {
				LOGGER.log(Level.WARNING, e, () -> "No s'ha pogut aplicar el Look and Feel: " + nomClasse);
				resultat.set(false);
			}
		});

		return resultat.get();
	}

	/**
	 * Actualitza visualment un component i tots els seus fills després de canviar el Look and Feel.
	 * <p>
	 * Totes les operacions Swing es redirigeixen a l'EDT si la crida es fa des d'un altre fil.
	 *
	 * @param component Component principal a actualitzar.
	 */
	public static void actualitzar(Component component) {

		if(component == null) {
			return;
		}

		executarEnEdt(() -> {
			SwingUtilities.updateComponentTreeUI(component);
			component.revalidate();
			component.repaint();
		});
	}

	//-------------------------------
	// HELPERS EDT
	//-------------------------------

	/**
	 * Executa una tasca al fil d'esdeveniments (EDT) de manera síncrona.
	 * <p>
	 * Si la crida ja és a l'EDT s'executa directament. En cas contrari, es delega a
	 * {@link SwingUtilities#invokeAndWait} perquè el flux de crida pugui confiar
	 * que la tasca ha acabat abans de continuar.
	 *
	 * @param tasca Tasca a executar a l'EDT.
	 */
	private static void executarEnEdt(Runnable tasca) {

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

	//-------------------------------
	// CONSULTES
	//-------------------------------

	/**
	 * Comprova si un Look and Feel està instal·lat.
	 * 
	 * @param nomClasse Nom complet de la classe del Look and Feel.
	 * @return {@code true} si el Look and Feel apareix entre els instal·lats.
	 */
	public static boolean estaInstallat(String nomClasse) {

		if(nomClasse == null || nomClasse.isBlank()) {
			return false;
		}

		for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			if(info.getClassName().equals(nomClasse)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Comprova si un Look and Feel es pot aplicar al sistema actual.
	 * <p>
	 * A diferència de versions anteriors, aquesta comprovació <b>no aplica</b> el Look and Feel:
	 * instancia la classe via reflexió i consulta {@link LookAndFeel#isSupportedLookAndFeel()},
	 * evitant qualsevol parpelleig visual o mutació de l'estat global de {@link UIManager}.
	 * <p>
	 * El resultat es memoritza al cache de manera atòmica amb {@code computeIfAbsent}.
	 *
	 * @param nomClasse Nom complet de la classe del Look and Feel.
	 * @return {@code true} si la classe existeix, és un {@link LookAndFeel} i està suportat.
	 */
	public static boolean esCompatible(String nomClasse) {

		if(nomClasse == null || nomClasse.isBlank()) {
			return false;
		}

		return CACHE_COMPATIBILITAT.computeIfAbsent(nomClasse, LookAndFeelSwing::comprovarCompatibilitat);
	}

	/**
	 * Comprovació real de compatibilitat sense aplicar el Look and Feel ni tocar {@link UIManager}.
	 *
	 * @param nomClasse Nom complet de la classe del Look and Feel.
	 * @return {@code true} si la classe es pot carregar, és un {@link LookAndFeel} i està suportat.
	 */
	private static boolean comprovarCompatibilitat(String nomClasse) {

		try {
			Class<?> classe = Class.forName(nomClasse);

			if(!LookAndFeel.class.isAssignableFrom(classe)) {
				return false;
			}

			LookAndFeel instancia = (LookAndFeel) classe.getDeclaredConstructor().newInstance();
			return instancia.isSupportedLookAndFeel();

		} catch(ClassNotFoundException e) {
			return false;

		} catch(ReflectiveOperationException | LinkageError e) {
			LOGGER.log(Level.FINE, e, () -> "No s'ha pogut comprovar la compatibilitat de: " + nomClasse);
			return false;
		}
	}

	/**
	 * Retorna els Look and Feel instal·lats al sistema.
	 * 
	 * @return Llista d'informació dels Look and Feel instal·lats.
	 */
	public static UIManager.LookAndFeelInfo[] getLookAndFeelsInstallats() {
		return UIManager.getInstalledLookAndFeels();
	}

	/**
	 * Retorna els noms visibles dels Look and Feel instal·lats.
	 * 
	 * @return Llista de noms dels Look and Feel instal·lats.
	 */
	public static List<String> getNomsLookAndFeelsInstallats() {

		List<String> noms = new ArrayList<>();

		for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			noms.add(info.getName());
		}

		return noms;
	}

	/**
	 * Cerca el nom de classe d'un Look and Feel instal·lat a partir del seu nom visible.
	 * 
	 * @param nom Nom visible del Look and Feel.
	 * @return Nom complet de la classe, o {@code null} si no s'ha trobat.
	 */
	public static String cercarClassePerNom(String nom) {

		if(nom == null || nom.isBlank()) {
			return null;
		}

		for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			if(info.getName().equalsIgnoreCase(nom)) {
				return info.getClassName();
			}
		}

		return null;
	}

	/**
	 * Retorna els temes predefinits de la llibreria.
	 * 
	 * @return Llista de temes predefinits.
	 */
	public static List<TemaLookAndFeelSwing> getTemesPredefinits() {
		return List.of(
				new TemaLookAndFeelSwing(
						"Sistema",
						SISTEMA,
						IconesPredeterminadesSwing.TEMA_JAVA,
						false
						),
				new TemaLookAndFeelSwing(
						"Nimbus",
						NIMBUS,
						IconesPredeterminadesSwing.TEMA_JAVA,
						false
						),
				new TemaLookAndFeelSwing(
						"Metal",
						METAL,
						IconesPredeterminadesSwing.TEMA_JAVA,
						false
						),
				new TemaLookAndFeelSwing(
						"Motif",
						MOTIF,
						IconesPredeterminadesSwing.TEMA_LINUX,
						false
						),
				new TemaLookAndFeelSwing(
						"GTK",
						GTK,
						IconesPredeterminadesSwing.TEMA_LINUX,
						false
						),
				new TemaLookAndFeelSwing(
						"Windows",
						WINDOWS,
						IconesPredeterminadesSwing.TEMA_WINDOWS,
						false
						),
				new TemaLookAndFeelSwing(
						"Windows Classic",
						WINDOWS_CLASSIC,
						IconesPredeterminadesSwing.TEMA_WINDOWS,
						false
						),
				new TemaLookAndFeelSwing(
						"macOS",
						MAC_OS,
						IconesPredeterminadesSwing.TEMA_MAC_OS,
						false
						)
				);
	}
	
	/**
	 * Retorna el nom de classe del Look and Feel actual.
	 * 
	 * @return Classe del Look and Feel actual.
	 */
	public static String getClasseActual() {
		return UIManager.getLookAndFeel().getClass().getName();
	}

}
