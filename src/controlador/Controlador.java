package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import logica.GameState;
import vista.VistaRompecabeza;

public class Controlador {

	private VistaRompecabeza vista;
	private GameState logica;


	public Controlador(VistaRompecabeza vista, GameState logica) {
		this.vista = vista;
		this.logica = logica;

		vista.botonArriba(new AccionBotononArriba());
		vista.botonAbajo(new AccionBotononAbajo());
		vista.botonDerecha(new AccionBotononDerecha());
		vista.botonIzquierdo(new AccionBotononIzquierda());

		vista.botonStart(new AccionComenzar());
		vista.botonReset(new AccionResetear());

		AgregarFocoEnjuego();

	}

	private void AgregarFocoEnjuego() {			

		try {

			vista.matrizJuego[logica.obtenerPosicionXdelCero()][logica.obtenerPosicionYdelCero()].addFocusListener(new FocusListener(){
				@Override
				public void focusGained(FocusEvent e) {
					// TODO Auto-generated method stub
				}

				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub
					vista.matrizJuego[logica.obtenerPosicionXdelCero()][logica.obtenerPosicionYdelCero()].requestFocusInWindow();
				}
			});


		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	class AccionBotononArriba implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent evento) {
			try {

				logica.moverPieza(logica.obtenerPosicionXdelCero()+1, logica.obtenerPosicionYdelCero());
				vista.nuevaMatriz(logica.getMatriz());
				vista.ActualizarPanel();
				vista.ActualizarCantidadMovimiento("Movimientos:"+logica.cantidadMovimiento());
				if(logica.estaResuelto()) {
					logica.RegistrarRecords();
					vista.ActualizarRecord("Record:"+logica.MostrarRecord());
					vista.Felicitaciones();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}			
		}
	}

	class AccionBotononAbajo implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent evento) {
			try {

				logica.moverPieza(logica.obtenerPosicionXdelCero()-1, logica.obtenerPosicionYdelCero());
				vista.nuevaMatriz(logica.getMatriz());
				vista.ActualizarPanel();
				vista.ActualizarCantidadMovimiento("Movimientos:"+logica.cantidadMovimiento());
				if(logica.estaResuelto()) {
					logica.RegistrarRecords();
					vista.ActualizarRecord("Record:"+logica.MostrarRecord());
					vista.Felicitaciones();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	class AccionBotononDerecha implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent evento) {
			try {

				logica.moverPieza(logica.obtenerPosicionXdelCero(), logica.obtenerPosicionYdelCero()-1);
				vista.nuevaMatriz(logica.getMatriz());
				vista.ActualizarPanel();
				vista.ActualizarCantidadMovimiento("Movimientos:"+logica.cantidadMovimiento());
				if(logica.estaResuelto()) {
					logica.RegistrarRecords();
					vista.ActualizarRecord("Record:"+logica.MostrarRecord());
					vista.Felicitaciones();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	class AccionBotononIzquierda implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent evento) {
			try {

				logica.moverPieza(logica.obtenerPosicionXdelCero(), logica.obtenerPosicionYdelCero()+1);
				vista.nuevaMatriz(logica.getMatriz());		
				vista.ActualizarPanel();
				vista.ActualizarCantidadMovimiento("Movimientos:"+logica.cantidadMovimiento());
				if(logica.estaResuelto()) {
					logica.RegistrarRecords();
					vista.ActualizarRecord("Record:"+logica.MostrarRecord());
					vista.Felicitaciones();
				}

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	class AccionComenzar implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent evento) {

			try {
				String datos = vista.ObtenerSeleccionCombo();

				if(datos.equals("3x3")) {
					vista.limpiarCuadricula();
					logica.SetearTamaño(3);
					vista.CrearCuadricula(logica.getMatriz());

				}
				if(datos.equals("4x4")) {
					vista.limpiarCuadricula();
					logica.SetearTamaño(4);
					vista.CrearCuadricula(logica.getMatriz());
				}
				if(datos.equals("5x5")) {
					vista.limpiarCuadricula();
					logica.SetearTamaño(5);
					vista.CrearCuadricula(logica.getMatriz());
				}

				vista.BotonesJuegos(new teclado());

			} catch (Exception e) {
				System.out.print("Algo salio mal \n");
			}	
		}
	}

	class AccionResetear implements ActionListener{

		public void actionPerformed(ActionEvent event) {
			String datos = vista.ObtenerSeleccionCombo();

			try {
				if(!datos.equals("Seleccione un nivel")) 
				{
					logica.SetearTamaño(logica.ObtenerNumeroCuadricula());
					vista.limpiarCuadricula();
					vista.CrearCuadricula(logica.getMatriz());
					vista.ActualizarCantidadMovimiento("Movimientos:  ");
					logica.GuardarRecordActual(vista.MostrarRecordActual());
					vista.ActualizarPanel();

				}	
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	class teclado implements KeyListener{
		@Override
		public void keyTyped(KeyEvent evento) {
			try {

				if(evento.getKeyChar()=='d') {
					logica.moverPieza(logica.obtenerPosicionXdelCero(), logica.obtenerPosicionYdelCero()-1);
					vista.nuevaMatriz(logica.getMatriz());		
					vista.ActualizarPanel();
					vista.ActualizarCantidadMovimiento("Movimientos:"+logica.cantidadMovimiento());
					if(logica.estaResuelto()) {
						logica.RegistrarRecords();
						vista.ActualizarRecord("Record:"+logica.MostrarRecord());
						vista.Felicitaciones();
					}
				}
				if(evento.getKeyChar()=='a') {
					logica.moverPieza(logica.obtenerPosicionXdelCero(), logica.obtenerPosicionYdelCero()+1);
					vista.nuevaMatriz(logica.getMatriz());		
					vista.ActualizarPanel();
					vista.ActualizarCantidadMovimiento("Movimientos:"+logica.cantidadMovimiento());
					if(logica.estaResuelto()) {
						logica.RegistrarRecords();
						vista.ActualizarRecord("Record:"+logica.MostrarRecord());
						vista.Felicitaciones();
					}
				}
				if(evento.getKeyChar()=='w') {
					logica.moverPieza(logica.obtenerPosicionXdelCero()+1, logica.obtenerPosicionYdelCero());
					vista.nuevaMatriz(logica.getMatriz());		
					vista.ActualizarPanel();
					vista.ActualizarCantidadMovimiento("Movimientos:"+logica.cantidadMovimiento());
					if(logica.estaResuelto()) {
						logica.RegistrarRecords();
						vista.ActualizarRecord("Record:"+logica.MostrarRecord());
						vista.Felicitaciones();
					}
				}
				if(evento.getKeyChar()=='s') {
					logica.moverPieza(logica.obtenerPosicionXdelCero()-1, logica.obtenerPosicionYdelCero());
					vista.nuevaMatriz(logica.getMatriz());		
					vista.ActualizarPanel();
					vista.ActualizarCantidadMovimiento("Movimientos:"+logica.cantidadMovimiento());
					if(logica.estaResuelto()) {
						logica.RegistrarRecords();
						vista.ActualizarRecord("Record:"+logica.MostrarRecord());
						vista.Felicitaciones();
					}
				}

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
		}
	}
}
