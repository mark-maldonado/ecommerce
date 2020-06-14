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
	var pw1 = document.getElementById("passwort_id");
	var pw2 = document.getElementById("repeat_passwort_id");
	var fehlermeldung = document.getElementById("fehlermeldung");
	
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