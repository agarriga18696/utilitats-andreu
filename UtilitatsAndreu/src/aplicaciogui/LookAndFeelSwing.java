package aplicaciogui;

import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Classe d'utilitat per aplicar Look and Feel en aplicacions Swing.
 * 
 * @author Andreu
 * @version 1.0
 */
public final class LookAndFeelSwing {

	//-------------------------------
	// ATRIBUTS ESTÀTICS
	//-------------------------------

	private static final Logger LOGGER = Logger.getLogger(LookAndFeelSwing.class.getName());

	private LookAndFeelSwing() {
		/*
		 * Classe d'utilitat no instanciable.
		 */
	}

	//-------------------------------
	// LOOK AND FEEL
	//-------------------------------

	/**
	 * Aplica el Look and Feel del sistema operatiu.
	 * 
	 * @return {@code true} si s'ha aplicat correctament.
	 */
	public static boolean aplicarSistema() {
		return aplicar(UIManager.getSystemLookAndFeelClassName());
	}

	/**
	 * Aplica el Look and Feel de Java (Metal).
	 * 
	 * @return {@code true} si s'ha aplicat correctament.
	 */
	public static boolean aplicarMetal() {
		return aplicar("javax.swing.plaf.metal.MetalLookAndFeel");
	}

	/**
	 * Aplica el Look and Feel Nimbus.
	 * 
	 * @return {@code true} si s'ha aplicat correctament.
	 */
	public static boolean aplicarNimbus() {
		return aplicar("javax.swing.plaf.nimbus.NimbusLookAndFeel");
	}

	/**
	 * Aplica un Look and Feel a partir del nom complet de la classe.
	 * 
	 * @param nomClasse Nom complet de la classe del Look and Feel.
	 * @return {@code true} si s'ha aplicat correctament.
	 */
	public static boolean aplicar(String nomClasse) {

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

}
