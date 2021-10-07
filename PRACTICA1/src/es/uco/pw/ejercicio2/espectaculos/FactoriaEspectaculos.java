package es.uco.pw.ejercicio2.espectaculos;

public abstract class FactoriaEspectaculos implements InterfazEspectaculo {

	private String tituloEspectaculo; // Titulo del espectaculo
	private String descripcionEspectaculo; // Descripcion del espectaculo
	private CategoriaEspectaculo categoriaEspectaculo; // Categoria del espectaculo
	private String tipoEspectaculo; // Tipo del espectaculo
	
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
	
	/**
	 * Funcion que crea un espectaculo de tipo puntual
	 * @param titulo Titulo del espectaculo
	 * @param descripcion Descripcion del espectaculo
	 * @param categoria Categoria del espectaculo
	 * @param tipo Tipo del espectaculo
	 * @return Espectaculo de tipo puntual
	 */
	
	public EspectaculoPuntual crearEspectaculoPuntual(String titulo,String descripcion, CategoriaEspectaculo categoria, String tipo) {
		return new EspectaculoPuntual(titulo,descripcion,categoria,tipo);
	}

	
}
