package aplicaciogui;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 * Classe d'utilitat per crear i gestionar llistes Swing genèriques.
 * 
 * @author Andreu
 * @version 1.0
 */
public final class LlistesSwing {

	private LlistesSwing() {
		/*
		 * Classe d'utilitat no instanciable.
		 */
	}

	//-------------------------------
	// SELECCIÓ
	//-------------------------------
	
	/**
	 * Retorna l'element seleccionat d'una {@link JList} embolicat en un {@link Optional}.
	 * <p>
	 * Si no hi ha cap element seleccionat, retorna {@link Optional#empty()}.
	 *
	 * @param <T> Tipus dels elements de la llista.
	 * @param llista Llista de la qual s'obté la selecció.
	 * @return {@link Optional} amb l'element seleccionat, o buit si no n'hi ha cap.
	 * @throws NullPointerException si {@code llista} és {@code null}.
	 */
	public static <T> Optional<T> getSeleccionat(JList<T> llista) {
		Objects.requireNonNull(llista, "La llista no pot ser null.");
		return Optional.ofNullable(llista.getSelectedValue());
	}

	/**
	 * Actualitza el contingut d'una {@link JList} amb els elements proporcionats.
	 * <p>
	 * Crea un nou {@link DefaultListModel} amb els elements indicats i l'assigna
	 * a la llista, substituint qualsevol model anterior.
	 *
	 * @param <T> Tipus dels elements de la llista.
	 * @param llista Llista que s'ha d'actualitzar.
	 * @param elements Nous elements que ha de mostrar la llista.
	 * @throws NullPointerException si {@code llista} o {@code elements} són {@code null}.
	 */
	public static <T> void actualitzar(JList<T> llista, List<T> elements) {
		Objects.requireNonNull(llista, "La llista no pot ser null.");
		Objects.requireNonNull(elements, "Els elements no poden ser null.");
		DefaultListModel<T> model = new DefaultListModel<>();
		model.addAll(elements);
		llista.setModel(model);
	}

}
