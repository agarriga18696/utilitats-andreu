package utilitats;

import java.util.Random;

/**
 * Classe d'utilitat per generar valors aleatoris.
 * 
 * @author Andreu
 * @version 1.0
 */

public class Aleatori {

	private static final Random random = new Random();

	/**
	 * Retorna un número enter aleatori entre {@code min} i {@code max} inclosos.
	 */
	public static int enter(int min, int max) {
		return random.nextInt(max - min + 1) + min;
	}

	/**
	 * Retorna un número decimal aleatori entre {@code min} i {@code max} inclosos.
	 */
	public static double decimal(double min, double max) {
		return min + (max - min) * random.nextDouble();
	}

	/**
	 * Retorna un booleà aleatori.
	 */
	public static boolean boolea() {
		return random.nextBoolean();
	}

}
