package es.uco.pw.negocio.usuario;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase que gestiona la informacion de un espectador
 * @author Jaime Lorenzo Sanchez
 *	@version 2.0
 */

public class UsuarioDTO {
	
	/**
	 * Identificador del usuario
	 */
	
	private int idUsuario;
	
	/**
	 * Nombre del espectador
	 */
	
	private String nombre = null; 
	
	/**
	 * Primer apellido del espectador
	 */
	
	private String apellido1 = null;
	
	/**
	 * Segundo apellido del espectador
	 */
	
	private String apellido2 = null; 
	
	/**
	 * Nombre de usuario del espectador
	 */
	
	private String nick = null; 
	
	/**
	 * Correo electronico unico del espectador
	 */
	
	private String correo = null; 
	
	/**
	 * Rol del usuario
	 */
	
	private RolUsuario rolUsuario;
	
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
	 * Funcion que obtiene el segundo apellido del espectador
	 * @return Segundo apellido del espectador
	 */
	
	public String getSegundoApellidoEspectador() {
		return this.apellido2;
	}
	
	/**
	 * Funcion que modifica el segundo apellido del espectador
	 * @param apellido2 Nuevo segundo apellido del espectador
	 */
	
	public void setSegundoApellidoEspectador(String apellido2) {
		this.apellido2 = apellido2;
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
	 * Funcion que comprueba si un correo es valido o no
	 * @param correo Correo a validar
	 * @return true si el correo es valido; false en caso contrario
	 */

	public Boolean comprobarValidezCorreo(String correo) {
		
		// Patr??n para validar el email
        
		Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		
		Matcher mather = pattern.matcher(correo);
		
		// Correo es valido
		
		if(mather.find() == true) {
			return true;
		}
		
		return false;
	}

	/**
	 * Funcion que obtiene el rol del usuario
	 * @return Rol del usuario
	 */
	public RolUsuario getRolUsuario() {
		return this.rolUsuario;
	}

	/**
	 * Funcion que modifica el rol del usuario
	 * @param rolUsuario Nuevo rol del usuario
	 */
	public void setRolUsuario(RolUsuario rolUsuario) {
		this.rolUsuario = rolUsuario;
	}
	/**
	 * Funcion que muestra los datos de un usuario
	 */
	public void imprimirUsuario() {
		System.out.println("\tIdentificador del usuario: " + this.idUsuario); // Mostramos el identificador del usuario
		System.out.println("\tNombre del usuario: " + this.nombre); // Mostramos el nombre del usuario
		System.out.println("\tPrimer apellido del usuario: " + this.apellido1); // Mostramos el primer apellido del usuario
		System.out.println("\tSegundo apellido del usuario: " + this.apellido2); // Mostramos el segundo apellido del usuario
		System.out.println("\tNick del usuario: " + this.nick); // Mostramos el nick del usuario
		System.out.println("\tCorreo del usuario: " + this.correo); // Mostramos el correo del usuario
	}

	
	
}
