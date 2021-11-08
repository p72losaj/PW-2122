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

import es.uco.pw.datos.dao.critica.CriticaDAO;
import es.uco.pw.datos.dao.relacion.EspectaculoCriticaDAO;
import es.uco.pw.datos.dao.relacion.UsuarioCriticaDAO;

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
				critica.setResenaCritica(this.listaCriticas.get(i).getResenaCritica()); // Obtenemos la resena de la critica
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
	/**
	 * Funcion que inserta una critica en la base de datos
	 * @param prop Fichero de configuracion
	 * @param sql Fichero de sentencias sql
	 * @param critica Datos de la critica ha insertar en la base de datos
	 * @param tituloEspectaculo Titulo del espectaculo a insertar en la base de datos
	 * @param puntuacion Puntuacion del espectaculo
	 * @return Estado de la insercion de los datos de la critica en la base de datos
	 */
	public int insercionCriticaGestor(Properties prop, Properties sql,CriticaDTO critica, String tituloEspectaculo, int puntuacion) {
		int status = 0; // Numero de filas modificadas de la base de datos
		CriticaDAO criticaDAO = new CriticaDAO();
		EspectaculoCriticaDAO puntuacionEspectaculo = new EspectaculoCriticaDAO();
		status = criticaDAO.insercionCritica(prop,sql,critica); // Insertamos los datos de la critica en la base de datos
		if(status != 0) { // Caso 1: Datos de la critica insertada en la base de datos
			int identificador = criticaDAO.obtencionIdentificadorCritica(prop,sql,critica.getTituloCritica()); // Obtenemos el identificador de la critica registrada en la base de datos
			// Caso de error: Identificador de la critica no obtenido
			if(identificador == 0) {status = criticaDAO.eliminacionCritica(prop,sql,critica.getTituloCritica());} // Eliminamos los datos de la critica de la base de datos
			else { // Caso de exito: Identificador de la critica obtenido
				critica.setIdentificadorCritica(identificador); // Almacenamos el identificador de la critica
				status = puntuacionEspectaculo.creacionRelacion(prop,sql,critica.getIdentificadorCritica(), tituloEspectaculo, puntuacion); // Registramos la puntuacion del espectaculo
				// Caso de error: Puntuacion del espectaculo no registrado en la base de datos
				if(status == 0) { status = criticaDAO.eliminacionCritica(prop,sql,critica.getTituloCritica());} // Eliminamos los datos de la critica 
				else { this.listaCriticas.add(critica); }// Caso de exito: Anadimos la critica en el gestor de criticas
			}
		}
		return status; // Insercion de los datos de la critica en la base de datos
	}
	/**
	 * Funcion que obtiene los datos de una critica en funcion de su identificador
	 * @param identificadorCritica Identificador de la critica
	 * @return Datos de la critica
	 */
	public CriticaDTO obtencionDatosCritica(int identificadorCritica) {
		CriticaDTO critica = new CriticaDTO(); // Critica vacia
		for(int i=0; i < this.listaCriticas.size(); i++) { // Recorremos la lista de criticas almacenadas en el gestor de criticas
			if(this.listaCriticas.get(i).getIdentificadorCritica() == identificadorCritica) { // Critica encontrada
				critica.setIdentificadorCritica(this.listaCriticas.get(i).getIdentificadorCritica()); // Obtenemos el identificador de la critica
				critica.setAutorCritica(this.listaCriticas.get(i).getAutorCritica()); // Obtenemos el autor de la critica
				critica.setResenaCritica(this.listaCriticas.get(i).getResenaCritica()); // Obtenemos la resena de la critica
				critica.setTituloCritica(this.listaCriticas.get(i).getTituloCritica()); // Obtenemos el titulo de la critica
			}
		}
		return critica; // Retornamos los datos de la critica
	}
	/**
	 * Funcion que comprueba la existencia de una critica dado su identificador
	 * @param identificadorCritica Identificador de la critica
	 * @return true si el identificador esta registrado; false en caso contrario
	 */
	public boolean comprobacionExistenciaIdentificadorCritica(int identificadorCritica) {
		for(int i=0; i < this.listaCriticas.size(); i++) { // Recorremos la lista de criticas almacenadas en el gestor de criticas
			if(this.listaCriticas.get(i).getIdentificadorCritica() == identificadorCritica) { // Identificador de la critica encontrado
				return true;
			}
		}
		return false; // Por defecto, retornamos false
	}
	/**
	 * Funcion que obtiene los datos de todas las criticas registradas en la base de datos
	 * @param prop Fichero de configuracion
	 * @param sql Fichero de propiedades
	 */
	public void obtencionDatosCriticas(Properties prop, Properties sql) {
		CriticaDAO criticaDAO = new CriticaDAO();
		EspectaculoCriticaDAO puntuacionEspectaculo = new EspectaculoCriticaDAO();
		UsuarioCriticaDAO evaluacionCritica = new UsuarioCriticaDAO();
		this.setListaCriticas(criticaDAO.obtencionCriticas(prop, sql)); // Obtenemos la lista de criticas registradas en la base de datos
		// Obtenemos el resto de datos de la critica
		for(int i=0; i < this.getListaCriticas().size(); i++) {
			this.getListaCriticas().get(i).setTituloEspectaculo(puntuacionEspectaculo.obtencionTituloEspectaculo(prop,sql,this.getListaCriticas().get(i).getIdentificadorCritica())); // Obtenemos el identificador del espectaculo
			this.getListaCriticas().get(i).setPuntuacionEspectaculo(puntuacionEspectaculo.obtencionPuntuacionEspectaculo(prop,sql,this.getListaCriticas().get(i).getIdentificadorCritica())); // Obtenemos la puntuacion del espectaculo
			this.getListaCriticas().get(i).setListaEvaluacionesCritica(evaluacionCritica.obtencionEvaluacionesCritica(prop,sql,this.getListaCriticas().get(i).getIdentificadorCritica())); // Obtenemos las evaluaciones de utilidad de las criticas
		}
	}

}
