package app;

import controlador.Controlador;
import logica.GameState;
import vista.VistaRompecabeza;

public class AplicacionRompecabezas {

	public static void main(String[] args) {

		VistaRompecabeza vista = new VistaRompecabeza();
		GameState logica = new GameState(3);

		Controlador control = new Controlador(vista,logica);
		vista.mostrarPantalla();		
	}
}
