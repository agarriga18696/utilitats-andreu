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
	
	public static <T> Optional<T> getSeleccionat(JList<T> llista) {
		Objects.requireNonNull(llista, "La llista no pot ser null.");
		return Optional.ofNullable(llista.getSelectedValue());
	}
	
	public static <T> void actualitzar(JList<T> llista, List<T> elements) {
		Objects.requireNonNull(llista, "La llista no pot ser null.");
		Objects.requireNonNull(elements, "Els elements no poden ser null.");
		DefaultListModel<T> model = new DefaultListModel<>();
		model.addAll(elements);
		llista.setModel(model);
	}

}
