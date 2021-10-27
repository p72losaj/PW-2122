package es.uco.pw.dao.espectaculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import es.uco.pw.datos.dao.comun.conexionBD.ConexionBD;
import es.uco.pw.negocio.espectaculo.CategoriaEspectaculo;
import es.uco.pw.negocio.espectaculo.EspectaculoDTO;
import es.uco.pw.negocio.espectaculo.SesionEspectaculoDTO;

/**
 * Clase que obtiene/modifica los datos de la tabla ESPECTACULO de la base de datos
 * @author Jaime Lorenzo Sanchez
 * @version 1.0
 */

public class EspectaculoDAO {
	private int identificadorEspectaculo;// Identificador del espectaculo
	private String tituloEspectaculo; // Titulo del espectaculo
	private String descripcionEspectaculo; // Descripcion del espectaculo
	private String categoriaEspectaculo; // Categoria del espectaculo
	private int localidadesEspectaculo; // Numero de localidades del espectaculo
	private int ventasEspectaculo; // Numero de ventas de un espectaculo
	/**
	 * Funcion que obtiene todos los datos de un espectaculo
	 * @param prop Fichero de propiedades para la conexion con la base de datos
	 * @param sql Fichero de propiedades
	 * @return Informacion de los espectaculos registrados en el sistema
	 */
	public ArrayList<EspectaculoDTO> obtencionEspectaculos(Properties prop, Properties sql) {
		ArrayList<EspectaculoDTO> espectaculos = new ArrayList<EspectaculoDTO>(); // Lista de espectaculos
		try {
			// Conexion con la base de datos
			Connection con = ConexionBD.getConexion(prop);
			// Sentencia del fichero sql para obtener los datos de todos los espectaculos
			PreparedStatement ps=con.prepareStatement(sql.getProperty("ObtencionEspectaculos"));
			// Ejecutamos la sentencia sql
			ResultSet rs = ps.executeQuery();
			// Recorremos las filas de la tabla
			while(rs.next()) {
				EspectaculoDTO espectaculo = new EspectaculoDTO();// Creamos un espectaculoDTO vacio
				espectaculo.setIdentificadorEspectaculo(rs.getInt("ID"));// Obtenemos el identificador del espectaculo
				espectaculo.setTituloEspectaculo(rs.getString("TITULO")); // Obtenemos el titulo del espectaculo
				espectaculo.setTipoEspectaculo(rs.getString("TIPO"));// Obtenemos el tipo del espectaculo
				espectaculo.setDescripcionEspectaculo(rs.getString("DESCRIPCION"));// Obtenemos la descripcion del espectaculo
				String categoria = rs.getString("CATEGORIA");// Obtenemos la categoria del espectaculo
				// Comprobamos cual es la categoria del espectaculo
				// Caso 1: El espectaculo es un concierto
				if(categoria.equals("CONCIERTO")) {
					espectaculo.setCategoriaEspectaculo(CategoriaEspectaculo.concierto);
				}
				// Caso 2: El espectaculo es un monologo
				else if(categoria.equals("MONOLOGO")) {
					espectaculo.setCategoriaEspectaculo(CategoriaEspectaculo.monologo);
				}
				// Caso 3: El espectaculo es una obra de teatro
				else if(categoria.equals("OBRA DE TEATRO")){
					espectaculo.setCategoriaEspectaculo(CategoriaEspectaculo.obraTeatro);
				}
				// Obtenemos el resto de la informacion del espectaculo en funcion del tipo del espectaculo
				
				// Caso1: El espectaculo es de tipo puntual
				if(espectaculo.getTipoEspectaculo().equals("PUNTUAL")) {
					// Sentencia del fichero sql para obtener los datos del espectaculo puntual
					PreparedStatement ps2=con.prepareStatement(sql.getProperty("ObtencionEspectaculoPuntual"));
					// Indicamos en la sentencia ps2 el titulo del espectaculo
					ps2.setString(1, espectaculo.getTituloEspectaculo());
					// Ejecutamos la sentencia sql
					ResultSet rs2 = ps2.executeQuery();
					// Obtenemos toda la informacion del espectaculo puntual
					while(rs2.next()) {
						SesionEspectaculoDTO sesion = new SesionEspectaculoDTO();// Inicalizamos una sesion vacia
						sesion.setDiaSesion(rs2.getInt("DIA"));// Obtenemos el dia del espectaculo
						sesion.setMesSesion(rs2.getInt("MES"));// Obtenemos el mes del espectaculo
						sesion.setAnoSesion(rs2.getInt("ANO"));// Obtenemos el ano del espectaculo
						String fecha = Integer.toString(sesion.getAnoSesion()) + "-" + Integer.toString(sesion.getMesSesion()) + "-" + Integer.toString(sesion.getDiaSesion());// Obtenemos la fecha completa del espectaculo
						sesion.setFechaCompletaSesion(fecha);// Obtenemos la fecha completa de la sesion
						sesion.setHoraSesion(rs2.getInt("HORA"));// Obtenemos la hora de la sesion
						sesion.setHoraSesion(rs2.getInt("MINUTOS"));// Obtenemos los minutos de la hora de la sesion
						String horaCompleta = Integer.toString(sesion.getHoraSesion()) + ":" + Integer.toString(sesion.getMinutosSesion()); // Obtenemos la hora completa de la sesion
						sesion.setHoraCompleta(horaCompleta);// Obtenemos la hora completa de la sesion
						espectaculo.setSesionEspectaculo(sesion);// Anadimos la sesion del espectaculo
					}
					// Cerramos la sentencia rs2
					rs2.close();
					// Cerramos la sentencia ps2
					ps2.close();
				}
				// Caso 2: El espectaculo es de tipo multiple
				
				// Caso 3: El espectaculo es de tipo temporada
				
				// Anadimos los datos del espectaculo a la lista
				espectaculos.add(espectaculo);
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
			System.out.println("Se ha producido un error al obtener los datos del espectaculo");
		}
		
		return espectaculos;
	}
	
	/**
	 * Funcion que obtiene el numero de ventas de un espectaculo
	 * @return Numero de ventas del espectaculo
	 */
	public int getVentasEspectaculo() {
		return this.ventasEspectaculo;
	}
	/**
	 * Funcion que modifica el numero de ventas de un espectaculo
	 * @param ventas Nuevo numero de ventas del espectaculo
	 */
	public void setVentasEspectaculo(int ventas) {
		this.ventasEspectaculo = ventas;
	}
	/**
	 * Funcion que obtiene el numero de localidades disponibles del espectaculo
	 * @return Numero de localidades disponibles del 
	 */
	public int getLocalidadesEspectaculo() {
		return this.localidadesEspectaculo;
	}
	/**
	 * Funcion que modifica el numero de localidades de un espectaculo
	 * @param localidades Nuevo numero de localidades disponibles de un espectaculo
	 */
	public void setLocalidadesEspectaculo(int localidades) {
		this.localidadesEspectaculo = localidades;
	}
	/**
	 * Funcion que obtiene la categoria de un espectaculo
	 * @return Categoria del espectaculo
	 */
	public String getCategoriaEspectaculo() {
		return this.categoriaEspectaculo;
	}
	/**
	 * Funcion que modifica la categoria de un espectaculo
	 * @param categoria Nueva categoria del espectaculo
	 */
	public void setCategoriaEspectaculo(String categoria) {
		this.categoriaEspectaculo = categoria;
	}
	/**
	 * Funcion que obtiene la descripcion de un espectaculo
	 * @return Descripcion del espectaculo
	 */
	public String getDescripcionEspectaculo() {
		return this.descripcionEspectaculo;
	}
	/**
	 * Funcion que modifica la descripcion de un espectaculo
	 * @param descripcion Nueva descripcion de un espectaculo
	 */
	public void setDescripcionEspectaculo(String descripcion) {
		this.descripcionEspectaculo = descripcion;
	}
	/**
	 * Funcion que obtiene el titulo de un espectaculo
	 * @return Titulo del espectaculo
	 */
	public String getTituloEspectaculo() {
		return this.tituloEspectaculo;
	}
	/**
	 * Funcion que modifica el titulo de un espectaculo
	 * @param titulo Nuevo titulo del espectaculo
	 */
	public void setTituloEspectaculo(String titulo) {
		this.tituloEspectaculo = titulo;
	}
	/**
	 * Funcion que obtiene el identificador de un espectaculo
	 * @return Identificador del espectaculo
	 */
	public int getIdentificadorEspectaculo() {
		return this.identificadorEspectaculo;
	}
	/**
	 * Funcion que modifica el identificador del espectaculo
	 * @param id Nuevo identificador del espectaculo
	 */
	public void setIdentificadorEspectaculo(int id) {
		this.identificadorEspectaculo = id;
	}
}
