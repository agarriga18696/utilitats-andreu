package utilitats;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 * Classe d'utilitat per treballar amb dates i hores.
 * 
 * @author Andreu
 * @version 1.1
 */

public class Data {

	////////////////////////////////////////////////////
	/// DATA I HORA
	////////////////////////////////////////////////////

	/**
	 * Retorna la data actual en format "dd/MM/yyyy".
	 */
	public static String avui() {
		return LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	/**
	 * Retorna l'hora actual en format "HH:mm:ss".
	 */
	public static String hora() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	}

	/**
	 * Retorna la data i hora actuals en una sola cadena.
	 */
	public static String ara() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
	}

	/**
	 * Retorna l'any actual.
	 */
	public static int any() {
		return LocalDate.now().getYear();
	}

	/**
	 * Retorna el mes actual.
	 */
	public static int mes() {
		return LocalDate.now().getMonthValue();
	}

	/**
	 * Retorna el dia actual.
	 */
	public static int dia() {
		return LocalDate.now().getDayOfMonth();
	}

	////////////////////////////////////////////////////
	/// CONVERSIONS
	////////////////////////////////////////////////////

	/**
	 * Converteix una cadena en format "dd/MM/yyyy" a LocalDate.
	 * Retorna null si el format no és vàlid.
	 */
	public static LocalDate aLocalDate(String text) {
		if(Cadenes.esBuida(text)) return null;
		try {
			return LocalDate.parse(text.strip(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		} catch(Exception e) {
			return null;
		}
	}
	
	/**
	 * Converteix una data LocalDate a cadena en format "dd/MM/yyyy".
	 * Retorna null si la data no és vàlida.
	 */
	public static String aText(LocalDate data) {
		if(data == null) return null;
		return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	
	////////////////////////////////////////////////////
	/// OPERACIONS AMB DATES
	////////////////////////////////////////////////////

	/**
	 * Calcula l'edat a partir de la data de naixement.
	 */
	public static int edat(LocalDate dataNaixement) {
		return Period.between(dataNaixement, LocalDate.now()).getYears();
	}

	////////////////////////////////////////////////////
	/// VALIDACIONS
	////////////////////////////////////////////////////

	/**
	 * Comprova si s'ha arribat a la majoria d'edat per la data de naixement.
	 */
	public static boolean esMajorEdat(LocalDate dataNaixement) {
		return Period.between(dataNaixement, LocalDate.now()).getYears() >= 18;
	}
	
	/**
	 * Comprova si una cadena en format "dd/MM/yyyy" és una data vàlida.
	 */
	public static boolean esValida(String text) {
		return aLocalDate(text) != null;
	}

}
