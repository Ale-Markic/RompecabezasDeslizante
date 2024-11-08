package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;

import org.junit.validator.PublicClassValidator;


public class VistaRompecabeza {

	private JFrame frame;

	//Inicio del juego
	private JComboBox comboBox;
	private String seleccion;

	private JLabel txtMovimiento;
	private JLabel textRecord;
	// Panel del juego

	public JPanel PanelJuego;

	//Control movimientos
	private JButton btnArriba;
	private JButton btnAbajo;
	private JButton btnIzquierda;
	private JButton btnDerecha;

	//Control juego 
	private JButton btnReset;
	private JButton btnComenzar;

	public JButton [][] matrizJuego;
	
	/**
	 * Create the application.
	 * @return 
	 */
	public VistaRompecabeza() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setEnabled(false);
		frame.setBounds(100, 100, 1008, 616);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		frame.setBackground(new Color(1.0f,1.0f,1.0f,0.5f));

		PanelesModificados panel = new PanelesModificados(10, 10, 0, 0);
		panel.setBackground(new Color(96, 23, 188));
		panel.setBounds(0, 0, 1008, 39);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel NombreJuego = new JLabel("Juego RompeCabeza");
		NombreJuego.setFont(new Font("Arial", Font.BOLD, 17));
		NombreJuego.setForeground(Color.WHITE);
		NombreJuego.setBounds(10, 0, 237, 39);
		panel.add(NombreJuego);

		BotonesModificados BotonCerrar = new BotonesModificados(10,10,10,10);
		BotonCerrar.setBackground(new Color(249, 98, 93));

		BotonCerrar.setBounds(959, 10, 25, 23);		

		BotonCerrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evento) {
				System.exit(0);
			}

		});

		panel.add(BotonCerrar);

		BotonesModificados btnMinimizar = new BotonesModificados(10,10,10,10);
		btnMinimizar.setBackground(new Color(32, 205, 58));
		btnMinimizar.setBounds(924, 11, 25, 22);

		btnMinimizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evento) {
				frame.setState(frame.ICONIFIED);
			}

		});

		panel.add(btnMinimizar);

		PanelJuego = new JPanel();
		PanelJuego.setBackground(new Color(166, 166, 166));
		PanelJuego.setBounds(341, 98, 643, 470);

		frame.getContentPane().add(PanelJuego);

		txtMovimiento = new JLabel("Movimientos:  ");
		txtMovimiento.setBounds(554, 62, 103, 29);
		frame.getContentPane().add(txtMovimiento);

		JTextArea txtrEsteTexto = new JTextArea();
		txtrEsteTexto.setFont(new Font("Arial", Font.PLAIN, 17));
		txtrEsteTexto.setText("El objetivo de este juego es ordenar\r\nlos números en secuencia.\r\n\r\nUtilizar las flechas del teclado, los\r\nbotones (<--;-->) o el mause para\r\nmover las casilla vacía.");
		txtrEsteTexto.setBounds(22, 239, 291, 176);
		frame.getContentPane().add(txtrEsteTexto);


		String [] lista = {"Seleccione un nivel", "3x3", "4x4", "5x5"};
		comboBox = new JComboBox(lista);

		comboBox.setBounds(36, 115, 240, 39);

		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evento) {

				seleccion =comboBox.getSelectedItem().toString();
			}

		});

		frame.getContentPane().add(comboBox);

		btnComenzar = new JButton("Start");
		btnComenzar.setVerticalAlignment(SwingConstants.TOP);

		btnComenzar.setBounds(36, 173, 89, 23);
		frame.getContentPane().add(btnComenzar);

		btnReset = new JButton("Reset");
		btnReset.setBounds(143, 173, 89, 23);
		frame.getContentPane().add(btnReset);

		ImageIcon btn2 = new ImageIcon(VistaRompecabeza.class.getResource("/archivosImg/Arriba.png"));	
		ImageIcon btnIzq = new ImageIcon(VistaRompecabeza.class.getResource("/archivosImg/Izquierda.png"));
		ImageIcon btnDer = new ImageIcon(VistaRompecabeza.class.getResource("/archivosImg/Derecha.png"));
		ImageIcon btnAbj = new ImageIcon(VistaRompecabeza.class.getResource("/archivosImg/Abajo.png"));

		JPanel panelFondo = new JPanel();
		panelFondo.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelFondo.setBackground(Color.WHITE);
		panelFondo.setBounds(0, 38, 1008, 578);
		frame.getContentPane().add(panelFondo);
		panelFondo.setLayout(null);


		btnArriba = new JButton("");	
		btnArriba.setBounds(116, 384, 61, 47);
		panelFondo.add(btnArriba);
		btnArriba.setIcon(new ImageIcon(btn2.getImage().getScaledInstance(btnArriba.getWidth(), btnArriba.getHeight(), Image.SCALE_SMOOTH)));


		btnDerecha = new JButton("");
		btnDerecha.setBounds(175, 432, 61, 47);
		btnDerecha.setIcon(new ImageIcon(btnDer.getImage().getScaledInstance(btnDerecha.getWidth(), btnDerecha.getHeight(), Image.SCALE_SMOOTH)));
		panelFondo.add(btnDerecha);

		btnIzquierda = new JButton("");
		btnIzquierda.setBounds(55, 432, 61, 47);
		panelFondo.add(btnIzquierda);
		btnIzquierda.setIcon(new ImageIcon(btnIzq.getImage().getScaledInstance(btnIzquierda.getWidth(), btnIzquierda.getHeight(), Image.SCALE_SMOOTH)));

		btnAbajo = new JButton("");
		btnAbajo.setBounds(116, 477, 61, 47);
		btnAbajo.setIcon(new ImageIcon(btnAbj.getImage().getScaledInstance(btnAbajo.getWidth(), btnAbajo.getHeight(), Image.SCALE_SMOOTH)));
		panelFondo.add(btnAbajo);

		textRecord = new JLabel("Record:  ");
		textRecord.setBounds(694, 27, 61, 24);
		panelFondo.add(textRecord);
	}
	
	public void botonArriba(ActionListener accion) {
		btnArriba.addActionListener(accion);
	}
	
	public void botonAbajo(ActionListener accion) {
		btnAbajo.addActionListener(accion);
	}
	
	public void botonIzquierdo(ActionListener accion) {
		btnIzquierda.addActionListener(accion);
	}
	
	public void botonDerecha(ActionListener accion) {
		btnDerecha.addActionListener(accion);
	}
	
	public void mostrarPantalla() {
		frame.setVisible(true);
	}
	
	public String ObtenerSeleccionCombo() {
		return seleccion;
	}
	
	public void botonStart(ActionListener accion) {
		btnComenzar.addActionListener(accion);
	}
	
	public void botonReset(ActionListener accion) {
		btnReset.addActionListener(accion);
	}
	
	public void limpiarCuadricula() {

		PanelJuego.removeAll();
	}
	
	public void CuadriculaPanelConfigurar(int valor) {
		PanelJuego.setLayout(new GridLayout(valor,valor));
		matrizJuego = new JButton[valor][valor]; 

	}
	
	public void ActualizarPanel() {
		PanelJuego.revalidate();
		PanelJuego.repaint();
	}
	
	public int ValorCuadricula() {
		return matrizJuego.length;
	}
	
	public void CrearCuadricula(int [][] cuadricula) {
		int valorCuadricula = cuadricula.length;
		CuadriculaPanelConfigurar(valorCuadricula);
		
		for ( int i =0;i<cuadricula.length;i++) {
			for(int j = 0;j<cuadricula[i].length;j++) {
				if(cuadricula[i][j]==0) {

					matrizJuego[i][j] = new JButton("");
					matrizJuego[i][j].setBackground(new Color(25, 55, 97));
					matrizJuego[i][j].setForeground(new Color(254, 220, 253).brighter().brighter());
					matrizJuego[i][j].setFont(new Font("Arial",Font.BOLD,20));
					matrizJuego[i][j].requestFocusInWindow();

				}
				else {
					matrizJuego[i][j]= new JButton(String.valueOf(cuadricula[i][j]));
					matrizJuego[i][j].setBackground(new Color(25, 55, 97));
					matrizJuego[i][j].setForeground(new Color(254, 220, 253).brighter().brighter());
					matrizJuego[i][j].setFont(new Font("Arial",Font.BOLD,20));

				}
				PanelJuego.add(matrizJuego[i][j]);
			}
		}
		
		ActualizarPanel();
	}	
	
	public void nuevaMatriz(int [][] nuevaMatriz) {

		for(int i =0;i<nuevaMatriz.length;i++) {
			for(int j=0;j<nuevaMatriz[i].length;j++) {
				if(nuevaMatriz[i][j]==0) {
					matrizJuego[i][j].setText("");

				}else {

					matrizJuego[i][j].setText(String.valueOf(nuevaMatriz[i][j]));
				}}

		}
	}	

	public void Felicitaciones() {
		JOptionPane.showMessageDialog(null, "Felicitaciones");
	}	

	public void ActualizarCantidadMovimiento(String nuevoMovimiento) {
		this.txtMovimiento.setText(nuevoMovimiento);
	}

	public void ActualizarRecord(String nuevoRecod) {
		this.textRecord.setText(nuevoRecod);
	}

	public int MostrarRecordActual() {
		return Integer.parseInt(this.textRecord.getText());
	}

	public JButton [][] largoCuadricula() {
		return this.matrizJuego;
	}

	public void BotonesJuegos(KeyListener acciones) {
		if(this.matrizJuego == null) {
		}	
		else {	
			for (int i = 0;i<matrizJuego.length;i++) {
				for(int j =0;j<matrizJuego[i].length;j++) {
					matrizJuego[i][j].addKeyListener(acciones);
					matrizJuego[i][j].setFocusable(true);
				}
			}
		}
	}
}
