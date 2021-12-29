package es.uco.pw.negocio.critica;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;

import es.uco.pw.datos.dao.critica.CriticaDAO;
import es.uco.pw.datos.dao.espectaculo.EspectaculoDAO;
import es.uco.pw.datos.dao.relacion.EspectaculoCriticaDAO;
import es.uco.pw.datos.dao.relacion.UsuarioCriticaDAO;
import es.uco.pw.datos.dao.sesion.SesionDAO;
import es.uco.pw.negocio.espectaculo.EspectaculoDTO;
import es.uco.pw.negocio.espectaculo.SesionEspectaculoDTO;

/**
 * Gestor de criticas, disenado mediante el patron de diseno Singleton
 * @author Jaime Lorenzo Sanchez
 * @author Jose Angel Exposito Fernandez 
 * @version 2.2
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
	 * @param prop Fichero de configuracion
	 * @param sql Fichero de sentencias sql
	 * @param identificadorCritica Identificador de la critica a leiminar
	 * @param correoUsuario Autor de la critica
	 * @param idUsuario Identificador del usuario
	 * @return Estado de eliminacion de la critica
	 */

	public String eliminacionCritica(Properties prop, Properties sql,int identificadorCritica, String correoUsuario, int idUsuario) {
		CriticaDAO criticaDAO = new CriticaDAO();
		EspectaculoCriticaDAO puntuacionDAO = new EspectaculoCriticaDAO();
		UsuarioCriticaDAO valoracion = new UsuarioCriticaDAO();
		String cadena = null;
		int identificadorEliminar = 0;
		/*
		 * COMPROBAMOS SI EL USUARIO PUEDE ELIMINAR LA CRITICA
		 */
		for(int i=0; i < this.listaCriticas.size(); i++) {
			
			if( (this.listaCriticas.get(i).getIdentificadorCritica() == identificadorCritica) && (this.listaCriticas.get(i).getAutorCritica().equals(correoUsuario))) 
			{
				identificadorEliminar = identificadorCritica;
			}
		}
		/*
		 * CASO ERROR
		 */
		if(identificadorEliminar == 0) {
			cadena = "Critica no encontrada o el usuario no tiene permisos para eliminar la critica";
		}
		else {
			// Eliminamos la puntuacion del espectaculo
			int status = puntuacionDAO.eliminacionPuntuacionEspectaculoCritica(prop, sql, identificadorEliminar);
			if(status == 0) {
				cadena = "Se ha producido un error al eliminar la puntuacion de la critica";
			}
			else {
				cadena = "Se ha eliminado la puntuacion de la critica";
				EvaluacionUtilidadCriticaDTO evaluacion = new EvaluacionUtilidadCriticaDTO();
				evaluacion = valoracion.obtencionValoracionCriticaIdentificador(prop, sql, identificadorEliminar);
				/*
				 * CRITICA CON VALORACION DE UTILIDAD
				 */
				if(evaluacion.getIdentificadorValoracion() != 0) {
					// Eliminamos la valoracion de utilidad de la critica
					status = valoracion.eliminarValoracionUtilidadIdentificadorCritica(prop, sql, identificadorEliminar);
					/*
					 * VALORACION DE UTILIDAD NO ELIMINADA
					 */
					if(status == 0) {
						cadena = cadena + "\n Se ha producido un error al eliminar la valoracion de utilidad de la critica";
					}
					/*
					 * VALORACION DE UTILIDAD ELIMINADA
					 */
					else {
						cadena = cadena + "\n Se ha eliminado la valoracion de utilidad de la critica";
						// Eliminamos los datos propios de la critica
						status = criticaDAO.eliminacionCritica(prop, sql, identificadorEliminar);
						if(status == 0) {
							cadena = "\n Se ha producido un error al eliminar la critica";
						}
						else {
							cadena = "\n Se ha eliminado los datos de la critica";
						}
					}
				}
				/*
				 * CRITICA SIN VALORACION DE UTILIDAD
				 */
				else {
					// Eliminamos los datos propios de la critica
					status = criticaDAO.eliminacionCritica(prop, sql, identificadorEliminar);
					if(status == 0) {
						cadena = "\n Se ha producido un error al eliminar la critica";
					}
					else {
						cadena = "\n Se ha eliminado los datos de la critica";
					}
				}
				
			}
		}
		/*
		for(int i=0; i<this.listaCriticas.size(); i++) { // Recorremos la lista de criticas
			if(this.listaCriticas.get(i).getTituloCritica().equals(identificadorCritica)) { // Critica encontrada
				 criticaDAO.eliminacionCritica(prop, sql,identificadorCritica);
				this.listaCriticas.remove(this.listaCriticas.get(i)); // Eliminamos la critica del gestor de criticas
			}
		}
		*/
		return cadena;
	}

	/**
	 * Funcion que inserta una critica en la base de datos
	 * @param prop Fichero de configuracion
	 * @param sql Fichero de sentencias sql
	 * @param correo Correo del usuario
	 * @param tituloCritica Titulo de la critica
	 * @param resenaCritica Resena de la critica
	 * @param tituloEspectaculo Titulo del espectaculo
	 * @param puntuacion 
	 * @param fechaActual Fecha actual
	 * @return Estado de la insercion de los datos de la critica en la base de datos
	 */
	public String registroCritica(Properties prop, Properties sql,String correo, String tituloCritica, String resenaCritica, String tituloEspectaculo, int puntuacion, String fechaActual){
		String estado = null; // Estado del registro de la critica
		CriticaDTO criticaDTO = new CriticaDTO();
		CriticaDAO criticaDAO = new CriticaDAO();
		SesionDAO sesionDAO = new SesionDAO();
		EspectaculoDTO espectaculoDTO = new EspectaculoDTO();
		EspectaculoDAO espectaculoDAO = new EspectaculoDAO();
		EspectaculoCriticaDAO puntuacionEspectaculo = new EspectaculoCriticaDAO();
		SesionEspectaculoDTO sesionDTO = new SesionEspectaculoDTO();
		int status = 0; // Numero de filas modificadas de la base de datos
		/*
		 * COMPROBACION DE LOS DATOS UNICOS DE LA CRITICA
		 */
		Boolean existenciaCritica = comprobacionExistenciaTituloCritica(tituloCritica); // Comprobacion de la existencia del titulo de la critica
		Boolean existenciaEspectaculo = espectaculoDAO.comprobacionExistenciaTituloEspectaculo(prop,sql,tituloEspectaculo); // Comprobacion de la existencia del titulo del espectaculo
		/*
		 * TITULO DE LA CRITICA YA REGISTRADO
		 */
		if(existenciaCritica == true) { estado = "Titulo de la critica ya registrado. No se ha realizado el registro de la critica"; } // Indicamos el estado del registro de la critica
		/*
		 * TITULO DEL ESPECTACULO NO REGISTRADO EN LA BASE DE DATOS
		 */
		else if(existenciaEspectaculo == false) { estado = "Titulo del espectaculo no registrado. No se ha realizado el registro de la critica"; }
		// Puntuacion del espectaculo es negativo
		else if(puntuacion < 0) { estado = "Puntuacion del espectaculo" + puntuacion + " negativa. No se ha realizado el registro de la critica"; }
		// Puntuacion del espectaculo es superior a 10
		else if(puntuacion > 10) { estado = "Puntuacion del espectaculo superior a 10. No se ha realizado el registro de la critica"; }
		/*
		 * DATOS UNICOS DE LA CRITICA NO REGISTRADOS
		 */
		else {
			criticaDTO.setAutorCritica(correo); // Almacenamos el autor de la critica
			criticaDTO.setTituloCritica(tituloCritica); // Almacenamos el titulo de la critica
			criticaDTO.setResenaCritica(resenaCritica); // Almacenamos la resena de la critica
			criticaDTO.setTituloEspectaculo(tituloEspectaculo); // Almacenamos el titulo del espectaculo
			criticaDTO.setPuntuacionEspectaculo(puntuacion); // Almacenamos la puntuacion del espectaculo
			/*
			 * OBTENCION DE LOS DATOS DEL ESPECTACULO
			 */
			espectaculoDTO = espectaculoDAO.obtencionEspectaculo(prop, sql,tituloEspectaculo); // Obtenemos los datos del espectaculo
			
			if(espectaculoDTO.getTipoEspectaculo().equals("puntual")) {
				// Obtenemos los datos de sesion del espectaculo
				sesionDTO = sesionDAO.obtencionSesionEspectaculoPuntual(prop, sql, tituloEspectaculo);
				// Realizamos la comprobacion de la fecha actual con la fecha de la sesion
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				java.sql.Date actual; // Fecha actual en formato sql
				try {
					java.util.Date parsed = format.parse(fechaActual);
					actual = new java.sql.Date(parsed.getTime());
					if(actual.compareTo(sesionDTO.getFechaCompletaSesion()) < 0) {
						estado = "Fecha de la sesion es posterior a la fecha actual";
						return estado;
					}
				}catch(Exception ex) {
					estado = "Se ha producido un error al transformar la fecha actual a formato sql.date";
					return estado;
				}
				
			}
			
			status = criticaDAO.insercionCritica(prop,sql,criticaDTO); // Insertamos los datos de la critica en la base de datos
			/*
			 * CRITICA NO INSERTADA EN LA BASE DE DATOS
			 */
			if(status == 0) 
			{ 
				estado = "Se ha producido un error al anadir los datos unicos de la critica en la base de datos";
				return estado;
			}
			/*
			 * CRITICA INSERTADA EN LA BASE DE DATOS
			 */
			
			else{
				estado = "Se han anadido los datos unicos de la critica a la base de datos";
				int identificador = criticaDAO.obtencionIdentificadorCritica(prop,sql,criticaDTO.getTituloCritica()); // Obtenemos el identificador de la critica registrada en la base de datos
				/*
				 * IDENTIFICADOR DE LA CRITICA NO OBTENIDO
				 */
				if(identificador == 0) {
					estado = "Se ha producido un error al obtener el identificador de la critica.";
					int status2 = criticaDAO.eliminacionCritica(prop,sql,identificador); // Eliminamos los datos de la critica de la base de datos
					if(status2 == 0) { estado = estado + "Se ha producido un error al eliminar los datos de critica";}
					else { estado = estado + " Se han eliminado los datos de la critica almacenados en la base de datos";}
					return estado;
				}
				/*
				 * IDENTIFICADOR DE LA CRITICA OBTENIDO
				 */
				else {
					criticaDTO.setIdentificadorCritica(identificador); // Almacenamos el identificador de la critica
					status = puntuacionEspectaculo.creacionRelacion(prop,sql,criticaDTO.getIdentificadorCritica(), criticaDTO.getTituloCritica(),criticaDTO.getPuntuacionEspectaculo()); // Registramos la puntuacion del espectaculo
					/*
					 * PUNTUACION DEL ESPECTACULO NO REGISTRADO
					 */
					if(status == 0) {
						estado = "Se ha producido un error al registrar la puntuacion del espectaculo.";
						int status2 = criticaDAO.eliminacionCritica(prop,sql,identificador); // Eliminamos los datos de la critica de la base de datos
						if(status2 == 0) { 
							estado = estado + "Se ha producido un error al eliminar los datos de critica";
						}
						else 
						{ 
							estado = estado + " Se han eliminado los datos de la critica registrados en la base de datos";
						}
						return estado;
					}
					/*
					 * PUNTUACION DEL ESPECTACULO REGISTRADO
					 */
					else {		
						estado = "Se han registrado correctamente los datos de la critica";
					}
				}
			}
		}

		return estado; // Retornamos el estado del registro de la critica
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
	public ArrayList<CriticaDTO> obtencionDatosCriticas(Properties prop, Properties sql) {
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
		
			return this.listaCriticas;
		
	}
	/**
	 * Funcion que realiza la valoracion de utilidad de una critica
	 * @param prop Fichero de configuracion
	 * @param sql Fichero de sentencias sql
	 * @param identificadorCritica Identificador de la critica
	 * @param correoUsuario Correo del usuario
	 * @param idUsuario Identificador del usuario
	 * @param valoracion Valoracion de utilidad de la critica
	 * @return Cadena con el estado de la valoracion de utilidad de la critica
	 */
	public String valoracionUtilidadCritica(Properties prop, Properties sql, int identificadorCritica, String correoUsuario, int idUsuario, int valoracion) {
		CriticaDTO criticaDTO = new CriticaDTO();
		EvaluacionUtilidadCriticaDTO valoracionCritica = new EvaluacionUtilidadCriticaDTO();
		UsuarioCriticaDAO evaluacionCritica = new UsuarioCriticaDAO();
		String estado = null; // Cadena que almacena el estado de la valoracion de utilidad de la critica
		Boolean comprobacionIdCritica = comprobacionExistenciaIdentificadorCritica(identificadorCritica); // Comprobamos si el identificador de la critica esta registrado en el gestor
		/*
		 * IDENTIFICADOR DE LA CRITICA NO REGISTRADO EN EL GESTOR DE CRITICAS
		 */
		if(comprobacionIdCritica == false) { estado = "Identificador de la critica no registrado en el gestor de criticas";}
		/*
		 * IDENTIFICADOR DE LA CRITICA REGISTRADO EN EL GESTOR DE CRITICAS
		 */
		else {
			criticaDTO = obtencionDatosCritica(identificadorCritica); // Obtenemos la informacion de la critica
			/*
			 * USUARIO ES EL AUTOR DE LA CRITICA
			 */
			if(criticaDTO.getAutorCritica().equals(correoUsuario)) {estado = "No se puede valorar la utilidad de una critica propia";}
			/*
			 * USUARIO NO ES EL AUTOR DE LA CRITICA
			 */
			else {
				/*
				 * COMPROBACION DE LA EXISTENCIA DE LA EVALUACION DE UTILIDAD DE LA CRITICA
				 */ 
				valoracionCritica = evaluacionCritica.obtencionValoracionCriticaUsuario(prop, sql, identificadorCritica,idUsuario);
				/*
				 * EL USUARIO YA HA REALIZADO LA VALORACION DE UTILIDAD DE LA CRITICA
				 */
				if(valoracionCritica.getValoracionCritica() != -1) { estado = "El usuario ya ha evaluado la utilidad de la critica"; }
				/*
				 * EL USUARIO NO HA REALIZADO LA VALORACION DE UTILIDAD DE LA CRITICA
				 */
				else {
					/*
					 * REGISTRAMOS LA VALORACION DE UTILIDAD DE LA CRITICA EN LA BASE DE DATOS
					 */
					int status = evaluacionCritica.registrovaloracionUtilidadCritica(prop,sql,criticaDTO.getIdentificadorCritica(),idUsuario,valoracion);
					/*
					 * NO SE HA MODIFICADO LA BASE DE DATOS
					 */
					if(status == 0) { estado = "No se ha registrado la valoracion de utilidad de la critica";}
					/*
					 * SE HA MODIFICADO LA BASE DE DATOS
					 */
					else {
						estado = "Se han anadido los datos de la valoracion de la critica a la base de datos";
					}
				}
			}
		}
		return estado; // Retornamos el estado de la valoracion de utilidad de la critica
	}
	

}
