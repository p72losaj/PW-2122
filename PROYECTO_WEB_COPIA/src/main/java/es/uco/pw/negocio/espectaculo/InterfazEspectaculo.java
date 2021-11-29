package es.uco.pw.negocio.espectaculo;

import java.util.ArrayList;

/**
 * Clase que almacena las declaraciones de las funciones comunes de un espectaculo
 * @author Jaime Lorenzo Sanchez
 * @version 2.0
 */

public interface InterfazEspectaculo {
	/**
	 * Funcion que obtiene el identificador del espectaculo
	 * @return Identificador del espectaculo
	 */
	public int getIdentificadorEspectaculo();
	/**
	 * Funcion que modifica el identificador del espectaculo
	 * @param id Nuevo identificador del espectaculo
	 */
	public void setIdentificadorEspectaculo(int id);
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
	
	public SesionEspectaculoDTO getSesionEspectaculo();
	
	/**
	 * Funcion que modifica la sesion de un espectaculo
	 * @param sesion Nueva sesion del espectaculo
	 */
	
	public void setSesionEspectaculo(SesionEspectaculoDTO sesion);
	
	/**
	 * Funcion que obtiene el numero de localidades disponibles del espectaculo
	 * @return Aforo de localidades del espectaculo
	 */
	
	public int getAforoLocalidadesEspectaculo();
	
	/**
	 * Funcion que modifica el aforo de localidades del espectaculo
	 * @param aforo Nuevo aforo de localidades del espectaculo
	 */
	
	public void setAforoLocalidadesEspectaculo(int aforo);
	
	/**
	 * Funcion que obtiene la lista de sesiones de un espectaculo
	 * @return Lista de sesiones de un espectaculo
	 */
	
	public ArrayList<SesionEspectaculoDTO> getSesionesEspectaculo();
	
	/**
	 * Funcion que modifica las sesiones de un espectaculo
	 * @param sesionesEspectaculo Nueva lista de sesiones de un espectaculo
	 */
	
	public void setSesionesEspectaculo(ArrayList<SesionEspectaculoDTO> sesionesEspectaculo);
	
}
