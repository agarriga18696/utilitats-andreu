package utilitats;

import java.util.Scanner;

/**
 * Classe d'utilitat per llegir dades d'entrada de l'usuari amb validació.
 * 
 * @author Andreu
 * @version 1.1
 */

public class Escriure {

	private static Scanner sc = new Scanner(System.in);

	////////////////////////////////////////////////////
	/// ENTERS
	////////////////////////////////////////////////////

	/**
	 * Llegeix un número enter.
	 */
	public static int enter(String missatge) {
		while(true) {
			mostrarMissatge(missatge);
			String linia = sc.nextLine().strip();
			try {
				return Integer.parseInt(linia);
			} catch(NumberFormatException e) {
				Missatges.error("Has d'introduir un número enter vàlid.");
			}
		}
	}

	/**
	 * Llegeix un número enter dins d'un rang.
	 */
	public static int enterRang(String missatge, int min, int max) {
		while(true) {
			int valor = enter(missatge);
			if(valor >= min && valor <= max) return valor;
			Missatges.error("El valor ha d'estar entre " + min + " i " + max + ".");
		}
	}

	/**
	 * Llegeix un número enter positiu.
	 */
	public static int enterPositiu(String missatge) {
		while(true) {
			int valor = enter(missatge);
			if(valor > 0) return valor;
			Missatges.error("El valor ha de ser positiu major que zero.");
		}
	}

	////////////////////////////////////////////////////
	/// DECIMALS
	////////////////////////////////////////////////////

	/**
	 * Llegeix un número decimal.
	 */
	public static double decimal(String missatge) {
		while(true) {
			mostrarMissatge(missatge);
			String linia = sc.nextLine().strip().replace(",", ".");
			try {
				return Double.parseDouble(linia);
			} catch(NumberFormatException e) {
				Missatges.error("Has d'introduir un número decimal vàlid.");
			}
		}
	}

	/**
	 * Llegeix un número decimal dins d'un rang.
	 */
	public static double decimalRang(String missatge, double min, double max) {
		while(true) {
			double valor = decimal(missatge);
			if(valor >= min && valor <= max) return valor;
			Missatges.error("El valor ha d'estar entre " + min + " i " + max + ".");
		}
	}

	/**
	 * Llegeix un número decimal positiu.
	 */
	public static double decimalPositiu(String missatge) {
		while(true) {
			double valor = decimal(missatge);
			if(valor > 0) return valor;
			Missatges.error("El valor ha de ser positiu major que zero.");
		}
	}

	////////////////////////////////////////////////////
	/// CADENES
	////////////////////////////////////////////////////

	/**
	 * Llegeix una cadena no buida.
	 */
	public static String cadena(String missatge) {
		while(true) {
			mostrarMissatge(missatge);
			String linia = sc.nextLine().strip();
			if(!linia.isBlank()) return linia;
			Missatges.error("L'entrada no pot estar buida");
		}
	}

	/**
	 * Llegeix una cadena que pot ser buida.
	 */
	public static String cadenaOpcional(String missatge) {
		mostrarMissatge(missatge);
		return sc.nextLine().strip();
	}

	/**
	 * Llegeix una cadena que compleixi una longitud mínima.
	 */
	public static String cadenaMinima(String missatge, int longMin) {
		while(true) {
			String valor = cadena(missatge);
			if(valor.length() >= longMin) return valor.strip();
			Missatges.error("La cadena ha de tenir almenys " + longMin + " caràcters.");
		}
	}

	////////////////////////////////////////////////////
	/// BOOLEANS I CONFIRMACIÓ
	////////////////////////////////////////////////////

	/**
	 * Llegeix una confirmació de l'usuari.
	 */
	public static boolean confirmar(String missatge) {
		while(true) {
			mostrarMissatge(missatge + " (s/n): ");
			String resposta = sc.nextLine().strip().toLowerCase();
			if(resposta.equals("s")) return true;
			if(resposta.equals("n")) return false;
			Missatges.error("Introdueix 's' per sí o 'n' per no.");
		}
	}

	////////////////////////////////////////////////////
	/// CARÀCTERS
	////////////////////////////////////////////////////

	/**
	 * Llegeix un caràcter.
	 */
	public static char caracter(String missatge) {
		while(true) {
			mostrarMissatge(missatge);
			String linia = sc.nextLine().strip();
			if(linia.length() == 1) return linia.charAt(0);
			Missatges.error("Has d'introduir exactament un caràcter.");
		}
	}
	
	////////////////////////////////////////////////////
	/// UTILITAT
	////////////////////////////////////////////////////

	/**
	 * Tanca el Scanner. S'ha de cridar al final del programa.
	 */
	public static void tancarEntrada() {
		sc.close();
	}
	
	// Imprimeix el missatge a mostrar a l'usuari en cada entrada. 
	private static void mostrarMissatge(String missatge) {
		if(missatge != null && !missatge.isBlank()) {
			System.out.print(missatge);
		}
	}

}
