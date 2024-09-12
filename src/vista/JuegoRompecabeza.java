package vista;

import java.awt.Dimension;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import logica.Matriz;

public class JuegoRompecabeza extends JPanel {
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
    	ArrayList<Integer> numeroMatriz = Matriz.crearMatriz(valorCuadricula);
    	numeroMatriz = Matriz.desordenarLaMatriz(numeroMatriz, 30);
    	 
    	int totalCuadricula = calcularValorDeCuadricula(valorCuadricula);
    	buttons = new JButton[totalCuadricula];  // Ajusta el tamaño del array de botones
    	
    	
    	creacionVisualDeLaMatriz(totalCuadricula, numeroMatriz);

    }
    
     /**
      * Calcula el valor de la cuadricula
      * @param valorCuadricula
      * @return 
      */
    private int calcularValorDeCuadricula(int valorCuadricula) {
    	return valorCuadricula * valorCuadricula;
    }
     
    
    /**
     * Metodo encargado de asignar las posiciones de la matriz a los botones 
     * @param totalCuadricula
     * @param numeroMatriz
     */
    private void creacionVisualDeLaMatriz(int totalCuadricula, ArrayList<Integer> numeroMatriz){
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
    
    public void resetearMatriz() {
    	//int [][] matrizBidimensional = convertirBotonesAMatriz(getBotones());
    	//ArrayList<Integer> arregloMatriz = Matriz.matrizBidimensionalAArrayList(matrizBidimensional);
    	
    	
    }

    
    
    private JButton[] getBotones() {
		return buttons;
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
        //boolean esGanador = true;
        int [][] matriz = convertirBotonesAMatriz(buttons);
        if(Matriz.ganoElJuego(matriz)) {
        	JOptionPane.showMessageDialog(this, "¡Has ganado!");
        }
        
    }
    
    private int [][] convertirBotonesAMatriz(JButton[] buttons) {
    	ArrayList <Integer> arreglo = new ArrayList<Integer>();
    	
    	for (int i = 0; i<buttons.length ; i++) {
    		if(buttons[i].getText().equals("")) {
    			arreglo.add(0);
    		}
    		else {
    			
        		int numero = Integer.parseInt(buttons[i].getText());
        		arreglo.add(numero);
    		}
    	}
    	int [][] matriz = Matriz.listaAArregloBidimensional(arreglo);
    	
    	return matriz;
    }
	

}
