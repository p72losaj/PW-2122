package es.uco.pw.espectador;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;

/**
 * Gestor de espectadores
 * @author Jaime Lorenzo Sanchez
 * @version 1.0
 */

public class Espectadores {
	
	/**
	 * Lista de espectadores
	 */
	
	ArrayList<Espectador> listaEspectadores;

	/**
	 * Constructor de clase vacio
	 */
	public Espectadores() {
		this.listaEspectadores =new ArrayList<Espectador>();
	}
	
	/**
	 * Funcion que anade un nuevo espectador a la lista de espectadores
	 * @param espectador Nuevo espectador a anadir en la lista de espectadores
	 * @return 1 si se ha anadido el espectador; 0 en caso contrario
	 */
	
	public int anadirNuevoEspectador(Espectador espectador) {
		
		int tamanoAnterior = this.listaEspectadores.size();
		
		this.listaEspectadores.add(espectador);
		
		if(this.listaEspectadores.size() == (tamanoAnterior+1)) {
			return 1;
		}
		
		return 0;
		
	}
	
	/**
	 * Funcion que comprueba si un usuario esta registrado en el sistema
	 * @param espectador Espectador a comprobar si esta registrado
	 * @return true si el usuario esta registrado; false en caso contrario
	 */
	
	
	public Boolean comprobarUsuario(Espectador espectador) {

		// Recorremos la lista de espectadores
		
		for(int i=0; i < this.listaEspectadores.size(); i++) {
			
			// Comprobamos si se ha encontrado el nick del espectador
			
			if(this.listaEspectadores.get(i).getNickEspectador().contentEquals(espectador.getNickEspectador())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Funcion que comprueba si el correo de un usuario esta registrado en el sistema
	 * @param correo Correo a comprobar si esta registrado
	 * @return true en caso de que el correo este registrado; false en caso contrario
	 */
	
	public Boolean comprobarCorreoEspectador(String correo) {
		// Recorremos la lista de espectadores
		for(int i=0; i < this.listaEspectadores.size(); i++) {
			// Correo encontrado
			if(this.listaEspectadores.get(i).getCorreoEspectador().equals(correo)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Funcion que obtiene los datos registrados de los espectadores
	 * @param prop fichero de propiedades
	 */
	
	public void leerEspectadores(Properties prop) {
		File fichero = new File(prop.getProperty("ficheroEspectadores"));
		Scanner s = null;
		try {
			
			s = new Scanner(fichero);
			
			// Leemos linea a linea el fichero
			
			while(s.hasNextLine()) {
				
				String linea = s.nextLine(); // guardamos la linea en un fichero
				
				Espectador espectador = new Espectador(); // creamos un espectador vacio
				
				String[] cadena2 = linea.split(":"); // Dividimos la linea en funcion de un limitador
				
				// Nombre del espectador
				
				if(cadena2[0].equals("Nombre del espectador")){
					espectador.setNombreEspectador(cadena2[1]);
				}
				
				// Primer apellido del espectador
				
				else if(cadena2[0].contentEquals("Primer apellido del espectador")) {
					espectador.setSegundoApellidoEspectador(cadena2[1]);
				}
				
				// Segundo apellido del espectador
				
				else if(cadena2[0].equals("Segundo apellido del espectador")) {
					espectador.setSegundoApellidoEspectador(cadena2[1]);
				}
				
				// Nick del espectador
				
				else if(cadena2[0].equals("Nick del espectador")) {
					espectador.setNickEspectador(cadena2[1]);
				}
				
				// Correo del espectador
				
				else if(cadena2[0].equals("Correo del espectador")) {
					espectador.setCorreoEspectador(cadena2[1]);
				}
				
				// Lista de criticas del espectador
				
				else if(cadena2[0].equals("Criticas del espectador")) {
					String replace = cadena2[1].replaceAll("^\\[|]$", "");
					ArrayList<String> nuevo = new ArrayList<String>(Arrays.asList(replace));
					espectador.setListaCriticasPropias(nuevo);
				}
				// Guardamos los datos del espectador en la lista de espectadores
				
				this.listaEspectadores.add(espectador);
				
			}
		}catch(Exception ex) {
			System.out.println("No se ha podido abrir el fichero de espectadores: " + fichero);
		}
		// Cerramos el fichero
		 finally {
				try {
					if (s != null)
						s.close();
				} catch (Exception ex) {
					System.out.println("Se ha producido un error al cerrar el fichero");
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
			
			// Escribimos linea a linea en el fichero
			
			for(int i=0; i < this.listaEspectadores.size(); i++) {
				
				// Nombre del espectador
				
				fichero.write("Nombre del espectador:" + this.listaEspectadores.get(i).getNombreEspectador() + "\n");
				
				// Primer apellido del espectador
				
				fichero.write("Primer apellido del espectador:" + this.listaEspectadores.get(i).getPrimerApellidoEspectador() + "\n");
				
				// Segundo apellido del espectador
				
				fichero.write("Segundo apellido del espectador:" + this.listaEspectadores.get(i).getSegundoApellidoEspectador() + "\n");
				
				// Nick del espectador
				
				fichero.write("Nick del espectador:" + this.listaEspectadores.get(i).getNickEspectador() + "\n");
				
				// Correo del espectador
				
				fichero.write("Correo del espectador:" + this.listaEspectadores.get(i).getCorreoEspectador() + "\n");
				
				// Lista de criticas del espectador
				
				fichero.write("Criticas del espectador:" + this.listaEspectadores.get(i).getListaCriticasPropias() + "\n");
			}
			
		}catch(Exception ex) {
			System.out.println("Se ha producido un error al abrir el fichero: " + fichero);
			return;
		}
		
		finally {
			try {
				fichero.close();
			}catch(Exception ex) {
				System.out.println("Se ha producido un error al cerrar el fichero: " + fichero);
			}
		}
	}
	
	

}
