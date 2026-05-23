package aplicaciogui;

import java.awt.Image;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Classe d'utilitat per carregar icones des dels recursos del projecte.
 * 
 * @author Andreu
 * @version 1.1
 */
public final class IconesSwing {

	//-------------------------------
	// ATRIBUTS ESTÀTICS
	//-------------------------------

	private static final Logger LOGGER = Logger.getLogger(IconesSwing.class.getName());
	private static final int MIDA_ICONA = 16;
	private static final Map<String, Icon> CACHE = new ConcurrentHashMap<>();

	private IconesSwing() {
		/*
		 * Classe d'utilitat no instanciable.
		 */
	}

	//-------------------------------
	// CÀRREGA D'ICONES
	//-------------------------------

	/**
	 * Carrega una icona amb la mida per defecte.
	 * 
	 * @param classeBase Classe base usada per localitzar el recurs.
	 * @param ruta Ruta del recurs dins el classpath. Exemple: {@code "/recursos/icones/"}.
	 * @param nomFitxer Nom del fitxer de la icona.
	 * @return Icona carregada, o {@code null} si no existeix.
	 */
	public static Icon carregar(Class<?> classeBase, String ruta, String nomFitxer) {
		return carregar(classeBase, ruta, nomFitxer, MIDA_ICONA);
	}

	/**
	 * Carrega una icona amb la mida indicada.
	 * 
	 * @param classeBase Classe base usada per localitzar el recurs.
	 * @param ruta Ruta del recurs dins el classpath. Exemple: {@code "/recursos/icones/"}.
	 * @param nomFitxer Nom del fitxer de la icona.
	 * @param mida Mida de la icona en píxels.
	 * @return Icona carregada, o {@code null} si no existeix.
	 */
	public static Icon carregar(Class<?> classeBase, String ruta, String nomFitxer, int mida) {
		
		if(mida <= 0) {
			throw new IllegalArgumentException("La mida de la icona ha de ser positiva.");
		}

		if(classeBase == null) {
			throw new IllegalArgumentException("La classe base no pot ser null.");
		}

		if(ruta == null || ruta.isBlank()) {
			throw new IllegalArgumentException("La ruta no pot ser buida.");
		}

		if(nomFitxer == null || nomFitxer.isBlank()) {
			throw new IllegalArgumentException("El nom del fitxer no pot ser buit.");
		}

		String rutaNormalitzada = normalitzarRuta(ruta);
		String clau = classeBase.getName() + "|" + rutaNormalitzada + nomFitxer + "#" + mida;

		if(CACHE.containsKey(clau)) {
			return CACHE.get(clau);
		}

		URL url = classeBase.getResource(rutaNormalitzada + nomFitxer);

		if(url == null) {
			LOGGER.log(Level.WARNING, "No s'ha trobat la icona: " + rutaNormalitzada + nomFitxer);
			return null;
		}

		ImageIcon iconaOriginal = new ImageIcon(url);

		Image imatgeEscalada = iconaOriginal.getImage()
				.getScaledInstance(mida, mida, Image.SCALE_SMOOTH);

		Icon icona = new ImageIcon(imatgeEscalada);
		CACHE.put(clau, icona);

		return icona;
	}

	//-------------------------------
	// MÈTODES PRIVATS
	//-------------------------------

	/**
	 * Normalitza la ruta assegurant-se que comença i acaba amb "/".
	 * Exemple: "recursos/icones" -> "/recursos/icones/"
	 * 
	 * @param ruta Ruta a normalitzar.
	 * @return Ruta normalitzada.
	 */
	private static String normalitzarRuta(String ruta) {
		String rutaNormalitzada = ruta.startsWith("/") ? ruta : "/" + ruta;
		return rutaNormalitzada.endsWith("/") ? rutaNormalitzada : rutaNormalitzada + "/";
	}

}
