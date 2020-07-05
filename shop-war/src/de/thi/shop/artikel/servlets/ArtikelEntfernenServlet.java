//CREATED BY SEYIT ARAR

package de.thi.shop.artikel.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import de.thi.shop.user.servlets.CheckAdmin;
import de.thi.shop.user.servlets.CheckAngemeldet;

/**
 * Servlet implementation class ArtikelEntfernenServlet
 */
@WebServlet("/artikelentfernenservlet")
public class ArtikelEntfernenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(lookup="java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArtikelEntfernenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Test ob User angemeldet ist
		CheckAngemeldet.checkAngemeldet(request, response);
		
		// Test ob User Admin ist
		CheckAdmin.checkAdmin(request, response);
						
		request.setCharacterEncoding("UTF-8");
		
		//Hole Attribute
		String artikelId = request.getParameter("artikelId");
	
		//L�schen
			try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement("DELETE FROM artikel WHERE id = ?")) {
			
				pstmt.setString(1, artikelId);
				pstmt.executeUpdate();
			
			} catch (Exception ex) {
				throw new ServletException(ex.getMessage());
			}
		
			response.sendRedirect("admin/artikel_entfernen_erfolgreich.jsp");
		
	}

	
	

}
