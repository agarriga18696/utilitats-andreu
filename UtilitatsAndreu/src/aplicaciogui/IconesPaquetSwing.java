package aplicaciogui;

import javax.swing.Icon;

/**
 * Classe d'utilitat per carregar icones agrupades per paquets dins la llibreria.
 * <p>
 * Les icones s'han d'ubicar dins:
 * {@code /aplicaciogui/recursos/icones/nom_paquet/}
 * 
 * @author Andreu
 * @version 1.0
 */
public final class IconesPaquetSwing {

	private IconesPaquetSwing() {
		/*
		 * Classe d'utilitat no instanciable.
		 */
	}

	//-------------------------------
	// RUTA
	//-------------------------------

	private static final String RUTA_BASE_ICONES = "/aplicaciogui/recursos/icones/";
	private static final String PAQUET_PREDETERMINAT = "fugue";

	//-------------------------------
	// MÈTODES DE LA CLASSE
	//-------------------------------

	/**
	 * Carrega una icona del paquet predeterminat.
	 * 
	 * @param nomFitxer Nom del fitxer de la icona.
	 * @return Icona carregada, o {@code null} si no existeix.
	 */
	public static Icon carregar(String nomFitxer) {
		return carregar(PAQUET_PREDETERMINAT, nomFitxer);
	}

	/**
	 * Carrega una icona del paquet predeterminat amb una mida concreta.
	 * 
	 * @param nomFitxer Nom del fitxer de la icona.
	 * @param mida Mida de la icona en píxels.
	 * @return Icona carregada, o {@code null} si no existeix.
	 */
	public static Icon carregar(String nomFitxer, int mida) {
		return carregar(PAQUET_PREDETERMINAT, nomFitxer, mida);
	}

	/**
	 * Carrega una icona d'un paquet concret.
	 * 
	 * @param paquet Nom del paquet d'icones.
	 * @param nomFitxer Nom del fitxer de la icona.
	 * @return Icona carregada, o {@code null} si no existeix.
	 */
	public static Icon carregar(String paquet, String nomFitxer) {
		return IconesSwing.carregar(
				IconesPaquetSwing.class,
				crearRutaPaquet(paquet),
				nomFitxer
				);
	}

	/**
	 * Carrega una icona d'un paquet concret amb una mida concreta.
	 * 
	 * @param paquet Nom del paquet d'icones.
	 * @param nomFitxer Nom del fitxer de la icona.
	 * @param mida Mida de la icona en píxels.
	 * @return Icona carregada, o {@code null} si no existeix.
	 */
	public static Icon carregar(String paquet, String nomFitxer, int mida) {
		return IconesSwing.carregar(
				IconesPaquetSwing.class,
				crearRutaPaquet(paquet),
				nomFitxer,
				mida
				);
	}

	//-------------------------------
	// MÈTODES PRIVATS
	//-------------------------------

	private static String crearRutaPaquet(String paquet) {

		if(paquet == null || paquet.isBlank()) {
			throw new IllegalArgumentException("El paquet d'icones no pot ser buit.");
		}

		return RUTA_BASE_ICONES + paquet.strip() + "/";
	}

}