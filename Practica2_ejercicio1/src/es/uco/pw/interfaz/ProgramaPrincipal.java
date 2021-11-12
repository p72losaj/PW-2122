package es.uco.pw.interfaz;

import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Properties;
import java.util.Scanner;

import es.uco.pw.datos.dao.relacion.UsuarioCriticaDAO;
import es.uco.pw.datos.dao.usuario.UsuarioDAO;
import es.uco.pw.interfaz.menus.Menus;
import es.uco.pw.negocio.critica.GestorCriticasDTO;
import es.uco.pw.negocio.espectaculo.CategoriaEspectaculo;
import es.uco.pw.negocio.espectaculo.GestorEspectaculosDTO;
import es.uco.pw.negocio.usuario.GestorUsuariosDTO;

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
			if(gestorCriticas.getListaCriticas().size() == 0) {
				return;
			}
			/*
			 * GESTOR DE USUARIOS
			 */
			GestorUsuariosDTO usuarios = new GestorUsuariosDTO(); // Creamos un gestor de usuarios
			usuarios.setListaEspectadores(usuarioDAO.obtenerUsuarios(sql,prop)); // Obtenemos los datos de los usuarios
			if(usuarios.getListaEspectadores().size() == 0) {
				return;
			}
			/*
			 * GESTOR DE ESPECTACULOS
			 */
			GestorEspectaculosDTO espectaculos = GestorEspectaculosDTO.getInstancia(); // Creamos el gestor de espectaculos
			espectaculos.obtenerEspectaculosRegistrados(prop, sql); // Obtenemos los datos de los espectaculos registrados en la base de datos
			if(espectaculos.getEspectaculos().size() == 0) {
				return;
			}
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
					
					/*
					 * INICIO DE SESION EN EL SISTEMA
					 */
					
					else if(opcionAcceso == 2) {
						/*
						 * COMPROBACION DE LOS DATOS DE INICIO DE SESION DEL USUARIO
						 */
						System.out.print("Introduce su correo para acceder a su cuenta: "); // Pedimos al usuario su correo
						String correoUsuario = entrada.nextLine(); // Obtenemos el correo del usuario
						Boolean encontrado = usuarios.comprobarExistenciaCorreoEspectador(correoUsuario); // Comprobamos si el correo esta registrado
						/*
						 * DATOS DE ACCESO DEL USUARIO INCORRECTOS
						 */
						if(encontrado == false) { System.out.println("Correo no registrado en la base de datos"); }
						/*
						 * DATOS DE ACCESO DEL USUARIO CORRECTOS
						 */
						else {
							int idUsuario = usuarios.obtencionIdentificadorUsuario(correoUsuario); // Obtencion del identificador del usuario
							/*
							 * ROL DEL USUARIO
							 */
							String rol = usuarios.obtencionRolUsuario(correoUsuario);
							/*
							 * USUARIO ADMINISTRADOR
							 */
							if(rol.equals("administrador")) {
								/*
								 * MENU DE ADMINISTRADOR
								 */
								int administrador = -1; // Opcion por defecto del menu de administrador
								while(administrador != 0) {
									entrada = new Scanner(System.in);
									menu.MostrarMenuAdministrador(); // Mostramos el menu del administrador
									try {
										administrador = entrada.nextInt(); // Obtenemos la funcionalidad deseada por el usuario
										entrada = new Scanner(System.in); // Limpiamos el buffer
										/*
										 * DAR DE ALTA UN ESPECTACULO
										 */
										if(administrador == 1) {
											/*
											 * OBTENCION DEL TITULO DEL ESPECTACULO
											 */
											String tituloEspectaculo=null; // Titulo del espectaculo
											System.out.print("Introduce el titulo del espectaculo: ");
											tituloEspectaculo = entrada.nextLine();
											/*
											 * OBTENCION DE LA DESCRIPCION DEL DESPECTACULO
											 */
											String descripcionEspectaculo=null; // Descripcion del espectaculo
											System.out.print("Introduce la descripcion del espectaculo: ");
											descripcionEspectaculo = entrada.nextLine();
											/*
											 * OBTENCION DE LA CATEGORIA DEL ESPECTACULO  
											 */
											menu.mostrarCategoriaEspectaculo();
											CategoriaEspectaculo categoriaEspectaculo = null;
											int opcionCategoria = 0;
											while(opcionCategoria == 0) {
												try {
													opcionCategoria = entrada.nextInt();
													entrada = new Scanner(System.in); // Limpiamos el buffer de entrada
													/*
													 * CATEGORIA DEL ESPECTACULO ES UNA OBRA DE TEATRO
													 */
													if(opcionCategoria == 1) { categoriaEspectaculo = CategoriaEspectaculo.obraTeatro;}
													/*
													 * CATEGORIA DEL ESPECTACULO ES UN MONOLOGO
													 */
													else if(opcionCategoria==2) { categoriaEspectaculo = CategoriaEspectaculo.monologo;}
													/*
													 * CATEGORIA DEL ESPECTACULO ES UN CONCIERTO
													 */
													else if(opcionCategoria==3) { categoriaEspectaculo = CategoriaEspectaculo.concierto;}
													/*
													 * CATEGORIA DEL ESPECTACULO NO DISPONIBLE
													 */
													else { 
														System.out.println("Categoria del espectaculo no disponible");
														opcionCategoria = 0;
													}
												}catch(Exception ex) {
													System.out.println("Se ha producido un error al obtener la categoria del espectaculo. Se esperaba un valor entero");
													entrada = new Scanner(System.in);
													}
											}
											/*
											 * OBTENCION DEL TIPO DE ESPECTACULO
											 */
											String tipoEspectaculo=null;
											menu.MostrarTiposEspectaculos();
											int opcionTipo = 0;
											while(opcionTipo == 0) {
												try {
													opcionTipo = entrada.nextInt();
													entrada = new Scanner(System.in); // Limpiamos el buffer de entrada
													/*
													 * TIPO DEL ESPECTACULO ES PUNTUAL
													 */
													if(opcionTipo == 1) {tipoEspectaculo = "puntual";}
													/*
													 * TIPO DEL ESPECTACULO ES MULTIPLE
													 */
													else if(opcionTipo == 2) {tipoEspectaculo = "multiple";}
													/*
													 * TIPO DEL ESPECTACULO ES TEMPORADA
													 */
													else if(opcionTipo==3) {tipoEspectaculo = "temporada";}
													/*
													 * TIPO DEL ESPECTACULO NO DISPONIBLE
													 */
													else { 
														System.out.println("Opcion seleccionada no disponible");
														opcionTipo = 0;
													}
												}catch(Exception ex) {
													System.out.println("Se ha producido un error al obtener el tipo del espectaculo. Se esperaba un valor entero");
													entrada = new Scanner(System.in); // Limpiamos el buffer
												}
											}
											/*
											 * OBTENEMOS EL AFORO DE LOCALIDADES DEL ESPECTACULO
											 */
											int aforoLocalidades = -1;
											while(aforoLocalidades < 0) {
												try {
													System.out.print("Introduce el aforo de localidades del espectaculo: ");
													aforoLocalidades = entrada.nextInt();
													entrada = new Scanner(System.in);
													if(aforoLocalidades < 0) {
														System.out.println("El numero de localidades debe ser positivo o 0");
													}
												}catch(Exception ex) {
													System.out.println("Se ha producido un error al obtener el aforo de localidades del espectaculo. Se esperaba un valor entero");
													entrada = new Scanner(System.in);
												}
											}
											/*
											 * OBTENEMOS EL NUMERO DE VENTAS DEL ESPECTACULO
											 */
											int ventasEspectaculo = -1;
											while(ventasEspectaculo < 0) {
												try {
													System.out.print("Introduce el numero de ventas del espectaculo: ");
													ventasEspectaculo = entrada.nextInt();
													entrada = new Scanner(System.in);
													if(ventasEspectaculo < 0) {
														System.out.println("El numero de ventas del espectaculo debe ser positivo o 0");
													}
													else if(ventasEspectaculo > aforoLocalidades) {
														System.out.println("El numero de ventas del espectaculo debe ser inferior o igual a: " + aforoLocalidades);
														ventasEspectaculo = -1;
													}
												}catch(Exception ex) {
													System.out.println("Se ha producido un error al obtener el numero de ventas del espectaculo. Se esperaba un valor entero");
													entrada = new Scanner(System.in);
												}
											}
											/*
											 * ESPECTACULO PUNTUAL
											 */
											if(tipoEspectaculo.equals("puntual")) {
												/*
												 * OBTENEMOS EL ANO DE LA FECHA DE LA SESION
												 */
												int anoPuntual = 0;
												System.out.print("Introduce el ano de la sesion: ");
												anoPuntual = entrada.nextInt();
												entrada = new Scanner(System.in);
												/*
												 * OBTENCION DEL MES DE LA SESION
												 */
												int mesPuntual = -1;
												while(mesPuntual < 0) {
													try {
														System.out.print("Introduce el mes de la sesion: ");
														mesPuntual = entrada.nextInt();
														entrada = new Scanner(System.in);
														if(mesPuntual < 1 || mesPuntual > 12) {
															System.out.println("El valor del mes debe estar en el rango [1,12] ");
															mesPuntual = -1;
														}
													}catch(Exception ex) {
														System.out.println("Se esperaba un valor entero al obtener el mes de la sesion");
														entrada = new Scanner(System.in);
													}
												}
												
												/*
												 * OBTENCION DEL DIA DE LA SESION
												 */
												
												int diaPuntual = -1;
												
												while(diaPuntual < 0) {
													try {
														System.out.print("Introduce el dia de la sesion: ");
														diaPuntual = entrada.nextInt();
														entrada = new Scanner(System.in);
														if(diaPuntual < 0) {
															System.out.println("Se esperaba un dia 0 o positivo");
															diaPuntual = 0;
														}
													}catch(Exception ex) {
														System.out.println("Se esperaba un valor entero al obtener el dia de la sesion");
														entrada = new Scanner(System.in);
													}
												}
												/*
												 * OBTENEMOS LA HORA DE LA SESION
												 */
												int horaPuntual = -1;
												System.out.println("Dada la hora de sesion -> hh:mm");
												while(horaPuntual < 0) {
													try {
														System.out.print("Introduce el valor de <hh>: ");
														horaPuntual = entrada.nextInt();
														entrada = new Scanner(System.in);
														if(horaPuntual < 0 || horaPuntual > 23) {
															System.out.println("Introduce una hora en el rango [0,23]");
															horaPuntual = -1;
														}
													}catch(Exception ex) {
														System.out.println("Se esperaba un valor entero al obtener el valor de <hh>");
														entrada = new Scanner(System.in);
													}
												}
												/*
												 * OBTENEMOS LOS MINUTOS DE LA SESION
												 */
												int minutosPuntual = -1;
												while(minutosPuntual < 0) {
													try {
														System.out.print("Introduce el valor de <mm>: ");
														minutosPuntual = entrada.nextInt();
														entrada = new Scanner(System.in);
														if(minutosPuntual < 0 || minutosPuntual > 59) {
															System.out.println("Se esperaba un valor de minutos en el rango [0,59]");
															minutosPuntual = -1;
														}
													}catch(Exception ex) {
														System.out.println("Se esperaba un valor entero al obtener el valor de <mm>");
														entrada = new Scanner(System.in);
													}
												}
												
												/*
												 * DAMOS DE ALTA AL ESPECTACULO
												 */
												System.out.println(espectaculos.darAltaEspectaculo(prop, sql, tituloEspectaculo, descripcionEspectaculo, categoriaEspectaculo, "puntual", aforoLocalidades, ventasEspectaculo, anoPuntual, mesPuntual, diaPuntual, horaPuntual, minutosPuntual, 0, 0, "", "", 0, 0));
												//espectaculos.darAltaEspectaculo(prop, sql, tituloEspectaculo, descripcionEspectaculo, categoriaEspectaculo, tipoEspectaculo, aforoLocalidades, ventasEspectaculo, anoPuntual, mesPuntual, diaPuntual, horaPuntual, minutosPuntual, horaMultiple1, minutosMultiple1, diaSemanaMultiple1, diaSemanaMultiple2, horaMultiple2, minutosMultiple2)
											}
											/*
											 * ESPECTACULO MULTIPLE
											 */
											else if(tipoEspectaculo.equals("multiple")) {
												/*
												 * OBTENEMOS LA HORA DE LA PRIMERA SESION DEL ESPECTACULO
												 */
												int horaMultiple1 = 0;
												System.out.println("Dada la hora de sesion -> hh:mm");
												while(horaMultiple1 < 0) {
													try {
														System.out.print("Introduce el valor de <hh>: ");
														horaMultiple1 = entrada.nextInt();
														entrada = new Scanner(System.in);
														if(horaMultiple1 < 0 || horaMultiple1 > 23) {
															System.out.println("Introduce una hora en el rango [0,23]");
															horaMultiple1 = -1;
														}
													}catch(Exception ex) {
														System.out.println("Se esperaba un valor entero al obtener el valor de <hh>");
														entrada = new Scanner(System.in);
													}
												}
												/*
												 * OBTENEMOS LA HORA DE LA SEGUNDA SESION
												 */
												int horaMultiple2 = 0;
												System.out.println("Dada la hora de sesion -> hh:mm");
												while(horaMultiple2 < 0) {
													try {
														System.out.print("Introduce el valor de <hh>: ");
														horaMultiple2 = entrada.nextInt();
														entrada = new Scanner(System.in);
														if(horaMultiple2 < 0 || horaMultiple2 > 23) {
															System.out.println("Introduce una hora en el rango [0,23]");
															horaMultiple2 = -1;
														}
													}catch(Exception ex) {
														System.out.println("Se esperaba un valor entero al obtener el valor de <hh>");
														entrada = new Scanner(System.in);
													}
												}
												/*
												 * OBTENEMOS LOS MINUTOS DE LA PRIMERA SESION
												 */
												int minutosMultiple1 = 0;
												while(minutosMultiple1 < 0) {
													try {
														System.out.print("Introduce el valor de <mm>: ");
														minutosMultiple1 = entrada.nextInt();
														entrada = new Scanner(System.in);
														if(minutosMultiple1 < 0 || minutosMultiple1 > 59) {
															System.out.println("Se esperaba un valor de minutos en el rango [0,59]");
															minutosMultiple1 = -1;
														}
													}catch(Exception ex) {
														System.out.println("Se esperaba un valor entero al obtener el valor de <mm>");
														entrada = new Scanner(System.in);
													}
												}
												/*
												 * OBTENEMOS LOS MINUTOS DE LA SEGUNDA SESION 
												 */
												int minutosMultiple2 = 0;
												while(minutosMultiple2 < 0) {
													try {
														System.out.print("Introduce el valor de <mm>: ");
														minutosMultiple2 = entrada.nextInt();
														entrada = new Scanner(System.in);
														if(minutosMultiple2 < 0 || minutosMultiple2 > 59) {
															System.out.println("Se esperaba un valor de minutos en el rango [0,59]");
															minutosMultiple2 = -1;
														}
													}catch(Exception ex) {
														System.out.println("Se esperaba un valor entero al obtener el valor de <mm>");
														entrada = new Scanner(System.in);
													}
												}
												/*
												 * OBTENEMOS EL DIA DE LA SEMANA DE AMBAS SESIONES
												 */
												String diaSemanaMultiple1 = null, diaSemanaMultiple2 = null;
												int opcionSemana1 = 0, opcionSemana2 = 0;
												while(opcionSemana1 == 0 || opcionSemana2 == 0) {
													menu.MostrarSemana(); // Mostramos el menu de dias de la semana
													if(opcionSemana1 == 0) {
														try {
															opcionSemana1 = entrada.nextInt();
															entrada = new Scanner(System.in);
															if(opcionSemana1 == 1) {
																diaSemanaMultiple1 = "lunes";
															}
															else if(opcionSemana1 == 2) {
																diaSemanaMultiple1 = "martes";
															}
															else if(opcionSemana1 == 3) {
																diaSemanaMultiple1 = "miercoles";
															}
															else if(opcionSemana1 == 4) {
																diaSemanaMultiple1 = "jueves";
															}
															else if(opcionSemana1 == 5) {
																diaSemanaMultiple1 = "viernes";
															}
															else if(opcionSemana1 == 6) {
																diaSemanaMultiple1 = "sabado";
															}
															else if(opcionSemana1 == 7) {
																diaSemanaMultiple1 = "domingo";
															}
															else {
																System.out.println("Dia de la semana no valido");
																opcionSemana1 = 0;
															}
														}catch(Exception ex) {
															System.out.println("La opcion del dia de la semana de la sesion 1 debe ser un valor entero");
															entrada = new Scanner(System.in);
														}
													}
													else {
														try {
															opcionSemana2 = entrada.nextInt();
															entrada = new Scanner(System.in);
															if(opcionSemana2 == 1) {
																diaSemanaMultiple2 = "lunes";
															}
															else if(opcionSemana2 == 2) {
																diaSemanaMultiple2 = "martes";
															}
															else if(opcionSemana2 == 3) {
																diaSemanaMultiple2 = "miercoles";
															}
															else if(opcionSemana2 == 4) {
																diaSemanaMultiple2 = "jueves";
															}
															else if(opcionSemana2 == 5) {
																diaSemanaMultiple2 = "viernes";
															}
															else if(opcionSemana2 == 6) {
																diaSemanaMultiple2 = "sabado";
															}
															else if(opcionSemana2 == 7) {
																diaSemanaMultiple2 = "domingo";
															}
															else {
																System.out.println("Dia de la semana no valido");
																opcionSemana2 = 0;
															}
														}catch(Exception ex) {
															System.out.println("La opcion del dia de la semana de la sesion 2 debe ser un valor entero");
															entrada = new Scanner(System.in);
														}
													}
													
												}
												/*
												 * OBTENEMOS EL DIA DE LA SEMANA DE LA SEGUNDA SESION
												 */
												/*
												 * DAMOS DE ALTA EL ESPECTACULO
												 */
												String cadena = espectaculos.darAltaEspectaculo(prop, sql, tituloEspectaculo, descripcionEspectaculo, categoriaEspectaculo, tipoEspectaculo, aforoLocalidades, ventasEspectaculo, 0, 0, 0, 0, 0, horaMultiple1, minutosMultiple1, diaSemanaMultiple1, diaSemanaMultiple2, horaMultiple2, minutosMultiple2);
												System.out.println(cadena);
											}
											/*
											 * ESPECTACULO TEMPORADA
											 */
											else if(tipoEspectaculo.equals("temporada")) {
												
											}
										}
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
										else if(administrador == 11) {
											/*
											 * MOSTRAMOS LAS CRITICAS REGISTRADAS EN EL GESTOR DE USUARIOS
											 */
											for(int i=0; i<gestorCriticas.getListaCriticas().size(); i++) { System.out.println(gestorCriticas.getListaCriticas().get(i).mostrarCritica());}
											/*
											 * OBTENEMOS EL IDENTIFICADOR DE LA CRITICA
											 */
											System.out.print("Introduce el identificador de la critica: ");
											int identificadorCritica = 0;
											try {
												identificadorCritica = entrada.nextInt();
												entrada = new Scanner(System.in); // Limpiamos el buffer de entrada
											}catch(Exception ex) {System.out.println("Al elegir el identificador de la critica, se esperaba un valor entero"); }
											/*
											 * VALORACION DE UTILIDAD DE LA CRITICA
											 */
											int valoracion = -1;
											while(valoracion < 0 || valoracion > 10) {
												try {
													System.out.print("Introduce una valoracion de utilidad en el rango [0,10]: ");
													valoracion = entrada.nextInt();
													entrada = new Scanner(System.in); // Limpiamos el buffer de entrada
													/*
													 * VALORACION DE UTILIDAD INFERIOR A 0
													 */
													if(valoracion < 0) {System.out.println("La valoracion de utilidad introducida <" + valoracion + "> es inferior a 0");}
													/*
													 * VALORACION DE UTILIDAD SUPERIOR A 10
													 */
													else if(valoracion > 10) {System.out.println("La valoracion de utilidad introducida <" + valoracion + "> es superior a 10");}
												}catch(Exception ex) { System.out.println("La valoracion de utilidad de la critica debe ser un entero");}
											}
											/*
											 * REALIZAMOS LA VALORACION DE UTILIDAD DE LA CRITICA
											 */
											if(identificadorCritica != 0) { System.out.println(gestorCriticas.valoracionUtilidadCritica(prop,sql,identificadorCritica,correoUsuario,idUsuario,valoracion ));}
										}
										entrada = new Scanner(System.in);
									}catch(Exception ex) {ex.getCause(); }
								}
							}
						
							/*
							 * USUARIO ESPECTADOR
							 */
							
							else{
								/*
								 * MENU DE FUNCIONALIDADES DEL ESPECTADOR
								 */
								int espectador = -1; // Opcion por defecto de las funcionalidades del menu
								entrada = new Scanner(System.in);  // Limpiamos el buffer de entrada
								while(espectador != 0) {
									menu.MostrarMenuEspectador(); // Mostramos el menu del espectador 
									try {
										espectador = entrada.nextInt(); // Obtenemos la funcionalidad del espectador 
										entrada = new Scanner(System.in); // Limpiamos el buffer de entrada
									}catch(Exception ex) { System.out.println("Se esperaba un valor entero al obtener la funcionalidad del espectador");}
										/*
										 * CREACION DE UNA NUEVA CRITICA
										 */
										
										if(espectador == 1) {			
											/*
											 * OBTENCION DE LA FECHA ACTUAL
											 */
											LocalDate fechaActual = LocalDate.now();
											/*
											 * OBTENCION DE LOS DATOS DE LA CRITICA
											 */
											System.out.print("Introduce el titulo de la critica: "); // Pedimos al usuario el titulo de la critica
											String titulo = entrada.nextLine(); // Obtenemos el titulo de la critica
											System.out.print("Introduce la resena de la critica: "); // Pedimos al usuario la resena de la critica
											String resenaCritica = entrada.nextLine(); // Obtenemos la resena de la critica
											/*
											 * OBTENCION DATOS DEL ESPECTACULO
											 */
											System.out.print("Introduce el titulo del espectaculo: "); // Pedimos al usuario el titulo del espectaculo
											String tituloEspectaculo = entrada.nextLine();// Obtenemos el titulo del espectaculo
											// Puntuacion del espectaculo
											int puntuacion = -1; 
											try {
												System.out.print("Introduce una puntuacion del espectaculo: "); // Pedimos la puntuacion del espectaculo
												puntuacion = entrada.nextInt(); // Obtenemos la puntuacion del espectaculo
												entrada = new Scanner(System.in); // Limpiamos el buffer
											}catch(Exception ex) {
												System.out.println("La puntuacion del espectaculo debe ser un entero");
											}
											/*
											 * Puntuacion del espectaculo debe ser distinta a -1
											 */
											if(puntuacion != -1) {
												/*
												 * REGISTRO DE LA CRITICA
												 */
												String registroCritica = gestorCriticas.registroCritica(prop, sql, correoUsuario, titulo,resenaCritica,tituloEspectaculo,puntuacion,fechaActual.toString()); // Realizamos el registro de la critica
												System.out.println(registroCritica); // Mostramos el estado del registro de la critica	
											}
										}
										
										/* VALORACION UTILIDAD DE UNA CRITICA
										 */
										
										else if(espectador == 2) {
											/*
											 * MOSTRAMOS LAS CRITICAS REGISTRADAS EN EL GESTOR DE USUARIOS
											 */
											for(int i=0; i<gestorCriticas.getListaCriticas().size(); i++) { System.out.println(gestorCriticas.getListaCriticas().get(i).mostrarCritica());}
											/*
											 * OBTENEMOS EL IDENTIFICADOR DE LA CRITICA
											 */
											System.out.print("Introduce el identificador de la critica: ");
											int identificadorCritica = 0;
											try {
												identificadorCritica = entrada.nextInt();
												entrada = new Scanner(System.in); // Limpiamos el buffer de entrada
											}catch(Exception ex) {System.out.println("Al elegir el identificador de la critica, se esperaba un valor entero"); }
											/*
											 * VALORACION DE UTILIDAD DE LA CRITICA
											 */
											int valoracion = -1;
											while(valoracion < 0 || valoracion > 10) {
												try {
													System.out.print("Introduce una valoracion de utilidad en el rango [0,10]: ");
													valoracion = entrada.nextInt();
													entrada = new Scanner(System.in); // Limpiamos el buffer de entrada
													/*
													 * VALORACION DE UTILIDAD INFERIOR A 0
													 */
													if(valoracion < 0) {System.out.println("La valoracion de utilidad introducida <" + valoracion + "> es inferior a 0");}
													/*
													 * VALORACION DE UTILIDAD SUPERIOR A 10
													 */
													else if(valoracion > 10) {System.out.println("La valoracion de utilidad introducida <" + valoracion + "> es superior a 10");}
												}catch(Exception ex) { System.out.println("La valoracion de utilidad de la critica debe ser un entero");}
											}
											/*
											 * REALIZAMOS LA VALORACION DE UTILIDAD DE LA CRITICA
											 */
											if(identificadorCritica != 0) { System.out.println(gestorCriticas.valoracionUtilidadCritica(prop,sql,identificadorCritica,correoUsuario,idUsuario,valoracion ));}
											entrada = new Scanner(System.in);
										}
										// Caso 3: Mostrar informacion espectaculos
										else if(espectador == 3) { espectaculos.imprimirEspectaculos(); }
								}
							}
						}
					}
				}catch(Exception ex) {
					System.out.println("Debe introducir un valor entero");
					entrada = new Scanner(System.in);
				}
			}
			
		}catch(Exception ex) {
			System.out.println("Se ha producido un error al obtener la informacion del fichero <config.properties> y/o <sql.properties>");
			return;
		}
		
	}

}
