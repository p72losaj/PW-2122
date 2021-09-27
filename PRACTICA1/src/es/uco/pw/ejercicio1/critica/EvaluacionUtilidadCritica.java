package es.uco.pw.ejercicio1.critica;

/**
 * Clase que gestiona las funciones para crear/modificar la evaluacion de una critica
 * @author Jaime Lorenzo Sanche
 * @version 1.0
 */

public class EvaluacionUtilidadCritica {

	private String autorEvaluacion; // Autor de la evaluacion de utilidad
	
	private String evaluacionCritica; // Evaluacion de la utilidad de la critica
	
	/**
	 * Constructor de clase
	 */
	
	public EvaluacionUtilidadCritica() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Funcion que obtiene el nombre de usuario del autor de la evaluacion de utilidad de una critica
	 * @return Nombre de usuario del espectador que evalua la utilidad de una critica
	 */
	
	public String getAutorEvaluacion() {
		return this.autorEvaluacion;
	}
	
	/**
	 * Funcion que modifica el nombre de usuario del autor de la evaluacion de utilidad de una critica
	 * @param nombre Nuevo nombre de usuario del espectador que evalua la utilidad de una critica
	 */
	
	public void setAutorEvaluacion(String nombre) {
		this.autorEvaluacion = nombre;
	}
	
	/**
	 * Funcion que obtiene la evaluacion de utilidad de una critica
	 * @return Evaluacion de una critica
	 */
	
	public String getEvaluacionCritica() {
		return this.evaluacionCritica;
	}
	
	/**
	 * Funcion que modifica la evaluacion de utilidad de una critica
	 * @param cad Nueva evaluacion de utilidad de una critica
	 */
	
	public void setEvaluacionCritica(String cad) {
		this.evaluacionCritica = cad;
	}

}
