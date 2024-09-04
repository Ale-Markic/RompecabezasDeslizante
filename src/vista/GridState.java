package vista;

import logica.GameState;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GridState {

	private GameState _gameState;
    private JButton[][] _buttons;  //matriz qe seria a grilla del juego, donde c/botón va a hcer una pieza del rompecabezas.
    private int emptyRow, emptyCol; // Posición del casillero vacío en la grilla (fila y columna)

    public GridState() {   //contructor
        this._gameState = new GameState();
    }

    public void setSelectedLevel(JComboBox comboBox, JPanel panel, JLabel puntosLabel, JLabel recordLabel) {
        this.selectedLevel(comboBox, panel, puntosLabel, recordLabel); //met.publico
    }

 /*   public void setClickOnStart(JButton btnStart, JPanel panel) {
        this.clickOnStart(btnStart, panel);  //met.publico
    }  

    public void setClickOnRestart(JButton btnRestart, JPanel panel) {
        this.clickOnRestart(btnRestart, panel);  //met.publico
    }
	*/
    private void selectedLevel(JComboBox comboBox, JPanel panel, JLabel puntosLabel, JLabel recordLabel) {
        comboBox.addActionListener(new ActionListener() { // recib eventos de acción, se selecciona un elemento en el combo box.
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedLevel = (String) comboBox.getSelectedItem(); //selecciono el nivel
                if (!selectedLevel.equals("Selecciona un nivel:")) {
                    int level = Integer.parseInt(selectedLevel.substring(0, 1));
                    gridGenerator(panel, level, puntosLabel, recordLabel);
        //            _gameState.setNivelYComodin(level);
        //            _gameState.verificarSiExisteRecord();  implementar en GameState
                }
            }
        });
    }
    
    private void gridGenerator(JPanel panel, int level, JLabel puntosLabel, JLabel recordLabel) {
        panel.removeAll(); // Limpiamos el panel antes de agregar la nueva grilla
        panel.setLayout(new GridLayout(level, level));  //configura el layout del panel para que organice los 
                                                        //componentes en una cuadrícula de tamaño level x level.
    } 
    
  
/*
    private void restart(JPanel panel) {
        _gameState.resetMoveCount();
        panel.setVisible(false); // Oculta el panel
    }
    
    
    private void clickOnRestart(JButton btnRestart, JPanel panel) {
        btnRestart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restart(panel);
            }
        });      
    }
	
	*/

	
	
	
}
