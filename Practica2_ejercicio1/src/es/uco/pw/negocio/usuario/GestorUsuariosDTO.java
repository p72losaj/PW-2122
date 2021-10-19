package es.uco.pw.negocio.usuario;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import es.uco.pw.datos.dao.comun.conexionBD.ConexionBD;

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

	public void setListaEspectadores(ArrayList<UsuarioDTO> listaEspectadores) {
		this.listaEspectadores = listaEspectadores;
	}


	

}
