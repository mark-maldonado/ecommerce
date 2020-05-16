//CREATED BY SEYIT ARAR

package de.thi.shop.artikel.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import de.thi.shop.artikel.beans.ArtikelBean;

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
		
		request.setCharacterEncoding("UTF-8");
		
		ArtikelBean artikelBean = new ArtikelBean();
		
		//Attribute in neu erstellte ArtikelBean schreiben
		artikelBean.setName(request.getParameter("artikelName"));
		artikelBean.setKategorieName(request.getParameter("artikelKategorie"));
		artikelBean.setPreis(Integer.parseInt(request.getParameter("artikelPreis")));
		artikelBean.setId(Long.parseLong(request.getParameter("artikelId")));
		
		//Handling f�r das Bild, das hochgeladen wurde
		InputStream inputStream = null;
		Part filePart = request.getPart("artikelBild");
		inputStream = filePart.getInputStream();
		
		//Name von Bild bekommen und trimmen, weil ganzer Path wiedergegeben wird
		String artikelBildName = filePart.getSubmittedFileName();
		String trimmed = artikelBildName.substring(artikelBildName.lastIndexOf("\\") + 1).trim();
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
	
		//Update in der Datenbank
			try(Connection con = ds.getConnection();
					PreparedStatement pstmt = con.prepareStatement("UPDATE artikel SET name = ?, preis = ?, idKategorie = ?, bild = ?, bildname = ? WHERE id = ?")){
				
				pstmt.setString(1, artikelBean.getName());
				pstmt.setInt(2, artikelBean.getPreis());
				pstmt.setLong(3, (artikelBean.getKategorieId()));
				pstmt.setBlob(4, inputStream);
				pstmt.setString(5, artikelBean.getBildName());
				pstmt.setLong(6, artikelBean.getId());
				pstmt.executeUpdate();
				
			} catch (Exception ex) {
				throw new ServletException(ex.getMessage());
				}

			final RequestDispatcher disp = request.getRequestDispatcher("/admin/artikel_bearbeiten_erfolgreich.jsp");
			disp.forward(request, response);			    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	
	
}
