<!-- CREATED BY  Maldonado -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page errorPage="../fehlermeldung/fehlerausgabe.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<base href="${pageContext.request.requestURI }" />
	<link rel="stylesheet" type="text/css" href="../style/style.css" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Registrierung Fehler</title>
</head>
<body>

<!-- Kopfzeile -->
<nav>
<ul>
	<li><img src="../img/logo.png" alt="Logobild" width="35" height="42"></li>
	<li><h1>Registrierung Fehler</h1></li>
</ul>
</nav>

<!-- Text -->
<main>
	<h2>Leider war die Registrierung nicht möglich. Die angegebene E-Mail Adresse ist bereits registriert.</h2>
	<div class="buttons center">
		 <a href="login.html">Login</a>
		 <a href="registrierung.html">Registrieren</a>
	</div>
</main>

<!-- Footer -->
<%@ include file="../jspfs/footer.jspf" %>

</body>
</html>