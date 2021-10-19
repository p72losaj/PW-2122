package es.uco.pw.negocio.espectaculo;

/**
 * Factoria de espectaculos
 * @author Jaime Lorenzo Sanchez
 * @version 2.0
 */

public abstract class FactoriaEspectaculosDTO {
	
	/**
	 * Constructor vacio de la factoria de espectaculos
	 */
	
	public FactoriaEspectaculosDTO() {
	}
	
	/**
	 * Funcion que crea un espectaculo de tipo puntual
	 * @param titulo Titulo del espectaculo
	 * @param descripcion Descripcion del espectaculo
	 * @param categoria Categoria del espectaculo
	 * @param tipo Tipo del espectaculo
	 * @param fecha Fecha del espectaculo
	 * @param hora Hora del espectaculo
	 * @param aforoLocalidades Numero de localidades del espectaculo
	 * @param ventasEspectaculo Numero de ventas del espectaculo
	 * @return Espectaculo de tipo puntual
	 */
	
	public EspectaculoPuntualDTO crearEspectaculoPuntual(String titulo,String descripcion, CategoriaEspectaculoDTO categoria, String tipo, String fecha, String hora, int aforoLocalidades, int ventasEspectaculo) {
		return new EspectaculoPuntualDTO(titulo,descripcion, categoria, tipo, fecha, hora, aforoLocalidades, ventasEspectaculo);
	}
	
	/**
	 * Funcion que crea un espectaculo de temporada
	 * @param titulo Titulo del espectaculo
	 * @param descripcion Descripcion del espectaculo
	 * @param categoria Categoria del espectaculo
	 * @param tipo Tipo del espectaculo
	 * @param fechaInicio Fecha de inicio del espectaculo
	 * @param fechaFin Fecha de fin del espectaculo
	 * @param dia Dia de la semana
	 * @param aforoLocalidades Numero de localidades del espectaculo
	 * @param ventasEspectaculo Numero de ventas del espectaculo
	 * @return Espectaculo de tipo temporada
	 */
	
	public EspectaculoTemporadaDTO crearEspectaculoTemporada(String titulo, String descripcion, CategoriaEspectaculoDTO categoria, String tipo, String fechaInicio, String fechaFin, String dia, int aforoLocalidades, int ventasEspectaculo) {
		return new EspectaculoTemporadaDTO(titulo, descripcion, categoria,tipo,fechaInicio,fechaFin,dia, aforoLocalidades, ventasEspectaculo);
	}

	/**
	 * Funcion que crea un espectaculo de tipo multiple
	 * @param titulo Titulo del espectaculo
	 * @param descripcion Descripcion del espectaculo
	 * @param categoria Categoria del espectaculo
	 * @param tipo Tipo del espectaculo
	 * @param dia1 Dia de la semana de la sesion 1 del espectaculo
	 * @param hora1 Hora de la sesion 1 del espectaculo
	 * @param dia2 Dia de la semana de la sesion 2 del espectaculo
	 * @param hora2 Hora de la sesion 2 del espectaculo
	 * @param aforoLocalidades Numero de localidades del espectaculo
	 * @param ventasEspectaculo Numero de ventas del espectaculo
	 * @return Espectaculo de tipo multiple
	 */
	
	public EspectaculoMultipleDTO crearEspectaculoMultiple(String titulo,String descripcion, CategoriaEspectaculoDTO categoria, String tipo, String dia1, String hora1, String dia2, String hora2, int aforoLocalidades, int ventasEspectaculo) {
		return new EspectaculoMultipleDTO(titulo,descripcion, categoria, tipo, dia1, hora1, dia2, hora2, aforoLocalidades, ventasEspectaculo);
	}
	
}
