package es.uco.pw.negocio.espectaculo;

import java.util.ArrayList;

/**
 * Clase que crea un espectaculo de temporada
 * @author Jaime Lorenzo sanchez
 * @version 1.0
 */

public class EspectaculoTemporadaDTO extends EspectaculoDTO {
	
	private ArrayList<SesionEspectaculoDTO> sesionesEspectaculo; // Lista de sesiones del espectaculo

	public EspectaculoTemporadaDTO(String titulo, String descripcion, CategoriaEspectaculoDTO categoria, String tipo,
			String fechaInicio, String fechaFin, String dia, int aforoLocalidades, int ventasEspectaculo) {
		ArrayList<SesionEspectaculoDTO> lista = new ArrayList<SesionEspectaculoDTO>(); // Lista de sesiones del espectaculo
		SesionEspectaculoDTO inicio = new SesionEspectaculoDTO(); // Sesion de inicio del espectaculo
		SesionEspectaculoDTO fin = new SesionEspectaculoDTO(); // Sesion de finalizacion del espectaculo
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
		this.setAforoLocalidadesEspectaculo(aforoLocalidades); // Obtenemos el aforo de localidades del espectaculo
		this.setVentasEspectaculo(ventasEspectaculo); // Obtenemos el numero de ventas del espectaculo
		return;
	}

	/**
	 * Funcion que obtiene las sesiones de un espectaculo
	 * @return Lista de sesiones del espectaculo
	 */
	
	public ArrayList<SesionEspectaculoDTO> getSesionesEspectaculo() {
		return this.sesionesEspectaculo;
	}


	/**
	 * Funcion que modifica las sesiones de un espectaculo
	 * @param sesionesEspectaculo Nueva lista de sesiones de un espectaculo
	 */
	
	public void setSesionesEspectaculo(ArrayList<SesionEspectaculoDTO> sesionesEspectaculo) {
		this.sesionesEspectaculo = sesionesEspectaculo;
	}

}
