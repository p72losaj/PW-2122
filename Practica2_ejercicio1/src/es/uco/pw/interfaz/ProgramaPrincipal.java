package es.uco.pw.interfaz;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

import es.uco.pw.datos.dao.critica.CriticaDAO;
import es.uco.pw.datos.dao.espectaculo.EspectaculoDAO;
import es.uco.pw.datos.dao.relacion.EspectaculoCriticaDAO;
import es.uco.pw.datos.dao.relacion.UsuarioCriticaDAO;
import es.uco.pw.datos.dao.usuario.UsuarioDAO;
import es.uco.pw.interfaz.menus.Menus;
import es.uco.pw.negocio.critica.CriticaDTO;
import es.uco.pw.negocio.critica.EvaluacionUtilidadCriticaDTO;
import es.uco.pw.negocio.critica.GestorCriticasDTO;
import es.uco.pw.negocio.espectaculo.GestorEspectaculosDTO;
import es.uco.pw.negocio.usuario.UsuarioDTO;
import es.uco.pw.negocio.usuario.GestorUsuariosDTO;
import es.uco.pw.negocio.usuario.RolUsuario;

/**
 * Funcion principal del programa
 * @author Jaime Lorenzo Sanchez
 * @author Jose Angel Exposito Fernandez
 * @version 2.0
 */

public class ProgramaPrincipal {
/**
 * Funcion principal del programa
 * @param args Argumentos pasados como parametros al realizar la ejecucion del programa
 */ 
	public static void main(String[] args){
		Properties prop = new Properties(); // Inicializamos la clase Properties para el fichero de propiedades
		Properties sql = new Properties(); // Clase properties para el fichero de propiedades sql
		// Creamos un usuario vacio de tipo DAO
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		// Creamos una critica vacia de tipo DAO
		CriticaDAO criticaDAO = new CriticaDAO(); 
		// Creamos un espectaculo vacio de tipo DAO
		EspectaculoDAO espectaculoDAO = new EspectaculoDAO();
		// Creamos una relacion Espectaculo-critica de tipo DAO
		EspectaculoCriticaDAO puntuacionEspectaculo = new EspectaculoCriticaDAO();
		// Relacion criticas-usuario
		UsuarioCriticaDAO evaluacionCritica = new UsuarioCriticaDAO();
		// Limpiamos el buffer de entrada
		Scanner entrada = new Scanner (System.in);
		// Anadimos la clase menu para obtener los distintos menus a mostrar al usuario
		Menus menu = new Menus(); 
		try {
			/*
			 * FICHERO DE PROPIEDADES
			 */
			String rutaAbsoluta = "./ficheros"; // Obtenemos la ubicacion de los ficheros
			String rutaFicheroConfiguracion = rutaAbsoluta + "/config.properties"; // Ruta del fichero de configuracion
			InputStream is = new FileInputStream(rutaFicheroConfiguracion); // Obtenemos la ruta del fichero de configuracion
			prop.load(is); // Leemos los datos del fichero de configuracion
			String rutaFicheroSQL = rutaAbsoluta + "/sql.properties"; // ruta del fichero sql
			is = new FileInputStream(rutaFicheroSQL); // Obtenemos la ruta del fichero de sentencias sql
			sql.load(is); // Leemos los datos del fichero de sentencias sql
			/*
			 * GESTOR DE CRITICAS
			 */
			GestorCriticasDTO gestorCriticas = GestorCriticasDTO.getInstancia(); // Creamos una instancia del gestor de criticas
			gestorCriticas.obtencionDatosCriticas(prop,sql); // Obtenemos los datos de las criticas registradas en la base de datos
			/*
			 * GESTOR DE USUARIOS
			 */
			GestorUsuariosDTO usuarios = new GestorUsuariosDTO(); // Creamos un gestor de usuarios
			usuarios.setListaEspectadores(usuarioDAO.obtenerUsuarios(sql,prop)); // Obtenemos los datos de los usuarios
			/*
			 * GESTOR DE ESPECTACULOS
			 */
			GestorEspectaculosDTO espectaculos = GestorEspectaculosDTO.getInstancia(); // Creamos el gestor de espectaculos
			espectaculos.obtenerEspectaculosRegistrados(prop, sql); // Obtenemos los datos de los espectaculos registrados en la base de datos
			/*
			 * MENU DE ACCESO
			 */
			int opcionAcceso = -1; // Opcion inicial para el menu de acceso
			while(opcionAcceso != 0) { // Obtenemos la opcion indicada por el usuario
				
				try {
					menu.MostrarMenuAcceso(); // Mostramos el menu principal
					System.out.print("Introduce una opcion: "); // Pedimos al usuario una funcionalidad del menu principal
					opcionAcceso = entrada.nextInt(); // Obtenemos la funcionalidad deseada por el usuario
					entrada = new Scanner(System.in);  // Limpiamos el buffer de entrada
					/*
					 * REGISTRO DE UN USUARIO
					 */
					if(opcionAcceso == 1) {
						/*
						 * OBTENCION DATOS DEL USUARIO
						 */
						System.out.println("Introduce su correo: "); // Pedimos al usuario su correo
						String correo = entrada.nextLine(); // Obtenemos el correo del usuario
						System.out.print("Introduce su nombre: "); // Pedimos al usuario su nombre
						String nombre = entrada.nextLine();  // Obtenemos el nombre del usuario
						System.out.print("Introduce su primer apellido: "); // Pedimos al usuario su primer apellido
						String apellido1 = entrada.nextLine(); // Obtenemos el primer apellido del usuario
						System.out.print("Introduce su segundo apellido: "); // Pedimos el segundo apellido del usuario
						String apellido2 = entrada.nextLine(); // Obtenemos el segundo apellido del usuario
						System.out.print("Introduce su nombre de usuario: "); // Pedimos el nick del usuario
						String nick = entrada.nextLine();// Obtenemos el nick del usuario
						/*
						 * MENU DE ROLES DEL USUARIO
						 */
						int opcionRol = 0; // Por defecto, la opcion del rol es 0
						menu.MostrarRolUsuario(); // Mostramos el menu de roles del usuario
						try {
							opcionRol = entrada.nextInt(); // Obtenemos el rol del usuario
							entrada = new Scanner(System.in); // Limpiamos el buffer de entrada
						}catch(Exception ex) {
							System.out.println("Se esperaba un valor de tipo entero al obtener el rol del usuario");
						}
						String rol = null; // Rol del usuario
						if(opcionRol == 1) {rol = "administrador"; } // Caso 1: El rol del usuario es administrador
						else if(opcionRol == 2) { rol = "espectador" ; } // Caso 2: el rol del usuario es espectador 
						// El rol del usuario es distinto a null
						if(rol != null) {
							/*
							 * REGISTRO DE LOS DATOS DEL USUARIO EN LA BASE DE DATOS
							 */
							String comprobacion = usuarios.registrarUsuario(prop,sql,correo,nombre, apellido1, apellido2,nick,rol); // Insertamos los datos del usuario en la base de datos
							System.out.println(comprobacion); // Imprimimos la comprobacion del registro del usuario
						}
					}
					
					// El usuario ha elegido la opcion de identificarse en el sistema
				
					else if(opcionAcceso == 2) {
						// Pedimos al usuario su correo
						System.out.print("Introduce su correo para acceder a su cuenta: ");
						// Obtenemos el correo del usuario
						String correo = entrada.nextLine();
						// Comprobamos si el correo esta registrado
						Boolean encontrado = usuarios.comprobarExistenciaCorreoEspectador(correo);
						// Caso de error: Correo no registrado en la base de datos
						if(encontrado == false) { System.out.println("Correo no registrado en la base de datos"); }
						// Caso de exito: Correo registrado en la base de datos
						else {
							// Creamos un usuario DTO vacio
							UsuarioDTO usuarioDTO = new UsuarioDTO();
							// Obtenemos los datos del usuario
							usuarioDTO = usuarios.obtenerDatosUsuario(correo); 
							// Caso 1: Usuario administrador
							if(usuarioDTO.getRolUsuario().equals(RolUsuario.administrador)) {
								int administrador = -1;
								while(administrador != 0) {
									// Mostramos el menu del administrador
									menu.MostrarMenuAdministrador();
									try {
										// Obtenemos la funcionalidad deseada por el usuario
										administrador = entrada.nextInt();
										 // Limpiamos el buffer
										entrada = new Scanner(System.in);
										// Caso 1: Dar de alta un espectaculo
										// Caso 2: Cancelar un espectaculo ( todas las sesiones o una en particular)
										// Caso 3: Actualizar los datos de un espectáculo
										// Caso 4: Contabilizar la venta de entradas para una sesión de un espectáculo
										// Caso 5: Consultar las localidades disponibles para un espectáculo, dada una fecha de  representación
										// caso 6: Búsqueda de espectáculos por título o por categoría
										// Caso 7: Búsqueda de próximos espectáculos con entradas disponibles, indicando o no una  categoría específica
										// Caso 8: Publicar una crítica para un espectáculo que ya se ha celebrado
										// Caso 9: Consultar las críticas de un espectáculo, dado su título
										// Caso 10: Eliminar criticas de un espectaculo, por parte del usuario que la creo
										// Caso 11: Valorar la utilidad de una crítica publicada por otro usuario
									}
									// Recogemos la excepcion producida -> El usuario no ha introducido un valor entero
									catch(Exception ex) {System.out.println("Se esperaba un valor entero"); }
								// Cierre de sesion
								}
							}
							
							// Caso 2: Usuario espectador
							
							else {
								int espectador = 1;
								// Limpiamos el buffer de entrada
								entrada = new Scanner(System.in); 
								while(espectador != 0) {
									// Mostramos el menu del espectador
									menu.MostrarMenuEspectador(); 
									try {
										// Obtenemos la funcionalidad del espectador
										espectador = entrada.nextInt(); 
										// Limpiamos el buffer de entrada
										entrada = new Scanner(System.in); 
										// Caso 1: Funcionalidad de creacion de una critica
										
										if(espectador == 1) {
											// Creamos una critica DTO vacia
											CriticaDTO criticaDTO = new CriticaDTO(); 
											// Almacenamos el correo del autor de la critica
											criticaDTO.setAutorCritica(usuarioDTO.getCorreoEspectador()); 
											// Pedimos al usuario el titulo de la critica
											System.out.print("Introduce el titulo de la critica: ");
											// Obtenemos el titulo de la critica
											String tituloCritica = entrada.nextLine();
											// Caso de error: Critica ya registrada en la base de datos
											if(gestorCriticas.comprobacionExistenciaTituloCritica(tituloCritica) == true) { System.out.println("El titulo de la critica <" + tituloCritica + "> ya esta registrado en la base de datos"); }
											// Caso de exito: Critica no registrada en la base de datos
											else {
												// Almacenamos el titulo de la critica
												criticaDTO.setTituloCritica(tituloCritica); 
												// Pedimos al usuario la resena de la critica
												System.out.print("Introduce la resena de la critica: ");
												// Obtenemos la resena de la critica
												String resenaCritica = entrada.nextLine();
												// Almacenamos la resena de la critica
												criticaDTO.setResenaCritica(resenaCritica); 
												// Pedimos al usuario el titulo del espectaculo
												System.out.print("Introduce el titulo del espectaculo: ");
												// Obtenemos el titulo del espectaculo
												String tituloEspectaculo = entrada.nextLine();
												// Caso de error: Espectaculo no esta registrado en la base de datos
												if(espectaculos.comprobarExistenciaTituloEspectaculo(tituloEspectaculo) == false ) { System.out.println("Espectaculo no registrado en la base de datos");}
												// Caso de exito: Espectaculo esta registrado en la base de datos
												else {
													int puntuacion = -1;
													// La puntuacion del espectaculo debe estar contenida en el rango [0,10]
													while(puntuacion < 0 || puntuacion > 10) {
														try {
															// Pedimos al usuario la puntuacion del espectaculo
															System.out.print("Introduce una puntuacion para el espectaculo en el rango [0,10]: ");
															// Obtenemos la puntuacion del espectaculo
															puntuacion = entrada.nextInt(); 
															// Limpiamos el buffer de entrada
															entrada = new Scanner(System.in);
															// Caso de error: Puntuacion inferior a 0
															if(puntuacion < 0) {System.out.println("La puntuacion introducida <" + puntuacion + "> es inferior a 0");}
															// Caso de error: Puntuacion superior a 10
															else if(puntuacion > 10) { System.out.println("La puntuacion introducida <" + puntuacion + "> es superior a 10");}
														}
														// Recogemos la excepcion -> Valor introducido por el usuario no es un valor entero
														catch(Exception ex) {System.out.println("El valor introducido no es un valor entero");}
													}
													// Insertamos la critica en la base de datos
													int status = gestorCriticas.insercionCriticaGestor(prop,sql,criticaDTO, tituloEspectaculo, puntuacion); 
													// Caso de error: Critica no ha sido registrada en la base de datos
													if(status == 0) { System.out.println("No se han registrado los datos de la critica en la base de datos");}
													// Caso de exito: Critica registrada en la base de datos
													else { 
														System.out.println("Critica anadida a la base de datos y al gestor de criticas");
													}
												}
											}
										}
										
										// Caso 2: Valorar la utilidad una critica
										
										else if(espectador == 2) {
											// creamos una valoracion de utilidad de la critica vacia
											EvaluacionUtilidadCriticaDTO valoracionCritica = new EvaluacionUtilidadCriticaDTO();
											// Mostramos las criticas registradas en la base de datos
											gestorCriticas.visualizacionCriticas();
											// Pedimos al usuario el identificador de la critica
											System.out.print("Introduce el identificador de la critica: ");
											try {
												// Obtenemos el identificador de la critica
												int identificadorCritica = entrada.nextInt();
												// Limpiamos el buffer de entrada
												entrada = new Scanner(System.in);
												// Caso de error: Critica no registrada en la base de datos
												if(gestorCriticas.comprobacionExistenciaIdentificadorCritica(identificadorCritica) == false) {System.out.println("El identificador de la critica no esta registrado en la base de datos");}
												// Caso de exito: Critica registrada en la base de datos
												else {
													// Creamos una critica vacia
													CriticaDTO critica = new CriticaDTO();
													// Obtenemos la informacion de la critica
													critica = gestorCriticas.obtencionDatosCritica(identificadorCritica); 
													// Caso de error: El usuario es el autor de la critica 
													if(critica.getAutorCritica().equals(usuarioDTO.getCorreoEspectador())) {System.out.println("No se puede valorar la utilidad de una critica propia");}
													// Caso exito: El usuario no es el autor de la critica
													else {
														// Obtenemos la evaluacion de utilidad de la critica por parte del usuario
														valoracionCritica = evaluacionCritica.obtencionValoracionCriticaUsuario(prop, sql, identificadorCritica,usuarioDTO.getIdUsuario());
														// Caso de error: El usuario ya ha evaluado la utilidad de la critica
														if(valoracionCritica.getValoracionCritica() != -1) { System.out.println("El usuario ya ha evaluado la utilidad de la critica"); }
														// Caso de exito: el usuario todavia no ha realizado la evaluacion de utilidad de la critica
														else {
															// Pedimos al usuario su valoracion de utilidad de la critica
															int valoracion = -1;
															// Pedimos la valoracion de utilidad de la critica
															while(valoracion < 0 || valoracion > 10) {
																try {
																	System.out.print("Introduce una valoracion de utilidad en el rango [0,10]: ");
																	// Obtenemos la valoracion de utilidad indicada por el usuario
																	valoracion = entrada.nextInt(); 
																	// Limpiamos el buffer de entrada
																	entrada = new Scanner(System.in); 
																	// Caso de error: Valoracion de utilidad inferior a 0
																	if(valoracion < 0) {System.out.println("La valoracion de utilidad introducida <" + valoracion + "> es inferior a 0");}
																	// Caso de error: Valoracion de utilidad superior a 0
																	else if(valoracion > 10) {System.out.println("La valoracion de utilidad introducida <" + valoracion + "> es superior a 10");}
																}catch(Exception ex) { System.out.println("Se esperaba un valor entero");}
															}
															// Registramos la valoracion de utilidad de la critica
															int status = evaluacionCritica.registrovaloracionUtilidadCritica(prop,sql,critica.getIdentificadorCritica(),usuarioDTO.getIdUsuario(),valoracion);
															// Caso de error: No se ha modificado la base de datos
															if(status == 0) { System.out.println("No se ha registrado la valoracion de utilidad de la critica");	}
															// Caso de exito: Se ha modificado la base de datos
															else {System.out.println("Se ha registrado la valoracion de utilidad de la critica");}
														}
													}
												}
											}
											catch(Exception ex) { 
												System.out.println("La opcion introducida no es un valor entero");
												// Limpiamos el buffer de entrada
												entrada = new Scanner(System.in);
											}
										}
										// Caso 3: Mostrar informacion espectaculos
										else if(espectador == 3) {
											espectaculos.imprimirEspectaculos();
										}
									}catch(Exception ex) {
										System.out.println("Se esperaba un valor entero");
										entrada = new Scanner(System.in);
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
			System.out.println("Se ha producido un error al obtener la informacion del fichero <config.properties> y/o <sql.properties>");
			return;
		}
		
	}

}
