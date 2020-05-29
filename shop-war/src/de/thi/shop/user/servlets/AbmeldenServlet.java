//CREATED BY MARK MALDONADO

package de.thi.shop.user.servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;


/**
 * Servlet implementation class AnmeldenServlet
 */
@WebServlet("/abmeldenservlet")
public class AbmeldenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(lookup="java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// eingeloggten user aus session löschen
		HttpSession session = request.getSession();
		session.invalidate();
		
		// zur login Seite weiterleiten
		response.sendRedirect("eingang/login.html");
	}
}
