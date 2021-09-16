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
			
			espectaculo1.mostrarMenuCategoriasEspectaculo();
			
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
		
		// Caso de error de meses: Se ha introducido un numero de meses no valido
		
		while (espectaculo1.getMesDescripcionEspectaculo() < 1 || espectaculo1.getMesDescripcionEspectaculo() > 12 ) {
			
			System.out.println("El numero de mes indicado debe ser superior a 0 e inferior a 13");
			
			System.out.println("Introduce un mes valido: ");
			
			try {
			
				int valor = teclado.nextInt();
				
				espectaculo1.setMesDescripcionEspectaculo(valor);
			}catch(Exception ex) {
				
				System.out.println("El mes de representacion del espectaculo1 debe ser un valor entero");
				
				return;
			}
		}
		
		
		// Obtenemos el dia de representacion del espectaculo1
		
		System.out.print("Introduce el dia de representacion del espectaculo: ");
		
		try {
			int valor = teclado.nextInt();
			espectaculo1.setDiaDescripcionEspectaculo(valor);
		}catch(Exception ex) {
			System.out.println("El dia de representacion del espectaculo1 debe ser un valor entero");
			return;
		}
		

		// Caso de error de dias: Se ha introducido un numero de dias no valido
		
		while (espectaculo1.getDiaDescripcionEspectaculo() < 0 || espectaculo1.getDiaDescripcionEspectaculo() > 31 ) {
			System.out.println("El numero de dias indicado debe ser superior a 0 e inferior a 32");
			System.out.println("Introduce un dia valido: ");
			try {
				int valor = teclado.nextInt();
				espectaculo1.setDiaDescripcionEspectaculo(valor);
			}catch(Exception ex) {
				System.out.println("El dia del espectaculo debe ser un valor entero");
				return;
			}
		}
		
		// Comprobamos si la fecha es correcta o no
		
		int comprobadorFecha = espectaculo1.comprobarValidezFechasMesDia(espectaculo1.getMesDescripcionEspectaculo(), espectaculo1.getDiaDescripcionEspectaculo() );
		
		// Mientras que las fechas introducidas no sean correctas, pediremos el mes y/o el dia al usuario
		
		int opcionFechas = 2;
		
		while(comprobadorFecha != 0) {
			
			// Obtenemos el mes o el dia del espectaculo
			
			opcionFechas = espectaculo1.crearMenuObtencionFecha(teclado);
			
			// El usuario desea modificar el mes de representacion del espectaculo
			
			if(opcionFechas == 0) {
				try {
					System.out.println("Introduce el nuevo mes de representacion del espectaculo");
					int valor = teclado.nextInt();
					espectaculo1.setMesDescripcionEspectaculo(valor);
				}catch(Exception ex) {
					System.out.println("El mes de representacion del espectaculo debe ser un valor entero");
					return;
				}
				
				// Caso de error de meses: Se ha introducido un numero de meses no valido
				
				while (espectaculo1.getMesDescripcionEspectaculo() < 1 || espectaculo1.getMesDescripcionEspectaculo() > 12 ) {
					
					System.out.println("El numero de mes indicado debe ser superior a 0 e inferior a 13");
					
					System.out.println("Introduce un mes valido: ");
					
					try {
					
						int valor = teclado.nextInt();
						
						espectaculo1.setMesDescripcionEspectaculo(valor);
					
					}catch(Exception ex) {
						
						System.out.println("El mes de representacion del espectaculo1 debe ser un valor entero");
						
						return;
					}
				}
				
			}
			
			// El usuario desea modificar el dia de representacion del espectaculo
			
			else if(opcionFechas == 1) {
				
				try {
				
					System.out.println("Introduce el dia de representacion del espectaculo");
					
					int valor = teclado.nextInt();
					
					espectaculo1.setDiaDescripcionEspectaculo(valor);
				
				}catch(Exception ex) {
				
					System.out.println("El dia de representacion del espectaculo1 debe ser un valor entero");
					
					return;
				
				}
				
				// Caso de error de dias: Se ha introducido un numero de dias no valido
				
				while (espectaculo1.getDiaDescripcionEspectaculo() < 0 || espectaculo1.getDiaDescripcionEspectaculo() > 31 ) {
					
					System.out.println("El numero de dias indicado debe ser superior a 0 e inferior a 32");
					
					System.out.println("Introduce un dia valido: ");
					
					try {
					
						int valor = teclado.nextInt();
						
						espectaculo1.setDiaDescripcionEspectaculo(valor);
					
					}catch(Exception ex) {
					
						System.out.println("El dia del espectaculo debe ser un valor entero");
						
						return;
					
					}
				
				}
				
			}
			
			// Caso de error. Se ha producido una exception
			
			if(opcionFechas == 2) {
				System.out.println("Opcion del menu no existente");
			}
			
			// Comprobamos si la fecha es correcta
			
			comprobadorFecha = espectaculo1.comprobarValidezFechasMesDia(espectaculo1.getMesDescripcionEspectaculo(), espectaculo1.getDiaDescripcionEspectaculo() );
		
		}
		
		// Obtenemos la fecha completa de representacion del espectaculo1
		
		String fecha = espectaculo1.getAnoDescripcionEspectaculo() + "-" + espectaculo1.getMesDescripcionEspectaculo() + "-" + espectaculo1.getDiaDescripcionEspectaculo();
		
		espectaculo1.setFechaRepresentacionEspectaculo(fecha);
		
		// Mostramos la fecha de representacion del espectaculo
		
		System.out.println("Fecha de representacion del espectaculo1: " + espectaculo1.getFechaRepresentacionEspectaculo());
		
		// Numero de localidades en venta del espectaculo1
		
		// Numero de localidades vendidas del espectaculo1
		
	}
	

}
