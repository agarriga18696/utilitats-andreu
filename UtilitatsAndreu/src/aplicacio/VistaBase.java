package aplicacio;

import utilitats.Menu;

/**
 * Classe base per crear la classe Vista.
 * 
 * @author Andreu
 * @version 1.1
 */

public abstract class VistaBase {

	////////////////////////////////////////////////////
	/// ATRIBUTS ABSTRACTES
	////////////////////////////////////////////////////

	protected abstract String titol();
	protected abstract String[] opcions();
	protected abstract void gestionar(int opcio);

	////////////////////////////////////////////////////
	/// MÈTODES DE LA CLASSE
	////////////////////////////////////////////////////

	/**
	 * Mostra el menú principal de l'aplicació amb el títol i les opcions.
	 * <p>
	 * Funcions:
	 * <ul>
	 * <li>Demana per consola una opció {@code int} fins que introdueixi l'opció per sortir</li>
	 * <li>Gestiona l'opció introduïda per l'usuari</li>
	 * </ul>
	 * <p>
	 * <i><b>Nota:</b> l'opció per sortir sempre és la darrera de l'array d'opcions.</i>
	 */
	public void menu() {
		String[] opcions = opcions();
		int opcio;
		do {
			opcio = Menu.mostrar(titol(), opcions);
			gestionar(opcio);
		} while(opcio != opcions.length);
	}

}
