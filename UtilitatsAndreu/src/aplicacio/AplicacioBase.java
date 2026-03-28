package aplicacio;

/**
 * Classe base per crear la classe Aplicacio.
 * 
 * @author Andreu
 * @version 1.0
 */

public abstract class AplicacioBase {

	////////////////////////////////////////////////////
	/// ATRIBUTS ABSTRACTES
	////////////////////////////////////////////////////

	protected abstract Controlador controlador();
	protected abstract VistaBase vista();

	////////////////////////////////////////////////////
	/// MÈTODES DE LA CLASSE
	////////////////////////////////////////////////////

	/**
	 * Executa l'aplicació.
	 */
	public void executar() {
		controlador().inicialitzar();
		vista().menu();
	}
	
}
