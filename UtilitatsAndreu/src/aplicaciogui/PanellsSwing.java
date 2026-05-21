package aplicaciogui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.function.Supplier;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Classe d'utilitat per crear panells Swing amb diferents layouts.
 * 
 * @author Andreu
 * @version 1.0
 */
public final class PanellsSwing {

	private PanellsSwing() {
		/*
		 * Classe d'utilitat no instanciable.
		 */
	}

	//-------------------------------
	// PANELL GENÈRIC
	//-------------------------------

	/**
	 * Retorna un {@code JPanel} amb el layout indicat.
	 * 
	 * @param layout Layout del panell.
	 * @return Panell creat.
	 */
	public static JPanel panel(LayoutManager layout) {
		return new JPanel(layout);
	}

	/**
	 * Retorna un {@code JPanel} amb layout i marge exterior.
	 * 
	 * @param layout Layout del panell.
	 * @param marge Marge exterior en píxels.
	 * @return Panell creat amb marge.
	 */
	public static JPanel panelAmbMarge(LayoutManager layout, int marge) {
		JPanel panel = new JPanel(layout);
		panel.setBorder(new EmptyBorder(marge, marge, marge, marge));
		return panel;
	}

	//-------------------------------
	// BORDERLAYOUT
	//-------------------------------

	/**
	 * Retorna un {@code JPanel} amb layout {@code BorderLayout}.
	 * 
	 * @return Panell amb layout {@code BorderLayout}.
	 */
	public static JPanel borderLayout() {
		return new JPanel(new BorderLayout());
	}

	//-------------------------------
	// FLOWLAYOUT
	//-------------------------------

	/**
	 * Retorna un {@code JPanel} amb {@code FlowLayout}.
	 * 
	 * @return Panell amb {@code FlowLayout}.
	 */
	public static JPanel flow() {
		return new JPanel(new FlowLayout());
	}

	/**
	 * Retorna un {@code JPanel} amb {@code FlowLayout} i alineació indicada.
	 * 
	 * @param alineacio Alineació del {@code FlowLayout}.
	 * @return Panell amb {@code FlowLayout}.
	 */
	public static JPanel flow(int alineacio) {
		return new JPanel(new FlowLayout(alineacio));
	}

	//-------------------------------
	// GRIDLAYOUT
	//-------------------------------
	
	/**
	 * Retorna un {@code JPanel} amb {@code GridLayout}.
	 * 
	 * @param files Nombre de files.
	 * @param columnes Nombre de columnes.
	 * @return Panell amb {@code GridLayout}.
	 */
	public static JPanel grid(int files, int columnes) {
		validarGrid(files, columnes);
		return new JPanel(new GridLayout(files, columnes));
	}
	
	/**
	 * Retorna un {@code JPanel} amb {@code GridLayout} i separació.
	 * 
	 * @param files Nombre de files.
	 * @param columnes Nombre de columnes.
	 * @param hgap Separació horitzontal.
	 * @param vgap Separació vertical.
	 * @return Panell amb {@code GridLayout}.
	 */
	public static JPanel grid(int files, int columnes, int hgap, int vgap) {
		validarGrid(files, columnes);
		return new JPanel(new GridLayout(files, columnes, hgap, vgap));
	}

	/**
	 * Retorna un {@code JPanel} amb {@code GridLayout} omplert amb components creats pel {@code Supplier}.
	 * 
	 * @param files Nombre de files.
	 * @param columnes Nombre de columnes.
	 * @param factory Creador de components.
	 * @return Panell amb components afegits.
	 */
	public static JPanel grid(int files, int columnes, Supplier<? extends JComponent> factory) {
		JPanel panel = grid(files, columnes);
		omplir(panel, files * columnes, factory);
		return panel;
	}

	/**
	 * Retorna un {@code JPanel} amb {@code GridLayout}, separació i components creats pel {@code Supplier}.
	 * 
	 * @param files Nombre de files.
	 * @param columnes Nombre de columnes.
	 * @param hgap Separació horitzontal.
	 * @param vgap Separació vertical.
	 * @param factory Creador de components.
	 * @return Panell amb components afegits.
	 */
	public static JPanel grid(
			int files,
			int columnes,
			int hgap,
			int vgap,
			Supplier<? extends JComponent> factory
			) {

		JPanel panel = grid(files, columnes, hgap, vgap);
		omplir(panel, files * columnes, factory);
		return panel;
	}

	//-------------------------------
	// BOXLAYOUT
	//-------------------------------

	/**
	 * Retorna un {@code JPanel} amb {@code BoxLayout} vertical.
	 * 
	 * @return Panell amb {@code BoxLayout.Y_AXIS}.
	 */
	public static JPanel boxVertical() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		return panel;
	}

	/**
	 * Retorna un {@code JPanel} amb {@code BoxLayout} horitzontal.
	 * 
	 * @return Panell amb {@code BoxLayout.X_AXIS}.
	 */
	public static JPanel boxHorizontal() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		return panel;
	}

	//-------------------------------
	// COMPONENTS
	//-------------------------------

	/**
	 * Retorna el component d'un contenidor si existeix i coincideix amb el tipus indicat.
	 * 
	 * @param component Component contenidor.
	 * @param index Índex del component fill.
	 * @param tipus Tipus esperat del component.
	 * @param <T> Tipus del component retornat.
	 * @return Component convertit al tipus indicat, o {@code null} si no coincideix.
	 */
	public static <T extends Component> T obtenirComponent(JComponent component, int index, Class<T> tipus) {

		if(component == null) {
			throw new IllegalArgumentException("El component no pot ser null.");
		}

		if(index < 0 || index >= component.getComponentCount()) {
			return null;
		}

		Component fill = component.getComponent(index);

		if(!tipus.isInstance(fill)) {
			return null;
		}

		return tipus.cast(fill);
	}

	//-------------------------------
	// MÈTODES PRIVATS
	//-------------------------------

	private static void omplir(JPanel panel, int quantitat, Supplier<? extends JComponent> factory) {
		for(int i = 0; i < quantitat; i++) {
			panel.add(factory.get());
		}
	}

	private static void validarGrid(int files, int columnes) {
		if(files <= 0 || columnes <= 0) {
			throw new IllegalArgumentException("Les files i columnes han de ser positives.");
		}
	}

}
