package utilitats;

import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Classe d'utilitat per treballar amb arrays.
 * 
 * @author Andreu
 * @version 2.0
 */

public class Arrays {

	////////////////////////////////////////////////////
	/// OPERACIONS
	////////////////////////////////////////////////////

	/**
	 * Genera un array de números enters aleatoris entre {@code min} i {@code max} inclosos.
	 * 
	 * @param quantitat Nombre d'elements a generar.
	 * @param min Valor mínim inclòs.
	 * @param max Valor màxim inclòs.
	 * @return Array de números enters aleatoris.
	 */
	public static int[] generarAleatoris(int quantitat, int min, int max) {
		int[] array = new int[quantitat];
		for(int i = 0; i < quantitat; i++) {
			array[i] = (Aleatori.enter(min, max));
		}
		return array;
	}

	/**
	 * Genera un array de números decimals aleatoris entre {@code min} i {@code max} inclosos.
	 * 
	 * @param quantitat Nombre d'elements a generar.
	 * @param min Valor mínim inclòs.
	 * @param max Valor màxim inclòs.
	 * @return Array de números decimals aleatoris.
	 */
	public static double[] generarAleatoris(int quantitat, double min, double max) {
		double[] array = new double[quantitat];
		for(int i = 0; i < quantitat; i++) {
			array[i] = (Aleatori.decimal(min, max));
		}
		return array;
	}

	////////////////////////////////////////////////////
	/// BUSCAR
	////////////////////////////////////////////////////

	/**
	 * Cerca un número enter en un array de manera lineal.
	 * Retorna l'índex on es troba, o -1 si no existeix.
	 */
	public static int cercar(int[] array, int valor) {
		for(int i = 0; i < array.length; i++) {
			if(array[i] == valor) return i;
		}
		return -1;
	}

	/**
	 * Cerca un número decimal en un array de manera lineal.
	 * Retorna l'índex on es troba, o -1 si no existeix.
	 */
	public static int cercar(double[] array, double valor) {
		for(int i = 0; i < array.length; i++) {
			if(array[i] == valor) return i;
		}
		return -1;
	}

	/**
	 * Cerca una cadena en un array de manera lineal sense distingir majúscules i minúscules.
	 * Retorna l'índex on es troba, o -1 si no existeix.
	 */
	public static int cercar(String[] array, String valor) {
		for(int i = 0; i < array.length; i++) {
			if(array[i] != null && array[i].equalsIgnoreCase(valor)) return i;
		}
		return -1;
	}

	/**
	 * Cerca un objecte en un array de manera lineal.
	 * Retorna l'índex on es troba, o -1 si no existeix.
	 */
	public static <T> int cercar(T[] array, T valor) {
		for(int i = 0; i < array.length; i++) {
			if(array[i] != null && array[i].equals(valor)) return i;
		}
		return -1;
	}

	/**
	 * Comprova si un número enter existeix en un array.
	 */
	public static boolean conte(int[] array, int valor) {
		return cercar(array, valor) != -1;
	}

	/**
	 * Comprova si un número decimal existeix en un array.
	 */
	public static boolean conte(double[] array, double valor) {
		return cercar(array, valor) != -1;
	}

	/**
	 * Comprova si una cadena existeix en un array.
	 */
	public static boolean conte(String[] array, String valor) {
		return cercar(array, valor) != -1;
	}

	/**
	 * Comprova si un objecte existeix en un array.
	 */
	public static <T> boolean conte(T[] array, T valor) {
		return cercar(array, valor) != -1;
	}

	////////////////////////////////////////////////////
	/// MANIPULACIÓ
	////////////////////////////////////////////////////

	/**
	 * Afegeix un element al final d'un array i retorna el nou array.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] afegir(T[] array, T element, Class<T> classe) {
		T[] nouArray = (T[]) Array.newInstance(classe, array.length + 1);
		for(int i = 0; i < array.length; i++) {
			nouArray[i] = array[i];
		}
		nouArray[array.length] = element;
		return nouArray;
	}

	/**
	 * Afegeix un element al final d'un array d'enters i retorna el nou array.
	 */
	public static int[] afegir(int[] array, int element) {
		int[] nouArray = new int[array.length + 1];
		for(int i = 0; i < array.length; i++) {
			nouArray[i] = array[i];
		}
		nouArray[array.length] = element;
		return nouArray;
	}

	/**
	 * Afegeix un element al final d'un array de decimals i retorna el nou array.
	 */
	public static double[] afegir(double[] array, double element) {
		double[] nouArray = new double[array.length + 1];
		for(int i = 0; i < array.length; i++) {
			nouArray[i] = array[i];
		}
		nouArray[array.length] = element;
		return nouArray;
	}

	/**
	 * Elimina l'element de l'índex indicat i retorna el nou array.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] eliminar(T[] array, int index, Class<T> classe) {
		T[] nouArray = (T[]) Array.newInstance(classe, array.length - 1);
		int j = 0;
		for(int i = 0; i < array.length; i++) {
			if(i != index) nouArray[j++] = array[i];
		}
		return nouArray;
	}

	/**
	 * Elimina l'element enter de l'índex indicat i retorna el nou array.
	 */
	public static int[] eliminar(int[] array, int index) {
		int[] nouArray = new int[array.length - 1];
		int j = 0;
		for(int i = 0; i < array.length; i++) {
			if(i != index) nouArray[j++] = array[i];
		}
		return nouArray;
	}

	/**
	 * Elimina l'element decimal de l'índex indicat i retorna el nou array.
	 */
	public static double[] eliminar(double[] array, int index) {
		double[] nouArray = new double[array.length - 1];
		int j = 0;
		for(int i = 0; i < array.length; i++) {
			if(i != index) nouArray[j++] = array[i];
		}
		return nouArray;
	}

	////////////////////////////////////////////////////
	/// ESTADÍSTICA
	////////////////////////////////////////////////////

	/**
	 * Retorna el valor màxim d'un array d'enters.
	 */
	public static int maxim(int[] array) {
		int max = array[0];
		for(int v : array) {
			if(v > max) max = v;
		}
		return max;
	}

	/**
	 * Retorna el valor màxim d'un array de decimals.
	 */
	public static double maxim(double[] array) {
		double max = array[0];
		for(double v : array) {
			if(v > max) max = v;
		}
		return max;
	}

	/**
	 * Retorna el valor mínim d'un array d'enters.
	 */
	public static int minim(int[] array) {
		int min = array[0];
		for(int v : array) {
			if(v < min) min = v;
		}
		return min;
	}

	/**
	 * Retorna el valor mínim d'un array de decimals.
	 */
	public static double minim(double[] array) {
		double min = array[0];
		for(double v : array) {
			if(v < min) min = v;
		}
		return min;
	}

	/**
	 * Retorna la suma de tots els elements d'un array d'enters.
	 */
	public static int suma(int[] array) {
		int total = 0;
		for(int v : array) {
			total += v;
		}
		return total;
	}

	/**
	 * Retorna la suma de tots els elements d'un array de decimals.
	 */
	public static double suma(double[] array) {
		double total = 0;
		for(double v : array) {
			total += v;
		}
		return total;
	}

	/**
	 * Retorna la mitjana dels elements d'un array d'enters.
	 */
	public static double mitjana(int[] array) {
		if(array.length == 0) return 0;
		return (double) suma(array) / array.length;
	}

	/**
	 * Retorna la mitjana dels elements d'un array de decimals.
	 */
	public static double mitjana(double[] array) {
		if(array.length == 0) return 0;
		return (double) suma(array) / array.length;
	}

	////////////////////////////////////////////////////
	/// VALIDACIONS
	////////////////////////////////////////////////////

	/**
	 * Comprova si un array d'objectes genèrics està buit.
	 */
	public static <T> boolean esBuit(T[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * Comprova si un array d'enters està buit.
	 */
	public static boolean esBuit(int[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * Comprova si un array de decimals està buit.
	 */
	public static boolean esBuit(double[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * Comprova si un índex és vàlid per a un array de longitud específica.
	 */
	public static boolean esIndexValid(int index, int longitud) {
		return index >= 0 && index < longitud;
	}

	////////////////////////////////////////////////////
	/// FILTRAT I CERCA
	////////////////////////////////////////////////////

	/**
	 * Retorna un nou array amb els elements de {@code array} que compleixen la condició del predicat.
	 * 
	 * @param array Array d'elements.
	 * @param predicat Condició que han de complir els elements.
	 * @return Nou array amb els elements que compleixen la condició.
	 */
	public static <T> T[] filtrar(T[] array, Predicate<T> predicat) {
		return java.util.Arrays.stream(array)
				.filter(predicat)
				.toArray(n -> java.util.Arrays.copyOf(array, n));
	}

	/**
	 * Retorna el primer element de {@code array} que compleix la condició del predicat,
	 * o {@code null} si cap element la compleix.
	 * 
	 * @param array Array d'elements.
	 * @param predicat Condició que ha de complir l'element.
	 * @return El primer element que compleix la condició, o {@code null} si no n'hi ha cap.
	 */
	public static <T> T primerQue(T[] array, Predicate<T> predicat) {
		return java.util.Arrays.stream(array)
				.filter(predicat)
				.findFirst()
				.orElse(null);
	}

	/**
	 * Retorna el nombre d'elements de {@code array} que compleixen la condició del predicat.
	 * 
	 * @param array Array d'elements.
	 * @param predicat Condició que han de complir els elements.
	 * @return Nombre d'elements que compleixen la condició.
	 */
	public static <T> long comptar(T[] array, Predicate<T> predicat) {
		return java.util.Arrays.stream(array)
				.filter(predicat)
				.count();
	}

	////////////////////////////////////////////////////
	/// COMPROVACIONS
	////////////////////////////////////////////////////

	/**
	 * Retorna {@code true} si algun element de {@code array} compleix la condició del predicat.
	 *
	 * @param array Array d'elements.
	 * @param predicat Condició a comprovar.
	 * @return {@code true} si algun element compleix la condició.
	 */
	public static <T> boolean existeix(T[] array, Predicate<T> predicat) {
		return java.util.Arrays.stream(array)
				.anyMatch(predicat);
	}

	/**
	 * Retorna {@code true} si tots els elements de {@code array} compleixen la condició del predicat.
	 *
	 * @param array Array d'elements.
	 * @param predicat Condició a comprovar.
	 * @return {@code true} si tots els elements compleixen la condició.
	 */
	public static <T> boolean tots(T[] array, Predicate<T> predicat) {
		return java.util.Arrays.stream(array)
				.allMatch(predicat);
	}

	/**
	 * Retorna {@code true} si cap element de {@code array} compleix la condició del predicat.
	 *
	 * @param array Array d'elements.
	 * @param predicat Condició a comprovar.
	 * @return {@code true} si cap element compleix la condició.
	 */
	public static <T> boolean cap(T[] array, Predicate<T> predicat) {
		return java.util.Arrays.stream(array)
				.noneMatch(predicat);
	}

	////////////////////////////////////////////////////
	/// TRANSFORMACIONS
	////////////////////////////////////////////////////

	/**
	 * Retorna un nou array amb els elements de {@code array} transformats per la funció {@code f}.
	 *
	 * @param array Array d'elements original.
	 * @param resultat Array del tipus de sortida (s'usa com a referència de tipus).
	 * @param f Funció de transformació.
	 * @return Nou array amb els elements transformats.
	 */
	public static <T, V> V[] transformar(T[] array, V[] resultat, Function<T, V> f) {
		return java.util.Arrays.stream(array)
				.map(f)
				.toArray(n -> java.util.Arrays.copyOf(resultat, n));
	}

	////////////////////////////////////////////////////
	/// ORDENACIÓ
	////////////////////////////////////////////////////

	/**
	 * Retorna un nou array amb els elements de {@code array} ordenats segons el comparador.
	 * L'array original no es modifica.
	 *
	 * @param array Array d'elements.
	 * @param comparador Criteri d'ordenació.
	 * @return Nou array ordenat.
	 */
	public static <T> T[] ordenar(T[] array, Comparator<T> comparador) {
		return java.util.Arrays.stream(array)
				.sorted(comparador)
				.toArray(n -> java.util.Arrays.copyOf(array, n));
	}

	/**
	 * Retorna l'element màxim de {@code array} segons el comparador,
	 * o {@code null} si l'array és buit.
	 *
	 * @param array Array d'elements.
	 * @param comparador Criteri de comparació.
	 * @return L'element màxim, o {@code null} si l'array és buit.
	 */
	public static <T> T obtenirMaxim(T[] array, Comparator<T> comparador) {
		if(esBuit(array)) return null;
		return java.util.Arrays.stream(array)
				.max(comparador)
				.orElse(null);
	}

	/**
	 * Retorna l'element mínim de {@code array} segons el comparador,
	 * o {@code null} si l'array és buit.
	 *
	 * @param array Array d'elements.
	 * @param comparador Criteri de comparació.
	 * @return L'element mínim, o {@code null} si l'array és buit.
	 */
	public static <T> T obtenirMinim(T[] array, Comparator<T> comparador) {
		if (esBuit(array)) return null;
		return java.util.Arrays.stream(array)
				.min(comparador)
				.orElse(null);
	}

}
