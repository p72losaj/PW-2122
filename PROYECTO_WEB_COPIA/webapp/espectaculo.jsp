<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import='es.uco.pw.negocio.espectaculo.EspectaculoDTO' %>
<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Informacion del espectaculo</title>
		
		<!-- Link hacia los archivos de estilos css -->
 		<link rel="stylesheet" href="css/css_welcome_user.css">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

		<meta name="author" content="">
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
        		<img src="imagenes/logo_bootstrap.png" width="30" height="30" class="d-inline-block align-top" alt="">
    			Página reshulona sin nombre
  			</a>
  			
  			<!-- Opción para acceder a la visualización y modificación de los datos personales del usuario-->
			<div class="nav-item active">
        		<a style="color: white" class="nav-link" href="mi_perfil.jsp">Mi perfil <span class="sr-only">(current)</span></a>
      		</div>
      		
      		<!-- Opción para mostrar espectáculos, ya sea todos o algunos en concreto que seleccionemos -->
	    	<div class="dropdown">
        		<a style="color: white" class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          		Espectáculos
        		</a>
        		<div class="dropdown-menu" aria-labelledby="navbarDropdown">
          			<a class="dropdown-item" href="#">Todos los espectáculos</a>
          			<div class="dropdown-divider"></div>
          			<a class="dropdown-item">Buscar por nombre
    	  				<form class="form-inline">
    						<input class="form-control mr-sm-2" type="search" placeholder="Introducir nombre" aria-label="Search">
    						<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Buscar</button>
  						</form>
          			</a>
          			<div class="dropdown-divider"></div>
          			<a class="dropdown-item">Buscar por categoría&nbsp;&nbsp;
          			<!-- Bug: al intentar selccionar la categoría desaparece el desplegable principal. El valor sí que se guarda -->
          			<!-- El campo de value es lo que se enviará -->
          			<form>
          			<select name="busqueda_categorias">
   						<option selected value="0">Seleccionar</option> 
   						<option value="2">Categoría 1</option> 
   						<option value="3">Categoría 2</option>
   						<option value="10">Categoría 3</option> 
					</select>
					</form>
          			</a>
          	    </div>
      		</div>
      		
      		<!-- Opción que permite cerrar la sesión o acceder a la página para darse de baja -->
			<div class="dropdown">
				<a style="color: white" href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Cerrar sesión&nbsp;&nbsp;</a>
				<div class="dropdown-menu text-center">
					<a><img src="imagenes/perfil.png" height="80" width="80"></a>
					<a><br/>&nbsp;Nombre completo&nbsp;</a>
					<a>&nbsp;correo@correo.com&nbsp;</a>
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
			<h2> ${nombre} </h2>
			<br/><br/>
			<h5>Tipo: ${si es un cocierto, monologo, etc}</h5>
			<br/>
			<h5>Lista de sesiones:</h5>
			<br/>
			<h5>${lista de sesiones}</h5>
			<br/>
			
			<div class="cont_form">
				<br/>
				<h2>&nbsp;&nbsp;Críticas</h2>
      			<form action="ServletAcceso">
        			<label for="name">Nuevo nombre</label>
        				<input type="text" id="nombre" name="nombre">
        	
        				<label for="primer_apellido">Nuevo primer apellido</label>
        				<input type="text" id="primer_apellido" name="primer_apellido">
        	
        				<label for="segundo_apellido">Nuevo segundo apellido</label>
        				<input type="text" id="segundo_apellido" name="segundo_apellido">
        	
        				<label for="user">Nuevo nick de usuario</label>
        				<input type="text" id="nick" name="nick">
        	
        				<br/>
      					<div class="zoom">
        					<input type="submit" name="accion" value="Modificar datos">
      					</div> 
      					<br/>
      				</form>
				</div> 
	
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>


	</body>
	
</html>