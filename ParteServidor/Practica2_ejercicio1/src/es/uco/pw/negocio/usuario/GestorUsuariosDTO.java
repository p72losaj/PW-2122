package es.uco.pw.negocio.usuario;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import es.uco.pw.datos.dao.comun.conexionBD.ConexionBD;
import es.uco.pw.datos.dao.usuario.UsuarioDAO;

/**
 * Gestor de espectadores
 * @author Jaime Lorenzo Sanchez
 * @version 2.0
 */

public class GestorUsuariosDTO {
	
	/**
	 * Lista de espectadores
	 */
	
	ArrayList<UsuarioDTO> listaEspectadores = new ArrayList<UsuarioDTO>();
	

	/**
	 * Constructor de clase vacio
	 */
	public GestorUsuariosDTO() {
		
	}
	
	
	/**
	 * Funcion que comprueba si un usuario esta registrado en el sistema
	 * @param nick Nombre de usuario del espectador
	 * @return true si el usuario esta registrado; false en caso contrario
	 */
	
	
	public Boolean comprobarExistenciaNickUsuario(String nick) {

		// Recorremos la lista de espectadores
		
		for(int i=0; i < this.listaEspectadores.size(); i++) 
		{
			
			// Comprobamos si se ha encontrado el nombre de usuario del espectador
			
			if(this.listaEspectadores.get(i).getNickEspectador().equals(nick) ) 
			{
				return true; // Se ha encontrado el nombre de usuario del espectador
			} // fin de la comprobacion de la existencia del nick del usuario
			
		} // fin recorrido de la lista de espectadores
		
		return false; // Por defecto, se retorna false
	
	}
	
	/**
	 * Funcion que comprueba si el correo de un usuario esta registrado en el sistema
	 * @param correo Correo a comprobar si esta registrado
	 * @return true en caso de que el correo este registrado; false en caso contrario
	 */
	
	public Boolean comprobarExistenciaCorreoEspectador(String correo) 
	{
		
		// Recorremos la lista de espectadores
		
		for(int i=0; i < this.listaEspectadores.size(); i++) {
			
			// Correo encontrado. Retornamos true
			
			if(this.listaEspectadores.get(i).getCorreoEspectador().equals(correo)) 
			{
				return true;
			} // fin de la comprobacion de la existencia del correo del espectador
			
		} // fin del recorrido de la lista de espectadores
		
		return false; // Por defecto, retornamos false
	}
	
	
	/**
	 * Funcion que muestra por pantalla los datos de los espectadores
	 */

	public void visualizarDatosEspectadores() {
		
		for(int i=0; i<this.listaEspectadores.size(); i++) {
			System.out.println("Identificador del usuario: " + this.listaEspectadores.get(i).getIdUsuario());
			System.out.println("Nombre del usuario: " + this.listaEspectadores.get(i).getNombreEspectador());
			System.out.println("Primer apellido del usuario: " + this.listaEspectadores.get(i).getPrimerApellidoEspectador());
			System.out.println("Segundo apellido del usuario: " + this.listaEspectadores.get(i).getSegundoApellidoEspectador());
			System.out.println("Nick del usuario: " + this.listaEspectadores.get(i).getNickEspectador());
			System.out.println("Correo del usuario: " + this.listaEspectadores.get(i).getCorreoEspectador());
			System.out.println("Rol del usuario: " + this.listaEspectadores.get(i).getRolUsuario());
		}
		
	}
	
	/**
	 * Funcion que obtiene los datos de un usuario registrado
	 * @param correo Correo del usuario
	 * @return Datos del usuario registrado
	 */

	public UsuarioDTO obtenerDatosUsuario(String correo) {
		// Creamos un espectador vacio
		UsuarioDTO espectador = new UsuarioDTO();
		// Recorremos la lista de espectadores
		for(int i=0; i < this.listaEspectadores.size(); i++) {
			// Obtenemos los datos del espectador
			if(this.listaEspectadores.get(i).getCorreoEspectador().equals(correo)) {
				espectador.setIdUsuario(this.listaEspectadores.get(i).getIdUsuario());
				espectador.setNombreEspectador(this.listaEspectadores.get(i).getNombreEspectador());
				espectador.setPrimerApellidoEspectador(this.listaEspectadores.get(i).getPrimerApellidoEspectador());
				espectador.setSegundoApellidoEspectador(this.listaEspectadores.get(i).getSegundoApellidoEspectador());
				espectador.setNickEspectador(this.listaEspectadores.get(i).getNickEspectador());
				espectador.setCorreoEspectador(correo);
				espectador.setRolUsuario(this.listaEspectadores.get(i).getRolUsuario());
			}
		}
		return espectador;// Retornamos los datos del usuario registrado
	}
	
	/**
	 * Funcion que obtene la lista de usuarios del gestor de usuarios
	 * @return Lista de usuarios
	 */
	
	public ArrayList<UsuarioDTO> getListaEspectadores() {
		return this.listaEspectadores;
	}

	/**
	 * Funcion que modifica la lista de usuarios del gestor de usuarios
	 * @param listaEspectadores Nueva lista de usuarios
	 */

	public void setListaEspectadores(Properties prop, Properties sql) {
		UsuarioDAO usuarios = new UsuarioDAO();
		this.listaEspectadores = usuarios.obtenerUsuarios( sql,  prop);
	}

	/**
	 * Funcion que registra los datos de un usuario en la base de datos
	 * @param prop Fichero de configuracion
	 * @param sql Fichero de sentencias sql
	 * @param correo Correo del usuario
	 * @param nombre Nombre del usuario
	 * @param apellido1 Primer apellido del usuario
	 * @param apellido2 segundo apellido del usuario
	 * @param nick Nick del usuario
	 * @param rol Rol del usuario 
	 * @return Estado de la insercion de los datos del usuario
	 */

	public String registrarUsuario(Properties prop, Properties sql, String correo, String nombre, String apellido1, String apellido2, String nick, String rol) {
		UsuarioDTO usuarioDTO = new UsuarioDTO(); 
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		String estado = new String(); // Estado del registro de los datos del usuario
		/*
		 * DATOS UNICOS DEL USUARIO
		 */
		Boolean validezCorreo = usuarioDTO.comprobarValidezCorreo(correo); // Comprobamos si el correo es valido
		Boolean encontrado = comprobarExistenciaCorreoEspectador(correo); // Comprobamos si el correo ya esta registrado en la base de datos
		Boolean existenciaNick = comprobarExistenciaNickUsuario(nick); // Comprobamos si el nick ya esta registrado en la base de datos 
		/*
		 * Caso 1: Correo no valido
		 */
		if(validezCorreo == false) {estado = "Correo " + correo + " no es valido"; }
		/*
		 * Caso 2: Correo ya registrado
		 */
		else if(encontrado == true) { estado = "Correo del usuario " + correo + " ya estaba registrado"; }
		/*
		 * Caso 3: Nick ya registrado
		 */
		else if(existenciaNick == true) { estado = "Nick del usuario " + nick + " ya estaba registrado";}
		/*
		 * DATOS UNICOS DEL USUARIO NO REGISTRADOS EN LA BASE DE DATOS
		 */
		else {
			usuarioDTO.setCorreoEspectador(correo); // Almacenamos el correo del usuario
			usuarioDTO.setNombreEspectador(nombre); // Almacenamos el nombre del usuario
			usuarioDTO.setPrimerApellidoEspectador(apellido1); // Almacenamos el primer apellido del usuario
			usuarioDTO.setSegundoApellidoEspectador(apellido2); // Almacenamos el segundo apellido del usuario
			usuarioDTO.setNickEspectador(nick); // Almacenamos el nick del usuario
			if(rol.equals("administrador")) { usuarioDTO.setRolUsuario(RolUsuario.administrador);} // El rol del usuario es administrador
			else if(rol.equals("espectador")) { usuarioDTO.setRolUsuario(RolUsuario.espectador); } // El rol del usuario es espectador
			int status = usuarioDAO.insertarUsuario(usuarioDTO,prop,sql); // Almacenamos los datos en la base de datos
			/*
			 * USUARIO ANADIDO A LA BASE DE DATOS
			 */
			if(status != 0) {
				/*
				 * OBTENCION DEL IDENTIFICADOR DEL USUARIO REGISTRADO
				 */
				int identificador = usuarioDAO.obtencionIdentificadorUsuario(prop,sql,usuarioDTO.getCorreoEspectador());
				// Caso de error: Identificador del usuario no obtenido
				if(identificador == 0) { estado = "Identificador del usuario registrado no obtenido"; }
				// Identificador del usuario obtenido
				else {
					usuarioDTO.setIdUsuario(identificador); // Almacenamos el identificador del usuario
					this.listaEspectadores.add(usuarioDTO); // Anadimos los datos del usuario al gestor de usuarios
					estado = "Registro del usuario en la base de datos ha sido un exito"; // Indicamos el estado del registro del usuario
				}
			}
			else { estado = "Error al registrar los datos del usuario en la base de datos"; }
		}
		return estado; // Retornamos el estado del registro de los datos del usuario
	}

	/**
	 * Funcion que obtiene el rol del usuario almacenado en el gestor
	 * @param correoUsuario Correo del usuario
	 * @return Rol del usuario
	 */

	public String obtencionRolUsuario(String correoUsuario) {
		String rol = null; // Rol por defecto del usuario
		for(int i=0; i < this.listaEspectadores.size(); i++) { // Recorremos la lista de usuarios
			if(this.listaEspectadores.get(i).getCorreoEspectador().equals(correoUsuario)) { rol = this.listaEspectadores.get(i).getRolUsuario().toString(); } // Obtenemos el rol del usuario
		}
		return rol; // Retornamos el rol del usuario
	}

	/**
	 * Funcion que obtiene el identificador del usuario registrado en el gestor de usuarios
	 * @param correo Correo del usuario
	 * @return Identificador del usuario registrado en el gestor de usuarios
	 */

	public int obtencionIdentificadorUsuario(String correo) {
		int identificador = 0;
		for(int i=0; i < this.listaEspectadores.size(); i++) {
			if(this.listaEspectadores.get(i).getCorreoEspectador().equals(correo)) {
				identificador = this.listaEspectadores.get(i).getIdUsuario();
			}
		}
		return identificador;
	}


	

}
