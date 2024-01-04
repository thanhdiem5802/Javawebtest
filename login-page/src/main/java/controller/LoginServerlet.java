package controller;

import java.sql.Statement;

import DAL.DBconnection;
import DAL.UserDAO;
import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class LoginServerlet
 */
public class LoginServerlet extends HttpServlet {
	public static final String SUCCESS = "Home.jsp";
	public static final String ERROR = "invalid.html";
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServerlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    response.setContentType("text/html;charset=UTF-8");
	    HttpSession session = request.getSession(true);
	    String url = ERROR;
	    try {
	        String user = request.getParameter("user");
	        String pass = request.getParameter("pass");
	        UserDAO dao = new UserDAO();
	        User authenticatedUser = dao.checkLogin(user, pass);
	        if (authenticatedUser != null) {
	            // Authentication successful
//	            session.setAttribute("authenticatedUser", authenticatedUser);
	            url = SUCCESS; // Redirect to a success page
	            session.setAttribute("authenticatedUser", authenticatedUser);
	            
	            	
	        } else {
	            // Authentication failed
	            url = ERROR; // Redirect to an error page
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); // Handle the exception or log it
	    } finally {
	        response.sendRedirect(url);
	    }
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
