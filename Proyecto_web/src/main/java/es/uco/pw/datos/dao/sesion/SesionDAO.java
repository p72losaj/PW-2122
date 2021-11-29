package es.uco.pw.datos.dao.sesion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import es.uco.pw.datos.dao.comun.conexionBD.ConexionBD;
import es.uco.pw.negocio.espectaculo.EspectaculoDTO;
import es.uco.pw.negocio.espectaculo.SesionEspectaculoDTO;

/**
 * Clase que gestiona la tabla de sesiones de un espectaculo 
 * @author Jaime Lorenzo Sanchez
 * @version 1.0
 */
public class SesionDAO {
	private int idSesion; // Identificador de la sesion
	private String tituloEspectaculo; // Titulo del espectaculo de la sesion
	private int DiaSesion; // Dia de la sesion
	private int MesSesion; // Mes de la sesion
	private int AnoSesion; // Ano de la sesion
	private int horaSesion; // Hora de la sesion
	private int minutosSesion; // Minutos de la sesion
	private String diaSemana; // Dia de la semana
	private int ventasEspectaculo; // Numero de ventas de la sesion
	/**
	 * Funcion que obtiene el numero de ventas de la sesion
	 * @return Numero de ventas de la sesion
	 */
	public int getVentasSesion() {
		return this.ventasEspectaculo;
	}
	/**
	 * Funcion que modifica el numero de ventas de una sesion
	 * @param ventas Nuevo numero de ventas de una sesion
	 */
	public void setVentasSesion(int ventas) {
		this.ventasEspectaculo = ventas;
	}
	/**
	 * Funcion que obtiene el dia de la semana
	 * @return Dia de la semana de la sesion
	 */
	public String getDiaSemana() {
		return this.diaSemana;
	}
	/**
	 * Funcion que modifica el dia de la semana de la sesion
	 * @param semana Nuevo dia de la semana de la sesion
	 */
	public void setDiaSemana(String semana) {
		this.diaSemana = semana;
	}
	/**
	 * Funcion que obtiene los minutos de la sesion
	 * @return Minutos de la sesion
	 */
	public int getMinutosSesion() {
		return this.minutosSesion;
	}
	/**
	 * Funcion que modifica los minutos de la sesion
	 * @param min Nuevos minutos de la sesion
	 */
	public void setMinutosSesion(int min) {
		this.minutosSesion = min;
	}
	/**
	 * Funcion que obtiene la hora de la sesion
	 * @return Hora de la sesion
	 */
	public int getHoraSesion() {
		return this.horaSesion;
	}
	/**
	 * Funcion que modifica la hora de la sesion
	 * @param hora Nueva hora de la sesion
	 */
	public void setHoraSesion(int hora) {
		this.horaSesion = hora;
	}
	/**
	 * Funcion que obtiene el ano de la sesion
	 * @return Ano de la sesion
	 */
	public int getAnoSesion() {
		return this.AnoSesion;
	}
	/**
	 * Funcion que modifica el ano de la sesion
	 * @param ano Nuevo ano de la sesion
	 */
	public void setAnoSesion(int ano) {
		this.AnoSesion = ano;
	}
	/**
	 * Funcion que obtiene el mes de la sesion
	 * @return Mes de la sesion
	 */
	public int getMesSesion() {
		return this.MesSesion;
	}
	/**
	 * Funcion que modifica el mes de la sesion
	 * @param mes Nuevo mes de la sesion
	 */
	public void setMesSesion(int mes) {
		this.MesSesion = mes;
	}
	/**
	 * Funcion que obtiene el dia de la sesion
	 * @return Dia de la sesion
	 */
	public int getDiaSesion() {
		return this.DiaSesion;
	}
	/**
	 * Funcion que modifica el dia de la sesion
	 * @param dia Nuevo dia de la sesion
	 */
	public void setDiaSesion(int dia) {
		this.DiaSesion = dia;
	}
	/**
	 * Funcion que obtiene el identificador de la sesion
	 * @return Identificador de la sesion
	 */
	public int getIdentificadorSesion(){
		return this.idSesion;
	}
	/**
	 * Funcion que modifica el identificador de la sesion
	 * @param id Nuevo identificador de la sesion
	 */
	public void setIdentificadorSesion(int id) {
		this.idSesion = id;
	}
	/**
	 * Funcion que obtiene el titulo del espectaculo de la sesion
	 * @return Titulo del espectaculo de la sesion
	 */
	public String getTituloEspectaculo() {
		return this.tituloEspectaculo;
	}
	/**
	 * Funcion que modifica el titulo del espectaculo de la sesion
	 * @param tituloEspectaculo Nuevo titulo del espectaculo de la sesion
	 */
	public void setTituloEspectaculo(String tituloEspectaculo) {
		this.tituloEspectaculo = tituloEspectaculo;
	}
	
	/**
	 * Funcion que anade los datos de sesion de un espectaculo
	 * @param prop Fichero de configuracion
	 * @param sql Fichero de sentencias sql
	 * @param espectaculo Datos del espectaculo
	 * @return Numero de filas modificadas de la base de datos
	 */
	
	public int anadirEventosEspectaculo(Properties prop, Properties sql, EspectaculoDTO espectaculo) {
		int status = 0; // Numero de filas modificadas de la base de datos
		try {
			Connection con = ConexionBD.getConexion(prop); // Conexion con la base de datos
			// ESPECTACULO PUNTUAL
			if(espectaculo.getTipoEspectaculo().equals("puntual")) {
				PreparedStatement ps=con.prepareStatement(sql.getProperty("RegistrarSesionEspectaculoPuntual")); // Sentencia sql para insertar los datos de sesion de un espectaculo puntual
				ps.setString(1, espectaculo.getTituloEspectaculo()); // Indicamos en la sentencia sql el titulo del espectaculo
				ps.setInt(2, espectaculo.getSesionEspectaculo().getDiaSesion()); // Indicamos en la sentencia sql el dia de la sesion
				ps.setInt(3, espectaculo.getSesionEspectaculo().getMesSesion()); // Indicamos en la sentencia sql el mes de la sesion
				ps.setInt(4, espectaculo.getSesionEspectaculo().getAnoSesion()); // Indicamos en la sentencia sql el ano de la sesion
				ps.setInt(5, espectaculo.getSesionEspectaculo().getHoraSesion()); // Indicamos en la sentencia sql la hora de la sesion
				ps.setInt(6, espectaculo.getSesionEspectaculo().getMinutosSesion()); // Indicamos en la sentencia sql los minutos de la sesion
				ps.setInt(7, espectaculo.getSesionEspectaculo().getVentasSesion()); // Indicamos en la sentencia sql el numero de ventas de la sesion
				status = ps.executeUpdate(); // Ejecutamos la sentencia sql
				ps.close(); // Cierre de la sentencia sql
			}
			// ESPECTACULO MULTIPLE
			else if(espectaculo.getTipoEspectaculo().equals("multiple")) {
				PreparedStatement ps=con.prepareStatement(sql.getProperty("RegistrarSesionesEspectaculo")); // Sentencia sql para insertar los datos de las sesiones de un espectaculo multiple
				for(int i=0; i < espectaculo.getSesionesEspectaculo().size(); i++) {
					ps.setString(1, espectaculo.getTituloEspectaculo());
					ps.setInt(2, espectaculo.getSesionesEspectaculo().get(i).getHoraSesion());
					ps.setInt(3, espectaculo.getSesionesEspectaculo().get(i).getMinutosSesion());
					ps.setString(4, espectaculo.getSesionesEspectaculo().get(i).getDiaSemana());
					ps.setInt(5, espectaculo.getSesionesEspectaculo().get(i).getVentasSesion()); // Indicamos en la sentencia sql el numero de ventas de la sesion
					status = ps.executeUpdate();
				}
				ps.close(); // Cierre de la sentencia sql
			}
			// ESPECTACULO TEMPORADA
			else if(espectaculo.getTipoEspectaculo().equals("temporada")) {
				PreparedStatement ps=con.prepareStatement(sql.getProperty("RegistrarSesionesEspectaculo")); // Sentencia sql para insertar los datos de las sesiones de un espectaculo multiple
				for(int i=0; i < espectaculo.getSesionesEspectaculo().size(); i++) {
					ps.setString(1, espectaculo.getTituloEspectaculo());
					ps.setInt(2, espectaculo.getSesionesEspectaculo().get(i).getHoraSesion());
					ps.setInt(3, espectaculo.getSesionesEspectaculo().get(i).getMinutosSesion());
					ps.setString(4, espectaculo.getSesionesEspectaculo().get(i).getDiaSemana());
					ps.setInt(5, espectaculo.getSesionesEspectaculo().get(i).getVentasSesion()); // Indicamos en la sentencia sql el numero de ventas de la sesion
					status = ps.executeUpdate();
				}
				ps.close(); // Cierre de la sentencia sql
			}
			if(con != null) {
				con = null; // Cierre de la conexion
			}
		}catch(Exception ex) {
			ex.getMessage();
		}
		return status;
	}
	
	/**
	 * Funcion que modifica los datos de sesion de un espectaculo
	 * @param prop Fichero de configuracion
	 * @param sql Fichero de sentencias sql
	 * @param sesion Datos modificados de la sesion
	 * @return Numero de filas modificadas de la sesion
	 */
	
	public int modificarSesionEspectaculo(Properties prop, Properties sql, SesionEspectaculoDTO sesion) {
		return 0;
	}
	
	/**
	 * Funcion que eliminar los datos de una sesion de la base de datos
	 * @param prop Fichero de configuracion
	 * @param sql Fichero de sentencias sql
	 * @param id Identificador de la sesion a eliminar
	 * @return Numero de filas eliminadas de la base de datos
	 */
	
	public int cancelarSesionEspectaculo(Properties prop, Properties sql, int id) {
		int status = 0;
		try {
			Connection con = ConexionBD.getConexion(prop);// Conexion con la base de datos
			PreparedStatement ps=con.prepareStatement(sql.getProperty("CancelarSesion"));// Sentencia sql para cancelar una sesion //CancelarSesion
			ps.setInt(1, id);// Indicamos en la sentencia sql el identificador de la sesion a eliminar
			status = ps.executeUpdate();// Ejecutamos la sentencia sql ( status = ps.executeUpdate() )
			 ps.close();// Cierre de la sentencia sql
			 if(con != null) {
					con = null; // Cierre de la conexion
				}	
		}catch(Exception ex) {
			ex.getCause();
		}
		return status;
	}
	/**
	 * Funcion que obtiene los datos de sesion de un espectaculo de tipo puntual
	 * @param prop Fichero de propiedades
	 * @param sql Fichero de sentencias sql
	 * @param titulo Titulo del espectaculo
	 * @return Datos de la sesion de un espectaculo puntual
	 */
	public SesionEspectaculoDTO obtencionSesionEspectaculoPuntual(Properties prop, Properties sql, String titulo) {
		SesionEspectaculoDTO sesion = new SesionEspectaculoDTO();
		try {
			Connection con = ConexionBD.getConexion(prop); // Conexion con la base de datos
			PreparedStatement ps=con.prepareStatement(sql.getProperty("ObtencionSesionesEspectaculo")); // Sentencia sql para obtener los datos de sesion del espectaculo
			ps.setString(1, titulo); // Indicamos en la sentencia sql el titulo del espectaculo
			ResultSet rs = ps.executeQuery(); // Ejecucion de la sentencia sql
			// Recorremos las filas obtenidas por la ejecucion de la sentencia sql
			while(rs.next()) {
				sesion.setIdentificadorSesion(rs.getInt("ID")); // Obtenemos el identificador de la sesion
				sesion.setDiaSesion(rs.getInt("DIA_SESION")); // Obtenemos el dia de la sesion
				sesion.setMesSesion(rs.getInt("MES_SESION")); // Obtenemos el mes de la sesion
				sesion.setAnoSesion(rs.getInt("ANO_SESION")); // Obtenemos el ano de la sesion
				sesion.setFechaCompletaSesion(sesion.getAnoSesion() + "-" + sesion.getMesSesion()+"-"+sesion.getDiaSesion()); // Obtenemos la fecha completa de la sesion
				sesion.setHoraSesion(rs.getInt("HORA_SESION")); // Obtenemos la hora de la sesion
				sesion.setMinutos(rs.getInt("MINUTOS_SESION")); // Obtenemos los minutos de la sesion
				sesion.setHoraCompleta(); // Obtenemos los datos de la hora completa
				sesion.setVentasSesion(rs.getInt("VENTAS")); // Obtenemos las ventas de la sesion
			}
			ps.close(); // Cierre de la sentencia sql
			if(con != null) {
				con = null; // Cierre de la conexion
			}
		}catch(Exception ex) {
			System.out.println("Se ha producido un error al obtener los datos del espectaculo");
		}
		return sesion; // Retornamos los datos de la sesion
	}
	/**
	 * Funcion que obtiene las sesiones de un espectaculo por temporada
	 * @param prop Fichero de configuracion
	 * @param sql Fichero de sentencias sql
	 * @param titulo Titulo del espectaculo
	 * @return Lista de sesiones del espectaculo
	 */
	public ArrayList<SesionEspectaculoDTO> obtencionSesionEspectaculoTemporada(Properties prop, Properties sql,
			String titulo) {
		ArrayList<SesionEspectaculoDTO> sesiones = new ArrayList<SesionEspectaculoDTO>(); // Lista de sesiones vacia del espectaculo de temporada
		try {
			Connection con = ConexionBD.getConexion(prop); // Conexion con la base de datos
			PreparedStatement ps=con.prepareStatement(sql.getProperty("ObtencionSesionesEspectaculo")); // Sentencia sql para obtener los datos de sesion del espectaculo
			ps.setString(1, titulo); // Indicamos en la sentencia sql el titulo del espectaculo
			ResultSet rs = ps.executeQuery(); // Ejecucion de la sentencia sql
			// Recorremos las filas obtenidas por la ejecucion de la sentencia sql
			while(rs.next()) {
				SesionEspectaculoDTO sesion = new SesionEspectaculoDTO(); // Creamos una sesion vacia
				sesion.setIdentificadorSesion(rs.getInt("ID")); // Obtenemos el identificador de la sesion
				sesion.setHoraSesion(rs.getInt("HORA_SESION")); // Obtenemos la hora de la sesion
				sesion.setMinutos(rs.getInt("MINUTOS_SESION")); // Obtenemos los minutos de la sesion
				sesion.setHoraCompleta(); // Obtenemos la hora completa de la sesion
				sesion.setDiaSemana(rs.getString("DIA_SEMANA_SESION")); // Obtenemos el dia de la semana de la sesion
				sesion.setVentasSesion(rs.getInt("VENTAS")); // Obtenemos las ventas de la sesion
				sesiones.add(sesion); // anadimos los datos de la sesion
			}
			ps.close(); // Cierre de la sentencia sql
			if(con != null) {
				con = null; // Cierre de la conexion
			}
		}catch(Exception ex) {
			System.out.println("Se ha producido un error al obtener los datos del espectaculo");
		}
		return sesiones; // Retornamos las sesiones del espectaculo
	}
	/**
	 * Funcion que obtiene las sesiones de un espectaculo multiple
	 * @param prop Fichero de configuracion
	 * @param sql Fichero de sentencias sql
	 * @param titulo Titulo del espectaculo
	 * @return Lista de sesiones del espectaculo
	 */
	public ArrayList<SesionEspectaculoDTO> obtencionSesionEspectaculoMultiple(Properties prop, Properties sql,
			String titulo) {
		ArrayList<SesionEspectaculoDTO> sesiones = new ArrayList<SesionEspectaculoDTO>(); // Lista de sesiones vacia del espectaculo de temporada
		try {
			Connection con = ConexionBD.getConexion(prop); // Conexion con la base de datos
			PreparedStatement ps=con.prepareStatement(sql.getProperty("ObtencionSesionesEspectaculo")); // Sentencia sql para obtener los datos de sesion del espectaculo
			ps.setString(1, titulo); // Indicamos en la sentencia sql el titulo del espectaculo
			ResultSet rs = ps.executeQuery(); // Ejecucion de la sentencia sql
			// Recorremos las filas obtenidas por la ejecucion de la sentencia sql
			while(rs.next()) {
				SesionEspectaculoDTO sesion = new SesionEspectaculoDTO(); // Creamos una sesion vacia
				sesion.setIdentificadorSesion(rs.getInt("ID")); // Obtenemos el identificador de la sesion
				sesion.setHoraSesion(rs.getInt("HORA_SESION")); // Obtenemos la hora de la sesion
				sesion.setMinutos(rs.getInt("MINUTOS_SESION")); // Obtenemos los minutos de la sesion
				sesion.setHoraCompleta(); // Obtenemos la hora completa de la sesion
				sesion.setDiaSemana(rs.getString("DIA_SEMANA_SESION")); // Obtenemos el dia de la semana de la sesion
				sesion.setVentasSesion(rs.getInt("VENTAS")); // Obtenemos el numero de ventas de la sesion
				sesiones.add(sesion); // anadimos los datos de la sesion
			}
			ps.close(); // Cierre de la sentencia sql
			if(con != null) {
				con = null; // Cierre de la conexion
			}
		}catch(Exception ex) {
			System.out.println("Se ha producido un error al obtener los datos del espectaculo");
		}
		return sesiones; // Retornamos las sesiones del espectaculo
	}
	/**
	 * Funcion que cancela las sesiones de un espectaculo
	 * @param prop Fichero de configuracion
	 * @param sql Fichero de sentencias sql
	 * @param tituloEspectaculo Titulo del espectaculo
	 * @return Numero de filas modificadas de la base de datos
	 */
	public int cancelarSesionesEspectaculo(Properties prop, Properties sql, String tituloEspectaculo) {
		int status = 0;
		try {
			Connection con = ConexionBD.getConexion(prop);// Conexion con la base de datos
			PreparedStatement ps=con.prepareStatement(sql.getProperty("CancelarSesiones"));// Sentencia sql para cancelar las sesiones de un espectaculo CancelarSesiones
			ps.setString(1, tituloEspectaculo);// Indicamos en la sentencia sql el titulo del espectaculo de las sesiones a eliminar
			 status = ps.executeUpdate();// Ejecutamos la sentencia sql ( status = ps.executeUpdate() )
			 ps.close();// Cierre de la sentencia sql
			 if(con != null) {
					con = null; // Cierre de la conexion
				}
			
		}catch(Exception ex) {
			ex.getCause();
		}
		return status;
	}
	
}
