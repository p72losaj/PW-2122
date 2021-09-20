package es.uco.pw.classes;

import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.sql.Date;

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
	
	private String tituloEspectaculo ;
	
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
	
	private java.sql.Date fechaRepresentacionEspectaculo;
	
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
			java.util.Date cambio = formato.parse(fecha);
			this.fechaRepresentacionEspectaculo = new java.sql.Date(cambio.getTime());
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
	
	/**
	 * Funcion que comprueba si el dia introducido se cumple en el mes indicado
	 * @param mes Mes de la fecha 
	 * @param dia Dia de la fecha
	 * @return Valor de comprobacion de la validez de las fechas
	 */

	public int comprobarValidezFechasMesDia(int mes, int dia){
		
		// Caso 1: El dia introducido es superior a 28 y el mes es febrero
		
		if(mes == 2 && dia > 28) {
			System.out.println("El mes " + mes + " no tiene " + dia + " dias."
					+ "Debe introducir como maximo un valor de 28 dias para dicho mes\n");
			return 1;
		}
		
		// Caso 2: El mes introducido tiene maximo 30 dias, pero el usuario ha indicado un numero superior de dias
		
		else if( dia > 30 && (mes == 4 || mes == 6 || mes == 9 || mes == 11) ){
			System.out.println("El mes " + mes + " no tiene " + dia + " dias."
					+ "Debe introducir como maximo un valor de 30 dias para dicho mes\n");
			return 1;
		}
		
		// Caso 3: El mes introducido tiene maximo 31 dias, pero el usuario ha indicado un numero superior de dias
		
		else if(dia > 31 && (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12)) {
			System.out.println("El mes " + mes + " no tiene " + dia + " dias."
					+ "Debe introducir como maximo un valor de 31 dias para dicho mes\n");
			return 1;
		}
		
		// Por defecto, las fechas son correctas
		
		return 0;
		
	}


	/**
	 * Funcion que crea un menu con las opciones de obtener la fecha
	 * @param teclado Scanner para introducir datos por teclado
	 * @return Opcion de la funcionalidad del menu
	 */
	
	public int crearMenuObtencionFecha(Scanner teclado) {
		int opcion = 2;
		System.out.println("Menu de obtencion de fechas");
		System.out.println("Pulsa 0 si deseas modificar el mes de representacion del espectaculo");
		System.out.println("Pulsa 1 si deseas modificar el dia de representacion del espectaculo");
		try {
			opcion = teclado.nextInt();
		}catch(Exception ex) {
			System.out.println("Introduce un valor entero al elegir una opcion del menu");
		}
		return opcion;
	}
	
	/**
	 * Funcion que muestra un menu con las categorias disponibles de un espectaculo
	 */

	public void mostrarMenuCategoriasEspectaculo() {
		
		System.out.println("\t Menu de categorias de un espectaculo");
		System.out.println("1. concierto");
		System.out.println("2. obra");
		System.out.println("3. monologo");
		System.out.println("Introduce una opcion: ");
	}
	


	
}
