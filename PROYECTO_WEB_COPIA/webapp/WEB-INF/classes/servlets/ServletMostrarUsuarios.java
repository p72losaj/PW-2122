package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.io.IOException;

import es.uco.pw.negocio.usuario.*;


/**
 * Servlet que pasa la lista de usuarios a listaUsuarios.jsp
 * @author Rocío del Viejo Cupido
 */
@WebServlet("/ServletMostrarUsuarios")
public class ServletMostrarUsuarios extends HttpServlet {
	private static final long serialVersionUID = 5L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletMostrarUsuarios() {
        super();
        // TODO Auto-generated constructor stub
    }

	//Inicializamos los ficheros de propiedades
	Properties prop = new Properties();
	Properties sql = new Properties();
	
	GestorUsuariosDTO gestus = new GestorUsuariosDTO();
	ArrayList<UsuarioDTO> usuarios = new ArrayList<UsuarioDTO>();
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		
		//Cargamos los ficheros de propiedades
		
		prop.load(getServletContext().getResourceAsStream("/WEB-INF/lib/config.properties"));
		sql.load(getServletContext().getResourceAsStream("/WEB-INF/lib/sql.properties"));
		
		//Cargamos y pasamos desde el gestor la lista con los usuarios registrados
		
		gestus.setListaEspectadores(prop, sql);
		usuarios = gestus.getListaEspectadores();
		
		//Pasamos la lista al JSP
		
		request.setAttribute("usuarios", usuarios);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
