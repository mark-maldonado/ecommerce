// CREATED BY MARK MALDOANDO

package de.thi.shop.einkaufswagen.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import de.thi.shop.einkaufswagen.beans.EinkaufswagenBean;
import de.thi.shop.user.beans.UserBean;

@WebServlet("/einkaufswagenhinzuservlet")
public class EinkaufswagenHinzuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// get Datasource
	@Resource(lookup="java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;
	// Attribut für bereits vorhandenen Artikel im Warenkorb
	private Long artikelVorhandenId = null;
	private int artikelVorhandenMenge = 0;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Format der zu lesenden Formulardaten
		request.setCharacterEncoding("UTF-8");
		
		// Session Bean erhalten
		HttpSession session = request.getSession(false);
		UserBean userBean = (UserBean) session.getAttribute("userBean");
		
		// Daten in Bean schreiben zur Weitergabe an JSP
		EinkaufswagenBean form = new EinkaufswagenBean();
		form.setArtikelId(Long.valueOf(request.getParameter("artikelId")));
		form.setName(request.getParameter("name"));
		form.setPreis(Integer.valueOf(request.getParameter("preis")));
		form.setBildName(request.getParameter("bildName"));
		form.setKategorieId(Long.valueOf(request.getParameter("kategorieId")));
		form.setMenge(Integer.valueOf(request.getParameter("menge")));
		form.setUserId(userBean.getId());
		
		// Daten in Datenbank schreiben
		persist(form);
		
		// Scope "Request" (request da Seite nur aufgebaut werden muss)
		request.setAttribute("form", form);
		// Redirect weil Daten in die Datenbank geschrieben wird
		response.sendRedirect("einkaufswagenservlet");
	}
	
	private void persist(EinkaufswagenBean form) throws ServletException {
		// 1 ÜBERPRÜFEN OB ARTIKEL IM EINKAUFSWAGEN BEREITS VORHANDEN
		try (Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"SELECT * FROM einkaufswagenPosition WHERE idUser LIKE ? AND artikelId=?")) {
			
			// PreparedStatement Grundgerüst befüllen
			pstmt.setLong(1, form.getUserId());
			pstmt.setLong(2, form.getArtikelId());
			
			// Ergebnis mit angegebener Email vergleichen
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs != null && rs.next()) {
					// vorhandene artikelId und Menge merken
					artikelVorhandenId = rs.getLong("id");
					artikelVorhandenMenge = rs.getInt("menge");
					artikelVorhandenMenge += form.getMenge();
				}
			}
			
		} catch (Exception ex) {
			// Falls nicht vorhanden, nichts machen
		}
		
		// 2 FALLS ARTIKEL BEREITS IM EINKAUFSWAGEN: ARTIKELANZAHL ERHÖHEN 
		if (artikelVorhandenId != null) {
			try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(
						"UPDATE einkaufswagenPosition SET menge = ? WHERE id = ?")) {
					
				// PreparedStatement Grundgerüst befüllen und Menge ändern
				pstmt.setLong(1, artikelVorhandenMenge);
				pstmt.setLong(2, artikelVorhandenId);
				pstmt.executeUpdate();
				
				// Menge zur Übergabe ändern
				form.setMenge(artikelVorhandenMenge);
				
				// Methode verlassen
				return;
				
			} catch (Exception ex) {
				throw new ServletException(ex.getMessage());
			}
		}
				
		// 3 ARTIKEL IN EINKAUFSWAGEN POSITION SCHREIBEN
		else {
			// Namen der Spalten, die die Keys automatisch generieren
			String[] generatedKeys = new String[] {"id"};
			
			// PreparedStatement für Grundgerüst 
			// (Ressourcen in runden Klammern nach try um sie im Nachhinein nicht wieder schließen zu müssen)
			try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(
						"INSERT INTO einkaufswagenPosition (artikelId,menge,zeitstempel,idUser) VALUES (?,?,?,?)", 
						generatedKeys)){
				
				// PreparedStatement Grundgerüst befüllen
				pstmt.setLong(1, form.getArtikelId());
				pstmt.setInt(2, form.getMenge());
				pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
				pstmt.setLong(4, form.getUserId());
				pstmt.executeUpdate();
				
			} catch (Exception ex) {
				throw new ServletException(ex.getMessage());
			}
		}
	}
}	