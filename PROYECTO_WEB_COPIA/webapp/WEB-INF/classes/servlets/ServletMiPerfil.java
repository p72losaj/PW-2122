package servlets;

import es.uco.pw.negocio.usuario.*;
import es.uco.pw.datos.dao.usuario.UsuarioDAO;

import java.io.IOException;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletEspectaculo
 */
@WebServlet("/ServletEspectaculo")
public class ServletEspectaculo extends HttpServlet {
	private static final long serialVersionUID = 3L;

    /**
     * Default constructor. 
     */
    public ServletEspectaculo() {
        // TODO Auto-generated constructor stub
    }

	//Inicializamos los ficheros de propiedades
	Properties prop = new Properties();
	Properties sql = new Properties();
    
	GestorUsuariosDTO gest_us = new GestorUsuariosDTO();
	UsuarioDTO us = new UsuarioDTO();
	String correo;
	String nombre;
	String apellido1;
	String apellido2
	String nick;
	int id;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		prop.load(getServletContext().getResourceAsStream("/WEB-INF/lib/config.properties"));
		sql.load(getServletContext().getResourceAsStream("/WEB-INF/lib/sql.properties"));
		
		String accion=request.getParameter("accion");

		if(accion.equals("Mostrar_datos")){
		
			//Cargamos la lista de espectadores del gestor
		
			gest_us.setListaEspectadores(prop, sql);
		
			//Asignamos el parÃ¡metro del JSP a la variable correo
			
			correo=request.getParameter("correo"); //correo es el id de lo que nos ha llegado
		
			//Llamamos a la funciÃ³n de devolver los datos de un usuario por su correo
		
			us = gest_us.obtenerDatosUsuario(correo);
			
			//Lo guardamos como atributo del request (parÃ¡metro para el JSP)
			
			request.setAttribute(us, "us");
		
			//Volvemos al JSP para poder mostrar los datos
		
			request.getRequestDispatcher("mi_perfil.jsp").forward(request, response);
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String accion=request.getParameter("accion");
		
		if(accion.equals("Modificar_datos")) {
				

			prop.load(getServletContext().getResourceAsStream("/WEB-INF/lib/config.properties"));
			sql.load(getServletContext().getResourceAsStream("/WEB-INF/lib/sql.properties"));
			
			
			//Pillamos los datos del JSP
			
			correo=request.getParameter("correo");
			nombre=request.getParameter("nombre");
			apellido1=request.getParameter("apellido1");
			apellido2=request.getParameter("apellido2");
			nick=request.getParameter("nick");
			

			//Cargamos lista de espectaadores en el gestor
			gest_us.setListaEspectadores(prop, sql);
			
			//Obtenemos el usuario que se va a cambiar
			us = gest_us.obtenerDatosUsuario(correo);
			
			
			//Depende de los campos que haya rellenado, cambiamos unos datos u otros
			
			if(!"". equals(nombre)) {
				us.setNombreEspectador(nombre);
			}
			
			if(!"". equals(apellido1)) {
				us.setPrimerApellidoEspectador(apellido1);
			}
			
			if(!"". equals(apellido2)) {
				us.setSegundoApellidoEspectador(apellido2);
			}
			
			if(!"". equals(nick)) {
				us.setNickEspectador(nick);
			}
			
			int error = gest_us.modificarUsuario(us.getIdUsuario(),correo,nombre,apellido1,apellido2, nick, prop,sql);
			
			//Devolvemos el usuario con los datos cambiados y volvemos a la pÃ¡gina principal
			
			request.setAttribute(us, "us");
			
			if((p.getRolUsuario()).equals("espectador")) {

				//Llevamos al espectador a su pÃ¡gina de bienvenida
				
				request.getRequestDispatcher("principal_espectador.jsp").forward(request, response);
			}
			
			else {
								
				//Llevamos al administrador a su pÃ¡gina de bienvenida
				
				request.getRequestDispatcher("principal_administrador.jsp").forward(request, response);
			}
			
		}
		else if(accion.equals("Dar_Baja")) {
			

			prop.load(getServletContext().getResourceAsStream("/WEB-INF/lib/config.properties"));
			sql.load(getServletContext().getResourceAsStream("/WEB-INF/lib/sql.properties"));
			
			
			//Pillamos los datos del JSP
			
			correo=request.getParameter("correo");			

			//Cargamos lista de espectaadores en el gestor
			gest_us.setListaEspectadores(prop, sql);
			
			//Obtenemos el usuario que se va a cambiar
			us = gest_us.obtenerDatosUsuario(correo);
			
			
			//Depende de los campos que haya rellenado, cambiamos unos datos u otros
			
			int error = gest_us.modificarUsuario(us.getIdUsuario(),"deleted","deleted","deleted","deleted", "deleted", prop,sql);
			
		
				//salimos a la pagina de inicio
			
				request.getRequestDispatcher("index.jsp").forward(request, response);
			
		}
	}

}
