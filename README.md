 Overview

This project is a simple web application that allows users to register and log in. It utilizes Java Servlets for backend processing, MySQL for data storage, and HTML/CSS for the frontend. The application is designed with a clean and user-friendly interface.

## Features

- User registration
- User login
- Password validation
- Username uniqueness check
- Responsive design

## Technologies Used

- Frontend- HTML, CSS
- Backend- Java, Servlet API
- Database- MySQL
- Environment- Apache Tomcat (or any compatible servlet container)

## Getting Started

### Prerequisites

1. Java Development Kit (JDK)
2. Apache Tomcat Server
3. MySQL Server
4. MySQL Connector/J (JDBC driver)

### Setup Instructions

1. -Clone the Repository-
   ```bash
   git clone https://github.com/yourusername/your-repository-name.git
   cd your-repository-name
   ```

2. Database Setup
   - Create a new database in MySQL named `login_register`.
   - Execute the following SQL command to create the required `users` table:
     ```sql
     CREATE TABLE users (
         id INT AUTO_INCREMENT PRIMARY KEY,
         username VARCHAR(50) NOT NULL UNIQUE,
         name VARCHAR(100) NOT NULL,
         email VARCHAR(100) NOT NULL,
         password VARCHAR(100) NOT NULL
     );
     ```

3. Configure Database Connection
   - Open the `LoginServlet.java` and `RegisterServlet.java` files.
   - Update the database connection details (username and password) accordingly:
     ```java
     private static final String JDBC_USER = "your_db_username";
     private static final String JDBC_PASSWORD = "your_db_password";
     ```

4. Deploy the Application
   - Copy the project folder into the `webapps` directory of your Apache Tomcat installation.
   - Start the Tomcat server.

5. **Access the Application:**
   - Open your web browser and go to:
     ```
     http://localhost:8080/your-repository-name/Home.jsp
     ```

## Usage

- Registration- Navigate to the registration page and fill out the form. Make sure the passwords match.
  
- Login- Use your registered credentials to log in.

## Contributions

Feel free to contribute to this project! You can submit issues, fork the repository, and create pull requests for improvements.

## License

This project is open-source and available under the MIT License.

## Acknowledgments

- [Java Documentation](https://docs.oracle.com/javase/8/docs/)
- [MySQL Documentation](https://dev.mysql.com/doc/)

For any questions or support, please reach out via GitHub issues.

---

Feel free to customize the README with your specific project details and links!
