//CREATED BY SEYIT ARAR

package de.thi.shop.user.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import de.thi.shop.user.beans.UserBean;


/**
 * Servlet implementation class AnmeldenServlet
 */
@WebServlet("/anmeldenservlet")
public class AnmeldenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(lookup="java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnmeldenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String email = request.getParameter("email");			//Holt die Attribute vom Login
		String passwort = request.getParameter("password");
		
		UserBean userBean = new UserBean(); 					//Neue Bean
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement("SELECT id,isAdmin FROM user WHERE mail = ? and passwort = ?")) {
			     
			pstmt.setString(1, email);
			pstmt.setString(2, passwort);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())										//Wenn ein Treffer, dann Attribute in die Bean und weiter
			{
				
				Long id = Long.valueOf(rs.getLong("id"));
				userBean.setId(id);
				userBean.setEmail(email);
				boolean isAdmin = rs.getBoolean("isAdmin");
				userBean.setIsAdmin(isAdmin);
				
				HttpSession session = request.getSession();
				session.setAttribute("userBean", userBean);
				session.setAttribute("isAdmin", isAdmin);
				
				response.sendRedirect("hauptseiteservlet");
			}
			else {

				response.sendRedirect("eingang/login_fehler.html");
				}
			} catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}

	

}
