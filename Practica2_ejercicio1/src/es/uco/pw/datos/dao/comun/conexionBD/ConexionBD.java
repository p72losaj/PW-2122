package es.uco.pw.datos.dao.comun.conexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Clase que gestiona la conexion con la base de datos
 * @author Jaime Lorenzo Sanchez
 * @version 1.0
 */

public class ConexionBD {
	
	/**
	 * Funcion que obtiene la conexion con la base de datos
	 * @param prop Fichero de propiedades
	 * @return Conexion con la base de datos
	 */

	public static Connection getConexion(Properties prop) {
		
		Connection con=null; // Por defecto, la conexion es nula
		try 
		{
			// Obtenemos el driver de mysql
			
			Class.forName(prop.getProperty("Driver"));
		  
			// Obtenemos los datos de conexion con la base de datos
		  
			con= DriverManager.getConnection(prop.getProperty("mysql"),prop.getProperty("usuarioUCO"),prop.getProperty("contrasenaUCO"));
		  
		} catch(Exception e) {
			System.out.println("Los datos de conexion con la base de datos no son correctos o no se esta utilizando una direccion IP de la UCO");
		}
		
		return con;
	}
}
