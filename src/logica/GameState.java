package logica;

public class GameState {
	private int [][] _matrizActual;
	
	public GameState(int tamanio) {
		this._matrizActual = Matriz.crearMatriz(tamanio);
	}
	
	public int [][] getMatrizActual(){
		return _matrizActual;
	}
	
	public void moverPieza(int fila, int columna) {
		Matriz.mover(_matrizActual, fila, columna);
	}

}
