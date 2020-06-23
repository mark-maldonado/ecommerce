// CREATED BY SEYIT ARAR //
"use sctrict";

// Wenn DOM Elemente geladen sind //
document.addEventListener("DOMContentLoaded", init);

function init() {
	var form = document.getElementById("form_registrierung");
	form.addEventListener("submit", checkForm);
	form.addEventListener("reset", confirmReset);
}

// Check Passwort Wiederholen //
function checkForm(evt) {
	// EingabeElemente zwischenspeichern
	var pw1 = document.getElementById("passwort_id");
	var pw2 = document.getElementById("repeat_passwort_id");
	var fehlermeldung = document.getElementById("fehlermeldung");
	
	// Prüfen ob Passwort den Anforderungen entspricht
	if(pw1.value.length < 6 || !/\d/.test(pw1.value)) {
		fehlermeldung.innerHTML = "Bitte benutzen Sie ein Passwort, das 6 Zeichen Lang und mindestens eine Zahl beinhalten";
		pw1.classList.add("fehlerinput");
		evt.preventDefault();
	}
	
	// Prüfen ob Passwort richtig wiederholt worden ist
	if(pw1.value != pw2.value) {
		fehlermeldung.innerHTML = "Bitte überprüfen Sie die Eingabe 'Passwort wiederholen'";
		pw2.classList.add("fehlerinput");
		evt.preventDefault();
	}
}

// Reset bestätigen //
function confirmReset(evt) {
	var reallyReset = confirm("Formular wirklich zurücksetzen?");
	if(!reallyReset) {
		evt.preventDefault();
	}
}