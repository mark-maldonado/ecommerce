<!-- CREATED BY Mark Maldonado -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page errorPage="../fehlermeldung/fehlerausgabe.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../jspfs/head.jspf" %>
	<script type="text/javascript" src="../script/suche.js"></script>
	<title>Hauptseite</title>
</head>
<body>

<!-- Kopfzeile -->
<nav>
<ul>
	<li><img src="../img/logo.png" alt="Logobild" width="35" height="42"></li>
	<li><h1>Hauptseite</h1></li>
	<li><a href="../abmeldenservlet">abmelden</a></li>
	<li><a href="../einkaufswagenservlet">zum Einkaufswagen</a></li>
	
	<!-- testen ob Admin angemeldet ist -->
	<c:if test="${sessionScope.isAdmin }">
		<li><a href="../kategorieentfernenservlet">Kategorie entfernen</a></li>
		<li><a href="../admin/kategorie_hinzu.jsp">Kategorie hinzufügen</a></li>
		<li><a href="../artikelhinzuservlet">Artikel hinzufügen</a></li>
	</c:if>
</ul>
</nav>

<main>
<!-- Kategorien -->
<section id="kategorien">
	<ul>
		<c:forEach var="kategorie" items="${alleKategorien}" varStatus="status">
			<li>
				<a href="../hauptseiteservlet?suche=${kategorie.kategorieName}">
				<img src="../kategoriebildladenservlet?id=${kategorie.id}" alt="${kategorie.kategorieName}" height="25" width="25">
				${kategorie.kategorieName}</a>
			</li>
		</c:forEach>
	</ul>
</section>

<!-- Suchfunktion -->
<section id="suchfunktion">
	<form>
		<fieldset><legend>Artikel Suchen</legend>
			<!-- Suche durch Text -->
			<div>
			  <label for="suche_id">Artikel:</label>
			  <input type="text" name="suche" id="suche_id" placeholder="Suchtext">
			</div>
		</fieldset>
	</form>
</section>

<!-- Artikel -->
<section id="artikelListe">

</section>
</main>

<!-- Footer -->
<%@ include file="../jspfs/footer.jspf" %>

</body>
</html>
