package base;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * 
 * @author Asier Iza
 *
 */
public class Principal {
	private static final Logger LOGGER = Logger.getLogger(Principal.class.getName());

	private static Scanner teclado = new Scanner(System.in);
	
	private static boolean permiso = false;
	
	private static boolean compuertasVerificadas = false;

	public static void main(String[] args) {

		System.out.println(
				"Este programa lee el nivel de agua de una presa y permite abrir compuertas si tenemos permiso y el nivel es superior a 50.");

		Handler fileHandler  = null;
		try{
			
            fileHandler  = new FileHandler("logs/opcionesMenu.log", true);
            
            fileHandler.setFormatter(new LogFormato());

            LOGGER.addHandler(fileHandler);
            
            
            fileHandler.setLevel(Level.FINE);

            
            LOGGER.setLevel(Level.FINE);
             
            LOGGER.config("Configuración hecha.");
            
            
		int nivel = leerNivelAgua();

		int opcion = 0;
		
	
		do {
			
			opcion = mostrarMenu(nivel);
			
			switch (opcion) {
			
			case 1:
				nivel = leerNivelAgua();
				permiso = false;
				compuertasVerificadas = false;
				break;
			case 2:
				boolean abiertas = intentarAbrirCompuertas();
				if (abiertas){
					mostrarMensajeCompuertasAbiertas();
				}else {
					mostrarMensajeNoSeCumplenCondiciones();
				}
				break;
			case 3:
				permiso = solicitarPermiso(nivel);
				if(!permiso) {
					System.out.println();
					System.out.print("El permiso solamente se concede si el nivel del agua es superior a 70.");
				}
				break;	
			case 4:
				compuertasVerificadas = verificarCompuertas();
				if(compuertasVerificadas) {
					System.out.println();
					System.out.print("�Compuertas verificadas!");
				}
				break;
			default:
				break;
			}
		} while (opcion != 5);
		}catch(IOException exception){
	        LOGGER.log(Level.SEVERE, "Ocurrió un error en FileHandler.", exception);
	    }
	}
	
	
/**
 * @author Asier Iza
 * @param nivel(int) que se genera en el método LeerNivelAgua
 * @return opción(int) introducida por el usuario
 */
	public static int mostrarMenu(int nivel) {
		int opcion;
		System.out.println();
		System.out.println("Nivel del agua: " + nivel);
		System.out.println();
		System.out.println("ACCIONES: ");
		System.out.println();
		System.out.println("1. Nueva lectura del nivel de agua.");
		System.out.println("2. Abrir compuertas. Requiere:");
		System.out.println("	3. Solicitar permiso. Estado: " + (permiso ? "CONCEDIDO" : "NO CONCEDIDO"));
		System.out.println("	4. Verificar compuertas. Estado: " + (compuertasVerificadas ? "VERIFICADAS" : "NO VERIFICADAS"));
		System.out.println("5. Salir");
		System.out.println();
		System.out.print("Introduce opci�n: ");
		opcion = teclado.nextInt();
		LOGGER.log(Level.FINE, "Se ha seleccionado la opción"+opcion);
		return opcion;
	}

	static int leerNivelAgua() {
		permiso = false;
		return (int) Math.round(Math.random() * 100);
	}

	static boolean intentarAbrirCompuertas() {
		if (permiso && compuertasVerificadas) {
			return true;
		}else {
			return false;
		}

	}

	private static void mostrarMensajeNoSeCumplenCondiciones() {
		System.out.println();
		System.out.print("No se cumplen las condiciones para abrir compuertas.");
	}

	private static void mostrarMensajeCompuertasAbiertas() {
		System.out.println();
		System.out.print("�Compuertas abiertas!");
	}
	
	static boolean solicitarPermiso(int nivel) {
		if (nivel > 70) {
			return true;
		}else {
			return false;
		}
	}
	static boolean verificarCompuertas() {		
		return true;
	}

}
