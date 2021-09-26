package es.uco.pw;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

import es.uco.pw.critica.GestorCriticas;
import es.uco.pw.espectador.Espectador;
import es.uco.pw.espectador.Espectadores;
import es.uco.pw.menu.Menus;

/**
 * Programa ejecutable 
 * @author Jaime Lorenzo Sanchez
 * @version 1.0
 */

public class ProgramaPrincipal {

	public static void main(String[] args){
		
		Properties prop = new Properties(); // Inicializamos la clase Properties
		
		Scanner teclado = new Scanner (System.in); // Clase scanner es necesaria para obtener datos por teclado
		
		Menus menu = new Menus(); // clase que gestiona los distintos menus a mostrar al usuario
		
		// Obtenemos la informacion del fichero de propiedades
		
		try {
			
			// Obtenemos la ubicacion actual del archivo
			
			String rutaAbsoluta = new File("").getAbsolutePath();
			
			// Obtenemos la ruta del fichero de propiedades
			
			String rutaFicheroPropiedades = rutaAbsoluta + "/bin/es/uco/pw/ficheros/propiedades.properties";
			
			InputStream is = new FileInputStream(rutaFicheroPropiedades);
			
			prop.load(is);
			
			// Obtenemos la ubicacion del fichero de datos de las criticas
			
			String rutaFicheroDatosCriticas = rutaAbsoluta + "/bin/es/uco/pw/ficheros/criticas.txt";
			
			// Especificamos en el fichero de propiedades la ubicacion del fichero de datos de criticas
			
			prop.setProperty("ficheroCriticas", rutaFicheroDatosCriticas);
			
			// Obtenemos la ubicacion del fichero de datos de los espectadores
			
			String rutaFicheroDatosEspectadores = rutaAbsoluta + "/bin/es/uco/pw/ficheros/espectadores.txt";
			
			// Especificamos en el fichero de propiedades la ubicacion del fichero de datos de espectadores
			
			prop.setProperty("ficheroEspectadores", rutaFicheroDatosEspectadores);
			
			// Creamos el gestor de criticas
			
			GestorCriticas gestorCriticas = GestorCriticas.getInstancia();
			
			// Creamos un gestor de espectadores
			
			Espectadores espectadores = new Espectadores();
			
			// Leemos los datos del fichero de texto de espectadores
			
			espectadores.leerEspectadores(prop);
			
			// Leemos los datos del fichero de texto de criticas
			
			// gestorCriticas.obtenerCriticasRegistradas(prop);
			
			// Obtenemos la informacion del fichero de espectadores
			
			espectadores.leerEspectadores(prop);
			
			int opcionAcceso = -1;
			
			// Obtenemos la opcion indicada por el usuario
			
			while(opcionAcceso != 0) {
				
				try {
					
					// Mostramos el menu principal
					
					menu.MostrarMenuAcceso();					
					
					opcionAcceso = teclado.nextInt();
					
					teclado.nextLine(); // Leemos cualquier caracter quedado como basura
					
					// El usuario ha elegido la opcion de registro
					
					if(opcionAcceso == 1) {
						
						// Creamos un espectador vacio
						
						Espectador espectador = new Espectador();
						
						// Pedimos al usuario su nombre de usuario
						
						System.out.println("Introduzca su nombre de usuario: ");
						
						espectador.setNickEspectador(teclado.nextLine());
						
						// El usuario ya esta registrado
						
						if(espectadores.comprobarUsuario(espectador) == true) {
							System.out.println("El nombre de usuario ya esta registrado");
						}
						
						// Usuario no registrado
						
						else {
							
							// Pedimos el nombre del usuario
							
							System.out.println("Introduzca su nombre: ");
							
							espectador.setNombreEspectador(teclado.next());
							
							// Pedimos el primer apellido del usuario
							
							System.out.println("Introduzca su primer apellido: ");
							
							espectador.setPrimerApellidoEspectador(teclado.next());
							
							// Pedimos el segundo apellido del usuario
							
							System.out.println("Introduzca su segundo apellido: ");
							
							espectador.setSegundoApellidoEspectador(teclado.next());
							
							// Pedimos el correo del espectador
							
							System.out.println("Introduzca su correo: ");
							
							String correo = teclado.next();
							
							// Comprobamos si el correo es valido
							
							Boolean valido = espectador.comprobarValidezCorreo(correo);
							
							while(valido == false) {
								System.out.println("El correo introducido no es valido.");
								System.out.println("Ejemplo de correo valido: xxx@xxx.com/es");
								System.out.print("Introduzca un correo valido: ");
								correo = teclado.next();
								valido = espectador.comprobarValidezCorreo(correo);
							}
							
							espectador.setCorreoEspectador(correo);	
							
							// Comprobamos si se ha anadido los datos del espectador en el sistema
							
							if(espectadores.anadirNuevoEspectador(espectador) == 0) {
								System.out.println("Se ha producido un error al registrar sus datos en el sistema");
							}
							
							else {
								espectadores.RegistrarEspectadores(prop);
								menu.MostrarMenuAcceso();
							}
							
						}
						
						
					}
					// El usuario ha elegido la opcion de identificarse en el sistema
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
