<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import='es.uco.pw.negocio.usuario.GestorUsuariosDTO' %>
<%@ page import='es.uco.pw.negocio.usuario.UsuarioDTO' %>
<%@ page import= 'java.util.ArrayList'; %>

<!-- JSP que recibe la lista con todos los usuarios desde ServletMostrarUsuarios y los muestra al administrador -->

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Todos los usuarios</title>
		
		
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

		<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,700" rel="stylesheet">
		
		<!-- Script para la búsqueda 
		<script type="text/javascript">
			function search() {
  				var input, filter, ul, li, a, i;
  				input = document.getElementById("input");
  				filter = input.value.toUpperCase();
  				ul = document.getElementById("ul");
  				li = ul.getElementsByTagName("li");
  				for (i = 0; i < li.length; i++) {
  					a = li[i].getElementsByTagName("a")[0];
  					if (a.innerHTML.toUpperCase().indexOf(filter) > -1) {
  						li[i].style.display = "block";
  					} else {
  						li[i].style.display = "none";
  					}
 				}
			}

		</script> -->
	</head>
	
	
	<body>
		<!-- Barra superior que sirve como menú para acceder a las diferentes funciones -->
		<nav class="navbar navbar-dark bg-dark">	
			<a style="color: white" class="navbar-toggler"><span class="navbar-toggler-icon"></span></a>
			<a class="navbar-brand" href="#">
        		<img src="imagenes/piermarini_logo.png" width="30" height="30" class="d-inline-block align-top" alt="">
    			Piermarini espectáculos
  			</a>
  			
  			<!-- Opción para acceder a su perfil y al listado de usuarios -->
	    	<div class="dropdown">
        		<a style="color: white" class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          		Usuarios
        		</a>
        		<div class="dropdown-menu" aria-labelledby="navbarDropdown">
          			<a class="dropdown-item" href="ServletMiPerfil?us=<%= us %>&accion=Mostrar_datos">Mi perfil</a>
          			<div class="dropdown-divider"></div>
          			<a class="dropdown-item" href="ServletMostrarUsuarios">Listado de usuarios</a>
          	    </div>
      		</div>

  			<!-- Opción para dar de alta un espectáculo -->
			<div class="nav-item active">
        		<a style="color: white" class="nav-link" href="">Añadir nuevo espectáculo<span class="sr-only">(current)</span></a>
      		</div>
      		
      		<!-- Opción para mostrar y modificar espectáculos, ya sea todos o algunos en concreto que seleccionemos -->
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
	
	

		<ul>
		<%	
			// Almacena la lista de usuarios proveniente del servlet
				
			ArrayList<UsuarioDTO> usuarios = new ArrayList<UsuarioDTO>();
			usuarios = (ArrayList<UsuarioDTO>)request.getAttribute("usuarios");
				
			for(UsuarioDTO us : usuarios){
					
		%>
			<li>
				<p>Nombre completo: <%= us.getNombreEspectador() %> <%= us.getPrimerApellidoEspectador() %> <%= us.getSegundoApellidoEspectador() %></p>
				<p>Nick: <%= us.getNickEspectador() %></p>
				<hr>
			</li>
		<%
			}
		%>
		</ul>

	</body>
</html>