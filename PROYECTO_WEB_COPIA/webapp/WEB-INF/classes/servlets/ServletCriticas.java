package servlets;

import es.uco.pw.negocio.critica.GestorCriticasDTO;
import es.uco.pw.negocio.critica.CriticaDTO;
import es.uco.pw.datos.dao.CriticaDAO;

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
@WebServlet("/ServletCriticas")
public class ServletEspectaculo extends HttpServlet {
	private static final long serialVersionUID = 6L;

    /**
     * Default constructor. 
     */
    public ServletCriticas() {
        // TODO Auto-generated constructor stub
    }

	//Inicializamos los ficheros de propiedades
	Properties prop = new Properties();
	Properties sql = new Properties();

	//CriticaDTO crit = new CriticaDTO();
	
	//CriticaDAO critdao = new CriticaDAO();
	GestorCriticasDTO criGestor = new GestorCriticasDTO();
	ArrayList<CriticaDTO> criticas = new ArrayList<CriticaDTO>();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//cargar properties
		prop.load(getServletContext().getResourceAsStream("/WEB-INF/lib/config.properties"));
		sql.load(getServletContext().getResourceAsStream("/WEB-INF/lib/sql.properties"));
		
		//seleccionar la accion
		
		String accion=request.getParameter("accion");
		

		//Asignamos todas las criticas al array y cargamos el gestor tambien
		criticas = criGestor.obtencionDatosCriticas(prop, sql);
		
			if(accion.equals("MostrarCriticas")) {		
		
					
		
					//Almacenamos datos de las criticas en un string
		
					String datosCriticas = "";
		
					for(int i = 0; i < criticas.size(), i++) {
		
						datosCriticas = datosCriticas + criticas.get(i).mostrarCritica();
			
					}
		
		
		
					//Guardamos las criticas, y todos los datos como atributos del request (parámetro para el JSP)
					request.setAttribute(datosCriticas, "datosCriticas");
					//request.setAttribute(criticas, "listaCriticas");
		
					//Vamos a la pagina que muestra todas las criticas
		
					request.getRequestDispatcher("criticas.jsp").forward(request, response);
			}
			else if(accion.equals("AnadirCritica")) {
				
				String resultado = "";
			
				//Obtenemos los distintos parametros para anadir una nueva critica
				String correo=request.getParameter("correo");
				String tituloCritica=request.getParameter("tituloCritica");
				String resenaCritica=request.getParameter("resena");
				String tituloEspectaculo=request.getParameter("tituloEspectaculo");
				int puntuacion=request.getParameter("puntuacion");
				String fechaActual=request.getParameter("fecha");
			
					
				//Registramos la nueva critica en el sistema
				resultado = criGestor.registroCritica(prop,sql, correo,  tituloCritica,  resenaCritica,  tituloEspectaculo, puntuacion, fechaActual);

				//Guardamos el resultado de introducir la nueva critica
				request.setAttribute(resultado, "resultado");
				
				
				//Mostramos si ha existo error o no al introducir la nueva critica
				request.getRequestDispatcher("criticas.jsp").forward(request, response);
			}
			else if(accion.equals("EliminarCritica")) {
				
				
				String resultado = "";
				
				//Obtenemos los distintos parametros para eliminar la critica
				String correoUsuario=request.getParameter("correo");
				int idCritica=request.getParameter("idCritica");
				int idUsuario=request.getParameter("idUsuario");
				
				//Eliminamos la critica
				
				resultado = criGestor.eliminacionCritica(prop,sql,identificadorCritica,correoUsuario,idUsuario);
				
				//Guardamos el resultado de introducir la nueva critica
				request.setAttribute(resultado, "resultado");
				
				
				//Mostramos si ha existo error o no al introducir la nueva critica
				request.getRequestDispatcher("criticas.jsp").forward(request, response);
				
				
			}
	
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
