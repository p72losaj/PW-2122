package es.uco.pw.datos.dao.relacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import es.uco.pw.datos.dao.comun.conexionBD.ConexionBD;

/**
 * Clase que relaciona las criticas y los espectaculos
 * @author Jaime Lorenzo Sanchez
 * @version 1.0
 */

public class EspectaculoCriticaDAO {
	private int id; // Identificador de la tabla
	private int idCritica; // Identificador de la critica
	private String tituloEspectaculo; // Titulo del espectaculo
	private int puntuacionEspectaculo; // Puntuacion del espectaculo
	/**
	 * Funcion que obtiene la puntuacion dada a un espectaculo
	 * @return Puntuacion del espectaculo
	 */
	public int getPuntuacionEspectaculo() { return this.puntuacionEspectaculo; }
	/**
	 * Funcion que modifica la puntuacion de un espectaculo
	 * @param puntuacion Nueva puntuacion del espectaculo
	 */
	public void setPuntuacionEspectaculo(int puntuacion) { this.puntuacionEspectaculo = puntuacion; }
	/**
	 * Funcion que obtiene el titulo del espectaculo que referencia la critica
	 * @return Titulo del espectaculo que referencia la critica
	 */
	public String getTituloEspectaculo() { return this.tituloEspectaculo; }
	/**
	 * Funcion que modifica el titulo del espectaculo que referencia la critica
	 * @param titulo Nuevo titulo del espectaculo que referencia la critica
	 */
	public void setTituloEspectaculo(String titulo) { this.tituloEspectaculo = titulo; }
	/**
	 * Funcion que obtiene el identificador de la critica
	 * @return Identificador de la critica
	 */
	public int getIdentificadorCritica() { return this.idCritica; }
	/**
	 * Funcion que modifica el identificador de la critica
	 * @param id Nuevo identificador de la critica
	 */
	public void setIdentificadorCritica(int id) { this.idCritica = id; }
	/**
	 * Funcion que obtiene el identificador de la tabla Espectaculo_critica
	 * @return Identificador de la tabla Espectaculo_critica
	 */
	public int getIdentificadorEspectaculo_critica() { return this.id; }
	/**
	 * Funcion que modifica el identificador de la tabla Espectaculo_critica
	 * @param id Nuevo identificador de la tabla Espectaculo_critica
	 */
	public void setIdentificadorEspectaculo_critica(int id) { this.id = id; }
	/**
	 * Funcion que registra la puntuacion de un espectaculo dada por una critica
	 * @param prop Fichero de configuracion
	 * @param sql Fichero de propiedades
	 * @param identificadorCritica Identificador de la critica
	 * @param tituloEspectaculo Titulo del espectaculo
	 * @param puntuacion Puntuacion del espectaculo
	 * @return Numero de filas modificadas de la base de datos
	 */
	public int creacionRelacion(Properties prop, Properties sql, int identificadorCritica, String tituloEspectaculo,
			int puntuacion) {
		int status = 0; // Numero de filas modificadas de la base de datos
		try {
			Connection con = ConexionBD.getConexion(prop); // Conexion con la base de datos
			PreparedStatement ps=con.prepareStatement(sql.getProperty("InsercionPuntuacionEspectaculo")); // Sentencia sql que registra la puntuacion de un espectaculo
			ps.setInt(1, identificadorCritica); // Indicamos en la sentencia sql el identificador de la critica
			ps.setString(2, tituloEspectaculo); // Indicamos en la sentencia sql el titulo del espectaculo
			ps.setInt(3, puntuacion); // Indicamos en la sentencia sql la puntuacion del espectaculo
			status = ps.executeUpdate(); // Ejecutamos la sentencia sql
			ps.close(); // cierre de la sentencia sql
			if(con != null) {
				con = null; // Cierre de la conexion con la base de datos
			}
		}catch(Exception ex) {
			System.out.println("Se ha producido un error al registrar la puntuacion del espectaculo");
		}
		return status;
	}
	/**
	 * Funcion que elimina la puntuacion del espectaculo en funcion del identificador de la critica
	 * @param prop Fichero de configuracion
	 * @param sql Fichero de sentencias sql
	 * @param identificadorCritica Identificador de la critica
	 * @return Numero de filas modificadas de la base de datos
	 */
	public int eliminacionPuntuacionEspectaculoCritica(Properties prop, Properties sql, int identificadorCritica) {
		int status = 0; // Numero de filas modificadas de la base de datos
		try {
			Connection con = ConexionBD.getConexion(prop); // Conexion con la base de datos
			PreparedStatement ps=con.prepareStatement(sql.getProperty("EliminacionPuntuacionEspectaculo_Critica")); // Sentencia sql que elimina la puntuacion del espectaculo en funcion del identificador de la critica
			ps.setInt(1, identificadorCritica); // Indicamos en la sentencia sql el identificador de la critica a eliminar
			status = ps.executeUpdate(); // Ejecutamos la sentencia sql
			ps.close(); // Cerramos la sentencia sql
			if(con != null) {
				con = null; // cierre de la conexion con la base de datos
			}
		}catch(Exception ex) {
			ex.getMessage();
		}
		return status; // Retornamos el numero de filas modificadas de la base de datos
	}
	/**
	 * Funcion que obtiene el titulo del espectaculo de la critica
	 * @param prop Fichero de configuracion
	 * @param sql Fichero de sentencias sql
	 * @param identificadorCritica Identificador de la critica
	 * @return titulo del espectaculo
	 */
	public String obtencionTituloEspectaculo(Properties prop, Properties sql, int identificadorCritica) {
		String tituloEspectaculo = null; // Titulo del espectaculo
		try {
			Connection con = ConexionBD.getConexion(prop); // Conexion con la base de datos
			PreparedStatement ps=con.prepareStatement(sql.getProperty("ObtencionPuntuacionCritica")); // Sentencia sql que obtiene los datos de la puntuacion del espectaculo de una critica
			ps.setInt(1, identificadorCritica); // Indicamos en la sentencia sql el identificador de la critica
			ResultSet rs = ps.executeQuery(); // Ejecutamos la sentencia sql
			// Recorremos los datos obtenidos por la ejecucion de la sentencia sql
			while(rs.next()) {
				tituloEspectaculo = rs.getString("TITULO_ESPECTACULO"); // Obtenemos el titulo del espectaculo
			}
			rs.close(); // Cierre de la ejecucion de la sentencia sql
			ps.close(); // Cierre de la sentencia sql
			if(con != null) {
				con = null; // cierre de la conexion
			}
		}catch(Exception ex) {
			System.out.println("Se ha producido un error al obtener el identificador del espectaculo que referencia la critica " + identificadorCritica);
		}
		return tituloEspectaculo; // Retornamos el identificador del espectaculo
	}
	/**
	 * Funcion que obtiene la puntuacion del espectaculo
	 * @param prop Fichero de configuracion
	 * @param sql Fichero de sentencias sql
	 * @param identificadorCritica Identificador de la critica
	 * @return Puntuacion del espectaculo
	 */
	public int obtencionPuntuacionEspectaculo(Properties prop, Properties sql, int identificadorCritica) {
		int puntuacion = 0; // Puntuacion del espectaculo
		try {
			Connection con = ConexionBD.getConexion(prop); // Conexion con la base de datos
			PreparedStatement ps=con.prepareStatement(sql.getProperty("ObtencionPuntuacionCritica")); // Sentencia sql que obtiene los datos de la puntuacion del espectaculo de una critica
			ps.setInt(1, identificadorCritica); // Indicamos en la sentencia sql el identificador de la critica
			ResultSet rs = ps.executeQuery(); // Ejecutamos la sentencia sql
			// Recorremos los datos obtenidos por la ejecucion de la sentencia sql
			while(rs.next()) {
				puntuacion = rs.getInt("PUNTUACION"); // Obtenemos la puntuacion del espectaculo
			}
			rs.close(); // Cierre de la ejecucion de la sentencia sql
			ps.close(); // Cierre de la sentencia sql
			if(con != null) {
				con = null; // cierre de la conexion
			}
		}catch(Exception ex) {
			System.out.println("Se ha producido un error al obtener la puntuacion del espectaculo que referencia la critica " + identificadorCritica);
		}
		return puntuacion;
	}
	
	
	
}
