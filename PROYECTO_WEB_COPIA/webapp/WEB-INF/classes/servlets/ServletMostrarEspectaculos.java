package servlets;

import es.uco.pw.negocio.espectaculo.GestorEspectaculosDTO;
import es.uco.pw.negocio.espectaculo.EspectaculoDTO;
import es.uco.pw.datos.dao.espectaculo.EspectaculoDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet para mostrar todos los espectáculos o por categorías
 */
@WebServlet(name="ServletMostrarEspectaculos", urlPatterns="/ServletMostrarEspectaculos") 
public class ServletMostrarEspectaculos extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletMostrarEspectaculos() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	//Inicializamos los ficheros de propiedades
	Properties prop = new Properties();
	Properties sql = new Properties();
 
    
	GestorEspectaculosDTO gestespect= new GestorEspectaculosDTO();
	ArrayList<EspectaculoDTO> listaEspectaculos = new ArrayList<EspectaculoDTO>(); //Para cargar la lista de espectáculos
	ArrayList<EspectaculoDTO> espectaculosSeleccionados = new ArrayList<EspectaculoDTO>(); //Lista auxiliar para los resultados
	String categoria;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		//Cargamos los ficheros de propiedades
		
		prop.load(getServletContext().getResourceAsStream("/WEB-INF/lib/config.properties"));
		sql.load(getServletContext().getResourceAsStream("/WEB-INF/lib/sql.properties"));
		
		//Cargar lista de espectáculos desde el gestor
		
		gestespect.obtenerEspectaculosRegistrados(prop, sql);
		listaEspectaculos=gestespect.getEspectaculos();
		
		
	    //Las diferentes acciones en las que se interactuará con el servlet
	    
		String accion=request.getParameter("accion");
		
		
		//Opción de cargar todos los espectáculos
		if(accion.equals("Mostrar_todos")){
						
			//Pasamos la lista completa al JSP
			
			request.setAttribute("listaEspectaculos", listaEspectaculos);
			
		
		//Opción de cargar sólo los conciertos
			
		}else if(accion.equals("Mostrar_conciertos")){ 
			
			categoria = "concierto";
			
			//Recorremos la lista completa
			
			for(int i=0; i < this.listaEspectaculos.size(); i++) 
			{
				// Comprobamos su categoría y si coincide lo seleccionamos
				
				if(this.listaEspectaculos.get(i).getCategoriaEspectaculo().equals(categoria) ) 
				{
					//Creamos un espectaculo vacio
					EspectaculoDTO espectaux = new EspectaculoDTO();
					
					//Lo igualamos al seleccionado
					espectaux=obtencionDatosEspectaculo(listaEspectaculos.get(i).getTituloEspectaculo());
					
					//Lo añadimos a la lista de resultados
					espectaculosSeleccionados.add(espectaux);
				} 
				
			} // fin recorrido de la lista de espectadores
			
			//Pasamos la lista al JSP
			
			request.setAttribute("listaEspectaculos", espectaculosSeleccionados);

			
			
			
		//Opción de cargar sólo los monólogos
			
		}else if(accion.equals("Mostrar_monologos")){ 
			
			categoria = "monologo";
			
			//Recorremos la lista completa
			
			for(int i=0; i < this.listaEspectaculos.size(); i++) 
			{
				// Comprobamos su categoría y si coincide lo seleccionamos
				
				if(this.listaEspectaculos.get(i).getCategoriaEspectaculo().equals(categoria) ) 
				{
					//Creamos un espectaculo vacio
					EspectaculoDTO espectaux = new EspectaculoDTO();
					
					//Lo igualamos al seleccionado
					espectaux=obtencionDatosEspectaculo(listaEspectaculos.get(i).getTituloEspectaculo());
					
					//Lo añadimos a la lista de resultados
					espectaculosSeleccionados.add(espectaux);
				} 
				
			} // fin recorrido de la lista de espectadores
			
			//Pasamos la lista al JSP
			
			request.setAttribute("listaEspectaculos", espectaculosSeleccionados);
			
			
			
			
		//Opción de cargar sólo las obras de teatro	
			
		}else if(accion.equals("Mostrar_obrasTeatro")){ 
			
			categoria = "concierto";
			
			//Recorremos la lista completa
			
			for(int i=0; i < this.listaEspectaculos.size(); i++) 
			{
				// Comprobamos su categoría y si coincide lo seleccionamos
				
				if(this.listaEspectaculos.get(i).getCategoriaEspectaculo().equals(categoria) ) 
				{
					//Creamos un espectaculo vacio
					EspectaculoDTO espectaux = new EspectaculoDTO();
					
					//Lo igualamos al seleccionado
					espectaux=obtencionDatosEspectaculo(listaEspectaculos.get(i).getTituloEspectaculo());
					
					//Lo añadimos a la lista de resultados
					espectaculosSeleccionados.add(espectaux);
				} 
				
			} // fin recorrido de la lista de espectadores
			
			//Pasamos la lista al JSP
			
			request.setAttribute("listaEspectaculos", espectaculosSeleccionados);

			
		}
	}
	
}