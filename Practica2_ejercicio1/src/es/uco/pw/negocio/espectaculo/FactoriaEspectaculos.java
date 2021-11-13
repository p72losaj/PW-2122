package es.uco.pw.negocio.espectaculo;

/**
 * Factoria de espectaculos
 * @author Jaime Lorenzo Sanchez
 * @version 2.0
 */

public abstract class FactoriaEspectaculos {
	
	/**
	 * Constructor vacio de la factoria de espectaculos
	 */
	
	public FactoriaEspectaculos() {
	}
	
	/**
	 * Funcion que crea un espectaculo de tipo puntual
	 * @param tituloEspectaculo Titulo del espectaculo
	 * @param descripcionEspectaculo Descripcion del espectaculo
	 * @param categoriaEspectaculo Categoria del espectaculo
	 * @param tipoEspectaculo Tipo del espectaculo
	 * @param aforoLocalidades Aforo de localidades del espectaculo
	 * @param ventasEspectaculo Numero de ventas del espectaculo
	 * @param anoPuntual Ano de la sesion
	 * @param mesPuntual Mes de la sesion
	 * @param diaPuntual Dia de la sesion
	 * @param horaPuntual Hora de la sesion
	 * @param minutosPuntual Minutos de la sesion
	 * @return Espectaculo de tipo puntual
	 */
	public static EspectaculoPuntualDTO crearEspectaculoPuntual(String tituloEspectaculo, String descripcionEspectaculo,
			CategoriaEspectaculo categoriaEspectaculo, String tipoEspectaculo, int aforoLocalidades,
			int ventasEspectaculo, int anoPuntual, int mesPuntual, int diaPuntual, int horaPuntual,
			int minutosPuntual) {
			EspectaculoPuntualDTO espectaculo = new EspectaculoPuntualDTO(tituloEspectaculo,descripcionEspectaculo,categoriaEspectaculo,tipoEspectaculo,aforoLocalidades,ventasEspectaculo
					,anoPuntual,mesPuntual,diaPuntual,horaPuntual,minutosPuntual);
		return espectaculo;
	}
	
	/**
	 * Funcion que crea un espectaculo de tipo multiple
	 * @param tituloEspectaculo Titulo del espectaculo
	 * @param descripcionEspectaculo Descripcion del espectaculo
	 * @param categoriaEspectaculo Categoria del espectaculo
	 * @param tipoEspectaculo Tipo del espectaculo
	 * @param aforoLocalidades Aforo de localidades del espectaculo
	 * @param ventasEspectaculo Numero de ventas del espectaculo
	 * @param horaMultiple1 Hora de la primera sesion del espectaculo
	 * @param minutosMultiple1 Minutos de la primera sesion del espectaculo
	 * @param diaSemanaMultiple1 Dia de la semana de la primera sesion del espectaculo
	 * @param diaSemanaMultiple2 Dia de la semana de la segunda sesion del espectaculo
	 * @param horaMultiple2 Hora de la segunda sesion del espectaculo
	 * @param minutosMultiple2 Minutos de la segunda sesion del espectaculo
	 * @return Espectaculo de tipo multiple
	 */

	public static EspectaculoMultipleDTO crearEspectaculoMultiple(String tituloEspectaculo, String descripcionEspectaculo,
			CategoriaEspectaculo categoriaEspectaculo, String tipoEspectaculo, int aforoLocalidades,
			int ventasEspectaculo, int horaMultiple1, int minutosMultiple1, String diaSemanaMultiple1,
			String diaSemanaMultiple2, int horaMultiple2, int minutosMultiple2) {
		return new EspectaculoMultipleDTO(tituloEspectaculo,descripcionEspectaculo,categoriaEspectaculo,tipoEspectaculo,aforoLocalidades,ventasEspectaculo,horaMultiple1,minutosMultiple1,diaSemanaMultiple1,diaSemanaMultiple2,horaMultiple2,minutosMultiple2);
	}

	public static EspectaculoTemporadaDTO crearEspectaculoMultiple(String tituloEspectaculo,
			String descripcionEspectaculo, CategoriaEspectaculo categoriaEspectaculo, String tipoEspectaculo,
			int aforoLocalidades, int ventasEspectaculo, int horaTemporada1, int minutosTemporada1,
			String diaSemanaTemporada, int horaTemporada2, int minutosTemporada2) 
	{
		return new EspectaculoTemporadaDTO(tituloEspectaculo,descripcionEspectaculo,categoriaEspectaculo,tipoEspectaculo,aforoLocalidades, 
				ventasEspectaculo,horaTemporada1,minutosTemporada1,diaSemanaTemporada,horaTemporada2,minutosTemporada2);
	}
	
}
