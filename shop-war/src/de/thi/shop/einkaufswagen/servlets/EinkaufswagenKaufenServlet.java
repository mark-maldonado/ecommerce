// CREATED BY MARK MALDOANDO

package de.thi.shop.einkaufswagen.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import de.thi.shop.user.beans.UserBean;

@WebServlet("/einkaufswagenkaufenservlet")
public class EinkaufswagenKaufenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// get Datasource
	@Resource(lookup="java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Format der zu lesenden Formulardaten
		request.setCharacterEncoding("UTF-8");
				
		// Variable für idBestellung
		Long idBestellung = null;
		
		// Session Bean erhalten
		HttpSession session = request.getSession(false);
		UserBean userBean = (UserBean) session.getAttribute("userBean");
		
		// 1 Bestellung erstellen
		// PreparedStatement für Grundgerüst 
		// (Ressourcen in runden Klammern nach try um sie im Nachhinein nicht wieder schließen zu müssen)
		try (Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"INSERT INTO bestellung (idKunde) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
			
			// PreparedStatement Grundgerüst befüllen
			pstmt.setLong(1, userBean.getId());
			pstmt.executeUpdate();
			
			// Ergebnis id in idBestellung speichern
			try (ResultSet rs = pstmt.getGeneratedKeys()) {
				if(rs.next()) {
					idBestellung = Long.valueOf(rs.getLong(1));
				}
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		
		// 2 alle Artikel von EinkaufswagenPosition in BestellungPosition
		try (Connection con = ds.getConnection();
			// 2.1 alle Artikel aus dem Einkaufswagen bekommen
			PreparedStatement pstmt = con.prepareStatement(
					"SELECT artikelId, menge FROM einkaufswagenposition WHERE idUser = ?")) {
			
			// PreparedStatement Grundgerüst befüllen
			pstmt.setLong(1, userBean.getId());
			
			try(ResultSet rs = pstmt.executeQuery()) {
				System.out.println("hier");
				// Ergebnis id in idBestellung speichern
				while (rs.next()) {
					// 2.2 Artikel in bestellungPosition schreiben
					try (Connection conTwo = ds.getConnection();
					PreparedStatement pstmtTwo = conTwo.prepareStatement(
							"INSERT INTO bestellungposition (artikelId, menge, idBestellung) VALUES (?,?,?)")) {
					
					// PreparedStatement Grundgerüst befüllen
					pstmtTwo.setLong(1, rs.getLong(1));
					pstmtTwo.setLong(2, rs.getInt(2));
					pstmtTwo.setLong(3, idBestellung);
					pstmtTwo.executeUpdate();
					}
				}
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		
		// 3 alle Artikel von Einkaufswagen löschen
		try (Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"DELETE FROM einkaufswagenposition WHERE idUser = ?")) {
			
			// PreparedStatement Grundgerüst befüllen
			pstmt.setLong(1, userBean.getId());
			pstmt.executeUpdate();
			
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		
		// Redirect weil Daten in die Datenbank geschrieben wird
		response.sendRedirect("einkaufswagen/einkaufswagen_kaufen_erfolgreich.jsp");
	}
}	