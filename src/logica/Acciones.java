package logica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * Clase package-private la cual realiza las acciones del juego
 */
class Acciones implements ActionListener {

	private JButton[] buttons;
	private int indice;
	private JuegoRompecabeza juego; // Para tener una referencia a la clase principal del juego


	public Acciones(JButton[] buttons, int indice, JuegoRompecabeza juego) {
		this.buttons = buttons;
		this.indice = indice;
		this.juego = juego;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clickButton = (JButton) e.getSource();
		int indiceClick = -1;

		for (int i = 0; i < buttons.length; i++) {
			if (buttons[i] == clickButton) {
				indiceClick = i;
				break;
			}
		}

		if (indiceClick != -1 && juego.esValidoElMovimiento(indiceClick)) {
			buttons[juego.getIndice()].setText(clickButton.getText());
			clickButton.setText("");
			juego.setIndice(indiceClick);

			juego.contarMovimiento();
			juego.RecordsMovimientosRealizados();
			juego.actualizarLabelMovimientos();

			juego.checkEstadoJuego();

		}

	}
}

