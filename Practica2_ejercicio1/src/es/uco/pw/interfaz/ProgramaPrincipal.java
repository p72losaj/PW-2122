package es.uco.pw.interfaz;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

import es.uco.pw.dao.espectaculo.EspectaculoDAO;
import es.uco.pw.data.dao.relacion.UsuarioCriticaDAO;
import es.uco.pw.datos.dao.critica.CriticaDAO;
import es.uco.pw.datos.dao.usuario.UsuarioDAO;
import es.uco.pw.interfaz.menus.Menus;
import es.uco.pw.negocio.critica.CriticaDTO;
import es.uco.pw.negocio.critica.GestorCriticasDTO;
import es.uco.pw.negocio.espectaculo.GestorEspectaculosDTO;
import es.uco.pw.negocio.usuario.UsuarioDTO;
import es.uco.pw.negocio.usuario.GestorUsuariosDTO;
import es.uco.pw.negocio.usuario.RolUsuario;

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
		
		UsuarioDAO usuarioDAO = new UsuarioDAO(); // Anadimos un usuario de tipo dao
		
		CriticaDAO criticaDAO = new CriticaDAO(); // Critica de tipo DAO
		
		EspectaculoDAO espectaculoDAO = new EspectaculoDAO(); // Creamos un espectaculo de tipo DAO
		
		Scanner entrada = new Scanner (System.in); // Clase scanner es necesaria para obtener datos por teclado
		
		Menus menu = new Menus(); // clase que gestiona los distintos menus a mostrar al usuario
		
		// Obtenemos la ubicacion actual de los ficheros
		
		String rutaAbsoluta = "./ficheros";
				
		try {
			
			// Obtenemos la ruta del fichero de propiedades
			
			String rutaFicheroPropiedades = rutaAbsoluta + "/config.properties";
			
			InputStream is = new FileInputStream(rutaFicheroPropiedades);
			
			prop.load(is);
			
			// Obtenemos la ruta del fichero sql
			
			String rutaFicheroSQL = rutaAbsoluta + "/sql.properties";
			
			is = new FileInputStream(rutaFicheroSQL);
			
			sql.load(is);
			
			// Creamos el gestor de criticas
			
			GestorCriticasDTO gestorCriticas = GestorCriticasDTO.getInstancia();
			
			// Obtenemos la informacion de las criticas registradas en la base de datos
			
			gestorCriticas.setListaCriticas(criticaDAO.obtencionCriticas(prop, sql));
			
			// Creamos un gestor de espectadores
			
			GestorUsuariosDTO usuarios = new GestorUsuariosDTO();
			
			// Obtenemos los datos de los usuarios
			
			usuarios.setListaEspectadores(usuarioDAO.obtenerUsuarios(sql,prop));
			
			// Creamos el gestor de espectaculos
			
			GestorEspectaculosDTO espectaculos = GestorEspectaculosDTO.getInstancia();
						
			// Almacenamos en el gestor de espectaculos la informacion de todos los espectaculos
			
			espectaculos.setListaEspectaculos(espectaculoDAO.obtencionEspectaculos(prop, sql));
			
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
						
						// Obtenemos el correo del usuario
						
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
						
						Boolean encontrado = usuarios.comprobarExistenciaCorreoEspectador(correo);
						
						// Correo ya registrado
						
						if(encontrado == true) {
							System.out.println("El correo introducido <" + correo + "> ya esta registrado");
						}
						
						// Correo no registrado
						
						else {
							// Obtenemos el nombre del usuario
							
							System.out.print("Introduce su nombre: ");
							
							String nombre = entrada.nextLine();
							
							usuarioDTO.setNombreEspectador(nombre); // Almacenamos el nombre del usuario
							
							// Obtenemos el primer apellido del usuario
							
							System.out.print("Introduc)e su primer apellido: ");
							
							String apellido1 = entrada.nextLine();
							
							usuarioDTO.setPrimerApellidoEspectador(apellido1); // Almacenamos el primer apellido del usuario
							
							// Obtenemos el segundo apellido del usuario
							
							System.out.print("Introduce su segundo apellido: ");
							
							String apellido2 = entrada.nextLine();
							
							usuarioDTO.setSegundoApellidoEspectador(apellido2); // Almacenamos el segundo apellido del usuario
							
							// Obtenemos el nick del usuario
							
							System.out.print("Introduce su nombre de usuario: ");
							
							String nick = entrada.nextLine();
							
							// Comprobamos si el nick es unico
							
							encontrado = usuarios.comprobarExistenciaNickUsuario(nick);
							
							// Nick encontrado
							
							if(encontrado == true) {
								System.out.println("El nick del usuario ya esta registrado");
							}
							
							// Nick no encontrado
							
							else {
								usuarioDTO.setNickEspectador(nick); // Almacenamos el nick del usuario
								
								// Obtenemos el rol del usuario
								
								int rol = 0;
								
								while(rol == 0) {
									menu.MostrarRolUsuario(); // Mostramos un menu con los roles del usuario
									try {
										rol = entrada.nextInt();
										entrada = new Scanner(System.in); // Limpiamos el buffer
										// El rol del usuario es administrador
										if(rol == 1) {
											usuarioDTO.setRolUsuario(RolUsuario.administrador);
										}
										// El rol del usuario es espectador
										else if(rol == 2) {
											usuarioDTO.setRolUsuario(RolUsuario.espectador);
										}
										// Opcion no disponible
										else {
											System.out.println("Opcion no disponible");
											rol = 0;
										}
									}catch(Exception ex) {
										System.out.println("El valor esperado es un entero");
										entrada = new Scanner(System.in); // Limpiamos el buffer
									}
									
								}
								
								// Almacenamos los datos en la base de datos
								
								int status = usuarioDAO.insertarUsuario(usuarioDTO,prop,sql);
								
								// Usuario anadido en la base de datos
								if(status != 0) {
									System.out.println("Usuario anadido en la base de datos");
									usuarios.setListaEspectadores(usuarioDAO.obtenerUsuarios(sql,prop));
								}
								
							}
						}
						
					}
					
					// El usuario ha elegido la opcion de identificarse en el sistema
				
					else if(opcionAcceso == 2) {
						// Pedimos al usuario su correo
						System.out.print("Introduce su correo para acceder a su cuenta: ");
						String correo = entrada.nextLine();
						// Comprobamos si el correo esta registrado
						Boolean encontrado = usuarios.comprobarExistenciaCorreoEspectador(correo);
						// Correo no registrado
						if(encontrado == false) {
							System.out.println("Correo no registrado en la base de datos");
						}
						// Correo registrado
						else {
							UsuarioDTO usuarioDTO = new UsuarioDTO();
							usuarioDTO = usuarios.obtenerDatosUsuario(correo); // Obtenemos los datos del usuario
							// Comprobamos si el usuario es administrador o espectador
							// Caso 1: Usuario administrador
							if(usuarioDTO.getRolUsuario().equals(RolUsuario.administrador)) {
								int administrador = -1;
								while(administrador != 0) {
									// Mostramos el menu del administrador
									menu.MostrarMenuAdministrador();
									try {
										// Obtenemos la funcionalidad deseada por el usuario
										administrador = entrada.nextInt();
										entrada = new Scanner(System.in); // Limpiamos el buffer

										// Caso 1: Dar de alta un espectaculo
										
										// Caso 2: Cancelar un espectaculo ( todas las sesiones o una en particular)
										
										// Caso 3: Actualizar los datos de un espectáculo
										
										// Caso 4: Contabilizar la venta de entradas para una sesión de un espectáculo
										
										// Caso 5: Consultar las localidades disponibles para un espectáculo, dada una fecha de  representación
										
										// caso 6: Búsqueda de espectáculos por título o por categoría
										
										// Caso 7: Búsqueda de próximos espectáculos con entradas disponibles, indicando o no una  categoría específica
										
										// Caso 8: Publicar una crítica para un espectáculo que ya se ha celebrado
										
										// Caso 9: Consultar las críticas de un espectáculo, dado su título

										// Caso 10: Eliminar críticas de un espectáculo, por parte del usuario que la creó
										
										// Caso 11: Valorar la utilidad de una crítica publicada por otro usuario
									}catch(Exception ex) {
										System.out.println("Se esperaba un valor entero");
									}
									
								}
							}
							// Caso 2: Usuario espectador
							else {
								int espectador = 1;
								while(espectador != 0) {
									menu.MostrarMenuEspectador(); // Mostramos el menu del espectador
									try {
										espectador = entrada.nextInt(); // Obtenemos la funcionalidad del espectador
										entrada = new Scanner(System.in); // Limpiamos el buffer de entrada
										// Caso 1: Crear una critica
										if(espectador == 1) {
											CriticaDTO criticaDTO = new CriticaDTO(); // Creamos una critica vacia
											criticaDTO.setAutorCritica(usuarioDTO.getCorreoEspectador()); // Almacenamos el correo del autor de la critica
											// Pedimos al usuario el titulo de la critica
											System.out.print("Introduce el titulo de la critica: ");
											String tituloCritica = entrada.nextLine();
											if(gestorCriticas.comprobacionExistenciaTituloCritica(tituloCritica) == true) { // Titulo de critica ya registrado
												System.out.println("El titulo de la critica <" + tituloCritica + "> ya esta registrado en la base de datos");
											}
											else { // Critica no registrada en la base de datos
												criticaDTO.setTituloCritica(tituloCritica); // Almacenamos el titulo de la critica
												// Pedimos al usuario la resena de la critica
												System.out.print("Introduce la resena de la critica: ");
												String resenaCritica = entrada.nextLine();
												criticaDTO.setResenaCritica(resenaCritica); // Almacenamos la resena de la critica
												// Pedimos al usuario el titulo del espectaculo
												System.out.print("Introduce el titulo del espectaculo: ");
												String tituloEspectaculo = entrada.nextLine();
												if(espectaculos.comprobarExistenciaTituloEspectaculo(tituloEspectaculo) == false ) {
													// Espectaculo no  registrado en la base de datos
													System.out.println("Espectaculo no registrado en la base de datos");
												}
												else {
													// Pedimos al usuario la puntuacion del espectaculo
													int puntuacion = -1;
													while(puntuacion < 0 || puntuacion > 10) {
														try {
															System.out.print("Introduce una puntuacion para el espectaculo en el rango [0,10]: ");
															puntuacion = entrada.nextInt(); // Obtenemos la puntuacion del espectaculo
															entrada = new Scanner(System.in); // Limpiamos el buffer de entrada
															if(puntuacion < 0) { // Error. Puntuacion inferior a 0
																System.out.println("La puntuacion introducida <" + puntuacion + "> es inferior a 0");
															}
															else if(puntuacion > 10) { // Error. Puntuacion superior a 10
																System.out.println("La puntuacion introducida <" + puntuacion + "> es superior a 10");
															}
														} catch(Exception ex) {
															System.out.println("El valor introducido no es un valor entero");
														}
													}
													int status = criticaDAO.insercionCritica(prop,sql,criticaDTO,tituloEspectaculo,puntuacion); // Insertamos la critica en la base de datos
													if(status == 0) { // Critica no registrada en la base de datos
														System.out.println("No se han registrado los datos de la critica en la base de datos");
													}
													else { // Critica registrada en la base de datos
														gestorCriticas.insercionCriticaGestor(criticaDTO); // Anadimos la critica en el gestor de criticas
														System.out.println("Critica registrada en la base de datos y anadida al gestor de criticas");
													}
												}
												
											}
										}
										// Caso 2: Valorar la utilidad una critica
										else if(espectador == 2) {
											UsuarioCriticaDAO relacion = new UsuarioCriticaDAO();
											// Mostramos las criticas al usuario
											gestorCriticas.visualizacionCriticas();
											// Pedimos al usuario el identificador de la critica
											System.out.print("Introduce el identificador de la critica: ");
											int identificadorCritica = entrada.nextInt();
											entrada = new Scanner(System.in); // Limpiamos el buffer de entrada
											// Comprobamos si el identificador de la critica esta registrado
											if(gestorCriticas.comprobacionExistenciaIdentificadorCritica(identificadorCritica) == false) {
												System.out.println("El identificador de la critica no esta registrado en la base de datos");
											}
											else {
												CriticaDTO critica = new CriticaDTO();
												critica = gestorCriticas.obtencionDatosCritica(identificadorCritica); // Obtenemos la informacion de la critica
												// Comprobamos si el autor de la critica coincide con el usuario registrado
												if(critica.getAutorCritica().equals(usuarioDTO.getCorreoEspectador())) {
													System.out.println("No se puede valorar la utilidad de una critica propia");
												}
												else {
													// Pedimos al usuario su valoracion de utilidad de la critica
												int valoracion = -1;
												while(valoracion < 0 || valoracion > 10) {
													try {
														System.out.print("Introduce una valoracion de utilidad en el rango [0,10]: ");
														valoracion = entrada.nextInt(); // Obtenemos la valoracion de utilidad indicada por el usuario
														entrada = new Scanner(System.in); // Limpiamos el buffer de entrada
														if(valoracion < 0) {
															System.out.println("La valoracion de utilidad introducida <" + valoracion + "> es inferior a 0");
														}
														else if(valoracion > 10) {
															System.out.println("La valoracion de utilidad introducida <" + valoracion + "> es superior a 10");
														}
													}catch(Exception ex) {
														System.out.println("Se esperaba un valor entero");
													}
												}
												int status = relacion.valoracionUtilidadCritica(prop,sql,critica.getIdentificadorCritica(),usuarioDTO.getIdUsuario(),valoracion); // Registramos la valoracion de utilidad de la critica
												// Comprobamos si se ha modificador la base de datos
												if(status == 0) { // No se ha modificado la base de datos
													System.out.println("No se ha registrado la valoracion de utilidad de la critica");
												}
												else {// Se ha modificador la base de datos
													System.out.println("Se ha registrado la valoracion de utilidad de la critica");
												}
												}
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
