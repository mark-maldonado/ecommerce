//CREATED BY SEYIT ARAR

package de.thi.shop.kategorie.servlets;

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
import javax.sql.DataSource;

import de.thi.shop.kategorie.beans.KategorieBean;
import de.thi.shop.user.servlets.CheckAdmin;
import de.thi.shop.user.servlets.CheckAngemeldet;

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
		// Test ob User angemeldet ist
		CheckAngemeldet.checkAngemeldet(request, response);
		
		// Test ob User Admin ist
		CheckAdmin.checkAdmin(request, response);
		
		request.setCharacterEncoding("UTF-8");
		
		// Bean erstellen und Name vergeben
		KategorieBean kategorieBean = new KategorieBean();
		kategorieBean.setKategorieName(request.getParameter("kategorieName"));
		
		
		//KategorieID holen
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement("SELECT id FROM kategorie WHERE name = ?")) {
		
				pstmt.setString(1, kategorieBean.getKategorieName());
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()) {
				kategorieBean.setId(rs.getLong("id"));
				}
				
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		
		
		//Prüfen ob es Artikel in der Kategorie gibt
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement("SELECT * FROM artikel WHERE idKategorie = ?")) {
			
				pstmt.setLong(1, kategorieBean.getId());
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()) {
					response.sendRedirect("admin/kategorie_entfernen_fehler.jsp");
				}
				else {
					//Löschen der Kategorie, da keine Artikel in Kategorie
					try(Connection con1 = ds.getConnection();
							PreparedStatement pstmt1 = con.prepareStatement("DELETE FROM kategorie WHERE name = ?")) {
								pstmt1.setString(1, kategorieBean.getKategorieName());
								pstmt1.executeUpdate();
							} catch (Exception ex) {
								throw new ServletException(ex.getMessage());
							}
					response.sendRedirect("admin/kategorie_entfernen_erfolgreich.jsp");
					}
		
		}	catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		 	}
		
	}

}
