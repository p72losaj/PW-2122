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
	 * Funcion que obtiene los datos de una critica y los almacena en una cadena
	 * @return Cadena con los datos de la critica
	 */
	public String mostrarCritica() {
		String critica = "Critica: " + this.idCritica + "\n"; // Anadimos el identificador de la critica
		critica = critica + "\tTitulo de la critica:" + this.getTituloCritica() + "\n"; // Anadimos el titulo de la critica
		critica = critica + "\tResena de la critica: " + this.getResenaCritica() + "\n"; // Anadimos la resena de la critica  
		critica = critica + "\tAutor de la critica: " + this.getAutorCritica() + "\n"; // Obtenemos el autor de la critica
		critica = critica + "\tTitulo del espectaculo: " + this.getTituloCritica()+"\n"; // Obtenemos el titulo de la critica 
		critica = critica + "\tPuntuacion del espectaculo: " + this.getPuntuacionEspectaculo()+"\n"; // Obtenemos la puntuacion del espectaculo
		// La critica tiene una lista de evaluaciones de utilidad
		if(!this.getListaEvaluacionesCritica().isEmpty()) {
			critica = critica + "\t Lista de evaluaciones de utilidad de la critica \n";
			// Recorremos la lista de evaluaciones de utilidad de la critica
			for(int i=0; i < this.getListaEvaluacionesCritica().size(); i++) {
				critica = critica + "\tIdentificador de la evaluacion de utilidad: " + this.getListaEvaluacionesCritica().get(i).getIdentificadorValoracion() + "\n"; // Identificador de la valoracion de utilidad
				critica = critica + "\tIdentificador del autor de la evaluacion: " + this.getListaEvaluacionesCritica().get(i).getIdentificadorAutor() + "\n";
				critica = critica + "\t Evaluacion de utilidad de la critica: " + this.getListaEvaluacionesCritica().get(i).getValoracionCritica() + "\n"; 
			}
		}
		
		return critica;
	}
	
}
