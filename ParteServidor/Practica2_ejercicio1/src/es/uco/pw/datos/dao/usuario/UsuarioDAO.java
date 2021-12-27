package es.uco.pw.datos.dao.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import es.uco.pw.datos.dao.comun.conexionBD.ConexionBD;
import es.uco.pw.negocio.usuario.RolUsuario;
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
	private RolUsuario RolUsuario;// Rol del usuario
	
	
	
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
	public RolUsuario getRolUsuario() {
		return this.RolUsuario;
	}

	/**
	 * Funcion que modifica el rol del usuario
	 * @param rolUsuario Nuevo rol del usuario
	 */
	public void setRolUsuario(RolUsuario rolUsuario) {
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
			String rolUsuario = usuarioDTO.getRolUsuario().toString();
			if(rolUsuario.equals("administrador")) {
					ps.setString(6, "administrador");
			}
			else if(rolUsuario.equals("espectador")) {
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

	/**
	 * Funcion que obtiene los datos de los usuarios registrados en la base de datos
	 * @param sql Fichero de propiedades sql
	 * @param prop Fichero de propiedades
	 * @return Lista de usuarios de la base de datos
	 */

	public ArrayList<UsuarioDTO> obtenerUsuarios(Properties sql, Properties prop) {
		
		ArrayList<UsuarioDTO> usuarios  = new ArrayList<UsuarioDTO>(); // Lista de usuarios
		
		try {
			
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
				if(rol.equals("administrador")) {
					usuarioDTO.setRolUsuario(RolUsuario.administrador);
				}
				else if(rol.equals("espectador")) {
					usuarioDTO.setRolUsuario(RolUsuario.espectador);
				}
				usuarios.add(usuarioDTO); // Anadimos el usuario a la lista de usuarios
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
		return usuarios;
		
		
	}
	/**
	 * Funcion que obtiene el identificador del usuario registrado en la base de datos
	 * @param prop Fichero de configuracion
	 * @param sql Fichero de sentencias sql
	 * @param correoEspectador Correo del usuario
	 * @return Identificador del usuario
	 */
	public int obtencionIdentificadorUsuario(Properties prop, Properties sql, String correoEspectador) {
		int identificador = 0; // Identificador por defecto del usuario
		try {
			Connection con = ConexionBD.getConexion(prop); // conexion con la base de datos
			PreparedStatement ps=con.prepareStatement(sql.getProperty("ObtencionUsuario")); // Sentencia sql para obtener los datos del usuario
			ps.setString(1, correoEspectador); // Indicamos en la sentencia sql el correo del usuario ha obtener
			ResultSet rs = ps.executeQuery(); // Ejecutamos la sentencia sql
			while(rs.next()) {
				identificador = rs.getInt("ID"); // Obtenemos el identificador del usuario
			}
			rs.close(); // Cierre de la ejecucion de la sentencia sql
			ps.close(); // Cierre de la sentencia sql
			if(con != null) { con = null; } // Cierre de la conexion con la base de datos
		}catch(Exception ex) {
			System.out.println("Se ha producido un error al obtener el identificador del usuario");
		}
		return identificador; // Retornamos el identificador del usuario
	}
	
	
	
	
	
	
	

	
	
}
