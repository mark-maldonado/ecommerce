<!-- CREATED BY SEYIT ARAR -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page errorPage="../fehlermeldung/fehlerausgabe.jsp" %>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="../style/style.css" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<base href="${pageContext.request.requestURI }" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Kategorie entfernen</title>
</head>
<body>

<!-- Kopfzeile -->
<nav>
	<img src="../img/logo.png" alt="Logobild" width="35" height="42">
	<h1>Kategorie entfernen</h1>
	<a href="../hauptseiteservlet">zurück</a>
</nav>

<!-- Text -->
<main>
	<form method="get" action="../kategorieloeschenservlet">
		<fieldset><legend>Kategorie entfernen</legend>
			<div>
				<label for="kategorieName_id">Kategorie:</label>
				<select name="kategorieName" id="kategorieName_id">
					<c:forEach var="currentKategorie" items="${kategorien}" varStatus="status">
						<option>${currentKategorie.kategorieName}</option>
					</c:forEach>
				</select>
			</div>
			<div>
				<button type="submit">Entfernen</button>
			</div>
		</fieldset>
	</form>
</main>

<!-- Footer -->
<%@ include file="../jspfs/footer.jspf" %>

</body>
</html>
