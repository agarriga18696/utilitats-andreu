package utilitats;

import java.text.Normalizer;

/**
 * Classe d'utilitats per a operacions amb cadenes de text.
 * 
 * @author Andreu
 * @version 1.2
 */

public class Cadenes {

	////////////////////////////////////////////////////
	/// TRANSFORMACIONS
	////////////////////////////////////////////////////

	/**
	 * Inverteix una cadena.
	 */
	public static String invertir(String text) {
		if(text == null) return null;
		return new StringBuilder(text).reverse().toString();
	}

	/**
	 * Elimina els accents i diacrítics d'una cadena.
	 */
	public static String eliminarAccents(String text) {
		if(text == null) return null;
		return Normalizer.normalize(text, Normalizer.Form.NFD).replaceAll("\\p{Mn}", "");
	}

	/**
	 * Elimina tots els espais d'una cadena.
	 */
	public static String eliminarEspais(String text) {
		if(text == null) return null;
		return text.replaceAll("\\s+", "");
	}

	////////////////////////////////////////////////////
	/// VALIDACIONS
	////////////////////////////////////////////////////

	/**
	 * Comprova si una cadena és nula ni buida.
	 */
	public static boolean esBuida(String valor) {
		return valor == null || valor.isBlank();
	}

	/**
	 * Comprova si una cadena té una longitud específica.
	 */
	public static boolean teLongitud(String valor, int longitud) {
		return valor != null && valor.length() == longitud;
	}

	/**
	 * Comprova si una cadena té una longitud dins un rang específic.
	 */
	public static boolean teLongitudEntre(String valor, int min, int max) {
		if(valor == null) return false;
		return valor.length() >= min && valor.length() <= max;
	}

	/**
	 * Comprova si una cadena està composta únicament per lletres. 
	 */
	public static boolean esSolamentLletres(String valor) {
		if(esBuida(valor)) return false;
		return valor.matches("[\\p{L}]+"); // Conté qualsevol lletra unicode.
	}

	/**
	 * Comprova si una cadena està composta únicament per lletres i espais. 
	 */
	public static boolean esSolamentLletresIEspais(String valor) {
		if(esBuida(valor)) return false;
		return valor.matches("[\\p{L} ]+"); // Conté qualsevol lletra unicode i un espai.
	}

	/**
	 * Comprova si una cadena està composta únicament per lletres i números (i guions o guions baixos). 
	 * Inclou majúscula i minúscula.
	 */
	public static boolean esAlfanumeric(String valor) {
		if(esBuida(valor)) return false;
		return valor.matches("^(?=.*[\\p{L}])(?=.*[0-9])[\\p{L}0-9_-]+$");
	}

	/**
	 * Comprova si una cadena és un palíndrom.
	 * Ignora majúscules, accents i espais.
	 */
	public static boolean esPalindrom(String text) {
		if(text == null) return false;
		String net = eliminarAccents(eliminarEspais(text)).toLowerCase();
		return net.equals(invertir(net));
	}

	/**
	 * Comprova si una cadena comença per un prefix especificat.
	 */
	public static boolean comencaPer(String text, String prefix) {
		if(text == null || prefix == null) return false;
		return text.toLowerCase().startsWith(prefix.toLowerCase());
	}

	/**
	 * Comprova si una cadena acabar per un sufix especificat.
	 */
	public static boolean acabaPer(String text, String sufix) {
		if(text == null || sufix == null) return false;
		return text.toLowerCase().endsWith(sufix.toLowerCase());
	}

}
