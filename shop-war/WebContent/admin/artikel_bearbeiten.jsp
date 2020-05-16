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
	<title>Artikel Bearbeiten</title>
</head>
<body>

<!-- Kopfzeile -->
<header>
	<img src="../img/logo.png" alt="Logobild" width="35" height="42">
	<h1><!-- Name der rechts neben dem Logo steht --></h1>
	<a href="../hauptseiteservlet">zurück</a>
</header>

<!-- Text -->
<main>
<form method="post" action="../artikelaktualisierenaervlet" enctype="multipart/form-data">
<section>
	<table>
		<tr>
			<th><p><img src="../artikelbildladenservlet?id=${artikelBean.id}" alt="${artikelBean.bildName}" height="50" width="50"></p>
			<p><input type="file" name="artikelBild" id="artikelBild_id" accept="image/*"></p>
			<th>
				<label for="artikelId_id">ID:</label>
					<input type="number" name="artikelId"  id ="artikelId_id" value="${artikelBean.id}" readonly>
				 <label for="artikelName_id">Name:</label>
				 	<input type="text" name="artikelName" id="artikelName_id" value="${artikelBean.name}" required>
				 <p><label for="artikelKategorie_id">Kategorie:</label>
				 	<select name="artikelKategorie" id="artikelKategorie_id" >
				 		<option>${artikelKategorie}</option>
							<c:forEach var="currentKategorie" items="${kategorien}" varStatus="status">
								<option>${currentKategorie.kategorieName}</option>
							</c:forEach>
					</select></p>
				<p><label for="artikelPreis_id">Preis:</label>
					<input type="number" name="artikelPreis" id="artikelPreis_id" value="${artikelBean.preis}" required></p>
			</th>
			<th>
				<a href="../artikelentfernenservlet?artikelId=${artikelBean.id}">Entfernen</a>
				<button type="submit" >Aktualisieren</button>
				
			</th>	 
		</tr>
	</table>
</section>
</form>
	
<!-- Footer -->
<%@ include file="../jspfs/footer.jspf" %>
</main>

</body>
</html>