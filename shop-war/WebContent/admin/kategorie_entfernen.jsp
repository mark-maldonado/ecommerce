<!-- CREATED BY SEYIT ARAR -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page errorPage="../fehlermeldung/fehlerausgabe.jsp" %>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.requestURI }" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Kategorie Entfernen</title>
</head>
<body>

<!-- Kopfzeile -->
<header>
	<img src="../img/logo.png" alt="Logobild" width="35" height="42">
	<h1><!-- Name der rechts neben dem Logo steht --></h1>
	<a href="../hauptseiteservlet">zurück</a>
</header>

<!-- Text -->
<form method="post" action="../kategorieloeschenservlet">
	<section>
		<label for="kategorieName_id">Kategorie:</label>
			<select name="kategorieName" id="kategorieName_id">
				<c:forEach var="currentKategorie" items="${kategorien}" varStatus="status">
					<option>${currentKategorie.kategorieName}</option>
				</c:forEach>
			</select>
	</section>
	<section>
		<button type="submit">Entfernen</button>
	</section>
</form>

<!-- Footer -->
<%@ include file="../jspfs/footer.jspf" %>

</body>
</html>
