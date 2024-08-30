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
