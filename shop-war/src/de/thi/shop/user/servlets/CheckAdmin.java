package de.thi.shop.user.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckAdmin {
	public static void checkAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Test ob user angemeldet ist
		// Session Bean erhalten		
		try {
			if (request.getSession(false).getAttribute("isAdmin").equals(false)) {
//				final RequestDispatcher dispatcher = request.getRequestDispatcher("/eingang/login.html");
//				dispatcher.forward(request, response);
				response.sendRedirect("hauptseiteservlet");
			}
		} 
		catch (Exception ex) {
//			final RequestDispatcher dispatcher = request.getRequestDispatcher("/eingang/login.html");
//			dispatcher.forward(request, response);
			response.sendRedirect("hauptseiteservlet");
		}
	}
}
