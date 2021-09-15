package es.uco.pw.classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Programa que implementa una clase llamada Programacion, la cual almacena una lista de espectaculos
 * @author Jaime Lorenzo Sanchez
 * @version 1.0
 *
 */

public class Programacion {
	/**
	 * Lista de espectaculos
	 */
	private ArrayList<Espectaculo> listaEspectaculos;
	
	/**
	 * Constructor vacio de la programacion
	 */
	
	public Programacion() {
		this.listaEspectaculos = new ArrayList<Espectaculo>();
	}
	
	/**
	 * Funcion que anade un espectaculo a la lista de espectaculos de la programacion
	 * @param espectaculo Espectaculo a anadir en la lista de espectaculos de la programacion
	 */
	
	public void programarEspectaculo(Espectaculo espectaculo) {
		listaEspectaculos.add(espectaculo);
	}
	
	/**
	 * Funcion que obtiene todos los espectaculos almacenados en la programacion
	 * @return Lista de espectaculos de la programacion
	 */
	
	public ArrayList<Espectaculo> verEspectaculos(){
		return this.listaEspectaculos;
	}
	
	/**
	 * Funcion que imprime por consola el contenido de todos los espectaculos de la programacion
	 */
	
	public void imprimirEventos() {
		// Recorremos la lista de espectaculos de la programacion
		for(int i=0; i<this.listaEspectaculos.size(); i++) {
			// Imprimimos la informacion de cada espectaculo de la programacion
			this.listaEspectaculos.get(i).toStringEspectaculo();
		}
	}
	
	/**
	 * Funcion que devuelve una lista de los titulos de los espectaculos almacenados en la programacion
	 * @return Lista de titulos de los espectaculos almacenados en la programacion
	 */
	
	public ArrayList<String> verTitulos(){
		// Creamos una lista de cadenas vacia
		ArrayList<String> titulos = new ArrayList<String>();
		
		// Recorremos la lista de espectaculos almacenados en la programacion
		
		for(int i=0; i<this.listaEspectaculos.size(); i++) {
			
			// Almacenamos en la lista de titulos los titulos de los espectaculos almacenados en la programacion
			
			titulos.add(this.listaEspectaculos.get(i).getTituloEspectaculo());
			
		}
		
		// Retornamos la lista de titulos de los espectaculos de la programacion
		
		return titulos;
	}
	
	/**
	 * Funcion que elimina un espectaculo dado su titulo
	 * @param tituloEspectaculo Titulo del espectaculo a eliminar de la programacion
	 * @return true si se ha eliminado el espectaculo de la programacion, false en caso contrario
	 */
	
	public Boolean eliminarEspectaculo(String tituloEspectaculo) {
		
		// Recorremos la lista de espectaculos almacenados en la programacion
		
		for(int i=0; i<this.listaEspectaculos.size(); i++ ) {
			
			// Comprobamos si el titulo a eliminar se corresponde con el titulo del espectaculo de la programacion
			
			if(this.listaEspectaculos.get(i).getTituloEspectaculo().equals(tituloEspectaculo)) {
				
				// Eliminamos el espectaculo de la lista de espectaculos de la programacion
				
				this.listaEspectaculos.remove(i);
				
				// Retornamos true
				
				return true;
			}
			
		}
		
		// Por defecto, devuelve false
		
		return false;
	}
	
	/**
	 * Funcion que devuelve la lista de espectaculos programados hasta una fecha
	 * @param fecha Fecha que establece el limite de los anuncios programados que se deseen obtener
	 * @return Lista de espectaculos programados hasta la fecha pasada como argumento
	 */
	
	public ArrayList<Espectaculo> verProximosEspectaculos(String fecha){
		
		// Creamos una lista de espectaculos vacia
		
		ArrayList<Espectaculo> espectaculos = new ArrayList<Espectaculo>();
		
		
		// Obtenemos la fecha actual en milisegundos
		
		long miliseconds = System.currentTimeMillis();
		
		// Transformamos la fecha actual en formato java.sql.Date

		java.sql.Date fechaActual = new java.sql.Date(miliseconds);
		
		// Formato de las cadenas
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		
		// Transformamos la fecha introducida en Date
		
		Date comparar;
		
		try {
			
			comparar = formato.parse(fecha);
			
			// La fecha introducida es posterior a la fecha actual
			
			if(comparar.after(fechaActual)) {

				// Recorremos la lista de espectaculos en programacion
				
				for(int i=0; i<this.listaEspectaculos.size(); i++) {
					
					// La fecha del espectaculo es anterior a la fecha a comparar
					
					if(this.listaEspectaculos.get(i).getFechaRepresentacionEspectaculo().before(comparar)) {

						// Anadimos el espectaculo a la lista de espectaculos a devolver
						
						espectaculos.add(this.listaEspectaculos.get(i));
						
					}
					
				}
			}
			
			// La fecha introducida no es posterior a la fecha actual
			
			else {
				return null;
			}
			
		} catch (ParseException e) {
			System.out.println("Se ha producido un error al comparar las fechas");
		}
		
		// Retornamos la lista de espectaculos programados hasta la fecha
		
		return espectaculos;
		
	}
	
	 //verEspectaculosDisponibles
	
	// end
}
