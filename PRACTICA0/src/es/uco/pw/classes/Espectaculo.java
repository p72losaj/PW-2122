package es.uco.pw.classes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Clase que representa los espectaculos culturales a programar en un teatro
 * @author Jaime Lorenzo Sanchez
 * @version 1.0
 *
 */

public class Espectaculo {

	/**
	 * Atributos de la clase
	 */
	
	/**
	 * Titulo del espectaculo, de tipo String
	 */
	
	private String tituloEspectaculo;
	
	/**
	 * Enumeracion que representa la categoria del espectaculo
	 */
	
	private CategoriaEspectaculoEnumeracion categoriaEspectaculo;
	
	/**
	 * Descripcion de un espectaculo
	 */
	
	private String descripcionEspectaculo;
	
	/**
	 * Ano de representacion de un espectaculo
	 */
	
	private int anoRepresentacionEspectaculo;
	
	/**
	 * Mes de representacion de un espectaculo
	 */
	
	private int mesRepresentacionEspectaculo;
	
	/**
	 * Dia de representacion de un espectaculo
	 */
	
	private int diaRepresentacionEspectaculo;
	
	/**
	 * Fecha de representacion del espectaculo
	 */
	
	private Date fechaRepresentacionEspectaculo;
	
	/**
	 * Numero de localidades a la venta
	 */
	
	private int localidadesVentaEspectaculo;
	
	/**
	 * Numero de localidades vendidas
	 */
	
	private int localidadesVendidasEspectaculo;
	
	/**
	 * Constructor vacio de un espectaculo
	 */
	
	public Espectaculo() {
		
	}
	
	/**
	 * Constructor parametrizado de un espectaculo 
	 * @param titulo Titulo del espectaculo
	 * @param categoria Categoria del espectaculo
	 * @param descripcion Descripcion del espectaculo
	 */
	
	public Espectaculo(String titulo, CategoriaEspectaculoEnumeracion categoria, String descripcion) {
		this.tituloEspectaculo = titulo;
		this.categoriaEspectaculo = categoria;
		this.descripcionEspectaculo = descripcion;
	}
	
	/**
	 * Funcion que obtiene el titulo de un espectaculo
	 * @return Titulo de un espectaculo
	 */
	
	public String getTituloEspectaculo() {
		return this.tituloEspectaculo;
	}
	
	/**
	 * Funcion que modifica el titulo de un espectaculo
	 * @param titulo Nuevo titulo del espectaculo
	 */
	
	public void setTituloEspectaculo(String titulo) {
		this.tituloEspectaculo = titulo;
	}
	
	/**
	 * Funcion que obtiene la categoria de un espectaculo
	 * @return Categoria de un espectaculo
	 */
	
	public CategoriaEspectaculoEnumeracion getCategoriaEspectaculo() {
		return this.categoriaEspectaculo;
	}
	
	/**
	 * Funcion que modifica la categoria de un espectaculo
	 * @param categoria Nueva categoria de un espectaculo
	 */
	
	public void setCategoriaEspectaculo(CategoriaEspectaculoEnumeracion categoria) {
		this.categoriaEspectaculo = categoria;
	}
	
	/**
	 * Funcion que obtiene la descripcion de un espectaculo
	 * @return Descripcion de un espectaculo
	 */
	
	public String getDescripcionEspectaculo() {
		return this.descripcionEspectaculo;
	}
	
	/**
	 * Funcion que modifica la descripcion de un espectaculo
	 * @param descripcion Nueva descripcion de un espectaculo
	 */
	
	public void setDescripcionEspectaculo(String descripcion) {
		this.descripcionEspectaculo = descripcion;
	}
	
	/**
	 * Funcion que obtiene el ano de descripcion de un espectaculo
	 * @return Ano de representacion de un espectaculo
	 */
	
	public int getAnoDescripcionEspectaculo() {
		return this.anoRepresentacionEspectaculo;
	}
	
	/**
	 * Funcion que modifica el ano de descripcion de un espectaculo
	 * @param ano Nuevo ano de descripcion de un espectaculo
	 */
	
	public void setAnoDescripcionEspectaculo(int ano) {
		this.anoRepresentacionEspectaculo = ano;
	}
	
	/**
	 * Funcion que obtiene el mes de descripcion de un espectaculo
	 * @return Mes de descripcion de un espectaculo
	 */
	
	public int getMesDescripcionEspectaculo() {
		return this.mesRepresentacionEspectaculo;
	}
	
	/**
	 * Funcion que modifica el mes de descripcion de un espectaculo
	 * @param mes Nuevo mes de descripcion de un espectaculo
	 */
	
	public void setMesDescripcionEspectaculo(int mes) {
		this.mesRepresentacionEspectaculo = mes;
	}
	
	/**
	 * Funcion que obtiene el dia de descripcion de un espectaculo
	 * @return Dia de descripcion de un espectaculo
	 */
	
	public int getDiaDescripcionEspectaculo() {
		return this.diaRepresentacionEspectaculo;
	}
	
	/**
	 * Funcion que modifica el dia de descripcion de un espectaculo
	 * @param dia Nuevo dia de descripcion de un espectaculo
	 */
	
	public void setDiaDescripcionEspectaculo(int dia) {
		this.diaRepresentacionEspectaculo = dia;
	}
	
	/**
	 * Funcion que obtiene la fecha de representacion de un espectaculo
	 * @return Fecha de representacion del espectaculo
	 */
	
	public Date getFechaRepresentacionEspectaculo() {
		return this.fechaRepresentacionEspectaculo;
	}
	
	/**
	 * Funcion que modifica la fecha de representacion de un espectaculo
	 * @param fecha Nueva fecha de representacion de un espectaculo
	 */
	
	public void setFechaRepresentacionEspectaculo(String fecha) {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); // Formato de las cadenas
		try {
			this.fechaRepresentacionEspectaculo = formato.parse(fecha);// Transformamos la fecha de representacion del espectaculo en java.util.Date
			System.out.println(getFechaRepresentacionEspectaculo());
		}catch(Exception ex) {
			System.out.println("Se ha producido un error al transformar la nueva fecha de representacion del anuncio en java.util.Date");
			return;
		}
	}
	
	/**
	 * Funcion que obtiene el numero de localidades a la venta de un espectaculo
	 * @return Numero de localidades a la venta de un espectaculo
	 */
	
	public int getNumeroLocalidadesVentaEspectaculo() {
		return this.localidadesVentaEspectaculo;
	}
	
	/**
	 * Funcion que modifica el numero de localidades a la venta de un espectaculo
	 * @param localidades Nuevo numero de localidades a la venta de un espectaculo
	 */
	
	public void setNumeroLocalidadesVentaEspectaculo(int localidades) {
		this.localidadesVentaEspectaculo = localidades;
	}
	
	/**
	 * Funcion que obtiene el numero de localidades vendidas de un espectaculo
	 * @return Numero de localidades vendidas de un espectaculo
	 */
	
	public int getNumeroLocalidadesVendidasEspectaculo() {
		return this.localidadesVendidasEspectaculo;
	}
	
	/**
	 * Funcion que modifica el numero de localidades vendidas de un espectaculo
	 * @param localidades Nuevo numero de localidades vendidas de un espectaculo
	 */
	
	public void setNumeroLocalidadesVendidasEspectaculo(int localidades) {
		this.localidadesVendidasEspectaculo = localidades;
	}
	
	/**
	 * Funcion que imprime toda la informacion de un espectaculo
	 */
	
	public void toStringEspectaculo(){
		System.out.println("Datos del espectaculo: ");
		System.out.println("Titulo del espectaculo: " + this.getTituloEspectaculo()); // Imprimimos el titulo del espectaculo
		System.out.println("Categoria del espectaculo: " + this.getCategoriaEspectaculo()); // Imprimimos la categoria del espectaculo
		System.out.println("Descripcion del espectaculo: " + this.getDescripcionEspectaculo()); // Imprimimos la descripcion del espectaculo
		System.out.println("Fecha de representacion del espectaculo: " + this.getFechaRepresentacionEspectaculo()); // Imprimimos la fecha de representacion del espectaculo
		System.out.println("Numero de localidades en venta del espectaculo: " + this.getNumeroLocalidadesVentaEspectaculo());// Imprimimos el numero de localidades en venta del espectaculo
		System.out.println("Numero de localidades vendidas del espectaculo: " + this.getNumeroLocalidadesVendidasEspectaculo());// Imprimimos el numero de localidades vendidas del espectaculo
	}
	
}
