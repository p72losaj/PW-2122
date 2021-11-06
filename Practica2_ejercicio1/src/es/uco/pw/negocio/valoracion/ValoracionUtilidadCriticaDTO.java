package es.uco.pw.negocio.valoracion;
/**
 * Clase que gestiona las operaciones sobre la valoracion de utilidad de una critica
 * @author Jaime Lorenzo Sanchez
 * @version 1.0
 */

public class ValoracionUtilidadCriticaDTO {
	// Identificador principal
	int idPrincipal;
	// Identificador de la critica
	int idCritica;
	// Identificador del autor de la evaluacion de utilidad
	int idAutor;
	// Valoracion de utilidad de la critica
	int evaluacion;
	/**
	 * Funcion que obtiene el identificador de la valoracion de utilidad de la critica
	 * @return Identificador de la valoracion de utilidad
	 */
	public int getIdentificadorValoracion() { return this.idPrincipal; }
	/**
	 * Funcion que modifica el identificador de la valoracion de utilidad de la critica
	 * @param id Nuevo identificador de la valoracion de utilidad
	 */
	public void setIdentificadorValoracion(int id) { this.idPrincipal = id; }
	/**
	 * Funcion que obtiene el identificador de la critica
	 * @return Identificador de la critica
	 */
	public int getIdentificadorCritica() { return this.idCritica; }
	/**
	 * Funcion que modifica el identificador de la critica
	 * @param id Nuevo identificador de la critica
	 */
	public void setIdentificadorCritica(int id) { this.idCritica = id; }
	/**
	 * Funcion que obtiene el identificador del autor de la evaluacion de utilidad
	 * @return Identificador del autor de la evaluacion de utilidad de la critica
	 */
	public int getIdentificadorAutor() { return this.idAutor; }
	/**
	 * Funcion que modifica el identificador del autor de evaluacion de utilidad de la critica
	 * @param id Nuevo identificador del autor de la evaluacion de utilidad
	 */
	public void setIdentificadorAutor(int id) { this.idAutor = id; }
	/**
	 * Funcion que obtiene la valoracion de utilidad de la critica
	 * @return Valoracion de utilidad de la critica
	 */
	public int getValoracionCritica() { return this.evaluacion; }
	/**
	 * Funcion que modifica la valoracion de utilidad de la critica
	 * @param id Nueva valoracion de utilidad de la critica
	 */
	public void setValoracionUtilidadCritica(int id) { this.evaluacion = id; }
}
