package servlets;

import es.uco.pw.negocio.espectaculo.EspectaculoDTO;

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
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ServletEspectaculo() {
        // TODO Auto-generated constructor stub
    }

	//Inicializamos los ficheros de propiedades
	Properties prop = new Properties();
	Properties sql = new Properties();
    
	EspectaculoDTO espectaculo = new EspectaculoDTO();
	EspectaculoDAO espect = new EspectaculoDAO();
	String titulo_espectaculo;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Esto no se para que coño es response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//Asignamos el parámetro del JSP a la variable titulo_espectaculo
		
		titulo_espectaculo=request.getParameter("titulo"); //titulo es el id de lo que nos ha llegado
		
		//Llamamos a la función de devolver los datos de un espectáculo por su título
		
		espectaculo = espect.obtencionEspectaculo(prop, sql, String titulo_espectaculo);
		
		//Guardamos el espectaculo obtenido como atributo del request (parámetro para el JSP)
		request.setAttribute(espectaculo, "espectaculo");
		
		//Vamos a la página de ese espectáculo
		
		request.getRequestDispatcher("espectaculo.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
