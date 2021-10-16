package es.uco.pw.negocio.critica;

import java.util.ArrayList;

/**
 * Clase que gestiona las operaciones get y set de una critica
 * @author Jaime Lorenzo Sanchez
 * @version 1.0
 */

public class Critica {
	
	/**
	 * Titulo de la critica
	 */
	
	private String tituloCritica = null;
	
	/**
	 * Puntuacion del espectaculo
	 */
	
	private int puntuacionEspectaculo = 0;
	
	/**
	 * Resena de la critica
	 */
	
	private String resena = null;
	
	/**
	 * Valoraciones de la utilidad de una critica
	 */
	
	ArrayList<EvaluacionUtilidadCritica> listaEvaluaciones = new ArrayList<EvaluacionUtilidadCritica>(); // Lista de evaluaciones de utilidad de una critica
	
	/**
	 * Autor de la critica
	 */
	
	private String autorCritica = new String();
	
	/**
	 * Funcion que obtiene el titulo de una critica
	 * @return Titulo de la critica
	 */
	
	public String getTituloCritica() {
		return this.tituloCritica;
	}
	
	/**
	 * Funcion que modifica el titulo de una critica
	 * @param titulo Nuevo titulo de la critica
	 */
	
	public void setTituloCritica(String titulo) {
		this.tituloCritica = titulo;
	}
	
	/**
	 * Funcion que obtiene la puntuacion de un espectaculo
	 * @return Puntuacion del espectaculo
	 */
	
	public int getPuntuacionEspectaculo() {
		return this.puntuacionEspectaculo;
	}
	
	/**
	 * Funcion que modifica la puntuacion de un espectaculo en una escala [0,10],
	 * siendo el 0 muy malo, 10 muy bueno
	 * @param puntuacion Nueva puntuacion del espectaculo
	 */
	
	public void setPuntuacionEspectaculo(int puntuacion) {
		this.puntuacionEspectaculo = puntuacion;
	}
	
	
	/**
	 * Funcion que obtiene la resena de una critica
	 * @return Resena de la critica
	 */
	
	public String getResenaEspectaculo() {
		return this.resena;
	}
	
	/**
	 * Funcion que modifica la resena de una critica
	 * @param resena Nueva resena del espectaculo
	 */
	
	public void setResenaEspectaculo(String resena) {
		this.resena = resena;
	}
	
	/**
	 * Funcion que obtiene las valoraciones de utilidad de una critica
	 * @return Lista de valoraciones de utilidad de una critica
	 */
	
	public ArrayList<EvaluacionUtilidadCritica> getValoracionesUtilidadCritica(){
		return this.listaEvaluaciones;
	}
	
	/**
	 * Funcion que modifica las valoraciones de la utilidad de una critica
	 * @param valoraciones Nuevas valoraciones de la utilidad de una critica
	 */
	
	public void setValoracionesUtilidadCritica(ArrayList<EvaluacionUtilidadCritica> valoraciones) {
		this.listaEvaluaciones = valoraciones;
	}
	
	/**
	 * Funcion que obtiene el autor de una critica
	 * @return Autor de la critica
	 */

	public String getAutorCritica() {
		return this.autorCritica;
	}
	
	/**
	 * Funcion que modifica el autor de una critica
	 * @param autorCritica Nuevo autor de una critica
	 */

	public void setAutorCritica(String autorCritica) {
		this.autorCritica = autorCritica;
	}
	
	/**
	 * Funcion que muestra la informacion de una critica
	 */

	public void mostrarCritica() {
		// Mostramos el titulo de la critica
		System.out.println("Titulo de la critica: " + this.getTituloCritica());
		// Mostramos la puntuacion del espectaculo
		System.out.println("Puntuacion de la critica: " + this.getPuntuacionEspectaculo());
		// Mostramos la resena de la critica
		System.out.println("Resena de la critica: " + this.getResenaEspectaculo());
		// Mostramos la lista de valoraciones de la critica
		System.out.println("Valoraciones de utilidad de la critica: ");
		for(int i=0; i < this.getValoracionesUtilidadCritica().size(); i++) {
			System.out.println("Autor valoracion: " + this.getValoracionesUtilidadCritica().get(i).getAutorEvaluacion() + "-Evaluacion Critica: " + this.getValoracionesUtilidadCritica().get(i).getEvaluacionCritica());
		}
		// Mostramos el autor de la critica
		System.out.println("Autor de la critica: " + this.getAutorCritica());
		
	}
	
}
