<!-- CREATED BY MARK MALDONADO -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page errorPage="../fehlermeldung/fehlerausgabe.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.requestURI }" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Artikel erfolgreich entfernt</title>
</head>
<body>

<!-- Kopfzeile -->
<header>
	<img src="../img/logo.png" alt="Logobild" width="35" height="42">
	<h1>Einkaufswagen</h1>
	<a href="einkaufswagen.jsp">zurück</a>
</header>

<!-- Text -->
<section>
	<h2>Artikel erfolgreich entfernt!</h2>
</section>

<!-- Footer -->
<%@ include file="../jspfs/footer.jspf" %>

</body>
</html>