package io.github.agarriga18696.andreuutils.swing;

import java.awt.Component;
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
 * @version 1.5
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

		EdtSwing.executar(() -> {
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

		EdtSwing.executar(() -> {
			SwingUtilities.updateComponentTreeUI(component);
			component.revalidate();
			component.repaint();
		});
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
	 * <p>
	 * La comprovació es fa en dos passos. Primer consulta {@link UIManager#getInstalledLookAndFeels()},
	 * que és l'API oficial de Swing per determinar quins LAF estan disponibles al sistema actual
	 * (per exemple, Windows i Windows Classic només apareixen en Windows). Si el LAF hi és,
	 * es considera compatible directament sense cap instanciació per reflexió.
	 * <p>
	 * Si no hi és a la llista instal·lada (per exemple, un LAF de tercers), es recorre a
	 * instanciar la classe via reflexió i consultar {@link LookAndFeel#isSupportedLookAndFeel()}.
	 * En aquest segon pas es capturen tant {@link ReflectiveOperationException} com
	 * {@link RuntimeException} (per cobrir {@code InaccessibleObjectException} de Java 9+)
	 * i {@link LinkageError}.
	 *
	 * @param nomClasse Nom complet de la classe del Look and Feel.
	 * @return {@code true} si el LAF és compatible amb el sistema actual.
	 */
	private static boolean comprovarCompatibilitat(String nomClasse) {

		// Primer: consultar la llista oficial de LAFs instal·lats per Swing.
		// Aquests estan garantits com a disponibles al sistema sense necessitat de reflexió.
		for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			if(info.getClassName().equals(nomClasse)) {
				return true;
			}
		}

		// Segon: per LAFs no instal·lats per defecte (p. ex. de tercers),
		// intentar instanciar-los i consultar isSupportedLookAndFeel().
		try {
			Class<?> classe = Class.forName(nomClasse);

			if(!LookAndFeel.class.isAssignableFrom(classe)) {
				return false;
			}

			LookAndFeel instancia = (LookAndFeel) classe.getDeclaredConstructor().newInstance();
			return instancia.isSupportedLookAndFeel();

		} catch(ClassNotFoundException _) {
			return false;

		} catch(ReflectiveOperationException | RuntimeException | LinkageError e) {
			// RuntimeException cobreix InaccessibleObjectException (Java 9+) i similars
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
						IconesSwing.TEMA_JAVA,
						false
						),
				new TemaLookAndFeelSwing(
						"Nimbus",
						NIMBUS,
						IconesSwing.TEMA_JAVA,
						false
						),
				new TemaLookAndFeelSwing(
						"Metal",
						METAL,
						IconesSwing.TEMA_JAVA,
						false
						),
				new TemaLookAndFeelSwing(
						"Motif",
						MOTIF,
						IconesSwing.TEMA_LINUX,
						false
						),
				new TemaLookAndFeelSwing(
						"GTK",
						GTK,
						IconesSwing.TEMA_LINUX,
						false
						),
				new TemaLookAndFeelSwing(
						"Windows",
						WINDOWS,
						IconesSwing.TEMA_WINDOWS,
						false
						),
				new TemaLookAndFeelSwing(
						"Windows Classic",
						WINDOWS_CLASSIC,
						IconesSwing.TEMA_WINDOWS,
						false
						),
				new TemaLookAndFeelSwing(
						"macOS",
						MAC_OS,
						IconesSwing.TEMA_MAC_OS,
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
