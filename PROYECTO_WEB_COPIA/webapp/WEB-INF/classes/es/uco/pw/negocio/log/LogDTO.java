package es.uco.pw.negocio.log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.Object;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Properties;
import es.uco.pw.datos.dao.log.LogDAO;
/**
 * Clase que gestiona la informacion de log
 * @author Jose Angel Exposito Fernandez
 *	@version 1.0
 */

public class LogDTO {
	
	/**
	 * Identificador del usuario
	 */
	
	private int idUsuario;
	
	/**
	 * Fecha de registro del usuario
	 */
	
	private java.sql.Date fechaRegistro = null; 
	
	/**
	 * Ultima fecha de logeo
	 */
	
	private java.sql.Date ultimaFecha = null;
	
	/**
	 * Contraseña del usuario
	 */
	
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
	
	public java.sql.Date getFechaRegistro() {
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
	
	
	public void insertarLog(int id, java.sql.Date d1, java.sql.Date d2, String pass, Properties prop, Properties sql) {
	
		LogDTO log = new LogDTO();
		LogDAO logger = new LogDAO();
		
		
		
		log.setIdUsuario(id); 
		log.setFechaRegistro(d1);
		log.setFechaFinal(d2);
		log.setPassword(pass);
		
		
		int n = logger.insertarLog(log, prop, sql);
		
		if(n < 0) {
			System.out.println("Error al insertar log");
			
		}
		
	}
		
		
	public Boolean ComprobarLog(int id, String pass, Properties prop, Properties sql) {
		
		LogDTO log = new LogDTO();
		LogDAO logger = new LogDAO();
		
		
		
		log.setIdUsuario(id); 
		log.setPassword(pass);
		
		
		 log = logger.obtenerDatosLog(prop, sql, id);
		
		if(!(log.getPassword().equals(pass))) {

			return false;
			
		}
		
		return true;
	}
		
		
	public Boolean ActualizarLog(int id, String pass, Properties prop, Properties sql) {
		
		LogDTO log = new LogDTO();
		LogDAO logger = new LogDAO();
		
		
		
		log.setIdUsuario(id); 
		log.setPassword(pass);
		
		
		 log = logger.obtenerDatosLog(prop, sql, id);
		
		if(!(log.getPassword().equals(pass))) {

			return false;
			
		}
		else {
			LocalDate fechaActual = LocalDate.now();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
			java.util.Date parsed = format.parse(fechaActual.toString());
			
			java.sql.Date fecha = new java.sql.Date(parsed.getTime());
			
			logger.borrarDatos(prop, sql, id);
			log.setFechaFinal(fecha);
			}
			catch(Exception ex) {
				System.out.println("Error con la fecha");
			}
			
			int n = logger.insertarLog(log, prop, sql);
			
			if(n < 0) {
				System.out.println("Error al insertar log");
				
			}

		}
			
			
		return true;
	}
		
			
	
		
}
