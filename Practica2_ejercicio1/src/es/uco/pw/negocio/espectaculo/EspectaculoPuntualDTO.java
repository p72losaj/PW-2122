package es.uco.pw.negocio.espectaculo;

/**
 * Funcion que crea un espectaculo de tipo puntual
 * @author Jaime Lorenzo Sanchez
 * @version 1.0
 */

public class EspectaculoPuntualDTO extends EspectaculoDTO {

	public EspectaculoPuntualDTO(String titulo, String descripcion, CategoriaEspectaculoDTO categoria, String tipo, String fecha, String hora, int aforoLocalidades, int ventasEspectaculo) {
		this.setTituloEspectaculo(titulo);// titulo del espectaculo
		this.setDescripcionEspectaculo(descripcion);// Descripcion del espectaculo
		this.setCategoriaEspectaculo(categoria);// Categoria del espectaculo
		this.setTipoEspectaculo(tipo); // Tipo del espectaculo
		SesionEspectaculoDTO sesion = new SesionEspectaculoDTO(); // Sesion de espectaculo vacia
		sesion.setFechaCompletaSesion(fecha); // Obtenemos la nueva fecha completa de la sesion
		sesion.setHoraCompleta(hora); // Obtenemos la hora completa de la sesion
		this.setSesionEspectaculo(sesion); // Obtenemos la sesion del espectaculo
		this.setAforoLocalidadesEspectaculo(aforoLocalidades); // Obtenemos el aforo de localidades del espectaculo
		this.setVentasEspectaculo(ventasEspectaculo); // Obtenemos el numero de ventas del espectaculo
		return;
	}
	

}
