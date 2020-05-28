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
	<link rel="stylesheet" type="text/css" href="../style/style.css" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Einkaufswagen Übersicht</title>
</head>
<body>

<!-- Kopfzeile -->
<nav>
<ul>
	<li><img src="../img/logo.png" alt="Logobild" width="35" height="42"></li>
	<li><h1>Einkaufswagen Übersicht</h1></li>
	<li><a href="../abmeldenservlet">abmelden</a></li>
	<li><a href="../einkaufswagenservlet">zurück zum Einkaufswagen</a></li>
</ul>
</nav>


<main>
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
				<p><b>Menge: </b>${artikel.menge }</p>
			</div>
		</div>
	</c:forEach>
</section>

<!-- Gesamtsumme -->
<section class="gesamtsumme">
	<p><b>Gesamtsumme: </b>${gesamtsumme }</p>
</section>

<!-- Kaufen Knopf -->
<section class="kaufen">
	<a href="../einkaufswagenkaufenservlet">Zahlungspflichtig Kaufen</a>
</section>
</main>

<!-- Footer -->
<%@ include file="../jspfs/footer.jspf" %>

</body>
</html>