<!-- CREATED BY MARK MALDONADO -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.requestURI }" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Fehlerseite</title>
</head>
<body>

<!-- Kopfzeile -->
<header>
	<img src="../img/logo.png" alt="Logobild" width="35" height="42">
	<h1>Fehlerausgabe</h1>
	<a href="../hauptseiteservlet">zurück zur Hauptseite</a>
</header>

<!-- Fehlerausgabe -->
<section>
	<h2>Es ist ein Fehler aufgetreten!</h2>
	<br>Bitte benachrichtigen Sie den Web-Administrator unter <b>admin@shop-war.org</b>.
	<br>Die Fehlermeldung lautet: ${pageContext.exception}
	<p><b>Stack trace:</b>
		<c:forEach var = "trace" items = "${pageContext.exception.stackTrace}">
			<br>${trace}
		</c:forEach>
	</p>
</section>
	
<!-- Footer -->
<%@ include file="../jspfs/footer.jspf" %>

</body>
</html>