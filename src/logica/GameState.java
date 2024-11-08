package logica;

import java.util.ArrayList;

public class GameState {
	private int[][] matriz;
	private int tamaño;
	private int filaVacia, columnaVacia;
	private int cantidadMovimientos;
	private int puntosRecords;

	public GameState(int tamaño) {
		this.tamaño = tamaño;
		iniciarJuego();
	}

	public void iniciarJuego() {

		ArrayList<Integer> numeros = Matriz.crearMatriz(tamaño);
		numeros = Matriz.desordenarLaMatriz(numeros, 50);
		matriz = Matriz.listaAArregloBidimensional(numeros);
		cantidadMovimientos = 0;
		this.puntosRecords=0;
		encontrarPosicionVacia();

	}

	public void SetearTamaño(int nuevoTamaño) {
		this.tamaño = nuevoTamaño;

		iniciarJuego();
	}

	private void encontrarPosicionVacia() {
		for (int i = 0; i < tamaño; i++) {
			for (int j = 0; j < tamaño; j++) {
				if (matriz[i][j] == 0) {
					filaVacia = i;
					columnaVacia = j;
					return;
				}
			}
		}
	}

	public boolean moverPieza(int fila, int columna) {
		if (esMovimientoValido(fila, columna)) {
			matriz[filaVacia][columnaVacia] = matriz[fila][columna];
			matriz[fila][columna] = 0;
			filaVacia = fila;
			columnaVacia = columna;
			cantidadMovimientos++;
			return true;
		}
		return false;
	}

	private boolean esMovimientoValido(int fila, int columna) {
		return (Math.abs(filaVacia - fila) == 1 && columnaVacia == columna) ||
				(Math.abs(columnaVacia - columna) == 1 && filaVacia == fila);

	}

	public int[][] getMatriz() {
		return matriz;
	}

	public int getCantidadMovimientos() {
		return cantidadMovimientos;
	}

	public boolean estaResuelto() {
		return Matriz.ganoElJuego(matriz);
	}

	public void SetearMatriz(int [][] nuevaMatriz) {

		for(int i = 0;i<nuevaMatriz.length;i++) {
			for(int j =0;j<nuevaMatriz[i].length;j++) {
				matriz[i][j] = nuevaMatriz[i][j];
			}
		}
		encontrarPosicionVacia();
	}

	public int obtenerPosicionXdelCero() {
		return this.filaVacia;
	}
	
	public int obtenerPosicionYdelCero() {
		return this.columnaVacia;
	}

	public int ObtenerNumeroCuadricula() {
		return this.tamaño;
	}

	public String cantidadMovimiento() {
		return String.valueOf(cantidadMovimientos);
	}

	public void RegistrarRecords() {
		if(this.puntosRecords==0) {
			this.puntosRecords=this.cantidadMovimientos;
		}
		if(this.puntosRecords>this.cantidadMovimientos) {
			this.puntosRecords=this.cantidadMovimientos;
		}
	}

	public String MostrarRecord() {
		return String.valueOf(this.puntosRecords);
	}
	
	public void GuardarRecordActual(int ValorRecord) {
		this.puntosRecords=ValorRecord;
	}
}
