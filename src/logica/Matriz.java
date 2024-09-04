package logica;

import java.util.ArrayList;
import java.util.List;

public class Matriz {
	
	/**
	 * Esta método recibe el input del usuario, el cual simboliza el tamaño de la matriz que desea 
	 * resolver. Para luego calcular el cuadrado de la entrada para determinar el largo y tamaño de la matriz
	 * @param tamanio
	 */
	public static int [][] crearMatriz(int tamanio) {
		return generarListaDeNElementos(tamanio);
		
	}
	/**
	 * Genera una matriz cuadrada de tamaño "tamanio*tamanio" que contiene los números del 1 hasta 'tamanio-1',
	 * seguidos por un espacio vacio representado por el valor 0.
	 * @param tamanio
	 * @return
	 */
	private static int [][] generarListaDeNElementos(int tamanio){
		int largo = tamanio*tamanio;
		List<Integer> lista = new ArrayList<Integer>();
		
		for(int i = 1; i < largo; i++) {
			lista.add(i);
		}
		
		lista.add(0);
		
		return generacionDeMatriz(tamanio, lista);
	}
	
	private static int [][] generacionDeMatriz(int tamanio, List <Integer> lista){
		int [][] matriz = new int [tamanio][tamanio];
		int index = 0;
		
		for(int i = 0; i<tamanio; i++) {
			for(int j = 0; j<tamanio; j++) {
				matriz[i][j] = lista.get(index++);
			}
		}
		
		return matriz;
	}
	
	/**
	 * Verifica si la matriz pasada por parámetro está ordenada en orden ascendente
	 * Este metodo omite el numero 0
	 * @param matriz
	 * @return true si está ordenada en orden ascendente y false de lo contrario
	 */
	public static boolean matrizOrdenada(int[] [] matriz) {
		int tamanio = matriz.length;
		int ultimoValor = 0;
		
		for(int i = 0; i<tamanio; i++) {
			for(int j = 0; j<tamanio; j++) {
				int valorActual = matriz[i][j];
				
				if(valorActual != 0) {
					if(valorActual < ultimoValor) {
						return false;
					}
				}
				ultimoValor = valorActual;
			}
		}
		
		return true;
	}
	
	
	public static boolean ganoElJuego(int[][] matriz) {
		return matrizOrdenada(matriz);
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
	
	
	private static boolean esIgualACero(int posicion) {
		return posicion == 0 ? true : false;
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
	 * Metodo encargado de hacer el movimiento de las piezas.
	 * Recibe la matriz del juego actual y las posiciones FILA y COLUMNA de la pieza que el usuario quiere mover al espacio vacio
	 * @param matriz
	 * @param fila
	 * @param columna
	 */
	public static void  mover(int[][] matriz, int fila, int columna) {
		int [] espacioVacio = obtenerLugarVacio(matriz);
		
		if(esMovimientoValido(matriz,espacioVacio, fila, columna)) {
			matriz = cambioDePosicion(matriz, espacioVacio, fila, columna);
		}
		else {
			System.out.println("Paso por el error, deberia arrojar exception");
			throw new IllegalArgumentException("no se puede hacer ese cambio de posicion");
			
		}
		
		//return matriz; //ANALIZAR BIEN QUE DEVUELVE ESTE METODO, SI UNN BOOLEANO O LA MATRIZ ACTUALIZADA O SI ES VOID
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
		if((fila == espacioVacio[0] && Math.abs(columna - espacioVacio[1]) == 1) ||
				(columna == espacioVacio[1] && Math.abs(fila - espacioVacio[0]) == 1)) {
			return true;
		}
		return false;
	}
	
	private static int [][] cambioDePosicion(int[][] matriz, int[] espacioVacio, int fila, int columna){
		matriz[espacioVacio[0]][espacioVacio[1]] = matriz[fila][columna];// se hace el cambio del espacio vacio
		matriz[fila][columna] = 0; // se pone en 0 el lugar que previamente ocupaba el numero
		
		return matriz;
	}
	
	public static void mostrarArreglo(int ar[]) { //muestra un arreglo
		System.out.print("[ ");
		for (int i = 0; i < ar.length -1; i++ ) {
			System.out.print(ar[i] + " , ");
		}
		System.out.println(ar [ar.length - 1] + " ]");
	}
	
	public static void mostrarMatriz(int mat[][]) { 
		
		for(int i = 0; i < mat.length; i++) {
			mostrarArreglo(mat[i]);
		}
	}

}
