package utilitats;

/**
 * Classe d'utilitat per crear menús per consola.
 * Permet crear menús principals, submenús i gestionar les opcions.
 * 
 * @author Andreu
 * @version 2.0
 */

public class Menu {

	////////////////////////////////////////////////////
	/// MÈTODES PRINCIPALS
	////////////////////////////////////////////////////

	/**
	 * Mostra un menú complet i retorna l'opció sel·leccionada.
	 * Les opcions estan numerades des del 1. La darrera opció ha de ser per sortir. 
	 * 
	 * @param titol Títol del menú.
	 * @param opcions Opcions del menú.
	 * @return L'opció sel·leccionada.
	 */
	public static int mostrar(String titol, String... opcions) {
		crearMenu(titol, opcions);
		return Escriure.enterRang("Sel·lecciona una opció: ", 1, opcions.length);
	}

	/**
	 * Mostra un menú complet sense validació de rang i retorna l'opció sel·leccionada.
	 * S'utilitza per gestionar manualment si l'opció és vàlida.
	 * 
	 * @param titol Títol del menú.
	 * @param opcions Opcions del menú.
	 * @return L'opció sel·leccionada.
	 */
	public static int mostrarSenseValidacio(String titol, String... opcions) {
		crearMenu(titol, opcions);
		return Escriure.enter("Sel·lecciona una opció: ");
	}

	////////////////////////////////////////////////////
	/// CONSTRUCCIÓ DEL MENÚ
	////////////////////////////////////////////////////

	/**
	 * Construeix i imprimeix el menú per pantalla.
	 * @param titol Títol del menú.
	 * @param opcions Opcions del menú.
	 */
	public static void crearMenu(String titol, String... opcions) {
		Missatges.titol(titol);
		mostrarOpcions(opcions);
		Missatges.saltLinia();
	}

	/**
	 * Mostra les opcions del menú numerades a partir del 1.
	 */
	private static void mostrarOpcions(String... opcions) {
		for(int i = 0; i < opcions.length; i++) {
			System.out.println((i+1) + ". " + opcions[i]);
		}
	}

	////////////////////////////////////////////////////
	/// SUBMENÚS
	////////////////////////////////////////////////////

	/**
	 * Mostra un submenú amb subtítol i retorna l'opció sel·leccionada.
	 * 
	 * @param subtitol Subtítol del submenú.
	 * @param opcions Opcions del submenú.
	 * @return L'opció sel·leccionada.
	 */
	public static int mostrarSubMenu(String subtitol, String... opcions) {
		Missatges.subtitol(subtitol);
		mostrarOpcions(opcions);
		Missatges.saltLinia();
		return Escriure.enterRang("Sel·lecciona una opció: ", 1, opcions.length);
	}

	////////////////////////////////////////////////////
	/// UTILITATS DE MENÚ
	////////////////////////////////////////////////////

	/**
	 * Comprova si una opció sel·leccionada correspon a l'opció de sortir.
	 * L'opció de sortir és la darrera opció del menú.
	 * 
	 * @param opcio Opció sel·leccionada.
	 * @param totalOpcions Nombre total d'opcions del menú.
	 * @return true si l'opció és la de sortir.
	 */
	public static boolean esSortir(int opcio, int totalOpcions) {
		return opcio == totalOpcions;
	}
	
}
