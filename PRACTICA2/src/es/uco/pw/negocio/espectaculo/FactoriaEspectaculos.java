package es.uco.pw.negocio.espectaculo;

/**
 * Factoria de espectaculos
 * @author Jaime Lorenzo Sanchez
 * @version 1.0
 */

public abstract class FactoriaEspectaculos implements InterfazEspectaculo {

	private String tituloEspectaculo; // Titulo del espectaculo
	private String descripcionEspectaculo; // Descripcion del espectaculo
	private CategoriaEspectaculo categoriaEspectaculo; // Categoria del espectaculo
	private String tipoEspectaculo; // Tipo del espectaculo
	private SesionEspectaculo sesionEspectaculo; // sesion del espectaculo
	 
	
	/**
	 * Constructor vacio de la factoria de espectaculos
	 */
	
	public FactoriaEspectaculos() {
	}


	@Override
	public String getTituloEspectaculo() {
		return this.tituloEspectaculo;
	}

	@Override
	public void setTituloEspectaculo(String titulo) {
		this.tituloEspectaculo = titulo;
	}

	@Override
	public String getDescripcionEspectaculo() {
		return this.descripcionEspectaculo;
	}
	
	@Override
	public void setDescripcionEspectaculo(String descripcion) {
		this.descripcionEspectaculo = descripcion;
	}
	
	@Override
	public CategoriaEspectaculo getCategoriaEspectaculo() {
		return this.categoriaEspectaculo;
	}
	
	@Override
	public void setCategoriaEspectaculo(CategoriaEspectaculo categoria) {
		this.categoriaEspectaculo = categoria;
	}
	
	@Override
	public String getTipoEspectaculo() {
		return this.tipoEspectaculo;
	}
	
	@Override
	public void setTipoEspectaculo(String tipo) {
		this.tipoEspectaculo = tipo;
	}
	
	@Override
	public SesionEspectaculo getSesionEspectaculo() {
		return this.sesionEspectaculo;
	}
	

	@Override
	public void setSesionEspectaculo(SesionEspectaculo sesion) {
		this.sesionEspectaculo = sesion;
	}
	
	@Override
	public EspectaculoPuntual crearEspectaculoPuntual(String titulo,String descripcion, CategoriaEspectaculo categoria, String tipo, String fecha, String hora) {
		return new EspectaculoPuntual(titulo,descripcion, categoria, tipo, fecha, hora);
	}
	
	@Override
	public EspectaculoTemporada crearEspectaculoTemporada(String titulo, String descripcion, CategoriaEspectaculo categoria, String tipo, String fechaInicio, String fechaFin, String dia) {
		return new EspectaculoTemporada(titulo, descripcion, categoria,tipo,fechaInicio,fechaFin,dia);
	}

	@Override
	public EspectaculoMultiple crearEspectaculoMultiple(String titulo,String descripcion, CategoriaEspectaculo categoria, String tipo, String dia1, String hora1, String dia2, String hora2) {
		return new EspectaculoMultiple(titulo,descripcion, categoria, tipo, dia1, hora1, dia2, hora2);
	}
	
}
