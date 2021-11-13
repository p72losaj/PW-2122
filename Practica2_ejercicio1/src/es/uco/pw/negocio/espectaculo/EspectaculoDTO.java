package es.uco.pw.negocio.espectaculo;

import java.util.ArrayList;

/**
 * Clase que gestiona las operaciones de un espectaculo
 * @author Jaime Lorenzo Sanchez
 * @version 1.0
 */

public class EspectaculoDTO implements InterfazEspectaculo{
	private int identificadorEspectaculo; // Identificador del espectaculo
	private String tituloEspectaculo; // Titulo del espectaculo
	private String descripcionEspectaculo; // Descripcion del espectaculo
	private CategoriaEspectaculo categoriaEspectaculo; // Categoria del espectaculo
	private String tipoEspectaculo; // Tipo del espectaculo
	private SesionEspectaculoDTO sesionEspectaculo; // sesion del espectaculo
	private int localidades; // Aforo de localidades del evento
	private ArrayList<SesionEspectaculoDTO> sesionesEspectaculo; // Lista de sesiones del espectaculo

	/**
	 * Constructor de clase
	 * @author Jaime Lorenzo Sanchez
	 */
	
	public EspectaculoDTO() {
		
	}
	
	
	@Override
	public void setSesionesEspectaculo(ArrayList<SesionEspectaculoDTO> sesionesEspectaculo) {
		this.sesionesEspectaculo = sesionesEspectaculo;
	}
	
	@Override
	public ArrayList<SesionEspectaculoDTO> getSesionesEspectaculo() {
		return this.sesionesEspectaculo;
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
	public int getIdentificadorEspectaculo() {
		return this.identificadorEspectaculo;
	}

	@Override
	public void setIdentificadorEspectaculo(int id) {
		this.identificadorEspectaculo = id;
	}


}
