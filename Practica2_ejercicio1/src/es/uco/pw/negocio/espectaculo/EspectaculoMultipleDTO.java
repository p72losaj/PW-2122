package es.uco.pw.negocio.espectaculo;

import java.util.ArrayList;

/**
 * Funcion que crea un espectaculo multiple
 * @author Jaime Lorenzo Sanchez
 * @version 1.0
 */

public class EspectaculoMultipleDTO extends EspectaculoDTO {

	private ArrayList<SesionEspectaculoDTO> sesionesEspectaculo; // Sesiones del espectaculo
	
	public EspectaculoMultipleDTO(String titulo, String descripcion, CategoriaEspectaculoDTO categoria, String tipo,
			String dia1, String hora1, String dia2, String hora2, int aforoLocalidades, int ventasEspectaculo) {
		ArrayList<SesionEspectaculoDTO> lista = new ArrayList<SesionEspectaculoDTO>(); // Lista de sesiones del espectaculo
		SesionEspectaculoDTO sesion1 = new SesionEspectaculoDTO(); // Sesion1 de espectaculo vacia
		SesionEspectaculoDTO sesion2 = new SesionEspectaculoDTO(); // Sesion2 de espectaculo vacia
		this.setTituloEspectaculo(titulo); // Obtenemos el titulo del espectaculo
		this.setDescripcionEspectaculo(descripcion);// Obtenemos la descripcion del espectaculo
		this.setCategoriaEspectaculo(categoria); // Obtenemos la categoria del espectaculo
		this.setTipoEspectaculo(tipo); // Obtenemos el tipo del espectaculo
		sesion1.setDiaSemana(dia1);// Obtenemos el dia de la semana de la sesion1
		sesion1.setHoraCompleta(hora1);// Obtenemos la hora completa de la sesion 1 
		lista.add(sesion1); // Anadimos la sesion1 a la lista de sesiones
		sesion2.setDiaSemana(dia2);// Obtenemos el dia de la semana de la sesion2
		sesion2.setHoraCompleta(hora2); // Obtenemos la hora completa de la sesion2
		lista.add(sesion2); // anadimos la sesion2 a la lista
		this.setSesionesEspectaculo(lista); // Modificamos la lista de sesiones del espectaculo
		this.setAforoLocalidadesEspectaculo(aforoLocalidades); // Obtenemos el aforo de localidades del espectaculo
		this.setVentasEspectaculo(ventasEspectaculo); // Obtenemos el numero de ventas del espectaculo
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
