// CREATED BY MARK MALDONADO

package de.thi.shop.user.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import de.thi.shop.user.beans.RegistrierenBean;

@WebServlet("/registrierenservlet")
public class RegistrierenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// get Datasource
	@Resource(lookup="java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;
	// Attribut für doppelte Email
	private boolean mailProblem = false;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Format der zu lesenden Formulardaten
		request.setCharacterEncoding("UTF-8");
		
		//Daten in Bean schreiben zur Weitergabe an JSP
		RegistrierenBean form = new RegistrierenBean();
		form.setMail(request.getParameter("mail"));
		form.setPasswort(request.getParameter("passwort"));
		form.setOrt(request.getParameter("ort"));
		form.setStrasse(request.getParameter("strasse"));
		form.setHausnummer(Long.valueOf(request.getParameter("hausnummer")));
		form.setIban(request.getParameter("iban"));
		form.setIsAdmin(false);
		
		// Daten in Datenbank schreiben
		persist(form);
		
		//Test ob EMail doppelt gefunden wurede
		if (mailProblem == true) {
			// Attribut zurrücksetzen
			mailProblem = false;
			// Fehler Seite aufrufen
			final RequestDispatcher dispatcher = request.getRequestDispatcher("/eingang/registrierung_fehler.jsp");
			dispatcher.forward(request, response);
		} else {		
		// Redirect weil Daten in die Datenbank geschrieben wird
		response.sendRedirect("eingang/registrierung_erfolgreich.jsp");
		}
	}
	
	private void persist(RegistrierenBean form) throws ServletException {		
		// 1 ÜBERPRÜFEN OB MAIL IN DER DATENBANK BEREITS VORHANDEN
		try (Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"SELECT * FROM user WHERE mail LIKE ?")) {

			// PreparedStatement Grundgerüst befüllen
			pstmt.setString(1, form.getMail());
			
			// Ergebnis mit angegebener Email vergleichen
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs != null && rs.next()) {
					if (rs.getString("mail").contentEquals(form.getMail()))	{
						// persist Methode verlassen und Fehlerseite aufrufen
						mailProblem = true;
						return;
					}
				}
			}
			
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		
		// 2 DATEN IN DATENBANK SCHREIBEN
		// PreparedStatement für Grundgerüst 
		// (Ressourcen in runden Klammern nach try um sie im Nachhinein nicht wieder schließen zu müssen)
		try (Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"INSERT INTO user (mail, passwort, ort, strasse, hausnummer, iban, isAdmin) VALUES (?,?,?,?,?,?,?)")) {

			// PreparedStatement Grundgerüst befüllen
			pstmt.setString(1, form.getMail());
			pstmt.setString(2, form.getPasswort());
			pstmt.setString(3, form.getOrt());
			pstmt.setString(4, form.getStrasse());
			pstmt.setLong(5, form.getHausnummer());
			pstmt.setString(6, form.getIban());
			pstmt.setBoolean(7, form.getIsAdmin());
			pstmt.executeUpdate();
			
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}
}	