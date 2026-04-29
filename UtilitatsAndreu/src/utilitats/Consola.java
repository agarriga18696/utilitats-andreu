package utilitats;

import java.util.Collection;

/**
 * Classe d'utilitat per mostrar dades estructurades per consola.
 * 
 * @author Andreu
 * @version 2.1
 */

public class Consola {

	////////////////////////////////////////////////////
	/// LLISTES
	////////////////////////////////////////////////////

	/**
	 * Mostra un array d'objectes genèrics en forma de llista amb guions.
	 */
	public static <T> void llista(T[] elements) {
		for(int i = 0; i < elements.length; i++) {
			System.out.println("- " + elements[i]);
		}
	}
	
	/**
	 * Mostra una llista d'objectes genèrics en forma de llista amb guions.
	 */
	public static <T> void llista(Collection<T> elements) {
		for(T element : elements) {
			System.out.println("- " + element);
		}
	}
	
	/**
	 * Mostra un array d'enters en forma de llista amb guions.
	 */
	public static void llista(int[] elements) {
		for(int i = 0; i < elements.length; i++) {
			System.out.println("- " + elements[i]);
		}
	}
	
	/**
	 * Mostra un array de decimals en forma de llista amb guions.
	 */
	public static void llista(double[] elements) {
		for(int i = 0; i < elements.length; i++) {
			System.out.println("- " + elements[i]);
		}
	}
	
	/**
	 * Mostra un array d'objectes genèrics en forma de llista amb un símbol específic.
	 */
	public static <T> void llista(T[] elements, String simbol) {
		for(int i = 0; i < elements.length; i++) {
			System.out.println(simbol + " " + elements[i]);
		}
	}
	
	/**
	 * Mostra una llista d'objectes genèrics en forma de llista amb un símbol específic.
	 */
	public static <T> void llista(Collection<T> elements, String simbol) {
		for(T element : elements) {
			System.out.println(simbol + " " + element);
		}
	}
	
	/**
	 * Mostra un array d'enters en forma de llista amb un símbol específic.
	 */
	public static void llista(int[] elements, String simbol) {
		for(int i = 0; i < elements.length; i++) {
			System.out.println(simbol + " " + elements[i]);
		}
	}
	
	/**
	 * Mostra un array de decimals en forma de llista amb un símbol específic.
	 */
	public static void llista(double[] elements, String simbol) {
		for(int i = 0; i < elements.length; i++) {
			System.out.println(simbol + " " + elements[i]);
		}
	}
	
	/**
	 * Mostra un array d'objectes genèrics en forma de llista numerada a partir del 1.
	 */
	public static <T> void llistaNumerada(T[] elements) {
		for(int i = 0; i < elements.length; i++) {
			System.out.println((i+1) + ". " + elements[i]);
		}
	}
	
	/**
	 * Mostra una llista d'objectes genèrics en forma de llista numerada a partir del 1.
	 */
	public static <T> void llistaNumerada(Collection<T> elements) {
		int i = 1;
	    for(T element : elements) {
	        System.out.println(i++ + ". " + element);
	    }
	}
	
	/**
	 * Mostra un array d'enters en forma de llista numerada a partir del 1.
	 */
	public static void llistaNumerada(int[] elements) {
		for(int i = 0; i < elements.length; i++) {
			System.out.println((i+1) + ". " + elements[i]);
		}
	}
	
	/**
	 * Mostra un array de decimals en forma de llista numerada a partir del 1.
	 */
	public static void llistaNumerada(double[] elements) {
		for(int i = 0; i < elements.length; i++) {
			System.out.println((i+1) + ". " + elements[i]);
		}
	}
	
	////////////////////////////////////////////////////
	/// UTILITATS
	////////////////////////////////////////////////////

	/**
	 * Pausa l'execució fins que l'usuari prem Enter.
	 */
	public static void esperarTeclaEnter() {
		System.out.println("\nPrem Enter per continuar...");
		Escriure.cadenaOpcional("");
	}

}
