package aplicaciogui;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Classe d'utilitat per crear i configurar finestres Swing.
 * 
 * @author Andreu
 * @version 1.0
 */
public final class FinestresSwing {

	private FinestresSwing() {
		/*
		 * Classe d'utilitat no instanciable.
		 */
	}

	//-------------------------------
	// MÈTODES DE LA CLASSE
	//-------------------------------
	
	/**
	 * Retorna un {@code JFrame} configurat amb els paràmetres indicats.
	 * 
	 * @param titol Títol de la finestra.
	 * @param amplada Amplada de la finestra.
	 * @param altura Altura de la finestra.
	 * @param componentRelatiu Component respecte al qual es centrarà la finestra.
	 * @param operacioTancament Operació de tancament del {@code JFrame}.
	 * @return Finestra configurada.
	 */
	public static JFrame frame(
			String titol, 
			int amplada, 
			int altura, 
			Component componentRelatiu, 
			int operacioTancament) {
		
		if(amplada <= 0 || altura <= 0) {
			throw new IllegalArgumentException("L'amplada i l'altura han de ser positives.");
		}
		
		JFrame frame = new JFrame(titol);
		frame.setSize(amplada, altura);
		frame.setLocationRelativeTo(componentRelatiu);
		frame.setDefaultCloseOperation(operacioTancament);
		
		return frame;
	}
	
	/**
	 * Retorna un {@code JFrame} bàsic centrat a la pantalla.
	 * 
	 * @param titol Títol de la finestra.
	 * @param amplada Amplada de la finestra.
	 * @param altura Altura de la finestra.
	 * @return Finestra configurada.
	 */
	public static JFrame frame(String titol, int amplada, int altura) {
		return frame(titol, amplada, altura, null, WindowConstants.EXIT_ON_CLOSE);
	}

}
