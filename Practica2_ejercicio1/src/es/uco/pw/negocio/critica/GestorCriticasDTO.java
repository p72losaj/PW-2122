package es.uco.pw.negocio.critica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Gestor de criticas, disenado mediante el patron de diseno Singleton
 * @author Jaime Lorenzo Sanchez
 * @version 1.0
 */

public class GestorCriticasDTO {
	
	ArrayList<CriticaDTO> listaCriticas = new ArrayList<CriticaDTO>(); // Lista de criticas
	/**
	 * Funcion que obtiene la lista de criticas del gestor de criticas
	 * @return Lista de criticas del gestor
	 */
	public ArrayList<CriticaDTO> getListaCriticas(){
		return this.listaCriticas;
	}
	/**
	 * Funcion que modifica la lista de criticas del gestor de criticas
	 * @param criticas Nueva lista de criticas del gestor de criticas
	 */
	public void setListaCriticas(ArrayList<CriticaDTO> criticas) {
		this.listaCriticas = criticas;
	}
	
	/**
	 *	Instancia unica de clase
	 */
	
	private static GestorCriticasDTO instancia = null;
	
	/**
	 * Constructor de clase
	 */
	
	private GestorCriticasDTO() {
		
	}
	
	/**
	 * Acceso a un punto de la instancia
	 */
	
	public static GestorCriticasDTO getInstancia() {
		if(instancia == null) {
			instancia = new GestorCriticasDTO();
		}
		return instancia;
	}
	
	/**
	 * Funcion que comprueba si el titulo de una critica esta registrado o no
	 * @param tituloCritica Titulo de la critica a comprobar su existencia en el sistema
	 * @return true si la critica esta ya registrada; false en caso contrario
	 */

	public boolean comprobacionExistenciaTituloCritica(String tituloCritica) {
		for(int i=0; i < this.listaCriticas.size(); i++) { // Recorremos la lista de criticas
			if(this.listaCriticas.get(i).getTituloCritica().equals(tituloCritica)) { // Busqueda critica
				return true; // Critica registrada -> Retornamos true
			}
		}
		return false; // Por defecto, retorna false
	}
	/**
	 * Funcion que muestra por pantalla la informacion de las criticas registradas
	 */
	public void visualizacionCriticas() {
		for(int i=0; i < this.listaCriticas.size(); i++) { // Recorremos la lista de criticas
			this.listaCriticas.get(i).mostrarCritica(); // Imprimimos la informacion de cada critica almacenada en el gestor de criticas
		}
	}
	/**
	 * Funcion que obtiene los datos de una critica en funcion de su titulo
	 * @param titulo Titulo de la critica
	 * @return Datos de la critica cuyo titulo es el indicado como parametro
	 */

	public CriticaDTO obtencionDatosCritica(String titulo) {
		CriticaDTO critica = new CriticaDTO(); // Creamos una critica vacia
		for(int i=0; i < this.listaCriticas.size(); i++) { // Recorremos la lista de criticas
			if(this.listaCriticas.get(i).getTituloCritica().equals(titulo) ) { // titulo de critica encontrada
				critica.setIdentificadorCritica(this.listaCriticas.get(i).getIdentificadorCritica()); // Obtenemos el identificador de la critica
				critica.setTituloCritica(titulo); // Titulo de la critica
				critica.setResenaEspectaculo(this.listaCriticas.get(i).getResenaEspectaculo()); // Obtenemos la resena de la critica
				critica.setAutorCritica(this.listaCriticas.get(i).getAutorCritica()); // Obtenemos el autor de la critica
			}
		}
		return critica; // Retornamos la informacion de la critica
	}
	
	/**
	 * Funcion que elimina los datos de una critica
	 * @param tituloEliminar Titulo de la critica a eliminar
	 */

	public void eliminacionCritica(String tituloEliminar) {
		for(int i=0; i<this.listaCriticas.size(); i++) { // Recorremos la lista de criticas
			if(this.listaCriticas.get(i).getTituloCritica().equals(tituloEliminar)) { // Critica encontrada
				this.listaCriticas.remove(this.listaCriticas.get(i)); // Eliminamos la critica del gestor de criticas
			}
		}
	}
	
	/**
	 * Funcion que muestra por pantalla los datos de las criticas de un usuario registrado
	 * @param correo Correo del espectador
	 */

	public void visualizacionCriticasUsuario(String correo) {
		for(int i=0; i<this.listaCriticas.size(); i++) { // Recorremos la lista de criticas
			if(this.listaCriticas.get(i).getAutorCritica().equals(correo)) { // Autor de la critica encontrado
				this.listaCriticas.get(i).mostrarCritica(); // Mostramos por pantalla la critica del usuario
			}
		}
	}

}
