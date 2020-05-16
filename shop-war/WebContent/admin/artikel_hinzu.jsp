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
	<title>Artikel Hinzufügen</title>
</head>
<body>

<!-- Kopfzeile -->
<nav>
	<img src="../img/logo.png" alt="Logobild" width="35" height="42">
	<h1><!-- Name der rechts neben dem Logo steht --></h1>
	<a href="../hauptseiteservlet">zurück</a>
</nav>

<!-- Text -->
<main>
	<form method="post" action="../artikelhinzufuegenservlet" enctype="multipart/form-data">
		<fieldset><legend>Artikel Hinzufügen</legend>
		<div>
			<label for="artikelBild_id">Bild:</label>
			<input type="file" name="artikelBild" id="artikelBild_id" accept="image/*">
		</div>
		<div>
			<label for="artikelName_id">Name:</label> 
			<input type="text" name="artikelName" id="artikelName_id" placeholder="Artikel Name" required>
		</div>
		<div>
			<label for="artikelKategorie_id">Kategorie:</label>
			<select name="artikelKategorie" id="artikelKategorie_id">
				<c:forEach var="currentKategorie" items="${kategorien}" varStatus="status">
					<option>${currentKategorie.kategorieName}</option>
				</c:forEach>
			</select>
		</div>
		<div>
			<label for="preis_id">Preis:</label>
			<input type="number" name="artikelPreis" id="preis_id" placeholder="Artikel Preis in Cents" required>
		</div>
		<div>
			<button type="submit">Hinzufügen</button>
		</div>
		</fieldset>
	</form>
</main>

<!-- Footer -->
<%@ include file="../jspfs/footer.jspf" %>

</body>
</html>