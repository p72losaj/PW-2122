<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import='es.uco.pw.negocio.espectaculo.EspectaculoDTO' %>
<%@ page import='es.uco.pw.negocio.critica.*' %>
<%@ page import='es.uco.pw.negocio.usuario.UsuarioDTO' %>
<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Informacion del espectaculo</title>
		
		<!-- Link hacia los archivos de estilos css -->
 		<link rel="stylesheet" href="css/css_welcome_user.css">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

		<meta name="author" content="Rocío del Viejo Cupido">
        <meta name="description" content="Página que muestra los datos de un espectáculo junto con la lista de críticas asociadas a él">
        <meta name="keywords" content="">

	</head>


	<body>
	
			<!-- Asignamos a una variable el resultado del servlet para poder mostrar los datos-->
		<%
		
			EspectaculoDTO espectaculo = getAttribute("espectaculo");
		
		%>


		<!-- Barra superior que sirve como menú para acceder a las diferentes funciones -->
		<nav class="navbar navbar-dark bg-dark">	
			<a style="color: white" class="navbar-toggler"><span class="navbar-toggler-icon"></span></a>
			<a class="navbar-brand" href="#">
        		<img src="imagenes/piermarini_logo.png" width="30" height="30" class="d-inline-block align-top" alt="">
    			Piermarini espectáculos
  			</a>
  			
  			<!-- Opción para acceder a la visualización y modificación de los datos personales del usuario-->
			<div class="nav-item active">
        		<a style="color: white" class="nav-link" href="ServletMiPerfil?us=<%= us %>&accion=Mostrar_datos">Mi perfil <span class="sr-only">(current)</span></a>
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
		
	
		<div class="container mt-4">
			<br/><br/><br/>
			<h2> <%= espectaculo.getTituloEspectaculo() %> </h2>
			<br/><br/>
			<h5>Tipo: <%= espectaculo.getCategoriaEspectaculo() %></h5>
			<br/>
			<h5>Descripción: <%= espectaculo.getDescripcionEspectaculo() %></h5>
			<br/>
			<h5>Aforo: <%=espectaculo.getAforoLocalidadesEspectaculo() %></h5>
			<h5>Lista de sesiones:</h5>
			<br/>
			<h5><!-- Poner la lista de sesiones --></h5>
			<br/>
			
			<div class="cont_form">
				<br/>
				<h2>&nbsp;&nbsp;Críticas</h2>
				
  				<!-- Desplegar aquí la lista de críticas. Comprobar si coincide con el usuario que tenemos guardado, en cuyo caso se dará la opción de que borre esa crítica.
  					Si después de desplegar todas vemos que no tiene ninguna crítica, le daremos la opción de añadir una
  				 -->
  				 <ul>
				<%	
				//Controla que el usuario no haya escrito una critica anteriormente de ese espectáculo
					int ncri = 0;
				
				//Usuario que ha entrado en el espectaculo
				
					UsuarioDTO us = new UsuarioDTO();
					us = (UsuarioDTO)request.getAttribute("us");
				
				// Almacena la lista de criticas proveniente del servlet
				
					ArrayList<CriticaDTO> criticas = new ArrayList<CriticaDTO>();
					criticas = (ArrayList<UsuarioDTO>)request.getAttribute("listaCriticas");
				
					for(CriticaDTO critica : criticas){
						
				%>
					<li>
						<h4><%= critica.getTituloCritica() %></h4>
						<p>Autor: <%= critica.getAutorCritica() %></p>
						<p>Critica: <%= critica.getResenaCritica() %></p>
						
				<%
						//Si una de las criticas es suya se le dara la opción de borrarla
						if(us.getNickEspectador().equals(critica.getAutorCritica())){
							ncri = 1;
							String correo = us.getCorreoEspectador();
							int idCritica = critica.getIdentificadorCritica();
							int idUsuario = us.getIdUsuario();
				%>	
						<a href="ServletCriticas?accion=EliminarCritica&correo=<%= correo %>&idCritica=<%= idCritica %>&idUsuarios=<%= idUsuario %>">Eliminar crítica</a>
				<% 	
						}
				
				%>
						<hr>
					</li>
				<%
					}
				%>
				</ul>
				
				<%
					//Si aun no tiene ninguna critica registrada para ese espectáculo, se le dará la opción de añadir una
					if(ncri==0){
				%>	
				
				<a href="ServletCriticas?us=<%= us %>&accion=AccesoANuevaCritica&espectaculo=<%= espectaculo %>">Añadir una crítica</a>
					
				<%
					}
				%>
			</div> 
	
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>


	</body>
	
</html>