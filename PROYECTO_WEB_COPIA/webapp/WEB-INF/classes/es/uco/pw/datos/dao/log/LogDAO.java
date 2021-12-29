package es.uco.pw.datos.dao.log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.text.DateFormat;

import es.uco.pw.datos.dao.comun.conexionBD.ConexionBD;
import es.uco.pw.negocio.log.LogDTO;
import java.lang.Object;


/**
 * Clase que gestiona la tabla de log
 * @author Jose Angel Exposito Fernandez
 * @version 1.0
 */

public class LogDAO {

	private int idUsuario;
	private java.sql.Date fechaRegistro = null; 
	private java.sql.Date ultimaFecha = null;
	private String password = null; 
	
	
	
	/**
	 * Funcion que obtiene el identificador del usuario
	 * @return Identificador del usuario
	 */
	
	public int getIdUsuario() {
		return this.idUsuario;
	}
	
	/**
	 * Funcion que modifica el identificador del usuario
	 * @param idUsuario Nuevo identificador del usuario
	 */

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * Funcion que obtiene la fecha de registro
	 * @return Fecha de registro
	 */
	
	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}
	
	/**
	 * Funcion que modifica  la fecha de registro
	 * @param fecha fecha de registro
	 */
	
	public void setFechaRegistro(java.sql.Date fecha) {
		this.fechaRegistro = fecha;
	}
	
	/**
	 * Funcion que obtiene la fecha de registro
	 * @return Fecha de registro
	 */
	
	public java.sql.Date getFechaFinal() {
		return this.ultimaFecha;
	}
	
	/**
	 * Funcion que modifica  la fecha de registro
	 * @param fecha fecha de registro
	 */
	
	public void setFechaFinal(java.sql.Date fecha) {
		this.ultimaFecha = fecha;
	}
	
	/**
	 * Funcion que modifica la contrasena
	 * @param contra constrasena
	 */

	public void setPassword(String pass) {
		this.password = pass;
	}

	/**
	 * Funcion que obtiene la contrasena
	 * @return Fecha de registro
	 */
	
	public String getPassword() {
		return this.password;
	}
	
	
	
	
	/**
	 * Funcion que inserta los datos de un nuevo usuario en la base de datos
	 * @param usuarioDTO Usuario a insertar en la base de datos
	 * @param prop Fichero de propiedades de conexion a la base de datos
	 * @param sql Fichero de propiedades sql
	 * @return Numero de filas modificadas de la base de datos
	 */

	public int insertarLog(LogDTO logDTO, Properties prop, Properties sql) {
		
		int status = 0; // Numero de filas anadidas en la base de datos
		
		try {
			Connection con = ConexionBD.getConexion(prop); // Conexion con la base de datos
			// Obtenemos la propiedad del fichero de propiedades
			PreparedStatement ps=con.prepareStatement(sql.getProperty("InsertarLog"));
			ps.setInt(1,logDTO.getIdUsuario());// Indicamos id de usuario
			
			ps.setDate(2,logDTO.getFechaRegistro());// Indicamos fecha de registro
			ps.setDate(3, logDTO.getFechaFinal());// Indicamos fecha final
		
			//ps.setString(2,new Java.sql.Date (logDTO.getFechaRegistro().toString()));// Indicamos fecha de registro
			//ps.setString(3, logDTO.getFechaFinal().toString());// Indicamos fecha final
			ps.setString(4, logDTO.getPassword());// Indicamos la contraseña
			
			
			status = ps.executeUpdate(); // Actualizamos la base de datos
			ps.close(); // Cerramos la sentencia ps
			// Cerramos la conexion con la base de datos
			if(con != null) {
				con = null;
			}
		}catch(Exception ex) {
			System.out.println("Se ha producido un error al anadir los datos del log");
		}
		
		return status; // Retornamos el numero de filas anadidas a la base de datos
	}

	
public LogDTO obtenerDatosLog(Properties sql, Properties prop, int idUsuario) {
		
		LogDTO log = new LogDTO();
		
		try {
			
			// Conexion con la base de datos
			Connection con = ConexionBD.getConexion(prop);
			// Obtener el log
			PreparedStatement ps=con.prepareStatement(sql.getProperty("BuscarLog"));
			ps.setInt(1,log.getIdUsuario());// Indicamos id de usuario
			// Ejecutamos la sentencia sql
			ResultSet rs = ps.executeQuery();
			// Recorremos las filas de la base de datos
		
			//DateFormat d = DateFormat.getDateInstance();
			
			log.setIdUsuario(rs.getInt("ID_USUARIO")); // Obtenemos el identificador del usuario
				log.setFechaRegistro(rs.getDate("FECHA_REGISTRO"));// Obtenemos fecha de registro
				log.setFechaFinal(rs.getDate("FECHA_ACTUAL"));// Obtenemos fecha del ultimo log
				log.setPassword(rs.getString("CONTRASENA"));// Obtenemos la contrasena

			// Cerramos la sentencia rs
			rs.close();
			// Cerramos la sentencia ps
			ps.close();
			// Cerramos la conexion con la base de datos
			if(con != null) {
				con = null;
			}
		}catch(Exception ex) {
			System.out.println("Se ha producido un error al obtener los datos de la base de datos");
		}
		return log;
		
		
	}

public void borrarDatos(Properties prop,Properties sql,int id) {
	
	try {
	
	Connection con = ConexionBD.getConexion(prop);
	// Obtener el log
	PreparedStatement ps=con.prepareStatement(sql.getProperty("EliminarLog"));
	ps.setInt(1,id);// Indicamos id de usuario
	// Ejecutamos la sentencia sql
	ps.executeQuery();
	ps.close();
	// Cerramos la conexion con la base de datos
	if(con != null) {
		con = null;
	}
	}
	catch(Exception ex) {
		System.out.println("Se ha producido un error al eliminar los datos de la base de datos");
	}











}

	
}
