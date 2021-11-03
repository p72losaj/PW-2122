package es.uco.pw.dao.espectaculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import es.uco.pw.datos.dao.comun.conexionBD.ConexionBD;
import es.uco.pw.negocio.espectaculo.CategoriaEspectaculo;
import es.uco.pw.negocio.espectaculo.EspectaculoDTO;
import es.uco.pw.negocio.espectaculo.SesionEspectaculoDTO;

/**
 * Clase que obtiene/modifica los datos de la tabla ESPECTACULO de la base de datos
 * @author Jaime Lorenzo Sanchez
 * @author Jose Angel Exposito Fernandez
 * @version 1.2
 */

public class EspectaculoDAO {
	private int identificadorEspectaculo;// Identificador del espectaculo
	private String tituloEspectaculo; // Titulo del espectaculo
	private String descripcionEspectaculo; // Descripcion del espectaculo
	private String categoriaEspectaculo; // Categoria del espectaculo
	private int localidadesEspectaculo; // Numero de localidades del espectaculo
	private int ventasEspectaculo; // Numero de ventas de un espectaculo
	/**
	 * Funcion que obtiene todos los datos de un espectaculo
	 * @param prop Fichero de propiedades para la conexion con la base de datos
	 * @param sql Fichero de propiedades
	 * @return Informacion de los espectaculos registrados en el sistema
	 */
	public ArrayList<EspectaculoDTO> obtencionEspectaculos(Properties prop, Properties sql) {
		ArrayList<EspectaculoDTO> espectaculos = new ArrayList<EspectaculoDTO>(); // Lista de espectaculos
		try {
			// Conexion con la base de datos
			Connection con = ConexionBD.getConexion(prop);
			// Sentencia del fichero sql para obtener los datos de todos los espectaculos
			PreparedStatement ps=con.prepareStatement(sql.getProperty("ObtencionEspectaculos"));
			// Ejecutamos la sentencia sql
			ResultSet rs = ps.executeQuery();
			// Recorremos las filas de la tabla
			while(rs.next()) {
				EspectaculoDTO espectaculo = new EspectaculoDTO();// Creamos un espectaculoDTO vacio
				espectaculo.setIdentificadorEspectaculo(rs.getInt("ID"));// Obtenemos el identificador del espectaculo
				espectaculo.setTituloEspectaculo(rs.getString("TITULO")); // Obtenemos el titulo del espectaculo
				espectaculo.setTipoEspectaculo(rs.getString("TIPO"));// Obtenemos el tipo del espectaculo
				espectaculo.setDescripcionEspectaculo(rs.getString("DESCRIPCION"));// Obtenemos la descripcion del espectaculo
				String categoria = rs.getString("CATEGORIA");// Obtenemos la categoria del espectaculo
				// Comprobamos cual es la categoria del espectaculo
				// Caso 1: El espectaculo es un concierto
				if(categoria.equals("concierto")) {
					espectaculo.setCategoriaEspectaculo(CategoriaEspectaculo.concierto);
				}
				// Caso 2: El espectaculo es un monologo
				else if(categoria.equals("monologo")) {
					espectaculo.setCategoriaEspectaculo(CategoriaEspectaculo.monologo);
				}
				// Caso 3: El espectaculo es una obra de teatro
				else if(categoria.equals("obra de teatro")){
					espectaculo.setCategoriaEspectaculo(CategoriaEspectaculo.obraTeatro);
				}		
				espectaculo.setAforoLocalidadesEspectaculo(rs.getInt("LOCALIDADES")); // Obtenemos el numero de localidades del espectaculo
				espectaculo.setVentasEspectaculo(rs.getInt("VENTAS"));// Obtenemos el numero de ventas del espectaculo
				// Obtenemos el resto de la informacion del espectaculo en funcion del tipo del espectaculo
				
				// Caso1: El espectaculo es de tipo puntual
				if(espectaculo.getTipoEspectaculo().equals("puntual")) {
					// Sentencia del fichero sql para obtener los datos del espectaculo puntual
					PreparedStatement ps2=con.prepareStatement(sql.getProperty("ObtencionEspectaculoPuntual"));
					// Indicamos en la sentencia ps2 el titulo del espectaculo
					ps2.setString(1, espectaculo.getTituloEspectaculo());
					// Ejecutamos la sentencia sql
					ResultSet rs2 = ps2.executeQuery();
					// Obtenemos toda la informacion del espectaculo puntual
					while(rs2.next()) {
						SesionEspectaculoDTO sesion = new SesionEspectaculoDTO();// Inicalizamos una sesion vacia
						sesion.setDiaSesion(rs2.getInt("DIA"));// Obtenemos el dia del espectaculo
						sesion.setMesSesion(rs2.getInt("MES"));// Obtenemos el mes del espectaculo
						sesion.setAnoSesion(rs2.getInt("ANO"));// Obtenemos el ano del espectaculo
						String fecha = Integer.toString(sesion.getAnoSesion()) + "-" + Integer.toString(sesion.getMesSesion()) + "-" + Integer.toString(sesion.getDiaSesion());// Obtenemos la fecha completa del espectaculo
						sesion.setFechaCompletaSesion(fecha);// Obtenemos la fecha completa de la sesion
						sesion.setHoraSesion(rs2.getInt("HORA"));// Obtenemos la hora de la sesion
						sesion.setHoraSesion(rs2.getInt("MINUTOS"));// Obtenemos los minutos de la hora de la sesion
						String horaCompleta = Integer.toString(sesion.getHoraSesion()) + ":" + Integer.toString(sesion.getMinutosSesion()); // Obtenemos la hora completa de la sesion
						sesion.setHoraCompleta(horaCompleta);// Obtenemos la hora completa de la sesion
						espectaculo.setSesionEspectaculo(sesion);// Anadimos la sesion del espectaculo
					}
					// Cerramos la sentencia rs2
					rs2.close();
					// Cerramos la sentencia ps2
					ps2.close();
				}
				// Caso 2: El espectaculo es de tipo multiple
				
				else if(espectaculo.getTipoEspectaculo().equals("multiple")) {
					// Sentencia del fichero sql para obtener los datos del fichero multiple
					PreparedStatement ps2=con.prepareStatement(sql.getProperty("ObtencionEspectaculoMultiple"));
					// Indicamos en la sentencia ps2 el titulo del espectaculo
					ps2.setString(1, espectaculo.getTituloEspectaculo());
					// Ejecutamos la sentencia sql
					ResultSet rs2 = ps2.executeQuery();
					// Obtenemos toda la informacion del espectaculo puntual
					while(rs2.next()) {
						ArrayList<SesionEspectaculoDTO> sesiones = new ArrayList<SesionEspectaculoDTO>(); // Creamos una lista de sesiones
						SesionEspectaculoDTO sesion1 = new SesionEspectaculoDTO();// Inicializamos una sesion vacia
						sesion1.setDiaSemana(rs2.getString("DIA_SEMANA1"));// Obtenemos el dia de la semana del espectaculo
						sesion1.setHoraSesion(rs2.getInt("HORA1"));// Obtenemos la hora de la sesion
						sesion1.setMinutos(rs2.getInt("MINUTOS1"));// Obtenemos los minutos de la sesion1
						String horaCompleta = Integer.toString(sesion1.getHoraSesion()) + ":" + Integer.toString(sesion1.getMinutosSesion()); // Obtenemos la hora completa de la sesion
						sesion1.setHoraCompleta(horaCompleta);// Obtenemos la hora completa de la sesion
						sesiones.add(sesion1); // Anadimos la sesion a la lista de sesiones
						SesionEspectaculoDTO sesion2 = new SesionEspectaculoDTO(); // Creamos una nueva sesion
						sesion2.setDiaSemana(rs2.getString("DIA_SEMANA2")); // Obtenemos el dia de la semana de la nueva sesion
						sesion2.setHoraSesion(rs2.getInt("HORA2")); // Obtenemos la hora de la sesion2
						sesion2.setMinutos(rs2.getInt("MINUTOS2")); // Obtenemos los minutos de la sesion 2
						String horaCompleta2 = Integer.toString(sesion2.getHoraSesion()) + ":" + Integer.toString(sesion2.getMinutosSesion()); // Obtenemos la hora completa de la nueva sesion
						sesion2.setHoraCompleta(horaCompleta2);// Obtenemos la hora completa de la sesion2
						sesiones.add(sesion2); // Anadimos la sesion2 a la lista de sesiones
						espectaculo.setSesionesEspectaculo(sesiones);// Anadimos la lista de sesiones
					}
					// Cerramos la sentencia rs2
					rs2.close();
					// Cerramos la sentencia ps2
					ps2.close();
				}
				
				// Caso 3: El espectaculo es de tipo temporada
				
				else if(espectaculo.getTipoEspectaculo().equals("temporada")) {
					// Sentencia del fichero sql para obtener los datos de la tabla
					PreparedStatement ps2=con.prepareStatement(sql.getProperty("ObtencionEspectaculoTemporada"));
					// Indicamos en la sentencia ps2 el titulo del espectaculo
					ps2.setString(1, espectaculo.getTituloEspectaculo());
					// Ejecutamos la sentencia sql
					ResultSet rs2 = ps2.executeQuery();
					// Obtenemos toda la informacion del espectaculo de temporada
					while(rs2.next()) {
						ArrayList<SesionEspectaculoDTO> sesiones = new ArrayList<SesionEspectaculoDTO>(); // Creamos una lista de sesiones
						SesionEspectaculoDTO sesion1 = new SesionEspectaculoDTO();// Inicializamos una sesion vacia
						sesion1.setDiaSemana(rs2.getString("DIA_SEMANA"));// Obtenemos el dia de la semana del espectaculo
						sesion1.setHoraSesion(rs2.getInt("HORA_INICIO"));// Obtenemos la hora de inicio la sesion
						sesion1.setMinutos(rs2.getInt("MINUTOS_INICIO"));// Obtenemos los minutos de inicio de la sesion1
						String horaCompleta = Integer.toString(sesion1.getHoraSesion()) + ":" + Integer.toString(sesion1.getMinutosSesion()); // Obtenemos la hora completa de la sesion
						sesion1.setHoraCompleta(horaCompleta);// Obtenemos la hora completa de la sesion
						sesiones.add(sesion1); // Anadimos la sesion a la lista de sesiones
						SesionEspectaculoDTO sesion2 = new SesionEspectaculoDTO(); // Creamos una nueva sesion
						sesion2.setDiaSemana(rs2.getString("DIA_SEMANA")); // Obtenemos el dia de la semana de la nueva sesion
						sesion2.setHoraSesion(rs2.getInt("HORA_FIN")); // Obtenemos la hora de finalizacion de la sesion2
						sesion2.setMinutos(rs2.getInt("MINUTOS_FIN")); // Obtenemos los minutos de finalizacion de la sesion 2
						String horaCompleta2 = Integer.toString(sesion2.getHoraSesion()) + ":" + Integer.toString(sesion2.getMinutosSesion()); // Obtenemos la hora completa de la nueva sesion
						sesion2.setHoraCompleta(horaCompleta2);// Obtenemos la hora completa de la sesion2
						sesiones.add(sesion2); // Anadimos la sesion2 a la lista de sesiones
						espectaculo.setSesionesEspectaculo(sesiones);// Anadimos la lista de sesiones
					}
					// Cerramos la sentencia rs2
					rs2.close();
					// Cerramos la sentencia ps2
					ps2.close();
				}
				
				// Anadimos los datos del espectaculo a la lista
				espectaculos.add(espectaculo);
			}
			// Cerramos la sentencia rs
			rs.close();
			// Cerramos la sentencia ps
			ps.close();
			// Cerramos la conexion con la base de datos
			if(con != null) {
				con = null;
			}
		}catch(Exception ex) {
			System.out.println("Se ha producido un error al obtener los datos del espectaculo");
		}
		
		return espectaculos;
	}
	
	/**
	 * Funcion que obtiene el numero de ventas de un espectaculo
	 * @return Numero de ventas del espectaculo
	 */
	public int getVentasEspectaculo() {
		return this.ventasEspectaculo;
	}
	/**
	 * Funcion que modifica el numero de ventas de un espectaculo
	 * @param ventas Nuevo numero de ventas del espectaculo
	 */
	public void setVentasEspectaculo(int ventas) {
		this.ventasEspectaculo = ventas;
	}
	/**
	 * Funcion que obtiene el numero de localidades disponibles del espectaculo
	 * @return Numero de localidades disponibles del 
	 */
	public int getLocalidadesEspectaculo() {
		return this.localidadesEspectaculo;
	}
	/**
	 * Funcion que modifica el numero de localidades de un espectaculo
	 * @param localidades Nuevo numero de localidades disponibles de un espectaculo
	 */
	public void setLocalidadesEspectaculo(int localidades) {
		this.localidadesEspectaculo = localidades;
	}
	/**
	 * Funcion que obtiene la categoria de un espectaculo
	 * @return Categoria del espectaculo
	 */
	public String getCategoriaEspectaculo() {
		return this.categoriaEspectaculo;
	}
	/**
	 * Funcion que modifica la categoria de un espectaculo
	 * @param categoria Nueva categoria del espectaculo
	 */
	public void setCategoriaEspectaculo(String categoria) {
		this.categoriaEspectaculo = categoria;
	}
	/**
	 * Funcion que obtiene la descripcion de un espectaculo
	 * @return Descripcion del espectaculo
	 */
	public String getDescripcionEspectaculo() {
		return this.descripcionEspectaculo;
	}
	/**
	 * Funcion que modifica la descripcion de un espectaculo
	 * @param descripcion Nueva descripcion de un espectaculo
	 */
	public void setDescripcionEspectaculo(String descripcion) {
		this.descripcionEspectaculo = descripcion;
	}
	/**
	 * Funcion que obtiene el titulo de un espectaculo
	 * @return Titulo del espectaculo
	 */
	public String getTituloEspectaculo() {
		return this.tituloEspectaculo;
	}
	/**
	 * Funcion que modifica el titulo de un espectaculo
	 * @param titulo Nuevo titulo del espectaculo
	 */
	public void setTituloEspectaculo(String titulo) {
		this.tituloEspectaculo = titulo;
	}
	/**
	 * Funcion que obtiene el identificador de un espectaculo
	 * @return Identificador del espectaculo
	 */
	public int getIdentificadorEspectaculo() {
		return this.identificadorEspectaculo;
	}
	/**
	 * Funcion que modifica el identificador del espectaculo
	 * @param id Nuevo identificador del espectaculo
	 */
	public void setIdentificadorEspectaculo(int id) {
		this.identificadorEspectaculo = id;
	}

	/**
	 * Funcion que obtiene el identificador de un espectaculo
	 * @param prop Fichero de configuracion
	 * @param sql Fichero de sentencias sql
	 * @param tituloEspectaculo Titulo del espectaculo
	 * @return Identificador del espectaculo
	 */
	public int obtencionEspectaculoIdentificador(Properties prop, Properties sql, String tituloEspectaculo) {
		int identificador = 0; // Identificador del espectaculo
		try {
			Connection con = ConexionBD.getConexion(prop); // Conexion con la base de datos
			PreparedStatement ps=con.prepareStatement(sql.getProperty("ObtencionEspectaculoTitulo")); // Sentencia sql para obtener la informacion del espectaculo buscado en funcion de su titulo
			ps.setString(1, tituloEspectaculo); // Indicamos en la sentencia sql el titulo del espectaculo
			ResultSet rs = ps.executeQuery(); // Ejecucion de la sentencia sql
			while(rs.next()) {
				identificador = rs.getInt("ID"); // Obtenemos el identificador del espectaculo
			}
			rs.close(); // Cerramos la ejecucion de la sentencia sql
			ps.close(); // Cierre de la sentencia sql
			if(con != null) {
				con = null; // Cierre de la conexion con la base de datos
			}
		}catch(Exception ex) {
			System.out.println("Se ha producido un error al obtener el identificador del espectaculo");
		}
		return identificador; // Retornamos el identificador del espectaculo
	}
}



/**
 * Funcion que anade los datos de una critica en la base de datos
 * @param prop Fichero de configuracion
 * @param sql Fichero de sentencias sql
 * @param espectaculoDTO Espectaculo a insertar en la base de datos
 * @param tituloEspectaculo Titulo del espectaculo
 * @return Numero de filas insertadas en la base de datos
 */
public int insercionEspectaculo(Properties prop, Properties sql, EspectaculoDTO espectaculoDTO,String tituloEspectaculo) {
	int status = 0; // Numero de filas modificadas de la base de datos
	try {
		Connection con = ConexionBD.getConexion(prop); // Conexion con la base de datos
		PreparedStatement ps=con.prepareStatement(sql.getProperty("InsercionEspectaculoComun")); // Sentencia sql para insertar un espectaculo en la base de datos
		ps.setString(1,espectaculoDTO.getTituloEspectaculo()); // Indicamos en la sentencia sql el titulo del espectaculo insertar
		ps.setString(2, espectaculoDTO.getTipoEspectaculo()); // Indicamos en la sentencia sql el tipo del espectaculo a insertar
		ps.setString(3, espectaculoDTO.getDescripcionEspectaculo()); // Indicamos en la sentencia sql la descripcion del espectaculo a insertar
		//Categoria del espectaculo
		try {
		if(CategoriaEspectaculo.concierto==EspectaculoDTO.getCategoriaEspectaculo()) {
			ps.setString(4,"concierto"); // Indicamos en la sentencia sql la categoria del espectaculo insertar
		}
		// Caso 2: El espectaculo es un monologo
		else if(CategoriaEspectaculo.monologo==EspectaculoDTO.getCategoriaEspectaculo()) {
			ps.setString(4,"monologo"); // Indicamos en la sentencia sql la categoria del espectaculo insertar
		}
		// Caso 3: El espectaculo es una obra de teatro
		else if(CategoriaEspectaculo.obraTeatro==EspectaculoDTO.getCategoriaEspectaculo()){
			ps.setString(4,"obra de teatro"); // Indicamos en la sentencia sql la categoria del espectaculo insertar
		
		}
		}catch(Exception ex) {
			System.out.println("Se ha producido un error al insertar la categoria del espectaculo");
		}
		//ps.setString(4,espectaculoDTO.getCategoriaEspectaculo()); // Indicamos en la sentencia sql el titulo del espectaculo insertar
		ps.setInt(5, espectaculoDTO.getAforoLocalidadesEspectaculo()); // Indicamos en la sentencia sql el aforo del espectaculo a insertar
		ps.setInt(6, espectaculoDTO.getVentasEspectaculo()); // Indicamos en la sentencia sql la venta de entradas del espectaculo a insertar
		
		status = ps.executeUpdate(); // Ejecutamos la sentencia sql
		ps.close(); // finalizacion de la sentencia sql
		if(con != null) {
			con = null; // Cerramos la conexion con la base de datos
		}
		
		
	//	InsercionEspectaculoPuntual: INSERT INTO `ESPECTACULO_PUNTUAL` ( `TITULO` , `DIA` , `MES` , `ANO` , `HORA` , `MINUTOS` ) VALUES (?, ?, ?, ?, ?, ?);

		if(espectaculoDTO.getTipoEspectaculo().equals("puntual")) {
		
			
			
			con = ConexionBD.getConexion(prop); // Conexion con la base de datos
			PreparedStatement ps2=con.prepareStatement(sql.getProperty("InsercionEspectaculoPuntual")); // Sentencia sql para insertar un espectaculo puntual en la base de datos
			ps2.setString(1,espectaculoDTO.getTituloEspectaculo()); // Indicamos en la sentencia sql el titulo del espectaculo insertar
			ps2.setString(2, "Dia");
			ps2.setString(3, "Mes");
			ps2.setString(4, "Ano");
			ps2.setString(5, "Horas");
			ps2.setString(6, "Minutos");
			status = ps2.executeUpdate(); // Ejecutamos la sentencia sql
			ps2.close(); // finalizacion de la sentencia sql
			if(con != null) {
				con = null; // Cerramos la conexion con la base de datos
			}
		
		}
		
//		InsercionEspectaculoMultiple: INSERT INTO `ESPECTACULO_MULTIPLE` ( `TITULO` , `DIA_SEMANA1` , `HORA1` , `MINUTOS1` , `DIA_SEMANA2` , `HORA2` , `MINUTOS2` ) VALUES (?, ?, ?, ?, ?, ?, ?);

		// Caso 2: El espectaculo es de tipo multiple
		
		else if(espectaculoDTO.getTipoEspectaculo().equals("multiple")) {
		
			con = ConexionBD.getConexion(prop); // Conexion con la base de datos
			PreparedStatement ps2=con.prepareStatement(sql.getProperty("InsercionEspectaculoMultiple")); // Sentencia sql para insertar un espectaculo multiple en la base de datos
			ps2.setString(1,espectaculoDTO.getTituloEspectaculo()); // Indicamos en la sentencia sql el titulo del espectaculo insertar
			ps2.setString(2, "Diasemana1");
			ps2.setString(3, "hora1");
			ps2.setString(4, "minutos1");
			ps2.setString(5, "diasemana2");
			ps2.setString(6, "hora2");
			ps2.setString(7, "Minutos2");
			status = ps2.executeUpdate(); // Ejecutamos la sentencia sql
			ps2.close(); // finalizacion de la sentencia sql
			if(con != null) {
				con = null; // Cerramos la conexion con la base de datos
			}
		
		
		
		}
		
		
		//InsercionEspectaculoTemporada: INSERT INTO `ESPECTACULO_TEMPORADA` ( `TITULO` , `DIA_SEMANA` , `HORA_INICIO` , `MINUTOS_INICIO` , `HORA_FIN` , `MINUTOS_FIN` ) VALUES (?, ?, ?, ?, ?, ?);

		// Caso 3: El espectaculo es de tipo temporada
		
		else if(espectaculoDTO.getTipoEspectaculo().equals("temporada")) {
		
			con = ConexionBD.getConexion(prop); // Conexion con la base de datos
			PreparedStatement ps2=con.prepareStatement(sql.getProperty("InsercionEspectaculoTemporada")); // Sentencia sql para insertar un espectaculo de temporada en la base de datos
			ps2.setString(1,espectaculoDTO.getTituloEspectaculo()); // Indicamos en la sentencia sql el titulo del espectaculo insertar
			ps2.setString(2, "Diasemana");
			ps2.setString(3, "hora");
			ps2.setString(4, "minutos");
			ps2.setString(5, "hora fin");
			ps2.setString(6, "minutos fin");
			status = ps2.executeUpdate(); // Ejecutamos la sentencia sql
			ps2.close(); // finalizacion de la sentencia sql
			if(con != null) {
				con = null; // Cerramos la conexion con la base de datos
			}
		
			
			
		}
		
