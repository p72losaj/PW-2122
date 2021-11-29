package es.uco.pw.negocio.espectaculo;

import java.util.ArrayList;
import java.util.Properties;

import es.uco.pw.datos.dao.espectaculo.EspectaculoDAO;
import es.uco.pw.datos.dao.sesion.SesionDAO;

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
	


	/*
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
	
	*/
	
	public Boolean cancelarEspectaculo( Properties prop, Properties sql,String tituloEliminar) {
		EspectaculoDAO esp = new EspectaculoDAO();
		// Recorremos la lista de espectaculo
		
		for(int i=0; i<this.listaEspectaculos.size(); i++) {
		
			// Titulo del espectaculo encontrado
			
			if(this.listaEspectaculos.get(i).getTituloEspectaculo().equals(tituloEliminar)) {
				// Eliminamos el espectaculo de la lista de espectaculos
				esp.eliminacionEspectaculo(prop, sql,  this.listaEspectaculos.get(i).getIdentificadorEspectaculo());
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
			// Caso 1: el espectaculo es de tipo puntual
			if(this.listaEspectaculos.get(i).getTipoEspectaculo().equals("puntual")) {
				System.out.println("\tSesion: " + this.listaEspectaculos.get(i).getSesionEspectaculo().getIdentificadorSesion());
				System.out.println("\tFecha de la sesion: " + this.listaEspectaculos.get(i).getSesionEspectaculo().getFechaCompletaSesion());// Imprimimos la fecha completa de la sesion
				System.out.println("\tHora completa de la sesion: " + this.listaEspectaculos.get(i).getSesionEspectaculo().getHoraCompleta());// Imprimimos la hora completa de la sesion
				System.out.println("\tNumero de ventas de localidades: " + this.listaEspectaculos.get(i).getSesionEspectaculo().getVentasSesion() );// Mostramos el numero de ventas del espectaculo
			}
			// Caso 2: El espectaculo es de tipo multiple
			else if(this.listaEspectaculos.get(i).getTipoEspectaculo().equals("multiple")) {
				// Recorremos las listas de sesiones
				for(int j=0; j < this.listaEspectaculos.get(i).getSesionesEspectaculo().size(); j++) {
					System.out.println("\tSesion " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getIdentificadorSesion()); // Imprimimos el numero de la sesion
					System.out.println("\tDia de la semana: " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getDiaSemana()); // Imprimimos el dia de la semana de la sesion
					System.out.println("\tHora de la semana: " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getHoraCompleta()); // Imprimimos la hora completa de la sesion
					System.out.println("\tNumero de ventas de localidades: " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getVentasSesion()); // Imprimimos el numero de ventas de localidades de la sesion
				}
			}
			// Caso 3: El espectaculo es de temporada
			else if(this.listaEspectaculos.get(i).getTipoEspectaculo().equals("temporada")) {
				for(int j=0; j < this.listaEspectaculos.get(i).getSesionesEspectaculo().size(); j++) {
					if(j == 0) {
						System.out.println("\tSesion " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getIdentificadorSesion()); // Imprimimos el numero de la sesion
						System.out.println("\tDia de la semana: " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getDiaSemana()); // Imprimimos el dia de la semana de la sesion
						System.out.println("\tHora de inicio de la sesion de la semana: " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getHoraCompleta()); // Imprimimos la hora de inicio completa de la sesion
						System.out.println("\tNumero de ventas de localidades: " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getVentasSesion()); // Imprimimos el numero de ventas de localidades de la sesion
					}
					else {
						System.out.println("\tHora de finalizacion de la sesion de la semana: " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getHoraCompleta()); // Imprimimos la hora de finalizacion completa de la sesion
					}
				}
			}
		}
		
	}

	/**
	 * Funcion que muestra los identificadores, titulos y sesiones de los espectaculos
	 */
	public void imprimirSesionesEspectaculos() {
		// Recorremos la lista de espectaculos
		for(int i=0; i < this.listaEspectaculos.size(); i++) {
			System.out.println("Espectaculo " + this.listaEspectaculos.get(i).getIdentificadorEspectaculo()); // Imprimimos el identificador del espectaculo
			System.out.println("\tTitulo del espectaculo: " + this.listaEspectaculos.get(i).getTituloEspectaculo()); // Mostramos el titulo del espectaculo
			// Caso 1: el espectaculo es de tipo puntual
			if(this.listaEspectaculos.get(i).getTipoEspectaculo().equals("puntual")) {
				System.out.println("\tSesion: " + this.listaEspectaculos.get(i).getSesionEspectaculo().getIdentificadorSesion());
			}
			// Caso 2: El espectaculo es de tipo multiple
			else if(this.listaEspectaculos.get(i).getTipoEspectaculo().equals("multiple")) {
				// Recorremos las listas de sesiones
				for(int j=0; j < this.listaEspectaculos.get(i).getSesionesEspectaculo().size(); j++) {
					System.out.println("\tSesion " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getIdentificadorSesion()); // Imprimimos el numero de la sesion
				}
			}
			// Caso 3: El espectaculo es de temporada
			else if(this.listaEspectaculos.get(i).getTipoEspectaculo().equals("temporada")) {
				for(int j=0; j < this.listaEspectaculos.get(i).getSesionesEspectaculo().size(); j++) {
					if(j == 0) {
						System.out.println("\tSesion " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getIdentificadorSesion()); // Imprimimos el numero de la sesion
					}
				}
			}	
		}
	}
	
	
	/**
	 * Funcion que muestra los identificadores, titulos y sesiones de los espectaculos
	 */
	public void imprimirVentasSesionesEspectaculos(int ide, int ids) {
		// Recorremos la lista de espectaculos
		for(int i=0; i < this.listaEspectaculos.size(); i++) {
			if(ide == this.listaEspectaculos.get(i).getIdentificadorEspectaculo()) {
			System.out.println("\tTitulo del espectaculo: " + this.listaEspectaculos.get(i).getTituloEspectaculo()); // Mostramos el titulo del espectaculo
			// Caso 1: el espectaculo es de tipo puntual
			if(this.listaEspectaculos.get(i).getTipoEspectaculo().equals("puntual")) {
				if(ids == this.listaEspectaculos.get(i).getSesionEspectaculo().getIdentificadorSesion()) {
				System.out.println("\tVentas de la sesion " + ids + ": " + this.listaEspectaculos.get(i).getSesionEspectaculo().getVentasSesion());
			}}
			// Caso 2: El espectaculo es de tipo multiple
			else if(this.listaEspectaculos.get(i).getTipoEspectaculo().equals("multiple")) {
				// Recorremos las listas de sesiones
				for(int j=0; j < this.listaEspectaculos.get(i).getSesionesEspectaculo().size(); j++) {
					if(ids == this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getIdentificadorSesion()) {
					System.out.println("\tVentas de la sesion " + ids + ": " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getVentasSesion()); // Imprimimos el numero de la sesion
				}}
			}
			// Caso 3: El espectaculo es de temporada
			else if(this.listaEspectaculos.get(i).getTipoEspectaculo().equals("temporada")) {
				for(int j=0; j < this.listaEspectaculos.get(i).getSesionesEspectaculo().size(); j++) {
					if(j == 0) {
						
						if(ids == this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getIdentificadorSesion()) {
						System.out.println("\tVentas de la sesion " + ids + ": " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getVentasSesion()); // Imprimimos el numero de la sesion
					}}
				}
			}	}
		}
	}
	
	
	
	
	
	
	
	
	/**
	 * Funcion que muestra por pantalla la informacion de todos los espectaculos de una categoria
	 */

	public void imprimirEspectaculosCat(CategoriaEspectaculo cat) {
		// Recorremos la lista de espectaculos
		for(int i=0; i < this.listaEspectaculos.size(); i++) {
			if(this.listaEspectaculos.get(i).getCategoriaEspectaculo().equals(cat)) {
			System.out.println("Espectaculo " + this.listaEspectaculos.get(i).getIdentificadorEspectaculo()); // Imprimimos el identificador del espectaculo
			System.out.println("\tTitulo del espectaculo: " + this.listaEspectaculos.get(i).getTituloEspectaculo()); // Mostramos el titulo del espectaculo
			System.out.println("\tDescripcion del espectaculo: " + this.listaEspectaculos.get(i).getDescripcionEspectaculo());// Mostramos la descripcion del espectaculo
			System.out.println("\tCategoria del espectaculo: " + this.listaEspectaculos.get(i).getCategoriaEspectaculo());// Mostramos la categoria del espectaculo
			System.out.println("\tTipo del espectaculo: " + this.listaEspectaculos.get(i).getTipoEspectaculo());// Mostramos el tipo del espectaculo
			System.out.println("\tAforo de localidades del espectaculo: " + this.listaEspectaculos.get(i).getAforoLocalidadesEspectaculo());// Mostramos el aforo de localidades del espectaculo
			// Caso 1: el espectaculo es de tipo puntual
			if(this.listaEspectaculos.get(i).getTipoEspectaculo().equals("puntual")) {
				System.out.println("\tSesion: " + this.listaEspectaculos.get(i).getSesionEspectaculo().getIdentificadorSesion());
				System.out.println("\tFecha de la sesion: " + this.listaEspectaculos.get(i).getSesionEspectaculo().getFechaCompletaSesion());// Imprimimos la fecha completa de la sesion
				System.out.println("\tHora completa de la sesion: " + this.listaEspectaculos.get(i).getSesionEspectaculo().getHoraCompleta());// Imprimimos la hora completa de la sesion
				System.out.println("\tNumero de ventas de localidades: " + this.listaEspectaculos.get(i).getSesionEspectaculo().getVentasSesion() );// Mostramos el numero de ventas del espectaculo
			}
			// Caso 2: El espectaculo es de tipo multiple
			else if(this.listaEspectaculos.get(i).getTipoEspectaculo().equals("multiple")) {
				// Recorremos las listas de sesiones
				for(int j=0; j < this.listaEspectaculos.get(i).getSesionesEspectaculo().size(); j++) {
					System.out.println("\tSesion " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getIdentificadorSesion()); // Imprimimos el numero de la sesion
					System.out.println("\tDia de la semana: " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getDiaSemana()); // Imprimimos el dia de la semana de la sesion
					System.out.println("\tHora de la semana: " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getHoraCompleta()); // Imprimimos la hora completa de la sesion
					System.out.println("\tNumero de ventas de localidades: " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getVentasSesion()); // Imprimimos el numero de ventas de localidades de la sesion
				}
			}
			// Caso 3: El espectaculo es de temporada
			else if(this.listaEspectaculos.get(i).getTipoEspectaculo().equals("temporada")) {
				for(int j=0; j < this.listaEspectaculos.get(i).getSesionesEspectaculo().size(); j++) {
					if(j == 0) {
						System.out.println("\tSesion " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getIdentificadorSesion()); // Imprimimos el numero de la sesion
						System.out.println("\tDia de la semana: " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getDiaSemana()); // Imprimimos el dia de la semana de la sesion
						System.out.println("\tHora de inicio de la sesion de la semana: " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getHoraCompleta()); // Imprimimos la hora de inicio completa de la sesion
						System.out.println("\tNumero de ventas de localidades: " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getVentasSesion()); // Imprimimos el numero de ventas de localidades de la sesion
					}
					else {
						System.out.println("\tHora de finalizacion de la sesion de la semana: " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getHoraCompleta()); // Imprimimos la hora de finalizacion completa de la sesion
					}
				}
			}
			
			}
		}
		
	}
	
	/**
	 * Funcion que muestra por pantalla la informacion de todos los espectaculos a partir de una fecha
	 */

	public void imprimirEspectaculosSesion(SesionEspectaculoDTO cat) {
		// Recorremos la lista de espectaculos
		for(int i=0; i < this.listaEspectaculos.size(); i++) {
			boolean fs = false;
			if(this.listaEspectaculos.get(i).getTipoEspectaculo().equals("puntual")) {
				if (cat.getFechaCompletaSesion().after( this.listaEspectaculos.get(i).getSesionEspectaculo().getFechaCompletaSesion())) {
					fs = true;
				}
				else {
					fs = false;
				}
				
				
				
				
				
			} else {
				
				fs = true;
			}
			
			
			
			
			
			if(fs == true) {
			System.out.println("Espectaculo " + this.listaEspectaculos.get(i).getIdentificadorEspectaculo()); // Imprimimos el identificador del espectaculo
			System.out.println("\tTitulo del espectaculo: " + this.listaEspectaculos.get(i).getTituloEspectaculo()); // Mostramos el titulo del espectaculo
			System.out.println("\tDescripcion del espectaculo: " + this.listaEspectaculos.get(i).getDescripcionEspectaculo());// Mostramos la descripcion del espectaculo
			System.out.println("\tCategoria del espectaculo: " + this.listaEspectaculos.get(i).getCategoriaEspectaculo());// Mostramos la categoria del espectaculo
			System.out.println("\tTipo del espectaculo: " + this.listaEspectaculos.get(i).getTipoEspectaculo());// Mostramos el tipo del espectaculo
			System.out.println("\tAforo de localidades del espectaculo: " + this.listaEspectaculos.get(i).getAforoLocalidadesEspectaculo());// Mostramos el aforo de localidades del espectaculo
			// Caso 1: el espectaculo es de tipo puntual
			if(this.listaEspectaculos.get(i).getTipoEspectaculo().equals("puntual")) {
				System.out.println("\tSesion: " + this.listaEspectaculos.get(i).getSesionEspectaculo().getIdentificadorSesion());
				System.out.println("\tFecha de la sesion: " + this.listaEspectaculos.get(i).getSesionEspectaculo().getFechaCompletaSesion());// Imprimimos la fecha completa de la sesion
				System.out.println("\tHora completa de la sesion: " + this.listaEspectaculos.get(i).getSesionEspectaculo().getHoraCompleta());// Imprimimos la hora completa de la sesion
				System.out.println("\tNumero de ventas de localidades: " + this.listaEspectaculos.get(i).getSesionEspectaculo().getVentasSesion() );// Mostramos el numero de ventas del espectaculo
			}
			// Caso 2: El espectaculo es de tipo multiple
			else if(this.listaEspectaculos.get(i).getTipoEspectaculo().equals("multiple")) {
				// Recorremos las listas de sesiones
				for(int j=0; j < this.listaEspectaculos.get(i).getSesionesEspectaculo().size(); j++) {
					System.out.println("\tSesion " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getIdentificadorSesion()); // Imprimimos el numero de la sesion
					System.out.println("\tDia de la semana: " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getDiaSemana()); // Imprimimos el dia de la semana de la sesion
					System.out.println("\tHora de la semana: " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getHoraCompleta()); // Imprimimos la hora completa de la sesion
					System.out.println("\tNumero de ventas de localidades: " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getVentasSesion()); // Imprimimos el numero de ventas de localidades de la sesion
				}
			}
			// Caso 3: El espectaculo es de temporada
			else if(this.listaEspectaculos.get(i).getTipoEspectaculo().equals("temporada")) {
				for(int j=0; j < this.listaEspectaculos.get(i).getSesionesEspectaculo().size(); j++) {
					if(j == 0) {
						System.out.println("\tSesion " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getIdentificadorSesion()); // Imprimimos el numero de la sesion
						System.out.println("\tDia de la semana: " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getDiaSemana()); // Imprimimos el dia de la semana de la sesion
						System.out.println("\tHora de inicio de la sesion de la semana: " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getHoraCompleta()); // Imprimimos la hora de inicio completa de la sesion
						System.out.println("\tNumero de ventas de localidades: " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getVentasSesion()); // Imprimimos el numero de ventas de localidades de la sesion
					}
					else {
						System.out.println("\tHora de finalizacion de la sesion de la semana: " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getHoraCompleta()); // Imprimimos la hora de finalizacion completa de la sesion
					}
				}
			}
			
			}
		}
		
	}
	
	
	
	
	/**
	 * Funcion que muestra por pantalla la informacion de todos los espectaculos con ventas disponibles categoria
	 */

	public void imprimirEspectaculosLoc(CategoriaEspectaculo cat, boolean bf) {
		// Recorremos la lista de espectaculos
		
		for(int i=0; i < this.listaEspectaculos.size(); i++) {
			if(bf == true) {
				if(this.listaEspectaculos.get(i).getAforoLocalidadesEspectaculo() > 0 ) {
					System.out.println("Espectaculo " + this.listaEspectaculos.get(i).getIdentificadorEspectaculo()); // Imprimimos el identificador del espectaculo
					System.out.println("\tTitulo del espectaculo: " + this.listaEspectaculos.get(i).getTituloEspectaculo()); // Mostramos el titulo del espectaculo
					System.out.println("\tDescripcion del espectaculo: " + this.listaEspectaculos.get(i).getDescripcionEspectaculo());// Mostramos la descripcion del espectaculo
					System.out.println("\tCategoria del espectaculo: " + this.listaEspectaculos.get(i).getCategoriaEspectaculo());// Mostramos la categoria del espectaculo
					System.out.println("\tTipo del espectaculo: " + this.listaEspectaculos.get(i).getTipoEspectaculo());// Mostramos el tipo del espectaculo
					System.out.println("\tAforo de localidades del espectaculo: " + this.listaEspectaculos.get(i).getAforoLocalidadesEspectaculo());// Mostramos el aforo de localidades del espectaculo
					// Caso 1: el espectaculo es de tipo puntual
					if(this.listaEspectaculos.get(i).getTipoEspectaculo().equals("puntual")) {
						System.out.println("\tSesion: " + this.listaEspectaculos.get(i).getSesionEspectaculo().getIdentificadorSesion());
						System.out.println("\tFecha de la sesion: " + this.listaEspectaculos.get(i).getSesionEspectaculo().getFechaCompletaSesion());// Imprimimos la fecha completa de la sesion
						System.out.println("\tHora completa de la sesion: " + this.listaEspectaculos.get(i).getSesionEspectaculo().getHoraCompleta());// Imprimimos la hora completa de la sesion
						System.out.println("\tNumero de ventas de localidades: " + this.listaEspectaculos.get(i).getSesionEspectaculo().getVentasSesion() );// Mostramos el numero de ventas del espectaculo
					}
					// Caso 2: El espectaculo es de tipo multiple
					else if(this.listaEspectaculos.get(i).getTipoEspectaculo().equals("multiple")) {
						// Recorremos las listas de sesiones
						for(int j=0; j < this.listaEspectaculos.get(i).getSesionesEspectaculo().size(); j++) {
							System.out.println("\tSesion " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getIdentificadorSesion()); // Imprimimos el numero de la sesion
							System.out.println("\tDia de la semana: " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getDiaSemana()); // Imprimimos el dia de la semana de la sesion
							System.out.println("\tHora de la semana: " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getHoraCompleta()); // Imprimimos la hora completa de la sesion
							System.out.println("\tNumero de ventas de localidades: " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getVentasSesion()); // Imprimimos el numero de ventas de localidades de la sesion
						}
					}
					// Caso 3: El espectaculo es de temporada
					else if(this.listaEspectaculos.get(i).getTipoEspectaculo().equals("temporada")) {
						for(int j=0; j < this.listaEspectaculos.get(i).getSesionesEspectaculo().size(); j++) {
							if(j == 0) {
								System.out.println("\tSesion " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getIdentificadorSesion()); // Imprimimos el numero de la sesion
								System.out.println("\tDia de la semana: " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getDiaSemana()); // Imprimimos el dia de la semana de la sesion
								System.out.println("\tHora de inicio de la sesion de la semana: " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getHoraCompleta()); // Imprimimos la hora de inicio completa de la sesion
								System.out.println("\tNumero de ventas de localidades: " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getVentasSesion()); // Imprimimos el numero de ventas de localidades de la sesion
							}
							else {
								System.out.println("\tHora de finalizacion de la sesion de la semana: " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getHoraCompleta()); // Imprimimos la hora de finalizacion completa de la sesion
							}
						}
					}
					
					
					
					
				}
			}
	
			else {
				if(this.listaEspectaculos.get(i).getCategoriaEspectaculo().equals(cat)) {
			System.out.println("Espectaculo " + this.listaEspectaculos.get(i).getIdentificadorEspectaculo()); // Imprimimos el identificador del espectaculo
			System.out.println("\tTitulo del espectaculo: " + this.listaEspectaculos.get(i).getTituloEspectaculo()); // Mostramos el titulo del espectaculo
			System.out.println("\tDescripcion del espectaculo: " + this.listaEspectaculos.get(i).getDescripcionEspectaculo());// Mostramos la descripcion del espectaculo
			System.out.println("\tCategoria del espectaculo: " + this.listaEspectaculos.get(i).getCategoriaEspectaculo());// Mostramos la categoria del espectaculo
			System.out.println("\tTipo del espectaculo: " + this.listaEspectaculos.get(i).getTipoEspectaculo());// Mostramos el tipo del espectaculo
			System.out.println("\tAforo de localidades del espectaculo: " + this.listaEspectaculos.get(i).getAforoLocalidadesEspectaculo());// Mostramos el aforo de localidades del espectaculo
			// Caso 1: el espectaculo es de tipo puntual
			if(this.listaEspectaculos.get(i).getTipoEspectaculo().equals("puntual")) {
				System.out.println("\tSesion: " + this.listaEspectaculos.get(i).getSesionEspectaculo().getIdentificadorSesion());
				System.out.println("\tFecha de la sesion: " + this.listaEspectaculos.get(i).getSesionEspectaculo().getFechaCompletaSesion());// Imprimimos la fecha completa de la sesion
				System.out.println("\tHora completa de la sesion: " + this.listaEspectaculos.get(i).getSesionEspectaculo().getHoraCompleta());// Imprimimos la hora completa de la sesion
				System.out.println("\tNumero de ventas de localidades: " + this.listaEspectaculos.get(i).getSesionEspectaculo().getVentasSesion() );// Mostramos el numero de ventas del espectaculo
			}
			// Caso 2: El espectaculo es de tipo multiple
			else if(this.listaEspectaculos.get(i).getTipoEspectaculo().equals("multiple")) {
				// Recorremos las listas de sesiones
				for(int j=0; j < this.listaEspectaculos.get(i).getSesionesEspectaculo().size(); j++) {
					System.out.println("\tSesion " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getIdentificadorSesion()); // Imprimimos el numero de la sesion
					System.out.println("\tDia de la semana: " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getDiaSemana()); // Imprimimos el dia de la semana de la sesion
					System.out.println("\tHora de la semana: " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getHoraCompleta()); // Imprimimos la hora completa de la sesion
					System.out.println("\tNumero de ventas de localidades: " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getVentasSesion()); // Imprimimos el numero de ventas de localidades de la sesion
				}
			}
			// Caso 3: El espectaculo es de temporada
			else if(this.listaEspectaculos.get(i).getTipoEspectaculo().equals("temporada")) {
				for(int j=0; j < this.listaEspectaculos.get(i).getSesionesEspectaculo().size(); j++) {
					if(j == 0) {
						System.out.println("\tSesion " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getIdentificadorSesion()); // Imprimimos el numero de la sesion
						System.out.println("\tDia de la semana: " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getDiaSemana()); // Imprimimos el dia de la semana de la sesion
						System.out.println("\tHora de inicio de la sesion de la semana: " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getHoraCompleta()); // Imprimimos la hora de inicio completa de la sesion
						System.out.println("\tNumero de ventas de localidades: " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getVentasSesion()); // Imprimimos el numero de ventas de localidades de la sesion
					}
					else {
						System.out.println("\tHora de finalizacion de la sesion de la semana: " + this.listaEspectaculos.get(i).getSesionesEspectaculo().get(j).getHoraCompleta()); // Imprimimos la hora de finalizacion completa de la sesion
					}
				}
			}
			
			}}
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
	
	/**
	 * Funcion que obtiene los datos de los espectaculos registrados en la base de datos
	 * @param prop Fichero de configuracion
	 * @param sql fichero de sentencias sql
	 */
	
	public void obtenerEspectaculosRegistrados(Properties prop, Properties sql) {
		EspectaculoDAO espectaculoDAO = new EspectaculoDAO();
		SesionDAO sesionDAO = new SesionDAO();
		this.setListaEspectaculos(espectaculoDAO.obtencionEspectaculos(prop,sql)); // Obtencion de los datos de la tabla Espectaculo
		// Obtencion de los datos de sesion del espectaculo
		for(int i=0; i < this.getEspectaculos().size(); i++) {
			String titulo = this.getEspectaculos().get(i).getTituloEspectaculo();
			// Sesion puntual
			if(this.getEspectaculos().get(i).getTipoEspectaculo().equals("puntual")) {
				this.getEspectaculos().get(i).setSesionEspectaculo(sesionDAO.obtencionSesionEspectaculoPuntual(prop, sql, titulo)); // Obtenemos la sesion del espectaculo puntual
			}
			// Sesion multiple
			else if(this.getEspectaculos().get(i).getTipoEspectaculo().equals("temporada")) {
				this.getEspectaculos().get(i).setSesionesEspectaculo(sesionDAO.obtencionSesionEspectaculoTemporada(prop, sql, titulo)); // Obtenemos la lista de sesiones de los espectaculos por temporada
			}
			// Sesion temporada
			else if(this.getEspectaculos().get(i).getTipoEspectaculo().equals("multiple")) {
				this.getEspectaculos().get(i).setSesionesEspectaculo(sesionDAO.obtencionSesionEspectaculoMultiple(prop, sql, titulo));
			}
		}
	}
	
	/**
	 * Funcion que registra los datos de un espectaculo
	 * @param tituloEspectaculo Titulo del espectaculo
	 * @param descripcionEspectaculo Descripcion del espectaculo
	 * @param categoriaEspectaculo Categoria del espectaculo
	 * @param tipoEspectaculo Tipo del espectaculo
	 * @param aforoLocalidades Aforo de localidades del espectaculo
	 * @param ventasEspectaculo Numero de ventas de la sesion del espectaculo
	 * @param anoPuntual Ano de la sesion de un espectaculo puntual
	 * @param mesPuntual Mes de la sesion de un espectaculo puntual
	 * @param diaPuntual Dia de la sesion de un espectaculo puntual
	 * @param horaPuntual Hora de la sesion de un espectaculo puntual
	 * @param minutosPuntual Minutos de la sesion de un espectaculo puntual
	 * @param horaMultiple1 Hora de la primera sesion del espectaculo multiple
	 * @param minutosMultiple1 Minutos de la primera sesion del espectaculo
	 * @param diaSemanaMultiple1 Primer dia de la semana del espectaculo multiple
	 * @param diaSemanaMultiple2 Dia de la semana de la segunda sesion del espectaculo multiple
	 * @param horaMultiple2 Hora de la segunda sesion del espectaculo multiple
	 * @param minutosMultiple2 Minutos de la segunda sesion del espectaculo multiple
	 * @param minutosTemporada2 Minutos de la segunda sesion del espectaculo de temporada
	 * @param horaTemporada2 Hora de la segunda sesion del espectaculo de temporada
	 * @param diaSemanaTemporada Dia de la semana de ambas sesiones del espectaculo de temporada
	 * @param minutosTemporada1 Minutos de la primera sesion del espectaculo de temporada
	 * @param horaTemporada1 Hora de la primera sesion del espectaculo de temporada
	 * @return Cadena con el estado del registro del espectaculo
	 */

	public String darAltaEspectaculo(Properties prop, Properties sql,String tituloEspectaculo, 
			String descripcionEspectaculo,CategoriaEspectaculo categoriaEspectaculo, 
			String tipoEspectaculo, int aforoLocalidades,
			int ventasEspectaculo1,
			int anoPuntual, int mesPuntual, int diaPuntual, int horaPuntual, int minutosPuntual, 
			int horaMultiple1, int minutosMultiple1, String diaSemanaMultiple1, String diaSemanaMultiple2, 
			int horaMultiple2, int minutosMultiple2, 
			int horaTemporada1, int minutosTemporada1, String diaSemanaTemporada, 
			int horaTemporada2, int minutosTemporada2,int ventasEspectaculo2) 
	{
		String cadena = "Se ha producido un error al dar de alta a los datos del usuario";
		EspectaculoDTO espectaculo = new EspectaculoDTO();
		Boolean existenciaTituloEspectaculo = comprobarExistenciaTituloEspectaculo(tituloEspectaculo); // Obtenemos la existencia del titulo del espectaculo
		EspectaculoDAO espectaculoDAO = new EspectaculoDAO();
		FactoriaEspectaculos factoria = null;
		/*
		 * TITULO DEL ESPECTACULO NO ES UNICO
		 */
		if(existenciaTituloEspectaculo == true) {cadena = "Titulo del espectaculo ya existente. No se pueden registrar los datos del espectaculo";}
		/*
		 * TITULO DEL ESPECTACULO NO EXISTENTE
		 */
		else {
			/*
			 * ESPECTACULO ES DE TIPO PUNTUAL
			 */
			
			if(tipoEspectaculo.equals("puntual")) {
				int comprobacionFechas = comprobarValidezFechasMesDia(mesPuntual, diaPuntual);
				/*
				 * MES ES FEBERO, NUMERO DE DIAS SUPERIOR A 28
				 */
				if(comprobacionFechas == -1) {
					cadena = "Error.El mes de febero tiene como maximo 28 dias.";
					return cadena;
				}
				/*
				 * MES DE MAXIMO 30 DIAS, NUMERO DE DIAS ES SUPERIOR A 30
				 */
				else if(comprobacionFechas == -2) {
					cadena = "Error. El mes indicado tiene como maximo 30 dias.";
					return cadena;
				}
				/*
				 * MES TIENE COMO MAXIMO 31 DIAS
				 */
				else if(comprobacionFechas == 1) {
					cadena = "Error. El mes indicado tiene como maximo 31 dias.";
					return cadena;
				}
				/*
				 * NUMERO DE VENTAS DEL ESPECTACULO ES SUPERIOR AL NUMERO DE LOCALIDADES
				 */
				if(ventasEspectaculo1 > aforoLocalidades) {
					cadena = "Error. El numero de ventas de localidades de la sesion es superior al numero de localidades del espectaculo";
					return cadena;
				}
				EspectaculoPuntualDTO puntual = FactoriaEspectaculos.crearEspectaculoPuntual(tituloEspectaculo,descripcionEspectaculo,categoriaEspectaculo,tipoEspectaculo,aforoLocalidades,ventasEspectaculo1,anoPuntual,mesPuntual,diaPuntual,horaPuntual,minutosPuntual);
				espectaculo.setSesionEspectaculo(puntual.getSesionEspectaculo());
				espectaculo.setAforoLocalidadesEspectaculo(puntual.getAforoLocalidadesEspectaculo());
				espectaculo.setTipoEspectaculo(puntual.getTipoEspectaculo());
				espectaculo.setCategoriaEspectaculo(puntual.getCategoriaEspectaculo());
				espectaculo.setDescripcionEspectaculo(puntual.getDescripcionEspectaculo());
				espectaculo.setTituloEspectaculo(puntual.getTituloEspectaculo());
			}
			/*
			 * ESPECTACULO ES DE TIPO MULTIPLE
			 */
			if(tipoEspectaculo.equals("multiple")){
				/*
				 * COMPROBAMOS SI LAS SESIONES SON EL MISMO DIA
				 */
				if( (diaSemanaMultiple1.equals(diaSemanaMultiple2) ) && (horaMultiple1 == horaMultiple2) && (minutosMultiple1 == minutosMultiple2)) {
					cadena = "Ambas sesiones tienen los mismos datos";
					return cadena;
				}
				/*
				 * COMPROBAMOS SI LAS VENTAS DE LAS SESIONES SON CORRECTAS
				 */
				if( (ventasEspectaculo1+ventasEspectaculo2) > aforoLocalidades) {
					cadena = "Error. El numero de ventas de localidades de las sesiones son superiores al numero de localidades del espectaculo";
					return cadena;
				}
				// DISMINUIMOS EL AFORO DE LOCALIDADES
				aforoLocalidades = ventasEspectaculo1+ventasEspectaculo2;
				// Creamos el espectaculo multiple
				EspectaculoMultipleDTO multiple = FactoriaEspectaculos.crearEspectaculoMultiple(tituloEspectaculo,descripcionEspectaculo,categoriaEspectaculo,tipoEspectaculo,aforoLocalidades,ventasEspectaculo1,horaMultiple1,minutosMultiple1,diaSemanaMultiple1,diaSemanaMultiple2,horaMultiple2, minutosMultiple2,ventasEspectaculo2);
				espectaculo.setSesionesEspectaculo(multiple.getSesionesEspectaculo());
				espectaculo.setAforoLocalidadesEspectaculo(multiple.getAforoLocalidadesEspectaculo());
				espectaculo.setTipoEspectaculo(multiple.getTipoEspectaculo());
				espectaculo.setTituloEspectaculo(multiple.getTituloEspectaculo());
				espectaculo.setDescripcionEspectaculo(multiple.getDescripcionEspectaculo());
				espectaculo.setCategoriaEspectaculo(multiple.getCategoriaEspectaculo());
			}
			/*
			 * ESPECTACULO ES DE TIPO TEMPORADA
			 */
			else if(tipoEspectaculo.equals("temporada")) {
				/*
				 * CASO DE ERROR: SESIONES A LA MISMA HORA
				 */
				if( (horaTemporada1 == horaTemporada2) && (minutosTemporada1 == minutosTemporada2)) {
					cadena = "Las sesiones del espectaculo de temporada tienen la misma hora";
					return cadena;
				}
				/*
				 * COMPROBAMOS SI LAS VENTAS DE LAS SESIONES SON CORRECTAS
				 */
				if( (ventasEspectaculo1+ventasEspectaculo2) > aforoLocalidades) {
					cadena = "Error. El numero de ventas de localidades de las sesiones son superiores al numero de localidades del espectaculo";
					return cadena;
				}
				// DISMINUIMOS EL AFORO DE LOCALIDADES
				aforoLocalidades = ventasEspectaculo1+ventasEspectaculo2;
				// Creamos el espectaculo
				EspectaculoTemporadaDTO temporada = FactoriaEspectaculos.crearEspectaculoMultiple(tituloEspectaculo, descripcionEspectaculo, categoriaEspectaculo, tipoEspectaculo, aforoLocalidades, ventasEspectaculo1, horaTemporada1, minutosTemporada1, diaSemanaTemporada, horaTemporada2, minutosTemporada2,ventasEspectaculo2);
				espectaculo.setTituloEspectaculo(temporada.getTituloEspectaculo());
				espectaculo.setDescripcionEspectaculo(temporada.getDescripcionEspectaculo());
				espectaculo.setCategoriaEspectaculo(temporada.getCategoriaEspectaculo());
				espectaculo.setTipoEspectaculo(temporada.getTipoEspectaculo());
				espectaculo.setAforoLocalidadesEspectaculo(temporada.getAforoLocalidadesEspectaculo());
				espectaculo.setSesionesEspectaculo(temporada.getSesionesEspectaculo());
			}
			
			/*
			 * REGISTRO DE LOS DATOS EN LA TABLA DE ESPECTACULO
			 */
			int status = espectaculoDAO.insercionEspectaculo(prop, sql, espectaculo);
			/*
			 * ESPECTACULO NO REGISTRADO EN LA BASE DE DATOS
			 */
			if(status == 0) {
				cadena = "Se ha producido un error al registrar los datos comunes del espectaculo";
			}
			/*
			 * ESPECTACULO REGISTRADO EN LA BASE DE DATOS
			 */
			else {
				espectaculo.setIdentificadorEspectaculo(espectaculoDAO.obtencionEspectaculoIdentificador(prop, sql, espectaculo.getTituloEspectaculo()));
				/*
				 * IDENTIFICADOR DEL ESPECTACULO NO OBTENIDO
				 */
				if(espectaculo.getIdentificadorEspectaculo() == 0) {
					cadena = "Se ha producido un error al obtener el identificador del espectaculo";
				}
				/*
				 * IDENTIFICADOR DEL ESPECTACULO OBTENIDO
				 */
				else {
					/*
					 * REGISTRO DE LOS DATOS DE SESION DEL ESPECTACULO
					 */
					SesionDAO sesion = new SesionDAO();
					status = sesion.anadirEventosEspectaculo(prop, sql, espectaculo);
					/*
					 * DATOS DE LA SESION NO REGISTRADOS
					 */
					if(status == 0) {cadena = "Se ha producido un error al registrar los datos de sesion del espectaculo";}
					
					/*
					 * DATOS DE SESION REGISTRADOS
					 */
					else {
						cadena = "Espectaculo registrado correctamente";
					}
					
				}
			}
		}
		return cadena;
	}
	
	/**
	 * Funcion que comprueba si el dia introducido se cumple en el mes indicado
	 * @param mes Mes de la fecha 
	 * @param dia Dia de la fecha
	 * @return Valor de comprobacion de la validez de las fechas
	 */

	public int comprobarValidezFechasMesDia(int mes, int dia){
		
		/*
		 * MES FEBRERO + DIA SUPERIOR A 28
		 */
		
		if(mes == 2 && dia > 28) { return -1; }
		
		/*
		 * MES DE MAXIMO 30 DIAS, NUMERO DE DIAS ES SUPERIOR A 30
		 */
		
		else if( dia > 30 && (mes == 4 || mes == 6 || mes == 9 || mes == 11) ){return -2; }
		
		// Caso 3: El mes introducido tiene maximo 31 dias, pero el usuario ha indicado un numero superior de dias
		
		else if(dia > 31 && (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12)) {return 1; }
		
		// Por defecto, las fechas son correctas
		
		return 0;
		
	}
	
	/**
	 * Funcion que cancela los datos de sesion de un espectaculo
	 * @param sql Fichero de sentencia sql
	 * @param prop Fichero de configuracion
	 * @param identificadorSesion Identificador de la sesion del espectaculo
	 * @param tituloEspectaculo Titulo del espectaculo a eliminar
	 * @return Estado de la cancelacion de la sesion del espectaculo
	 */

	public String cancelarSesionEspectaculo(Properties prop, Properties sql, int identificadorSesion, String tituloEspectaculo) {
		String cadena = "Se ha producido un error un error al cancelar la sesion del espectaculo";
		EspectaculoDTO espectaculoDTO = new EspectaculoDTO();
		SesionDAO sesion = new SesionDAO();
	
		/*
		 * OBTENCION DATOS DEL ESPECTACULO
		 */
		boolean fa = false;  
		for(int i = 0;i < this.listaEspectaculos.size();i++) {
			
			if(this.listaEspectaculos.get(i).getTituloEspectaculo().equals(tituloEspectaculo)){
				fa = true;
				espectaculoDTO = obtencionDatosEspectaculo(tituloEspectaculo);
			}
			
			
		}
		
		if(fa == false) {
			return "El titulo no esta registrado";
		}
		
		
		
		
		
		/*
		 * para i desde 0 hasta gestor.listaEspectaculos.size
		 * 		si !gestor.listaEspectaculos.get(i).getTituloEspectaculo.equals(tituloEspectaculo) entonces
		 * 			cadena = Titulo del espectaculo no registrado en el gestor de espectaculos
		 * 		si_no 
		 * 			espectaculoDTO = obtencionDatosEspectaculo(tituloEspectaculo)
		 * 		fin_si
		 * fin_para
		 */
		/*
		 * COMPROBACION IDENTIFICADOR DE LA SESION DEL ESPECTACULO
		 */
	
		
		boolean fe = false;
		if(espectaculoDTO.getTipoEspectaculo().equals("puntual")) {
			if (espectaculoDTO.getSesionEspectaculo().getIdentificadorSesion() == identificadorSesion) {
				fe = true;
			}
				
		}
		else {
			for(int i = 0; i< espectaculoDTO.getSesionesEspectaculo().size();i++) {
				if (espectaculoDTO.getSesionesEspectaculo().get(i).getIdentificadorSesion() == identificadorSesion) {
					fe = true;
				}
			}
			
		}
		
		/*
		 * si espectaculoDTO.getTipoEspectaculo().equals("puntual") entonces
		 * 		si espectaculoDTO.getSesionEspectaculo().getIdentificador() == identificadorEspectaculo entonces
		 * 			id = 1;
		 * 		fin_si
		 * si_no
		 * 		para i desde 0 hasta espectaculoDTO.getSesionesEspectaculo().size
		 * 			si espectaculoDTO.getSesionesEspectaculo().get(i).getIdentificador() == identificadorEspectaculo entonces
		 * 				id = 1;
		 * 			fin_si
		 * 		fin_para
		 * fin_si
		 */
		
		if(fe == false) {
			cadena = "Sesion no resgistrada";
		}
		else {
			int status = sesion.cancelarSesionEspectaculo(prop,sql,identificadorSesion);
			if(status != 0) {
				cadena = "Error al eliminar la sesion";
			}
			else {
				cadena = "Sesion elimina correctamente";
			}
			
		}
		
		
		/*
		 * si id == 0 entonces
		 * 	cadena = sesion del espectaculo no registrado
		 * si_no
		 * 		int status = sesion.cancelarSesionEspectaculo(espectaculo.getIdentificadorEspectaculo());
		 * 		si status == 0 entonces cadena = Error al eliminar la sesion del espectaculo
		 * 		si_no cadena = Sesion del espectaculo cancelado correctamente
		 * 		fin_si
		 * fin_si
		 * 
		 */
		
		
		
		return cadena;
	}
	
	/**
	 * Funcion que obtiene los datos de un espectaculo
	 * @param tituloEspectaculo Titulo del espectaculo
	 * @return Datos del espectaculo
	 */
	public EspectaculoDTO obtencionDatosEspectaculo(String tituloEspectaculo) {
		EspectaculoDTO espectaculo = new EspectaculoDTO();
		boolean flag = false;
		// Recorremos la lista de espectaculos del gestor
		for(int i=0; i < this.listaEspectaculos.size();i++) {
			// Titulo encontrado
			if(this.listaEspectaculos.get(i).getTituloEspectaculo().equals(tituloEspectaculo)) {
				flag = true;
				espectaculo.setIdentificadorEspectaculo(this.listaEspectaculos.get(i).getIdentificadorEspectaculo());
				espectaculo.setTituloEspectaculo(tituloEspectaculo);
				espectaculo.setDescripcionEspectaculo(this.listaEspectaculos.get(i).getDescripcionEspectaculo());
				espectaculo.setCategoriaEspectaculo(this.listaEspectaculos.get(i).getCategoriaEspectaculo());
				espectaculo.setAforoLocalidadesEspectaculo(this.listaEspectaculos.get(i).getAforoLocalidadesEspectaculo());
				espectaculo.setTipoEspectaculo(this.listaEspectaculos.get(i).getTipoEspectaculo());
				// ESPECTACULO PUNTUAL
				if(espectaculo.getTipoEspectaculo().equals("puntual")) {espectaculo.setSesionEspectaculo(this.listaEspectaculos.get(i).getSesionEspectaculo());}
				// ESPECTACULO MULTIPLE O DE TEMPORADA
				else {espectaculo.setSesionesEspectaculo(this.listaEspectaculos.get(i).getSesionesEspectaculo());}
			}
		}
		if(flag) {
		return espectaculo;}
		else {
			return null;
		}
	}
	
	/**
	 * Funcion que cancela todas las sesiones de un espectaculo
	 * @param prop Fichero de configuracion
	 * @param sql Fichero de sentencias sql
	 * @param tituloEspectaculo Titulo del espectaculo
	 * @return Estado de la cancelacion de las sesiones
	 */

	public String cancelarSesionEspectaculo(Properties prop, Properties sql, String tituloEspectaculo) {
		String cad = "Se ha producido un error al cancelar las sesiones del espectaculo";
		EspectaculoDTO espectaculoDTO = new EspectaculoDTO();
		SesionDAO sesion = new SesionDAO();
		int bf = 0;
		/*
		 * OBTENCION DATOS DEL ESPECTACULO
		 */
			for(int i=0; i < this.listaEspectaculos.size();i++) {
			// Titulo encontrado
			if(this.listaEspectaculos.get(i).getTituloEspectaculo().equals(tituloEspectaculo)) {
				bf = 1;	
				break;
				}
		
			}
		
		if(bf != 1) {
			cad = "Titulo del espectaculo no registrado en el gestor de espectaculos";
			return cad;
			
		}
			
			/*
		 * para i desde 0 hasta gestor.listaEspectaculos.size
		 * 		si !gestor.listaEspectaculos.get(i).getTituloEspectaculo.equals(tituloEspectaculo) entonces
		 * 			cadena = Titulo del espectaculo no registrado en el gestor de espectaculos
		 * 			return caden
		 * fin_para
		 */
		
		
		int status = sesion.cancelarSesionesEspectaculo(prop,sql,tituloEspectaculo);
		
		if(status != 0) {
			cad = "Se han eliminado las sesiones del espectaculo ";
		}
		/*
		 * ELIMINACION DE LAS SESIONES DEL ESPECTACULO
		 * int status = sesion.cancelarSesionesEspectaculo(prop, sql, tituloEspectaculo);
		 * si status != 0 entonces cad = Se han eliminado las sesiones del espectaculo 
		 */
		
		return cad;
	}

	
	public String modificarDatosEspectaculo(Properties prop, Properties sql,int id,String descripcionEspectaculo, int aforoLocalidades) {
		
		EspectaculoDAO espectaculo = new EspectaculoDAO();
		
		
		if(espectaculo.modificarEspectaculos( prop,  sql, id, descripcionEspectaculo,  aforoLocalidades)!=0) {
			return "Espectaculo modificado";
		}
		else {
			return "El espectaculo no se ha podido modificar";
		}
		
		
	}
	
}
