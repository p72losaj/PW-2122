package es.uco.pw.negocio.espectaculo;

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
	
	/**
	 * Funcion que obtiene la sesion de un espectaculo
	 * @return Sesion del espectaculo
	 */
	
	public SesionEspectaculo getSesionEspectaculo();
	
	/**
	 * Funcion que modifica la sesion de un espectaculo
	 * @param sesion Nueva sesion del espectaculo
	 */
	
	public void setSesionEspectaculo(SesionEspectaculo sesion);

	
	/**
	 * Funcion que crea un espectaculo de tipo puntual
	 * @param titulo Titulo del espectaculo
	 * @param descripcion Descripcion del espectaculo
	 * @param categoria Categoria del espectaculo
	 * @param tipo Tipo del espectaculo
	 * @param fecha Fecha del espectaculo
	 * @param hora Hora del espectaculo
	 * @return Espectaculo de tipo puntual
	 */
	
	public EspectaculoPuntual crearEspectaculoPuntual(String titulo,String descripcion, CategoriaEspectaculo categoria, String tipo, String fecha, String hora);
	
	/**
	 * Funcion que crea un espectaculo de temporada
	 * @param titulo Titulo del espectaculo
	 * @param descripcion Descripcion del espectaculo
	 * @param categoria Categoria del espectaculo
	 * @param tipo Tipo del espectaculo
	 * @param fechaInicio Fecha de inicio del espectaculo
	 * @param fechaFin Fecha de fin del espectaculo
	 * @param dia Dia de la semana
	 * @return Espectaculo de tipo temporada
	 */
	
	public EspectaculoTemporada crearEspectaculoTemporada(String titulo,String descripcion, CategoriaEspectaculo categoria, String tipo, String fechaInicio, String fechaFin, String dia);

	/**
	 * Funcion que crea un espectaculo de tipo multiple
	 * @param titulo Titulo del espectaculo
	 * @param descripcion Descripcion del espectaculo
	 * @param categoria Categoria del espectaculo
	 * @param tipo Tipo del espectaculo
	 * @param dia1 Dia de la semana de la sesion 1 del espectaculo
	 * @param hora1 Hora de la sesion 1 del espectaculo
	 * @param dia2 Dia de la semana de la sesion 2 del espectaculo
	 * @param hora2 Hora de la sesion 2 del espectaculo
	 * @return Espectaculo de tipo multiple
	 */
	
	public EspectaculoMultiple crearEspectaculoMultiple(String titulo,String descripcion, CategoriaEspectaculo categoria, String tipo, String dia1, String hora1, String dia2, String hora2);
	
}
