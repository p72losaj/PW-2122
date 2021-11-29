<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import='servlets.ServletAcceso' %>

<!DOCTYPE html>

<html>
    <head>

        <title>Proyecto Web registro</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <meta name="author" content="">
        <meta name="description" content="Formulario de registro">
        <meta name="keywords" content="sign up,formulario de registro">
        
        <!-- Link hacia el archivo de estilos css -->
        <link rel="stylesheet" href="css/css_sign_up.css">
        
        <style type="text/css">
            
        </style>
        
    </head>

<body>

<div class="page">
  <div class="container">
  
    <div class="left">
      <div class="sign_up">Sign up</div>
      <div class="eula">Por favor, introduce tus datos personales.</div>
   	  <div class="zoom">  
        <div class="eula"><a href="index.jsp">¿Ya tienes una cuenta? Inicia sesión</a></div>
      </div>
    </div>
    
    <div class="right">
    
        <!-- Script para la animación del gradiente (añadir aparte) -->
    <!-- 
    <script>
var current = null;
document.querySelector('#Email').addEventListener('focus', function(e) {
  if (current) current.pause();
  current = anime({
    targets: 'path',
    strokeDashoffset: {
      value: 0,
      duration: 700,
      easing: 'easeOutQuart'
    },
    strokeDasharray: {
      value: '240 1386',
      duration: 700,
      easing: 'easeOutQuart'
    }
  });
});
document.querySelector('#Usuario').addEventListener('focus', function(e) {
  if (current) current.pause();
  current = anime({
    targets: 'path',
    strokeDashoffset: {
      value: -336,
      duration: 700,
      easing: 'easeOutQuart'
    },
    strokeDasharray: {
      value: '240 1386',
      duration: 700,
      easing: 'easeOutQuart'
    }
  });
});
document.querySelector('#Iniciar sesión').addEventListener('focus', function(e) {
  if (current) current.pause();
  current = anime({
    targets: 'path',
    strokeDashoffset: {
      value: -730,
      duration: 700,
      easing: 'easeOutQuart'
    },
    strokeDasharray: {
      value: '530 1386',
      duration: 700,
      easing: 'easeOutQuart'
    }
  });
});
</script>
-->
    
      <svg viewBox="0 0 320 300">
        <defs>
          <linearGradient
                          inkscape:collect="always"
                          id="linearGradient"
                          x1="13"
                          y1="193.49992"
                          x2="307"
                          y2="193.49992"
                          gradientUnits="userSpaceOnUse">
            <stop
                  style="stop-color:#ff00ff;"
                  offset="0"
                  id="stop876" />
            <stop
                  style="stop-color:#ff0000;"
                  offset="1"
                  id="stop878" />
          </linearGradient>
        </defs>
        <path d="m 40,120.00016 239.99984,-3.2e-4 c 0,0 24.99263,0.79932 25.00016,35.00016 0.008,34.20084 -25.00016,35 -25.00016,35 h -239.99984 c 0,-0.0205 -25,4.01348 -25,38.5 0,34.48652 25,38.5 25,38.5 h 215 c 0,0 20,-0.99604 20,-25 0,-24.00396 -20,-25 -20,-25 h -190 c 0,0 -20,1.71033 -20,25 0,24.00396 20,25 20,25 h 168.57143" />
      </svg>
      
      
      <div class="form" method="GET">
      <form action="ServletAcceso">
      	<div class="columnas">
        	<label for="name">Nombre</label>
        	<input type="text" id="nombre" name="nombre">
        	
        	<label for="primer_apellido">Primer apellido</label>
        	<input type="text" id="primer_apellido" name="primer_apellido">
        	
        	<label for="segundo_apellido">Segundo apellido</label>
        	<input type="text" id="segundo_apellido" name="segundo_apellido">
        	
        	<label for="user">Usuario</label>
        	<input type="text" id="nick" name="nick">
        	
        	<label for="email">Email</label>
        	<input type="email" id="correo" name="correo">
        	
      		<div class="zoom">
        		<input type="submit" name="accion" value="Registrarse">
      		</div> 
        </div>
      </form>
      </div>
      
      
    </div>
  </div>
</div>
</body>
</html>