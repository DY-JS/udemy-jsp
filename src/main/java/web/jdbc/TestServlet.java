package web.jdbc;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(name = "TestServlet", value = "/TestServlet")
public class TestServlet extends HttpServlet {
    //define datasource connection pool for resource injection
    @Resource(name = "jdbc/students")
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //1. Set up the printWriter
        PrintWriter out = response.getWriter();

        //2. Get a connection to DB
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myConn = dataSource.getConnection();
            //3 Create a SQL statements
            String sqlQuery = "SELECT * FROM students";
            myStmt = myConn.createStatement();
            //4  Execute SQL Query
            myRs = myStmt.executeQuery(sqlQuery);
            //5  Process the result set
            while (myRs.next()) {
                String email = myRs.getString("email");
                out.println(email);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
