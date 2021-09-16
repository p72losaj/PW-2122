package es.uco.pw;

/**
 * Programa simple que implementa una serie de operaciones
 * @author Jaime Lorenzo Sanchez
 * @version 1.0
 */

public class Operaciones {
	/**
	 * Funcion que obtiene el resto de la division de 2 valores enteros
	 * @param a Dividendo de la division
	 * @param b Divisor de la division
	 * @return Resto de la division
	 */
	public int restoDivision (int a, int b) {
		if(b==0) {
			throw new ArithmeticException();
		}
		return a%b;
	}
	
	public float cocienteDivision (int a, int b) {
		return a/b;
	}
	
}
