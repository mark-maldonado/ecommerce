// CREATED BY MARK MALDONADO //
"use sctrict";

// Wenn DOM Elemente geladen sind //
document.addEventListener("DOMContentLoaded", init);

function init() {
	// EventListener bei jedem Tastendruck
	document.getElementById("suche_id").addEventListener("keyup", search);
}

function search() {
	// SuchString speichern
	var search = document.getElementById("suche_id").value;
	
	// SuchURL erstellen
	var searchURL = "../hauptseitesuchenservlet";
	if (search != null && search.length>0)
		searchURL += "?suche=" + encodeURIComponent(search);
	
	xmlhttp = new XMLHttpRequest();
	
	// addEventListener new since XHR 2
	xmlhttp.addEventListener("load",function() {
		document.getElementById("artikelListe").innerHTML = xmlhttp.responseText;
	});
	
	// Suche an Servlet senden
	xmlhttp.open("GET", searchURL, true);
	xmlhttp.send();
}