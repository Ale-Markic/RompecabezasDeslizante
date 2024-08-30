package logica;

import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class MatrizTest {
	
	/*
	@Test
	public void generacionDeListaEsCorrecta() {
		ArrayList <Integer> lista = new ArrayList<Integer>();
		lista.add(1);
		lista.add(2);
		lista.add(3);
		lista.add(4);
		lista.add(5);
		lista.add(6);
		lista.add(7);
		lista.add(8);
		lista.add(9);
		lista.add(10);
		lista.add(11);
		lista.add(12);
		lista.add(13);
		lista.add(14);
		lista.add(15);
		lista.add(null);
		assertEquals(lista, Matriz.crearMatriz(4));
	}
	
	@Test
	public void generacionDeListaEsIncorrecta() {
		ArrayList <Integer> lista = new ArrayList<Integer>();
		lista.add(1);
		lista.add(2);
		lista.add(3);
		lista.add(4);
		lista.add(5);
		lista.add(6);
		lista.add(7);
		lista.add(8);
		lista.add(9);
		lista.add(10);
		lista.add(11);
		lista.add(12);
		lista.add(13);
		lista.add(14);
		lista.add(15);
		lista.add(null);
		assertNotEquals(lista, Matriz.crearMatriz(5));
	}
	*/
	
	
	@Test
	public void armadoDeMatrizEsCorrecto() {
		int [][] matriz = {
				{1,2,3},
				{4,5,6},
				{7,8, 0}
		};
		assertArrayEquals(matriz, Matriz.crearMatriz(3));
	}
	
	@Test
	public void matrizEstaOrdenada() {
		int [][] matriz = {
				{1, 2, 3},
				{4, 5, 6},
				{7, 8, 0}
		};
		
		assertTrue(Matriz.matrizOrdenada(matriz));
	}
	
	@Test
	public void elJugadorGanoDevuelveTrue() {
		int [][] matriz = {
				{1, 2, 3},
				{4, 5, 6},
				{7, 8, 0}
		};
		
		assertTrue(Matriz.ganoElJuego(matriz));
	}
	
	@Test
	public void elJugadorGanoDevuelveFalse() {
		//Matriz sin ordenar
		int [][] matriz = {
				{1, 2, 3},
				{4, 7, 6},
				{5, 8, 0}
		};
		
		assertFalse(Matriz.ganoElJuego(matriz));
	}
	
	@Test
	public void obtenerLugarVacioDevuelveTrue() {
		int [][] matriz = {
				{0, 1, 3},
				{4, 8, 6},
				{7, 5, 2}
		};
		int[] esperado = {0,0};
		assertArrayEquals (esperado, Matriz.obtenerLugarVacio(matriz));
	}
	

}
