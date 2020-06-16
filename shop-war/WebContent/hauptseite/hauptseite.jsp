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
	<form method="get" action="../hauptseiteservlet">
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
	<section class="artikel">
		<c:forEach var="artikel" items="${alleArtikel}" varStatus="status">
			<div>
				<!-- Bild -->
				<img src="../artikelbildladenservlet?id=${artikel.id }" alt="${artikel.bildName }" height="115" width="115">
				<!-- Information -->
				<div class="info">
					<p><b>Name: </b>${artikel.name }</p>
					<p><b>Kategorie: </b>${artikel.kategorieName }</p>
					<p><b>Preis: </b>${artikel.preisString }</p>
				</div>
				<!-- Artikel Bearbeiten Knopf -->
				<c:if test="${sessionScope.isAdmin }">
					<div>
						<a href="../artikelbearbeitenservlet?id=${artikel.id }&name=${artikel.name }&preis=${artikel.preis }&kategorie=${artikel.kategorieName }">Artikel bearbeiten</a>
					</div>
				</c:if>
				<!-- Anzahl in den Einkaufwagen -->
				<form method="get" action="../einkaufswagenhinzuservlet">
					<fieldset><legend>Artikel in den Einkaufswagen hinzufügen</legend>
						<div>
							<!-- Übergabewerte in Form schreiben -->
							<input type="hidden" name="artikelId" value="${artikel.id }">
							<!-- Input Wert -->
						  <label for="menge_id">Menge:</label>
						  <input type="number" name="menge" id="menge_id" value="1" min="1">
						  <button type="submit">In den Einkaufswagen</button>
						</div>
					</fieldset>
				</form>
			</div>
		</c:forEach>
		
		<!-- falls Suche leer -->
		<c:if test="${empty alleArtikel }">
			<h2>Es wurden keine Artikel für Ihren Suchbegriff gefunden.</h2>
		</c:if>
	</section>
</section>
</main>

<!-- Footer -->
<%@ include file="../jspfs/footer.jspf" %>

</body>
</html>
