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
	<title>Einkaufswagen</title>
</head>
<body>

<!-- Kopfzeile -->
<nav>
	<img src="../img/logo.png" alt="Logobild" width="35" height="42">
	<h1>Einkaufswagen</h1>
	<a href="../abmeldenservlet">abmelden</a>
	<a href="../einkaufswagenservlet">zurück zur Hauptseite</a>	
</nav>


<main>
<!-- Artikel -->
<section>
	<c:forEach var="artikel" items="${alleArtikel}" varStatus="status">
		<div>
			<!-- Bild -->
			<img src="../artikelbildladenservlet?id=${artikel.id }" alt="${artikel.bildName }" height="75" width="75">
			<!-- Information -->
			<div>
				<p><b>Name: </b>${artikel.name }</p>
				<p><b>Kategorie: </b>${artikel.kategorieName }</p>
				<p><b>Preis: </b>${artikel.preisString }</p>
				<p><b>Menge: </b>${artikel.menge }</p>
			</div>
			<!-- Artikel entfernen Knopf -->
			<div>
				<a href="../einkaufswagenentfernenservlet?id=${artikel.id }&name=${artikel.name }">Artikel entfernen</a>
			</div>
		</div>
	</c:forEach>
</section>

<!-- Gesamtsumme -->
<section>
	<p><b>Gesamtsumme: </b>${form.summe }</p>
</section>

<!-- Kaufen Knopf -->
<section>
	<a href="../einkaufswagenkaufenservlet">Kaufen</a>
</section>
</main>

<!-- Footer -->
<%@ include file="../jspfs/footer.jspf" %>

</body>
</html>