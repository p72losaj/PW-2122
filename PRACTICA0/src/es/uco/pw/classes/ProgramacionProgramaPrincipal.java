package es.uco.pw.classes;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Programa principal que comprueba el funcionamiento de una programacion de espectaculos
 * @author Jaime Lorenzo Sanchez
 * @version 1.0
 */

public class ProgramacionProgramaPrincipal {

	public static void main(String[] args) {
		
		Scanner teclado = new Scanner (System.in); // Clase scanner es necesaria para obtener datos por teclado
		
		String cadena; // Cadena que almacena los datos temporales del espectaculo (tipo String)
		
		int opcionMenuCategorias = 0; // Parametro de control del menu de categorias del espectaculo
		
		Programacion programacion = new Programacion(); // Creamos una programacion vacia
		
		/*
		 * Creamos 2 espectaculos con los siguientes caracteristicas:
		 * 		1- Ambos espectaculos deben tener distinta categoria.
		 * 		2- Creamos los espectaculos utilizando el constructor vacio y los metodos set.
		 * 		3- Un espectaculo debe tener localidades disponibles y el otro sin localidades disponibles
		 * 
		 */
		
		Espectaculo espectaculo1 = new Espectaculo(); // Creamos el espectaculo1 usando el constructor vacio
		
		System.out.println("Obtencion de datos del espectaculo1");
		
		// Obtenemos el titulo del espectaculo1
		
		System.out.print("Introduce el titulo del espectaculo: ");
		
		cadena = teclado.nextLine();
		
		espectaculo1.setTituloEspectaculo(cadena);
		
		// Obtenemos la categoria del espectaculo1
			
			while(opcionMenuCategorias != -1) {
							
			espectaculo1.mostrarMenuCategoriasEspectaculo(); // Menu de categorias del espectaculo
							
			try {
								
			opcionMenuCategorias = teclado.nextInt(); // Categoria elegida por el usuario
								
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
		
		// Obtenemos la descripcion del espectaculo1
			
		System.out.println("Introduce la descripcion del espectaculo1: ");
		
		teclado.nextLine();
		
		cadena = teclado.nextLine();
		
		espectaculo1.setDescripcionEspectaculo(cadena);
		
		// Obtenemos el ano de representacion del espectaculo1
		
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
		
		// Fecha completa de representacion del espectaculo1
				
		cadena = espectaculo1.getAnoDescripcionEspectaculo() + "-" + espectaculo1.getMesDescripcionEspectaculo() + "-" + espectaculo1.getDiaDescripcionEspectaculo();
				
		espectaculo1.setFechaRepresentacionEspectaculo(cadena);
		
		// Obtenemos el numero de localidades a la venta del espectaculo1
		
		System.out.println("Introduce el numero de localidades a la venta del espectaculo: ");
		
		int opcion = teclado.nextInt();
		
		espectaculo1.setNumeroLocalidadesVentaEspectaculo(opcion);
		
		// Obtenemos el numero de localidades vendidas del espectaculo1
		
		System.out.println("Introduce el numero de localidades vendidas del espectaculo: ");
		
		opcion = teclado.nextInt();
		
		espectaculo1.setNumeroLocalidadesVendidasEspectaculo(opcion);
		
		// Obtenemos los datos del espectaculo2
		
		System.out.println("Obteniendo los datos del espectaculo2");
		
		Espectaculo espectaculo2 = new Espectaculo(); // Creamos el espectaculo1 usando el constructor vacio
		
		teclado.nextLine();
		
		// Obtenemos el titulo del espectaculo2
		
		System.out.print("Introduce el titulo del espectaculo: ");
		
		String cadena2 = teclado.nextLine();
		
		espectaculo2.setTituloEspectaculo(cadena2);
		
		opcionMenuCategorias = 0;
		
		// Obtenemos la categoria del espectaculo2
			
		while(opcionMenuCategorias != -1) {
							
			espectaculo2.mostrarMenuCategoriasEspectaculo(); // Menu de categorias del espectaculo
							
			try {
								
			opcionMenuCategorias = teclado.nextInt(); // Categoria elegida por el usuario
								
			// El usuario ha indicado la categoria concierto
								
			if(opcionMenuCategorias == 1) {
				
				// Comprobamos si la categoria coincide con el espectaculo1
				
				if(espectaculo1.getCategoriaEspectaculo().equals(CategoriaEspectaculoEnumeracion.concierto)) {
					System.out.println("El espectaculo1 ya es de tipo concierto. Introduce una categoria distinta");
				}
									
				else {
					// Modificamos la categoria del espectaculo
					
					espectaculo2.setCategoriaEspectaculo(CategoriaEspectaculoEnumeracion.concierto);
										
					// Salimos del bucle que muestra el menu de espectaculos
										
					opcionMenuCategorias = -1;
				}
									
			}
								
			// El usuario ha indicado la categoria obra
								
			else if(opcionMenuCategorias == 2) {
				
				// Comprobamos si la categoria coincide con el espectaculo1
				
				if(espectaculo1.getCategoriaEspectaculo().equals(CategoriaEspectaculoEnumeracion.obra)) {
					System.out.println("El espectaculo1 ya es de tipo obra. Introduce una categoria distinta");
				}
				
				else {
					
					// Modificamos la categoria del espectaculo

					espectaculo2.setCategoriaEspectaculo(CategoriaEspectaculoEnumeracion.obra);
										
					// Salimos del bucle que muestra el menu de espectaculos
										
					opcionMenuCategorias = -1;
				}
				
									
			}
								
			// El usuario ha indicado la categoria monologo
								
			else if(opcionMenuCategorias == 3) {
				
				// Comprobamos si la categoria coincide con el espectaculo1
				
				if(espectaculo1.getCategoriaEspectaculo().equals(CategoriaEspectaculoEnumeracion.monologo)) {
					System.out.println("El espectaculo1 ya es de tipo monologo. Introduce una categoria distinta");
				}
				
				else {
					
					// Modificamos la categoria del espectaculo
					
					espectaculo2.setCategoriaEspectaculo(CategoriaEspectaculoEnumeracion.monologo);
										
					// Salimos del bucle que muestra el menu de espectaculos
										
					opcionMenuCategorias = -1;	
				}
				
			}
								
			}catch(Exception ex) {
				System.out.println("La opcion del menu debe ser un entero");
				return;
			}
							
		}
		
		// Obtenemos la descripcion del espectaculo2
		
		teclado.nextLine();
		
		System.out.println("Introduce la descripcion del espectaculo2: ");
		
		cadena2 = teclado.nextLine();
		
		espectaculo2.setDescripcionEspectaculo(cadena2);
		
		// Obtenemos el ano de representacion del espectaculo2
		
		System.out.print("Introduce el ano de representacion del espectaculo2: ");
		
		try {
			int valor = teclado.nextInt();
			espectaculo2.setAnoDescripcionEspectaculo(valor);
		}catch(Exception ex) {
			System.out.println("El ano de representacion del espectaculo1 debe ser un valor entero");
			return;
		}
		
		// Obtenemos el mes de representacion del espectaculo2
		
		System.out.print("Introduce el mes de representacion del espectaculo1: ");
		
		try {
			int valor = teclado.nextInt();
			espectaculo2.setMesDescripcionEspectaculo(valor);
		}catch(Exception ex) {
			System.out.println("El mes de representacion del espectaculo1 debe ser un valor entero");
			return;
		}
		
		// Caso de error de meses: Se ha introducido un numero de meses no valido
		
		while (espectaculo2.getMesDescripcionEspectaculo() < 1 || espectaculo2.getMesDescripcionEspectaculo() > 12 ) {
					
			System.out.println("El numero de mes indicado debe ser superior a 0 e inferior a 13");
					
			System.out.println("Introduce un mes valido: ");
			
			try {
			
				int valor = teclado.nextInt();
			
				espectaculo2.setMesDescripcionEspectaculo(valor);
			
			}catch(Exception ex) {
			
				System.out.println("El mes de representacion del espectaculo1 debe ser un valor entero");
			
				return;
			
			}
		}
		
		// Obtenemos el dia de representacion del espectaculo1
		
		System.out.print("Introduce el dia de representacion del espectaculo2: ");
		
		try {
			int valor = teclado.nextInt();
			espectaculo2.setDiaDescripcionEspectaculo(valor);
		}catch(Exception ex) {
			System.out.println("El dia de representacion del espectaculo1 debe ser un valor entero");
			return;
		}
		
		// Caso de error de dias: Se ha introducido un numero de dias no valido
		
		while (espectaculo2.getDiaDescripcionEspectaculo() < 0 || espectaculo2.getDiaDescripcionEspectaculo() > 31 ) {
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
		
		comprobadorFecha = espectaculo2.comprobarValidezFechasMesDia(espectaculo2.getMesDescripcionEspectaculo(), espectaculo2.getDiaDescripcionEspectaculo() );
				
		// Mientras que las fechas introducidas no sean correctas, pediremos el mes y/o el dia al usuario
				
		opcionFechas = 2;
				
		while(comprobadorFecha != 0) {
		
			// Obtenemos el mes o el dia del espectaculo
					
			opcionFechas = espectaculo2.crearMenuObtencionFecha(teclado);
					
			// El usuario desea modificar el mes de representacion del espectaculo
					
			if(opcionFechas == 0) {
					
				try {
				
					System.out.println("Introduce el nuevo mes de representacion del espectaculo2");
							
					int valor = teclado.nextInt();
					
					espectaculo2.setMesDescripcionEspectaculo(valor);
						
				}catch(Exception ex) {
				
					System.out.println("El mes de representacion del espectaculo debe ser un valor entero");
					
					return;
					
				}
						
				
				// Caso de error de meses: Se ha introducido un numero de meses no valido
						
				
				while (espectaculo2.getMesDescripcionEspectaculo() < 1 || espectaculo2.getMesDescripcionEspectaculo() > 12 ) {
							
							
					System.out.println("El numero de mes indicado debe ser superior a 0 e inferior a 13");
							
					
					System.out.println("Introduce un mes valido: ");
							
					
					try {
							
					
						int valor = teclado.nextInt();
								
						
						espectaculo2.setMesDescripcionEspectaculo(valor);
							
							
					}catch(Exception ex) {
								
					
						System.out.println("El mes de representacion del espectaculo2 debe ser un valor entero");
								
						
						return;
							
					}
					
				}
						
				
			}
					
			
			// El usuario desea modificar el dia de representacion del espectaculo
					
			
			else if(opcionFechas == 1) {
						
			
				try {
						
				
					System.out.println("Introduce el dia de representacion del espectaculo");
							
					
					int valor = teclado.nextInt();
							
					
					espectaculo2.setDiaDescripcionEspectaculo(valor);
						
						
				}catch(Exception ex) {
						
				
					System.out.println("El dia de representacion del espectaculo2 debe ser un valor entero");
							
					
					return;
						
						
				}
						
				
				// Caso de error de dias: Se ha introducido un numero de dias no valido
						
				
				while (espectaculo2.getDiaDescripcionEspectaculo() < 0 || espectaculo2.getDiaDescripcionEspectaculo() > 31 ) {
							
							
					System.out.println("El numero de dias indicado debe ser superior a 0 e inferior a 32");
							
					
					System.out.println("Introduce un dia valido: ");
							
					
					try {
							
					
						int valor = teclado.nextInt();
								
						
						espectaculo2.setDiaDescripcionEspectaculo(valor);
							
							
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
					
			
			comprobadorFecha = espectaculo2.comprobarValidezFechasMesDia(espectaculo2.getMesDescripcionEspectaculo(), espectaculo2.getDiaDescripcionEspectaculo() );
				
				
		}
		
		
		// Fecha completa de representacion del espectaculo1
				
		
		cadena = espectaculo2.getAnoDescripcionEspectaculo() + "-" + espectaculo2.getMesDescripcionEspectaculo() + "-" + espectaculo2.getDiaDescripcionEspectaculo();
				
		espectaculo2.setFechaRepresentacionEspectaculo(cadena);
		
		// Obtenemos el numero de localidades vendidas del espectaculo2
		
		System.out.println("Introduce el numero de localidades vendidas del espectaculo: ");
		
		opcion = teclado.nextInt();
		
		espectaculo2.setNumeroLocalidadesVendidasEspectaculo(opcion);
		
		// Anadimos el espectaculo1 a la programacion
		
		programacion.programarEspectaculo(espectaculo1);
		
		// Anadimos el espectaculo2 a la programacion
		
		programacion.programarEspectaculo(espectaculo2);
		
		
		/**
		 * Añadir otros dos espectáculos a la programación, utilizando el constructor parametrizado
		 */
		
		// Creamos el espectaculo3 usando el constructor parametrizado
		
		Espectaculo espectaculo3 = new Espectaculo("Rey Leon", CategoriaEspectaculoEnumeracion.obra, "Representacion del Rey Leon");
		
		// fecha de representacion del espectaculo
		
		espectaculo3.setFechaRepresentacionEspectaculo("2025-09-21");
		
		// Numero de localidades vendidas
		
		espectaculo3.setNumeroLocalidadesVendidasEspectaculo(100);
		
		// Numero de localidades en venta
		
		espectaculo3.setNumeroLocalidadesVentaEspectaculo(100);
		
		// Creamos el espectaculo4 usando el constructor parametrizado
		
		Espectaculo espectaculo4 = new Espectaculo("David", CategoriaEspectaculoEnumeracion.monologo, "Historia de David");
		
		// Fecha de representacion del espectaculo4 
		
		espectaculo4.setFechaRepresentacionEspectaculo("2021-09-20");
		
		// Numero de localidades vendidas
		
		espectaculo4.setNumeroLocalidadesVendidasEspectaculo(100);
				
		// Numero de localidades en venta
				
		espectaculo4.setNumeroLocalidadesVentaEspectaculo(100);
		
		// Anadimos el espectaculo3 a la programacion
		
		programacion.programarEspectaculo(espectaculo3);
		
		// Anadimos el espectaculo4 a la programacion
		
		programacion.programarEspectaculo(espectaculo4);
		
		// Obtenemos la lista de espectaculos de la programacion
		
		ArrayList<String> nombreEspectaculos = programacion.verTitulos();
		
		// Mostramos los titulos de los espectaculos en la programacion
		
		System.out.println("Mostrando los titulos de los espectaculos en la programacion");
		
		for(int i=0; i < nombreEspectaculos.size(); i++) {
			System.out.println("Espectaculo: " + i + ": " + nombreEspectaculos.get(i));
		}
		
		// Metodo verProximosEspectaculos con una fecha valida
		
		ArrayList<Espectaculo> listaEspectaculos = programacion.verProximosEspectaculos("2049-01-02");
		
		System.out.println("Mostrado los proximos espectaculos usando fecha valida");
		
		for(int i=0; i < listaEspectaculos.size(); i++) {
			listaEspectaculos.get(i).toStringEspectaculo();
		}
		
		// Metodo verProximosEspectaculos con una fecha no valida
		
		listaEspectaculos = programacion.verProximosEspectaculos("1999-01-01");
		
		System.out.println("Mostrando los proximos espectaculos usando fecha no valida");
		
		for(int i=0; i < listaEspectaculos.size(); i++) {
			listaEspectaculos.get(i).toStringEspectaculo();
		}
		
		/*
		 * Invocar al método verEspectaculosDisponibles para una categoría de espectáculo considerada en los pasos 2 o 3.
		 */
		
		listaEspectaculos = programacion.verEspectaculosDisponibles(CategoriaEspectaculoEnumeracion.monologo);
		
		System.out.println("Mostrando los monologos disponibles");
		
		for(int i=0; i < listaEspectaculos.size(); i++) {
			listaEspectaculos.get(i).toStringEspectaculo();
		}
		
		// Invocamos el metodo eliminarEspectaculo
		
		System.out.println("Eliminando el espectaculo 3...");
		
		programacion.eliminarEspectaculo(espectaculo3.getTituloEspectaculo());
		
		System.out.println("Mostrando los espectaculos disponibles");
		
		programacion.imprimirEventos();
		
		
		
	}
	

}
