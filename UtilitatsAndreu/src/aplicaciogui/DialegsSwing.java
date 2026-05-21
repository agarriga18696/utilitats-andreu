package aplicaciogui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Window;
import java.awt.Dialog;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 * Classe d'utilitat per mostrar diàlegs Swing.
 * 
 * @author Andreu
 * @version 1.0
 */
public final class DialegsSwing {

	private DialegsSwing() {
		/*
		 * Classe d'utilitat no instanciable.
		 */
	}

	//-------------------------------
	// DIÀLEGS SIMPLES
	//-------------------------------

	/**
	 * Mostra un missatge informatiu.
	 * 
	 * @param pare Component pare del diàleg.
	 * @param titol Títol del diàleg.
	 * @param missatge Missatge a mostrar.
	 */
	public static void info(Component pare, String titol, String missatge) {
		JOptionPane.showMessageDialog(
				pare,
				missatge,
				titol,
				JOptionPane.INFORMATION_MESSAGE
				);
	}

	/**
	 * Mostra un missatge d'avís.
	 * 
	 * @param pare Component pare del diàleg.
	 * @param titol Títol del diàleg.
	 * @param missatge Missatge a mostrar.
	 */
	public static void avis(Component pare, String titol, String missatge) {
		JOptionPane.showMessageDialog(
				pare,
				missatge,
				titol,
				JOptionPane.WARNING_MESSAGE
				);
	}

	/**
	 * Mostra un missatge d'error.
	 * 
	 * @param pare Component pare del diàleg.
	 * @param titol Títol del diàleg.
	 * @param missatge Missatge a mostrar.
	 */
	public static void error(Component pare, String titol, String missatge) {
		JOptionPane.showMessageDialog(
				pare,
				missatge,
				titol,
				JOptionPane.ERROR_MESSAGE
				);
	}

	/**
	 * Mostra un diàleg de confirmació.
	 * 
	 * @param pare Component pare del diàleg.
	 * @param titol Títol del diàleg.
	 * @param missatge Missatge de confirmació.
	 * @return {@code true} si l'usuari prem Sí.
	 */
	public static boolean confirmar(Component pare, String titol, String missatge) {
		int resposta = JOptionPane.showConfirmDialog(
				pare,
				missatge,
				titol,
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE
				);

		return resposta == JOptionPane.YES_OPTION;
	}

	//-------------------------------
	// DIÀLEGS AMB TEXT LLARG
	//-------------------------------

	/**
	 * Mostra una finestra modal amb text llarg i scroll.
	 * 
	 * @param pare Component pare del diàleg.
	 * @param titol Títol del diàleg.
	 * @param contingut Text a mostrar.
	 * @param amplada Amplada del diàleg.
	 * @param altura Altura del diàleg.
	 */
	public static void textLlarg(Component pare, String titol, String contingut, int amplada, int altura) {

		Window finestraPare = null;

		if(pare instanceof Window window) {
			finestraPare = window;
		} else if(pare != null) {
			finestraPare = SwingUtilities.getWindowAncestor(pare);
		}

		JDialog dialeg = new JDialog(
				finestraPare,
				titol,
				Dialog.ModalityType.APPLICATION_MODAL
				);

		dialeg.setLayout(new BorderLayout());
		dialeg.setSize(amplada, altura);
		dialeg.setLocationRelativeTo(pare);

		JTextArea txtContingut = new JTextArea(contingut);
		txtContingut.setEditable(false);
		txtContingut.setLineWrap(true);
		txtContingut.setWrapStyleWord(true);
		txtContingut.setCaretPosition(0);

		dialeg.add(new JScrollPane(txtContingut), BorderLayout.CENTER);

		JButton btnTancar = new JButton("Tancar");
		btnTancar.addActionListener(_ -> dialeg.dispose());

		JPanel pnlBotons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pnlBotons.add(btnTancar);

		dialeg.add(pnlBotons, BorderLayout.SOUTH);
		dialeg.setVisible(true);
	}

}
