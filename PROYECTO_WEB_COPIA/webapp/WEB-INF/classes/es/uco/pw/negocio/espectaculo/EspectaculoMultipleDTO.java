package es.uco.pw.negocio.espectaculo;

import java.util.ArrayList;

/**
 * Funcion que crea un espectaculo multiple
 * @author Jaime Lorenzo Sanchez
 * @version 2.0
 */

public class EspectaculoMultipleDTO extends EspectaculoDTO {

	public EspectaculoMultipleDTO(String tituloEspectaculo, String descripcionEspectaculo,
			CategoriaEspectaculo categoriaEspectaculo, String tipoEspectaculo, int aforoLocalidades,
			int ventasEspectaculo1, int horaMultiple1, int minutosMultiple1, String diaSemanaMultiple1,
			String diaSemanaMultiple2, int horaMultiple2, int minutosMultiple2, int ventasEspectaculo2) {
		ArrayList<SesionEspectaculoDTO> lista = new ArrayList<SesionEspectaculoDTO>(); // Lista de sesiones del espectaculo
		SesionEspectaculoDTO sesion = new SesionEspectaculoDTO(); // Sesion de espectaculo vacia
		this.setTituloEspectaculo(tituloEspectaculo);
		this.setDescripcionEspectaculo(descripcionEspectaculo);
		this.setCategoriaEspectaculo(categoriaEspectaculo);
		this.setTipoEspectaculo(tipoEspectaculo);
		this.setAforoLocalidadesEspectaculo(aforoLocalidades);
		/*
		 * DATOS DE LA SESION 1 DEL ESPECTACULO
		 */
		sesion.setHoraSesion(horaMultiple1);
		sesion.setMinutos(minutosMultiple1);
		sesion.setHoraCompleta(); // Hora completa de la sesion
		sesion.setDiaSemana(diaSemanaMultiple1);
		sesion.setVentasSesion(ventasEspectaculo1); // Numero de ventas de la primera sesion
		lista.add(sesion); // anadimos la sesion a la lista de sesiones
		/*
		 * DATOS DE LA SESION 2 DEL ESPECTACULO
		 */
		sesion = new SesionEspectaculoDTO(); // Sesion de espectaculo vacia
		sesion.setHoraSesion(horaMultiple2);
		sesion.setMinutos(minutosMultiple2);
		sesion.setHoraCompleta(); // Hora completa de la sesion
		sesion.setDiaSemana(diaSemanaMultiple2);
		sesion.setVentasSesion(ventasEspectaculo2); // Numero de ventas de la segunda sesion
		lista.add(sesion); // Anadimos la sesion a la lista de sesiones
		this.setSesionesEspectaculo(lista); // Anadimos la lista de sesiones al espectaculo
	}

}
