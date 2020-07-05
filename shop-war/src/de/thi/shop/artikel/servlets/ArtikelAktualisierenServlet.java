//CREATED BY SEYIT ARAR

package de.thi.shop.artikel.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

import de.thi.shop.artikel.beans.ArtikelBean;
import de.thi.shop.user.servlets.CheckAdmin;
import de.thi.shop.user.servlets.CheckAngemeldet;

/**
 * Servlet implementation class ArtikelAktualisierenServlet
 */
@WebServlet("/artikelaktualisierenservlet")
@MultipartConfig
public class ArtikelAktualisierenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(lookup="java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArtikelAktualisierenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Test ob User angemeldet ist
		CheckAngemeldet.checkAngemeldet(request, response);
		
		// Test ob User Admin ist
		CheckAdmin.checkAdmin(request, response);
		
		request.setCharacterEncoding("UTF-8");
		
		ArtikelBean artikelBean = new ArtikelBean();
		
		//Attribute in neu erstellte ArtikelBean schreiben
		artikelBean.setName(request.getParameter("artikelName"));
		artikelBean.setKategorieName(request.getParameter("artikelKategorie"));
		artikelBean.setPreis(Math.round(100f*Float.parseFloat(request.getParameter("artikelPreis"))));
		artikelBean.setId(Long.parseLong(request.getParameter("artikelId")));
		
		//KategorieId wird von der Datenbank geholt, da nur KategorieName von jsp weitergegeben wird
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement("SELECT * FROM kategorie WHERE name = ?")) {
			
			pstmt.setString(1, artikelBean.getKategorieName());
			ResultSet rs = pstmt.executeQuery();
			
				if(rs.next()){
					artikelBean.setKategorieId(rs.getLong("id"));
				}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		
		//Test ob artikelBild mitgeliefert wurde
		boolean bildAvailable = false;
		try {
			//Fehler bei nicht vorhandenem Bildnamen provozieren
			request.getPart("artikelBild").getSubmittedFileName().substring(0, request.getPart("artikelBild").getSubmittedFileName().lastIndexOf("."));
			bildAvailable = true;
		} catch(Exception e) {
			bildAvailable = false;
		}
		
		
		//Falls Bild ausgewaehlt wurde
		if (bildAvailable) {
			//Handling f�r das Bild, das hochgeladen wurde
			InputStream inputStream = null;
			Part filePart = request.getPart("artikelBild");
			inputStream = filePart.getInputStream();
			
			//Name von Bild bekommen und trimmen, weil ganzer Path wiedergegeben wird
			String artikelBildName = filePart.getSubmittedFileName();
			String trimmed = artikelBildName.substring(0, artikelBildName.lastIndexOf("."));
			artikelBean.setBildName(trimmed);
				
			//Wenn nichts hochgeladen, wird das aktuelle Bild von der Datenbank geholt
			InputStream original = null;
			try(Connection con = ds.getConnection();
					PreparedStatement pstmt = con.prepareStatement("SELECT bild FROM artikel WHERE id = ?")){
				
				pstmt.setLong(1, artikelBean.getId());
				
					try(ResultSet rs = pstmt.executeQuery()) {
						if(rs.next()) {
							original = rs.getBinaryStream("bild");
						}
					}
			} catch (Exception ex) {
				throw new ServletException(ex.getMessage());
				}
			
			InputStreamReader reader = new InputStreamReader(inputStream);
				if(!(reader.ready())){
					inputStream = original;
			}
				
			//Update bild, bildName in der Datenbank
			try(Connection con = ds.getConnection();
					PreparedStatement pstmt = con.prepareStatement("UPDATE artikel SET bild = ?, bildname = ? WHERE id = ?")){
				
				pstmt.setBlob(1, inputStream);
				pstmt.setString(2, artikelBean.getBildName());
				pstmt.setLong(3, artikelBean.getId());
				pstmt.executeUpdate();
				
			} catch (Exception ex) {
				throw new ServletException(ex.getMessage());
			}
		}
		
		//Update name, preis, idKategorie in der Datenbank
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement("UPDATE artikel SET name = ?, preis = ?, idKategorie = ? WHERE id = ?")){
			
			pstmt.setString(1, artikelBean.getName());
			pstmt.setInt(2, artikelBean.getPreis());
			pstmt.setLong(3, (artikelBean.getKategorieId()));
			pstmt.setLong(4, artikelBean.getId());
			pstmt.executeUpdate();
			
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}

		response.sendRedirect("admin/artikel_bearbeiten_erfolgreich.jsp");
	}
	
}
