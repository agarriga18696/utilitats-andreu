package utilitats;

import java.awt.*;

import javax.swing.*;

/**
 * Classe d'utilitats per a components d'interfície gràfica.
 * 
 * @author Andreu
 * @version 1.0
 */

public final class GUI {

	private GUI() {}
	
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
	 * Retorna un JPanel amb el Layout indicat per paràmetre.
	 */
	public static final JPanel panel(LayoutManager layout) {
		return new JPanel(layout);
	}
	
	/**
	 * Retorna un JPanel amb GridLayout i les files i columnes especificades.
	 */
	public static final JPanel panelGrid(int rows, int cols) {
		if(rows == 0 || cols == 0) return null;
		return new JPanel(new GridLayout(rows, cols));
	}
	
	/**
	 * Retorna un JPanel amb GridLayout i les files, columnes i gap especificats.
	 */
	public static final JPanel panelGrid(int rows, int cols, int hgap, int vgap) {
		if(rows == 0 || cols == 0) return null;
		return new JPanel(new GridLayout(rows, cols, hgap, vgap));
	}

	/*
	 * COMPONENTS
	 */
	
	/**
	 * Retorna un JLabel amb el text.
	 */
	public static final JLabel label(String text) {
		return new JLabel(text);
	}

	/**
	 * Retorna un JLabel amb el text i alineació.
	 */
	public static final JLabel label(String text, int horizontalAlignment) {
		return new JLabel(text, horizontalAlignment);
	}
	
	/**
	 * Retorna un JButton amb un text.
	 */
	public static final JButton button(String text) {
		return new JButton(text);
	}
	
	/*
	 * FUNCIONALITATS
	 */
	
	/**
	 * Afegeix un component al {@code panel} i el retorna.
	 */
	public static final JPanel add(JPanel panel, Component comp) {
		JPanel pnl = panel;
		pnl.add(comp);
		return pnl;
	}
	
	/**
	 * Afegeix els components passats per paràmetre al {@code panel} i el retorna.
	 */
	public static final JPanel add(JPanel panel, Component... comp) {
		JPanel pnl = panel;
		for(Component c : comp) {
			pnl.add(c);
		}
		return pnl;
	}
	
	/**
	 * Retorna un JButton de dins un JComponent.
	 */
	public static final JButton getButton(JComponent jcomp, int n) {
		if(!(jcomp.getComponent(n) instanceof JButton _)) return null;
		return (JButton) jcomp.getComponent(n);
	}
	
	/**
	 * Retorna un JPanel amb GridLayout, les files i columnes especificades i farcit amb el JComponent especificat.
	 */
	public static final JPanel panelGridComp(int rows, int cols, JComponent jcomp) {
		if(rows == 0 || cols == 0) return null;
		JPanel panel = new JPanel(new GridLayout(rows, cols));
		for(int i = 0; i < rows * cols; i++) {
			panel.add(jcomp);
		}
		return panel;
	}
	
	/**
	 * Retorna un JPanel amb GridLayout, les files i columnes especificades, gaps i farcit amb el JComponent especificat.
	 */
	public static final JPanel panelGridComp(int rows, int cols, int hgap, int vgap, JComponent jcomp) {
		if(rows == 0 || cols == 0) return null;
		JPanel panel = new JPanel(new GridLayout(rows, cols, hgap, vgap));
		for(int i = 0; i < rows * cols; i++) {
			panel.add(jcomp);
		}
		return panel;
	}

}
