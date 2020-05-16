package de.thi.shop.artikel.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class ArtikelHinzuServlet
 */
@WebServlet("/artikelhinzuservlet")
public class ArtikelHinzuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(lookup="java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArtikelHinzuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		List<KategorieBean> kategorien = new ArrayList<KategorieBean>();
		
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement("SELECT name FROM kategorie")) {
			     
			try (ResultSet rs = pstmt.executeQuery()) {
				
				while(rs.next()){
					//Bei jeder gefundenen Kategorie, neuer Eintrag in Liste
					KategorieBean kategorieBean = new KategorieBean();			
					String kategorieName = rs.getString("name");    
					//�berpr�fung ob Kategorie vom aktuellen Artikel, damit kein doppelter Eintrag in Liste
					kategorieBean.setKategorieName(kategorieName);
					kategorien.add(kategorieBean);
				}
			}
		}catch(Exception ex) {
			  throw new ServletException(ex.getMessage());
		}		
		
		request.setAttribute("kategorien", kategorien);
	
	RequestDispatcher disp = request.getRequestDispatcher("admin/artikel_hinzu.jsp");
	disp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

}
