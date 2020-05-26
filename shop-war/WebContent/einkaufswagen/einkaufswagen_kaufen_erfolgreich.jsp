<!-- CREATED BY SEYIT ARAR -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page errorPage="../fehlermeldung/fehlerausgabe.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="../style/style.css" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<base href="${pageContext.request.requestURI }" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Danke für Ihren Einkauf!</title>
</head>
<body>

<!-- Kopfzeile -->
<nav>
	<img src="../img/logo.png" alt="Logobild" width="35" height="42">
	<h1>Einkaufswagen</h1>
	<a href="../hauptseiteservlet">zurück zur Hauptseite</a>
</nav>

<!-- Text -->
<main>
	<h2>Danke für Ihren Einkauf!</h2>
    <h3>Ihre Bestellung wird Ihnen so schnell wie möglich zugesandt.</h3>
</main>

<!-- Footer -->
<%@ include file="../jspfs/footer.jspf" %>

</body>
</html>