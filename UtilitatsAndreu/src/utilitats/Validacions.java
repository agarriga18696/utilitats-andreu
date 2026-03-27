package utilitats;

import java.util.regex.Pattern;

/**
 * Classe d'utilitat per validar dades.
 * Tots els mètodes retornen un valor booleà.
 * 
 * @author Andreu
 * @version 1.2
 */

public class Validacions {

	////////////////////////////////////////////////////
	/// NÚMEROS
	////////////////////////////////////////////////////

	/**
	 * Comprova si una cadena és un número enter vàlid.
	 */
	public static boolean esEnter(String valor) {
		if(Cadenes.esBuida(valor)) return false;
		try {
			Integer.parseInt(valor.strip());
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}

	/**
	 * Comprova si una cadena és un número decimal vàlid.
	 */
	public static boolean esDecimal(String valor) {
		if(Cadenes.esBuida(valor)) return false;
		try {
			Double.parseDouble(valor.strip().replace(",", "."));
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}

	/**
	 * Comprova si un número enter és positiu major que zero.
	 */
	public static boolean esPositiu(int valor) {
		return valor > 0;
	}

	/**
	 * Comprova si un número decimal és positiu major que zero.
	 */
	public static boolean esPositiu(double valor) {
		return valor > 0;
	}

	/**
	 * Comprova si un número enter es troba dins un rang específic.
	 */
	public static boolean esEnRang(int valor, int min, int max) {
		return valor >= min && valor <= max;
	}

	/**
	 * Comprova si un número decimal es troba dins un rang específic.
	 */
	public static boolean esEnRang(double valor, double min, double max) {
		return valor >= min && valor <= max;
	}

	////////////////////////////////////////////////////
	/// EMAIL
	////////////////////////////////////////////////////

	// Patró per detectar una adreça electrònica vàlida.
	// -> 1r bloc [\\w._%+\\-]: conté lletres, dígits, guions baixos, punts, subratllats, percentatges, més, guions.
	// -> 2n bloc @: conté @
	// -> 3r bloc [\\w.\\-]: conté lletres, números, punts i guions
	// -> 4t bloc .[a-zA-Z]{2,}: conté un punt i 2 o més lletres (.es, .com, .org)
	private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w._%+\\-]+@[\\w.\\-]+\\.[a-zA-Z]{2,}$");

	/**
	 * Comprova si una cadena és un email vàlid.
	 */
	public static boolean esEmail(String valor) {
		if(Cadenes.esBuida(valor)) return false;
		return EMAIL_PATTERN.matcher(valor.strip()).matches();
	}

	////////////////////////////////////////////////////
	/// TELÈFON
	////////////////////////////////////////////////////

	/**
	 * Comprova si una cadena és un telèfon espanyol vàlid de 9 dígits.
	 */
	public static boolean esTelefon(String valor) {
		if(Cadenes.esBuida(valor)) return false;
		// 9 dígits en total:
		// -> Comença per 6,7 o 9.
		// -> Conté 8 números més entre 0 i 9.
		return valor.strip().matches("[679]\\d{8}");
	}

	////////////////////////////////////////////////////
	/// DNI
	////////////////////////////////////////////////////

	private static final String LLETRES_DNI = "TRWAGMYFPDXBNJZSQVHLCKE";

	/**
	 * Comprova si una cadena és un DNI vàlid en format i lletra.
	 */
	public static boolean esDNI(String valor) {
		if(Cadenes.esBuida(valor)) return false;
		String dni = valor.strip().toUpperCase();
		if(!dni.matches("\\d{8}[A-Z]")) return false; // Conté 8 dígits i una lletra.
		int numero = Integer.parseInt(dni.substring(0, 8));
		char lletraCorrecta = LLETRES_DNI.charAt(numero % 23);
		return dni.charAt(8) == lletraCorrecta;
	}

}
