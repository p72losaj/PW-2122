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
		return 0;
	}
	
	
	
	
}
