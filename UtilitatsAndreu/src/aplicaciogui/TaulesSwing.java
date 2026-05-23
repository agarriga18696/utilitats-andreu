package aplicaciogui;

import java.awt.Component;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 * Classe d'utilitat per crear i gestionar taules Swing genèriques.
 * <p>
 * Ofereix mètodes de fàbrica per construir un {@link JTable} a partir d'una
 * llista de columnes ({@link ColumnaSwing}) i un model ({@link ModelTaulaSwing}).
 * <p>
 * Exemple d'ús:
 * <pre>
 *     List&lt;ColumnaSwing&lt;Producte&gt;&gt; columnes = List.of(
 *         new ColumnaSwing&lt;&gt;("ID",    Producte::getId),
 *         new ColumnaSwing&lt;&gt;("Nom",   Producte::getNom),
 *         new ColumnaSwing&lt;&gt;("Preu",  Producte::getPreu)
 *     );
 *
 *     ModelTaulaSwing&lt;Producte&gt; model = TaulesSwing.model(productes, columnes);
 *     JTable taula = TaulesSwing.taula(model);
 *
 *     // Quan calgui actualitzar les dades:
 *     model.actualitzar(novesLlista);
 *
 *     // Per obtenir l'element seleccionat:
 *     Optional&lt;Producte&gt; seleccionat = TaulesSwing.getFilaSeleccionada(taula);
 * </pre>
 *
 * @author Andreu
 * @version 1.0
 */
public final class TaulesSwing {

	private TaulesSwing() {
		/*
		 * Classe d'utilitat no instanciable.
		 */
	}

	//-------------------------------
	// FÀBRICA
	//-------------------------------

	/**
	 * Crea un {@link ModelTaulaSwing} amb les dades i columnes indicades.
	 *
	 * @param <T> Tipus de l'objecte que representa cada fila.
	 * @param dades Llista inicial d'objectes.
	 * @param columnes Definicions de les columnes.
	 * @return Model de taula creat.
	 */
	public static <T> ModelTaulaSwing<T> model(List<T> dades, List<ColumnaSwing<T>> columnes) {
		Objects.requireNonNull(dades, "Les dades no poden ser null.");
		Objects.requireNonNull(columnes, "Les columnes no poden ser null.");
		return new ModelTaulaSwing<>(dades, columnes);
	}

	/**
	 * Crea un {@link JTable} a partir d'un {@link ModelTaulaSwing} ja construït.
	 * <p>
	 * La taula es configura amb selecció simple, ordenació per capçalera,
	 * capçaleres fixes i alçada de fila de 24 píxels.
	 *
	 * @param <T> Tipus de l'objecte que representa cada fila.
	 * @param model Model de taula.
	 * @return Taula configurada.
	 */
	public static <T> JTable taula(ModelTaulaSwing<T> model) {
		Objects.requireNonNull(model, "El model no pot ser null.");

		JTable taula = new JTable(model);

		taula.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		taula.setAutoCreateRowSorter(true);
		taula.setFillsViewportHeight(true);
		taula.setRowHeight(24);
		taula.getTableHeader().setReorderingAllowed(false);

		return taula;
	}

	/**
	 * Crea un {@link ModelTaulaSwing} i un {@link JTable} en un sol pas.
	 * <p>
	 * Equivalent a cridar {@link #model(List, List)} i després {@link #taula(ModelTaulaSwing)}.
	 * Útil quan no cal guardar una referència al model.
	 * Si cal actualitzar les dades posteriorment, useu el mètode en dos passos
	 * per conservar la referència al model.
	 *
	 * @param <T> Tipus de l'objecte que representa cada fila.
	 * @param dades Llista inicial d'objectes.
	 * @param columnes Definicions de les columnes.
	 * @return Taula configurada.
	 */
	public static <T> JTable taula(List<T> dades, List<ColumnaSwing<T>> columnes) {
		return taula(model(dades, columnes));
	}

	//-------------------------------
	// AMPLADES DE COLUMNA
	//-------------------------------

	/**
	 * Ajusta l'amplada de cada columna al contingut més ample que conté,
	 * tenint en compte tant la capçalera com totes les cel·les.
	 * <p>
	 * Activa {@link JTable#AUTO_RESIZE_OFF} perquè les amplades calculades
	 * no siguin sobreescrites pel mode d'auto-redimensionat.
	 * Cridar aquest mètode un cop les dades ja siguin a la taula.
	 *
	 * @param taula Taula sobre la qual s'aplica l'ajust.
	 */
	public static void ajustarAmpladesAContingut(JTable taula) {
		Objects.requireNonNull(taula, "La taula no pot ser null.");

		taula.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		for(int col = 0; col < taula.getColumnCount(); col++) {

			TableColumn columna = taula.getColumnModel().getColumn(col);

			/* Amplada de la capçalera. */
			TableCellRenderer headerRenderer = columna.getHeaderRenderer();
			if(headerRenderer == null) {
				headerRenderer = taula.getTableHeader().getDefaultRenderer();
			}
			Component headerComp = headerRenderer.getTableCellRendererComponent(
					taula, columna.getHeaderValue(), false, false, 0, col);

			int maxAmplada = headerComp.getPreferredSize().width + 10;

			/* Amplada màxima de les cel·les. */
			for(int fila = 0; fila < taula.getRowCount(); fila++) {
				TableCellRenderer renderer = taula.getCellRenderer(fila, col);
				Component comp = taula.prepareRenderer(renderer, fila, col);
				maxAmplada = Math.max(maxAmplada, comp.getPreferredSize().width + 10);
			}

			columna.setPreferredWidth(maxAmplada);
		}
	}

	/**
	 * Assigna una amplada preferida a una columna.
	 * L'usuari pot continuar redimensionant-la arrossegant la capçalera.
	 * <p>
	 * Activa {@link JTable#AUTO_RESIZE_LAST_COLUMN}: les columnes amb amplada
	 * explícita la respecten i l'última columna ocupa l'espai restant del panell.
	 * Si vols que totes les columnes tinguin amplades fixes sense que cap s'estiri,
	 * usa {@link #ajustarAmpladesAContingut(JTable)} o {@link #setAmpladaFixa(JTable, int, int)}.
	 *
	 * @param taula Taula sobre la qual s'aplica el canvi.
	 * @param indicColumna Índex de la columna (ordre de la vista).
	 * @param amplada Amplada en píxels.
	 */
	public static void setAmplada(JTable taula, int indicColumna, int amplada) {
		Objects.requireNonNull(taula, "La taula no pot ser null.");
		taula.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		taula.getColumnModel().getColumn(indicColumna).setPreferredWidth(amplada);
	}

	/**
	 * Assigna una amplada fixa a una columna.
	 * L'usuari no pot redimensionar-la.
	 *
	 * @param taula Taula sobre la qual s'aplica el canvi.
	 * @param indicColumna Índex de la columna (ordre de la vista).
	 * @param amplada Amplada en píxels.
	 */
	public static void setAmpladaFixa(JTable taula, int indicColumna, int amplada) {
		Objects.requireNonNull(taula, "La taula no pot ser null.");

		TableColumn columna = taula.getColumnModel().getColumn(indicColumna);
		columna.setMinWidth(amplada);
		columna.setMaxWidth(amplada);
		columna.setWidth(amplada);
		columna.setResizable(false);
	}

	//-------------------------------
	// SELECCIÓ
	//-------------------------------

	/**
	 * Retorna l'objecte corresponent a la fila seleccionada de la taula.
	 * <p>
	 * Té en compte l'ordenació activa: converteix l'índex de la vista
	 * a l'índex del model amb {@link JTable#convertRowIndexToModel(int)}.
	 *
	 * @param <T> Tipus de l'objecte que representa cada fila.
	 * @param taula Taula sobre la qual es consulta la selecció.
	 * @return L'objecte seleccionat, o {@link Optional#empty()} si no hi ha cap fila seleccionada.
	 */
	@SuppressWarnings("unchecked")
	public static <T> Optional<T> getFilaSeleccionada(JTable taula) {
		Objects.requireNonNull(taula, "La taula no pot ser null.");

		int filaVista = taula.getSelectedRow();

		if(filaVista < 0) {
			return Optional.empty();
		}

		int filaModel = taula.convertRowIndexToModel(filaVista);
		ModelTaulaSwing<T> model = (ModelTaulaSwing<T>) taula.getModel();

		return Optional.of(model.getFila(filaModel));
	}

}
