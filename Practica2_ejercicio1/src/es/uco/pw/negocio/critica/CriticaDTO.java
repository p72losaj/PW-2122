package es.uco.pw.negocio.critica;

import java.util.ArrayList;

/**
 * Clase que gestiona las operaciones get y set de una critica
 * @author Jaime Lorenzo Sanchez
 * @version 2.0
 */

public class CriticaDTO {
	
	private int idCritica; // Identificador de la critica
	private String tituloCritica; // Titulo de la critica
	private String resenaCritica; // Resena de la critica
	private String autorCritica; // Autor de la critica
	private ArrayList<EvaluacionUtilidadCriticaDTO> listaEvaluaciones; // Evaluaciones de utilidad de las criticas
	private String tituloEspectaculo; // Identificador del espectaculo al que se refiere la critica
	private int puntuacionEspectaculo; // Puntuacion del espectaculo
	/**
	 * Funcion que obtiene la puntuacion del espectaculo
	 * @return Puntuacion del espectaculo
	 */
	public int getPuntuacionEspectaculo() {return this.puntuacionEspectaculo; }
	/**
	 * Funcion que modifica la puntuacion del espectaculo
	 * @param puntuacion Nueva puntuacion del espectaculo
	 */
	public void setPuntuacionEspectaculo(int puntuacion) { this.puntuacionEspectaculo = puntuacion;}
	/**
	 * Funcion que obtiene el titulo del espectaculo al que puntua la critica
	 * @return Titulo del espectaculo
	 */
	public String getTituloEspectaculo() {return this.tituloEspectaculo; }
	/**
	 * Funcion que modifica el titulo del espectaculo al que puntua la critica
	 * @param id Nuevo titulo del espectaculo
	 */
	public void setTituloEspectaculo(String titulo) { this.tituloEspectaculo = titulo; }
	/**
	 * Funcion que obtiene el identificador de la critica
	 * @return Identificador de la critica
	 */
	public int getIdentificadorCritica() { return this.idCritica;}
	/**
	 * Funcion que modifica el identificador de una critica
	 * @param id Nuevo identificador de la critica
	 */
	public void setIdentificadorCritica(int id) { this.idCritica = id; }
	/**
	 * Funcion que obtiene el titulo de una critica
	 * @return Titulo de la critica
	 */
	public String getTituloCritica() {return this.tituloCritica; }
	/**
	 * Funcion que modifica el titulo de una critica
	 * @param titulo Nuevo titulo de la critica
	 */
	public void setTituloCritica(String titulo) {this.tituloCritica = titulo;}
	/**
	 * Funcion que obtiene la resena de una critica
	 * @return Resena de la critica
	 */
	public String getResenaCritica() {return this.resenaCritica; }
	/**
	 * Funcion que modifica la resena de una critica
	 * @param resena Nueva resena del espectaculo
	 */
	public void setResenaCritica(String resena) {this.resenaCritica = resena; }
	/**
	 * Funcion que obtiene el autor de una critica
	 * @return Autor de la critica
	 */
	public String getAutorCritica() { return this.autorCritica; }
	/**
	 * Funcion que modifica el autor de una critica
	 * @param autorCritica Nuevo autor de una critica
	 */
	public void setAutorCritica(String autorCritica) { this.autorCritica = autorCritica; }
	/**
	 * Funcion que obtiene las evaluaciones de utilidad de una critica
	 * @return Evaluaciones de utilidad de la critica
	 */
	public ArrayList<EvaluacionUtilidadCriticaDTO> getListaEvaluacionesCritica(){ return this.listaEvaluaciones; }
	/**
	 * Funcion que modifica la lista de evaluaciones de utilidad de una critica
	 * @param lista Nueva lista de evaluaciones de utilidad de la critica
	 */
	public void setListaEvaluacionesCritica(ArrayList<EvaluacionUtilidadCriticaDTO> lista) { this.listaEvaluaciones = lista; }
	/**
	 * Funcion que muestra la informacion de una critica
	 */
	public void mostrarCritica() {
		System.out.println("Critica: " + this.idCritica); // Imprimimos el identificador de la critica
		System.out.println("\tTitulo de la critica: " + this.getTituloCritica()); // Imprimimos el titulo de la critica
		System.out.println("\tResena de la critica: " + this.getResenaCritica()); // Imprimimos la resena de la critica
		System.out.println("\tAutor de la critica: " + this.getAutorCritica()); // Imprimimos el autor de la critica
		System.out.println("\tTitulo del espectaculo: " + this.tituloEspectaculo); // Imprimimos el identificador del espectaculo
		System.out.println("\tPuntuacion del espectaculo: " + this.puntuacionEspectaculo); // Imprimimos la puntuacion del espectaculo
		// La critica tiene una lista de evaluaciones de utilidad
		if(!this.listaEvaluaciones.isEmpty()) {
			System.out.println("Lista de evaluaciones de utilidad de la critica");
			// Recorremos la lista de evaluaciones de utilidad de la critica
			for(int i=0; i < this.listaEvaluaciones.size(); i++) {
				System.out.println("\tIdentificador de la evaluacion de utilidad: " + this.listaEvaluaciones.get(i).getIdentificadorValoracion()); // Imprimimos el identificador de la evaluacion de utilidad
				System.out.println("\tIdentificador del autor de la evaluacion: " + this.listaEvaluaciones.get(i).getIdentificadorAutor());// Imprimimos el autor de la evaluacion de utilidad de la critica
				System.out.println("\t Evaluacion de utilidad de la critica: " + this.listaEvaluaciones.get(i).getValoracionCritica()); // Imprimimos la evaluacion de utilidad de la critica
			}
		}
	}
	
}
