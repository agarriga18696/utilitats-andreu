package utilitats;

import java.awt.*;
import java.util.function.Supplier;

import javax.swing.*;

/**
 * Classe d'utilitats per a components d'interfície gràfica.
 * 
 * @author Andreu
 * @version 1.0
 */

public final class GUI {
 private GUI() {
   /* This utility class should not be instantiated */
 }

	/*
	 * CONTENIDORS
	 */

	/**
	 * Retorna un JFrame configurat amb els paràmetres indicats.
	 */
	public static final JFrame frame(
			String title, 
			int width, 
			int height, 
			Component c,
			int operation) {

		if(width == 0 || height == 0) return null;

		JFrame frame = new JFrame(title);

		frame.setSize(width, height);
		frame.setLocationRelativeTo(c);
		frame.setDefaultCloseOperation(operation);

		return frame;
	}

	/**
	 * Retorna un JPanel amb GridLayout i les files i columnes especificades.
	 */
	public static final JPanel panelGrid(int rows, int cols) {
		if(rows == 0 || cols == 0) return null;
		return new JPanel(new GridLayout(rows, cols));
	}

	/**
	 * Retorna un JPanel amb GridLayout, les files i columnes especificades 
	 * i farcit amb els components creats pel Supplier indicat.
	 */
	public static final JPanel panelGrid(int rows, int cols, Supplier<JComponent> factory) {
		if(rows == 0 || cols == 0) return null;
		JPanel panel = new JPanel(new GridLayout(rows, cols));
		for(int i = 0; i < rows * cols; i++) {
			panel.add(factory.get()); // Crea una instància nova cada vegada, no reutilitza la mateixa.
		}
		return panel;
	}

	/**
	 * Retorna un JPanel amb GridLayout i les files, columnes i gap especificats.
	 */
	public static final JPanel panelGrid(int rows, int cols, int hgap, int vgap) {
		if(rows == 0 || cols == 0) return null;
		return new JPanel(new GridLayout(rows, cols, hgap, vgap));
	}

	/**
	 * Retorna un JPanel amb GridLayout, les files, columnes i gaps especificats
	 * i farcit amb components creats pel Supplier indicat.
	 */
	public static final JPanel panelGrid(int rows, int cols, int hgap, int vgap, Supplier<JComponent> factory) {
		if(rows == 0 || cols == 0) return null;
		JPanel panel = new JPanel(new GridLayout(rows, cols, hgap, vgap));
		for(int i = 0; i < rows * cols; i++) {
			panel.add(factory.get()); // Crea una instància nova cada vegada, no reutilitza la mateixa.
		}
		return panel;
	}

	/*
	 * FUNCIONALITATS
	 */

	/**
	 * Retorna el component de l'índex indicat si és del tipus especificat, 
	 * o {@code null} si no existeix o el tipus no coincideix.
	 */
	public static final <T extends Component> T getComponent(JComponent jcomp, int n, Class<T> type) {
		if(n < 0 || n >= jcomp.getComponentCount()) return null;
		Component comp = jcomp.getComponent(n);
		if(!type.isInstance(comp)) return null;
		return type.cast(comp);
	}
	

}
