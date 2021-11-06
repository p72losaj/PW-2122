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
		// Inicializamos la clase Properties para el fichero de propiedades
		Properties prop = new Properties();
		// Clase properties para el fichero de propiedades sql
		Properties sql = new Properties(); 
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
		// Obtenemos la ubicacion actual de los ficheros
		String rutaAbsoluta = "./ficheros";
		try {
			// Obtenemos la ruta del fichero de configuracion
			String rutaFicheroConfiguracion = rutaAbsoluta + "/config.properties";
			InputStream is = new FileInputStream(rutaFicheroConfiguracion);
			// Leemos los datos del fichero de configuracion
			prop.load(is);
			// Obtenemos la ruta del fichero de sentencias sql
			String rutaFicheroSQL = rutaAbsoluta + "/sql.properties";
			is = new FileInputStream(rutaFicheroSQL);
			// Leemos los datos del fichero de sentencias sql
			sql.load(is);
			// Creamos una instancia del gestor de criticas
			GestorCriticasDTO gestorCriticas = GestorCriticasDTO.getInstancia();
			// Obtenemos la informacion de las criticas registradas en la base de datos
			gestorCriticas.setListaCriticas(criticaDAO.obtencionCriticas(prop, sql));
			// Obtenemos el resto de datos de la critica
			for(int i=0; i < gestorCriticas.getListaCriticas().size(); i++) {
				gestorCriticas.getListaCriticas().get(i).setTituloEspectaculo(puntuacionEspectaculo.obtencionTituloEspectaculo(prop,sql,gestorCriticas.getListaCriticas().get(i).getIdentificadorCritica())); // Obtenemos el identificador del espectaculo
				gestorCriticas.getListaCriticas().get(i).setPuntuacionEspectaculo(puntuacionEspectaculo.obtencionPuntuacionEspectaculo(prop,sql,gestorCriticas.getListaCriticas().get(i).getIdentificadorCritica())); // Obtenemos la puntuacion del espectaculo
				gestorCriticas.getListaCriticas().get(i).setListaEvaluacionesCritica(evaluacionCritica.obtencionEvaluacionesCritica(prop,sql,gestorCriticas.getListaCriticas().get(i).getIdentificadorCritica())); // Obtenemos las evaluaciones de utilidad de las criticas
			}
			
			// Creamos un gestor de usuarios
			GestorUsuariosDTO usuarios = new GestorUsuariosDTO();
			// Obtenemos los datos de los usuarios
			usuarios.setListaEspectadores(usuarioDAO.obtenerUsuarios(sql,prop));
			// Creamos el gestor de espectaculos
			GestorEspectaculosDTO espectaculos = GestorEspectaculosDTO.getInstancia();
			// Almacenamos en el gestor de espectaculos la informacion de todos los espectaculos
			
			int opcionAcceso = -1;
			// Obtenemos la opcion indicada por el usuario
			while(opcionAcceso != 0) {
				try {
					// Mostramos el menu principal
					menu.MostrarMenuAcceso();
					// Pedimos al usuario una funcionalidad del menu principal
					System.out.print("Introduce una opcion: ");
					// Obtenemos la funcionalidad deseada por el usuario
					opcionAcceso = entrada.nextInt();
					// Limpiamos el buffer de entrada
					entrada = new Scanner(System.in); 
					// Caso 1: Funcionalidad de registro
					if(opcionAcceso == 1) {
						// Creamos un usuario de tipo DTO vacio
						UsuarioDTO usuarioDTO = new UsuarioDTO();
						// Pedimos al usuario su correo
						System.out.println("Introduce su correo: ");
						// Obtenemos el correo del usuario
						String correo = entrada.nextLine();
						// Comprobamos si el correo es valido
						Boolean validezCorreo = usuarioDTO.comprobarValidezCorreo(correo);
						// Caso de error: Correo introducido no es valido
						while(validezCorreo == false) {
							System.out.println("El correo introducido <" + correo + "> no es valido");
							// Pedimos al usuario su correo
							System.out.print("Introduce un correo valido: ");
							// Obtenemos el correo del usuario
							correo = entrada.nextLine();
							// Comprobamos la validez del correo introducido por el usuario
							validezCorreo = usuarioDTO.comprobarValidezCorreo(correo);
						}
						// Almacenamos el correo del usuario
						usuarioDTO.setCorreoEspectador(correo);
						// Comprobamos si el correo esta registrado en la base de datos
						Boolean encontrado = usuarios.comprobarExistenciaCorreoEspectador(correo);
						// Caso error: Correo ya registrado en la base de datos
						if(encontrado == true) {System.out.println("El correo introducido <" + correo + "> ya esta registrado"); }
						// Caso de exito: Correo no registrado
						else {
							// Pedimos al usuario su nombre
							System.out.print("Introduce su nombre: ");
							// Obtenemos el nombre del usuario
							String nombre = entrada.nextLine();
							// Almacenamos el nombre del usuario
							usuarioDTO.setNombreEspectador(nombre); 
							// Pedimos al usuario su primer apellido
							System.out.print("Introduce su primer apellido: ");
							// Obtenemos el primer apellido del usuario
							String apellido1 = entrada.nextLine();
							// Almacenamos el primer apellido del usuario
							usuarioDTO.setPrimerApellidoEspectador(apellido1); 
							// Pedimos el segundo apellido del usuario
							System.out.print("Introduce su segundo apellido: ");
							// Obtenemos el segundo apellido del usuario
							String apellido2 = entrada.nextLine();
							// Almacenamos el segundo apellido del usuario
							usuarioDTO.setSegundoApellidoEspectador(apellido2); 
							// Pedimos el nick del usuario
							System.out.print("Introduce su nombre de usuario: ");
							// Obtenemos el nick del usuario
							String nick = entrada.nextLine();
							// Comprobamos si el nick es unico
							encontrado = usuarios.comprobarExistenciaNickUsuario(nick);
							// Caso de error: Nick ya registrado en la base de datos
							if(encontrado == true) { System.out.println("El nick del usuario ya esta registrado"); }
							// Caso de exito: Nick no registrado en la base de datos
							else {
								// Almacenamos el nick del usuario
								usuarioDTO.setNickEspectador(nick); 
								int rol = 0;
								while(rol == 0) {
									// Mostramos un menu con los roles del usuario
									menu.MostrarRolUsuario(); 
									try {
										// Obtenemos el rol del usuario
										rol = entrada.nextInt();
										// Limpiamos el buffer de entrada
										entrada = new Scanner(System.in); 
										// Caso 1: El rol del usuario es administrador -> Almacenamos rol administrador
										if(rol == 1) { usuarioDTO.setRolUsuario(RolUsuario.administrador);}
										// Caso 2: El rol del usuario es espectador -> Almacenamos rol espectador
										else if(rol == 2) {usuarioDTO.setRolUsuario(RolUsuario.espectador);}
										// Caso de error: Opcion no disponible en el menu de rol de usuario
										else { System.out.println("Opcion no disponible"); }
									
									}
									// Recogemos la excepcion en caso de que se produzca -> Valor introducido por el usuario no es un entero
									catch(Exception ex) {
										System.out.println("El valor esperado es un entero");
										// Limpiamos el buffer de entrada
										entrada = new Scanner(System.in); 
									}
								// Fin de obtencion del rol del usuario
								}
								// Almacenamos los datos en la base de datos
								int status = usuarioDAO.insertarUsuario(usuarioDTO,prop,sql);
								// Caso de exito: Usuario anadido en la base de datos
								if(status != 0) {
									System.out.println("Usuario anadido en la base de datos");
									// Anadimos los datos del usuario al gestor de usuarios
									usuarios.setListaEspectadores(usuarioDAO.obtenerUsuarios(sql,prop));
								}
								// Caso de error: Usuario no registrado en la base de datos
								else {System.out.println("Usuario no se ha anadido a la base de datos");}
							}
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
													int status = criticaDAO.insercionCritica(prop,sql,criticaDTO);
													// Caso de error: Critica no ha sido registrada en la base de datos
													if(status == 0) { System.out.println("No se han registrado los datos de la critica en la base de datos");}
													// Caso de exito: Critica registrada en la base de datos
													else { 
														// Obtenemos el identificador de la critica registrada en la base de datos
														int identificador = criticaDAO.obtencionIdentificadorCritica(prop,sql,criticaDTO.getTituloCritica());
														// Caso de error: identificador de la critica no encontrado
														if(identificador == 0) {
															System.out.println("Identificador de la critica no registrado en la base de datos");
															// Eliminamos los datos de la critica de la base de datos
															status = criticaDAO.eliminacionCritica(prop,sql,criticaDTO.getTituloCritica());
															// Caso de error: Critica no eliminada de la base de datos
															if(status == 0) {System.out.println("Critica no eliminada de la base de datos. Notifique este error al administrador del sistema");}
															// Caso exito: Critica eliminada de la base de datos
															else {System.out.println("Critica eliminada de la base de datos");}
														}
														// Caso de exito: Identificador de la critica encontrado
														else {
															// Almacenamos el identificador de la critica
															criticaDTO.setIdentificadorCritica(identificador);
															// Registramos la puntuacion del espectaculo
															status = puntuacionEspectaculo.creacionRelacion(prop,sql,criticaDTO.getIdentificadorCritica(), tituloEspectaculo, puntuacion);
															// Caso de error: Puntuacion del espectaculo no registrado en la base de datos
															if(status == 0) { 
																System.out.println("Puntuacion del espectaculo no registrada en la base de datos");
																// Eliminamos los datos de la critica
																status = criticaDAO.eliminacionCritica(prop,sql,criticaDTO.getTituloCritica()); 
																// Caso de error: Critica no eliminada de la base de datos
																if(status == 0) {System.out.println("Critica no eliminada de la base de datos. Notifique este error al administrador del sistema");}
																// Caso de exito: Critica eliminada de la base de datos
																else {System.out.println("Critica eliminada de la base de datos");}
															}
															// Caso exito: Puntuacion registrada en la base de datos
															else {
																// Anadimos la critica en el gestor de criticas
																gestorCriticas.insercionCriticaGestor(criticaDTO); 
																System.out.println("Critica registrada en la base de datos y anadida al gestor de criticas");
															}
														}
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
