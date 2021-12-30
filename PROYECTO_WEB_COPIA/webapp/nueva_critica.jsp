<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Nueva critica</title>
</head>
<body>

<%
		UsuarioDTO us = new UsuarioDTO();
		request.getAttribute(us, "us");
		EspectaculoDTO espectaculo = new EspectaculoDTO();
		request.getAttribute(espectaculo, "espectaculo");
		String correo = us.getCorreoEspectador();
		String tituloEspectaculo = espectaculo.getTituloEspectaculo();
%>

	<form action="ServletCriticas">
	
		<input type="hidden" name="correo" value="<%= correo %>">
		<input type="hidden" name="tituloEspectaculo" value="<%= tituloEspectaculo %>">
	
		<input type="text" size="20" name="tituloCritica" placeholder="Titulo de la reseña">
	
		<br/><br/>
	
		<textarea name="resena" rows="10" cols="40" placeholder="Escribe aquí tu reseña"></textarea>

		<br/><br/>
		
		<input type="submit" name="accion" value="AnadirCritica">

	</form>

		<br/><br/>

		<a href="espectaculo.jsp?espectaculo=<%= espectaculo %>">Volver a datos del espectáculo</a>

	
</body>
</html>