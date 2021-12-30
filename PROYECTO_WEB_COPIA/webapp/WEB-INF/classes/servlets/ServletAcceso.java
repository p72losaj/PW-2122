package servlets;

import es.uco.pw.datos.dao.usuario.UsuarioDAO;
import es.uco.pw.negocio.usuario.*;
import es.uco.pw.negocio.log.LogDTO;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.Object;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Properties;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Controlador
 */
@WebServlet(name="ServletAcceso", urlPatterns="/ServletAcceso") 
public class ServletAcceso extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAcceso() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	//Inicializamos los ficheros de propiedades
	Properties prop = new Properties();
	Properties sql = new Properties();
 
    
	GestorUsuariosDTO us= new GestorUsuariosDTO();
	UsuarioDTO p=new UsuarioDTO();
	LogDTO logger=new LogDTO();
	String estado; //variable para la comprobación en el registro


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		//Cargamos los ficheros de propiedades
		
		prop.load(getServletContext().getResourceAsStream("/WEB-INF/lib/config.properties"));
		sql.load(getServletContext().getResourceAsStream("/WEB-INF/lib/sql.properties"));
		
		us.setListaEspectadores(prop, sql); //Cargamos la lista de espectadores
	    //Las diferentes acciones en las que se interactuará con el servlet
	    
		String accion=request.getParameter("accion");

		if(accion.equals("Iniciar sesión")){
			
			//Cargamos los datos desde el formulario
			String correo=request.getParameter("correo");
			String pass=request.getParameter("password");
			
			//Llamamos a la funciones correspondientes del gestor para llevar a cabo la validación
		
			int idlog = us.obtencionIdentificadorUsuario(correo);
		
			
			if(logger.ComprobarLog(idlog,pass,prop,sql)==true) { 
				
				
				p = us.obtenerDatosUsuario(correo);
				//Guardamos al usuario obtenido como atributo del request (parámetro para el JSP)
				request.setAttribute(p, "us");
				
				//actualizamos el log
				logger.ActualizarLog(idlog,pass, prop, sql);
				
				
				//Acceso correcto
				if((p.getRolUsuario()).equals("espectador")) {

					//Llevamos al espectador a su página de bienvenida
					request.getRequestDispatcher("principal_espectador.jsp").forward(request, response);
				}
				
				else {
					
					//Llevamos al administrador a su página de bienvenida
					request.getRequestDispatcher("principal_administrador.jsp").forward(request, response);
				}
				
				//Acceso incorrecto, lo queda en la página de inicio de sesión
			}else{ 
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}

			
		//Opción de cerrar sesión y volver a la página de inicio de sesión
		}else if(accion.equals("Desconectar")){ 
			request.getRequestDispatcher("index.jsp").forward(request, response);

			
		//Opción de registrarse en el sistema
		}else if(accion.equals("Registrarse")) { 
			
			//Cargamos los datos desde el JSP
			String nombre=request.getParameter("nombre");
			String primer_apellido=request.getParameter("primer_apellido");
			String segundo_apellido=request.getParameter("segundo_apellido");
			String nick=request.getParameter("nick");
			String correo=request.getParameter("correo");
			String password=request.getParameter("password");
			
			LocalDate fechaActual = LocalDate.now();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
			java.util.Date parsed = format.parse(fechaActual.toString());
			
			java.sql.Date fecha = new java.sql.Date(parsed.getTime());
			
			}
			catch(Exception ex) {
				System.out.println("Error con la fecha");
			}
			
			
			
			//En principio sólo se podrán registrar nuevos espectadores para impedir que alguien no autorizado se registre como administrador
			String rol="espectador"; 
			
			//Llamamos a la función del gestor para llevar a cabo el registro
			estado = us.registrarUsuario(prop, sql, correo, nombre, primer_apellido, segundo_apellido, nick, rol);
			int idusu = us.obtencionIdentificadorUsuario(correo);
			
			logger.insertarLog(idusu, fecha, fecha, password,  prop,  sql);
			
			//El registro se ha realizado correctamente
			if(estado == "Registro del usuario en la base de datos ha sido un exito") { 
				request.getRequestDispatcher("principal_espectador.jsp").forward(request, response);
			}
			
			//No se ha registrado correctamente y se quedaría en la página de registro
			else { 
				request.getRequestDispatcher("sign_up.jsp").forward(request, response);
			}
			
		}
	}
	
}