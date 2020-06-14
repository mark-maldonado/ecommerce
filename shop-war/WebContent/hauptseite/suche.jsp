<!-- CREATED BY Mark Maldonado -->

<%@ page language="java" contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core"%>



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