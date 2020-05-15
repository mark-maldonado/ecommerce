<!-- CREATED BY Mark Maldonado -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page errorPage="../fehlermeldung/fehlerausgabe.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.requestURI }" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Hauptseite</title>
</head>
<body>

<!-- Kopfzeile -->
<header>
	<img src="../img/logo.png" alt="Logobild" width="35" height="42">
	<h1>Hauptseite</h1>
	<a href="../abmeldenservlet">abmelden</a>
	<a href="../einkaufswagenservlet">zum Einkaufswagen</a>	
	
	<!-- testen ob Admin angemeldet ist -->
	<c:if test="${sessionScope.isAdmin }">
		<a href="../admin/kategorie_entfernen.jsp">Kategorie entfernen</a>
		<a href="../admin/kategorie_hinzu.jsp">Kategorie hinzufügen</a>
		<a href="../artikelhinzuservlet">Artikel hinzufügen</a>
	</c:if>
</header>

<!-- Suchfunktion -->
<section>
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
<section>
	<c:forEach var="artikel" items="${alleArtikel}" varStatus="status">
		<div>
			<!-- Bild -->
			<img src="../artikelbildladenservlet?id=${artikel.id }" alt="${artikel.bildName }" height="50" width="50">
			<!-- Information -->
			<div>
				<p><b>Name: </b>${artikel.name }</p>
				<p><b>Kategorie: </b>${artikel.kategorieName }</p>
				<p><b>Preis: </b>${artikel.preisString }</p>
			</div>
			<!-- Artikel Bearbeiten Knopf -->
			<c:if test="${sessionScope.isAdmin }">
				<a href="../atikelbearbeitenservlet?id=${artikel.id }">Artikel bearbeiten</a>
			</c:if>
			<!-- Anzahl in den Einkaufwagen -->
			<form method="get" action="../einkaufswagenhinzuservlet?artikelId=${artikel.id }name=${artikel.name },preis=${artikel.preis },bildName=${artikel.bildName },kategorieId=${artikel.kategorieId },kategorieName=${artikel.kategorieName }">
				<fieldset><legend>Artikel Suchen</legend>
					<div>
					  <label for="menge_id">Menge:</label>
					  <input type="number" name="menge" id="menge_id" value="1">
					  <button name="submit" type="submit">In den Einkaufswagen</button>
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

<!-- Footer -->
<%@ include file="../jspfs/footer.jspf" %>

</body>
</html>
