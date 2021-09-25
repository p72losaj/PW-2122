package es.uco.pw;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

/**
 * Programa ejecutable 
 * @author Jaime Lorenzo Sanchez
 * @version 1.0
 */

public class ProgramaPrincipal {

	public static void main(String[] args){
		
		Properties prop = new Properties(); // Inicializamos la clase Properties
		
		Scanner teclado = new Scanner (System.in); // Clase scanner es necesaria para obtener datos por teclado
		
		// Obtenemos la informacion del fichero de propiedades
		
		try {
			// Obtenemos la ubicacion actual del archivo
			String rutaAbsoluta = new File("").getAbsolutePath();
			// Obtenemos la ruta del fichero de propiedades
			String rutaFicheroPropiedades = rutaAbsoluta + "/src/es/uco/pw/ficheros/propiedades.properties";
			InputStream is = new FileInputStream(rutaFicheroPropiedades);
			prop.load(is);
			// Obtenemos la ubicacion del fichero de datos de las criticas
			String rutaFicheroDatosCriticas = rutaAbsoluta + "/src/es/uco/pw/ficheros/criticas.txt";
			// Especificamos en el fichero de propiedades la ubicacion del fichero de datos de criticas
			prop.setProperty("ficheroCriticas", rutaFicheroDatosCriticas);
			System.out.println(prop.getProperty("ficheroCriticas"));
		}catch(Exception ex) {
			System.out.println("Se ha producido un error al obtener la informacion del fichero de propiedades");
			return;
		}
		
	}

}
