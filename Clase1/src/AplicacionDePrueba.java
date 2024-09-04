import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;

public class AplicacionDePrueba {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AplicacionDePrueba window = new AplicacionDePrueba();
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
	public AplicacionDePrueba() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3,3));
	
		frame.setTitle("Matriz 3x3");
		
		
		  // Crear la matriz de datos
        int[][] matriz = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
		
		
		for(int i = 0; i <3; i++) {
			for(int j = 0; j < 3; j++) {
				JLabel label = new JLabel(String.valueOf(matriz[i][j]));
				label.setHorizontalAlignment(JLabel.CENTER);
				panel.add(label);
			}
		}
		
		frame.add(panel);
	}
}
