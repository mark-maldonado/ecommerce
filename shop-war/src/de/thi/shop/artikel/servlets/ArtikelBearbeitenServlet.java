//CREATED BY SEYIT ARAR

package de.thi.shop.artikel.servlets;

import java.io.IOException;
import java.sql.Connection;
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

import de.thi.shop.artikel.beans.ArtikelBean;
import de.thi.shop.kategorie.beans.KategorieBean;
import de.thi.shop.user.servlets.CheckAngemeldet;



/**
 * Servlet implementation class ArtikelBearbeitenServlet
 */
@WebServlet("/artikelbearbeitenservlet")
public class ArtikelBearbeitenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(lookup="java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArtikelBearbeitenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Test ob User angemeldet ist
		CheckAngemeldet.checkAngemeldet(request, response);
		
		request.setCharacterEncoding("UTF-8");
	
		//Attribute holen
		String artikelName = request.getParameter("name");
		Long artikelId = Long.valueOf(request.getParameter("id"));
		float artikelPreis = Integer.parseInt(request.getParameter("preis"))/100f;
		String kategorieName = request.getParameter("kategorie");
		
		ArtikelBean artikelBean = new ArtikelBean();
		
		//Attribute in die ArtikelBean
		artikelBean.setName(artikelName);
		artikelBean.setPreisFloat(artikelPreis);
		artikelBean.setId(artikelId);
		artikelBean.setKategorieName(kategorieName);
		
		//Hole Liste mit allen Kategorien
		List<KategorieBean> kategorien = kategorienHolen(artikelBean);				
		
		//Gebe Attribute an artikel_bearbeiten.jsp weiter
		request.setAttribute("kategorien", kategorien);					
		request.setAttribute("artikelBean", artikelBean);
		
		final RequestDispatcher disp = request.getRequestDispatcher("/admin/artikel_bearbeiten.jsp");
		disp.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
		
	private List<KategorieBean> kategorienHolen(ArtikelBean artikelBean) throws ServletException
	{
		List<KategorieBean> kategorien = new ArrayList<KategorieBean>();
		
		try(Connection con = ds.getConnection();
				Statement stmt = con.createStatement();
			    ResultSet rs = stmt.executeQuery("SELECT name FROM kategorie")) {
				
				while(rs.next()){
					
					//Bei jeder gefundenen Kategorie, neuer Eintrag in Liste
					KategorieBean kategorieBean = new KategorieBean();			
					String kategorieName = rs.getString("name");    
					
					//�berpr�fung ob Kategorie vom aktuellen Artikel, damit kein doppelter Eintrag in Liste
						if(kategorieName.equals(artikelBean.getKategorieName())) {
							//dann nichts
						}
						else {
							kategorieBean.setKategorieName(kategorieName);
							kategorien.add(kategorieBean);
						}
				}
		}catch(Exception ex) {
			  throw new ServletException(ex.getMessage());
		}		
		return kategorien; //return Liste
	}
}
