package es.uco.pw.critica;

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
	
	private ArrayList<Integer> valoracionesUtilidadCritica = new ArrayList<Integer>(); // Valoraciones de utilidad de una critica
	
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
	
	public ArrayList<Integer> getValoracionesUtilidadCritica(){
		return this.valoracionesUtilidadCritica;
	}
	
	/**
	 * Funcion que modifica las valoraciones de la utilidad de una critica
	 * @param valoraciones Nuevas valoraciones de la utilidad de una critica
	 */
	
	public void setValoracionesUtilidadCritica(ArrayList<Integer> valoraciones) {
		this.valoracionesUtilidadCritica = valoraciones;
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
	
}
