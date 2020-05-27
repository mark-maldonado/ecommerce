<!-- CREATED BY SEYIT ARAR -->

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
	<title>Kategorie schon vorhanden</title>
</head>
<body>

<!-- Kopfzeile -->
<nav>
<ul>
	<li><img src="../img/logo.png" alt="Logobild" width="35" height="42"></li>
	<li><h1>Kategorie hinzufügen</h1></li>
	<li><a href="../hauptseiteservlet">zurück zur Hauptseite</a></li>
</ul>
</nav>

<!-- Text -->
<main>
	<h2>Die Kategorie ${form.kategorieName } ist schon vorhanden!</h2>
</main>

<!-- Footer -->
<%@ include file="../jspfs/footer.jspf" %>

</body>
</html>