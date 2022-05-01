<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/Login.css">
</head>
<body>
	<div class="contenedor">
		<div class="envoltura">
			<div><h1>Login</h1></div>
			<form action="Login" method="POST">
			<div class="fila">
				<label for="usuario">Usuario:</label>
				<input type="text" name="usuario">
			</div>
			<div class="fila2">
				<label for="password">Contraseña:</label>
				<input type="password" name="password">
			</div>
			<div class="boton">
				<input type="submit" value="Enviar">
			</div>
			
			</form>
		</div>
	</div>
	
</body>
</html>