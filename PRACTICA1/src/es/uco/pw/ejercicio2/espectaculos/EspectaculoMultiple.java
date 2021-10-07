package es.uco.pw.ejercicio2.espectaculos;

import java.util.ArrayList;

/**
 * Funcion que crea un espectaculo multiple
 * @author Jaime Lorenzo Sanchez
 * @version 1.0
 */

public class EspectaculoMultiple extends FactoriaEspectaculos {

	private ArrayList<SesionEspectaculo> sesionesEspectaculo; // Sesiones del espectaculo
	
	public EspectaculoMultiple(String titulo, String descripcion, CategoriaEspectaculo categoria, String tipo,
			String dia1, String hora1, String dia2, String hora2) {
		ArrayList<SesionEspectaculo> lista = new ArrayList<SesionEspectaculo>(); // Lista de sesiones del espectaculo
		SesionEspectaculo sesion1 = new SesionEspectaculo(); // Sesion1 de espectaculo vacia
		SesionEspectaculo sesion2 = new SesionEspectaculo(); // Sesion2 de espectaculo vacia
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
