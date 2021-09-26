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
		
		Scanner s = null;
		
		try {
			
			// Leemos el fichero de datos
		
			s = new Scanner(fichero);
			
			// Recorremos todas las lineas del fichero
			
			while( s.hasNextLine() ) {
				
				// Creamos una critica vacia
				
				Critica critica = new Critica();
				
				// Creamos una variable para obtener la informacion la critica
				
				String cadena = s.nextLine(); // Guardamos la linea en un string
				
				String[] cadena2 = cadena.split(":"); // Dividimos la linea en funcion de un limitador
				
				// Titulo de la critica
				
				if(cadena2[0].equals("TituloCritica")) {
					critica.setTituloCritica(cadena2[1]);
				}
				
				// Puntuacion de la critica
				
				else if(cadena2[0].equals("puntuacionCritica")) {
					try {
						critica.setPuntuacionEspectaculo(Integer.valueOf(cadena2[1]));
					}catch(Exception ex) {
						System.out.println("Se ha producido un error al obtener la puntuacion del espectaculo: ");
						return;
					}
				}
				
				// Resena de la critica
				
				else if(cadena2[0].equals("valoracionesUtilidad")) {
					// Recorremos la lita de valores de utilidad
					try {
						String[] cad = cadena2[1].split(",");
						
					}catch(Exception ex) {
						System.out.println("Se ha producido un error al obtener las valoraciones de la utilidad de la critica");
						return;
					}
				}
				
				
				// Almacenamos la critica en el gestor de criticas
				
				
			}
				
		}catch(Exception ex) {
			System.out.println("Se ha producido un error al abrir el fichero: " + fichero);
			return;
		}
		// Cerramos el fichero
		finally {
			try {
				if(s != null) {
					s.close();
				}
			}catch(Exception ex) {
				System.out.println("Se ha producido un error al cerrar el fichero: " + fichero);
			}
		}
	}
	
	
	
	
}
