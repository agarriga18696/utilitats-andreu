package io.github.agarriga18696.andreuutils.swing;

import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

/**
 * Classe d'utilitat per carregar la icona de l'aplicació.
 * <p>
 * Les icones estan empaquetades dins la llibreria a diverses mides.
 * Es recomana usar {@link #carregarTotes()} per obtenir totes les mides
 * i passar-les a {@code JFrame.setIconImages()}, de manera que el sistema
 * operatiu trii automàticament la resolució més adequada.
 *
 * @author Andreu
 * @version 1.0
 */
public final class IconesApp {

	private IconesApp() {
		/*
		 * Classe d'utilitat no instanciable.
		 */
	}

	//-------------------------------
	// CONSTANTS
	//-------------------------------

	private static final String RUTA = "/io/github/agarriga18696/andreuutils/icons/app/andreu-";
	private static final int[] MIDES = { 16, 32, 64, 128, 256, 512 };

	//-------------------------------
	// MÈTODES DE LA CLASSE
	//-------------------------------

	/**
	 * Carrega la icona de l'aplicació a la mida indicada.
	 *
	 * @param mida Mida en píxels (p. ex. {@code 64} per a 64×64).
	 * @return {@link ImageIcon} carregada, o {@code null} si no es troba.
	 */
	public static ImageIcon carregar(int mida) {

		String ruta = RUTA + mida + "x" + mida + ".png";
		URL url = IconesApp.class.getResource(ruta);

		if(url == null) {
			return null;
		}

		return new ImageIcon(url);
	}

	/**
	 * Carrega totes les mides disponibles de la icona de l'aplicació.
	 * <p>
	 * La llista resultant es pot passar directament a
	 * {@code JFrame.setIconImages()} perquè el sistema operatiu
	 * triï la mida més adequada per a cada context.
	 *
	 * @return Llista d'imatges de la icona a totes les mides disponibles.
	 */
	public static List<Image> carregarTotes() {

		List<Image> imatges = new ArrayList<>();

		for(int mida : MIDES) {
			ImageIcon icona = carregar(mida);
			if(icona != null) {
				imatges.add(icona.getImage());
			}
		}

		return imatges;		
	}

}
