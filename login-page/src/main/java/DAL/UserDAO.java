package DAL;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.User;

public class UserDAO extends DBconnection {
	private Connection conn = null;
	private PreparedStatement pre = null;
	private ResultSet rs = null;

	public List<User> getAll() {
		List<User> list = new ArrayList<>();
		String sql = "SELECT * FROM user";

		try (PreparedStatement st = connection.prepareStatement(sql); ResultSet rs = st.executeQuery()) {

			while (rs.next()) {
				// Create a User object and populate it with data from the ResultSet
				User user = new User();
				user.setUser(rs.getString("user"));
				user.setPassword(rs.getString("password"));

				// Add the User object to the list
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Handle the exception or throw a custom exception as needed
		}

		return list;

	}

	private void closeConnection() throws Exception {
		if (rs != null) {
			rs.close();
		}
		if (pre != null) {
			pre.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

	public User checkLogin(String user, String password) throws SQLException, Exception {
		User result = null;
		try {
			conn = connection;
			if (conn != null) {
				String sql = "SELECT * FROM user WHERE user = ? AND password = ?";
				pre = conn.prepareStatement(sql);
				pre.setString(1, user);
				pre.setString(2, password);
				rs = pre.executeQuery();
				if (rs.next()) {
					String email = rs.getString("email");
					String address = rs.getString("address");
					String country = rs.getString("country");
					String urlimage  = rs.getString("urlimage");
					result = new User(user, password, address, country, email, urlimage);
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); // Handle the exception or log it
		} finally {
//			closeConnection();
		}
		return result;
	}

	public void registerUser(User u) throws Exception,SQLException {

		try {
			conn = connection;
			if (conn != null) {
				String sql = "INSERT INTO user  VALUES (?, ?, ?, ?, ?,?)";						
				pre = conn.prepareStatement(sql);
				pre.setString(1, u.getUser());
				pre.setString(2, u.getPassword());
				pre.setString(3, u.getEmail());
				pre.setString(4, u.getAddress());
				pre.setString(5, u.getCountry());
				pre.setString(6, u.getUrlimage());
				pre.executeUpdate();

			}

		} catch (Exception e) {
			e.printStackTrace(); // Handle the exception or log it
		} finally {
//			closeConnection();
		}
		

	}
	public User uploadImage(String username, String urlImage) throws Exception, SQLException {
	    User result = null;
	    try {
	        conn = connection;
	        if (conn != null) {
	            String sql = "UPDATE user SET urlimage = ? WHERE user = ?";
	            pre = conn.prepareStatement(sql);
	            pre.setString(1, urlImage);
	            pre.setString(2, username);
	            
	            // Use executeUpdate for an UPDATE statement
	            int rowsAffected = pre.executeUpdate();
	            
	            if (rowsAffected > 0) {
	                System.out.println("Upload Success");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	       
	    }
	    return result;
	}
	
	public User Updateuser(String pass, String email, String address, String country, String user,String urlimgae) throws Exception, SQLException {
		 User updatedUser = null; // Initialize to null
	    try {
	        conn = connection;
	        if (conn != null) {
	            // Check if the user exists
	            User existingUser = checkexsited(user);

	            if (existingUser != null) {
	                String sql = "UPDATE user SET password = ?, email = ?, address = ?, Country = ?,urlimage= ? WHERE user = ?";
	                pre = conn.prepareStatement(sql);
	                pre.setString(1, pass);
	                pre.setString(2, email);
	                pre.setString(3, address);
	                pre.setString(4, country);
	                pre.setString(5, urlimgae);
	                pre.setString(6, user);
	                pre.executeUpdate();
//	                
	                updatedUser = new User(user, pass, address, country, email, urlimgae) ;
           }
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); // Handle the exception or log it
	    } finally {
	        // closeConnection();
	    }
	    
	    return updatedUser; 
	}


	// Helper method to check if a user exists
	


	public User checkexsited(String user) throws SQLException, Exception {

		try {
			conn = connection;
			if (conn != null) {
				String sql = "SELECT * FROM user WHERE user = ? ";
				pre = conn.prepareStatement(sql);
				pre.setString(1, user);

				rs = pre.executeQuery();
				if (rs.next()) {

					return new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6));
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); // Handle the exception or log it
		} finally {
//			closeConnection();
		}
		return null;
		
	}
	public static void main(String[] args) {
	    String usernameToCheck = "thanhdiem"; // Replace with the username you want to check
	    UserDAO dao = new UserDAO();

	    try {
	        User user = dao.checkexsited(usernameToCheck);
	        if (user != null) {
	            System.out.println("User exists:");
	            System.out.println("Username: " + user.getUser());
	            System.out.println("Email: " + user.getEmail());
	            System.out.println("url: " + user.getUrlimage());
	            // Print other user information as needed
	        } else {
	            System.out.println("User does not exist.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Handle database-related exceptions
	    } catch (Exception e) {
	        e.printStackTrace();
	        // Handle other exceptions
	    }
	}
}

	
	




	    


