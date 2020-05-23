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

@WebServlet("/einkaufswagenentfernenservlet")
public class EinkaufswagenEntfernenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// get Datasource
	@Resource(lookup="java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Format der zu lesenden Formulardaten
		request.setCharacterEncoding("UTF-8");
		
		// Session Bean erhalten
		HttpSession session = request.getSession(false);
		UserBean userBean = (UserBean) session.getAttribute("userBean");
		
		// PreparedStatement f�r Grundger�st 
		// (Ressourcen in runden Klammern nach try um sie im Nachhinein nicht wieder schlie�en zu m�ssen)
		try (Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"DELETE FROM einkaufswagenPosition WHERE idUser LIKE ? AND artikelId = ?")) {

			// PreparedStatement Grundger�st bef�llen
			pstmt.setLong(1, userBean.getId());
			pstmt.setLong(2, Long.valueOf(request.getParameter("artikelId")));
			
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		
		// Scope "Request" (request da Seite nur aufgebaut werden muss)
		request.setAttribute("artikelName", request.getParameter("artikelName"));
		// Redirect weil Daten in die Datenbank geschrieben wird
		response.sendRedirect("einkaufswagen/einkaufswagen_entfernen_erfolgreich.jsp");
	}
}	