package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Blob;

import DAL.UserDAO;
import Model.User;

/**
 * Servlet implementation class UpdataeSeverlet
 */
public class UpdataeSeverlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdataeSeverlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession(true);
		User authenticatedUser = (User) session.getAttribute("authenticatedUser");

	    if (authenticatedUser != null) {
	        String username = authenticatedUser.getUser(); // Get the username
	        String pass     = authenticatedUser.getPassword();
	        String email 	= request.getParameter("email");
	        String address  = request.getParameter("address");
	        String country  = request.getParameter("country");
	       
	        UserDAO dao = new UserDAO();
	        try {
				User updateuser = dao.Updateuser(pass, email, address, country, username,null );
				if (updateuser != null) {
					 authenticatedUser.setEmail(email);
					 authenticatedUser.setAddress(address);
					 authenticatedUser.setCountry(country);
					 String update ="Update Successful!!";
						request.setAttribute("update", update);
					request.getRequestDispatcher("userprofile.jsp").forward(request, response);
				} else {
					response.sendRedirect("editProfile.jsp");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	    } else {
	        response.sendRedirect("editProfile.jsp");
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
