<!-- CREATED BY MARK MALDONADO -->

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
	<title>Kategorie Hinzufügen erfolgreich</title>
</head>
<body>

<!-- Kopfzeile -->
<nav>
	<img src="../img/logo.png" alt="Logobild" width="35" height="42">
	<h1>Kategorie hinzufügen</h1>
	<a href="../hauptseiteservlet">zurück zur Hauptseite</a>
</nav>

<!-- Text -->
<main>
	<h2>Die Kategorie ${kategorieBean.kategorieName } wurde erfolgreich hinzugefügt!</h2>
</main>

<!-- Footer -->
<%@ include file="../jspfs/footer.jspf" %>

</body>
</html>