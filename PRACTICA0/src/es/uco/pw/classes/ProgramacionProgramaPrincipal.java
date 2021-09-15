package es.uco.pw.classes;

import java.util.Scanner;

/**
 * Programa principal que comprueba el funcionamiento de una programacion de espectaculos
 * @author Jaime Lorenzo Sanchez
 * @version 1.0
 */

public class ProgramacionProgramaPrincipal {

	public static void main(String[] args) {
		
		Scanner teclado = new Scanner (System.in); // Clase scanner es necesaria para obtener datos por teclado
		
		// Creamos una programacion vacia
		
		Programacion programacion = new Programacion();
		
		/*
		 * Creamos 2 espectaculos con los siguientes caracteristicas:
		 * 		1- Ambos espectaculos deben tener distinta categoria.
		 * 		2- Creamos los espectaculos utilizando el constructor vacio y los metodos set.
		 * 		3- Un espectaculo debe tener localidades disponibles y el otro sin localidades disponibles
		 */
		
		Espectaculo espectaculo1 = new Espectaculo(); // Creamos el espectaculo1 usando el constructor vacio
		
		// Obtenemos el titulo del espectaculo1
		
		System.out.print("Introduduce el titulo del espectaculo1: ");
		
		String titulo = teclado.nextLine();
		
		espectaculo1.setTituloEspectaculo(titulo);
		
		// Mostramos un menu con las categorias disponibles de los espectaculos
		
		int opcionMenuCategorias = 0;
		
		while(opcionMenuCategorias != -1) {
			
			mostrarMenuCategoriasEspectaculo();
			
			try {
			
				// Obtenemos la categoria indicada por el usuario
				
				opcionMenuCategorias = teclado.nextInt();
				
				// El usuario ha indicado la categoria concierto
				
				if(opcionMenuCategorias == 1) {
					
					// Modificamos la categoria del espectaculo
					
					espectaculo1.setCategoriaEspectaculo(CategoriaEspectaculoEnumeracion.concierto);
					
					// Salimos del bucle que muestra el menu de espectaculos
					
					opcionMenuCategorias = -1;
					
				}
				
				// El usuario ha indicado la categoria obra
				
				else if(opcionMenuCategorias == 2) {
					
					// Modificamos la categoria del espectaculo
					
					espectaculo1.setCategoriaEspectaculo(CategoriaEspectaculoEnumeracion.obra);
					
					// Salimos del bucle que muestra el menu de espectaculos
					
					opcionMenuCategorias = -1;
					
				}
				
				// El usuario ha indicado la categoria monologo
				
				else if(opcionMenuCategorias == 3) {

					// Modificamos la categoria del espectaculo
					
					espectaculo1.setCategoriaEspectaculo(CategoriaEspectaculoEnumeracion.monologo);
					
					// Salimos del bucle que muestra el menu de espectaculos
					
					opcionMenuCategorias = -1;
				}
				
			}catch(Exception ex) {
				System.out.println("La opcion del menu debe ser un entero");
				return;
			}
			
		}
		
		// Fecha de representacion del espectaculo1
		
		// Obtenemos el ano de representacion del espectaculo
		
		System.out.print("Introduce el ano de representacion del espectaculo1: ");
		
		try {
			int valor = teclado.nextInt();
			espectaculo1.setAnoDescripcionEspectaculo(valor);
		}catch(Exception ex) {
			System.out.println("El ano de representacion del espectaculo1 debe ser un valor entero");
			return;
		}
		
		// Obtenemos el mes de representacion del espectaculo1
		
		System.out.print("Introduce el mes de representacion del espectaculo1: ");
		
		try {
			int valor = teclado.nextInt();
			espectaculo1.setMesDescripcionEspectaculo(valor);
		}catch(Exception ex) {
			System.out.println("El mes de representacion del espectaculo1 debe ser un valor entero");
			return;
		}
		
		// Obtenemos el dia de representacion del espectaculo1
		
		System.out.print("Introduce el dia de representacion del espectaculo1: ");
		
		try {
			int valor = teclado.nextInt();
			espectaculo1.setDiaDescripcionEspectaculo(valor);
		}catch(Exception ex) {
			System.out.println("El dia de representacion del espectaculo1 debe ser un valor entero");
			return;
		}
		
		// Obtenemos la fecha completa de representacion del espectaculo1
		
		String fecha = espectaculo1.getAnoDescripcionEspectaculo() + "-" + espectaculo1.getMesDescripcionEspectaculo() + "-" + espectaculo1.getDiaDescripcionEspectaculo();
		
		espectaculo1.setFechaRepresentacionEspectaculo(fecha);
		
		System.out.println(espectaculo1.getFechaRepresentacionEspectaculo());
		
		// Numero de localidades en venta del espectaculo1
		
		// Numero de localidades vendidas del espectaculo1
	}
	
	/**
	 * Funcion que muestra un menu con las categorias disponibles de un espectaculo
	 */

	private static void mostrarMenuCategoriasEspectaculo() {
		System.out.println("\t Menu de categorias de un espectaculo");
		System.out.println("1. concierto");
		System.out.println("2. obra");
		System.out.println("3. monologo");
		System.out.println("Introduce una opcion: ");
	}

}
