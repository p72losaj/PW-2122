package es.uco.pw.negocio.critica;

/**
 * Clase que gestiona las funciones para crear/modificar la evaluacion de una critica
 * @author Jaime Lorenzo Sanche
 * @version 2.0
 */

public class EvaluacionUtilidadCriticaDTO {
	
	private int idPrincipal; // Identificador principal
	private int idCritica; // Identificador de la critica
	private int idAutor; // Identificador del autor de evaluacion de la critica
	private int evaluacion; // Evaluacion de la utilidad de la critica
	/**
	 * Constructor de clase
	 */
	public EvaluacionUtilidadCriticaDTO() {
		// TODO Auto-generated constructor stub
	}
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
