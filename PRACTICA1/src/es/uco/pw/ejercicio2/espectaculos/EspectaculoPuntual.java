package es.uco.pw.ejercicio2.espectaculos;

public class EspectaculoPuntual extends FactoriaEspectaculos {

	public EspectaculoPuntual(String titulo, String descripcion, CategoriaEspectaculo categoria, String tipo, String fecha, String hora) {
		this.setTituloEspectaculo(titulo);// titulo del espectaculo
		this.setDescripcionEspectaculo(descripcion);// Descripcion del espectaculo
		this.setCategoriaEspectaculo(categoria);// Categoria del espectaculo
		this.setTipoEspectaculo(tipo); // Tipo del espectaculo
		SesionEspectaculo sesion = new SesionEspectaculo(); // Sesion de espectaculo vacia
		sesion.setFechaCompletaSesion(fecha); // Obtenemos la nueva fecha completa de la sesion
		sesion.setHoraCompleta(hora); // Obtenemos la hora completa de la sesion
		this.setSesionEspectaculo(sesion); // Modificamos la sesion del espectaculo
		return;
	}
	

}
