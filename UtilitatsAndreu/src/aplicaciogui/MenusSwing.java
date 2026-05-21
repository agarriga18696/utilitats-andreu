package aplicaciogui;

import java.util.Objects;

import javax.swing.Icon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

/**
 * Classe d'utilitat per crear i configurar menús Swing.
 * 
 * @author Andreu
 * @version 1.2
 */
public final class MenusSwing {

	private MenusSwing() {
		/*
		 * Classe d'utilitat no instanciable.
		 */
	}

	//-------------------------------
	// BARRES I MENÚS
	//-------------------------------

	/**
	 * Retorna una nova {@code JMenuBar}.
	 * 
	 * @return Barra de menú creada.
	 */
	public static JMenuBar barraMenu() {
		return new JMenuBar();
	}

	/**
	 * Retorna un {@code JMenu} amb el text indicat.
	 * 
	 * @param text Text del menú.
	 * @return Menú creat.
	 */
	public static JMenu menu(String text) {
		return new JMenu(text);
	}
	
	/**
	 * Retorna un {@code JMenu} amb el text indicat i un mnemònic.
	 * 
	 * @param text Text del menú.
	 * @param mnemonic Mnemònic de teclat del menú.
	 * @return Menú creat amb icona.
	 */
	public static JMenu menu(String text, int mnemonic) {
		JMenu menu = new JMenu(text);
		menu.setMnemonic(mnemonic);
		return menu;
	}
	
	/**
	 * Retorna un {@code JMenu} amb el text indicat i un mnemònic.
	 * 
	 * @param text Text del menú.
	 * @param mnemonic Mnemònic de teclat del menú.
	 * @return Menú creat amb icona.
	 */
	public static JMenu menu(String text, char mnemonic) {
		JMenu menu = new JMenu(text);
		menu.setMnemonic(mnemonic);
		return menu;
	}

	/**
	 * Retorna un {@code JMenu} amb el text indicat i una icona.
	 * 
	 * @param text Text del menú.
	 * @param icona Icona del menú.
	 * @return Menú creat amb icona.
	 */
	public static JMenu menu(String text, Icon icona) {
		JMenu menu = new JMenu(text);
		menu.setIcon(icona);
		return menu;
	}
	
	/**
	 * Retorna un {@code JMenu} amb el text indicat, una icona i un mnemònic.
	 * 
	 * @param text Text del menú.
	 * @param icona Icona del menú.
	 * @param mnemonic Mnemònic de teclat del menú.
	 * @return Menú creat amb icona.
	 */
	public static JMenu menu(String text, Icon icona, int mnemonic) {
		JMenu menu = new JMenu(text);
		menu.setIcon(icona);
		menu.setMnemonic(mnemonic);
		return menu;
	}
	
	/**
	 * Retorna un {@code JMenu} amb el text indicat, una icona i un mnemònic.
	 * 
	 * @param text Text del menú.
	 * @param icona Icona del menú.
	 * @param mnemonic Mnemònic de teclat del menú.
	 * @return Menú creat amb icona.
	 */
	public static JMenu menu(String text, Icon icona, char mnemonic) {
		JMenu menu = new JMenu(text);
		menu.setIcon(icona);
		menu.setMnemonic(mnemonic);
		return menu;
	}

	//-------------------------------
	// ITEMS DE MENÚ
	//-------------------------------

	/**
	 * Retorna un {@code JMenuItem} amb el text indicat.
	 * 
	 * @param text Text de l'item de menú.
	 * @return Item de menú creat.
	 */
	public static JMenuItem item(String text) {
		return new JMenuItem(text);
	}

	/**
	 * Retorna un {@code JMenuItem} amb el text indicat i una acció associada a l'esdeveniment de clic.
	 * 
	 * @param text Text de l'item de menú.
	 * @param accio Acció a executar quan es faci clic a l'item de menú.
	 * @return Item de menú creat amb acció associada.
	 */
	public static JMenuItem item(String text, Runnable accio) {
		Objects.requireNonNull(accio, "L'acció no pot ser null.");

		JMenuItem item = new JMenuItem(text);
		item.addActionListener(_ -> accio.run());

		return item;
	}

	/**
	 * Retorna un {@code JMenuItem} amb el text indicat, una icona i una acció associada a l'esdeveniment de clic.
	 * 
	 * @param text Text de l'item de menú.
	 * @param icona Icona de l'item de menú.
	 * @param accio Acció a executar quan es faci clic a l'item de menú.
	 * @return Item de menú creat amb icona i acció associada.
	 */
	public static JMenuItem item(String text, Icon icona, Runnable accio) {
		Objects.requireNonNull(accio, "L'acció no pot ser null.");

		JMenuItem item = new JMenuItem(text, icona);
		item.addActionListener(_ -> accio.run());

		return item;
	}

	/**
	 * Retorna un {@code JMenuItem} amb text, icona, drecera i acció.
	 * 
	 * @param text Text de l'opció.
	 * @param icona Icona de l'opció.
	 * @param drecera Drecera de teclat.
	 * @param accio Acció que s'executarà en seleccionar l'opció.
	 * @return Opció de menú creada.
	 */
	public static JMenuItem item(String text, Icon icona, KeyStroke drecera, Runnable accio) {
		JMenuItem item = item(text, icona, accio);
		item.setAccelerator(drecera);
		return item;
	}

	//-------------------------------
	// RADIO ITEMS
	//-------------------------------

	/**
	 * Retorna un {@code JRadioButtonMenuItem}.
	 * 
	 * @param text Text de l'opció.
	 * @param seleccionat Indica si l'opció està seleccionada inicialment.
	 * @param accio Acció que s'executarà en seleccionar l'opció.
	 * @return Opció de menú de tipus radio creada.
	 */
	public static JRadioButtonMenuItem radioItem(String text, boolean seleccionat, Runnable accio) {
		Objects.requireNonNull(accio, "L'acció no pot ser null.");

		JRadioButtonMenuItem item = new JRadioButtonMenuItem(text, seleccionat);
		item.addActionListener(_ -> accio.run());

		return item;
	}

}
