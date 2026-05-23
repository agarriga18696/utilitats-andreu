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
 * @version 1.2
 */
public final class IconesSwing {

	//-------------------------------
	// ATRIBUTS ESTÀTICS
	//-------------------------------

	private static final Logger LOGGER = Logger.getLogger(IconesSwing.class.getName());
	private static final int MIDA_ICONA = 16;
	private static final Map<ClauCache, Icon> CACHE = new ConcurrentHashMap<>();

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
	 * <p>
	 * Les icones carregades es guarden en una memòria cau. Si la icona no existeix,
	 * es retorna {@code null} sense emmagatzemar res a la cau.
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
		ClauCache clau = new ClauCache(classeBase, rutaNormalitzada, nomFitxer, mida);

		return CACHE.computeIfAbsent(clau, k -> {
			URL url = k.classeBase().getResource(k.ruta() + k.nomFitxer());

			if(url == null) {
				LOGGER.log(Level.WARNING, () -> "No s'ha trobat la icona: " + k.ruta() + k.nomFitxer());
				return null;
			}

			ImageIcon iconaOriginal = new ImageIcon(url);
			Image imatgeEscalada = iconaOriginal.getImage()
					.getScaledInstance(k.mida(), k.mida(), Image.SCALE_SMOOTH);

			return new ImageIcon(imatgeEscalada);
		});
	}

	//-------------------------------
	// MÈTODES PRIVATS
	//-------------------------------

	/**
	 * Normalitza la ruta assegurant-se que comença i acaba amb "/".
	 * Exemple: "recursos/icones" → "/recursos/icones/"
	 *
	 * @param ruta Ruta a normalitzar.
	 * @return Ruta normalitzada.
	 */
	private static String normalitzarRuta(String ruta) {
		String rutaNormalitzada = ruta.startsWith("/") ? ruta : "/" + ruta;
		return rutaNormalitzada.endsWith("/") ? rutaNormalitzada : rutaNormalitzada + "/";
	}

	//-------------------------------
	// TIPUS INTERNS
	//-------------------------------

	/**
	 * Clau de la memòria cau d'icones.
	 * Agrupa els quatre paràmetres que identifiquen unívocament una icona carregada.
	 *
	 * @param classeBase Classe base usada per localitzar el recurs.
	 * @param ruta Ruta normalitzada del recurs.
	 * @param nomFitxer Nom del fitxer de la icona.
	 * @param mida Mida de la icona en píxels.
	 */
	private record ClauCache(Class<?> classeBase, String ruta, String nomFitxer, int mida) {}

}
