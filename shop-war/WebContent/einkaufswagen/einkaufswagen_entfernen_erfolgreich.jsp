<!-- CREATED BY MARK MALDONADO -->

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
	<title>Artikel erfolgreich entfernt</title>
</head>
<body>

<!-- Kopfzeile -->
<nav>
	<img src="../img/logo.png" alt="Logobild" width="35" height="42">
	<h1>Einkaufswagen</h1>
	<a href="../einkaufswagenservlet">zurück zum Einkaufswagen</a>
</nav>

<!-- Text -->
<main>
	<h2>Artikel wurde erfolgreich vom Einkaufswagen entfernt!</h2>
</main>

<!-- Footer -->
<%@ include file="../jspfs/footer.jspf" %>

</body>
</html>