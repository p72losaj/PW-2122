package es.uco.pw.datos.dao.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;

import es.uco.pw.datos.dao.comun.conexionBD.ConexionBD;
import es.uco.pw.negocio.usuario.RolUsuarioDTO;
import es.uco.pw.negocio.usuario.UsuarioDTO;

/**
 * Clase que gestiona la tabla usuario de la base de datos
 * @author Jaime Lorenzo Sanchez
 * @version 1.0
 */

public class UsuarioDAO {
	private int IdenficadorUsuario;// Identificador del usuario
	private String CorreoUsuario;// Correo del usuario
	private String NombreUsuario;// Nombre del usuario
	private String PrimerApellidoUsuario;// Primer apellido del usuario
	private String SegundoApellidoUsuario;// Segundo apellido del usuario
	private String NickUsuario;// Nombre de usuario del usuario
	private RolUsuarioDTO RolUsuario;// Rol del usuario
	
	
	
	/**
	 * Funcion que obtiene el identificador del usuario
	 * @return Identificador del usuario de la base de datos
	 */
	
	public int getIdentificadorUsuarioDAO() {
		return this.IdenficadorUsuario;
	}
	
	/**
	 * Funcion que modifica el identificador del usuario
	 * @param id Nuevo identificador del usuario
	 */
	
	public void setIdentificadorUsuarioDAO(int id) {
		this.IdenficadorUsuario = id;
	}
	
	/**
	 * Funcion que obtiene el correo del usuario
	 * @return Correo del usuario
	 */
	
	public String getCorreoUsuarioDAO() {
		return this.CorreoUsuario;
	}
	
	/**
	 * Funcion que modifica el correo del usuario
	 * @param correo Nuevo correo del usuario
	 */
	
	public void setCorreoUsuarioDAO(String correo) {
		this.CorreoUsuario = correo;
	}

	/**
	 * Funcion que obtiene el nombre del usuario
	 * @return Nombre del usuario
	 */
	public String getNombreUsuario() {
		return this.NombreUsuario;
	}

	/**
	 * Funcion que modifica el nombre del usuario
	 * @param nombreUsuario Nuevo nombre del usuario
	 */
	
	public void setNombreUsuario(String nombreUsuario) {
		this.NombreUsuario = nombreUsuario;
	}

	/**
	 * Funcion que obtiene el primer apellido del usuario
	 * @return Primer apellido del usuario
	 */
	
	public String getPrimerApellidoUsuario() {
		return this.PrimerApellidoUsuario;
	}

	/**
	 * Funcion que modifica el primer apellido del usuario
	 * @param primerApellidoUsuario Nuevo primer apellido del usuario
	 */
	public void setPrimerApellidoUsuario(String primerApellidoUsuario) {
		this.PrimerApellidoUsuario = primerApellidoUsuario;
	}

	/**
	 * Funcion que obtiene el segundo apellido del usuario
	 * @return Segundo apellido del usuario
	 */
	public String getSegundoApellidoUsuario() {
		return this.SegundoApellidoUsuario;
	}

	/**
	 * Funcion que modifica el segundo apellido del usuario
	 * @param segundoApellidoUsuario Nuevo segundo apellido del usuario
	 */
	public void setSegundoApellidoUsuario(String segundoApellidoUsuario) {
		this.SegundoApellidoUsuario = segundoApellidoUsuario;
	}

	/**
	 * Funcion que obtiene el nick del usuario
	 * @return Nick del usuario
	 */
	public String getNickUsuario() {
		return this.NickUsuario;
	}

	/**
	 * Funcion que modifica el nick del usuario
	 * @param nickUsuario Nuevo nick del usuario
	 */
	public void setNickUsuario(String nickUsuario) {
		this.NickUsuario = nickUsuario;
	}

	/**
	 * Funcion que obtiene el rol del usuario
	 * @return Rol del usuario
	 */
	public RolUsuarioDTO getRolUsuario() {
		return this.RolUsuario;
	}

	/**
	 * Funcion que modifica el rol del usuario
	 * @param rolUsuario Nuevo rol del usuario
	 */
	public void setRolUsuario(RolUsuarioDTO rolUsuario) {
		RolUsuario = rolUsuario;
	}
	
	/**
	 * Funcion que inserta los datos de un nuevo usuario en la base de datos
	 * @param usuarioDTO Usuario a insertar en la base de datos
	 * @param prop Fichero de propiedades de conexion a la base de datos
	 * @param sql Fichero de propiedades sql
	 * @return Numero de filas modificadas de la base de datos
	 */

	public int insertarUsuario(UsuarioDTO usuarioDTO, Properties prop, Properties sql) {
		
		int status = 0; // Numero de filas anadidas en la base de datos
		
		try {
			Connection con = ConexionBD.getConexion(prop); // Conexion con la base de datos
			// Obtenemos la propiedad del fichero de propiedades
			PreparedStatement ps=con.prepareStatement(sql.getProperty("InsertarUsuario"));
			ps.setString(1,usuarioDTO.getCorreoEspectador());// Indicamos en la sentencia ps el correo del usuario
			ps.setString(2, usuarioDTO.getNombreEspectador());// Indicamos el nombre del usuario en la sentencia ps
			ps.setString(3, usuarioDTO.getPrimerApellidoEspectador());// Indicamos el primer apellido del usuario en la sentencia ps
			ps.setString(4, usuarioDTO.getSegundoApellidoEspectador());// Indicamos el segundo apellido del usuario en la sentencia ps
			ps.setString(5, usuarioDTO.getNickEspectador());// Indicamos el nick del usuario en la sentencia ps
			if(usuarioDTO.getRolUsuario().equals(RolUsuarioDTO.administrador)) {
					ps.setString(6, "administrador");
			}
			else if(usuarioDTO.getRolUsuario().equals(RolUsuarioDTO.espectador)) {
				ps.setString(6, "espectador");
			}
			
			status = ps.executeUpdate(); // Actualizamos la base de datos
			ps.close(); // Cerramos la sentencia ps
			// Cerramos la conexion con la base de datos
			if(con != null) {
				con = null;
			}
		}catch(Exception ex) {
			System.out.println("Se ha producido un error al anadir los datos del usuario en la base de datos");
		}
		
		return status; // Retornamos el numero de filas anadidas a la base de datos
	}
	
	
	

	
	
}
