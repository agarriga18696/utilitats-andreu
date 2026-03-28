package aplicacio;

import utilitats.Fitxers;
import utilitats.Missatges;

/**
 * Classe base per crear la classe Controlador.
 * 
 * @author Andreu
 * @version 1.2
 */

public abstract class ControladorBase implements Inicialitzable, Finalitzable {

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
	@Override
	public void inicialitzar() {
		Fitxers.crearDirectoriSiNoExisteix(directori());
		carregar();
	}

	/**
	 * Finalitza l'aplicació.
	 */
	@Override
	public void finalitzar() {
		Missatges.subtitol("Sortir de l'aplicació");
		guardar();
		Missatges.fiPrograma();
		System.exit(0);
	}

}
