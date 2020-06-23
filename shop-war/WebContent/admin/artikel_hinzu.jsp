<!-- CREATED BY SEYIT ARAR -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page errorPage="../fehlermeldung/fehlerausgabe.jsp" %>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../jspfs/head.jspf" %>
	<title>Artikel Hinzufügen</title>
</head>
<body>

<!-- Kopfzeile -->
<nav>
<ul>
	<li><img src="../img/logo.png" alt="Logobild" width="35" height="42"></li>
	<li><h1>Artikel hinzufügen</h1></li>
	<li><a href="../hauptseiteservlet">zurück</a></li>
</ul>
</nav>

<!-- Text -->
<main>
	<form method="post" action="../artikelhinzufuegenservlet" enctype="multipart/form-data">
		<fieldset><legend>Artikel Hinzufügen</legend>
		<div>
			<label for="artikelBild_id">Bild:</label>
			<input class="bild" type="file" name="artikelBild" id="artikelBild_id" accept="image/*" required>
		</div>
		<div>
			<label for="artikelName_id">Name:</label> 
			<input type="text" name="artikelName" id="artikelName_id" placeholder="Artikel Name" required>
		</div>
		<div class="selectdiv">
			<label for="artikelKategorie_id">Kategorie:</label>
			<select name="artikelKategorie" id="artikelKategorie_id" required>
				<c:forEach var="currentKategorie" items="${kategorien}" varStatus="status">
					<option>${currentKategorie.kategorieName}</option>
				</c:forEach>
			</select>
		</div>
		<div>
			<label for="preis_id">Preis:</label>
			<input type="number" step="0.01" name="artikelPreis" id="preis_id" placeholder="Preis in Euro" required>
		</div>
		<div class="buttons">
			<button type="submit">Hinzufügen</button>
		</div>
		</fieldset>
	</form>
</main>

<!-- Footer -->
<%@ include file="../jspfs/footer.jspf" %>

</body>
</html>