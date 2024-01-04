package Model;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ImageUploader {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/account";
        String username = "root";
        String password = "root";
        
        String imagePath = "C:\\Users\\Dell\\eclipse-workspace\\login-page\\src\\main\\webapp\\image\\userdefault.jpg"; // Adjust the path to your image file
        
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Connect to the database
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            
            // Read the image file into a byte array
            File imageFile = new File(imagePath);
            FileInputStream fis = new FileInputStream(imageFile);
            byte[] imageData = new byte[(int) imageFile.length()];
            fis.read(imageData);
            fis.close();
            
            // SQL query to insert the image data into the database
            String sql = "update user set urlimage=?";
            
            // Create a prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            // Set the image data as a BLOB parameter
            preparedStatement.setBytes(1, imageData);
            
            // Execute the insert statement
            preparedStatement.executeUpdate();
            
            // Close the database connection
            connection.close();
            
            System.out.println("Image inserted successfully into the database.");
        } catch (Exception e) {
            e.printStackTrace();
        }
//        try { 
//        	  
//            // Create a file object 
//            File f = new File("Example.docx"); 
//  
//            // Get the absolute path of file f 
//            String absolute = f.getAbsolutePath(); 
//  
//            // Display the file path of the file object 
//            // and also the file path of absolute file 
//            System.out.println("Original  path: "
//                               + f.getPath()); 
//            System.out.println("Absolute  path: "
//                               + absolute); 
//        } 
//        catch (Exception e) { 
//            System.err.println(e.getMessage()); 
//        } 
    }
}
