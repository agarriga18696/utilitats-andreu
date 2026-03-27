package utilitats;

/**
 * Classe d'utilitat per mostrar misssatges per consola.
 * 
 * @author Andreu
 * @version 1.1
 */

public class Missatges {

	private static final String SALT = "\n";
	private static final int REPETICIONS = 50;

	////////////////////////////////////////////////////
	/// MISSATGES ESTÀNDARD
	////////////////////////////////////////////////////

	/**
	 * Mostra un missatge simple per consola.
	 */
	public static void mostrar(String missatge) {
		System.out.println(missatge);
	}

	/**
	 * Mostra un missatge simple en línia per consola.
	 */
	public static void mostrarEnLinia(String missatge) {
		System.out.print(missatge);
	}

	/**
	 * Mostra un missatge d'error per consola.
	 */
	public static void error(String missatge) {
		System.out.println(SALT + "ERROR: " + missatge + SALT);
	}

	/**
	 * Mostra un missatge d'èxit per consola.
	 */
	public static void exit(String missatge) {
		System.out.println(SALT + "OK: " + missatge + SALT);
	}

	/**
	 * Mostra un missatge d'avís per consola.
	 */
	public static void avis(String missatge) {
		System.out.println(SALT + "AVÍS: " + missatge + SALT);
	}

	/**
	 * Mostra un missatge d'informació per consola.
	 */
	public static void info(String missatge) {
		System.out.println(SALT + "INFO: " + missatge + SALT);
	}

	////////////////////////////////////////////////////
	/// TÍTOLS I SEPARADORS
	////////////////////////////////////////////////////

	/**
	 * Mostra un títol de secció amb separadors.
	 * Format:
	 * ==============================
	 *  TITOL
	 * ==============================
	 */
	public static void titol(String titol) {
		saltLinia();
		separadorGran();
		System.out.println(" " + titol.toUpperCase().strip());
		separadorGran();
	}

	/**
	 * Mostra un subtítol de secció amb un petit separador.
	 * Format:
	 * --- Subtítol ---
	 */
	public static void subtitol(String subtitol) {
		System.out.println(SALT + "--- " + subtitol + " ---");
	}

	/**
	 * Mostrar una línia separadora.
	 */
	public static void separador() {
		System.out.println("-".repeat(REPETICIONS));
	}

	/**
	 * Mostrar una línia separadora amb un caracter especificat.
	 */
	public static void separador(String caracter) {
		System.out.println(caracter.repeat(REPETICIONS));
	}
	
	/**
	 * Mostrar una línia separadora repetint un caracter especificat n vegades.
	 */
	public static void separador(String caracter, int repeticions) {
		System.out.println(caracter.repeat(repeticions));
	}
	
	/**
	 * Mostrar una línia separadora gran.
	 */
	public static void separadorGran() {
		System.out.println("=".repeat(REPETICIONS));
	}

	/**
	 * Mostra un salt de línia.
	 */
	public static void saltLinia() {
		System.out.println();
	}

	////////////////////////////////////////////////////
	/// MISSATGES DE PROGRAMA COMUNS
	////////////////////////////////////////////////////

	/**
	 * Mostra el missatge de fi de programa.
	 */
	public static void fiPrograma() {
		System.out.println(SALT + "Fi del programa. Fins la pròxima!");
	}
	
	/**
	 * Mostra el missatge d'opció no vàlida.
	 */
	public static void opcioNoValida() {
		error("Opció no vàlida. Selecciona una opció vàlida.");
	}
	
	/**
	 * Mostra el missatge en cas que no hi hagi elements.
	 */
	public static void llistaBuida() {
		avis("No hi ha elements per mostrar.");
	}
	
	/**
	 * Mostra el missatge en cas de no trobar un element.
	 */
	public static void elementNoTrobat() {
		avis("No s'ha trobat l'element.");
	}
	
	/**
	 * Mostra el missatge en cas que un element s'ha afegit correctament.
	 */
	public static void elementAfegit() {
		exit("Element afegit correctament.");
	}
	
	/**
	 * Mostra el missatge en cas que un element s'ha eliminat correctament.
	 */
	public static void elementEliminat() {
		exit("Element eliminat correctament.");
	}
	
	/**
	 * Mostra el missatge en cas que un element s'ha modificat correctament.
	 */
	public static void elementModificat() {
		exit("Element modificat correctament.");
	}
	
	/**
	 * Mostra el missatge en cas que s'hagin guardat dades correctament.
	 */
	public static void dadesGuardades() {
		exit("Dades guardades correctament.");
	}
	
	/**
	 * Mostra el missatge en cas que s'hagin carregat dades correctament.
	 */
	public static void dadesCarregades() {
		exit("Dades carregades correctament.");
	}
	
}
