package es.uco.pw.dao.espectaculo;

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
