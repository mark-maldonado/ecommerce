// CREATED BY MARK MALDOANDO

package de.thi.shop.einkaufswagen.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
import de.thi.shop.user.servlets.CheckAngemeldet;

@WebServlet("/einkaufswagenaktualisierenservlet")
public class EinkaufswagenAktualisierenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// get Datasource
	@Resource(lookup="java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;
	// Attribut für bereits vorhandenen Artikel im Warenkorb
	private Long artikelVorhandenId = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Test ob User angemeldet ist
		CheckAngemeldet.checkAngemeldet(request, response);
						
		// Format der zu lesenden Formulardaten
		request.setCharacterEncoding("UTF-8");
		
		// Session Bean erhalten
		HttpSession session = request.getSession(false);
		UserBean userBean = (UserBean) session.getAttribute("userBean");
		
		// Daten in Bean schreiben zur Weitergabe an JSP
		EinkaufswagenBean form = new EinkaufswagenBean();
		form.setArtikelId(Long.valueOf(request.getParameter("artikelId")));
		form.setMenge(Integer.valueOf(request.getParameter("menge")));
		form.setUserId(userBean.getId());
		
		// Daten in Datenbank schreiben
		persist(form);
		
		// Redirect weil Daten in die Datenbank geschrieben wird
		response.sendRedirect("einkaufswagenservlet");
	}
	
	private void persist(EinkaufswagenBean form) throws ServletException {
		// 1 ID IN EINKAUFSWAGEN FINDEN
		try (Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"SELECT * FROM einkaufswagenPosition WHERE idUser LIKE ? AND artikelId = ?")) {
			
			// PreparedStatement Grundgerüst befüllen
			pstmt.setLong(1, form.getUserId());
			pstmt.setLong(2, form.getArtikelId());
			
			// Ergebnis mit angegebener Email vergleichen
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs != null && rs.next()) {
					// vorhandene artikelId und Menge merken
					artikelVorhandenId = rs.getLong("id");
				}
			}
			
		} catch (Exception ex) {
			// Falls nicht vorhanden, nichts machen
		}

		// 2 MENGE ÄNDERN
		try (Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"UPDATE einkaufswagenPosition SET menge = ? WHERE id = ?")) {
				
			// PreparedStatement Grundgerüst befüllen und Menge ändern
			pstmt.setLong(1, form.getMenge());
			pstmt.setLong(2, artikelVorhandenId);
			pstmt.executeUpdate();
			
			// Methode verlassen
			return;
			
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}
}	