package vista;

import javax.swing.JFrame;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import logica.JuegoRompecabeza;

import javax.swing.JOptionPane;
//import Interfaces.GridState;

public class InterfazVisual {

	 private JFrame _frame;  //rectangulo base
	 private JPanel _panel;  //rectagulo dentro
	 private JLabel puntosLabel;  //para tener en cuenta los numeros 
	 private String nombreUsuario;
	 private JuegoRompecabeza juegoPanel = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//InterfazVisual window = new InterfazVisual(;
					//window._frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfazVisual(String nombre) {   
		this.nombreUsuario = nombre;
		initialize();    
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		inicializarElFrame();

		
		
	     /////////////////////
	     // CREACION DE PANEL PARA GRILLA
	     /////////////////////
	     _panel = new JPanel();
	     _panel.setBackground(new Color(192, 192, 192));
	     _panel.setBounds(225, 48, 338, 336); //medidas del panel dentro del frame
	     
	     /////////////////////
	     // CREACION DE LABELS
	     /////////////////////
	     puntosLabel = new JLabel("Movimientos: ");
	     puntosLabel.setBounds(340, 11, 100, 23); //se establece pos y tamaño
	     JLabel recordLabel = new JLabel("Record: ");
	     recordLabel.setBounds(490, 11, 100, 23);   //se establece pos y tamaño
	     
	     JLabel nombreLabel = new JLabel("Bienvedio/a " + nombreUsuario + "!");
	     configurarNombreLabel(nombreLabel);
	     
	     /////////////////////
	     // CREACION DE BOTONES
	     /////////////////////
	     
	     JButton btnStart = new JButton("Start");
	     btnStart.setBounds(27, 81, 89, 23);

	     JButton btnRestart = new JButton("Reset");
	     btnRestart.setBounds(126, 81, 89, 23);
	     
	     /////////////////////
	     // CREACION DE COMBOBOX
	     /////////////////////
	     JComboBox<String> comboBox = new JComboBox<>();  //opciones de como presentar la grilla
	     String[] levels = { "Selecciona un nivel:", "3x3", "4x4", "5x5" };   //opciones a elegir 
	     comboBox.setModel(new DefaultComboBoxModel<>(levels));  //muestra los niv x su forma de defaul
	     comboBox.setBounds(27, 48, 188, 22); //establecemos pos y tamaño
	     
	  // Muestra un panel vacío inicialmente (juegoPanel es null)
		    _panel.setLayout(new BorderLayout());
		    _panel.add(new JLabel("Seleccione un nivel para empezar"), BorderLayout.CENTER); // Texto para indicar que no hay grilla aún
		    _panel.revalidate();
		    _panel.repaint();
		    
	     configuracionDelJuego(juegoPanel);
	     
	     // Reiniciar el juego
	     
	     btnRestart.addActionListener(new ActionListener() {
	    	 @Override
	     	 public void actionPerformed(ActionEvent e) {
	    		 if(juegoPanel != null) {
	    			 usuarioPresionoElBotonRestart();
	    		 }
	    	 }
	    	 
	     });
	     
	     //Jugador cambia el tamaño de la matriz
	     btnStart.addActionListener(new ActionListener() {
	    	 @Override
	    	 public void actionPerformed(ActionEvent e) {
	    		 String tamanioSeleccionado = comboBox.getSelectedItem().toString();
	    		 juegoPanel = cambiarEstado(juegoPanel,tamanioSeleccionado, recordLabel);
	    		 
	    		 
	    	 }
	     });
	     
	     creacionDeReglas();
	     agregarComponentesAlFrame(comboBox, recordLabel, btnStart, btnRestart, nombreLabel);
}
	
	
	private JuegoRompecabeza cambiarEstado(JuegoRompecabeza juegoPanel, String tamanioSeleccionado, JLabel recordLabel) {
		// Reemplazar el panel vacío o anterior juegoPanel por el nuevo juegoPanel
        _panel.removeAll();  // Remueve el contenido anterior (texto o juegoPanel anterior)
        
        juegoPanel = new JuegoRompecabeza(JuegoRompecabeza.obtenerTamanioSeleccionado(tamanioSeleccionado));  // Crea el panel con la grilla
        _panel.add(juegoPanel, BorderLayout.CENTER);  // Agrega el nuevo panel con grilla
        
        juegoPanel.setearTamanioVentana(_panel.getSize());  // Ajusta el tamaño de los botones
        juegoPanel.setPuntosLabel(puntosLabel);  // Aquí se asigna el puntosLabel después de inicializar juegoPanel
        juegoPanel.setRecords(recordLabel);
        _panel.revalidate();  // Revalida el panel para actualizar el diseño
        _panel.repaint();     // Redibuja el panel
        
        return juegoPanel;

	}
	
	private void configuracionDelJuego(JuegoRompecabeza juegoPanel) {
	     _panel.setBorder(null);
	     _panel.revalidate();
		 _panel.repaint();
	     
	}
	
	
	private void creacionDeReglas() {

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
	       addComponentToFrame(gameRules);
		
	}
	
	private void agregarComponentesAlFrame(JComboBox<String> comboBox, JLabel recordLabel,JButton btnStart, JButton btnRestart, JLabel nombreLabel ) {
	    addComponentToFrame(recordLabel);
	    addComponentToFrame(comboBox);
	    addComponentToFrame(btnStart);
	    addComponentToFrame(btnRestart);
	    addComponentToFrame(nombreLabel);
		
	}
	
	private void inicializarElFrame() {
		_frame = new JFrame("RompeCabezas Deslizante");
		_frame.setResizable(false);  //queda el tamaño fijo del rectangulo 
		_frame.setBounds(100, 100, 628, 475);  //damos tamaño y pos al recgulo mayor
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //tenga la opcion de salir con la "x"
		_frame.getContentPane().setLayout(null);  //para cada componente qe creo le yo las medidas y posiciones
		_frame.setResizable(false);
		_frame.setLocationRelativeTo(null); //centramos el rectangulo
	}
	
	private void addComponentToFrame(JComponent component) {
        _frame.getContentPane().add(component);
    }
	
	private void usuarioPresionoElBotonRestart() {
		juegoPanel.reiniciarJuego();
		juegoPanel.setearTamanioVentana(_panel.getSize());
	}
	
	private void configurarNombreLabel(JLabel nombreLabel) {
		nombreLabel.setBounds(50, 0, 150, 50);
	}
	
	  /**
     * Método para obtener el frame de la clase Juego.
     */
    public JFrame getFrame() {
        return _frame;
    }
}
