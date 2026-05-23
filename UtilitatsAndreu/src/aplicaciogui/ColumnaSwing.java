package aplicaciogui;

import java.util.Objects;
import java.util.function.Function;

/**
 * Definició d'una columna per a {@link ModelTaulaSwing}.
 * <p>
 * Agrupa el nom de capçalera i la funció que extreu el valor de la columna
 * a partir d'un objecte del tipus {@code T}.
 *
 * @param <T> Tipus de l'objecte que representa cada fila de la taula.
 * @param nom Nom de la capçalera de la columna.
 * @param extractor Funció que extreu el valor de la columna de l'objecte fila.
 *
 * @author Andreu
 * @version 1.0
 */
public record ColumnaSwing<T>(String nom, Function<T, Object> extractor) {

	/**
	 * Constructor compacte amb validació.
	 */
	public ColumnaSwing {
		Objects.requireNonNull(nom, "El nom de la columna no pot ser null.");
		if(nom.isBlank()) {
			throw new IllegalArgumentException("El nom de la columna no pot ser buit.");
		}
		Objects.requireNonNull(extractor, "L'extractor no pot ser null.");
	}

}
