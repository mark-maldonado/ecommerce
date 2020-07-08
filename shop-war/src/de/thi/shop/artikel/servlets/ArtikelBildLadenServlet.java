// CREATED BY MARK MALDOANDO

package de.thi.shop.artikel.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import de.thi.shop.user.servlets.CheckAngemeldet;

@WebServlet("/artikelbildladenservlet")
public class ArtikelBildLadenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// get Datasource
	@Resource(lookup="java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Test ob User angemeldet ist
		CheckAngemeldet.checkAngemeldet(request, response);
						
		// Format der zu lesenden Formulardaten
		request.setCharacterEncoding("UTF-8");
		
		// empfangene id lesen
		Long id = Long.valueOf(request.getParameter("id"));
		
		// DB-Zugriff
		try (Connection con = ds.getConnection();
			 PreparedStatement pstmt = con.prepareStatement("SELECT bild FROM artikel WHERE id = ?") ) {
			
			// Grundger�st mit 1 Wert bef�llen
			pstmt.setLong(1, id);
			
			// Ergebnis zum Befehl speichern
			try (ResultSet rs = pstmt.executeQuery()) {
			
				// gefunden Eintrag lesen und weitergeben
				if (rs != null && rs.next()) {
					Blob bild = rs.getBlob("bild");
					
					// Bildl�nge in Header schreiben
					response.reset();
					long length = bild.length();
					response.setHeader("Content-Length",String.valueOf(length));
					
					// Bin�rdaten an output Stream weiterreichen
					try (InputStream in = bild.getBinaryStream()) {
						// Puffergr��e festelegen
						final int bufferSize = 256;
						byte[] buffer = new byte[bufferSize];
						
						// Bilddaten in den Output Stream schreiben
						ServletOutputStream out = response.getOutputStream();
						while ((length = in.read(buffer)) != -1) {
							out.write(buffer,0,(int) length);
						}
						
						// ausf�hren
						out.flush();
					}
				}
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}
}	