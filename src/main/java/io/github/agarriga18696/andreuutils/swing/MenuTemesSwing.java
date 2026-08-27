package io.github.agarriga18696.andreuutils.swing;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;

/**
 * Classe d'utilitat per crear menús de selecció de Look and Feel.
 * 
 * @author Andreu
 * @version 1.1
 */
public final class MenuTemesSwing {

	private MenuTemesSwing() {
		/*
		 * Classe d'utilitat no instanciable.
		 */
	}

	//-------------------------------
	// MENÚ DE TEMES
	//-------------------------------

	/**
	 * Crea un menú de temes amb els Look and Feel predefinits de la llibreria.
	 * 
	 * @param componentActualitzar Component principal que s'actualitzarà visualment.
	 * @return Menú de temes creat.
	 */
	@SuppressWarnings("unused")
	public static JMenu crearMenuTemes(Component componentActualitzar) {
		return crearMenuTemes(componentActualitzar, nomTema -> {});
	}

	/**
	 * Crea un menú de temes amb els Look and Feel predefinits de la llibreria.
	 * 
	 * @param componentActualitzar Component principal que s'actualitzarà visualment.
	 * @param accioTemaAplicat Acció executada quan un tema s'aplica correctament.
	 * @return Menú de temes creat.
	 */
	public static JMenu crearMenuTemes(
			Component componentActualitzar,
			Consumer<String> accioTemaAplicat
			) {

		Objects.requireNonNull(accioTemaAplicat, "L'acció no pot ser null.");

		return crearMenuTemes(
				componentActualitzar,
				LookAndFeelSwing.getTemesPredefinits(),
				accioTemaAplicat
				);
	}

	/**
	 * Crea un menú de temes amb una llista personalitzada de Look and Feel.
	 * 
	 * @param componentActualitzar Component principal que s'actualitzarà visualment.
	 * @param temes Llista de temes a mostrar.
	 * @param accioTemaAplicat Acció executada quan un tema s'aplica correctament.
	 * @return Menú de temes creat.
	 */
	public static JMenu crearMenuTemes(
			Component componentActualitzar,
			List<TemaLookAndFeelSwing> temes,
			Consumer<String> accioTemaAplicat
			) {

		Objects.requireNonNull(temes, "La llista de temes no pot ser null.");
		Objects.requireNonNull(accioTemaAplicat, "L'acció no pot ser null.");

		JMenu mnuTemes = MenusSwing.menu(
				"Temes",
				IconesSwing.carregar(IconesSwing.TEMES),
				KeyEvent.VK_T
				);

		ButtonGroup grupTemes = new ButtonGroup();

		String classeActual = LookAndFeelSwing.getClasseActual();

		for(TemaLookAndFeelSwing tema : temes) {

			boolean seleccionat = tema.classe().equals(classeActual);
			boolean compatible = LookAndFeelSwing.esCompatible(tema.classe());

			JRadioButtonMenuItem item = MenusSwing.radioItem(
					tema.nom(),
					seleccionat,
					() -> aplicarTema(componentActualitzar, tema, accioTemaAplicat)
					);

			item.setIcon(IconesSwing.carregar(tema.icona()));
			item.setEnabled(compatible);

			grupTemes.add(item);
			mnuTemes.add(item);
		}

		return mnuTemes;
	}

	//-------------------------------
	// MÈTODES PRIVATS
	//-------------------------------

	private static void aplicarTema(
			Component componentActualitzar,
			TemaLookAndFeelSwing tema,
			Consumer<String> accioTemaAplicat
			) {

		if(!LookAndFeelSwing.esCompatible(tema.classe())) {
			return;
		}

		if(LookAndFeelSwing.aplicar(tema.classe())) {
			LookAndFeelSwing.actualitzar(componentActualitzar);
			accioTemaAplicat.accept(tema.nom());
		}
	}

}