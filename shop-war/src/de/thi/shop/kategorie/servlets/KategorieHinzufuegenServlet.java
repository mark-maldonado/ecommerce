//CREATED BY SEYIT ARAR
package de.thi.shop.kategorie.servlets;

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

import de.thi.shop.kategorie.beans.KategorieBean;

/**
 * Servlet implementation class KategorieHinzufuegenServlet
 */
@WebServlet("/kategoriehinzufuegenservlet")
public class KategorieHinzufuegenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(lookup="java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KategorieHinzufuegenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String kategorieName = request.getParameter("kategorieName");
		
		// Bean erstellen und Name vergeben
		KategorieBean kategorieBean = new KategorieBean();
		kategorieBean.setKategorieName(kategorieName);
		
		boolean vorhanden = false;
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement("SELECT name FROM kategorie WHERE name = ?")){
			
			pstmt.setString(1, kategorieName);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				vorhanden = true;
				RequestDispatcher disp = request.getRequestDispatcher("admin/kategorie_hinzu_fehler.jsp");
				disp.forward(request, response);
			}
			
		}catch(Exception ex) {
			throw new ServletException(ex);
		}	
		
		if(!vorhanden) {
		String [] generatedKeys = new String[] {"id"};
		
		//in Datenbank einfügen
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement("INSERT INTO kategorie (name) VALUES (?)", generatedKeys)){
			
			pstmt.setString(1, kategorieName);
			pstmt.executeUpdate();
		}catch(Exception ex) {
			throw new ServletException(ex);
		}	
		
		// Bean mitgeben
		request.setAttribute("kategorieBean", kategorieBean);
		
		RequestDispatcher disp = request.getRequestDispatcher("admin/kategorie_hinzu_erfolgreich.jsp");
		disp.forward(request, response);
		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
