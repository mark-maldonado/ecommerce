package de.thi.shop.kategorie.servlets;

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

/**
 * Servlet implementation class KategorieBildLadenServlet
 */
@WebServlet("/kategoriebildladenservlet")
public class KategorieBildLadenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// get Datasource
	@Resource(lookup="java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;
	
	
    public KategorieBildLadenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Format der zu lesenden Formulardaten
				request.setCharacterEncoding("UTF-8");
				
				// empfangenen namen lesen
				String name = request.getParameter("name");
				
				// DB-Zugriff
				try (Connection con = ds.getConnection();
					 PreparedStatement pstmt = con.prepareStatement("SELECT bild FROM kategorie WHERE name = ?") ) {
					
					// Grundgerüst mit 1 Wert befüllen
					pstmt.setString(1, name);
					
					// Ergebnis zum Befehl speichern
					try (ResultSet rs = pstmt.executeQuery()) {
					
						// gefunden Eintrag lesen und weitergeben
						if (rs != null && rs.next()) {
							Blob bild = rs.getBlob("bild");
							
							// Bildlänge in Header schreiben
							response.reset();
							long length = bild.length();
							response.setHeader("Content-Length",String.valueOf(length));
							
							// Binärdaten an output Stream weiterreichen
							try (InputStream in = bild.getBinaryStream()) {
								// Puffergröße festelegen
								final int bufferSize = 256;
								byte[] buffer = new byte[bufferSize];
								
								// Bilddaten in den Output Stream schreiben
								ServletOutputStream out = response.getOutputStream();
								while ((length = in.read(buffer)) != -1) {
									out.write(buffer,0,(int) length);
								}
								
								// ausführen
								out.flush();
							}
						}
					}
				} catch (Exception ex) {
					throw new ServletException(ex.getMessage());
				}
			}
}
