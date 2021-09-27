package es.uco.pw;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

import es.uco.pw.comun.menus.Menus;
import es.uco.pw.ejercicio1.critica.Critica;
import es.uco.pw.ejercicio1.critica.GestorCriticas;
import es.uco.pw.ejercicio1.espectador.Espectador;
import es.uco.pw.ejercicio1.espectador.GestorEspectadores;

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
		
		String rutaAbsoluta = "./ficheros"; 
				
		
		try {
			
			// Obtenemos la ruta del fichero de propiedades
			
			
			String rutaFicheroPropiedades = rutaAbsoluta + "/propiedades.properties";
			
			InputStream is = new FileInputStream(rutaFicheroPropiedades);
			
			prop.load(is);
			
			// Obtenemos la ubicacion del fichero de datos de las criticas
			
			String rutaFicheroDatosCriticas = rutaAbsoluta + "/criticas.txt";
			
			// Especificamos en el fichero de propiedades la ubicacion del fichero de datos de criticas
			
			prop.setProperty("ficheroCriticas", rutaFicheroDatosCriticas);
			
			// Obtenemos la ubicacion del fichero de datos de los espectadores
			
			String rutaFicheroDatosEspectadores = rutaAbsoluta + "/espectadores.txt";
			
			// Especificamos en el fichero de propiedades la ubicacion del fichero de datos de espectadores
			
			prop.setProperty("ficheroEspectadores", rutaFicheroDatosEspectadores);
			
			// Creamos el gestor de criticas
			
			GestorCriticas gestorCriticas = GestorCriticas.getInstancia();
			
			// Creamos un gestor de espectadores
			
			GestorEspectadores espectadores = new GestorEspectadores();
			
			// Leemos los datos del fichero de texto de espectadores
			
			espectadores.leerEspectadores(prop);
			
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
							
							int opcion2 = -1;
							
							while(opcion2 != 0) {
								
								// Mostramos el menu
								
								menu.MostrarMenuUsuarioRegistrado();
								
								// Menu de criticas
								
								opcion2 = entrada.nextInt();
								
								if(opcion2 == 1) {
									
									entrada = new Scanner(System.in); // Limpiamos el buffer de entrada
									
									int opcionCriticas = -1;
									
									while(opcionCriticas != 0) {
										
										// Mostramos el menu de criticas
										
										menu.MostrarMenuCriticas();
										
										opcionCriticas = entrada.nextInt();
										
										entrada = new Scanner(System.in); // Limpiamos el buffer de entrada
										
										// Funcion de creacion de una critica
										
										if(opcionCriticas == 1) {
											// Creamos la critica
											int creacionCritica = gestorCriticas.crearNuevaCritica(entrada);
										}
										
										// Funcion de consulta de todas las criticas disponibles
										
										// Funcion de borrado de una critica
										
										// Funcion de votacion de la utilidad de una critica de otro usuario registrado
										
										// Funcion de busqueda de las criticas de un usuario registrado
									}
									
								}
								
								// Menu de espectaculos
								else if(opcion2 == 2) {
									entrada = new Scanner(System.in); // Limpiamos el buffer de entrada
									// Mostramos el menu de espectaculos
								
								}
							}
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
