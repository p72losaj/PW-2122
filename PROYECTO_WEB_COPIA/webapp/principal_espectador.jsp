<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import='java.util.Date' %>
<%@ page import='es.uco.pw.negocio.usuario.UsuarioDTO' %>

<!-- Bugs encontrados a solucionar -->
<!-- Bug: al intentar selccionar la categoría desaparece el desplegable principal. El valor sí que se guarda -->

<!DOCTYPE html>
<html>


	<head>
		<meta charset="UTF-8">
		
		<!-- Link hacia los archivos de estilos css -->
 		<link rel="stylesheet" href="css/css_welcome_user.css">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

		<title>Bienvenida espectador</title>
		<meta name="author" content="">
        <meta name="description" content="Página principal del espectador en el que se le da acceso a su perfil y al apartado de espectáculos">
        <meta name="keywords" content="">
		
		<!-- Scripts para la obtención de la hora de conexión y la actual -->
		<script type="text/javascript" src="hora_actual.js"></script>
	</head>
	
	
	
	<!-- Inicializamos startTime() para la obtención de la hora -->
	<body onload="startTime()">
	
			<!-- Asignamos a una variable el resultado del servlet para poder mostrar los datos-->
		<%
		
			UsuarioDTO us = getAttribute("us");
		
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
			<!-- Mensaje de bienvenida personalizado con el nombre del usuario -->
			<h1>Bienvenido al sistema, ${us.nick} .</h1>
			<br/><br/>

			<h3>Tiempo de conexión: </h3>
			
			<!-- Contenedor que muestra la hora actual y la de conexión-->
			<div id="clockdate">
  				<div class="clockdate-wrapper">
    				<div id="clock"></div>
    				<div id="date"></div>
  				</div>
			</div>
		</div> 
	
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

	</body>


</html>