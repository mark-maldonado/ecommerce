<!-- CREATED BY SEYIT ARAR -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page errorPage="../fehlermeldung/fehlerausgabe.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../jspfs/head.jspf" %>
	<title>Registrierung Erfolgreich!</title>
</head>
<body>

<!-- Kopfzeile -->
<nav>
<ul>
	<li><img src="../img/logo.png" alt="Logobild" width="35" height="42"></li>
	<li><h1>Registrierung erfolgreich</h1></li>
</ul>
</nav>

<!-- Text -->
<main>
	<h2>Ihre Registrierung war erfolgreich!</h2>
	<div class="button center">
		<a href="login.html" title="zum_Login">Zurück zum Login</a>
	</div>
</main>

<!-- Footer -->
<%@ include file="../jspfs/footer.jspf" %>

</body>
</html>