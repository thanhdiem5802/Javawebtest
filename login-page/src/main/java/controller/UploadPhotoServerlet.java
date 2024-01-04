package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import DAL.UserDAO;
import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/UploadPhoto")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10,      // 10MB
                 maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class UploadPhotoServerlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadPhotoServerlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("photo");
        String urlImage = getFileName(filePart);
        UserDAO dao = new UserDAO();
        
        // Define the directory where uploaded images will be stored
        String UPLOAD_DIRECTORY = getServletContext().getRealPath("/") + "image";

        // Check if the directory exists; if not, create it
        File uploadDir = new File(UPLOAD_DIRECTORY);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Define the full path where the uploaded image will be stored
        String filePath = UPLOAD_DIRECTORY + File.separator + urlImage;
        
        InputStream input = null;
        OutputStream output = null;

        try {
            input = filePart.getInputStream();
            output = new FileOutputStream(filePath);

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }

            String uploadMessage = "Upload successful! ";
            request.setAttribute("uploadMessage", uploadMessage);

            // Construct the image path without the context path
            String imagePath = "image/" + urlImage;
            request.setAttribute("imagePath", imagePath);
            System.out.println("Uploaded image stored at: " + filePath.replace("\\", "/"));
            System.out.println("Image path is " + imagePath);

            // Retrieve the authenticated user from the session
            User authenticatedUser = (User) request.getSession().getAttribute("authenticatedUser");
            
            if (authenticatedUser != null) {
                // Update the user's URL image in the database
            	authenticatedUser.setUrlimage(imagePath);
                dao.uploadImage(authenticatedUser.getUser(), imagePath); 
                getServletContext().getRequestDispatcher("/userprofile.jsp").forward(request, response);
            } else {
                // Handle the case where the user is not authenticated
                response.sendRedirect("login.jsp"); // Redirect to login page or handle it as needed
            }
        } catch (IOException e) {
            e.printStackTrace();
            String uploadMessage = "Upload failed!";
            request.setAttribute("uploadMessage", uploadMessage);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database-related exceptions
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions
        } finally {
            // Close resources in a finally block
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private String getFileName(Part part) {
        String contentDispositionHeader = part.getHeader("content-disposition");
        for (String headerPart : contentDispositionHeader.split(";")) {
            if (headerPart.trim().startsWith("filename")) {
                return headerPart.substring(headerPart.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
