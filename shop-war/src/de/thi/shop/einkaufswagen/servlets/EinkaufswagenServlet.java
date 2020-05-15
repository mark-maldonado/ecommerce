// CREATED BY MARK MALDONADO

package de.thi.shop.einkaufswagen.servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/einkaufswagenservlet")
public class EinkaufswagenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// get Datasource
	@Resource(lookup="java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}	