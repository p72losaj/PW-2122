package es.uco.pw.negocio.espectaculo;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase que crea y gestiona un gestor de espectaculos
 * @author Jaime Lorenzo Sanchez
 * @author Jose Angel Exposito Fernandez
 * @version 2.0
 */

public class GestorEspectaculosDTO {
	
	ArrayList<EspectaculoPuntualDTO> listaEspectaculosPuntual = new ArrayList<EspectaculoPuntualDTO>(); // Lista de espectaculos puntuales
	
	ArrayList<EspectaculoMultipleDTO> listaEspectaculosMultiple = new ArrayList<EspectaculoMultipleDTO>(); // Lista de espectaculos multiples
	
	ArrayList<EspectaculoTemporadaDTO> listaEspectaculoTemporada = new ArrayList<EspectaculoTemporadaDTO>(); // Lista de espectaculos de temporada
	
	/**
	 * Funcion que obtiene la lista de espectculos puntuales
	 * @return Lista de espectaculos puntuales
	 */
	
	public ArrayList<EspectaculoPuntualDTO> getListaEspectaculosPuntual() {
		return listaEspectaculosPuntual;
	}

	public void setListaEspectaculosPuntual(ArrayList<EspectaculoPuntualDTO> listaEspectaculosPuntual) {
		this.listaEspectaculosPuntual = listaEspectaculosPuntual;
	}

	public ArrayList<EspectaculoMultipleDTO> getListaEspectaculosMultiple() {
		return listaEspectaculosMultiple;
	}

	public void setListaEspectaculosMultiple(ArrayList<EspectaculoMultipleDTO> listaEspectaculosMultiple) {
		this.listaEspectaculosMultiple = listaEspectaculosMultiple;
	}

	public ArrayList<EspectaculoTemporadaDTO> getListaEspectaculoTemporada() {
		return listaEspectaculoTemporada;
	}

	public void setListaEspectaculoTemporada(ArrayList<EspectaculoTemporadaDTO> listaEspectaculoTemporada) {
		this.listaEspectaculoTemporada = listaEspectaculoTemporada;
	}
	
	/**
	 *	Instancia unica de clase
	 */
	
	private static GestorEspectaculosDTO instancia = null;
	
	private FactoriaEspectaculosDTO factoria; // Factoria de espectaculos
	
	/**
	 * Constructor de clase
	 */
	
	private GestorEspectaculosDTO() {
		
	}
	
	/**
	 * Acceso a un punto de la instancia
	 */
	
	public static GestorEspectaculosDTO getInstancia() {
		if(instancia == null) {
			instancia = new GestorEspectaculosDTO();
		}
		return instancia;
	}
	
	/**
	 * Funcion que crea un espectaculo usando una factoria
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
	 * @param aforoLocalidades Numero de localidades del espectaculo
	 * @param ventasEspectaculo Numero de ventas del espectaculo
	 */

	public void CrearEspectaculo(String tituloEspectaculo, String descripcionEspectaculo,CategoriaEspectaculoDTO categoriaEspectaculo,
			String tipoEspectaculo, String fechaPuntual, String horaPuntual, String diaMultiple1,
			String horaMultiple1,  String diaMultiple2, String horaMultiple2, String fechaInicioTemporada,
			String fechaFinTemporada, String diaTemporada, int aforoLocalidades,int ventasEspectaculo) {
		
		// Caso 1: El espectaculo a crear es de tipo puntual
		
		if(tipoEspectaculo.equals("puntual")) {
			EspectaculoPuntualDTO puntual; // Espectaculo puntual vacio
			puntual = factoria.crearEspectaculoPuntual(tituloEspectaculo, descripcionEspectaculo, categoriaEspectaculo, tipoEspectaculo, fechaPuntual, horaPuntual,aforoLocalidades,ventasEspectaculo); // Creacion del espectaculo puntual
			this.listaEspectaculosPuntual.add(puntual); // Anadimos el espectaculo puntual a la lista de espectaculos puntuales
		}
		
		// caso 2: El espectaculo a crear es de tipo multiple
		
		else if(tipoEspectaculo.equals("multiple")) {
			EspectaculoMultipleDTO multiple; // Espectaculo multiple vacio
			multiple = factoria.crearEspectaculoMultiple(tituloEspectaculo, descripcionEspectaculo, categoriaEspectaculo, tipoEspectaculo, diaMultiple1, horaMultiple1, diaMultiple2, horaMultiple2,aforoLocalidades,ventasEspectaculo);
			this.listaEspectaculosMultiple.add(multiple); // anadimos el espectaculo multiple a la lista de espectaculos multiple
		}
		
		// Caso 3: el espectaculo a crear es de temporada
		
		else if(tipoEspectaculo.equals("temporada")) {
			EspectaculoTemporadaDTO temporada; // Espectaculo de temporada vacio
			temporada = factoria.crearEspectaculoTemporada(tituloEspectaculo, descripcionEspectaculo, categoriaEspectaculo, tipoEspectaculo, fechaInicioTemporada, fechaFinTemporada, diaTemporada,aforoLocalidades,ventasEspectaculo); // Creamos el espectaculo de temporada
			this.listaEspectaculoTemporada.add(temporada); // anadimos el espectaculo de temporada a la lista de espectaculos de temporada
		}
		
	}
	
	
	public void cancelarEspectaculo(String tituloEliminar) {
		
		// Recorremos las listas de espectaculos
		
		for(int i=0; i<this.listaEspectaculosPuntual.size(); i++) {
		

			if(this.listaEspectaculosPuntual.get(i).getTituloEspectaculo().equals(tituloEliminar)) {
				this.listaEspectaculosPuntual.remove(this.listaEspectaculosPuntual.get(i));
			}
		}
		for(int i=0; i<this.listaEspectaculosMultiple.size(); i++) {
			

				if(this.listaEspectaculosMultiple.get(i).getTituloEspectaculo().equals(tituloEliminar)) {
					this.listaEspectaculosMultiple.remove(this.listaEspectaculosMultiple.get(i));
				}
		}
		for(int i=0; i<this.listaEspectaculoTemporada.size(); i++) {
					

					if(this.listaEspectaculoTemporada.get(i).getTituloEspectaculo().equals(tituloEliminar)) {
						this.listaEspectaculoTemporada.remove(this.listaEspectaculoTemporada.get(i));
					}
		}
		
	}
	
	public void BuscarEspectaculos(Scanner entrada) {
		String titulo = "";
		CategoriaEspectaculoDTO cat = CategoriaEspectaculoDTO.concierto;
		System.out.print("Buscar por 1 titulo o 2 categoria");
		int opc = entrada.nextInt();
		if(opc == 1) {
			
			System.out.print("Introduce el titulo del espectaculo: ");
			titulo = entrada.nextLine();
			
			
			for(int i=0; i<this.listaEspectaculosPuntual.size(); i++) {
				

				if(this.listaEspectaculosPuntual.get(i).getTituloEspectaculo().equals(titulo)) {

					System.out.print(this.listaEspectaculosPuntual.get(i).getTituloEspectaculo());
					System.out.print(this.listaEspectaculosPuntual.get(i).getDescripcionEspectaculo());
					System.out.print(this.listaEspectaculosPuntual.get(i).getCategoriaEspectaculo());
				}
			}
			for(int i=0; i<this.listaEspectaculosMultiple.size(); i++) {
				

					if(this.listaEspectaculosMultiple.get(i).getTituloEspectaculo().equals(titulo)) {
						System.out.print(this.listaEspectaculosMultiple.get(i).getTituloEspectaculo());
						System.out.print(this.listaEspectaculosMultiple.get(i).getDescripcionEspectaculo());
						System.out.print(this.listaEspectaculosMultiple.get(i).getCategoriaEspectaculo());	
					}
			}
			for(int i=0; i<this.listaEspectaculoTemporada.size(); i++) {
						

						if(this.listaEspectaculoTemporada.get(i).getTituloEspectaculo().equals(titulo)) {
							System.out.print(this.listaEspectaculoTemporada.get(i).getTituloEspectaculo());
							System.out.print(this.listaEspectaculoTemporada.get(i).getDescripcionEspectaculo());
							System.out.print(this.listaEspectaculoTemporada.get(i).getCategoriaEspectaculo());	
							}
			}
			
		}
		else {
			
			int cato = 0;
			while(cato == 0){
			System.out.print("Introduce categoria, 1. Concierto, 2. Monologo 3. Teatro ");
			try{cato = entrada.nextInt();
			if(cato == 1) {
				cat = CategoriaEspectaculoDTO.concierto;
			}
			else if(cato == 2) {
				cat = CategoriaEspectaculoDTO.monologo;
			}
			else if(cato == 3) {
				cat = CategoriaEspectaculoDTO.obraTeatro;
			}}catch(Exception ex){
				System.out.println(" la opcion indicada no es una categoria valida ");}
			}
			
			
			
			for(int i=0; i<this.listaEspectaculosPuntual.size(); i++) {
				

				if(this.listaEspectaculosPuntual.get(i).getCategoriaEspectaculo().equals(cat)) {

					System.out.print(this.listaEspectaculosPuntual.get(i).getTituloEspectaculo());
					System.out.print(this.listaEspectaculosPuntual.get(i).getDescripcionEspectaculo());
					System.out.print(this.listaEspectaculosPuntual.get(i).getCategoriaEspectaculo());
				}
			}
			for(int i=0; i<this.listaEspectaculosMultiple.size(); i++) {
				

					if(this.listaEspectaculosMultiple.get(i).getCategoriaEspectaculo().equals(cat)) {
						System.out.print(this.listaEspectaculosMultiple.get(i).getTituloEspectaculo());
						System.out.print(this.listaEspectaculosMultiple.get(i).getDescripcionEspectaculo());
						System.out.print(this.listaEspectaculosMultiple.get(i).getCategoriaEspectaculo());
					}
			}
			for(int i=0; i<this.listaEspectaculoTemporada.size(); i++) {
						

						if(this.listaEspectaculoTemporada.get(i).getCategoriaEspectaculo().equals(cat)) {
							System.out.print(this.listaEspectaculoTemporada.get(i).getTituloEspectaculo());
							System.out.print(this.listaEspectaculoTemporada.get(i).getDescripcionEspectaculo());
							System.out.print(this.listaEspectaculoTemporada.get(i).getCategoriaEspectaculo());	
						}
			}
			
			
			
			
			
			
			
			
		}
		
		
		
		
	};
	
	
}
