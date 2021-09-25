package es.uco.pw.espectador;

import java.util.ArrayList;

/**
 * Clase que gestiona la informacion de un espectador
 * @author Jaime Lorenzo Sanchez
 *	@version 1.0
 */

public class Espectador {
	
	/**
	 * Nombre del espectador
	 */
	
	String nombre; 
	
	/**
	 * Primer apellido del espectador
	 */
	
	String apellido1;
	
	/**
	 * Segundo apellido del espectador
	 */
	
	String apellido2; 
	
	/**
	 * Nombre de usuario del espectador
	 */
	
	String nick; 
	
	/**
	 * Correo electronico unico del espectador
	 */
	
	String correo; 
	
	/**
	 * Lista de criticas realizadas por el espectador
	 */
	
	ArrayList <String> listaCriticas = new ArrayList<String>();
	
	/**
	 * Funcion que obtiene el nombre del espectador
	 * @return Nombre del espectador
	 */
	
	public String getNombreEspectador() {
		return this.nombre;
	}
	
	/**
	 * Funcion que modifica el nombre de un espectador
	 * @param nombre Nuevo nombre del espectador
	 */
	
	public void setNombreEspectador(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Funcion que obtiene el primer apellido del espectador
	 * @return Primer apellido del espectador
	 */
	
	public String getPrimerApellidoEspectador() {
		return this.apellido1;
	}
	
	/**
	 * Funcion que modifica el primer apellido del espectador
	 * @param apellido1 Nuevo primer apellido del espectador
	 */
	
	public void setPrimerApellidoEspectador(String apellido1) {
		this.apellido1 = apellido1;
	}
	
	/**
	 * Funcion que obtiene el nick del espectador
	 * @return Nick del espectador
	 */
	
	public String getNickEspectador() {
		return this.nick;
	}
	
	/**
	 * Funcion que modifica el nick del espectador
	 * @param nick Nuevo nick del espectador
	 */
	
	public void setNickEspectador(String nick) {
		this.nick = nick;
	}
	
	/**
	 * Funcion que obtiene el correo del espectador
	 * @return Correo del espectador
	 */
	
	public String getCorreoEspectador() {
		return this.correo;
	}
	
	/**
	 * Funcion que modifica el correo del espectador
	 * @param correo Nuevo correo del espectador
	 */
	
	public void setCorreoEspectador(String correo) {
		this.correo = correo;
	}
	
	/**
	 * Funcion que obtiene los titulos de las criticas escritas por el espectador
	 * @return Lista de titulos de las criticas escritas por el espectador
	 */
	
	public ArrayList<String> getListaCriticasPropias(){
		return this.listaCriticas;
	}
	
	/**
	 * Funcion que modifica la lista de criticas escritas por el espectador
	 * @param criticas Nueva lista de criticas escritas por el espectador
	 */
	
	public void setListaCriticasPropias(ArrayList <String> criticas) {
		this.listaCriticas = criticas;
	}
	
}
