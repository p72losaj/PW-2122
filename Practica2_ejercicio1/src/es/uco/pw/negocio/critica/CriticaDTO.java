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
	private String resena; // Resena de la critica
	private String autorCritica; // Autor de la critica
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
	public String getResenaEspectaculo() {return this.resena; }
	/**
	 * Funcion que modifica la resena de una critica
	 * @param resena Nueva resena del espectaculo
	 */
	public void setResenaEspectaculo(String resena) {this.resena = resena; }
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
	 * Funcion que muestra la informacion de una critica
	 */
	public void mostrarCritica() {
		System.out.println("Critica: " + this.idCritica); // Imprimimos el identificador de la critica
		System.out.println("\tTitulo de la critica: " + this.getTituloCritica()); // Imprimimos el titulo de la critica
		System.out.println("\tResena de la critica: " + this.getResenaEspectaculo()); // Imprimimos la resena de la critica
		System.out.println("\tAutor de la critica: " + this.getAutorCritica()); // Imprimimos el autor de la critica
	}
	
}
