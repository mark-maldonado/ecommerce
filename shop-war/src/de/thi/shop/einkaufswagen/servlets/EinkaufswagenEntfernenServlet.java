// CREATED BY MARK MALDOANDO

package de.thi.shop.einkaufswagen.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

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

@WebServlet("/einkaufswagenentfernenservlet")
public class EinkaufswagenEntfernenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// get Datasource
	@Resource(lookup="java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Test ob User angemeldet ist
		CheckAngemeldet.checkAngemeldet(request, response);
						
		// Format der zu lesenden Formulardaten
		request.setCharacterEncoding("UTF-8");
		
		// Daten in Bean schreiben zur Weitergabe an JSP
		EinkaufswagenBean einkaufswagenBean = new EinkaufswagenBean();
		einkaufswagenBean.setArtikelId(Long.valueOf(request.getParameter("id")));
		einkaufswagenBean.setName(request.getParameter("name"));
				
		// Session Bean erhalten
		HttpSession session = request.getSession(false);
		UserBean userBean = (UserBean) session.getAttribute("userBean");
		
		// PreparedStatement für Grundgerüst 
		// (Ressourcen in runden Klammern nach try um sie im Nachhinein nicht wieder schließen zu müssen)
		try (Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"DELETE FROM einkaufswagenposition WHERE idUser = ? AND artikelId = ?")) {
			
			// PreparedStatement Grundgerüst befüllen
			pstmt.setLong(1, userBean.getId());
			pstmt.setLong(2, einkaufswagenBean.getArtikelId());
			pstmt.executeUpdate();
			
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		
		// Redirect weil Daten in die Datenbank geschrieben wird
		response.sendRedirect("einkaufswagenservlet");
	}
}	