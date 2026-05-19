package aplicaciogui;

import javax.swing.SwingUtilities;

/**
 * Classe base per crear aplicacions d'interfície gràfica.
 * 
 * @author Andreu
 * @version 1.0
 */

public abstract class AplicacioGuiBase {

	/**
	 * Inicialitza i construeix la interfície gràfica.
	 * Aquí s'han de crear els components, configurar-los i afegir-los al contenidor principal.
	 * Aquest mètode ha de ser implementat per les subclasses.
	 */
	protected abstract void inicialitzar();
	
	/**
	 * Executa la inicialització de la interfície gràfica en el fil d'esdeveniments de Swing (Event Dispatch Thread).
	 */
	public final void executar() {
		SwingUtilities.invokeLater(this::inicialitzar);
	}
	
}
