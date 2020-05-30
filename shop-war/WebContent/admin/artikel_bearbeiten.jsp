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
	<title>Artikel bearbeiten</title>
</head>
<body>

<!-- Kopfzeile -->
<nav>
<ul>
	<li><img src="../img/logo.png" alt="Logobild" width="35" height="42"></li>
	<li><h1>Artikel bearbeiten</h1></li>
	<li><a href="../hauptseiteservlet">zurück</a></li>
</ul>
</nav>

<!-- Text -->
<main>
	<form method="post" action="../artikelaktualisierenservlet" enctype="multipart/form-data">
	<fieldset><legend>Artikel bearbeiten</legend>			
			<div>
				<img src="../artikelbildladenservlet?id=${artikelBean.id}" alt="${artikelBean.bildName}" height="115" width="115">
				<label for="artikelBeild_id">Bild:</label>
				<input class="bild" type="file" name="artikelBild" id="artikelBild_id" accept="image/*">
			</div>
			<div>
				<label for="artikelId_id">ID:</label>
				<input type="number" name="artikelId"  id ="artikelId_id" value="${artikelBean.id}" readonly>
			</div>
			<div>
				 <label for="artikelName_id">Name:</label>
				 <input type="text" name="artikelName" id="artikelName_id" value="${artikelBean.name}" required>
			</div>
			<div class="selectdiv">
				<label for="artikelKategorie_id">Kategorie:</label>
			 	<select name="artikelKategorie" id="artikelKategorie_id" >
			 		<option value="${artikelBean.kategorieName}" selected>${artikelBean.kategorieName}</option>
						<c:forEach var="currentKategorie" items="${kategorien}" varStatus="status">
							<option value="${currentKategorie.kategorieName}">${currentKategorie.kategorieName}</option>
						</c:forEach>
				</select>
			</div>
			<div>
				<label for="artikelPreis_id">Preis:</label>
				<input type="number" name="artikelPreis" id="artikelPreis_id" value="${artikelBean.preis}" required>
			</div>
			<div class="buttons">
				<a href="../artikelentfernenservlet?artikelId=${artikelBean.id}">Entfernen</a>
				<button type="submit" >Aktualisieren</button>
				
			</div>	 
		</fieldset>
	</form>
</main>
	
<!-- Footer -->
<%@ include file="../jspfs/footer.jspf" %>

</body>
</html>