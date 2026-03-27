package utilitats;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToDoubleFunction;

/**
 * Classe d'utilitat per treballar amb Col·leccions.
 * 
 * @author Andreu
 * @version 1.7
 */

public class Coleccions {

	////////////////////////////////////////////////////
	/// OPERACIONS
	////////////////////////////////////////////////////

	/**
	 * Retorna la suma de tots els elements d'una col·lecció de números.
	 * 
	 * @param llista Col·lecció de números.
	 * @return La suma de tots els elements.
	 */
	public static double suma(Collection<? extends Number> llista) {
		double total = 0;
		for(Number n : llista) {
			total += n.doubleValue();
		}
		return total;
	}

	/**
	 * Genera una col·lecció de números enters aleatoris entre {@code min} i {@code max} inclosos.
	 * 
	 * @param quantitat Nombre d'elements a generar.
	 * @param min Valor mínim inclòs.
	 * @param max Valor màxim inclòs.
	 * @return Llista de números enters aleatoris.
	 */
	public static List<Integer> generarAleatoris(int quantitat, int min, int max) {
		List<Integer> coleccio = new ArrayList<>();
		for(int i = 0; i < quantitat; i++) {
			coleccio.add(Aleatori.enter(min, max));
		}
		return coleccio;
	}

	/**
	 * Genera una col·lecció de números decimals aleatoris entre {@code min} i {@code max} inclosos.
	 * 
	 * @param quantitat Nombre d'elements a generar.
	 * @param min Valor mínim inclòs.
	 * @param max Valor màxim inclòs.
	 * @return Llista de números decimals aleatoris.
	 */
	public static List<Double> generarAleatoris(int quantitat, double min, double max) {
		List<Double> coleccio = new ArrayList<>();
		for(int i = 0; i < quantitat; i++) {
			coleccio.add(Aleatori.decimal(min, max));
		}
		return coleccio;
	}

	////////////////////////////////////////////////////
	/// ESTADÍSTICA
	////////////////////////////////////////////////////

	/**
	 * Retorna el valor màxim d'una col·lecció genèrica.
	 */
	public static <T> T maxim(Collection<T> coleccio, Comparator<T> comparador) {
		return coleccio.stream().max(comparador).orElseThrow();
	}
	
	/**
	 * Retorna el valor mínim d'una col·lecció genèrica.
	 */
	public static <T> T minim(Collection<T> coleccio, Comparator<T> comparador) {
		return coleccio.stream().min(comparador).orElseThrow();
	}
	
	/**
	 * Retorna la mitjana dels elements d'una col·lecció genèrica.
	 */
	public static <T> double mitjana(Collection<T> coleccio, ToDoubleFunction<T> selector) {
		return coleccio.stream().mapToDouble(selector).average().orElse(0);
	}

	////////////////////////////////////////////////////
	/// MANIPULACIÓ
	////////////////////////////////////////////////////

	/**
	 * Elimina totes les ocurrències de {@code element} d'una llista 
	 * i retorna una nova llista sense aquests elements.
	 * La llista original no es modifica.
	 * 
	 * @param llista Llista original d'elements.
	 * @param element Element a eliminar de la llista.
	 * @return Nova llista sense les ocurrències de {@code element}.
	 */
	public static <T> List<T> eliminarElements(Collection<T> llista, T element) {
		List<T> llistaNova = new ArrayList<>(llista);
		llistaNova.removeAll(Collections.singleton(element));
		return llistaNova;
	}

	/**
	 * Retorna una nova llista amb tots els elements de les dues col·leccions passades per paràmetre.
	 * 
	 * @param coleccio1 Primera col·lecció.
	 * @param coleccio2 Segona col·lecció.
	 * @return Nova llista amb tots els elements de les dues col·leccions.
	 */
	public static <T> List<T> unir(Collection<T> coleccio1, Collection<T> coleccio2) {
		List<T> llistaNova = new ArrayList<>(coleccio1);
		llistaNova.addAll(coleccio2);
		return llistaNova;
	}

	/**
	 * Retorna una nova llista amb els elements comuns de les dues col·leccions.
	 * Les col·leccions originals no es modifiquen.
	 *
	 * @param coleccio1 Primera col·lecció.
	 * @param coleccio2 Segona col·lecció.
	 * @return Nova llista amb els elements comuns.
	 */
	public static <T> List<T> elementsComuns(Collection<T> coleccio1, Collection<T> coleccio2) {
		List<T> coleccioComuns = new ArrayList<>(coleccio1);
		coleccioComuns.retainAll(coleccio2);
		return coleccioComuns;
	}

	/**
	 * Retorna una nova llista amb els elements de {@code coleccio1} 
	 * que no apareixen a {@code coleccio2}.
	 * Les col·leccions originals no es modifiquen.
	 *
	 * @param coleccio1 Col·lecció original.
	 * @param coleccio2 Col·lecció amb els elements a eliminar.
	 * @return Nova llista sense els elements comuns.
	 */
	public static <T> List<T> eliminarElementsComuns(Collection<T> coleccio1, Collection<T> coleccio2) {
		List<T> coleccioNova = new ArrayList<>(coleccio1);
		coleccioNova.removeAll(coleccio2);
		return coleccioNova;
	}

	/**
	 * Retorna una llista amb els elements de {@code llista} majors que {@code valorObjectiu}.
	 * 
	 * @param llista Llista d'elements numèrics.
	 * @param valorObjectiu Valor de referència per el filtratge.
	 * @return Llista amb els elements majors que {@code valorObjectiu}.
	 */
	public static <T extends Number & Comparable<T>> List<T> obtenirNumerosMajorsQueN(Collection<T> llista, T valorObjectiu) {
		List<T> llistaFinal = new ArrayList<>();
		for(T n : llista) {
			if(n.compareTo(valorObjectiu) > 0) llistaFinal.add(n);
		}
		return llistaFinal;
	}

	/**
	 * Retorna una llista amb els elements de {@code llista} menors que {@code valorObjectiu}.
	 * 
	 * @param llista Llista d'elements numèrics.
	 * @param valorObjectiu Valor de referència per el filtratge.
	 * @return Llista amb els elements menors que {@code valorObjectiu}.
	 */
	public static <T extends Number & Comparable<T>> List<T> obtenirNumerosMenorsQueN(Collection<T> llista, T valorObjectiu) {
		List<T> llistaFinal = new ArrayList<>();
		for(T n : llista) {
			if(n.compareTo(valorObjectiu) < 0) llistaFinal.add(n);
		}
		return llistaFinal;
	}

}
