<!-- CREATED BY  Maldonado -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page errorPage="../fehlermeldung/fehlerausgabe.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.requestURI }" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Registrierung Fehler</title>
</head>
<body>

<!-- Kopfzeile -->
<header>
	<img src="../img/logo.png" alt="Logobild" width="35" height="42">
</header>

<!-- Text -->
<section>
	<h1>Registrierungsfehler</h1>
	<h2>Leider war die Registrierung nicht möglich. Die angegebene E-Mail Adresse ist bereits registriert.</h2>
	<div>
		 <a href="login.html">Login</a>
		 <a href="registrierung.html">Registrieren</a>
	</div>
</section>

<!-- Footer -->
<%@ include file="../jspfs/footer.jspf" %>

</body>
</html>