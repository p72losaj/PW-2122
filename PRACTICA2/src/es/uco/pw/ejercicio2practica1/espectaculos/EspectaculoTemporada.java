package es.uco.pw.ejercicio2practica1.espectaculos;

import java.util.ArrayList;

/**
 * Clase que crea un espectaculo de temporada
 * @author Jaime Lorenzo sanchez
 * @version 1.0
 */

public class EspectaculoTemporada extends FactoriaEspectaculos {
	
	private ArrayList<SesionEspectaculo> sesionesEspectaculo; // Lista de sesiones del espectaculo

	public EspectaculoTemporada(String titulo, String descripcion, CategoriaEspectaculo categoria, String tipo,
			String fechaInicio, String fechaFin, String dia) {
		ArrayList<SesionEspectaculo> lista = new ArrayList<SesionEspectaculo>(); // Lista de sesiones del espectaculo
		SesionEspectaculo inicio = new SesionEspectaculo(); // Sesion de inicio del espectaculo
		SesionEspectaculo fin = new SesionEspectaculo(); // Sesion de finalizacion del espectaculo
		this.setTituloEspectaculo(titulo); // Titulo del espectaculo 
		this.setDescripcionEspectaculo(descripcion); // Descripcion del espectaculo
		this.setCategoriaEspectaculo(categoria); // Categoria del espectaculo
		this.setTipoEspectaculo(tipo); // Tipo del espectaculo
		inicio.setFechaCompletaSesion(fechaInicio); // Obtenemos la fecha de inicio del espectaculo
		inicio.setDiaSemana(dia); // Obtenemos el dia de la semana de la fecha de inicio
		lista.add(inicio); 		// anadimos la sesion a la lista de sesiones del espectaculo
		fin.setFechaCompletaSesion(fechaFin); // Obtenemos la fecha fin de la sesion
		fin.setDiaSemana(dia); // Obtenemos el dia de la semana de la fecha fin de la sesion
		lista.add(fin); 		// anadimos la sesion a la lista de sesiones del espectaculo
		this.setSesionesEspectaculo(lista);// Modificamos la lista de sesiones del espectaculo
		return;
	}

	/**
	 * Funcion que obtiene las sesiones de un espectaculo
	 * @return Lista de sesiones del espectaculo
	 */
	
	public ArrayList<SesionEspectaculo> getSesionesEspectaculo() {
		return this.sesionesEspectaculo;
	}


	/**
	 * Funcion que modifica las sesiones de un espectaculo
	 * @param sesionesEspectaculo Nueva lista de sesiones de un espectaculo
	 */
	
	public void setSesionesEspectaculo(ArrayList<SesionEspectaculo> sesionesEspectaculo) {
		this.sesionesEspectaculo = sesionesEspectaculo;
	}

}
