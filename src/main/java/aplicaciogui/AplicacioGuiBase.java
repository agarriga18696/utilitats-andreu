package aplicaciogui;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

/**
 * Classe base per crear aplicacions d'interfície gràfica.
 * <p>
 * Proporciona el cicle de vida bàsic d'una aplicació Swing: hooks previs i
 * posteriors a la inicialització, gestió automàtica d'excepcions no capturades
 * a l'EDT i suport per aplicar un Look and Feel abans d'inicialitzar.
 *
 * @author Andreu
 * @version 1.1
 */
public abstract class AplicacioGuiBase {

	//-------------------------------
	// ATRIBUTS ESTÀTICS
	//-------------------------------

	private static final Logger LOGGER = Logger.getLogger(AplicacioGuiBase.class.getName());

	//-------------------------------
	// CICLE DE VIDA — HOOKS
	//-------------------------------

	/**
	 * Hook cridat just abans de {@link #inicialitzar()}.
	 * <p>
	 * Les subclasses poden sobreescriure aquest mètode per executar configuracions
	 * prèvies (per exemple, carregar propietats o inicialitzar serveis).
	 * La implementació per defecte no fa res.
	 */
	protected void abansInicialitzar() {
		// implementació per defecte buida
	}

	/**
	 * Inicialitza i construeix la interfície gràfica.
	 * <p>
	 * Aquí s'han de crear els components, configurar-los i afegir-los al contenidor
	 * principal. Aquest mètode ha de ser implementat per les subclasses.
	 */
	protected abstract void inicialitzar();

	/**
	 * Hook cridat just després de {@link #inicialitzar()}.
	 * <p>
	 * Les subclasses poden sobreescriure aquest mètode per executar accions
	 * posteriors a la inicialització (per exemple, mostrar la finestra o iniciar
	 * un temporitzador). La implementació per defecte no fa res.
	 */
	protected void despresInicialitzar() {
		// implementació per defecte buida
	}

	//-------------------------------
	// EXECUCIÓ
	//-------------------------------

	/**
	 * Executa la inicialització de la interfície gràfica a l'EDT, instal·lant
	 * prèviament un gestor d'excepcions no capturades.
	 */
	public final void executar() {
		installarGestorExcepcions();
		SwingUtilities.invokeLater(this::executarCicleDeVida);
	}

	/**
	 * Aplica el Look and Feel indicat i a continuació executa la inicialització
	 * de la interfície gràfica a l'EDT, igual que {@link #executar()}.
	 *
	 * @param laf Nom complet de la classe del Look and Feel a aplicar.
	 */
	public final void executarAmbLookAndFeel(String laf) {
		installarGestorExcepcions();
		SwingUtilities.invokeLater(() -> {
			LookAndFeelSwing.aplicar(laf);
			executarCicleDeVida();
		});
	}

	//-------------------------------
	// MÈTODES PRIVATS
	//-------------------------------

	/**
	 * Executa el cicle de vida complet: hook previ, inicialització i hook posterior.
	 * S'ha de cridar sempre des de l'EDT.
	 */
	private void executarCicleDeVida() {
		abansInicialitzar();
		inicialitzar();
		despresInicialitzar();
	}

	/**
	 * Instal·la un gestor d'excepcions no capturades per a tots els fils,
	 * incloent l'EDT. Quan es produeix una excepció no capturada, es registra
	 * al log i es mostra un diàleg d'error a l'usuari.
	 */
	private void installarGestorExcepcions() {
		Thread.setDefaultUncaughtExceptionHandler((fil, excepcio) -> {
			LOGGER.log(Level.SEVERE, excepcio,
					() -> "Excepció no capturada al fil: " + fil.getName());
			EdtSwing.executarMesTard(() ->
				DialegsSwing.error(null, "Error inesperat",
						"S'ha produït un error inesperat:\n" + excepcio.getMessage())
			);
		});
	}

}
