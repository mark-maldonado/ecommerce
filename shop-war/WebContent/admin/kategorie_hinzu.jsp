<!-- CREATED BY SEYIT ARAR -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page errorPage="../fehlermeldung/fehlerausgabe.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.requestURI }" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Kategorie Hinzufügen</title>
</head>
<body>

<!-- Kopfzeile -->
<header>
	<img src="../img/logo.png" alt="Logobild" width="35" height="42">
	<h1><!-- Name der rechts neben dem Logo steht --></h1>
	<a href="../hauptseiteservlet">zurück</a>
</header>

<!-- Text -->
<form method="post" action="../kategoriehinzufuegenservlet">
<section>
	<label for="kategorieId">Kategorie-Name:</label>
	<input type="text" name="kategorieName" id="kategorieId" placeholder="Kategorie-Name" required></th>
</section>
<section>
	<button type="submit">Hinzufügen</button>
</section>
</form>

<!-- Footer -->
<%@ include file="../jspfs/footer.jspf" %>

</body>
</html>