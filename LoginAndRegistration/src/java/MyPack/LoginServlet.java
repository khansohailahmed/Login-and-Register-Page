package MyPack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

    // JDBC URL, username and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/login_register";
    private static final String JDBC_USER = "your_db_username";
    private static final String JDBC_PASSWORD = "your_db_password";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        try (PrintWriter out = response.getWriter()) {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish the database connection
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_register", "root", "Sohail0622")) {
                
                // Prepare SQL query to check if the user exists with the given username and password
                String query = "SELECT * FROM users WHERE username = ? AND password = ?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, username);
                pst.setString(2, password);
                
                // Execute the query
                ResultSet rs = pst.executeQuery();
                
                // Check if user exists
                if (rs.next()) {
                    out.println("<h3>Login successful! Welcome, " + rs.getString("name") + ".</h3>");
                } else {
                    out.println("<h3 style='color:red;'>Invalid username or password!</h3>");
                }
                
            } catch (Exception e) {
                e.printStackTrace();
                out.println("<h3 style='color:red;'>Database connection failed!</h3>");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Login Servlet";
    }
}
