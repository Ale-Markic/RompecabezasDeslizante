
import javax.swing.JFrame;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
//import Interfaces.GridState;

public class InterfazVisual {

	 private JFrame _frame;  //rectangulo base
	 private JPanel _panel;  //rectagulo dentro
	 private GridState _gridState;  //gestiona el estado y la lógica del juego.
	 private JLabel puntosLabel;  //para tener en cuenta los numeros 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazVisual window = new InterfazVisual();
					window._frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfazVisual() {   //contructor
		this._gridState = new GridState();   //inicializo el objeto
		initialize();    //llama al metodo incializar
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		_frame = new JFrame("RompeCabezas Deslizante");
		_frame.setResizable(false);  //queda el tamaño fijo del rectangulo 
		_frame.setBounds(100, 100, 628, 475);  //damos tamaño y pos al recgulo mayor
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //tenga la opcion de salir con la "x"
		_frame.getContentPane().setLayout(null);  //para cada componente qe creo le yo las medidas y posiciones
		_frame.setResizable(false);
		_frame.setLocationRelativeTo(null); //centramos el rectangulo
		
	     /////////////////////
	     // CREACION DE PANEL PARA GRILLA
	     /////////////////////
		
	     _panel = new JPanel();
	     
	     
	     	     
	     
		 _panel.setBounds(225, 48, 338, 336); //medidas del panel dentro del frame
		
		 
		 _panel.setBackground(new Color(230, 57, 70));
	     
		 
	      /////////////////////
	     // CREACION DE LABELS
	     /////////////////////
	     puntosLabel = new JLabel("Movimientos: ");
	     puntosLabel.setBounds(340, 11, 100, 23); //se establece pos y tamaño
	     JLabel recordLabel = new JLabel("Record: ");
	     recordLabel.setBounds(490, 11, 100, 23);   //se establece pos y tamaño
	        
	     
	     /////////////////////
	     // CREACION DE BOTONES
	     /////////////////////
	     JButton btnStart = new JButton("Start");
	     btnStart.setBounds(27, 81, 89, 23);

	     JButton btnRestart = new JButton("Reset");
	     btnRestart.setBounds(126, 81, 89, 23);
	//     _gridState.setClickOnStart(btnStart, _panel);  /*agregar logica en gridState*/
	 //    _gridState.setClickOnRestart(btnRestart, _panel); //Asocia los botones  a su funcion mediante el evento en gridState
	     
	     
	     /////////////////////
	     // CREACION DE COMBOBOX
	     /////////////////////
	     JComboBox<String> comboBox = new JComboBox<>();  //opciones de como presentar la grilla
	     String[] levels = { "Selecciona un nivel:", "3x3", "4x4", "5x5" };   //opciones a elegir 
	     comboBox.setModel(new DefaultComboBoxModel<>(levels));  //muestra los niv x su forma de defaul
	     comboBox.setBounds(27, 48, 188, 22); //establecemos pos y tamaño
	     _gridState.setSelectedLevel(comboBox, _panel, puntosLabel, recordLabel);     /*/*agregar logica en gridState*/
	    
	     String ValorSeleccionado =(String) comboBox.getSelectedItem();

	     ///
		 ///Chequear el comoBox
		 ///
	     /*
	     int valorFinal =0;
	     if(ValorSeleccionado=="3x3"){valorFinal=3;}else
	     elif(ValorSeleccionado=="4x4"){valorFinal =4;
	    	 
	     }
	     */
	     JuegoRompecabeza juegoPanel = new JuegoRompecabeza(3);
         
		 _panel.add(juegoPanel,BorderLayout.PAGE_START); 
	     juegoPanel.setearTamanioVentana(_panel.getSize());
	     
	     
	     
	     
       /////////////////////
       // CREACION DE REGLAS
      /////////////////////
      JTextArea gameRules = new JTextArea();  //mostrar texto
      gameRules.setFont(new Font("consolas", Font.PLAIN, 16));  //fuente
      gameRules.setWrapStyleWord(true);  //si no entra la palabra sige en e sig. renglon
      gameRules.setLineWrap(true);   //ajuste automatico de las lineas 
      gameRules.setText(
      "El objetivo del juego es ordenar los números en secuencia. " +
      "\n\nUtiliza las flechas del teclado o los botones para mover la casilla vacía."
      );
      gameRules.setBounds(27, 120, 188, 264); //establece tamaño y pos 

       addComponentToFrame(_panel);
       addComponentToFrame(puntosLabel);
       addComponentToFrame(recordLabel);
       addComponentToFrame(comboBox);
       addComponentToFrame(btnStart);
       addComponentToFrame(btnRestart);
       addComponentToFrame(gameRules);
	     
/*       // Configurar un KeyListener para mover las piezas con las teclas
       _frame.addKeyListener(new KeyAdapter() {
           @Override
           public void keyPressed(KeyEvent e) {
               int key = e.getKeyCode();
               switch (key) {
                   case KeyEvent.VK_UP:
                       _gridState.moveUp();
                       break;
                   case KeyEvent.VK_DOWN:
                       _gridState.moveDown();
                       break;
                   case KeyEvent.VK_LEFT:
                       _gridState.moveLeft();
                       break;
                   case KeyEvent.VK_RIGHT:
                       _gridState.moveRight();
                       break;
               }
               _panel.repaint(); // Redibujar la grilla después de mover
           }
       });
       */
}
	
	private void addComponentToFrame(JComponent component) {
        _frame.getContentPane().add(component);
    }
}
