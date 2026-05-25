package aplicaciogui;

import java.awt.Component;
import java.awt.Dimension;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Classe d'utilitat per crear i configurar finestres Swing.
 * 
 * @author Andreu
 * @version 1.3
 */
public final class FinestresSwing {

	private FinestresSwing() {
		/*
		 * Classe d'utilitat no instanciable.
		 */
	}

	//-------------------------------
	// CLASSE BUILDER INTERNA
	//-------------------------------

	/**
	 * Builder per construir un {@link JFrame} de manera fluïda.
	 * <p>
	 * S'obté a través de {@link FinestresSwing#builder(String)}.
	 * Els valors per defecte són: mida 800×600, centrada a pantalla,
	 * redimensionable i amb operació de tancament {@link WindowConstants#EXIT_ON_CLOSE}.
	 */
	public static final class FrameBuilder {

		private final String titol;
		private int amplada = 800;
		private int altura = 600;
		private Dimension midaMinima = null;
		private Component componentRelatiu = null;
		private int operacioTancament = WindowConstants.EXIT_ON_CLOSE;
		private boolean redimensionable = true;
		private ImageIcon icona = null;

		private FrameBuilder(String titol) {
			this.titol = titol;
		}

		/**
		 * Estableix la mida de la finestra.
		 *
		 * @param amplada Amplada en píxels.
		 * @param altura  Altura en píxels.
		 * @return Aquest builder.
		 */
		public FrameBuilder mida(int amplada, int altura) {
			this.amplada = amplada;
			this.altura = altura;
			return this;
		}

		/**
		 * Estableix la mida mínima de la finestra.
		 *
		 * @param amplada Amplada mínima en píxels.
		 * @param altura  Altura mínima en píxels.
		 * @return Aquest builder.
		 */
		public FrameBuilder midaMinima(int amplada, int altura) {
			this.midaMinima = new Dimension(amplada, altura);
			return this;
		}

		/**
		 * Centra la finestra relativa a un component.
		 * Si {@code component} és {@code null}, es centra a la pantalla.
		 *
		 * @param component Component de referència per centrar la finestra.
		 * @return Aquest builder.
		 */
		public FrameBuilder relativa(Component component) {
			this.componentRelatiu = component;
			return this;
		}

		/**
		 * Centra la finestra automàticament a la pantalla.
		 *
		 * @return Aquest builder.
		 */
		public FrameBuilder centrada() {
			this.componentRelatiu = null;
			return this;
		}

		/**
		 * Fa la finestra no redimensionable.
		 *
		 * @return Aquest builder.
		 */
		public FrameBuilder noRedimensionable() {
			this.redimensionable = false;
			return this;
		}

		/**
		 * Estableix l'operació de tancament de la finestra.
		 *
		 * @param operacio Operació de tancament (p. ex. {@link WindowConstants#DISPOSE_ON_CLOSE}).
		 * @return Aquest builder.
		 */
		public FrameBuilder operacioTancament(int operacio) {
			this.operacioTancament = operacio;
			return this;
		}

		/**
		 * Estableix la icona de la finestra.
		 * <p>
		 * Si {@code icona} és {@code null} o la imatge no s'ha pogut carregar, s'ignora.
		 *
		 * @param icona Icona a aplicar.
		 * @return Aquest builder.
		 */
		public FrameBuilder icona(ImageIcon icona) {
			this.icona = icona;
			return this;
		}

		/**
		 * Construeix i retorna el {@link JFrame} configurat.
		 *
		 * @return Finestra configurada.
		 * @throws IllegalArgumentException si l'amplada o l'altura no són positives.
		 */
		public JFrame build() {

			if(amplada <= 0 || altura <= 0) {
				throw new IllegalArgumentException("L'amplada i l'altura han de ser positives.");
			}

			JFrame frame = new JFrame(titol);
			frame.setSize(amplada, altura);
			frame.setResizable(redimensionable);
			frame.setLocationRelativeTo(componentRelatiu);
			frame.setDefaultCloseOperation(operacioTancament);

			if(midaMinima != null) {
				frame.setMinimumSize(midaMinima);
			}

			if(icona != null && icona.getImage() != null) {
				frame.setIconImage(icona.getImage());
			}

			return frame;
		}
	}

	//-------------------------------
	// CREAR FRAME
	//-------------------------------

	/**
	 * Retorna un nou {@link FrameBuilder} per construir un {@link JFrame} de manera fluïda.
	 *
	 * @param titol Títol de la finestra.
	 * @return Builder per configurar la finestra.
	 */
	public static FrameBuilder builder(String titol) {
		return new FrameBuilder(titol);
	}

	/**
	 * Retorna un {@code JFrame} configurat amb els paràmetres indicats.
	 *
	 * @param titol Títol de la finestra.
	 * @param amplada Amplada de la finestra.
	 * @param altura Altura de la finestra.
	 * @param componentRelatiu Component respecte al qual es centrarà la finestra.
	 * 						   Si és {@code null}, es centra a la pantalla.
	 * @param operacioTancament Operació de tancament del {@code JFrame}.
	 * @param icona Icona de la finestra. Si és {@code null}, s'usa la icona per defecte.
	 * @return Finestra configurada.
	 */
	public static JFrame frame(
			String titol,
			int amplada,
			int altura,
			Component componentRelatiu,
			int operacioTancament,
			ImageIcon icona) {

		if(amplada <= 0 || altura <= 0) {
			throw new IllegalArgumentException("L'amplada i l'altura han de ser positives.");
		}

		JFrame frame = new JFrame(titol);
		frame.setSize(amplada, altura);
		frame.setLocationRelativeTo(componentRelatiu);
		frame.setDefaultCloseOperation(operacioTancament);

		if(icona != null && icona.getImage() != null) {
			frame.setIconImage(icona.getImage());
		}

		return frame;
	}

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

		return frame(titol, amplada, altura, componentRelatiu, operacioTancament, null);
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

	//-------------------------------
	// CONFIGURACIÓ DE LA FINESTRA
	//-------------------------------

	/**
	 * Estableix el botó per defecte de la finestra.
	 * <p>
	 * El botó per defecte és activat automàticament quan l'usuari prem {@code Enter},
	 * sempre que cap altre component no consumeixi la tecla (per exemple, una
	 * {@code JTextArea} o un {@code JComboBox} obert).
	 *
	 * @param finestra Finestra a configurar.
	 * @param boto Botó que actuarà com a botó per defecte.
	 * @throws NullPointerException si {@code finestra} o {@code boto} són {@code null}.
	 */
	public static void botoPerDefecte(JFrame finestra, JButton boto) {
		Objects.requireNonNull(finestra, "finestra no pot ser null");
		Objects.requireNonNull(boto, "boto no pot ser null");
		finestra.getRootPane().setDefaultButton(boto);
	}

}
