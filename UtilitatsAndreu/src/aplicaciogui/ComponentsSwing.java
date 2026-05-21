package aplicaciogui;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Classe d'utilitat per crear components Swing comuns.
 * 
 * @author Andreu
 * @version 1.0
 */
public final class ComponentsSwing {

	private ComponentsSwing() {
		/*
		 * Classe d'utilitat no instanciable.
		 */
	}

	//-------------------------------
	// BOTONS
	//-------------------------------

	/**
	 * Retorna un {@code JButton} amb el text indicat.
	 * 
	 * @param text Text del botó.
	 * @return Botó creat.
	 */
	public static JButton boto(String text) {
		return new JButton(text);
	}

	/**
	 * Retorna un {@code JButton} amb el text indicat i una acció associada a l'esdeveniment de clic.
	 *  
	 * @param text Text del botó.
	 * @param accio Acció a executar quan es faci clic al botó.
	 * @return Botó creat amb acció associada.
	 */
	public static JButton boto(String text, Runnable accio) {
		Objects.requireNonNull(accio, "L'acció no pot ser null.");

		JButton boto = new JButton(text);
		boto.addActionListener(_ -> accio.run());

		return boto;
	}
	
	/**
	 * Retorna un {@code JButton} amb text, icona i acció.
	 * 
	 * @param text Text del botó.
	 * @param icona Icona del botó.
	 * @param accio Acció a executar quan es faci clic al botó.
	 * @return Botó creat amb icona i acció associada.
	 */
	public static JButton boto(String text, Icon icona, Runnable accio) {
		Objects.requireNonNull(accio, "L'acció no pot ser null.");

		JButton boto = new JButton(text, icona);
		boto.addActionListener(_ -> accio.run());

		return boto;
	}

	/**
	 * Retorna un {@code JButton} amb el text indicat i una acció associada a l'esdeveniment de clic.
	 * 
	 * @param text Text del botó.
	 * @param listener ActionListener a executar quan es faci clic al botó.
	 * @return Botó creat amb ActionListener associat.
	 */
	public static JButton boto(String text, ActionListener listener) {
		Objects.requireNonNull(listener, "L'ActionListener no pot ser null.");

		JButton boto = new JButton(text);
		boto.addActionListener(listener);

		return boto;
	}

	//-------------------------------
	// ETIQUETES
	//-------------------------------

	/**
	 * Retorna un {@code JLabel} amb el text indicat.
	 * 
	 * @param text Text de l'etiqueta.
	 * @return Etiqueta creada.
	 */
	public static JLabel etiqueta(String text) {
		return new JLabel(text);
	}

	/**
	 * Retorna un {@code JLabel} amb el text indicat i alineat al centre.
	 * 
	 * @param text Text de l'etiqueta.
	 * @return Etiqueta creada amb alineació centrada.
	 */
	public static JLabel etiquetaCentrada(String text) {
		return new JLabel(text, SwingConstants.CENTER);
	}

	//-------------------------------
	// CAMPS DE TEXT
	//-------------------------------

	/**
	 * Retorna un {@code JTextField} amb el nombre de columnes indicat.
	 * 
	 * @param columnes Nombre de columnes del camp de text.
	 * @return Camp de text creat.
	 */
	public static JTextField campText(int columnes) {
		return new JTextField(columnes);
	}

	//-------------------------------
	// CHECKBOX
	//-------------------------------

	/**
	 * Retorna un {@code JCheckBox} amb el text indicat.
	 * 
	 * @param text Text del checkbox.
	 * @return Checkbox creat.
	 */
	public static JCheckBox checkbox(String text) {
		return new JCheckBox(text);
	}

	//-------------------------------
	// COMBOBOX
	//-------------------------------

	/**
	 * Retorna un {@code JComboBox} amb els items indicats.
	 * 
	 * @param <T> Tipus dels items del combo box.
	 * @param items Array d'items que es mostraran al combo box.
	 * @return Combo box creat amb els items indicats.
	 */
	public static <T> JComboBox<T> comboBox(T[] items) {
		return new JComboBox<>(items);
	}

	/**
	 * Retorna un {@code JComboBox} amb els items indicats.
	 * 
	 * @param items Array de strings que es mostraran al combo box.
	 * @return Combo box creat amb els items indicats.
	 */
	public static JComboBox<String> comboBox(String... items) {
		return new JComboBox<>(items);
	}

	//-------------------------------
	// JLIST
	//-------------------------------
	
	/**
	 * Retorna una {@code JList} genèrica buida.
	 * 
	 * @param <T> Tipus d'element de la llista.
	 * @return Llista creada.
	 */
	public static <T> JList<T> llista() {
		return new JList<>();
	}
	
	/**
	 * Retorna una {@code JList} genèrica amb els elements indicats.
	 * 
	 * @param <T> Tipus d'element de la llista.
	 * @param elements Array d'elements que es mostraran a la llista.
	 * @return Llista creada amb els elements indicats.
	 */
	public static <T> JList<T> llista(T[] elements) {
		return new JList<>(elements);
	}
	
	//-------------------------------
	// SCROLL
	//-------------------------------

	/**
	 * Retorna un {@code JScrollPane} que conté el component indicat.
	 * 
	 * @param component Component que es mostrarà dins el scroll.
	 * @return ScrollPane creat.
	 */
	public static JScrollPane scroll(Component component) {
		return new JScrollPane(component);
	}

}
