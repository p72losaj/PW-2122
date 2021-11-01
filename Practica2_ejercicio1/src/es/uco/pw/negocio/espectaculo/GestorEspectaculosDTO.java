package es.uco.pw.negocio.espectaculo;

import java.util.ArrayList;

/**
 * Clase que crea y gestiona un gestor de espectaculos
 * @author Jaime Lorenzo Sanchez
 * @author Jose Angel Exposito Fernandez
 * @version 2.0
 */

public class GestorEspectaculosDTO {
	
	ArrayList<EspectaculoDTO> listaEspectaculos = new ArrayList<EspectaculoDTO>(); // Lista de espectaculos
	
	
	/**
	 * Funcion que obtiene la lista de espectaculos del gestor
	 * @return Lista de espectaculos puntuales
	 */
	
	public ArrayList<EspectaculoDTO> getEspectaculos() {
		return listaEspectaculos;
	}

	public void setListaEspectaculos(ArrayList<EspectaculoDTO> listaEspectaculosPuntual) {
		this.listaEspectaculos = listaEspectaculosPuntual;
	}
	
	/**
	 *	Instancia unica de clase
	 */
	
	private static GestorEspectaculosDTO instancia = null;
	
	private FactoriaEspectaculos factoria; // Factoria de espectaculos
	
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

	public void CrearEspectaculo(String tituloEspectaculo, String descripcionEspectaculo,CategoriaEspectaculo categoriaEspectaculo,
			String tipoEspectaculo, String fechaPuntual, String horaPuntual, String diaMultiple1,
			String horaMultiple1,  String diaMultiple2, String horaMultiple2, String fechaInicioTemporada,
			String fechaFinTemporada, String diaTemporada, int aforoLocalidades,int ventasEspectaculo) {
		
		// Caso 1: El espectaculo a crear es de tipo puntual
		
		if(tipoEspectaculo.equals("puntual")) {
			EspectaculoPuntualDTO puntual; // Espectaculo puntual vacio
			puntual = factoria.crearEspectaculoPuntual(tituloEspectaculo, descripcionEspectaculo, categoriaEspectaculo, tipoEspectaculo, fechaPuntual, horaPuntual,aforoLocalidades,ventasEspectaculo); // Creacion del espectaculo puntual
			this.listaEspectaculos.add(puntual); // Anadimos el espectaculo puntual a la lista de espectaculos puntuales
		}
		
		// caso 2: El espectaculo a crear es de tipo multiple
		
		else if(tipoEspectaculo.equals("multiple")) {
			EspectaculoMultipleDTO multiple; // Espectaculo multiple vacio
			multiple = factoria.crearEspectaculoMultiple(tituloEspectaculo, descripcionEspectaculo, categoriaEspectaculo, tipoEspectaculo, diaMultiple1, horaMultiple1, diaMultiple2, horaMultiple2,aforoLocalidades,ventasEspectaculo);
			this.listaEspectaculos.add(multiple); // anadimos el espectaculo multiple a la lista de espectaculos multiple
		}
		
		// Caso 3: el espectaculo a crear es de temporada
		
		else if(tipoEspectaculo.equals("temporada")) {
			EspectaculoTemporadaDTO temporada; // Espectaculo de temporada vacio
			temporada = factoria.crearEspectaculoTemporada(tituloEspectaculo, descripcionEspectaculo, categoriaEspectaculo, tipoEspectaculo, fechaInicioTemporada, fechaFinTemporada, diaTemporada,aforoLocalidades,ventasEspectaculo); // Creamos el espectaculo de temporada
			this.listaEspectaculos.add(temporada); // anadimos el espectaculo de temporada a la lista de espectaculos de temporada
		}
		
	}
	
	
	public Boolean cancelarEspectaculo(String tituloEliminar) {
		
		// Recorremos la lista de espectaculo
		
		for(int i=0; i<this.listaEspectaculos.size(); i++) {
		
			// Titulo del espectaculo encontrado
			
			if(this.listaEspectaculos.get(i).getTituloEspectaculo().equals(tituloEliminar)) {
				// Eliminamos el espectaculo de la lista de espectaculos
				this.listaEspectaculos.remove(this.listaEspectaculos.get(i));
				// Retornamos true
				return true;
			}
		}
		
		// Por defecto, retornamos false
		
		return false;
		
	}
	/*
	public void BuscarEspectaculos(Scanner entrada) {
		String titulo = "";
		CategoriaEspectaculo cat = CategoriaEspectaculo.concierto;
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
				cat = CategoriaEspectaculo.concierto;
			}
			else if(cato == 2) {
				cat = CategoriaEspectaculo.monologo;
			}
			else if(cato == 3) {
				cat = CategoriaEspectaculo.obraTeatro;
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
	
	*/
	
	/**
	 * Funcion que muestra por pantalla la informacion de todos los espectaculos almacenados en el gestor
	 */

	public void imprimirEspectaculos() {
		// Recorremos la lista de espectaculos
		for(int i=0; i < this.listaEspectaculos.size(); i++) {
			System.out.println("Espectaculo " + this.listaEspectaculos.get(i).getIdentificadorEspectaculo()); // Imprimimos el identificador del espectaculo
			System.out.println("\tTitulo del espectaculo: " + this.listaEspectaculos.get(i).getTituloEspectaculo()); // Mostramos el titulo del espectaculo
			System.out.println("\tDescripcion del espectaculo: " + this.listaEspectaculos.get(i).getDescripcionEspectaculo());// Mostramos la descripcion del espectaculo
			System.out.println("\tCategoria del espectaculo: " + this.listaEspectaculos.get(i).getCategoriaEspectaculo());// Mostramos la categoria del espectaculo
			System.out.println("\tTipo del espectaculo: " + this.listaEspectaculos.get(i).getTipoEspectaculo());// Mostramos el tipo del espectaculo
			System.out.println("\tAforo de localidades del espectaculo: " + this.listaEspectaculos.get(i).getAforoLocalidadesEspectaculo());// Mostramos el aforo de localidades del espectaculo
			System.out.println("\tNumero de ventas del espectaculo: " + this.listaEspectaculos.get(i).getVentasEspectaculo());// Mostramos el numero de ventas del espectaculo
			// Caso 1: el espectaculo es de tipo puntual
			if(this.listaEspectaculos.get(i).getTipoEspectaculo().equals("puntual")) {
				System.out.println("\tFecha de la sesion: " + this.listaEspectaculos.get(i).getSesionEspectaculo().getFechaCompletaSesion());// Imprimimos la fecha completa de la sesion
				System.out.println("\tHora completa de la sesion: " + this.listaEspectaculos.get(i).getSesionEspectaculo().getHoraCompleta());// Imprimimos la hora completa de la sesion
				
			}
			// Caso 2: El espectaculo es de tipo multiple
			else if(this.listaEspectaculos.get(i).getTipoEspectaculo().equals("multiple")) {
				// Recorremos las listas de sesiones
				for(int j=0; j < this.listaEspectaculos.get(i).getSesionesEspectaculo().size(); j++) {
					System.out.println("\tSesion " + (j + 1)); // Imprimimos el numero de la sesion
					System.out.println("\tDia de la semana: " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getDiaSemana()); // Imprimimos el dia de la semana de la sesion
					System.out.println("\tHora de la semana: " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getHoraCompleta()); // Imprimimos la hora completa de la sesion
				}
			}
			// Caso 3: El espectaculo es de temporada
			else if(this.listaEspectaculos.get(i).getTipoEspectaculo().equals("temporada")) {
				for(int j=0; j < this.listaEspectaculos.get(i).getSesionesEspectaculo().size(); j++) {
					if(j == 0) {
						System.out.println("\tSesion " + (j + 1)); // Imprimimos el numero de la sesion
						System.out.println("\tDia de la semana: " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getDiaSemana()); // Imprimimos el dia de la semana de la sesion
						System.out.println("\tHora de inicio de la sesion de la semana: " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getHoraCompleta()); // Imprimimos la hora de inicio completa de la sesion
					}
					else {
						System.out.println("\tHora de finalizacion de la sesion de la semana: " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getHoraCompleta()); // Imprimimos la hora de finalizacion completa de la sesion
					}
				}
			}
		}
		
	}

	/**
	 * Funcion que comprueba si un espectaculo esta registrado en la base de datos
	 * @param tituloEspectaculo Titulo del espectaculo
	 * @return true si el espectaculo esta registrado en la base de datos; false en caso contrario
	 */
	public boolean comprobarExistenciaTituloEspectaculo(String tituloEspectaculo) {
		for(int i=0; i  < this.listaEspectaculos.size(); i++) { // Recorremos la lista de espectaculos
			if(this.listaEspectaculos.get(i).getTituloEspectaculo().equals(tituloEspectaculo)) { // Titulo del espectaculo encontrado
				return true; // Retornamos true pues hemos encontrado el titulo del espectaculo
			}
		}
		return false; // Por defecto, retorna false
	}
}
