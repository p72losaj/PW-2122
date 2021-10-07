package es.uco.pw.ejercicio2.espectaculos;

import java.util.ArrayList;

import es.uco.pw.ejercicio1.critica.Critica;

/**
 * Clase que crea y gestiona un gestor de espectaculos
 * @author Jaime Lorenzo Sanchez
 * @version 1.0
 */

public class GestorEspectaculos {
	
	ArrayList<Critica> listaCriticas = new ArrayList<Critica>(); // Lista de criticas asociadas al espectaculo
	
	ArrayList<EspectaculoPuntual> listaEspectaculosPuntual = new ArrayList<EspectaculoPuntual>(); // Lista de espectaculos puntuales
	
	ArrayList<EspectaculoMultiple> listaEspectaculosMultiple = new ArrayList<EspectaculoMultiple>(); // Lista de espectaculos multiples
	
	ArrayList<EspectaculoTemporada> listaEspectaculoTemporada = new ArrayList<EspectaculoTemporada>(); // Lista de espectaculos de temporada
	
	/**
	 *	Instancia unica de clase
	 */
	
	private static GestorEspectaculos instancia = null;
	
	private FactoriaEspectaculos factoria; // Factoria de espectaculos
	
	/**
	 * Constructor de clase
	 */
	
	private GestorEspectaculos() {
		
	}
	
	/**
	 * Acceso a un punto de la instancia
	 */
	
	public static GestorEspectaculos getInstancia() {
		if(instancia == null) {
			instancia = new GestorEspectaculos();
		}
		return instancia;
	}
	
	/**
	 * Funcion que da de alta un espectaculo ( crea un espectaculo) usando una factoria
	 * @param tituloEspectaculo Titulo del espectaculo a crear
	 * @param descripcionEspectaculo Descripcion del espectaculo
	 * @param categoriaEspectaculo Categoria del espectaculo
	 * @param tipoEspectaculo Tipo del espectaculo
	 * @param fechaPuntual Fecha completa de sesion de un espectaculo puntual
	 * @param horaPuntual Hora completa de sesion de un espectaculo puntual
	 * @param diaMultiple1 Dia de la semana de la sesion1 de un espectaculo multiple
	 * @param horaMultiple1 Hora completa de la sesion 1 de un espectaculo multiple
	 * @param diaMultiple2 Dia de la semana de la sesion 2 de un espectaculo multiple
	 * @param horaMultiple2 Hora completa de la sesion 2 de un espectaculo multiple
	 * @param fechaInicioTemporada Fecha completa de inicio de una sesion de un espectaculo de temporada
	 * @param fechaFinTemporada Fecha completa de finalizacion de una sesion de un espectaculo de temporada
	 * @param diaTemporada Dia de la semana de una sesion de un espectaculo de temporada
	 */

	public void DarAltaEspectaculo(String tituloEspectaculo, String descripcionEspectaculo,CategoriaEspectaculo categoriaEspectaculo,
			String tipoEspectaculo, String fechaPuntual, String horaPuntual, String diaMultiple1,
			String horaMultiple1,  String diaMultiple2, String horaMultiple2, String fechaInicioTemporada,
			String fechaFinTemporada, String diaTemporada) {
		
		// Caso 1: El espectaculo a crear es de tipo puntual
		
		if(tipoEspectaculo.equals("puntual")) {
			EspectaculoPuntual puntual; // Espectaculo puntual vacio
			puntual = factoria.crearEspectaculoPuntual(tituloEspectaculo, descripcionEspectaculo, categoriaEspectaculo, tipoEspectaculo, fechaPuntual, horaPuntual); // Creacion del espectaculo puntual
			this.listaEspectaculosPuntual.add(puntual); // Anadimos el espectaculo puntual a la lista de espectaculos puntuales
		}
		
		// caso 2: El espectaculo a crear es de tipo multiple
		
		else if(tipoEspectaculo.equals("multiple")) {
			EspectaculoMultiple multiple; // Espectaculo multiple vacio
			multiple = factoria.crearEspectaculoMultiple(tituloEspectaculo, descripcionEspectaculo, categoriaEspectaculo, tipoEspectaculo, diaMultiple1, horaMultiple1, diaMultiple2, horaMultiple2);
			this.listaEspectaculosMultiple.add(multiple); // anadimos el espectaculo multiple a la lista de espectaculos multiple
		}
		
		// Caso 3: el espectaculo a crear es de temporada
		
		else if(tipoEspectaculo.equals("temporada")) {
			EspectaculoTemporada temporada; // Espectaculo de temporada vacio
			temporada = factoria.crearEspectaculoTemporada(tituloEspectaculo, descripcionEspectaculo, categoriaEspectaculo, tipoEspectaculo, fechaInicioTemporada, fechaFinTemporada, diaTemporada); // Creamos el espectaculo de temporada
			this.listaEspectaculoTemporada.add(temporada); // anadimos el espectaculo de temporada a la lista de espectaculos de temporada
		}
		
	}
	
}
