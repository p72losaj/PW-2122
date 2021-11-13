package es.uco.pw.negocio.espectaculo;

import java.util.ArrayList;

/**
 * Clase que crea un espectaculo de temporada
 * @author Jaime Lorenzo sanchez
 * @version 2.0
 */

public class EspectaculoTemporadaDTO extends EspectaculoDTO {

	public EspectaculoTemporadaDTO(String titulo, String descripcion, CategoriaEspectaculo categoria, String tipo,
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

	public EspectaculoTemporadaDTO(String tituloEspectaculo, String descripcionEspectaculo,
			CategoriaEspectaculo categoriaEspectaculo, String tipoEspectaculo, int aforoLocalidades,
			int ventasEspectaculo, int horaTemporada1, int minutosTemporada1, String diaSemanaTemporada,
			int horaTemporada2, int minutosTemporada2) {
		ArrayList<SesionEspectaculoDTO> lista = new ArrayList<SesionEspectaculoDTO>(); // Lista de sesiones del espectaculo
		SesionEspectaculoDTO inicio = new SesionEspectaculoDTO(); // Sesion de inicio del espectaculo
		SesionEspectaculoDTO fin = new SesionEspectaculoDTO(); // Sesion de finalizacion del espectaculo
		this.setTituloEspectaculo(tituloEspectaculo);
		this.setDescripcionEspectaculo(descripcionEspectaculo);
		this.setCategoriaEspectaculo(categoriaEspectaculo);
		this.setTipoEspectaculo(tipoEspectaculo);
		this.setAforoLocalidadesEspectaculo(aforoLocalidades);
		this.setVentasEspectaculo(ventasEspectaculo);
		inicio.setHoraSesion(horaTemporada1);
		inicio.setMinutos(minutosTemporada1);
		inicio.setHoraCompleta();
		inicio.setDiaSemana(diaSemanaTemporada);
		lista.add(inicio);
		fin.setDiaSemana(diaSemanaTemporada);
		fin.setHoraSesion(horaTemporada2);
		fin.setMinutos(minutosTemporada2);
		fin.setHoraCompleta();
		lista.add(fin);
		this.setSesionesEspectaculo(lista);
		return;
	}
	

}
