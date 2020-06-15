// CREATED BY SEYIT ARAR //

"use sctrict";

//Quelle: https://stackoverflow.com/questions/4603289/how-to-detect-that-javascript-and-or-cookies-are-disabled

//Wenn DOM Elemente geladen sind //
document.addEventListener("DOMContentLoaded", checkCookie);

function checkCookie(){
    var cookieEnabled = navigator.cookieEnabled;
    if (!cookieEnabled){ 
        document.cookie = "testcookie";
        cookieEnabled = document.cookie.indexOf("testcookie")!=-1;
    }
    return cookieEnabled || showCookieFail();
}

function showCookieFail(){
	alert("Bitte aktivieren sie die Cookies!");
}

