package utilitats;

/**
 * Interfície que permet convertir un objecte serialitzat a format CSV.
 * Les classes que la implementin podran utilitzar el mètode Fitxers.guardarObjectesCSV().
 * 
 * @author Andreu
 * @version 1.0
 */

public interface SerialitzableCSV {

	/**
	 * Converteix l'objecte a una línia CSV amb el separador indicat. Normalment ';' o ','.
	 */
	String aCSV(String separador);
	
}
