package utilitats;

/**
 * Classe d'utilitat per a conversions de tipus.
 * 
 * @author Andreu
 * @version 1.1
 */

public class Conversor {

	/**
	 * Converteix una cadena a enter.
	 * Retorna el valor per defecte si la conversió falla.
	 */
	public static Integer aEnter(String valor) {
		try {
			return Integer.parseInt(valor.strip());
		} catch(Exception e) {
			return null;
		}
	}
	
	/**
	 * Converteix una cadena a enter.
	 * Retorna el valor per defecte si la conversió falla.
	 */
	public static Double aDecimal(String valor) {
		try {
			return Double.parseDouble(valor.strip().replace(",", "."));
		} catch(Exception e) {
			return null;
		}
	}
	
	/**
	 * Converteix un valor genèric a cadena.
	 * Retorna el valor per defecte si la conversió falla.
	 */
	public static <T> String aCadena(T valor) {
		if(valor == null) return null;
		return valor.toString();
	}
	
	/**
	 * Converteix un booleà a "Sí" o "No".
	 */
	public static String aSiNo(boolean valor) {
		return valor ? "Sí" : "No";
	}
	
}
