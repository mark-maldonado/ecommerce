// CREATED BY MARK MALDONADO

package de.thi.shop.einkaufswagen.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import de.thi.shop.einkaufswagen.beans.EinkaufswagenBean;
import de.thi.shop.user.beans.UserBean;

@WebServlet("/einkaufswagenuebersichtservlet")
public class EinkaufswagenUebersichtServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// get Datasource
	@Resource(lookup="java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// userBean erstellen
		UserBean userBean = new UserBean();
		
		// Test ob user angemeldet ist
		// Session Bean erhalten
		try {
			if (request.getSession(false).getAttribute("userBean") == null) {
				final RequestDispatcher dispatcher = request.getRequestDispatcher("/eingang/login.html");
				dispatcher.forward(request, response);
			} else {
				userBean = (UserBean) request.getSession().getAttribute("userBean");
			}
		} 
		catch(Exception Ex) {
		}

		// Format der zu lesenden Formulardaten
		request.setCharacterEncoding("UTF-8");
		
		//zu übergebene Liste von Bohne erstellen
		List<EinkaufswagenBean> alleArtikel = new ArrayList<EinkaufswagenBean>();
		
		// Gesamtsumme Variable
		int gesamtsumme = 0;
		String gesamtsummeString = "";
		
		// DB-Zugriff
		try (Connection con = ds.getConnection();
			// Grundgerüst erstellen
			 PreparedStatement pstmt = con.prepareStatement("SELECT artikel.id, artikel.name, preis, bildName, kategorie.id AS kategroieId, kategorie.name AS kategorieName, menge FROM artikel INNER JOIN kategorie ON (artikel.idKategorie = kategorie.id) INNER JOIN einkaufswagenPosition ON (artikel.id = einkaufswagenPosition.artikelId) WHERE einkaufswagenPosition.idUser = ?")) {

			//Grundgerüst mit 1 Wert füllen
			pstmt.setLong(1, userBean.getId());
			try (ResultSet rs = pstmt.executeQuery()) {
			
				//solange Werte vorhanden (von den Gesuchten)
				while (rs.next()) {
					//Bohne erstellen und mit Werte der gefundenen Zeile befüllen
					EinkaufswagenBean artikel = new EinkaufswagenBean();
					
					Long id = Long.valueOf(rs.getLong("id"));
					artikel.setId(id);
					
					String name = rs.getString("name");
					artikel.setName(name);
					
					// Cents in Euro String umwandeln
					int preis = Integer.valueOf(rs.getInt("preis"));
					String preisString = String.format("%10.2f€", preis/100.0);
					artikel.setPreisString(preisString);
					
					String bildName = rs.getString("bildName");
					artikel.setBildName(bildName);
					
					Long kategorieId = Long.valueOf(rs.getLong("kategroieId"));
					artikel.setKategorieId(kategorieId);
					
					String kategorieName = rs.getString("kategorieName");
					artikel.setKategorieName(kategorieName);
					
					int menge = rs.getInt("menge");
					artikel.setMenge(menge);
				
					// Gesamtsumme addieren
					gesamtsumme += preis * menge;
					
					// Bohne der Liste hinzufügen
					alleArtikel.add(artikel);
				} // while rs.next()
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		
		// Gesamtsumme in String
		Locale locale = new Locale("de", "DE");      
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
		gesamtsummeString = currencyFormatter.format(gesamtsumme/100.0);
				
		// Scope "Request" (request da Seite nur aufgebaut werden muss)
		request.setAttribute("alleArtikel", alleArtikel);
		request.setAttribute("gesamtsumme", gesamtsummeString);
		
		// Weiterleiten an JSP
		final RequestDispatcher dispatcher = request.getRequestDispatcher("einkaufswagen/einkaufswagen_uebersicht.jsp");
		dispatcher.forward(request, response);
	}
}	