package es.uco.pw.display;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.util.Properties;
import java.util.Scanner;

import es.uco.pw.datos.dao.usuario.UsuarioDAO;
import es.uco.pw.display.menus.Menus;
import es.uco.pw.negocio.critica.Critica;
import es.uco.pw.negocio.critica.GestorCriticas;
import es.uco.pw.negocio.espectaculo.GestorEspectaculos;
import es.uco.pw.negocio.usuario.UsuarioDTO;
import es.uco.pw.negocio.usuario.GestorUsuariosDTO;



/**
 * Programa ejecutable 
 * @author Jaime Lorenzo Sanchez
 * @author Jose Angel Exposito Fernandez
 * @version 2.0
 */

public class ProgramaPrincipal {

	public static void main(String[] args){
		
		Properties prop = new Properties(); // Inicializamos la clase Properties para el fichero de propiedades
		
		Properties sql = new Properties(); // Clase properties para el fichero de propiedades sql
		
		Scanner entrada = new Scanner (System.in); // Clase scanner es necesaria para obtener datos por teclado
		
		Menus menu = new Menus(); // clase que gestiona los distintos menus a mostrar al usuario
		
		// Obtenemos la ubicacion actual de los ficheros
		
		System.out.println();
		
		String rutaAbsoluta = "./ficheros";
		
				
		try {
			
			// Obtenemos la ruta del fichero de propiedades
			
			String rutaFicheroPropiedades = rutaAbsoluta + "/propiedades.properties";
			
			InputStream is = new FileInputStream(rutaFicheroPropiedades);
			
			prop.load(is);
			
			// Obtenemos la ruta del fichero sql
			
			String rutaFicheroSQL = rutaAbsoluta + "sql.properties";
			
			is = new FileInputStream(rutaFicheroSQL);
			
			sql.load(is);
			
			// Creamos el gestor de criticas
			
			GestorCriticas gestorCriticas = GestorCriticas.getInstancia();
			
			// Creamos un gestor de espectadores
			
			GestorUsuariosDTO espectadores = new GestorUsuariosDTO();
			
			int opcionAcceso = -1;
			
			// Obtenemos la opcion indicada por el usuario
			
			while(opcionAcceso != 0) {
				
				try {
					
					// Mostramos el menu principal
					
					menu.MostrarMenuAcceso();					
					
					System.out.print("Introduce una opcion: ");
					
					opcionAcceso = entrada.nextInt();
					
					entrada = new Scanner(System.in); // Limpiamos el buffer de entrada
					
					// El usuario ha elegido la opcion de registro
					
					if(opcionAcceso == 1) {
						
						UsuarioDTO usuarioDTO = new UsuarioDTO();
						
						// Obtenemos los datos del espectador
						
						System.out.println("Introduce su correo: ");
						
						String correo = entrada.nextLine();
						
						// Comprobamos si el correo es valido
						
						Boolean validezCorreo = usuarioDTO.comprobarValidezCorreo(correo);
						
						// Correo introducido no es valido
						
						while(validezCorreo == false) {
							System.out.println("El correo introducido <" + correo + "> no es valido");
							System.out.print("Introduce un correo valido: ");
							correo = entrada.nextLine(); // Obtenemos el nuevo correo
							validezCorreo = usuarioDTO.comprobarValidezCorreo(correo); // Volvemos a comprobar la validez del correo
						}
						
						// Almacenamos el correo del usuario
						
						usuarioDTO.setCorreoEspectador(correo);
						
						// Comprobamos si el correo esta registrado en la base de datos
						
						UsuarioDAO usuarioDAO = new UsuarioDAO();
						
						Boolean encontrado = usuarioDAO.comprobarExistenciaCorreo(usuarioDTO.getCorreoEspectador(), sql);
						
						
						
					}
					
					// El usuario ha elegido la opcion de identificarse en el sistema
				
					else if(opcionAcceso == 2) {
						
					}
				
				}catch(Exception ex) {
					System.out.println("Debe introducir un valor entero");
				}
			}
			
		}catch(Exception ex) {
			System.out.println("Se ha producido un error al obtener la informacion del fichero <propiedades.properties> y/o <sql.properties>");
			return;
		}
		
	}

}
