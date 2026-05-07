package utilitats;

import javax.swing.Icon;
import javax.swing.JButton;

/**
 * Classe d'utilitats per a components d'interfície gràfica.
 * 
 * @author Andreu
 * @version 1.0
 */

public final class GUI {
	
	private GUI() {}

	////////////////////////////////////////////////////
	/// CONTENIDORS
	////////////////////////////////////////////////////

	////////////////////////////////////////////////////
	/// ELEMENTS INTERACTIUS
	////////////////////////////////////////////////////

	/**
	 * Retorna un JButton amb un títol i icona.
	 */
	public static final JButton boto(String titol, Icon icona) {
		return icona != null ? new JButton(titol, icona) : new JButton(titol);
	}
	
}
