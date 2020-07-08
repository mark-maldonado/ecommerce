// CREATED BY MARK MALDONADO

package de.thi.shop.user.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckAdmin {
	public static void checkAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Test Attribut "isAdmin" = false		
		try {
			if (request.getSession(false).getAttribute("isAdmin").equals(false)) {
				response.sendRedirect("hauptseiteservlet");
			}
		} 
		catch (Exception ex) {
			response.sendRedirect("hauptseiteservlet");
		}
	}
}
