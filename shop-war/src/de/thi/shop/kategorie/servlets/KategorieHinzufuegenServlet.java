//CREATED BY SEYIT ARAR
package de.thi.shop.kategorie.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import de.thi.shop.kategorie.beans.KategorieBean;
import de.thi.shop.user.servlets.CheckAngemeldet;

/**
 * Servlet implementation class KategorieHinzufuegenServlet
 */
@WebServlet("/kategoriehinzufuegenservlet")
@MultipartConfig
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Test ob User angemeldet ist
		CheckAngemeldet.checkAngemeldet(request, response);
		
		request.setCharacterEncoding("UTF-8");
		
		// Bean erstellen und Name vergeben
		KategorieBean kategorieBean = new KategorieBean();
		kategorieBean.setKategorieName(request.getParameter("kategorieName"));
		
		//Icon für Kategorie holen
		Part filePart = request.getPart("kategorieBild");
		InputStream inputStream = null;
		inputStream = filePart.getInputStream();
		String bildName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		
		//.jpg von Namen entfernen
		if (bildName.indexOf(".") > 0) {
			   bildName = bildName.substring(0, bildName.lastIndexOf("."));
		}
		
		//BildName in KategorieBean
		kategorieBean.setBildName(bildName);
		
		boolean vorhanden = false;
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement("SELECT name FROM kategorie WHERE name = ?")){
			
			pstmt.setString(1, kategorieBean.getKategorieName());
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				vorhanden = true;
				response.sendRedirect("admin/kategorie_hinzu_fehler.jsp");
			}
			
		}catch(Exception ex) {
			throw new ServletException(ex);
		}	
		
		if(!vorhanden) {
		String [] generatedKeys = new String[] {"id"};
		
		//in Datenbank  einfügen
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement("INSERT INTO kategorie (name, bildK, bildNameK) VALUES (?,?,?)", generatedKeys)){
			
			pstmt.setString(1, kategorieBean.getKategorieName());
			pstmt.setBlob(2, inputStream);
			pstmt.setString(3, kategorieBean.getBildName());
			pstmt.executeUpdate();
			
			try(ResultSet rs = pstmt.getGeneratedKeys()) {
				while (rs.next()) {
					kategorieBean.setId(rs.getLong(1));
				}
			}
			catch(Exception ex) {
				throw new ServletException(ex);
			}
		}catch(Exception ex) {
			throw new ServletException(ex);
		}	
		
		// Bean mitgeben
		
		response.sendRedirect("admin/kategorie_hinzu_erfolgreich.jsp");
		}
	}
}
