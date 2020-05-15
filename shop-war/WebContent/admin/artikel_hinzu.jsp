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
<header>
	<img src="../img/logo.png" alt="Logobild" width="35" height="42">
	<h1><!-- Name der rechts neben dem Logo steht --></h1>
	<a href="../hauptseiteservlet">zurück</a>
</header>

<!-- Text -->
<form method="post" action="../artikelhinzufuegenservlet" enctype="multipart/form-data">
<section>
	<table>
		<tr>
			<th><input type="file" name="artikelBild" id="artikelBild_id" accept="image/*">
			</th>
			<th>
			<p><label for="artikelName_id">Name:</label> 
				<input type="text" name="artikelName" id="artikelName_id" placeholder="Artikel Name" required></p>
			<p><label for="artikelKategorie_id">Kategorie:</label>
				<select name="artikelKategorie" id="artikelKategorie_id">
						<c:forEach var="currentKategorie" items="${kategorien}" varStatus="status">
							<option>${currentKategorie.kategorieName}</option>
						</c:forEach>
					</select></p>
			<p><label for="preis_id">Passwort:</label>
				<input type="number" name="artikelPreis" id="preis_id" placeholder="Artikel Preis" required></p>
		</tr>
	</table>
</section>
<section>
	<input type="submit" value ="Hinzufügen">
</section>
</form>
<!-- Footer -->
<%@ include file="../jspfs/footer.jspf" %>

</body>
</html>