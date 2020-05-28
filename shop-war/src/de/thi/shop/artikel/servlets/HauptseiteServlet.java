// CREATED BY MARK MALDOANDO

package de.thi.shop.artikel.servlets;

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

import de.thi.shop.artikel.beans.ArtikelBean;
import de.thi.shop.kategorie.beans.KategorieBean;

@WebServlet("/hauptseiteservlet")
public class HauptseiteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//get Datasource
	@Resource(lookup="java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Test ob user angemeldet ist
		// Session Bean erhalten		
		try {
			if (request.getSession(false).getAttribute("userBean") == null) {
				final RequestDispatcher dispatcher = request.getRequestDispatcher("/eingang/login.html");
				dispatcher.forward(request, response);
			}
		} 
		catch (Exception ex) {
		}
		
		// Format der zu lesenden Formulardaten
		request.setCharacterEncoding("UTF-8");
		// Such-String lesen
		String suche = request.getParameter("suche");
		
		// Kategorien aus Datenbank in eine Bean speichern zur Weitergaben an die JSP
		List<KategorieBean> alleKategorien = kategorien();
		
		// Artiekl aus Datenbank in eine Bean speichern zur Weitergaben an die JSP
		List<ArtikelBean> alleArtikel = search(suche);
		
		// Scope "Request" (request da Seite nur aufgebaut werden muss)
		request.setAttribute("alleKategorien", alleKategorien);
		request.setAttribute("alleArtikel", alleArtikel);
		
		// Weiterleiten an JSP
		final RequestDispatcher dispatcher = request.getRequestDispatcher("hauptseite/hauptseite.jsp");
		dispatcher.forward(request, response);
	}
	
	private List<KategorieBean> kategorien() throws ServletException {
		
		System.out.println("inside kategorien");
		
		List<KategorieBean> kategorien = new ArrayList<KategorieBean>();
		
		//Hole alle Kategorien
		try(Connection con = ds.getConnection();
				Statement stmt = con.createStatement();
			    ResultSet rs = stmt.executeQuery("SELECT name, id FROM kategorie")) {
			     
				while(rs.next()){	
					System.out.println("kategorien inside while");
					
					//Bei jeder gefundenen Kategorie, neuer Eintrag in Liste
					KategorieBean kategorieBean = new KategorieBean();			
					String kategorieName = rs.getString("name");    
					
					System.out.println(kategorieName);
					
					//ï¿½berprï¿½fung ob Kategorie vom aktuellen Artikel, damit kein doppelter Eintrag in Liste
					kategorieBean.setKategorieName(kategorieName);
					kategorieBean.setId(rs.getLong("id"));
					kategorien.add(kategorieBean);
				}
		} catch(Exception ex) {
			  throw new ServletException(ex.getMessage());
		}		
		
		return kategorien;
	}
	
	private List<ArtikelBean> search(String suche) throws ServletException {
		System.out.println("inside search");
		
		// lastname wenn leer alle Anzeigen sonst, lastname als Zwischenwert suchen
		suche = (suche == null || suche == "") ? "%" : "%" + suche + "%";
		//zu übergebene Liste von Bohne erstellen
		List<ArtikelBean> alleArtikel = new ArrayList<ArtikelBean>();
		
		// DB-Zugriff
		try (Connection con = ds.getConnection();
			// Grundgerüst erstellen
			 PreparedStatement pstmt = con.prepareStatement("SELECT artikel.id, artikel.name, preis, bildName, kategorie.id AS kategroieId, kategorie.name AS kategorieName FROM artikel INNER JOIN kategorie ON (artikel.idKategorie = kategorie.id) WHERE artikel.name LIKE ? OR kategorie.name LIKE ? ORDER BY artikel.name")) {

			//Grundgerüst mit 1 Wert füllen
			pstmt.setString(1, suche);
			pstmt.setString(2, suche);
			try (ResultSet rs = pstmt.executeQuery()) {
			
				//solange Werte vorhanden (von den Gesuchten)
				while (rs.next()) {
					System.out.println("inside search while next");
					
					//Bohne erstellen und mit Werte der gefundenen Zeile befüllen
					ArtikelBean artikel = new ArtikelBean();
					
					Long id = Long.valueOf(rs.getLong("id"));
					artikel.setId(id);
					
					String name = rs.getString("name");
					artikel.setName(name);
					
					System.out.println(name);
					
					// Cents in Euro String umwandeln
					int preis = Integer.valueOf(rs.getInt("preis"));
					String preisString = String.format("%10.2f€", preis/100.0);
					artikel.setPreis(preis);
					artikel.setPreisString(preisString);
					
					String bildName = rs.getString("bildName");
					artikel.setBildName(bildName);
					
					Long kategorieId = Long.valueOf(rs.getLong("kategroieId"));
					artikel.setKategorieId(kategorieId);
					
					String kategorieName = rs.getString("kategorieName");
					artikel.setKategorieName(kategorieName);
				
					// Bohne der Liste hinzufügen
					alleArtikel.add(artikel);
				} // while rs.next()
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		return alleArtikel;
	}
}	