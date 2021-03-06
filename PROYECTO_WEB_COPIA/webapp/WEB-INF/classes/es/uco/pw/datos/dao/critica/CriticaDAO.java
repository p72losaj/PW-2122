package es.uco.pw.datos.dao.critica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import es.uco.pw.datos.dao.comun.conexionBD.ConexionBD;
import es.uco.pw.datos.dao.relacion.EspectaculoCriticaDAO;
import es.uco.pw.datos.dao.relacion.UsuarioCriticaDAO;
import es.uco.pw.negocio.critica.CriticaDTO;

/**
 * Clase que gestiona la informacion de las criticas en la base de datos
 * @author Jaime Lorenzo Sanchez
 * @version 1.0
 */

public class CriticaDAO {
	private int idCritica; // Identificador de la critica
	private String tituloCritica; // Titulo de la critica
	private String resenaCritica; // Resena de la critica
	private String autorCritica; // Autor de la critica
	/**
	 * Funcion que obtiene el autor de la critica
	 * @return Correo del autor de la critica
	 */
	public String getAutorCritica() {
		return this.autorCritica;
	}
	/**
	 * Funcion que modifica el autor de una critica
	 * @param autor Nuevo correo del autor de la critica
	 */
	public void setAutorCritica(String autor) {
		this.autorCritica = autor;
	}
	/**
	 * Funcion que obtiene la resena de una critica
	 * @return Resena de la critica
	 */
	public String getResenaCritica() {
		return this.resenaCritica;
	}
	/**
	 * Funcion que modifica la resena de la critica
	 * @param resena Nueva resena de la critica
	 */
	public void setResenaCritica(String resena) {
		this.resenaCritica = resena;
	}
	/**
	 * Funcion que obtiene el titulo de una critica
	 * @return Titulo de la critica
	 */
	public String getTituloCritica() {
		return this.tituloCritica;
	}
	/**
	 * Funcion que modifica el titulo de una critica
	 * @param titulo Nuevo titulo de la critica
	 */
	public void setTituloCritica(String titulo) {
		this.tituloCritica = titulo;
	}
	/**
	 * Funcion que obtiene el identificador de una critica
	 * @return Identificador de la critica
	 */
	public int getIdentificadorCritica() {
		return this.idCritica;
	}
	/**
	 * Funcion que modifica el identificador de una critica
	 * @param id Nuevo identificador de la critica
	 */
	public void setIdentificadorCritica(int id) {
		this.idCritica = id;
	}
	/**
	 * Funcion que obtiene la informacion de las criticas registradas en la base de datos
	 * @param config Fichero de configuracion
	 * @param sql Fichero de sentencias sql
	 * @return Criticas registradas en la base de datos
	 */
	public ArrayList<CriticaDTO> obtencionCriticas(Properties config, Properties sql){
		ArrayList<CriticaDTO> criticas = new ArrayList<CriticaDTO>(); // Creamos una lista de criticas vacia
		try {
			Connection con = ConexionBD.getConexion(config); // Conexion con la base de datos
			PreparedStatement ps=con.prepareStatement(sql.getProperty("ObtencionCriticas")); // Sentencia sql para obtener la informacion de todas las criticas
			ResultSet rs = ps.executeQuery(); // Ejecucion de la sentencia sql
			while(rs.next()) { // Recorremos las filas de la tabla de la base de datos
				CriticaDTO critica = new CriticaDTO(); // Creacion de una critica vacia
				critica.setIdentificadorCritica(rs.getInt("ID")); // Obtenemos el identificador de la critica
				critica.setTituloCritica(rs.getString("TITULO")); // Obtenemos el titulo de la critica
				critica.setResenaCritica(rs.getString("RESENA")); // Obtenemos la resena de la critica
				critica.setAutorCritica(rs.getString("AUTOR")); // Obtenemos el autor de la critica
				criticas.add(critica); // Guardamos la informacion de la critica en el gestor
			}
			rs.close(); // Finalizacion de la ejecucion de la sentencia sql
			ps.close(); // Cierre de la sentencia sql
			if(con != null) {
				con = null; // Cierre de la conexion con la base de datos
			}
		}catch(Exception ex) {
			System.out.println("Se ha producido un error al obtener los datos de las criticas");
		}
		return criticas; // Retornamos la lista de criticas
	}
	/**
	 * Funcion que anade los datos de una critica en la base de datos
	 * @param prop Fichero de configuracion
	 * @param sql Fichero de sentencias sql
	 * @param criticaDTO Critica a insertar en la base de datos
	 * @return Numero de filas insertadas en la base de datos
	 */
	public int insercionCritica(Properties prop, Properties sql, CriticaDTO criticaDTO) {
		int status = 0; // Numero de filas modificadas de la base de datos
		try {
			Connection con = ConexionBD.getConexion(prop); // Conexion con la base de datos
			PreparedStatement ps=con.prepareStatement(sql.getProperty("InsercionCritica")); // Sentencia sql para insertar una critica en la base de datos
			ps.setString(1,criticaDTO.getTituloCritica()); // Indicamos en la sentencia sql el titulo de la critica a insertar
			ps.setString(2, criticaDTO.getResenaCritica()); // Indicamos en la sentencia sql la resena de la critica a insertar
			ps.setString(3, criticaDTO.getAutorCritica()); // Indicamos en la sentencia sql el autor de la critica
			status = ps.executeUpdate(); // Ejecutamos la sentencia sql
			ps.close(); // finalizacion de la sentencia sql
			if(con != null) {
				con = null; // Cerramos la conexion con la base de datos
			}
		}catch(Exception ex) {
			System.out.println("Se ha producido un error al insertar los datos de la critica en la base de datos");
		}
		return status; // Retornamos el numero de filas modificadas de la base de datos
	}
	/**
	 * Funcion que elimina los datos de una critica
	 * @param prop Fichero de configuracion
	 * @param sql Fichero de propiedades
	 * @param identificadorEliminar Identificador de la critica
	 * @return Numero de filas modificadas de la base de datos
	 */
	public int eliminacionCritica(Properties prop, Properties sql, int identificadorEliminar) {
		int status = 0; // Numero de filas modificadas de la base de datos
		try {
			Connection con = ConexionBD.getConexion(prop); // Conexion con la base de datos
			PreparedStatement ps=con.prepareStatement(sql.getProperty("EliminacionCriticaIdentificador")); // Sentencia sql para eliminar una critica en funcion de su titulo
			ps.setInt(1, identificadorEliminar); // Indicamos en la sentencia sql el titulo de la critica a eliminar
			status = ps.executeUpdate(); // Ejecutamos la sentencia sql
			ps.close(); // Cerramos la sentencia sql
			if(con != null) {
				con = null; // Cierre de la conexion con la base de datos
			}
		}catch(Exception ex) {
			System.out.println("Se ha producido un error al eliminar los datos de la critica");
		}
		return status; // Retornamos el numero de filas modificadas de la base de datos
	}
	/**
	 * Funcion que obtiene el identificador de una critica
	 * @param prop Fichero de configuracion
	 * @param sql Fichero de sentencias sql
	 * @param tituloCritica Titulo de la critica
	 * @return Identificador de la critica
	 */
	public int obtencionIdentificadorCritica(Properties prop, Properties sql, String tituloCritica) {
		int identificador = 0; // Identificador de la critica
		try {
			Connection con = ConexionBD.getConexion(prop); // Conexion con la base de datos
			PreparedStatement ps=con.prepareStatement(sql.getProperty("BusquedaCriticaTitulo")); // Sentencia sql para obtener la informacion de una critica dado su titulo
			ps.setString(1, tituloCritica); // Indicamos en la sentencia sql el titulo de la critica
			ResultSet rs = ps.executeQuery(); // Ejecucion de la sentencia sql
			while(rs.next()) { // Recorremos las filas obtenidas al ejecutar la sentencia sql
				identificador = rs.getInt("ID");
			}
			rs.close(); // Cierre de la ejecucion de la sentencia sql
			ps.close(); // Cierre de la sentencia sql
			if(con != null) {
				con = null; // Cierre de la conexion
			}
		}catch(Exception ex) {
			System.out.println("Se ha producido un error al obtener el identificador de la critica");
		}
		return identificador; // Retornamos el identificador de la critica
	}
	
}
