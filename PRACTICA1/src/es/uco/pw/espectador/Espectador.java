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
	
}
