<!-- CREATED BY Mark Maldonado -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page errorPage="../fehlermeldung/fehlerausgabe.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<base href="${pageContext.request.requestURI }" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Hauptseite</title>
</head>
<body>

<!-- Kopfzeile -->
<nav>
	<img src="../img/logo.png" alt="Logobild" width="35" height="42">
	<h1>Hauptseite</h1>
	<a href="../abmeldenservlet">abmelden</a>
	<a href="../einkaufswagenservlet">zum Einkaufswagen</a>	
	
	<!-- testen ob Admin angemeldet ist -->
	<c:if test="${sessionScope.isAdmin }">
		<a href="../kategorieentfernenservlet">Kategorie entfernen</a>
		<a href="../admin/kategorie_hinzu.jsp">Kategorie hinzufügen</a>
		<a href="../artikelhinzuservlet">Artikel hinzufügen</a>
	</c:if>
</nav>

<main>
<!-- Suchfunktion -->
<section id="suchfunktion">
	<form method="get" action="../hauptseiteservlet">
		<fieldset><legend>Artikel Suchen</legend>
			<div>
			  <label for="suche_id">Artikel:</label>
			  <input type="text" name="suche" id="suche_id" placeholder="Suchtext">
			  <button name="submit" type="submit">Suchen</button>
			</div>
		</fieldset>
	</form>
</section>

<!-- Artikel -->
<section class="artikel">
	<c:forEach var="artikel" items="${alleArtikel}" varStatus="status">
		<div>
			<!-- Bild -->
			<img src="../artikelbildladenservlet?id=${artikel.id }" alt="${artikel.bildName }" height="75" width="75">
			<!-- Information -->
			<div>
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
				<fieldset><legend>Artikel Suchen</legend>
					<div>
						<!-- Übergabewerte in Form schreiben -->
						<input type="hidden" name="artikelId" value="${artikel.id }">
						<input type="hidden" name="name" value="${artikel.name }">
						<input type="hidden" name="preis" value="${artikel.preis }">
						<input type="hidden" name="kategorieId" value="${artikel.kategorieId }">
						<input type="hidden" name="kategorieName" value="${artikel.kategorieName }">
						<!-- Input Wert -->
					  <label for="menge_id">Menge:</label>
					  <input type="number" name="menge" id="menge_id" value="1">
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
</main>

<!-- Footer -->
<%@ include file="../jspfs/footer.jspf" %>

</body>
</html>
