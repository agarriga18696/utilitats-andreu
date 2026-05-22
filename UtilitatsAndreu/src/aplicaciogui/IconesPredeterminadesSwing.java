package aplicaciogui;

import javax.swing.Icon;

/**
 * Catàleg d'icones predeterminades incloses dins la llibreria gràfica.
 * <p>
 * Aquesta classe només defineix els noms de les icones i ofereix mètodes
 * còmodes per carregar-les des dels recursos interns de la llibreria.
 * 
 * @author Andreu
 * @version 1.1
 */
public final class IconesPredeterminadesSwing {

	private IconesPredeterminadesSwing() {
		/*
		 * Classe d'utilitat no instanciable.
		 */
	}

	//-------------------------------
	// ACCIONS PRINCIPALS
	//-------------------------------

	public static final String INICI = "home.png";
	public static final String NOU = "new.png";
	public static final String NOU_TEXT = "new-text.png";
	public static final String GUARDAR = "disk.png";
	public static final String CARREGAR = "folder-horizontal-open.png";
	public static final String SORTIR = "door-open-out.png";
	public static final String PANTALLA_COMPLETA = "arrow-out.png";

	//-------------------------------
	// ACCIONS CRUD
	//-------------------------------

	public static final String AFEGIR = "plus.png";
	public static final String TREURE = "minus.png";
	public static final String ELIMINAR = "bin.png";
	public static final String EDITAR = "pencil.png";
	public static final String CERCAR = "magnifier.png";
	public static final String ACTUALITZAR = "arrow-circle.png";

	//-------------------------------
	// FITXERS I DOCUMENTS
	//-------------------------------

	public static final String OBRIR = "folder-open.png";
	public static final String GUARDAR_COM = "disk--pencil.png";
	public static final String IMPRIMIR = "printer.png";
	public static final String COPIAR = "document-copy.png";
	public static final String ENGANXAR = "clipboard-paste.png";
	public static final String RETALLAR = "scissors.png";
	public static final String NETEJAR = "eraser.png";
	public static final String IMPORTAR = "document-import.png";
	public static final String EXPORTAR = "document-export.png";

	public static final String DOCUMENT = "document.png";
	public static final String DOCUMENT_BINARI = "document-binary.png";
	public static final String DOCUMENT_PDF = "document-pdf.png";
	public static final String DOCUMENT_EXCEL = "document-excel.png";
	public static final String DOCUMENT_WORD = "document-word.png";
	public static final String DOCUMENT_CODI = "document-code.png";

	//-------------------------------
	// LLIBRES
	//-------------------------------

	public static final String LLIBRE = "book.png";
	public static final String LLIBRE_AFEGIR = "book--plus.png";
	public static final String LLIBRE_ELIMINAR = "book--minus.png";
	public static final String MANUAL = "book-question.png";

	//-------------------------------
	// DADES I VISUALITZACIÓ
	//-------------------------------

	public static final String FILTRAR = "funnel.png";
	public static final String ORDENAR = "sort.png";
	public static final String TAULA = "table.png";
	public static final String LLISTA = "application-list.png";
	public static final String VEURE = "eye.png";
	public static final String INFORME = "report.png";
	public static final String PROPIETATS = "property.png";
	public static final String VISTA = "monitor.png";
	public static final String RESOLUCIO = "monitor-medium.png";
	public static final String TEMES = "palette.png";
	public static final String TEMA = "palette-medium.png";
	public static final String PREFERENCIES = "gear.png";
	public static final String IDIOMES = "locale.png";

	//-------------------------------
	// ESTATS I MISSATGES
	//-------------------------------

	public static final String OK = "tick.png";
	public static final String ERROR = "cross.png";
	public static final String AVIS = "exclamation.png";

	//-------------------------------
	// ESTADÍSTIQUES I ASSOLIMENTS
	//-------------------------------

	public static final String ESTADISTIQUES = "chart.png";

	public static final String MEDALLA_BRONZE = "medal-bronze.png";
	public static final String MEDALLA_PLATA = "medal-silver.png";
	public static final String MEDALLA_OR = "medal.png";

	public static final String TROFEU_BRONZE = "trophy-bronze.png";
	public static final String TROFEU_PLATA = "trophy-silver.png";
	public static final String TROFEU_OR = "trophy.png";

	//-------------------------------
	// AJUDA I INFORMACIÓ
	//-------------------------------

	public static final String AJUDA = "lifebuoy.png";
	public static final String INFORMACIO = "information.png";
	public static final String INTERROGACIO = "question.png";
	public static final String BUG = "bug.png";
	public static final String FEEDBACK = "megaphone.png";
	public static final String COMPARTIR = "share.png";
	public static final String COMPARTIR_GLOBAL = "globe-share.png";

	//-------------------------------
	// DATA I TEMPS
	//-------------------------------

	public static final String CALENDARI = "calendar.png";
	public static final String RELLOTGE = "clock.png";
	public static final String ALARMA = "alarm-clock.png";

	//-------------------------------
	// USUARIS I SEGURETAT
	//-------------------------------

	public static final String USUARI = "user.png";
	public static final String USUARI_ELIMINAR = "user--minus.png";
	public static final String USUARI_AFEGIR = "user--plus.png";
	public static final String USUARI_EDITAR = "user--pencil.png";
	public static final String USUARIS = "users.png";
	public static final String ADRECES = "address-book.png";
	public static final String CLAU = "key.png";
	public static final String BLOQUEJAR = "lock.png";
	public static final String DESBLOQUEJAR = "lock-unlock.png";
	public static final String SEGURETAT = "shield.png";

	//-------------------------------
	// SISTEMA I EINES
	//-------------------------------

	public static final String APLICACIO = "application.png";
	public static final String APLICACIO_AFEGIR = "application-plus.png";
	public static final String EINES = "wrench.png";
	public static final String MANTENIMENT = "hammer.png";
	public static final String SCRIPT = "script.png";
	public static final String RAPID = "lightning.png";
	public static final String ASSISTENT = "wand.png";

	//-------------------------------
	// TEMES / SISTEMES
	//-------------------------------
	
	public static final String WINDOWS = "windows.png";
	public static final String LINUX = "animal-penguin.png";
	public static final String MACOS = "mac-os.png";

	public static final String TEMA_JAVA = TEMA;
	public static final String TEMA_EXTERN = NOU_TEXT;

	public static final String TEMA_WINDOWS = WINDOWS;
	public static final String TEMA_MAC_OS = MACOS;
	public static final String TEMA_LINUX = LINUX;

	//-------------------------------
	// BASE DE DADES
	//-------------------------------

	public static final String BASE_DADES = "database.png";
	public static final String BASE_DADES_AFEGIR = "database--plus.png";
	public static final String BASE_DADES_ELIMINAR = "database--minus.png";
	public static final String BASE_DADES_EDITAR = "database--pencil.png";
	public static final String BASE_DADES_EXPORTAR = "database--arrow.png";
	public static final String BASE_DADES_NUVOL = "database-cloud.png";

	//-------------------------------
	// CARPETES
	//-------------------------------

	public static final String CARPETA = "folder.png";
	public static final String CARPETA_AFEGIR = "folder--plus.png";
	public static final String CARPETA_ELIMINAR = "folder--minus.png";
	public static final String CARPETA_EDITAR = "folder--pencil.png";
	public static final String CARPETA_XARXA = "folder-network-horizontal-open.png";

	//-------------------------------
	// MARCADORS I ORGANITZACIÓ
	//-------------------------------

	public static final String CATEGORIES = "category.png";
	public static final String ETIQUETA = "tag.png";
	public static final String ETIQUETES = "tags.png";
	public static final String PREFERIT = "star.png";
	public static final String MARCADOR = "bookmark.png";
	public static final String NOTIFICACIO = "bell.png";

	//-------------------------------
	// NAVEGACIÓ I FLUX
	//-------------------------------

	public static final String NAVEGACIO = "navigation.png";
	public static final String INICIAR = "arrow-skip.png";
	public static final String CONTINUAR = "arrow-continue.png";
	public static final String ATURAR = "arrow-stop.png";
	public static final String TORNAR = "arrow-return.png";

	//-------------------------------
	// NÚVOL I XARXA
	//-------------------------------

	public static final String NUVOL = "application-cloud.png";
	public static final String ORDINADOR_NUVOL = "computer-cloud.png";
	public static final String SERVIDOR_NUVOL = "server-cloud.png";

	//-------------------------------
	// ALTRES
	//-------------------------------

	public static final String COR = "heart.png";
	public static final String JOC = "game.png";

	//-------------------------------
	// MÈTODES DE LA CLASSE
	//-------------------------------

	/**
	 * Carrega una icona predeterminada amb la mida per defecte.
	 * 
	 * @param nomFitxer Nom del fitxer de la icona.
	 * @return Icona carregada, o {@code null} si no existeix.
	 */
	public static Icon carregar(String nomFitxer) {
		return IconesPaquetSwing.carregar(nomFitxer);
	}

	/**
	 * Carrega una icona predeterminada amb la mida indicada.
	 * 
	 * @param nomFitxer Nom del fitxer de la icona.
	 * @param mida Mida de la icona en píxels.
	 * @return Icona carregada, o {@code null} si no existeix.
	 */
	public static Icon carregar(String nomFitxer, int mida) {
		return IconesPaquetSwing.carregar(nomFitxer, mida);
	}

}