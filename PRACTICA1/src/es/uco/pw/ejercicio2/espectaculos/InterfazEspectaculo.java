package es.uco.pw.ejercicio2.espectaculos;

/**
 * Clase que almacena las declaraciones de las funciones comunes de un espectaculo
 * @author Jaime Lorenzo Sanchez
 * @version 1.0
 */

public interface InterfazEspectaculo {
	/**
	 * Funcion que obtiene el titulo de un espectaculo
	 * @return Titulo del espectaculo
	 */
	
	public String getTituloEspectaculo();
	
	/**
	 * Funcion que modifica el titulo de un espectaculo
	 * @param titulo Nuevo titulo del espectaculo
	 */
	
	public void setTituloEspectaculo(String titulo);
	
	/**
	 * Funcion que obtiene la descripcion de un espectaculo
	 * @return Descripcion del espectaculo
	 */
	
	public String getDescripcionEspectaculo();
	
	/**
	 * Funcion que modifica la descripcion de un espectaculo
	 * @param descripcion Nueva descripcion del espectaculo
	 */
	
	public void setDescripcionEspectaculo(String descripcion);
	
	/**
	 * Funcion que obtiene la categoria de un espectaculo
	 * @return Categoria del espectaculo
	 */
	
	public CategoriaEspectaculo getCategoriaEspectaculo();
	
	/**
	 * Funcion que modifica la categoria de un espectaculo
	 * @param categoria Nueva categoria del espectaculo
	 */
	
	public void setCategoriaEspectaculo(CategoriaEspectaculo categoria);
	
	/**
	 * Funcion que obtiene el tipo del espectaculo
	 * @return Tipo del espectaculo
	 */
	
	public String getTipoEspectaculo();
	
	/**
	 * Funcion que modifica el tipo del espectaculo
	 * @param tipo Nuevo tipo del espectaculo
	 */
	
	public void setTipoEspectaculo(String tipo);
}
