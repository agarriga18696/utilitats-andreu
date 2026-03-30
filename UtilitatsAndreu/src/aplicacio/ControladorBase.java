package aplicacio;

import utilitats.Fitxers;
import utilitats.Missatges;

/**
 * Classe base per crear la classe Controlador.
 * 
 * @author Andreu
 * @version 2.0
 */

public abstract class ControladorBase {

	////////////////////////////////////////////////////
	/// ATRIBUTS ABSTRACTES
	////////////////////////////////////////////////////

	protected abstract String directori();
	protected abstract void carregar();
	protected abstract void guardar();

	////////////////////////////////////////////////////
	/// MÈTODES DE LA CLASSE
	////////////////////////////////////////////////////

	/**
	 * Inicialitza el directori i carrega les dades des del fitxer.
	 */
	public void inicialitzar() {
		Fitxers.crearDirectoriSiNoExisteix(directori());
		carregar();
	}

	/**
	 * Finalitza l'aplicació.
	 */
	public void finalitzar() {
		guardar();
		Missatges.fiPrograma();
		System.exit(0);
	}

}
