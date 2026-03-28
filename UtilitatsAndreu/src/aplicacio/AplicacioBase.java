package aplicacio;

/**
 * Classe base per crear la classe Aplicacio.
 * 
 * @author Andreu
 * @version 1.1
 */

public abstract class AplicacioBase {

	////////////////////////////////////////////////////
	/// ATRIBUTS ABSTRACTES
	////////////////////////////////////////////////////

	protected abstract ControladorBase controlador();
	protected abstract VistaBase vista(ControladorBase controlador);

	////////////////////////////////////////////////////
	/// MÈTODES DE LA CLASSE
	////////////////////////////////////////////////////

	/**
	 * Executa l'aplicació.
	 */
	public void executar() {
		ControladorBase controlador = controlador();
		controlador.inicialitzar();
		vista(controlador).menu();
	}
	
}
