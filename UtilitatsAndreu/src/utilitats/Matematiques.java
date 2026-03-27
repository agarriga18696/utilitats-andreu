package utilitats;

/**
 * Classe d'utilitat per a operacions matemàtiques.
 * 
 * @author Andreu
 * @version 1.1
 */

public class Matematiques {

	////////////////////////////////////////////////////
	/// OPERACIONS BÀSIQUES
	////////////////////////////////////////////////////

	/**
	 * Comprova si un número és parell.
	 */
	public static boolean esParell(int n) {
		return n % 2 == 0;
	}
	
	/**
	 * Comprova si un número és múltiple d'un altre.
	 */
	public static boolean esMultiple(int numero, int divisor) {
		return numero % divisor == 0;
	}

	/**
	 * Comprova si un número és primer.
	 */
	public static boolean esPrimer(int n) {
		if(n < 2) return false;
		if(n == 2) return true;
		if(n % 2 == 0) return false;
		for(int i = 3; i <= Math.sqrt(n); i += 2) {
			if(n % i == 0) return false;
		}
		return true;
	}

	////////////////////////////////////////////////////
	/// CANVIS DE BASE
	////////////////////////////////////////////////////

	/**
	 * Converteix un número decimal a binari i el retorna a String.
	 */
	public static String aBinari(int n) {
		return Integer.toBinaryString(n);
	}
	
	/**
	 * Converteix un número decimal a hexadecimal i el retorna a String.
	 */
	public static String aHexadecimal(int n) {
		return Integer.toHexString(n);
	}
	
	/**
	 * Converteix un número binari com String a decimal.
	 */
	public static int binariADecimal(String binari) {
		return Integer.parseInt(binari, 2);
	}
	
}
