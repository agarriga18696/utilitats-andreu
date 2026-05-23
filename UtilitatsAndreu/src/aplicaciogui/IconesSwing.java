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
 * <p>
 * Inclou el motor de càrrega genèric amb memòria cau, els mètodes de càrrega
 * ràpida per a les icones internes de la llibreria (paquet Fugue) i les constants
 * semàntiques que identifiquen les icones per concepte en lloc de per nom de fitxer.
 *
 * @author Andreu
 * @version 2.0
 */
public final class IconesSwing {

	//-------------------------------
	// ATRIBUTS ESTÀTICS
	//-------------------------------

	private static final Logger LOGGER = Logger.getLogger(IconesSwing.class.getName());
	private static final int MIDA_ICONA = 16;
	private static final Map<ClauCache, Icon> CACHE = new ConcurrentHashMap<>();

	/* Ruta interna de les icones Fugue dins el .jar de la llibreria. */
	private static final String RUTA_ICONES_INTERNES = "/aplicaciogui/recursos/icones/fugue/";

	private IconesSwing() {
		/*
		 * Classe d'utilitat no instanciable.
		 */
	}

	//-------------------------------
	// CONSTANTS SEMÀNTIQUES — ACCIONS PRINCIPALS
	//-------------------------------

	public static final String INICI       = "home.png";
	public static final String NOU         = "new.png";
	public static final String NOU_TEXT    = "new-text.png";
	public static final String GUARDAR     = "disk.png";
	public static final String CARREGAR    = "folder-horizontal-open.png";
	public static final String SORTIR      = "door-open-out.png";
	public static final String PANTALLA_COMPLETA = "arrow-out.png";

	//-------------------------------
	// CONSTANTS SEMÀNTIQUES — CRUD
	//-------------------------------

	public static final String AFEGIR      = "plus.png";
	public static final String TREURE      = "minus.png";
	public static final String ELIMINAR    = "bin.png";
	public static final String EDITAR      = "pencil.png";
	public static final String CERCAR      = "magnifier.png";
	public static final String ACTUALITZAR = "arrow-circle.png";

	//-------------------------------
	// CONSTANTS SEMÀNTIQUES — FITXERS I DOCUMENTS
	//-------------------------------

	public static final String OBRIR        = "folder-open.png";
	public static final String GUARDAR_COM  = "disk--pencil.png";
	public static final String IMPRIMIR     = "printer.png";
	public static final String COPIAR       = "document-copy.png";
	public static final String ENGANXAR     = "clipboard-paste.png";
	public static final String RETALLAR     = "scissors.png";
	public static final String NETEJAR      = "eraser.png";
	public static final String IMPORTAR     = "document-import.png";
	public static final String EXPORTAR     = "document-export.png";

	public static final String DOCUMENT        = "document.png";
	public static final String DOCUMENT_BINARI = "document-binary.png";
	public static final String DOCUMENT_PDF    = "document-pdf.png";
	public static final String DOCUMENT_EXCEL  = "document-excel.png";
	public static final String DOCUMENT_WORD   = "document-word.png";
	public static final String DOCUMENT_CODI   = "document-code.png";

	//-------------------------------
	// CONSTANTS SEMÀNTIQUES — LLIBRES
	//-------------------------------

	public static final String LLIBRE          = "book.png";
	public static final String LLIBRE_AFEGIR   = "book--plus.png";
	public static final String LLIBRE_ELIMINAR = "book--minus.png";
	public static final String MANUAL          = "book-question.png";

	//-------------------------------
	// CONSTANTS SEMÀNTIQUES — DADES I VISUALITZACIÓ
	//-------------------------------

	public static final String FILTRAR    = "funnel.png";
	public static final String ORDENAR    = "sort.png";
	public static final String TAULA      = "table.png";
	public static final String LLISTA     = "application-list.png";
	public static final String VEURE      = "eye.png";
	public static final String INFORME    = "report.png";
	public static final String PROPIETATS = "property.png";
	public static final String VISTA      = "monitor.png";
	public static final String RESOLUCIO  = "monitor-medium.png";
	public static final String TEMES      = "palette.png";
	public static final String TEMA       = "palette-medium.png";
	public static final String PREFERENCIES = "gear.png";
	public static final String IDIOMES    = "locale.png";

	//-------------------------------
	// CONSTANTS SEMÀNTIQUES — ESTATS I MISSATGES
	//-------------------------------

	public static final String OK    = "tick.png";
	public static final String ERROR = "cross.png";
	public static final String AVIS  = "exclamation.png";

	//-------------------------------
	// CONSTANTS SEMÀNTIQUES — ESTADÍSTIQUES I ASSOLIMENTS
	//-------------------------------

	public static final String ESTADISTIQUES  = "chart.png";

	public static final String MEDALLA_BRONZE = "medal-bronze.png";
	public static final String MEDALLA_PLATA  = "medal-silver.png";
	public static final String MEDALLA_OR     = "medal.png";

	public static final String TROFEU_BRONZE  = "trophy-bronze.png";
	public static final String TROFEU_PLATA   = "trophy-silver.png";
	public static final String TROFEU_OR      = "trophy.png";

	//-------------------------------
	// CONSTANTS SEMÀNTIQUES — AJUDA I INFORMACIÓ
	//-------------------------------

	public static final String AJUDA          = "lifebuoy.png";
	public static final String INFORMACIO     = "information.png";
	public static final String INTERROGACIO   = "question.png";
	public static final String BUG            = "bug.png";
	public static final String FEEDBACK       = "megaphone.png";
	public static final String COMPARTIR      = "share.png";
	public static final String COMPARTIR_GLOBAL = "globe-share.png";

	//-------------------------------
	// CONSTANTS SEMÀNTIQUES — DATA I TEMPS
	//-------------------------------

	public static final String CALENDARI = "calendar.png";
	public static final String RELLOTGE  = "clock.png";
	public static final String ALARMA    = "alarm-clock.png";

	//-------------------------------
	// CONSTANTS SEMÀNTIQUES — USUARIS I SEGURETAT
	//-------------------------------

	public static final String USUARI          = "user.png";
	public static final String USUARI_ELIMINAR = "user--minus.png";
	public static final String USUARI_AFEGIR   = "user--plus.png";
	public static final String USUARI_EDITAR   = "user--pencil.png";
	public static final String USUARIS         = "users.png";
	public static final String ADRECES         = "address-book.png";
	public static final String CLAU            = "key.png";
	public static final String BLOQUEJAR       = "lock.png";
	public static final String DESBLOQUEJAR    = "lock-unlock.png";
	public static final String SEGURETAT       = "shield.png";

	//-------------------------------
	// CONSTANTS SEMÀNTIQUES — SISTEMA I EINES
	//-------------------------------

	public static final String APLICACIO        = "application.png";
	public static final String APLICACIO_AFEGIR = "application-plus.png";
	public static final String EINES            = "wrench.png";
	public static final String MANTENIMENT      = "hammer.png";
	public static final String SCRIPT           = "script.png";
	public static final String RAPID            = "lightning.png";
	public static final String ASSISTENT        = "wand.png";

	//-------------------------------
	// CONSTANTS SEMÀNTIQUES — TEMES / SISTEMES OPERATIUS
	//-------------------------------

	public static final String WINDOWS = "windows.png";
	public static final String LINUX   = "animal-penguin.png";
	public static final String MACOS   = "mac-os.png";

	/* Àlies semàntics per a les icones dels temes de Look and Feel. */
	public static final String TEMA_JAVA    = TEMA;
	public static final String TEMA_EXTERN  = NOU_TEXT;
	public static final String TEMA_WINDOWS = WINDOWS;
	public static final String TEMA_MAC_OS  = MACOS;
	public static final String TEMA_LINUX   = LINUX;

	//-------------------------------
	// CONSTANTS SEMÀNTIQUES — BASE DE DADES
	//-------------------------------

	public static final String BASE_DADES          = "database.png";
	public static final String BASE_DADES_AFEGIR   = "database--plus.png";
	public static final String BASE_DADES_ELIMINAR = "database--minus.png";
	public static final String BASE_DADES_EDITAR   = "database--pencil.png";
	public static final String BASE_DADES_EXPORTAR = "database--arrow.png";
	public static final String BASE_DADES_NUVOL    = "database-cloud.png";

	//-------------------------------
	// CONSTANTS SEMÀNTIQUES — CARPETES
	//-------------------------------

	public static final String CARPETA          = "folder.png";
	public static final String CARPETA_AFEGIR   = "folder--plus.png";
	public static final String CARPETA_ELIMINAR = "folder--minus.png";
	public static final String CARPETA_EDITAR   = "folder--pencil.png";
	public static final String CARPETA_XARXA    = "folder-network-horizontal-open.png";

	//-------------------------------
	// CONSTANTS SEMÀNTIQUES — MARCADORS I ORGANITZACIÓ
	//-------------------------------

	public static final String CATEGORIES  = "category.png";
	public static final String ETIQUETA    = "tag.png";
	public static final String ETIQUETES   = "tags.png";
	public static final String PREFERIT    = "star.png";
	public static final String MARCADOR    = "bookmark.png";
	public static final String NOTIFICACIO = "bell.png";

	//-------------------------------
	// CONSTANTS SEMÀNTIQUES — NAVEGACIÓ I FLUX
	//-------------------------------

	public static final String NAVEGACIO = "navigation.png";
	public static final String INICIAR   = "arrow-skip.png";
	public static final String CONTINUAR = "arrow-continue.png";
	public static final String ATURAR    = "arrow-stop.png";
	public static final String TORNAR    = "arrow-return.png";

	//-------------------------------
	// CONSTANTS SEMÀNTIQUES — NÚVOL I XARXA
	//-------------------------------

	public static final String NUVOL           = "application-cloud.png";
	public static final String ORDINADOR_NUVOL = "computer-cloud.png";
	public static final String SERVIDOR_NUVOL  = "server-cloud.png";

	//-------------------------------
	// CONSTANTS SEMÀNTIQUES — ALTRES
	//-------------------------------

	public static final String COR = "heart.png";
	public static final String JOC = "game.png";

	//-------------------------------
	// CÀRREGA RÀPIDA — ICONES INTERNES (paquet Fugue)
	//-------------------------------

	/**
	 * Carrega una icona del paquet Fugue intern de la llibreria, amb la mida per defecte.
	 * <p>
	 * El nom de fitxer pot ser una constant semàntica d'aquesta classe (p. ex. {@link #AFEGIR})
	 * o qualsevol constant de {@link IconesFugue}.
	 *
	 * @param nomFitxer Nom del fitxer de la icona.
	 * @return Icona carregada, o {@code null} si no existeix.
	 */
	public static Icon carregar(String nomFitxer) {
		return carregar(IconesSwing.class, RUTA_ICONES_INTERNES, nomFitxer);
	}

	/**
	 * Carrega una icona del paquet Fugue intern de la llibreria, amb la mida indicada.
	 *
	 * @param nomFitxer Nom del fitxer de la icona.
	 * @param mida Mida de la icona en píxels.
	 * @return Icona carregada, o {@code null} si no existeix.
	 */
	public static Icon carregar(String nomFitxer, int mida) {
		return carregar(IconesSwing.class, RUTA_ICONES_INTERNES, nomFitxer, mida);
	}

	//-------------------------------
	// CÀRREGA GENÈRICA — RECURSOS EXTERNS
	//-------------------------------

	/**
	 * Carrega una icona des d'un recurs extern, amb la mida per defecte.
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
	 * Carrega una icona des d'un recurs extern, amb la mida indicada.
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
