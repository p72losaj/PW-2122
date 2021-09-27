package es.uco.pw.ejercicio1.espectador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Gestor de espectadores
 * @author Jaime Lorenzo Sanchez
 * @version 1.0
 */

public class GestorEspectadores {
	
	/**
	 * Lista de espectadores
	 */
	
	ArrayList<Espectador> listaEspectadores = new ArrayList<Espectador>();

	/**
	 * Constructor de clase vacio
	 */
	public GestorEspectadores() {
		
	}
	
	/**
	 * Funcion que anade un nuevo espectador a la lista de espectadores
	 * @param entrada Buffer de entrada
	 * @param espectador Nuevo espectador a anadir en la lista de espectadores
	 * @param entrada buffer de entrada
	 * @return 1 si se ha anadido el espectador a la lista de espectadores; 0 en caso contrario
	 */
	
	public int anadirNuevoEspectador(Scanner entrada) 
	{
		
		Espectador espectador = new Espectador(); // Creamos un espectador vacio

		entrada = new Scanner(System.in); // Limpiamos el buffer de entrada 
		
		System.out.println("Introduzca su nombre de usuario: ");
		
		espectador.setNickEspectador(entrada.nextLine()); // Almacenamos el nick del espectador
		
		// Comprobamos si el usuario esta ya registrado
		
		if(comprobarExistenciaNickUsuario(espectador.getNickEspectador() ) == true) {
			
			// El nombre de usuario del espectador esta registrado
			
			System.out.println("El nombre de usuario ya esta registrado");
			
			return 0;
		
		} // Fin de la comprobacion de la existencia del nombre de usuario
		
		// El nombre de usuario del espectador no esta registrado
		
		else {
			
			// Pedimos el nombre del usuario
			
			System.out.println("Introduzca su nombre: ");
			
			espectador.setNombreEspectador(entrada.nextLine()); // Almacenamos el nombre del espectador
			
			// Pedimos el primer apellido del usuario
			
			System.out.println("Introduzca su primer apellido: ");
			
			espectador.setPrimerApellidoEspectador(entrada.nextLine()); // Almacenamos el primer apellido del espectador
			
			// Pedimos el segundo apellido del usuario
			
			System.out.println("Introduzca su segundo apellido: ");
			
			espectador.setSegundoApellidoEspectador(entrada.nextLine()); // Almacenamos el segundo apellido del espectador
			
			// Pedimos el correo del espectador
			
			System.out.println("Introduzca su correo: ");
			
			String correo = entrada.nextLine();
			
			Boolean valido = false;
			
			while(valido == false) {
				
				valido = espectador.comprobarValidezCorreo(correo); // Comprobamos si el correo es valido
				
				// El correo no es valido
				
				if(valido == false) {
					System.out.println("El correo introducido no es valido.");
					System.out.println("Ejemplo de correo valido: xxx@xxx.com/es");
					System.out.print("Introduzca un correo valido: ");
					correo = entrada.nextLine();
				}
				
				// El correo es valido
				
				else{
					
					// Comprobamos si el correo es unico
					Boolean unicidadCorreo = this.comprobarExistenciaCorreoEspectador(correo);
					// El correo ya existe
					if(unicidadCorreo == true) 
					{
						System.out.println("El correo introducido ya existe");
						System.out.println("Introduce <salir> si no desea anadir un correo");
						System.out.print("En caso contrario, introduce un nuevo correo: ");
						correo = entrada.nextLine();
						// El usuario no desea anadir un correo
						if(correo.equals("salir")) {return 0;}
					}
					// El correo no existe -> Almacenamos el correo del espectador
					else {espectador.setCorreoEspectador(correo);}
				}
				
			}
			
			// Almacenamos los datos del espectador en la lista de espectadores
			
			this.listaEspectadores.add(espectador);
			
			entrada = new Scanner(System.in); // Limpiamos el buffer de entrada
			return 1;
			
		} // fin_else
	}
	
	/**
	 * Funcion que comprueba si un usuario esta registrado en el sistema
	 * @param nick Nombre de usuario del espectador
	 * @return true si el usuario esta registrado; false en caso contrario
	 */
	
	
	public Boolean comprobarExistenciaNickUsuario(String nick) {

		// Recorremos la lista de espectadores
		
		for(int i=0; i < this.listaEspectadores.size(); i++) 
		{
			
			// Comprobamos si se ha encontrado el nombre de usuario del espectador
			
			if(this.listaEspectadores.get(i).getNickEspectador().equals(nick) ) 
			{
				return true; // Se ha encontrado el nombre de usuario del espectador
			} // fin de la comprobacion de la existencia del nick del usuario
			
		} // fin recorrido de la lista de espectadores
		
		return false; // Por defecto, se retorna false
	
	}
	
	/**
	 * Funcion que comprueba si el correo de un usuario esta registrado en el sistema
	 * @param correo Correo a comprobar si esta registrado
	 * @return true en caso de que el correo este registrado; false en caso contrario
	 */
	
	public Boolean comprobarExistenciaCorreoEspectador(String correo) 
	{
		
		// Recorremos la lista de espectadores
		
		for(int i=0; i < this.listaEspectadores.size(); i++) {
			
			// Correo encontrado. Retornamos true
			
			if(this.listaEspectadores.get(i).getCorreoEspectador().equals(correo)) 
			{
				return true;
			} // fin de la comprobacion de la existencia del correo del espectador
			
		} // fin del recorrido de la lista de espectadores
		
		return false; // Por defecto, retornamos false
	}
	
	/**
	 * Funcion que obtiene los datos registrados de los espectadores
	 * @param prop fichero de propiedades
	 */
	
	public void leerEspectadores(Properties prop) {
		
		
		String nombreFichero = prop.getProperty("ficheroEspectadores");
		
		BufferedReader br = null; // Variable para leer el fichero
		
		try {
			
			// Abrimos el fichero de texto
			
			 br = new BufferedReader(new FileReader(nombreFichero));
			
			String texto = br.readLine(); // Leemos la primera linea del fichero
			
			// Repetimos mientras no llegue al final del fichero
			
			while(texto != null) {
				
				Espectador espectador = new Espectador(); // Creamos un espectador vacio
				
				// Leemos la linea actual
				
				StringTokenizer st = new StringTokenizer(texto,":"); // Dividimos la cadena en partes en funcion del limitador <:>
				
				// Recorremos la cadena de tokens para extraer los elementos
				
				ArrayList<String> linea = new ArrayList<String>();
				
				while(st.hasMoreTokens()) {
					// Almacenamos cada elemento de la linea
					linea.add(st.nextToken());
				}
				
				// Mostramos los elementos de la linea
				
				for(int i=0; i<linea.size(); i++) {
					
					// Guardamos el nombre del espectador
					
					if(i==0) {
						espectador.setNombreEspectador(linea.get(i));
					}
					
					// Guardamos el primer apellido del espectador
					
					else if(i==1) {
						espectador.setPrimerApellidoEspectador(linea.get(i));
					}
					
					// Guardamos el segundo apellido del espectador
					
					else if(i==2) {
						espectador.setSegundoApellidoEspectador(linea.get(i));
					}
					
					// Guardamos el nombre de usuario del espectador
					
					else if(i==3) {
						espectador.setNickEspectador(linea.get(i));
					}
					
					// Guardamos el correo del espectador
					
					else if(i==4) {
						espectador.setCorreoEspectador(linea.get(i));
					}
					
					// Guardamos la lista de criticas del espectador
					
					
					
				}
				
				// Almacenamos los datos del espectador en la lista de espectadores
				
				this.listaEspectadores.add(espectador);
				
				// Leemos la siguiente linea
				
				texto = br.readLine();
			}
			
		}catch(Exception ex) {
			System.out.println("No se ha podido abrir el fichero: " + nombreFichero);
		}
		finally {
            try {
                if(br != null)
                    br.close();
            }
            catch (Exception e) {
                System.out.println("Error al cerrar el fichero");
                System.out.println(e.getMessage());
            }
        }
	}
	
	/**
	 * Funcion que guarda los datos de los espectadores en un fichero de texto
	 * @param prop Fichero de propiedades
	 */
	
	
	public void RegistrarEspectadores(Properties prop) {
		
		// Fichero de datos de los espectadores
		
		FileWriter fichero = null;
		
		try {
			
			// Abrimos el fichero de espectadores
			
			fichero = new FileWriter(prop.getProperty("ficheroEspectadores"));
			
			BufferedWriter escritura = new BufferedWriter(fichero);
			
			// Escribimos linea a linea en el fichero
			
			for(int i=0; i < this.listaEspectadores.size(); i++) {
				
				// Nombre del espectador
				
				escritura.write(this.listaEspectadores.get(i).getNombreEspectador() + ":");
				
				// Primer apellido del espectador
				
				escritura.write(this.listaEspectadores.get(i).getPrimerApellidoEspectador()+":");
				
				// Segundo apellido del espectador
				
				escritura.write(this.listaEspectadores.get(i).getSegundoApellidoEspectador() + ":");
				
				// Nick del espectador
				
				escritura.write(this.listaEspectadores.get(i).getNickEspectador() + ":");
				
				// Correo del espectador
				
				escritura.write(this.listaEspectadores.get(i).getCorreoEspectador());
				
				escritura.newLine(); // Escribimos una nueva linea
			}
			escritura.close(); // Cerramos el fichero
			
		}catch(Exception ex) {
			System.out.println("Se ha producido un error al abrir el fichero: " + fichero);
			return;
		}
	}
	
	/**
	 * Funcion que muestra por pantalla los datos de los espectadores
	 * @param prop
	 */

	public void visualizarDatosEspectadores(Properties prop) {
		
		for(int i=0; i<this.listaEspectadores.size(); i++) {
			System.out.println("Nombre del espectador: " + this.listaEspectadores.get(i).getNombreEspectador());
			System.out.println("Primer apellido del espectador: " + this.listaEspectadores.get(i).getPrimerApellidoEspectador());
			System.out.println("Segundo apellido del espectador: " + this.listaEspectadores.get(i).getSegundoApellidoEspectador());
			System.out.println("Nombre de usuario del espectador: " + this.listaEspectadores.get(i).getNickEspectador());
			System.out.println("Correo del espectador: " + this.listaEspectadores.get(i).getCorreoEspectador());
		}
		
	}
	
	/**
	 * Funcion que obtiene los datos de un usuario registrado
	 * @param nick Nombre de usuario del espectador
	 * @return Datos del usuario registrado
	 */

	public Espectador obtenerDatosUsuario(String nick) {
		// Creamos un espectador vacio
		Espectador espectador = new Espectador();
		// Recorremos la lista de espectadores
		for(int i=0; i < this.listaEspectadores.size(); i++) {
			// Obtenemos los datos del espectador
			if(this.listaEspectadores.get(i).getNickEspectador().equals(nick)) {
				espectador.setNombreEspectador(this.listaEspectadores.get(i).getNombreEspectador());
				espectador.setPrimerApellidoEspectador(this.listaEspectadores.get(i).getPrimerApellidoEspectador());
				espectador.setSegundoApellidoEspectador(this.listaEspectadores.get(i).getSegundoApellidoEspectador());
				espectador.setNickEspectador(nick);
				espectador.setCorreoEspectador(this.listaEspectadores.get(i).getCorreoEspectador());
			}
		}
		return espectador;// Retornamos los datos del usuario registrado
	}
	
	

}
