package es.uco.pw.datos.dao.relacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import es.uco.pw.datos.dao.comun.conexionBD.ConexionBD;
import es.uco.pw.negocio.critica.EvaluacionUtilidadCriticaDTO;

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
	public int registrovaloracionUtilidadCritica(Properties prop, Properties sql, int identificadorCritica, int idUsuario,
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
	
	/**
	 * Funcion que elimina la valoracion de la utilidad de una critica en funcion del identificador de la critica
	 * @param prop Fichero de configuracion
	 * @param sql Fichero de sentencias sql
	 * @param identificadorCritica Identificador de la critica eliminada
	 * @return Numero de filas modificadas de la base de datos
	 */
	public int eliminarValoracionUtilidadIdentificadorCritica(Properties prop, Properties sql, int identificadorCritica) {
		int status = 0; // Numero de filas modificadas de la base de datos
		try {
			Connection con = ConexionBD.getConexion(prop); // Conexion con la base de datos
			PreparedStatement ps=con.prepareStatement(sql.getProperty("EliminacionValoracionUtilidadCritica")); // Sentencia sql para eliminar la valoracion de utilidad de una critica en funcion del identificador de la critica
			ps.setInt(1, identificadorCritica); // Indicamos en la sentencia sql el identificador de la critica
			status = ps.executeUpdate(); // Ejecutamos la sentencia sql
			ps.close(); // Cerremos la sentencia sql
			if(con != null) {
				con = null; // Cerramos la conexion con la base de datos
			}
		}catch(Exception ex) {
			System.out.println("Se ha producido un error al eliminar la valoracion de utilidad de la critica");
		}
		return status;
	}
	/**
	 * Funcion que obtiene la valoracion de utilidad de la critica por un usuario
	 * @param prop Fichero de configuracion
	 * @param sql Fichero de sentencias sql
	 * @param identificadorCritica Identificador de la critica
	 * @param idUsuario Identificador del usuario
	 * @return Datos de la valoracion de utilidad de la critica por el usuario
	 */
	public EvaluacionUtilidadCriticaDTO obtencionValoracionCriticaUsuario(Properties prop, Properties sql,
			int identificadorCritica, int idUsuario) {
		EvaluacionUtilidadCriticaDTO valoracion = new EvaluacionUtilidadCriticaDTO(); // Creamos una nueva valoracion de utilidad de la critica
		valoracion.setIdentificadorCritica(identificadorCritica); // almacenamos el identificador de la critica
		valoracion.setIdentificadorAutor(idUsuario); // Almacenamos el identificador del usuario
		valoracion.setValoracionUtilidadCritica(-1); // Por defecto, la valoracion de utilidad de la critica es -1
		try {
			Connection con = ConexionBD.getConexion(prop); // Conexion con la base de datos
			PreparedStatement ps=con.prepareStatement(sql.getProperty("ObtencionValoracionesCritica")); // Sentencia sql para obtener las valoraciones de utilidad de una critica
			ps.setInt(1, identificadorCritica); // Indicamos en la sentencia sql el identificador de la critica
			ResultSet rs = ps.executeQuery(); // Ejecutamos la sentencia sql
			while(rs.next() ) { // Recorremos las filas obtenidas en la ejecucion de la sentencia sql
				if(rs.getInt("ID_AUTOR") == idUsuario) { // Identificador del usuario encontrado
					valoracion.setValoracionUtilidadCritica(rs.getInt("VALORACION_UTILIDAD")); // Obtenemos la valoracion de utilidad de la critica
				}
			}
			rs.close(); // Cierre de la ejecucion de la sentencia sql
			ps.close(); // cierre de la sentencia sql
			if(con != null) {con = null;} // Cierre de la conexion
		}catch(Exception ex) {System.out.println("Se ha producido un error al obtener los datos de valoracion de utilidad de una critica");}
		return valoracion; // Retornamos los datos de la valoracion de utilidad
	}
	/**
	 * Funcion que obtiene la lista de evaluaciones de utilidad de una critica
	 * @param prop fichero de configuracion
	 * @param sql fichero de sentencias sql
	 * @param identificadorCritica Identificador de la critica
	 * @return Evaluaciones de utilidad de una critica
	 */
	public ArrayList<EvaluacionUtilidadCriticaDTO> obtencionEvaluacionesCritica(Properties prop, Properties sql,
			int identificadorCritica) {
		ArrayList<EvaluacionUtilidadCriticaDTO> listaEvaluaciones = new ArrayList<EvaluacionUtilidadCriticaDTO>(); // Lista de evaluaciones de utilidad de la critica
		try {
			Connection con = ConexionBD.getConexion(prop); // Conexion con la base de datos
			PreparedStatement ps=con.prepareStatement(sql.getProperty("ObtencionValoracionesCritica")); // Sentencia sql para obtener las valoraciones de utilidad de una critica
			ps.setInt(1, identificadorCritica); // Indicamos en la sentencia sql el identificador de la critica
			ResultSet rs = ps.executeQuery(); // Ejecutamos la sentencia sql
			while(rs.next() ) { // Recorremos las filas obtenidas en la ejecucion de la sentencia sql
				EvaluacionUtilidadCriticaDTO evaluacion = new EvaluacionUtilidadCriticaDTO(); // Creamos una evaluacion de utilidad de la critica vacia
				evaluacion.setIdentificadorValoracion(rs.getInt("ID"));// Obtenemos el identificador de la evaluacion
				evaluacion.setIdentificadorCritica(identificadorCritica); // Obtenemos el identificador de la critica
				evaluacion.setIdentificadorAutor(rs.getInt("ID_AUTOR")); // Obtenemos el identificador del autor de la critica
				evaluacion.setValoracionUtilidadCritica(rs.getInt("VALORACION_UTILIDAd")); // Obtenemos la valoracion de utilidad de la critica
				listaEvaluaciones.add(evaluacion); // Anadimos los datos de la evaluacion a la lista de evaluaciones de utilidad
			}
			rs.close(); // Cierre de la ejecucion de la sentencia sql
			ps.close(); // cierre de la sentencia sql
			if(con != null) { con = null; } // cierre de la conexion con la base de datos
		}catch(Exception ex) {
			System.out.println("Se ha producido un error al obtener la lista de evaluaciones de utilidad de la critica");
		}
		return listaEvaluaciones; // Recornamos los datos de las evaluaciones de utilidad de la critica
	}
	/**
	 * 
	 */
	public EvaluacionUtilidadCriticaDTO obtencionValoracionCriticaIdentificador(Properties prop, Properties sql,
			int identificadorEliminar) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
