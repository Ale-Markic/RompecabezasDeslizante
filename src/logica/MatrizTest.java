package logica;

import static org.junit.Assert.*;
import org.junit.Test;

public class MatrizTest {
	
	@Test
	public void matrizEstaNoEstaOrdenadaDevuelveFalse() {
		int [][] matriz = {
				{1, 2, 6},
				{7, 0, 3},
				{4, 5, 8}
		};
		
		assertFalse(Matriz.ganoElJuego(matriz));
	}
	
	
	
	@Test
	public void elJugadorGanoDevuelveTrue() {
		int [][] matriz = {
				{1, 2, 3},
				{4, 5, 6},
				{7, 8, 0}
		};
		
		assertTrue(Matriz.ganoElJuego(matriz));
		System.out.println();
		System.out.println();
		
	}
	
	@Test
	public void elJugadorGanoDevuelveFalse() {
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
		assertArrayEquals(esperado, Matriz.obtenerLugarVacio(matriz));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void movimientoDevuelveError() { 
		int [][] matriz = {
				{1, 2, 3},
				{4, 5, 6},
				{7, 8, 0}
		};
		Matriz.mover(matriz, 2,2);
	}
}
