package aplicaciogui;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

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
