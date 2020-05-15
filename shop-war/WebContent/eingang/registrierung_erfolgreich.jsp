<!-- CREATED BY SEYIT ARAR -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page errorPage="../fehlermeldung/fehlerausgabe.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.requestURI }" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Registrierung Erfolgreich!</title>
</head>
<body>

<!-- Kopfzeile -->
<header>
	<img src="../img/logo.png" alt="Logobild" width="35" height="42">
	<h1>erfolgreiche Registrierung</h1>
</header>

<!-- Text -->
<section>
	
	<h2>Ihre Registrierung war erfolgreich!</h2>
	<a href="login.html" title="zum_Login">Zurück zum Login</a></p>
</section>

<!-- Footer -->
<%@ include file="../jspfs/footer.jspf" %>

</body>
</html>