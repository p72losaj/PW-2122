package es.uco.pw.ejercicio2.espectaculos;

public class EspectaculoPuntual extends FactoriaEspectaculos {

	public EspectaculoPuntual(String titulo, String descripcion, CategoriaEspectaculo categoria, String tipo) {
		this.setTituloEspectaculo(titulo);// titulo del espectaculo
		this.setCategoriaEspectaculo(categoria);// Categoria del espectaculo
		this.setDescripcionEspectaculo(descripcion);// Descripcion del espectaculo
		this.setTipoEspectaculo(tipo); // Tipo del espectaculo
		return;
	}
	

}
