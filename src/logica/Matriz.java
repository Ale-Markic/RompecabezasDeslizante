package logica;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Matriz {
	
	/**
	 * Esta método recibe el input del usuario, el cual simboliza el tamaño de la matriz que desea 
	 * resolver. Para luego calcular el cuadrado de la entrada para determinar el largo y tamaño de la matriz
	 * @param tamanio
	 */
	public static ArrayList<Integer> crearMatriz(int tamanio) {
		return generarListaDeNElementos(tamanio);
		
	}
	/**
	 * Genera una matriz cuadrada de tamaño "tamanio*tamanio" que contiene los números del 1 hasta 'tamanio-1',
	 * seguidos por un espacio vacio representado por el valor 0.
	 * @param tamanio
	 * @return
	 */
	private static ArrayList<Integer> generarListaDeNElementos(int tamanio){
		int largo = tamanio*tamanio;
		ArrayList<Integer> lista = new ArrayList<Integer>();
		
		for(int i = 1; i < largo; i++) {
			lista.add(i);
		}
		
		lista.add(0);
		
		return lista;
	}
	
	/**
	 * Devuelve un arreglo de enteros donde se encuentra la posicion del lugar vacio en la matriz
	 * se lee de la siguiente manera int [FILA][COLUMNA]
	 * @param matriz
	 * @return
	 */
	public static int [] obtenerLugarVacio(int [][] matriz) {
		int [] lugarVacio = new int[2];
		int largo = matriz.length;
		
		for(int i=0; i < largo; i++) {
			for(int j = 0; j < largo; j++) {
				if(esIgualACero(matriz[i][j])) {
					lugarVacio = asignarPosicion(lugarVacio, i, j);
				}
			}
		}
		return lugarVacio;
	}
	
	/**
	 * Metodo encargado de hacer el movimiento de las piezas.
	 * Recibe la matriz del juego actual y las posiciones FILA y COLUMNA de la pieza que el usuario quiere mover al espacio vacio
	 * @param matriz
	 * @param fila
	 * @param columna
	 */
	public static int [][] mover(int[][] matriz, int fila, int columna) {
		int [] espacioVacio = obtenerLugarVacio(matriz);
		
		if(esMovimientoValido(matriz,espacioVacio, fila, columna)) {
			matriz = cambioDePosicionDelCero(matriz, espacioVacio, fila, columna);
		}
		
		else {
			throw new IllegalArgumentException("no se puede hacer ese cambio de posicion");			
		}
		
		
		return matriz; 
	}
	/**
	 * Metodo encargado de desordenar la matriz para que le aparezca desordenada al usuario.
	 * Recibe un {@link ArrayList} ordenado y luego lo desordena aleatoriamente para asegurar que el juego siempre sea soluble.
	 * @param matrizLista
	 * @param cantMovimientos
	 * @return {@link ArrayList} desordenado.
	 */
	public static ArrayList<Integer> desordenarLaMatriz(ArrayList<Integer> matrizLista, int cantMovimientos) {
		
		int [][] direcciones = obtenerPosiblesDirecciones();
		int [][] matrizBidimensional = listaAArregloBidimensional(matrizLista);
		
		for(int i = 0; i < cantMovimientos; i++) {
			int [] espacioVacio = Matriz.obtenerLugarVacio(matrizBidimensional);
			matrizBidimensional = moverEspacioVacioAleatoriamente(matrizBidimensional, espacioVacio, direcciones);

		}
		matrizLista = matrizBidimensionalAArrayList(matrizBidimensional);
		return matrizLista;
		
	}
	
	private static int [][] obtenerPosiblesDirecciones(){
		int [][] direcciones = {
				{-1, 0},	// Arriba
				{1, 0},		// Abajo
				{0, -1},	//Izquierda
				{0, 1}		//Derecha
		};
		
		return direcciones;
	}
	
	private static int [][] moverEspacioVacioAleatoriamente(int [][] matriz, int [] espacioVacio, int [][] movimientosPosibles) {
		boolean movimientoValido = false;
		
		while(!movimientoValido) {
			int [] direccion = elegirDireccionAleatoria(movimientosPosibles);
			int nuevaFila = espacioVacio[0] + direccion [0];
			int nuevaColumna = espacioVacio[1] + direccion [1];
			
			if(esMovimientoValido(matriz, espacioVacio, nuevaFila, nuevaColumna)) {
				matriz = mover(matriz, nuevaFila, nuevaColumna);
				movimientoValido = true;
			}
		}
		
		return matriz;
	}
	
	/**
	 * Metodo encargado de convertir un ArrayList a un arreglo bidimensional. 

	 * @param matrizLista
	 * @return un arregloBidimensional de la forma {@link int [][]}
	 */
	public static int [][] listaAArregloBidimensional(ArrayList<Integer> matrizLista){
		int tamano = (int) Math.sqrt(matrizLista.size());
		int [][] nuevaMatriz = new int [tamano][tamano];
		
		for (int i = 0; i < tamano; i++) {
			for (int j = 0; j < tamano; j++) {
				nuevaMatriz[i][j] = matrizLista.get(i * tamano + j);
			}
		}
		return nuevaMatriz;
	}
	
	/**
	 * Metodo encargado de convertir una matrizBidimensional a un ArrayList
	 * @param matrizBidimensional
	 * @return {@link ArrayList} 
	 */
	public static ArrayList<Integer> matrizBidimensionalAArrayList(int [][] matrizBidimensional){
		int tamano = matrizBidimensional.length;
		ArrayList<Integer> nuevaMatriz = new ArrayList<Integer>();
		
		for(int i = 0; i<tamano; i++) {
			for (int j = 0; j < tamano; j++) {
				nuevaMatriz.add(matrizBidimensional[i][j]);
			}
		}
		
		return nuevaMatriz;
	}
	
	/**
	 * Metodo encargado de asignar la posicion vacia al arreglo
	 * @param ar El arreglo que recibe debe tener 2 posiciones 
	 * @param fila
	 * @param columna
	 * @return Un arreglo, el cual esta contenido por [FILA, COLUMNA]
	 */
	private static int[] asignarPosicion(int[] ar, int fila, int columna) {
		ar[0] = fila;
		ar[1] = columna;
		
		return ar;
	}
	
	/**
	 * Metodo encargado de verificar si el usuario gano el juego, se debe pasar un arreglo bidimensional
	 * @param matriz
	 * @return true si la matriz pasada por parametro está ordenada de manera ascendente y false de lo contrario.
	 */
	public static boolean ganoElJuego(int[][] matriz) {
		return matrizOrdenada(matriz);
	}
	
	/**
	 * Verifica si la matriz pasada por parámetro está ordenada en orden ascendente
	 * Este metodo omite el numero 0
	 * @param matriz
	 * @return true si está ordenada en orden ascendente y false de lo contrario
	 */
	private static boolean matrizOrdenada(int[][] matriz) {
		int tamanio = matriz.length;
		int ultimoValor = 0;
		
		for(int i = 0; i<tamanio; i++) {
			for(int j = 0; j<tamanio; j++) {
				int valorActual = matriz[i][j];
	
				if(valorActual != 0) {
					if(valorActual < ultimoValor) {
						return false;
					}
					ultimoValor = valorActual;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Metodo encargado de verificar si el movimiento que quiere realizar el usuario es un movimiento valido.
	 * @param matriz
	 * @param espacioVacio
	 * @param fila
	 * @param columna
	 * @return true si el movimiento es valido, false de lo contrario.
	 */
	private static boolean esMovimientoValido(int[][] matriz, int[] espacioVacio, int fila, int columna) {
		
		if(fueraDeRango(fila,columna, matriz.length)) {
			return false;
		}
		
		if((fila == espacioVacio[0] && Math.abs(columna - espacioVacio[1]) == 1) ||
				(columna == espacioVacio[1] && Math.abs(fila - espacioVacio[0]) == 1)) {
			return true;
		}
		return false;
	}
	
	private static int [][] cambioDePosicionDelCero(int[][] matriz, int[] espacioVacio, int fila, int columna){
		matriz[espacioVacio[0]][espacioVacio[1]] = matriz[fila][columna];// se hace el cambio del espacio vacio
		matriz[fila][columna] = 0; // se pone en 0 el lugar que previamente ocupaba el numero
		
		return matriz;
	}
	
	private static boolean esIgualACero(int posicion) {
		return posicion == 0 ? true : false;
	}
		
	private static int [] elegirDireccionAleatoria(int [][] movimientosPosibles) {
		Random random = new Random();
		
		int [] direccion = movimientosPosibles[random.nextInt(4)];
		
		return direccion;
	}
	
	private static boolean fueraDeRango(int fila, int columna, int tamanio) {
		if(fila > (tamanio-1) || fila < 0) {
			return true;
		}
		if(columna > (tamanio-1) || columna < 0) {
			return true;
		}
		return false;
	}
}
