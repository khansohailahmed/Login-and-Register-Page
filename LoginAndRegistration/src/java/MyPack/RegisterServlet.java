package MyPack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // Fetch user data from the form
            String username = request.getParameter("username");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");

            // Validate passwords match
            if (!password.equals(confirmPassword)) {
                out.println("<h3 style='color:red;'>Passwords do not match!</h3>");
                return;
            }

            // Establish database connection
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");  // Load MySQL JDBC Driver
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_register", "root", "Sohail0622");

                // Check if username is already taken
                String query = "SELECT * FROM users WHERE username=?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    out.println("<h3 style='color:red;'>Username is already taken!</h3>");
                } else {
                    // Insert user data into the database
                    String insertQuery = "INSERT INTO users(username, name, email, password) VALUES (?, ?, ?, ?)";
                    PreparedStatement insertPs = con.prepareStatement(insertQuery);
                    insertPs.setString(1, username);
                    insertPs.setString(2, name);
                    insertPs.setString(3, email);
                    insertPs.setString(4, password);

                    int result = insertPs.executeUpdate();
                    if (result > 0) {
                        out.println("<h3 style='color:green;'>Registration successful!</h3>");
                        // Redirect to the login page after successful registration
                        response.sendRedirect("Login.jsp");  // Assuming you have a login.jsp page for user login
                    } else {
                        out.println("<h3 style='color:red;'>Registration failed. Please try again.</h3>");
                    }
                }

                // Close connection
                con.close();
            } catch (ClassNotFoundException | SQLException e) {
                out.println("<h3 style='color:red;'>Database error: " + e.getMessage() + "</h3>");
            }
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
        return "Registration Servlet";
    }
}
