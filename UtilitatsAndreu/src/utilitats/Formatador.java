package utilitats;

/**
 * Classe d'utilitat per formatar i transformar textos i números.
 * 
 * @author Andreu
 * @version 1.1
 */

public class Formatador {

	////////////////////////////////////////////////////
	/// NÚMEROS
	////////////////////////////////////////////////////

	/**
	 * Formata un decimal amb el nombre de decimals indicat.
	 */
	public static String decimal(double valor, int decimals) {
		return String.format("%." + decimals + "f", valor);
	}

	////////////////////////////////////////////////////
	/// CADENES
	////////////////////////////////////////////////////

	/**
	 * Converteix una cadena a majúscules.
	 */
	public static String majuscules(String text) {
		if(text == null) return null;
		return text.toUpperCase();
	}

	/**
	 * Converteix una cadena a minúscules.
	 */
	public static String minuscules(String text) {
		if(text == null) return null;
		return text.toLowerCase();
	}

	/**
	 * Capitalitza una cadena convertint la primera lletra en majúscula i la resta en minúscules.
	 */
	public static String capitalitzar(String text) {
		if(Cadenes.esBuida(text)) return text;
		return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
	}

	/**
	 * Capitalitza cada paraula d'una cadena.
	 */
	public static String capitalitzarParaules(String text) {
		if(Cadenes.esBuida(text)) return text;
		String[] paraules = text.strip().split("\\s+");
		StringBuilder sb = new StringBuilder();
		for(String p : paraules) {
			sb.append(capitalitzar(p)).append(" ");
		}
		return sb.toString().strip();
	}

}
