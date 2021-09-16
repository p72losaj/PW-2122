package es.uco.pw;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Programa que prueba metodos de mostrar informacion por pantalla
 * @author Jaime Lorenzo Sanchez
 * @version 1.0
 */

public class HolaMundoProgramaPrincipal {
	
	public static void main(String[] args) {
		
		Operaciones op = new Operaciones();
		
		int res = op.restoDivision(8, 5);
		
		float coc = op.cocienteDivision(8, 5);
		
		// Mostramos por pantalla una cadena
		
		System.out.println("Hola Mundo");
		
		// Mostramos por pantalla un argumento pasado como parametro
		
		System.out.println(args[0]);
		
		// Mostramos por pantalla la operacion de dividir
		
		System.out.println("CocienteDivision: " + coc);
		System.out.println("RestoDivision: " + res);
		
		// Obtenemos la fecha actual en milisegundos
		
		long miliseconds = System.currentTimeMillis();
		
		// Transformamos la fecha actual en formato java.sql.Date

		java.sql.Date fechaActual = new java.sql.Date(miliseconds);
		
		// Formato de las fechas java.util.Date
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		
		// Obtenemos una fecha para comparar
		
		try {
			Date comparar = formato.parse(args[1]);
			if(comparar.before(fechaActual)) {
				System.out.println(comparar + " es anterior a " + fechaActual);
			}
			else if(comparar.after(fechaActual)) {
				System.out.println(comparar + " es posterior a " + fechaActual);
			}
		}catch(Exception ex) {
			System.out.println("Se ha producido un error al comparar las fechas");
		}
		
		// end_main
	}

}
