package es.uco.pw.comun.menus;

/**
 * Clase que gestiona los distintos menus que se mostraran al usuario
 * @author Jaime Lorenzo Sanchez
 * @version 1.0
 */

public class Menus {
	
	/**
	 * Constructor de clase
	 */

	public Menus() {
		
	}
	
	/**
	 * Funcion que muestra el menu de acceso
	 */
	
	public void MostrarMenuAcceso() {
		
		System.out.println("MENU DE ACCESO DEL USUARIO");
		System.out.println("0. Salir del sistema");
		System.out.println("1. Registrar los datos del usuario en el sistema");
		System.out.println("2. Identificarse en el sistema");
		
	}
	
	/**
	 * Funcion que muestra el menu al usuario cuando se identifica en el sistema
	 */
	
	public void MostrarMenuUsuarioRegistrado() {
		System.out.println("MENU DE USUARIO");
		System.out.println("0. SALIR DEL MENU");
		System.out.println("1. CRITICAS");
		System.out.println("2. USUARIO");
		System.out.println("3. ESPECTACULOS");
		System.out.println("Introduce una opcion: ");
	}
	
	/**
	 * Funcion que muestra el menu de criticas
	 */

	public void MostrarMenuCriticas() {
		System.out.println("MENU DE CRITICAS");
		System.out.println("0. SALIR DEL MENU DE CRITICAS");
		System.out.println("1. CREAR UNA CRITICA");
		System.out.println("2. CONSULTAR TODAS LAS CRITICAS DISPONIBLES");
		System.out.println("3. BORRAR UNA CRITICA DEL USUARIO");
		System.out.println("4. VOTAR LA UTILIDAD DE UNA CRITICA DE OTRO USUARIO REGISTRADO");
		System.out.println("5. BUSCAR LAS CRITICAS DE UN USUARIO REGISTRADO");
		System.out.println("INTRODUCE UNA OPCION: ");
	}

}
