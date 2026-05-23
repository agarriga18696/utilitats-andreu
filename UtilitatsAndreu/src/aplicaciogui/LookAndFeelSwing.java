package aplicaciogui;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Classe d'utilitat per aplicar Look and Feel en aplicacions Swing.
 * 
 * @author Andreu
 * @version 1.2
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
	 * 
	 * @param nomClasse Nom complet de la classe del Look and Feel.
	 * @return {@code true} si s'ha aplicat correctament.
	 */
	public static boolean aplicar(String nomClasse) {

		if(nomClasse == null || nomClasse.isBlank()) {
			return false;
		}

		try {
			UIManager.setLookAndFeel(nomClasse);
			return true;

		} catch(Exception e) {
			LOGGER.log(Level.WARNING, "No s'ha pogut aplicar el Look and Feel: " + nomClasse, e);
			return false;
		}
	}

	/**
	 * Actualitza visualment un component i tots els seus fills després de canviar el Look and Feel.
	 * 
	 * @param component Component principal a actualitzar.
	 */
	public static void actualitzar(Component component) {

		if(component == null) {
			return;
		}

		SwingUtilities.updateComponentTreeUI(component);
		component.revalidate();
		component.repaint();
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
	 * Comprova si un Look and Feel es pot aplicar realment.
	 * 
	 * @param nomClasse Nom complet de la classe del Look and Feel.
	 * @return {@code true} si es pot aplicar correctament.
	 */
	public static boolean esCompatible(String nomClasse) {

		if(nomClasse == null || nomClasse.isBlank()) {
			return false;
		}

		if(CACHE_COMPATIBILITAT.containsKey(nomClasse)) {
			return CACHE_COMPATIBILITAT.get(nomClasse);
		}

		LookAndFeel lookAndFeelActual = UIManager.getLookAndFeel();
		boolean compatible;

		try {
			UIManager.setLookAndFeel(nomClasse);
			compatible = true;

		} catch(Exception e) {
			compatible = false;

		} finally {
			if(lookAndFeelActual != null) {
				try {
					UIManager.setLookAndFeel(lookAndFeelActual);
				} catch(Exception e) {
					LOGGER.log(Level.WARNING, "No s'ha pogut restaurar el Look and Feel anterior.", e);
				}
			}
		}

		CACHE_COMPATIBILITAT.put(nomClasse, compatible);
		return compatible;
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
