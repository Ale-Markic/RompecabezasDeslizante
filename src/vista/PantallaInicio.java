package vista;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;

public class PantallaInicio {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaInicio window = new PantallaInicio();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PantallaInicio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(175, 238, 238));
		inicializarFrame();
		
		//crear JPanel con imagen de fondo
		JPanel panelConFondo = new JPanel() {
			@Override
			protected void paintComponent (Graphics g ) {
				super.paintComponent(g);
				ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/resources/rompecabezasDeslizante.png"));
				Image imagen = imagenFondo.getImage();
				
				 
                // Ajustar la imagen al ancho del panel manteniendo las proporciones
                int panelWidth = getWidth();
                int originalWidth = imagen.getWidth(this);
                int originalHeight = imagen.getHeight(this);
                
                // Calcular el nuevo alto proporcional
                int newHeight = (panelWidth * originalHeight) / originalWidth;
                
                // Dibujar la imagen escalada al ancho del panel
                g.drawImage(imagen, 100, 25, panelWidth, newHeight, this);
			}
		};
		
		configurarPanelConFondo(panelConFondo);
		
		JLabel lblNombre = new JLabel ("Ingrese su nombre: ");
		configuracionlblNombre(lblNombre, panelConFondo);

		
		JTextField textoNombre = new JTextField();
		configuracionTextoNombre(textoNombre, panelConFondo);
		
		
		JButton btnComenzar = new JButton("Comenzar");
		configuracionbtnComenzar(btnComenzar, panelConFondo);
		
		JLabel btnSignoDePregunta = new JLabel("?");
		configuracionlblSignoDePregunta(btnSignoDePregunta);

		
		
		// Establecer acción al presionar Enter
        frame.getRootPane().setDefaultButton(btnComenzar);
		
		btnComenzar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreUsuario = textoNombre.getText();
				if(!nombreUsuario.isEmpty()) {
					InterfazVisual pantallaJuego = new InterfazVisual(nombreUsuario);
					pantallaJuego.getFrame().setVisible(true);
					frame.dispose(); //Cierra la pantalla de inicio
				}
				else {
					JOptionPane.showMessageDialog(btnSignoDePregunta, "Por favor ingrese un nombre antes de ingresar");
				}
			}
		});
		
		frame.getContentPane().add(panelConFondo);
	}
	
	
	/**
	 * Inicializa el frame
	 */
	private void inicializarFrame() {
		frame.setBounds(100, 100, 628, 475);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
	}	
	private void configurarPanelConFondo(JPanel panelConFondo) {
		panelConFondo.setBackground(new Color(175, 238, 238));
		panelConFondo.setBounds(0, 0, 400, 400);
		panelConFondo.setLayout(null);
	}
	private void configuracionbtnComenzar(JButton btnComenzar, JPanel panelConFondo) {
		btnComenzar.setBounds(227, 320, 150, 30);
		panelConFondo.add(btnComenzar);
	}
	private void configuracionTextoNombre(JTextField textoNombre, JPanel panelConFondo) {
		textoNombre.setBounds(227,270,150,30);
		//textoNombre.setColumns(10);
		panelConFondo.add(textoNombre);
	}
	private void configuracionlblNombre(JLabel lblNombre, JPanel panelConFondo) {
		lblNombre.setBounds(242, 240, 150, 30);
		panelConFondo.add(lblNombre);
	}
	
	private void configuracionlblSignoDePregunta(JLabel lblSignoDePregunta) {
		
		String extra = "Para obtener mayor informacion presione aqui"; 
		
		lblSignoDePregunta.setFont(new Font("Arial", Font.BOLD, 20)); //Fuente grande
		lblSignoDePregunta.setBounds(550, 400, 30, 30);
		
		lblSignoDePregunta.setToolTipText(extra);
		frame.getContentPane().add(lblSignoDePregunta);		
	}
}
