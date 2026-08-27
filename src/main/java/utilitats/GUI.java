package utilitats;

import java.awt.Component;
import java.util.function.Supplier;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import aplicaciogui.FinestresSwing;
import aplicaciogui.PanellsSwing;

/**
 * Façana de compatibilitat per a utilitats Swing antigues.
 * <p>
 * En codi nou es recomana utilitzar directament:
 * {@code FinestresSwing}, {@code PanellsSwing}, {@code ComponentsSwing},
 * {@code MenusSwing}, {@code DialegsSwing}, {@code IconesSwing}
 * i {@code LookAndFeelSwing}.
 * 
 * @author Andreu
 * @version 1.0
 * 
 * @deprecated Des de la versió 4.3. Utilitza les classes específiques del paquet
 * 				{@code aplicaciogui} en lloc d'aquesta façana.
 */
@Deprecated(since = "4.3", forRemoval = false)
public final class GUI {

	private GUI() {
		/*
		 * Classe d'utilitat no instanciable.
		 */
	}

	//-------------------------------
	// FINESTRES
	//-------------------------------

	/**
	 * Retorna un {@code JFrame} configurat amb els paràmetres indicats.
	 * 
	 * @param title Títol de la finestra.
	 * @param width Amplada de la finestra.
	 * @param height Altura de la finestra.
	 * @param c Component relatiu.
	 * @param operation Operació de tancament.
	 * @return Finestra configurada.
	 */
	public static JFrame frame(String title, int width, int height, Component c, int operation) {
		return FinestresSwing.frame(title, width, height, c, operation);
	}

	//-------------------------------
	// PANELLS
	//-------------------------------

	/**
	 * Retorna un {@code JPanel} amb {@code GridLayout}.
	 * 
	 * @param rows Nombre de files.
	 * @param cols Nombre de columnes.
	 * @return Panell creat.
	 */
	public static JPanel panelGrid(int rows, int cols) {
		return PanellsSwing.grid(rows, cols);
	}

	/**
	 * Retorna un {@code JPanel} amb {@code GridLayout} i separació.
	 * 
	 * @param rows Nombre de files.
	 * @param cols Nombre de columnes.
	 * @param hgap Separació horitzontal.
	 * @param vgap Separació vertical.
	 * @return Panell creat.
	 */
	public static JPanel panelGrid(int rows, int cols, int hgap, int vgap) {
		return PanellsSwing.grid(rows, cols, hgap, vgap);
	}

	/**
	 * Retorna un {@code JPanel} amb {@code GridLayout} omplert amb components.
	 * 
	 * @param rows Nombre de files.
	 * @param cols Nombre de columnes.
	 * @param factory Creador de components.
	 * @return Panell creat.
	 */
	public static JPanel panelGrid(int rows, int cols, Supplier<? extends JComponent> factory) {
		return PanellsSwing.grid(rows, cols, factory);
	}

	/**
	 * Retorna un {@code JPanel} amb {@code GridLayout}, separació i components.
	 * 
	 * @param rows Nombre de files.
	 * @param cols Nombre de columnes.
	 * @param hgap Separació horitzontal.
	 * @param vgap Separació vertical.
	 * @param factory Creador de components.
	 * @return Panell creat.
	 */
	public static JPanel panelGrid(
			int rows,
			int cols,
			int hgap,
			int vgap,
			Supplier<? extends JComponent> factory
			) {

		return PanellsSwing.grid(rows, cols, hgap, vgap, factory);
	}

	//-------------------------------
	// COMPONENTS
	//-------------------------------

	/**
	 * Retorna el component de l'índex indicat si coincideix amb el tipus esperat.
	 * 
	 * @param jcomp Component contenidor.
	 * @param n Índex del component.
	 * @param type Tipus esperat.
	 * @param <T> Tipus del component retornat.
	 * @return Component convertit, o {@code null} si no coincideix.
	 */
	public static <T extends Component> T getComponent(JComponent jcomp, int n, Class<T> type) {
		return PanellsSwing.obtenirComponent(jcomp, n, type);
	}

}