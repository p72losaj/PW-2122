package es.uco.pw;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

import es.uco.pw.comun.menus.Menus;
import es.uco.pw.ejercicio1.critica.GestorCriticas;
import es.uco.pw.ejercicio1.espectador.Espectador;
import es.uco.pw.ejercicio1.espectador.Espectadores;

/**
 * Programa ejecutable 
 * @author Jaime Lorenzo Sanchez
 * @version 1.0
 */

public class ProgramaPrincipal {

	public static void main(String[] args){
		
		Properties prop = new Properties(); // Inicializamos la clase Properties
		
		Scanner entrada = new Scanner (System.in); // Clase scanner es necesaria para obtener datos por teclado
		
		Menus menu = new Menus(); // clase que gestiona los distintos menus a mostrar al usuario
		
		// Obtenemos la ubicacion actual del archivo
		
		String rutaAbsoluta = new File("").getAbsolutePath();
		
		try {
			
			// Obtenemos la ruta del fichero de propiedades
			
			// String rutaFicheroPropiedades = rutaAbsoluta + "/bin/es/uco/pw/comun/ficheros/propiedades.properties";
			
			String rutaFicheroPropiedades = rutaAbsoluta + "/src/es/uco/pw/comun/ficheros/propiedades.properties";
			
			InputStream is = new FileInputStream(rutaFicheroPropiedades);
			
			prop.load(is);
			
			// Obtenemos la ubicacion del fichero de datos de las criticas
			
			String rutaFicheroDatosCriticas = rutaAbsoluta + "/src/es/uco/pw/comun/ficheros/criticas.txt";
			
			// Especificamos en el fichero de propiedades la ubicacion del fichero de datos de criticas
			
			prop.setProperty("ficheroCriticas", rutaFicheroDatosCriticas);
			
			// Obtenemos la ubicacion del fichero de datos de los espectadores
			
			String rutaFicheroDatosEspectadores = rutaAbsoluta + "/src/es/uco/pw/comun/ficheros/espectadores.txt";
			
			// Especificamos en el fichero de propiedades la ubicacion del fichero de datos de espectadores
			
			prop.setProperty("ficheroEspectadores", rutaFicheroDatosEspectadores);
			
			// Creamos el gestor de criticas
			
			GestorCriticas gestorCriticas = GestorCriticas.getInstancia();
			
			// Creamos un gestor de espectadores
			
			Espectadores espectadores = new Espectadores();
			
			// Leemos los datos del fichero de texto de espectadores
			
			espectadores.leerEspectadores(prop);
			
			// Mostramos la lista de espectadores por pantalla
			
			espectadores.visualizarDatosEspectadores(prop);
			
			// Leemos los datos del fichero de texto de criticas
			
			// gestorCriticas.obtenerCriticasRegistradas(prop);
			
			int opcionAcceso = -1;
			
			// Obtenemos la opcion indicada por el usuario
			
			while(opcionAcceso != 0) {
				
				try {
					
					// Mostramos el menu principal
					
					menu.MostrarMenuAcceso();					
					
					System.out.print("Introduce una opcion: ");
					
					opcionAcceso = entrada.nextInt();
					
					// El usuario ha elegido la opcion de registro
					
					if(opcionAcceso == 1) {
						
						// Obtenemos los datos del espectador

						entrada = new Scanner(System.in); // Limpiamos el buffer de entrada
						
						if(espectadores.anadirNuevoEspectador(entrada) == 1) {
							System.out.println("Se han registrado los datos del espectador");
							espectadores.RegistrarEspectadores(prop); // Actualizamos el fichero de datos de los espectadores
							entrada = new Scanner(System.in); // Limpiamos el buffer de entrada
						}
					}
					
					// El usuario ha elegido la opcion de identificarse en el sistema
					
					else if(opcionAcceso == 2){
						entrada = new Scanner(System.in); // Limpiamos el buffer de entrada
						System.out.print("Introduce su nombre de usuario para acceder a su cuenta: ");
						String cadena = entrada.nextLine();
						// Comprobamos si el nombre de usuario del espectador esta registrado
						Boolean existe = espectadores.comprobarExistenciaNickUsuario(cadena);
						// El nombre de usuario no esta registrado
						if(existe == false) {
							System.out.println("El nombre de usuario introducido no esta registrado");
						}
						// El nombre de usuario esta registrado
						else {
							// Obtenemos los datos del usuario registrado
							Espectador espectador = new Espectador();
							espectador = espectadores.obtenerDatosUsuario(cadena); // Obtenemos los datos del usuario registrado
							System.out.println("Bienvenido " + espectador.getNickEspectador());
							
						}
					}
					
				}catch(Exception ex) {
					System.out.println("Debe introducir un valor entero");
				}
			}
			
		}catch(Exception ex) {
			System.out.println("Se ha producido un error al obtener la informacion del fichero de propiedades");
			return;
		}
		
	}

}
