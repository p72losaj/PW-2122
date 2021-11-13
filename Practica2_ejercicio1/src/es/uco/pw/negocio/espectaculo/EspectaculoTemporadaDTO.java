package es.uco.pw.negocio.espectaculo;

import java.util.ArrayList;

/**
 * Clase que crea un espectaculo de temporada
 * @author Jaime Lorenzo sanchez
 * @version 2.0
 */

public class EspectaculoTemporadaDTO extends EspectaculoDTO {

	public EspectaculoTemporadaDTO(String tituloEspectaculo, String descripcionEspectaculo,
			CategoriaEspectaculo categoriaEspectaculo, String tipoEspectaculo, int aforoLocalidades,
			int ventasEspectaculo, int horaTemporada1, int minutosTemporada1, String diaSemanaTemporada,
			int horaTemporada2, int minutosTemporada2, int ventasEspectaculo2) {
		ArrayList<SesionEspectaculoDTO> lista = new ArrayList<SesionEspectaculoDTO>(); // Lista de sesiones del espectaculo
		SesionEspectaculoDTO inicio = new SesionEspectaculoDTO(); // Sesion de inicio del espectaculo
		SesionEspectaculoDTO fin = new SesionEspectaculoDTO(); // Sesion de finalizacion del espectaculo
		this.setTituloEspectaculo(tituloEspectaculo);
		this.setDescripcionEspectaculo(descripcionEspectaculo);
		this.setCategoriaEspectaculo(categoriaEspectaculo);
		this.setTipoEspectaculo(tipoEspectaculo);
		this.setAforoLocalidadesEspectaculo(aforoLocalidades);
		inicio.setHoraSesion(horaTemporada1);
		inicio.setMinutos(minutosTemporada1);
		inicio.setHoraCompleta();
		inicio.setDiaSemana(diaSemanaTemporada);
		inicio.setVentasSesion(ventasEspectaculo);
		lista.add(inicio);
		fin.setDiaSemana(diaSemanaTemporada);
		fin.setHoraSesion(horaTemporada2);
		fin.setMinutos(minutosTemporada2);
		fin.setHoraCompleta();
		fin.setVentasSesion(ventasEspectaculo2);
		lista.add(fin);
		this.setSesionesEspectaculo(lista);
		return;
	}
	

}
