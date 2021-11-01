package es.uco.pw.data.dao.relacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;

import es.uco.pw.datos.dao.comun.conexionBD.ConexionBD;

/**
 * Clase que gestiona la tabla de relacion de los usuarios y las criticas de la base de datos
 * @author Jaime Lorenzo Sanchez
 * @version 1.0
 */

public class UsuarioCriticaDAO {
	private int id; // Identificador de la tabla de relacion
	private int idCritica; // Identificador de la critica
	private int idAutor; // Identificador del autor de la valoracion de utilidad de la critica
	private int utilidadCritica; // Valoracion de la utilidad de la critica
	/**
	 * Funcion que obtiene la valoracion de utilidad de la critica
	 * @return Valoracion de utilidad de la critica
	 */
	public int getUtilidadCritica() { return this.utilidadCritica; }
	/**
	 * Funcion que modifica la valoracion de utilidad de la critica
	 * @param id Nueva valoracion de utilidad de la critica
	 */
	public void setUtilidadCritica(int id) { this.utilidadCritica = id; }
	/**
	 * Funcion que obtiene el identificador del autor de la valoracion de utilidad de la critica
	 * @return Identificador del autor de la valoracion
	 */
	public int getIdentificadorAutor() { return this.idAutor; }	
	/**
	 * Funcion que modifica el identificador del autor de la valoracion de utilidad de la critica
	 * @param id Nuevo identificador del autor de valoracion de utilidad de la critica
	 */
	public void setIdentificadorAutor(int id) { this.idAutor = id; }
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
	 * Funcion que obtiene el identificador de la relacion de los usuarios y criticas
	 * @return Identificador de la relacion de usuarios y criticas
	 */
	public int getIdentificadorUsuarioCritica() { return this.id; }
	/**
	 * Funcion que modifica el identificador de la relacion de los usuarios y criticas
	 * @param id Nuevo identificador de la relacion usuarios y criticas
	 */
	public void setIdentificadorUsuarioCritica(int id) { this.id = id; }
	/**
	 * Funcion que registra la valoracion de utilidad de una critica
	 * @param prop Fichero de configuracion
	 * @param sql Fichero de sentencias sql
	 * @param identificadorCritica Identificador de la critica
	 * @param idUsuario Identificador del usuario
	 * @param valoracion Valoracion de utilidad del usuario
	 * @return Numero de filas modificadas de la base de datos
	 */
	public int valoracionUtilidadCritica(Properties prop, Properties sql, int identificadorCritica, int idUsuario,
			int valoracion) {
		int status = 0; // Numero de filas modificadas de la base de datos
		try {
			Connection con = ConexionBD.getConexion(prop); // Conexion con la base de datos
			PreparedStatement ps=con.prepareStatement(sql.getProperty("InsercionValoracionUtilidadCritica")); // Sentencia sql para registrar la valoracion de utilidad de una critica
			ps.setInt(1, identificadorCritica); // Indicamos en la sentencia sql el identificador de la critica
			ps.setInt(2, idUsuario); // Indicamos en la sentencia sql el identificador del usuario
			ps.setInt(3, valoracion); // Indicamos en la sentencia sql la valoracion de utilidad de la critica
			status = ps.executeUpdate(); // Ejecutamos la sentencia sql
			ps.close(); // Cierre de la sentencia sql
			if(con != null) {
				con = null; // Cierre de la conexion con la base de datos
			}
		}catch(Exception ex) {
			System.out.println("Se ha producido un error al registrar la valoracion de utilidad de la critica en la base de datos");
		}
		return status;
	}
}
