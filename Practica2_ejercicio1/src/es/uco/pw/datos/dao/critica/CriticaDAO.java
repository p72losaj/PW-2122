package es.uco.pw.datos.dao.critica;

/**
 * Clase que gestiona la informacion de las criticas en la base de datos
 * @author Jaime Lorenzo Sanchez
 * @version 1.0
 */

public class CriticaDAO {
	private int idCritica; // Identificador de la critica
	private String tituloCritica; // Titulo de la critica
	private String resenaCritica; // Resena de la critica
	private String autorCritica; // Autor de la critica
	/**
	 * Funcion que obtiene el autor de la critica
	 * @return Correo del autor de la critica
	 */
	public String getAutorCritica() {
		return this.autorCritica;
	}
	/**
	 * Funcion que modifica el autor de una critica
	 * @param autor Nuevo correo del autor de la critica
	 */
	public void setAutorCritica(String autor) {
		this.autorCritica = autor;
	}
	/**
	 * Funcion que obtiene la resena de una critica
	 * @return Resena de la critica
	 */
	public String getResenaCritica() {
		return this.resenaCritica;
	}
	/**
	 * Funcion que modifica la resena de la critica
	 * @param resena Nueva resena de la critica
	 */
	public void setResenaCritica(String resena) {
		this.resenaCritica = resena;
	}
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
	 * Funcion que obtiene el identificador de una critica
	 * @return Identificador de la critica
	 */
	public int getIdentificadorCritica() {
		return this.idCritica;
	}
	/**
	 * Funcion que modifica el identificador de una critica
	 * @param id Nuevo identificador de la critica
	 */
	public void setIdentificadorCritica(int id) {
		this.idCritica = id;
	}
}
