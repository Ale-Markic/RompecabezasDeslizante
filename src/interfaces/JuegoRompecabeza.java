
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;

public class JuegoRompecabeza extends JPanel {  // Cambia 'JButton' a 'JPanel'

    private JButton[] buttons;
    private int indice;
    private int valorCuadricula;

    public JuegoRompecabeza(int valorCuadricula) {
        this.valorCuadricula = valorCuadricula;
        this.setLayout(new GridLayout(valorCuadricula, valorCuadricula));  // Configura el diseño de la cuadrícula
        
        
        iniciarJuego();  // Inicializa el juego
    }

    public void setearTamanioVentana(Dimension tamanioPanel) {
        int ancho = tamanioPanel.width / valorCuadricula;
        int alto = tamanioPanel.height / valorCuadricula;

        for (JButton boton : buttons) {
            boton.setPreferredSize(new Dimension(ancho, alto));
        }

        revalidate();  // Para actualizar el layout después de cambiar el tamaño
        repaint();     // Redibujar el panel
    }
    
    
     private void iniciarJuego() {
        ArrayList<Integer> numeroMatriz = new ArrayList<>();

        int totalCuadricula = valorCuadricula * valorCuadricula;

        for (int i = 1; i < totalCuadricula; i++) {  // Crea el array con los valores para la cuadrícula
            numeroMatriz.add(i);
        }
        numeroMatriz.add(0);  // Añade el espacio vacío

        Collections.shuffle(numeroMatriz);  // Mezcla los números aleatoriamente

        buttons = new JButton[totalCuadricula];  // Ajusta el tamaño del array de botones

        for (int i = 0; i < totalCuadricula; i++) {
            int numero = numeroMatriz.get(i);

            if (numero == 0) {  // Si el número es 0, crea un botón vacío
                buttons[i] = new JButton("");
                indice = i;
            } else {  // En caso contrario, crea un botón con el número correspondiente
                buttons[i] = new JButton(String.valueOf(numero));
            }

            buttons[i].addActionListener(new Acciones());  // Añade el listener de acción
            this.add(buttons[i]);  // Añade el botón al panel
        }
    }

    private class Acciones implements ActionListener {
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

            if (indiceClick != -1 && esValidoElMovimiento(indiceClick)) {
                buttons[indice].setText(clickButton.getText());
                clickButton.setText("");
                indice = indiceClick;
                checkEstadoJuego();
            }
        }
    }

    private boolean esValidoElMovimiento(int clickIndice) {
        // Verifica si el movimiento es válido basándose en la cuadrícula
        int filaActual = indice / valorCuadricula;
        int columnaActual = indice % valorCuadricula;
        int filaClick = clickIndice / valorCuadricula;
        int columnaClick = clickIndice % valorCuadricula;

        return (Math.abs(filaActual - filaClick) == 1 && columnaActual == columnaClick) ||
               (Math.abs(columnaActual - columnaClick) == 1 && filaActual == filaClick);
    }

    private void checkEstadoJuego() {
        boolean esGanador = true;
        for (int i = 0; i < buttons.length - 1; i++) {
            if (!buttons[i].getText().equals(String.valueOf(i + 1))) {
                esGanador = false;
                break;
            }
        }

        if (esGanador) {
            JOptionPane.showMessageDialog(this, "¡Has ganado!");
        }
    }
}
