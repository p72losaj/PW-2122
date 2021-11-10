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
	
	public void obtenerEspectaculosRegistrados(Properties prop, Properties sql) {
		// Creamos un espectaculo vacio de tipo DAO
		EspectaculoDAO espectaculoDAO = new EspectaculoDAO();
		SesionDAO sesionDAO = new SesionDAO();
		// Obtencion de los datos de la tabla Espectaculo
		this.setListaEspectaculos(espectaculoDAO.obtencionEspectaculos(prop,sql));
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
	 * @param ventasEspectaculo
	 * @param anoPuntual
	 * @param mesPuntual
	 * @param diaPuntual
	 * @param horaPuntual
	 * @param minutosPuntual
	 * @return
	 */

	public String darAltaEspectaculo(Properties prop, Properties sql,String tituloEspectaculo, String descripcionEspectaculo,
			CategoriaEspectaculo categoriaEspectaculo, String tipoEspectaculo, int aforoLocalidades,
			int ventasEspectaculo, int anoPuntual, int mesPuntual, int diaPuntual, int horaPuntual,
			int minutosPuntual) {
		String cadena = null;
		EspectaculoDTO espectaculo = new EspectaculoDTO();
		Boolean existenciaTituloEspectaculo = comprobarExistenciaTituloEspectaculo(tituloEspectaculo); // Obtenemos la existencia del titulo del espectaculo
		int comprobacionFechas;
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
				/*
				 * COMPROBACION DE LA VALIDEZ DE LAS FECHAS
				 */
				comprobacionFechas = comprobarValidezFechasMesDia(mesPuntual, diaPuntual);
				/*
				 * MES ES FEBERO, NUMERO DE DIAS SUPERIOR A 28
				 */
				if(comprobacionFechas == -1) {
					cadena = "El mes de febero tiene como maximo 28 dias. No se han registrado los datos del espectaculo";
					return cadena;
				}
				/*
				 * MES DE MAXIMO 30 DIAS, NUMERO DE DIAS ES SUPERIOR A 30
				 */
				else if(comprobacionFechas == -2) {
					cadena = "El mes tiene como maximo 30 dias. No se han registrado los datos del espectaculo";
					return cadena;
				}
				/*
				 * MES TIENE COMO MAXIMO 31 DIAS
				 */
				else if(comprobacionFechas == 1) {
					cadena = "El mes indicado tiene como maximo 31 dias. No se han registrado los datos del espectaculo";
					return cadena;
				}
				/*
				 * FECHA ES VALIDA
				 */
				else {
					/*
					 * Creamos el espectaculo de tipo puntual
					 */
					espectaculo = factoria.crearEspectaculoPuntual(tituloEspectaculo,descripcionEspectaculo,categoriaEspectaculo,tipoEspectaculo,aforoLocalidades,ventasEspectaculo,anoPuntual,mesPuntual,diaPuntual,horaPuntual,minutosPuntual); // Creacion del espectaculo puntual
				}
				
			}
			/*
			 * ESPECTACULO ES DE TIPO MULTIPLE
			 */
			/*
			 * ESPECTACULO ES DE TIPO TEMPORADA
			 */
			
			/*
			 * REGISTRO DE LOS DATOS EN LA TABLA DE ESPECTACULO
			 */
			EspectaculoDAO espectaculoDAO = new EspectaculoDAO();
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
				 * REGISTRO DE LOS DATOS DE SESION DEL ESPECTACULO
				 */
				SesionDAO sesion = new SesionDAO();
				status = sesion.anadirSesionEspectaculo(prop, sql, espectaculo); // Anadimos los datos de la sesion
				/*
				 * DATOS DE LA SESION NO REGISTRADOS
				 */
				if(status == 0) {
					cadena = "Se ha producido un error al registrar los datos de sesion del espectaculo";
					espectaculoDAO.eliminacionEspectaculo(prop,sql,espectaculo.getIdentificadorEspectaculo());
				}
				/*
				 * DATOS DE SESION REGISTRADOS
				 */
				else {
					this.listaEspectaculos.add(espectaculo); // Anadimos el espectaculo al gestor de espectaculos
					cadena = "Espectaculo registrado en la base de datos";
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

	
}
