package es.uco.pw.ejercicio1.critica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.util.StringTokenizer;

import es.uco.pw.ejercicio1.espectador.Espectador;

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
	
	public void obtencionCriticasRegistradas(Properties prop) {
		
		String nombreFichero = prop.getProperty("ficheroCriticas");
		
		BufferedReader br = null; // Variable para leer el fichero
		
		try {
			
			// Abrimos el fichero de texto
			
			 br = new BufferedReader(new FileReader(nombreFichero));
			
			String texto = br.readLine(); // Leemos la primera linea del fichero
			
			// Repetimos mientras no llegue al final del fichero
			
			while(texto != null) {
				
				// Creamos una critica vacia
				
				Critica critica = new Critica();
				
				/*
				 *  Leemos la linea actual
				 *  Dividimos la cadena en partes en funcion del limitador <;>
				 */
				
				StringTokenizer st = new StringTokenizer(texto,";"); 
				
				// Recorremos la cadena de tokens para extraer los elementos
				
				ArrayList<String> linea = new ArrayList<String>();
				
				while(st.hasMoreTokens()) {
					// Almacenamos cada elemento de la linea
					linea.add(st.nextToken());
				}
				
				// Obtenemos los elementos de la linea
				
				for(int i=0; i<linea.size(); i++) {
					
					// Guardamos el titulo de la critica
					
					if(i==0) {
						critica.setTituloCritica(linea.get(i));
					}
					
					// Guardamos la puntuacion de la critica
					
					else if(i == 1) {
						critica.setPuntuacionEspectaculo(Integer.parseInt(linea.get(i)));
					}
					
					// Guardamos la resena de la critica
					
					else if(i== 2) {
						critica.setResenaEspectaculo(linea.get(i));
					}
					
					// Guardamos el autor de la critica
					
					else if(i == 3) {
						critica.setAutorCritica(linea.get(i));
					}
					
					// Guardamos las valoraciones de utilidad de la critica
					
				}
				
				// Almacenamos los datos del espectador en la lista de espectadores
				
				this.listaCriticas.add(critica);
				
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
	 * Funcion que crea una critica nueva
	 * @param entrada Buffer de entrada
	 * @param nombre Nombre de usuario del espectador
	 * @return 1 si se ha creado la critica; 0 en caso contrario
	 */

	public int creacionNuevaCritica(Scanner entrada, String nombre) {
		
		// Creamos una critica vacia
		
		Critica critica = new Critica();
		
		// Obtenemos el titulo de la critica
		
		System.out.print("Introduce el titulo de la critica: ");
		
		critica.setTituloCritica(entrada.nextLine());
		
		// Comprobamos si la critica ya esta registrada
		
		// Critica ya registrada
		
		if(comprobacionExistenciaTituloCritica(critica.getTituloCritica()) == true) {
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
			// Obtenemos la resena de la critica
			
			System.out.print("Introduce la resena de la critica: ");
			
			critica.setResenaEspectaculo(entrada.nextLine());
			
			// Almacenamos el autor de la critica
			
			critica.setAutorCritica(nombre);
			
			// Anadimos la critica a la lista de criticas
			
			this.listaCriticas.add(critica);
			
			return 1; // Se ha anadido la critica a la lista de criticas
			
		}
	}
	
	/**
	 * Funcion que comprueba si el titulo de una critica esta registrado o no
	 * @param tituloCritica Titulo de la critica a comprobar su existencia en el sistema
	 * @return true si la critica esta ya registrada; false en caso contrario
	 */

	public boolean comprobacionExistenciaTituloCritica(String tituloCritica) {
		// Recorremos la lista de criticas
		for(int i=0; i < this.listaCriticas.size(); i++) {
			// Titulo de critica encontrada
			if(this.listaCriticas.get(i).getTituloCritica().equals(tituloCritica)) {
				return true; // Critica registrada -> Retornamos true
			}
		}
		return false; // Por defecto, retorna false
	}
	
	/**
	 * Funcion que guarda la lista de criticas en un fichero de texto
	 * @param prop Fichero de propiedades
	 */

	public void RegistroCriticas(Properties prop) {
		// TODO Auto-generated method stub
		
		FileWriter fichero = null;
		
		try {
			
			// Abrimos el fichero de criticas
			
			fichero = new FileWriter(prop.getProperty("ficheroCriticas"));
			
			BufferedWriter escritura = new BufferedWriter(fichero);
			
			// Escribimos linea a linea en el fichero
			
			for(int i=0; i < this.listaCriticas.size(); i++) {
				
				// Titulo de la critica
				
				escritura.write(this.listaCriticas.get(i).getTituloCritica() + ";");
				
				// Puntuacion espectaculo
				
				escritura.write(this.listaCriticas.get(i).getPuntuacionEspectaculo()+";");
				
				// Resena de la critica
				
				escritura.write(this.listaCriticas.get(i).getResenaEspectaculo() + ";");
				
				// Autor de la critica
				
				escritura.write(this.listaCriticas.get(i).getAutorCritica() + ";");
				
				// Valoraciones de utilidad de la critica
				
				ArrayList<EvaluacionUtilidadCritica> lista = this.listaCriticas.get(i).getValoracionesUtilidadCritica();
				
				// Recorremos la lista de valoraciones de utilidad de la critica
				
				for(int j=0; j<lista.size(); j++) {
					// Escribimos en el fichero los datos de las valoraciones de utilidad de la critica
					escritura.write(lista.get(j).getAutorEvaluacion() + ":" + lista.get(j).getEvaluacionCritica());
					if(j != lista.size()) {
						escritura.write(",");
					}
				}
				
				escritura.newLine(); // Escribimos una nueva linea
			}
			escritura.close(); // Cerramos el fichero
			
		}catch(Exception ex) {
			System.out.println("Se ha producido un error al abrir el fichero: " + fichero);
			return;
		}
		
	}
	
	/**
	 * Funcion que evalua la utilidad de una critica
	 * @param entrada Buffer de entrada
	 * @param nombre Nombre de usuario del espectador que esta evaluando la utilidad de una critica
	 * @return 1 si se ha realizado la evaluacion; 0 en caso contrario
	 */

	public int evaluacionUtilidadCritica(String nombre) {
		

		return 0; // Por defecto retorna 0
	}
	
	/**
	 * Funcion que muestra por pantalla la informacion de las criticas registradas
	 */

	public void visualizacionCriticas() {
		
		// Recorremos la lista de criticas
		
		for(int i=0; i < this.listaCriticas.size(); i++) {
			// Mostramos la informacion de cada critica
			System.out.println("Critica: " + (i+1));
			this.listaCriticas.get(i).mostrarCritica();
			System.out.println(); // Imprimimos una linea vacia
		}
		
	}


	
	
	
	
}
