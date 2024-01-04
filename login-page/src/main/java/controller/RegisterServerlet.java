package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Connection;

import DAL.DBconnection;
import DAL.UserDAO;
import Model.User;

/**
 * Servlet implementation class RegisterServerlet
 */
public class RegisterServerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServerlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String country = request.getParameter("country"); 
        String cpass = request.getParameter("cpass");
        String urlimae = "image/userdefaul.jpg";
       
        if (!pass.equals(cpass)) {
            // Password and confirm password do not match
            String errorMessage = "Cannot confirm password";
            request.setAttribute("errorMessage", errorMessage);
            // Forward the request to the JSP page
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            UserDAO dao = new UserDAO();
            try {
				User u = dao.checkexsited(user);
				if(u != null) {
					
					String msg ="User existed!!";
					request.setAttribute("msg", msg);
					request.getRequestDispatcher("register.jsp").forward(request, response);
				}
				else {
	                User accout = new User(user, cpass, address, country, email, urlimae);

					dao.registerUser(accout);
					String successmsg = "Register successful";
							request.setAttribute("successmsg", successmsg);
							request.getRequestDispatcher("Home.jsp").forward(request, response);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
       
    }



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
