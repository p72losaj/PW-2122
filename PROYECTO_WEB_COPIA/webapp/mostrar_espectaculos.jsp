<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import='es.uco.pw.negocio.espectaculo.GestorEspectaculosDTO'; %>
<%@ page import= 'es.uco.pw.negocio.espectaculo.EspectaculoDTO'; %>  
<%@ page import= 'java.util.ArrayList'; %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<title>Resultado de la seleccion de espectaculos</title>

	<link rel="stylesheet" href="css/css_lista_espectaculos.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

	<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,700" rel="stylesheet">

	<script src="js/jquery-3.2.1.js"></script>
	<script src="js/script.js"></script>

</head>
<body>

		<!-- Barra superior que sirve como menú para acceder a las diferentes funciones -->
		<nav class="navbar navbar-dark bg-dark">	
			<a style="color: white" class="navbar-toggler"><span class="navbar-toggler-icon"></span></a>
			<a class="navbar-brand" href="#">
        		<img src="imagenes/logo_bootstrap.png" width="30" height="30" class="d-inline-block align-top" alt="">
    			Página reshulona sin nombre
  			</a>
  			
  			<!-- Opción para acceder a la visualización y modificación de los datos personales del usuario-->
			<div class="nav-item active">
        		<a style="color: white" class="nav-link" href="ServletMiPerfil?us=<%= us %>&?accion=Mostrar_datos">Mi perfil <span class="sr-only">(current)</span></a>
      		</div>
      		
      		
      		<!-- Opción para mostrar espectáculos, ya sea todos o algunos en concreto que seleccionemos -->
	    	<div class="dropdown">
        		<a style="color: white" class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          		Espectáculos
        		</a>
        		<div class="dropdown-menu" aria-labelledby="navbarDropdown">
          			<a class="dropdown-item" href="ServletMostrarEspectaculos?accion=Mostrar_todos">Todos los espectáculos</a>
          			<div class="dropdown-divider"></div>
          			<a class="dropdown-item">Buscar por nombre
    	  				<form class="form-inline">
    						<input class="form-control mr-sm-2" type="search" placeholder="Introducir nombre" aria-label="Search">
    						<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Buscar</button>
  						</form>
          			</a>
          			<div class="dropdown-divider"></div>
          			<a class="dropdown-item" href="ServletMostrarEspectaculos?accion=Mostrar_conciertos">Todos los conciertos</a>
          			<div class="dropdown-divider"></div>          			
          			<a class="dropdown-item" href="ServletMostrarEspectaculos?accion=Mostrar_monologos">Todos los monólogos</a>
          			<div class="dropdown-divider"></div>          			
          			<a class="dropdown-item" href="ServletMostrarEspectaculos?accion=Mostrar_obrasTeatro">Todas las obras de teatro</a>
          	    </div>
      		</div>
      		
      		<!-- Opción que permite cerrar la sesión o acceder a la página para darse de baja -->
			<div class="dropdown">
				<a style="color: white" href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Cerrar sesión&nbsp;&nbsp;</a>
				<div class="dropdown-menu text-center">
					<a><img src="imagenes/perfil.png" height="80" width="80"></a>
					<a><br/>&nbsp;<%= us.getNombreEspectador() %> <%= us.getPrimerApellidoEspectador() %>&nbsp;</a>
					<a>&nbsp;<%= us.getCorreoEspectador() %>&nbsp;</a>
					<div class="dropdown-divider"></div>
					<a href="index.jsp" class="dropdown-item">Salir</a>
					<a href="#" class="dropdown-item">
					<form class="form-inline">
    					<button class="btn btn-sm btn-outline-secondary" style="color: red" type="button">Darse de baja</button>
  					</form>
					</a>
				</div>
			</div>
		</nav>
	


	<div class="wrap">
		<h1>Espectáculos seleccionados:</h1>
		<section class="products-list">
			<table>
				<%
					// Almacena la lista de espectáculos proveniente del servlet
				
					ArrayList<EspectaculoDTO> listaEspectaculos =new ArrayList<EspectaculoDTO>();
					listaEspectaculos = (ArrayList<EspectaculoDTO>)request.getAttribute("listaEspectaculos");
					
					//Variable para controlar la disposición de las columnas
					
					int salto = 0;
					
					//Vamos recorriendo la lista y mostrando el título de los espectáculos
					
					for(EspectaculoDTO es : listaEspectaculos){
	
				%>
					<th>
						<div class="product-item">
							<a href="ServletEspectaculo?us=<%= es %>"><%= es.getTituloEspectaculo() %></a>
						</div>
					</th>
				<%
						salto++;
						if(salto==4);
				%>
					<tr>
				<%
							salto=0;
						}
					}
				%>
				
			</table>
		</section>
	</div>

</body>
</html>