package es.uco.pw.ejercicio1.critica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

/**
 * Gestor de criticas, disenado mediante el patron de diseno Singleton
 * @author Jaime Lorenzo Sanchez
 * @version 1.0
 */

public class GestorCriticas {
	ArrayList<Critica> listaCriticas = new ArrayList<Critica>(); // Lista de criticas
	
	/**
	 *	Instancia unica de clase
	 */
	
	private static GestorCriticas instancia = null;
	
	/**
	 * Constructor de clase
	 */
	
	private GestorCriticas() {
		
	}
	
	/**
	 * Acceso a un punto de la instancia
	 */
	
	public static GestorCriticas getInstancia() {
		if(instancia == null) {
			instancia = new GestorCriticas();
		}
		return instancia;
	}
	
	/**
	 * Funcion que obtiene la informacion de las criticas registradas en un fichero de texto
	 * @param prop Fichero de propiedades
	 */
	
	public void obtenerCriticasRegistradas(Properties prop) {

		File fichero = new File(prop.getProperty("ficheroCriticas"));
		
		
		
		
	}
	
	/**
	 * Funcion que crea una critica nueva
	 * @param entrada Buffer de entrada
	 * @return 1 si se ha creado la critica; 0 en caso contrario
	 */

	public int crearNuevaCritica(Scanner entrada) {
		
		// Creamos una critica vacia
		
		Critica critica = new Critica();
		
		// Obtenemos el titulo de la critica
		
		System.out.print("Introduce el titulo de la critica: ");
		
		critica.setTituloCritica(entrada.nextLine());
		
		// Comprobamos si la critica ya esta registrada
		
		// Critica ya registrada
		
		if(comprobarExistenciaTituloCritica(critica.getTituloCritica()) == true) {
			System.out.println("Critica ya registrada");
			return 0; // No se crea la critica
		}
		
		// Critica no registrada
		
		else {
			// Obtenemos la puntuacion de la critica
			int opcion = -1;
			while(opcion == -1) {
				System.out.print("Introduce la puntuacion de la critica [0-muy malo, 10-Perfecto]: ");
				
				try {
					
					opcion = entrada.nextInt();
					
					if( (opcion < 0) || (opcion > 10) ) {
						System.out.println("Puntuacion de la critica fuera del rango establecido");
						entrada = new Scanner(System.in); // Limpiamos el buffer de entrada
						opcion = -1;
					}
					else {
						critica.setPuntuacionEspectaculo(opcion); // Almacenamos la puntuacion de la critica
					}
					entrada = new Scanner(System.in); // Limpiamos el buffer de entrada
				}catch(Exception ex) {
					System.out.println("La puntuacion de la critica debe ser un valor entero");
					entrada = new Scanner(System.in); // Limpiamos el buffer de entrada
				}
			}
			// 
		}
		
		return 0;
	}
	
	/**
	 * Funcion que comprueba si el titulo de una critica esta registrado o no
	 * @param tituloCritica Titulo de la critica a comprobar su existencia en el sistema
	 * @return true si la critica esta ya registrada; 0 en caso contrario
	 */

	private boolean comprobarExistenciaTituloCritica(String tituloCritica) {
		// Recorremos la lista de criticas
		for(int i=0; i < this.listaCriticas.size(); i++) {
			// Titulo de critica encontrada
			if(this.listaCriticas.get(i).getTituloCritica().equals(tituloCritica)) {
				return true; // Critica registrada -> Retornamos true
			}
		}
		return false; // Por defecto, retorna false
	}
	
	
	
	
}
