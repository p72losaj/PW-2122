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
	
	ArrayList<UsuarioDTO> listaEspectadores;

	/**
	 * Constructor de clase vacio
	 */
	public GestorUsuariosDTO() {
		
	}
	
	/**
	 * Funcion que anade un nuevo espectador a la lista de espectadores
	 * @param entrada Buffer de entrada
	 * @param espectador Nuevo espectador a anadir en la lista de espectadores
	 * @param entrada buffer de entrada
	 * @return 1 si se ha anadido el espectador a la lista de espectadores; 0 en caso contrario
	 */
	
	public int anadirNuevoEspectador(Scanner entrada) 
	{
		
		UsuarioDTO espectador = new UsuarioDTO(); // Creamos un espectador vacio

		entrada = new Scanner(System.in); // Limpiamos el buffer de entrada 
		
		System.out.println("Introduzca su nombre de usuario: ");
		
		espectador.setNickEspectador(entrada.nextLine()); // Almacenamos el nick del espectador
		
		// Comprobamos si el usuario esta ya registrado
		
		if(comprobarExistenciaNickUsuario(espectador.getNickEspectador() ) == true) {
			
			// El nombre de usuario del espectador esta registrado
			
			System.out.println("El nombre de usuario ya esta registrado");
			
			return 0;
		
		} // Fin de la comprobacion de la existencia del nombre de usuario
		
		// El nombre de usuario del espectador no esta registrado
		
		else {
			
			// Pedimos el nombre del usuario
			
			System.out.println("Introduzca su nombre: ");
			
			espectador.setNombreEspectador(entrada.nextLine()); // Almacenamos el nombre del espectador
			
			// Pedimos el primer apellido del usuario
			
			System.out.println("Introduzca su primer apellido: ");
			
			espectador.setPrimerApellidoEspectador(entrada.nextLine()); // Almacenamos el primer apellido del espectador
			
			// Pedimos el segundo apellido del usuario
			
			System.out.println("Introduzca su segundo apellido: ");
			
			espectador.setSegundoApellidoEspectador(entrada.nextLine()); // Almacenamos el segundo apellido del espectador
			
			// Pedimos el correo del espectador
			
			System.out.println("Introduzca su correo: ");
			
			String correo = entrada.nextLine();
			
			Boolean valido = false;
			
			while(valido == false) {
				
				valido = espectador.comprobarValidezCorreo(correo); // Comprobamos si el correo es valido
				
				// El correo no es valido
				
				if(valido == false) {
					System.out.println("El correo introducido no es valido.");
					System.out.println("Ejemplo de correo valido: xxx@xxx.com/es");
					System.out.print("Introduzca un correo valido: ");
					correo = entrada.nextLine();
				}
				
				// El correo es valido
				
				else{
					
					// Comprobamos si el correo es unico
					Boolean unicidadCorreo = this.comprobarExistenciaCorreoEspectador(correo);
					// El correo ya existe
					if(unicidadCorreo == true) 
					{
						System.out.println("El correo introducido ya existe");
						System.out.println("Introduce <salir> si no desea anadir un correo");
						System.out.print("En caso contrario, introduce un nuevo correo: ");
						correo = entrada.nextLine();
						// El usuario no desea anadir un correo
						if(correo.equals("salir")) {return 0;}
					}
					// El correo no existe -> Almacenamos el correo del espectador
					else {espectador.setCorreoEspectador(correo);}
				}
				
			}
			
			// Almacenamos los datos del espectador en la lista de espectadores
			
			this.listaEspectadores.add(espectador);
			
			entrada = new Scanner(System.in); // Limpiamos el buffer de entrada
			return 1;
			
		} // fin_else
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
	 * @param nick Nombre de usuario del espectador
	 * @return Datos del usuario registrado
	 */

	public UsuarioDTO obtenerDatosUsuario(String nick) {
		// Creamos un espectador vacio
		UsuarioDTO espectador = new UsuarioDTO();
		// Recorremos la lista de espectadores
		for(int i=0; i < this.listaEspectadores.size(); i++) {
			// Obtenemos los datos del espectador
			if(this.listaEspectadores.get(i).getNickEspectador().equals(nick)) {
				espectador.setNombreEspectador(this.listaEspectadores.get(i).getNombreEspectador());
				espectador.setPrimerApellidoEspectador(this.listaEspectadores.get(i).getPrimerApellidoEspectador());
				espectador.setSegundoApellidoEspectador(this.listaEspectadores.get(i).getSegundoApellidoEspectador());
				espectador.setNickEspectador(nick);
				espectador.setCorreoEspectador(this.listaEspectadores.get(i).getCorreoEspectador());
			}
		}
		return espectador;// Retornamos los datos del usuario registrado
	}
	
	/**
	 * Funcion que obtiene los datos de los usuarios registrados en la base de datos
	 * @param sql Fichero de propiedades sql
	 * @param prop Fichero de propiedades
	 */

	public void obtenerUsuarios(Properties sql, Properties prop) {
		
		try {
			// Vaciamos la lista
			this.listaEspectadores  = new ArrayList<UsuarioDTO>();
			// Conexion con la base de datos
			Connection con = ConexionBD.getConexion(prop);
			// Propiedad para obtener los datos de todos los usuarios
			PreparedStatement ps=con.prepareStatement(sql.getProperty("ConsultarUsuarios"));
			// Ejecutamos la sentencia sql
			ResultSet rs = ps.executeQuery();
			// Recorremos las filas de la base de datos
			while(rs.next()) {
				UsuarioDTO usuarioDTO = new UsuarioDTO(); // Creamos un usuario de tipo DTO
				usuarioDTO.setIdUsuario(rs.getInt("ID")); // Obtenemos el identificador del usuario
				usuarioDTO.setCorreoEspectador(rs.getString("CORREO"));// Obtenemos el correo del usuario
				usuarioDTO.setNombreEspectador(rs.getString("NOMBRE"));// Obtenemos el nombre del usuario
				usuarioDTO.setPrimerApellidoEspectador(rs.getString("APELLIDO1"));// Obtenemos el primer apellido del usuario
				usuarioDTO.setSegundoApellidoEspectador(rs.getString("APELLIDO2"));// Obtenemos el segundo apellido del usuario
				usuarioDTO.setNickEspectador(rs.getString("NICK")); // Obtenemos el nick del usuario
				String rol = rs.getString("ROL");
				if(rol.contentEquals("administrador")) {
					usuarioDTO.setRolUsuario(RolUsuarioDTO.administrador);
				}
				else if(rol.equals("espectador")) {
					usuarioDTO.setRolUsuario(RolUsuarioDTO.espectador);
				}
				this.listaEspectadores.add(usuarioDTO); // Anadimos el usuario a la lista de usuarios
			}
			// Cerramos la sentencia rs
			rs.close();
			// Cerramos la sentencia ps
			ps.close();
			// Cerramos la conexion con la base de datos
			if(con != null) {
				con = null;
			}
		}catch(Exception ex) {
			System.out.println("Se ha producido un error al obtener los datos de la base de datos");
		}
	}
	
	

}
