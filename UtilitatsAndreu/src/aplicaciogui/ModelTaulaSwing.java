package aplicaciogui;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.table.AbstractTableModel;

/**
 * Model de taula genèric per a {@link javax.swing.JTable}.
 * <p>
 * Basat en una llista d'objectes del tipus {@code T} i una llista de
 * {@link ColumnaSwing} que defineix com s'extreu el valor de cada columna.
 * Les cel·les no són editables per defecte.
 * <p>
 * Per actualitzar les dades useu {@link #actualitzar(List)}, que notifica
 * automàticament la vista.
 *
 * @param <T> Tipus de l'objecte que representa cada fila de la taula.
 *
 * @author Andreu
 * @version 1.0
 */
public final class ModelTaulaSwing<T> extends AbstractTableModel {

	//-------------------------------
	// ATRIBUTS ESTÀTICS
	//-------------------------------

	private static final long serialVersionUID = 1L;

	//-------------------------------
	// ATRIBUTS
	//-------------------------------

	private List<T> dades;
	private final List<ColumnaSwing<T>> columnes;

	//-------------------------------
	// CONSTRUCTOR
	//-------------------------------

	/**
	 * Crea un model de taula amb les dades i columnes indicades.
	 *
	 * @param dades Llista inicial d'objectes.
	 * @param columnes Definicions de les columnes.
	 */
	public ModelTaulaSwing(List<T> dades, List<ColumnaSwing<T>> columnes) {
		Objects.requireNonNull(dades, "Les dades no poden ser null.");
		Objects.requireNonNull(columnes, "Les columnes no poden ser null.");
		this.dades = new ArrayList<>(dades);
		this.columnes = List.copyOf(columnes);
	}

	//-------------------------------
	// IMPLEMENTACIÓ AbstractTableModel
	//-------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getRowCount() {
		return this.dades.size();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getColumnCount() {
		return this.columnes.size();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getColumnName(int col) {
		return this.columnes.get(col).nom();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getValueAt(int row, int col) {
		return this.columnes.get(col).extractor().apply(this.dades.get(row));
	}

	/**
	 * Les cel·les del model no són editables.
	 *
	 * @return Sempre {@code false}.
	 */
	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}

	/**
	 * Retorna la classe del valor de la primera fila per a la columna indicada.
	 * Permet que {@link javax.swing.JTable} apliqui renderitzadors per defecte:
	 * nombres alineats a la dreta, booleans com a checkbox, etc.
	 *
	 * @return Classe del valor, o {@code Object.class} si la taula és buida o el valor és null.
	 */
	@Override
	public Class<?> getColumnClass(int col) {
		if(this.dades.isEmpty()) {
			return Object.class;
		}
		Object valor = getValueAt(0, col);
		return valor != null ? valor.getClass() : Object.class;
	}

	//-------------------------------
	// MÈTODES DE LA CLASSE
	//-------------------------------

	/**
	 * Substitueix les dades del model i notifica la vista perquè es repinti.
	 *
	 * @param novesDades Nova llista d'objectes.
	 */
	public void actualitzar(List<T> novesDades) {
		Objects.requireNonNull(novesDades, "Les noves dades no poden ser null.");
		this.dades = new ArrayList<>(novesDades);
		fireTableDataChanged();
	}

	/**
	 * Retorna l'objecte de la fila indicada (índex del model, no de la vista).
	 *
	 * @param row Índex de fila del model.
	 * @return Objecte corresponent a la fila.
	 */
	public T getFila(int row) {
		return this.dades.get(row);
	}

	/**
	 * Retorna el nombre de files del model.
	 *
	 * @return Nombre d'objectes a les dades.
	 */
	public int getMida() {
		return this.dades.size();
	}

}
