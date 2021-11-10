package es.uco.pw.negocio.espectaculo;

/**
 * Funcion que crea un espectaculo de tipo puntual
 * @author Jaime Lorenzo Sanchez
 * @version 2.0
 */

public class EspectaculoPuntualDTO extends EspectaculoDTO {	

	public EspectaculoPuntualDTO(String tituloEspectaculo, String descripcionEspectaculo,
			CategoriaEspectaculo categoriaEspectaculo, String tipoEspectaculo, int aforoLocalidades,
			int ventasEspectaculo, int anoPuntual, int mesPuntual,int diaPuntual, int horaPuntual, int minutosPuntual) {
			
		SesionEspectaculoDTO sesion = new SesionEspectaculoDTO(); // Sesion de espectaculo vacia
		this.setTituloEspectaculo(tituloEspectaculo); // Obtenemos el titulo del espectaculo
		this.setDescripcionEspectaculo(descripcionEspectaculo); // Obtenemos la descripcion del espectaculo
		this.setCategoriaEspectaculo(categoriaEspectaculo); // Obtenemos la categoria del espectaculo
		this.setTipoEspectaculo(tipoEspectaculo); // Obtenemos el tipo del espectaculo
		this.setAforoLocalidadesEspectaculo(aforoLocalidades); // Obtenemos el aforo de localidades
		this.setVentasEspectaculo(ventasEspectaculo); // Obtenemos las ventas del espectaculo
		sesion.setAnoSesion(anoPuntual); // Obtenemos el ano de la sesion
		sesion.setMesSesion(mesPuntual); // Obtenemos el mes de la sesion
		sesion.setDiaSesion(diaPuntual); // Obtenemos el dia de la sesion
		sesion.setFechaCompletaSesion(anoPuntual+"-"+mesPuntual+"-"+diaPuntual); // Obtenemos la fecha completa de la sesion
		sesion.setHoraSesion(horaPuntual); // Obtenemos la hora de la sesion
		sesion.setMinutos(minutosPuntual); // Obtenemos los minutos de la sesion
		sesion.setHoraCompleta(); // Obtenemos la hora completa
		this.setSesionEspectaculo(sesion); // Obtenemos los datos de la sesion
		return;
	}
	

}
