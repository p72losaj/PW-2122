package es.uco.pw.datos.dao.sesion;

import java.util.Properties;

import es.uco.pw.negocio.espectaculo.EspectaculoDTO;

/**
 * Clase que gestiona la tabla de sesiones de un espectaculo 
 * @author Jaime Lorenzo Sanchez
 * @version 1.0
 */
public class SesionDAO {
	private int idSesion; // Identificador de la sesion
	private String tituloEspectaculo; // Titulo del espectaculo de la sesion
	private int DiaSesion; // Dia de la sesion
	private int MesSesion; // Mes de la sesion
	private int AnoSesion; // Ano de la sesion
	private int horaSesion; // Hora de la sesion
	private int minutosSesion; // Minutos de la sesion
	private String diaSemana; // Dia de la semana
	/**
	 * Funcion que obtiene el dia de la semana
	 * @return Dia de la semana de la sesion
	 */
	public String getDiaSemana() {
		return this.diaSemana;
	}
	/**
	 * Funcion que modifica el dia de la semana de la sesion
	 * @param semana Nuevo dia de la semana de la sesion
	 */
	public void setDiaSemana(String semana) {
		this.diaSemana = semana;
	}
	/**
	 * Funcion que obtiene los minutos de la sesion
	 * @return Minutos de la sesion
	 */
	public int getMinutosSesion() {
		return this.minutosSesion;
	}
	/**
	 * Funcion que modifica los minutos de la sesion
	 * @param min Nuevos minutos de la sesion
	 */
	public void setMinutosSesion(int min) {
		this.minutosSesion = min;
	}
	/**
	 * Funcion que obtiene la hora de la sesion
	 * @return Hora de la sesion
	 */
	public int getHoraSesion() {
		return this.horaSesion;
	}
	/**
	 * Funcion que modifica la hora de la sesion
	 * @param hora Nueva hora de la sesion
	 */
	public void setHoraSesion(int hora) {
		this.horaSesion = hora;
	}
	/**
	 * Funcion que obtiene el ano de la sesion
	 * @return Ano de la sesion
	 */
	public int getAnoSesion() {
		return this.AnoSesion;
	}
	/**
	 * Funcion que modifica el ano de la sesion
	 * @param ano Nuevo ano de la sesion
	 */
	public void setAnoSesion(int ano) {
		this.AnoSesion = ano;
	}
	/**
	 * Funcion que obtiene el mes de la sesion
	 * @return Mes de la sesion
	 */
	public int getMesSesion() {
		return this.MesSesion;
	}
	/**
	 * Funcion que modifica el mes de la sesion
	 * @param mes Nuevo mes de la sesion
	 */
	public void setMesSesion(int mes) {
		this.MesSesion = mes;
	}
	/**
	 * Funcion que obtiene el dia de la sesion
	 * @return Dia de la sesion
	 */
	public int getDiaSesion() {
		return this.DiaSesion;
	}
	/**
	 * Funcion que modifica el dia de la sesion
	 * @param dia Nuevo dia de la sesion
	 */
	public void setDiaSesion(int dia) {
		this.DiaSesion = dia;
	}
	/**
	 * Funcion que obtiene el identificador de la sesion
	 * @return Identificador de la sesion
	 */
	public int getIdentificadorSesion(){
		return this.idSesion;
	}
	/**
	 * Funcion que modifica el identificador de la sesion
	 * @param id Nuevo identificador de la sesion
	 */
	public void setIdentificadorSesion(int id) {
		this.idSesion = id;
	}
	/**
	 * Funcion que obtiene el titulo del espectaculo de la sesion
	 * @return Titulo del espectaculo de la sesion
	 */
	public String getTituloEspectaculo() {
		return this.tituloEspectaculo;
	}
	/**
	 * Funcion que modifica el titulo del espectaculo de la sesion
	 * @param tituloEspectaculo Nuevo titulo del espectaculo de la sesion
	 */
	public void setTituloEspectaculo(String tituloEspectaculo) {
		this.tituloEspectaculo = tituloEspectaculo;
	}
	
	public int anadirSesionEspectaculo(Properties prop, Properties sql, EspectaculoDTO espectaculo) {
		return 0;
	}
	
	public int modificarSesionEspectaculo(Properties prop, Properties sql, EspectaculoDTO espectaculo) {
		return 0;
	}
	
	public int cancelarSesionEspectaculo(Properties prop, Properties sql, EspectaculoDTO espectaculo) {
		return 0;
	}
	public SesionDAO obtencionSesionEspectaculoPuntual(Properties prop, Properties sql, String titulo) {
		// TODO Auto-generated method stub
		return null;
	}
}
