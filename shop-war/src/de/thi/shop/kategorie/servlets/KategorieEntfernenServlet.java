//CREATED BY SEYT ARAR
package de.thi.shop.kategorie.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
 * Servlet implementation class KategorieEntfernenAufbauServlet
 */
@WebServlet("/kategorieentfernenservlet")
public class KategorieEntfernenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(lookup="java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;
       
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KategorieEntfernenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		List<KategorieBean> kategorien = new ArrayList<KategorieBean>();
		
		//Hole alle Kategorien
		try(Connection con = ds.getConnection();
				Statement stmt = con.createStatement();
			    ResultSet rs = stmt.executeQuery("SELECT name, id FROM kategorie")) {
			     
				while(rs.next()){
					
					//Bei jeder gefundenen Kategorie, neuer Eintrag in Liste
					KategorieBean kategorieBean = new KategorieBean();			
					String kategorieName = rs.getString("name");    
					
					//�berpr�fung ob Kategorie vom aktuellen Artikel, damit kein doppelter Eintrag in Liste
					kategorieBean.setKategorieName(kategorieName);
					kategorieBean.setId(rs.getLong("id"));
					kategorien.add(kategorieBean);
				}
		}catch(Exception ex) {
			  throw new ServletException(ex.getMessage());
		}		
		
		request.setAttribute("kategorien", kategorien);
	
	RequestDispatcher disp = request.getRequestDispatcher("admin/kategorie_entfernen.jsp");
	disp.forward(request, response);
	}

}
