<!-- CREATED BY Mark Maldonado -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page errorPage="../fehlermeldung/fehlerausgabe.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../jspfs/head.jspf" %>
	<title>Einkaufswagen</title>
</head>
<body>

<!-- Kopfzeile -->
<nav>
<ul>
	<li><img src="../img/logo.png" alt="Logobild" width="35" height="42"></li>
	<li><h1>Einkaufswagen</h1></li>
	<li><a href="../abmeldenservlet">abmelden</a></li>
	<li><a href="../hauptseiteservlet">zurück zur Hauptseite</a></li>
</ul>
</nav>


<main>
	<!-- falls Einkaufswagen leer -->
	<c:choose>
		<c:when test="${empty alleArtikel }">
			<h2>Ihr Einkaufswagen ist leer.</h2>
		</c:when>
		
		<c:otherwise>
		<!-- Artikel -->
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

					<!-- Artikel entfernen Knopf -->
					<div>
						<a href="../einkaufswagenentfernenservlet?id=${artikel.id }&name=${artikel.name }">Artikel entfernen</a>
					</div>

					<!-- Menge ändern -->
					<form method="get" action="../einkaufswagenaktualisierenservlet">
						<fieldset><legend>Menge aktualisieren</legend>
							<div>
								<!-- Übergabewerte in Form schreiben -->
								<input type="hidden" name="artikelId" value="${artikel.id }">
								<!-- Input Wert -->
							  <label for="menge_id">Menge:</label>
							  <input type="number" name="menge" id="menge_id" value="${artikel.menge }" min="1">
							  <button class="yellow" type="submit">Aktualisieren</button>
							</div>
						</fieldset>
					</form>
				</div>
			</c:forEach>
		</section>
		
		<!-- Gesamtsumme -->
		<section class="gesamtsumme">
			<p><b>Gesamtsumme: </b>${gesamtsumme }</p>
		</section>
		
		<!-- Kaufen Knopf -->
		<section class="kaufen">
			<a href="../einkaufswagenuebersichtservlet">Weiter</a>
		</section>
		
		</c:otherwise>
	</c:choose>
</main>

<!-- Footer -->
<%@ include file="../jspfs/footer.jspf" %>

</body>
</html>