package es.uco.pw.negocio.espectaculo;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Clase que gestiona la fecha y hora de una sesion
 * @author Jaime Lorenzo Sanchez
 * @version 1.0
 */

public class SesionEspectaculoDTO {
	
	private int dia; // dia de la fecha de la sesion
	private int mes; // mes de la fecha de la sesion
	private int ano; // ano de la fecha de la sesion
	private Date fecha; // fecha de la sesion
	private int hora; // hora de la sesion
	private int minutos; // minutos de la sesion
	private String horaCompleta; // Hora completa de la sesion
	private String diaSemana; // dia de la semana de la sesion
	
	/**
	 * Funcion que obtiene el dia de la semana de la sesion
	 * @return Dia de la semana de la sesion
	 */
	
	public String getDiaSemana() {
		return this.diaSemana;
	}
	
	/**
	 * Funcion que modifica el dia de la semana del espectaculo
	 * @param diaSemana Nuevo dia de la semana del espectaculo
	 */
	
	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}
	
	/**
	 * Funcion que obtiene una hora completa
	 * @return Hora completa (horas:minutos)
	 */
	
	public String getHoraCompleta() {
		this.horaCompleta = String.valueOf(this.hora) +":" + String.valueOf(this.minutos);
		return this.horaCompleta;
	}
	
	/**
	 * Funcion que modifica la hora completa de una sesion
	 * @param hora Nueva hora completa de la sesion
	 */
	
	public void setHoraCompleta(String hora) {
		this.horaCompleta = hora;
	}
	
	/**
	 * Funcion que obtiene los minutos de la hora completa de la sesion
	 * @return Minutos de la hora completa de la sesion
	 */
	
	public int getMinutosSesion() {
		return this.minutos;
	}
	
	/**
	 * Funcion que modifica los minutos de la hora completa de la sesion
	 * @param minutos Nuevos minutos de la hora completa de la sesion
	 */
	
	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}
	
	/**
	 * Funcion que obtiene la hora de la sesion
	 * @return Hora de la sesion
	 */
	
	public int getHoraSesion() {
		return this.hora;
	}
	
	/**
	 * Funcion que modifica la hora de la sesion
	 * @param hora Nueva hora de la sesion
	 */
	
	public void setHoraSesion(int hora) {
		this.hora = hora;
	}
	
	/**
	 * Funcion que obtiene la fecha de la sesion
	 * @return Fecha de la sesion
	 */
	
	public Date getFechaCompletaSesion() {
		return this.fecha;
	}
	
	/**
	 * Funcion que modifica la fecha completa de la sesion de un espectaculo
	 * @param fecha Nueva fecha completa de la sesion del espectaculo
	 */
	
	public void setFechaCompletaSesion(String fecha) {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); // Formato de las cadenas
		try {
			java.util.Date cambio = formato.parse(fecha);
			this.fecha = new java.sql.Date(cambio.getTime());
		}catch(Exception ex) {
			System.out.println("Se ha producido un error al transformar la nueva fecha de representacion del anuncio en java.util.Date");
			return;
		}
	}
	
	/**
	 * Funcion que obtiene el ano de la sesion
	 * @return Ano de la sesion
	 */
	
	public int getAnoSesion() {
		return this.ano;
	}
	
	/**
	 * Funcion que modifica el ano de sesion
	 * @param ano Nuevo ano de la sesion
	 */
	
	public void setAnoSesion(int ano) {
		this.ano = ano;
	}
	
	/**
	 * Funcion que obtiene el mes de la fecha de la sesion
	 * @return Mes de la sesion
	 */
	
	public int getMesSesion() {
		return this.mes;
	}
	
	/**
	 * Funcion que modifica el mes de la fecha de la sesion
	 * @param mes Nuevo mes de la sesion
	 */
	
	public void setMesSesion(int mes) {
		this.mes = mes;
	}
	
	/**
	 * Funcion que obtiene el dia de la sesion
	 * @return Dia de la sesion
	 */
	
	public int getDiaSesion() {
		return this.dia;
	}
	
	/**
	 * Funcion que modifica el dia de la fecha de la sesion
	 * @param dia Nuevo dia de la fecha de la sesion
	 */
	
	public void setDiaSesion(int dia) {
		this.dia = dia;
	}
	
	/**
	 * Constructor vacio de clase
	 */
	
	public SesionEspectaculoDTO() {
	}
	
}
