package logica;

import java.util.ArrayList;

public class GameState {
	private ArrayList<Integer> _matrizActual;
	
	public GameState(int tamanio) {
		this._matrizActual = Matriz.crearMatriz(tamanio);
	}
	
	public ArrayList<Integer> getMatrizActual(){
		return _matrizActual;
	}
	
}
