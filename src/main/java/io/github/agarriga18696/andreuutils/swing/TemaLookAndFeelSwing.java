package io.github.agarriga18696.andreuutils.swing;

/**
 * Representa un Look and Feel disponible per a una aplicació Swing.
 * 
 * @param nom Nom visible del tema.
 * @param classe Nom complet de la classe del Look and Feel.
 * @param icona Nom del fitxer de la icona associada.
 * @param extern Indica si el tema pertany a una llibreria externa.
 * 
 * @author Andreu
 * @version 1.0
 */
public record TemaLookAndFeelSwing(
		String nom,
		String classe,
		String icona,
		boolean extern
		) {
}