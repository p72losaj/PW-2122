package es.uco.pw.negocio.espectaculo;

/**
 * Clase que gestiona las operaciones de un espectaculo
 * @author Jaime Lorenzo Sanchez
 * @version 1.0
 */

public class EspectaculoDTO implements InterfazEspectaculoDTO{
	
	private String tituloEspectaculo; // Titulo del espectaculo
	private String descripcionEspectaculo; // Descripcion del espectaculo
	private CategoriaEspectaculoDTO categoriaEspectaculo; // Categoria del espectaculo
	private String tipoEspectaculo; // Tipo del espectaculo
	private SesionEspectaculoDTO sesionEspectaculo; // sesion del espectaculo
	private int localidades; // Aforo de localidades del evento
	private int ventasEspectaculo; // Numero de ventas del espectaculo

	/**
	 * Constructor de clase
	 * @author Jaime Lorenzo Sanchez
	 */
	
	public EspectaculoDTO() {
		
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
	public CategoriaEspectaculoDTO getCategoriaEspectaculo() {
		return this.categoriaEspectaculo;
	}

	@Override
	public void setCategoriaEspectaculo(CategoriaEspectaculoDTO categoria) {
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
	public SesionEspectaculoDTO getSesionEspectaculo() {
		return this.sesionEspectaculo;
	}

	@Override
	public void setSesionEspectaculo(SesionEspectaculoDTO sesion) {
		this.sesionEspectaculo = sesion;
	}

	@Override
	public int getAforoLocalidadesEspectaculo() {
		// TODO Auto-generated method stub
		return this.localidades;
	}

	@Override
	public void setAforoLocalidadesEspectaculo(int aforo) {
		this.localidades = aforo;
	}

	@Override
	public int getVentasEspectaculo() {
		return this.ventasEspectaculo;
	}

	@Override
	public void setVentasEspectaculo(int ventas) {
		this.ventasEspectaculo = ventas;
	}


}
