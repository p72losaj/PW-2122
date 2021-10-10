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
import es.uco.pw.ejercicio2.espectaculos.CategoriaEspectaculo;
import es.uco.pw.ejercicio2.espectaculos.GestorEspectaculos;

/**
 * Programa ejecutable 
 * @author Jaime Lorenzo Sanchez
 * @author Jose Angel Exposito Fernandez
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
			
			gestorCriticas.obtencionCriticasRegistradas(prop);
			
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
						
						if(existe == false) {System.out.println("El nombre de usuario introducido no esta registrado");}
						
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
											int creacionCritica = gestorCriticas.creacionNuevaCritica(entrada, espectador.getNickEspectador());
											// Caso 1: No se ha creado la critica
											if(creacionCritica == 0) {
												System.out.println("No se han registrado los datos de la critica");
											}
											// Caso 2: Se ha creado la critica
											else {
												// Actualizamos el fichero de criticas
												gestorCriticas.RegistroCriticas(prop);
												// Actualizamos el gestor de criticas
												gestorCriticas.obtencionCriticasRegistradas(prop);
											}
										}
										
										// Funcion de consulta de todas las criticas disponibles
										
										else if(opcionCriticas == 2) {
											gestorCriticas.visualizacionCriticas();
										}
										
										// Funcion de borrado de una critica
										
										else if(opcionCriticas == 3) {
											// Obtenemos el titulo de la critica
											System.out.print("Introduce el titulo de la critica a eliminar: ");
											String tituloEliminar = entrada.nextLine();
											
											// Comprobamos si el titulo de la critica esta registrado
											
											boolean encontrado = gestorCriticas.comprobacionExistenciaTituloCritica(tituloEliminar);
											
											// Caso 0: el titulo de la critica no esta registrado
											
											if(encontrado == false) { System.out.println("El titulo de la critica no esta registrado en el sistema");}
											
											// Caso 1: El titulo de la critica esta registrado
											
											else {
												// Obtenemos los datos de la critica
												
												Critica critica = gestorCriticas.obtencionDatosCritica(tituloEliminar);
												
												// Comprobamos si el usuario es autor de la critica
												
												// Caso 0: El espectador es el autor de la critica
												
												if(critica.getAutorCritica().equals(espectador.getNickEspectador()) ) {
													// Eliminamos los datos de la critica
													gestorCriticas.eliminacionCritica(tituloEliminar);
													
													// Actualizamos el fichero de texto
													
													gestorCriticas.RegistroCriticas(prop);
													
													System.out.println("Critica eliminada");
												}
												
												// Caso 1: El espectador no es el autor de la critica
												
												else {
													System.out.println("El usuario no es autor de la critica. No tiene permisos para eliminar los datos de la critica");
												}
											}
										}
										
										// Funcion de votacion de la utilidad de una critica de otro usuario registrado
										
										else if(opcionCriticas == 4) {
											// Obtenemos el titulo de la critica
											System.out.print("Introduce el titulo de la critica a evaluar: ");
											String titulo = entrada.nextLine(); 
											Critica critica = new Critica(); // Creamos una critica vacio
											
											// Comprobamos si el titulo de la critica esta registrado
											
											Boolean encontrado = gestorCriticas.comprobacionExistenciaTituloCritica(titulo);
											
											// Caso 0: Critica no encontrada
											
											if(encontrado == false) {
												System.out.println("Titulo de critica no registrada en el sistema");
											}
											
											// Caso 1: Critica encontrada
											
											else {
												// Obtenemos los datos de la critica
												critica = gestorCriticas.obtencionDatosCritica(titulo);
												// Comprobamos si el usuario puede valorar la utilidad de la critica
												
												// Caso 0: El usuario no puede valorar la utilidad de la critica -> Es el autor de la critica
												
												if(critica.getAutorCritica().equals(espectador.getNickEspectador())) {
													System.out.println("El usuario no puede valorar la utilidad de la critica -> Es el autor de la critica");
												}
												
												// Caso 1: El usuario puede valorar la utilidad de la critica
												
												else {
													int evaluacionCritica = gestorCriticas.evaluacionUtilidadCritica(entrada,critica, espectador.getNickEspectador());
													// Caso 0: No se ha registrado la evaluacion de utilidad del usuario
													if(evaluacionCritica == 0) {
														System.out.println("No se ha registrado la evaluacion de utilidad de la critica");
													}
													// Caso 1: Se ha registrado la evaluacion de utilidad del usuario
													else {
														// Actualizamos los datos de la critica
														System.out.println("Actualizando los datos de la critica");
														gestorCriticas.RegistroCriticas(prop);
														// Actualizamos el gestor de criticas
														gestorCriticas.obtencionCriticasRegistradas(prop);
													}
												}
											}
											
										}
										
										// Funcion de busqueda de las criticas de un usuario registrado
										
										else if(opcionCriticas == 5) {
											
											// Pedimos un nombre de usuario registrado
											
											System.out.print("Introduce el nombre del usuario ha buscar sus criticas creadas:");
											
											String usuario = entrada.nextLine();
											
											// Comprobamos si el usuario esta registrado
											
											boolean encontrado = espectadores.comprobarExistenciaNickUsuario(usuario);
											
											// Caso 0: Usuario no encontrada
											
											if(encontrado == false) {
												System.out.println("Usuario <" + usuario + "> no encontrado");
											}
											
											// Caso 1: Usuario encontrado
											
											else {
												System.out.println("Mostrando las criticas del usuario " + usuario);
												gestorCriticas.visualizacionCriticasUsuario(usuario);
											}
											
										}
									}
									
								}
								
								// Menu de usuarios
								
								else if(opcion2 == 2) {
									entrada = new Scanner(System.in); // Limpiamos el buffer de entrada
									// Mostramos el menu de los usuarios
								
								}
								
								// Menu de espectaculos
								else if(opcion2 == 3) {
									
									GestorEspectaculos gestorEspectaculos = GestorEspectaculos.getInstancia();
									
									entrada = new Scanner(System.in); // Limpiamos el buffer de entrada
									
									int opcionEspectaculos = -1;
									
									while(opcionEspectaculos != 0) {
										
										// Mostramos el menu de Espectaculos
										
										menu.MostrarMenuEspectaculos();
										
										opcionEspectaculos = entrada.nextInt();
										
										entrada = new Scanner(System.in); // Limpiamos el buffer de entrada
										
										// Creacion de Espectaculos
										
										if(opcionEspectaculos == 1) {
											// Creamos el espectaculo
											
											String titulo = "",descr = "",tipo = "",fechaP = "",horaP = "",diaM1 = "", horaM1 = "",diaM2 = "",horaM2 = "",fechaIn = "" ,fechaFi = "",diaI = "";
											CategoriaEspectaculo cat = CategoriaEspectaculo.concierto;
											
											
											menu.MostrarTiposEspectaculos();
											int opcionEsp = entrada.nextInt();
												if(opcionEsp == 1) {
													System.out.print("Introduce el titulo del espectaculo: ");
													titulo = entrada.nextLine();
													System.out.print("Introduce la descripcion del espectaculo: ");
													descr = entrada.nextLine();
													tipo = "puntual";
													System.out.print("Introduce la fecha del espectaculo: ");
													fechaP = entrada.nextLine();
													System.out.print("Introduce la hora del espectaculo: ");
													horaP = entrada.nextLine();
													diaM1 = "";
													horaM1 = "";
													diaM2 = "";
													horaM2 = "";
													fechaIn="";
													fechaFi="";
													diaI="";
													
													
												}
												else if(opcionEsp == 2) {
													System.out.print("Introduce el titulo del espectaculo: ");
													titulo = entrada.nextLine();
													System.out.print("Introduce la descripcion del espectaculo: ");
													descr = entrada.nextLine();
													tipo = "multiple";
													fechaP = "";
													horaP = "";
													System.out.print("Introduce el dia 1 espectaculo: ");
													diaM1 = entrada.nextLine();
													System.out.print("Introduce la hora 1 del espectaculo: ");
													horaM1 = entrada.nextLine();
													System.out.print("Introduce el dia 2 espectaculo: ");
													diaM2 = entrada.nextLine();
													System.out.print("Introduce la hora 2 del espectaculo: ");
													horaM2 = entrada.nextLine();
													
													fechaIn="";
													fechaFi="";
													diaI="";
													
												}
												else if(opcionEsp == 3){
													System.out.print("Introduce el titulo del espectaculo: ");
													titulo = entrada.nextLine();
													System.out.print("Introduce la descripcion del espectaculo: ");
													descr = entrada.nextLine();
													tipo = "temporada";
													fechaP = "";
													horaP = "";
													diaM1 = "";
													horaM1 = "";
													diaM2 = "";
													horaM2 = "";
													System.out.print("Introduce la fecha de inicio del espectaculo: ");
													fechaIn= entrada.nextLine();
													System.out.print("Introduce la fecha de fin del espectaculo: ");
													fechaFi= entrada.nextLine();
													System.out.print("Introduce el dia del espectaculo");
													diaI= entrada.nextLine();
													
												}
												
												int cato = 0;
												while(cato == 0){
												System.out.print("Introduce categoria, 1. Concierto, 2. Monologo 3. Teatro ");
												try{cato = entrada.nextInt();
												if(cato == 1) {
													cat = CategoriaEspectaculo.concierto;
												}
												else if(cato == 2) {
													cat = CategoriaEspectaculo.monologo;
												}
												else if(cato == 3) {
													cat = CategoriaEspectaculo.obraTeatro;
												}}catch(Exception ex){
													System.out.println(" la opcion indicada no es una categoria valida ");}
												}
												
										gestorEspectaculos.DarAltaEspectaculo(titulo,descr,cat,tipo,fechaP,horaP,diaM1,horaM1,diaM2,horaM2,fechaIn,fechaFi,diaI);
												
										}//fin registrar espectaculo
								
										
										else if(opcionEspectaculos == 2) {
										
											System.out.print("Introduce el titulo del espectaculo: ");
											gestorEspectaculos.cancelarEspectaculo(entrada.nextLine());
										
										}//fin cancelar espectaculo
										
										else if(opcionEspectaculos == 3) {
											
											System.out.print("Introduce el titulo antiguo del espectaculo: ");
											gestorEspectaculos.cancelarEspectaculo(entrada.nextLine());
								
											String titulo = "",descr = "",tipo = "",fechaP = "",horaP = "",diaM1 = "", horaM1 = "",diaM2 = "",horaM2 = "",fechaIn = "" ,fechaFi = "",diaI = "";
											CategoriaEspectaculo cat = CategoriaEspectaculo.concierto;
											
											System.out.print("Introduzca los nuevos datos");
											menu.MostrarTiposEspectaculos();
											int opcionEsp = entrada.nextInt();
												if(opcionEsp == 1) {
													System.out.print("Introduce el titulo del espectaculo: ");
													titulo = entrada.nextLine();
													System.out.print("Introduce la descripcion del espectaculo: ");
													descr = entrada.nextLine();
													tipo = "puntual";
													System.out.print("Introduce la fecha del espectaculo: ");
													fechaP = entrada.nextLine();
													System.out.print("Introduce la hora del espectaculo: ");
													horaP = entrada.nextLine();
													diaM1 = "";
													horaM1 = "";
													diaM2 = "";
													horaM2 = "";
													fechaIn="";
													fechaFi="";
													diaI="";
													
													
												}
												else if(opcionEsp == 2) {
													System.out.print("Introduce el titulo del espectaculo: ");
													titulo = entrada.nextLine();
													System.out.print("Introduce la descripcion del espectaculo: ");
													descr = entrada.nextLine();
													tipo = "multiple";
													fechaP = "";
													horaP = "";
													System.out.print("Introduce el dia 1 espectaculo: ");
													diaM1 = entrada.nextLine();
													System.out.print("Introduce la hora 1 del espectaculo: ");
													horaM1 = entrada.nextLine();
													System.out.print("Introduce el dia 2 espectaculo: ");
													diaM2 = entrada.nextLine();
													System.out.print("Introduce la hora 2 del espectaculo: ");
													horaM2 = entrada.nextLine();
													
													fechaIn="";
													fechaFi="";
													diaI="";
													
												}
												else if(opcionEsp == 3){
													System.out.print("Introduce el titulo del espectaculo: ");
													titulo = entrada.nextLine();
													System.out.print("Introduce la descripcion del espectaculo: ");
													descr = entrada.nextLine();
													tipo = "temporada";
													fechaP = "";
													horaP = "";
													diaM1 = "";
													horaM1 = "";
													diaM2 = "";
													horaM2 = "";
													System.out.print("Introduce la fecha de inicio del espectaculo: ");
													fechaIn= entrada.nextLine();
													System.out.print("Introduce la fecha de fin del espectaculo: ");
													fechaFi= entrada.nextLine();
													System.out.print("Introduce el dia del espectaculo");
													diaI= entrada.nextLine();
													
												}
											int cato = 0;
												while(cato == 0){
												System.out.print("Introduce categoria, 1. Concierto, 2. Monologo 3. Teatro ");
												try{cato = entrada.nextInt();
												if(cato == 1) {
													cat = CategoriaEspectaculo.concierto;
												}
												else if(cato == 2) {
													cat = CategoriaEspectaculo.monologo;
												}
												else if(cato == 3) {
													cat = CategoriaEspectaculo.obraTeatro;
												}}catch(Exception ex){
													System.out.println(" la opcion indicada no es una categoria valida ");}
												}
										gestorEspectaculos.DarAltaEspectaculo(titulo,descr,cat,tipo,fechaP,horaP,diaM1,horaM1,diaM2,horaM2,fechaIn,fechaFi,diaI);
												
								
											
											
										}
										
										else if(opcionEspectaculos == 4) {
											System.out.print("No implementado");
										}

										else if(opcionEspectaculos == 5) {

											System.out.print("No implementado");
										}	
										

										else if(opcionEspectaculos == 6) {
											
											gestorEspectaculos.BuscarEspectaculos(entrada);
											
										}

										else if(opcionEspectaculos == 7) {
											System.out.print("No implementado");}

										else if(opcionEspectaculos == 8) {
											System.out.print("No implementado");}

										else if(opcionEspectaculos == 9) {
											System.out.print("No implementado");}
									

										else if(opcionEspectaculos == 10) {
											System.out.print("No implementado");}
										

										else if(opcionEspectaculos == 11) {
											System.out.print("No implementado");}
										
									}
										
										
										
										
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
