//CREATED BY SEYIT ARAR

package de.thi.shop.kategorie.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class KategorieEntfernenServlet
 */
@WebServlet("/kategorieloeschenservlet")
public class KategorieLoeschenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(lookup="java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KategorieLoeschenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
			try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement("DELETE FROM kategorie WHERE name = ?")) {
			
				pstmt.setString(1, request.getParameter("kategorieName"));
				pstmt.executeUpdate();
			
			}	catch (Exception ex) {
				throw new ServletException(ex.getMessage());
		 		}
	
		request.setAttribute("kategorieName", request.getAttribute("kategorieName"));
		RequestDispatcher disp = request.getRequestDispatcher("admin/kategorie_entfernen_erfolgreich.jsp");
		disp.forward(request, response);
	}

}
