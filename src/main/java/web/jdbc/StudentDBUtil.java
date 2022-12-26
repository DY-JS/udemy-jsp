package web.jdbc;

import model.Student;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDBUtil {
    private DataSource dataSource;

    public StudentDBUtil(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Student> getStudents() throws Exception {
        List<Student> students = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            //get a connection
            myConn = dataSource.getConnection();
            //create sql statement
            String sqlQuery = "SELECT * FROM students ORDER BY lastname";
            myStmt = myConn.createStatement();
            //execute query
            myRs = myStmt.executeQuery(sqlQuery);
            //process result set
            while (myRs.next()) {
                //retrieve data from result set row
                int id = myRs.getInt("id");
                String firstName = myRs.getString("firstname");
                String lastName = myRs.getString("lastname");
                String email = myRs.getString("email");
                //create new student
                Student student = new Student(id, firstName, lastName, email);
                //add student to List
                students.add(student);
            }

            return students;
        } finally {
            //close JDBC objects
            close(myConn, myRs, myStmt);
        }
    }

    private void close(Connection myConn, ResultSet myRs, Statement myStmt) {
        try {
            if (myRs != null) {
               myRs.close();
            }

            if (myStmt != null) {
                myStmt.close();
            }

            if (myConn != null) { //puts back in connection pool
                myConn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Student addStudent(Student student) throws Exception {
        Connection myConn = null;
        PreparedStatement myStmt = null;
        //ResultSet myRs=null;  //c генерацией ключа

        try {
            //get db connection
            myConn = dataSource.getConnection();

            //create sql for insert
            String sqlQuery = "INSERT INTO students (firstname, lastname, email) "
                    + "VALUES (?, ?, ?)";
            //c генерацией ключа
            //myStmt = myConn.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
            myStmt = myConn.prepareStatement(sqlQuery); //без генерации ключа
            //set the param values for the student
            myStmt.setString(1, student.getFirstName());
            myStmt.setString(2, student.getLastName());
            myStmt.setString(3, student.getEmail());
            //execute sql insert-------
//            myStmt.executeUpdate();
//            myRs = myStmt.getGeneratedKeys();  //c генерацией ключа
//            if (myRs.next()) {
//                student.setId(myRs.getInt(1));
//            }   ИЛИ:
            myStmt.execute();
            return student;
        } finally {
            //clean up JDBC objects
            close(myConn, null, myStmt);
            //close(myConn, myRs, myStmt);
        }

    }

    public Student getStudent(String studentId)
        throws Exception {
        Student student = null;

        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        int id;

        try {
            //convert student id to int
            id = Integer.parseInt(studentId);

            //get connection to db
            myConn = dataSource.getConnection();

            //create sql to get selected student
            String sqlQuery = "SELECT * FROM students WHERE id = ?";

            //create prepared statement
            myStmt = myConn.prepareStatement(sqlQuery);

            //set params
            myStmt.setInt(1, id);

            //execute statement
            myRs = myStmt.executeQuery();

            //retrieve data from resultset row
            if (myRs.next()) {
                String firstName = myRs.getString("firstname");
                String lastName = myRs.getString("lastname");
                String email = myRs.getString("email");

                // use studentId during construction
                student = new Student(id, firstName, lastName, email);
                System.out.printf("s " + student);
            } else {
                throw new Exception("Could not find student id: " + studentId);
            }

            return student;
        } finally {
            //clean up JDBC objects
            close(myConn, myRs, myStmt);
        }
    }

    public void updeteStudent(Student student) throws  Exception {
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            //getDB connection
            myConn = dataSource.getConnection();

            //create SQL updateStatement
            String sqlQuery = "UPDATE students SET firstname=?, lastname=?, email=? WHERE id=?";

            //prepare statement
            myStmt = myConn.prepareStatement(sqlQuery);

            //set params (вместо ? в SQL запросе)
            myStmt.setString(1, student.getFirstName());
            myStmt.setString(2, student.getLastName());
            myStmt.setString(3, student.getEmail());
            myStmt.setInt(4, student.getId());
            //execute SQL statement
            myStmt.execute();
        } finally {
            //clean up JDBC objects
            close(myConn, null, myStmt);
        }
    }

    public void deleteStudent(String studentId) throws Exception{

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            // convert studentId to int
            int id = Integer.parseInt(studentId);
            //get connection to database
            myConn = dataSource.getConnection();
            //create sql to delete student
            String sqlQuery = "DELETE FROM students WHERE id=?";

            //prepare statement
            myStmt = myConn.prepareStatement(sqlQuery);

            //set params
            myStmt.setInt(1, id);
            //execute sql statement
            myStmt.execute();
        } finally {
            //clean up JDBC code
            close(myConn, null, myStmt);
        }
    }

    public List<Student> searchStudents(String theSearchName)  throws Exception {
        List<Student> students = new ArrayList<>();

        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        int studentId;

        try {
            // get connection to database
            myConn = dataSource.getConnection();
            // only search by name if theSearchName is not empty
            if (theSearchName != null && theSearchName.trim().length() > 0) {
                // create sql to search for students by name
                String sql = "select * from students where lower(firstname) like ? or lower(lastname) like ?";
                // create prepared statement
                myStmt = myConn.prepareStatement(sql);
                // set params
                String theSearchNameLike = "%" + theSearchName.toLowerCase() + "%";
                myStmt.setString(1, theSearchNameLike);
                myStmt.setString(2, theSearchNameLike);

            } else {
                // create sql to get all students
                String sql = "select * from students order by lastname";
                // create prepared statement
                myStmt = myConn.prepareStatement(sql);
            }

            // execute statement
            myRs = myStmt.executeQuery();
            // retrieve data from result set row
            while (myRs.next()) {
                // retrieve data from result set row
                int id = myRs.getInt("id");
                String firstName = myRs.getString("firstname");
                String lastName = myRs.getString("lastname");
                String email = myRs.getString("email");
                // create new student object
                Student tempStudent = new Student(id, firstName, lastName, email);
                // add it to the list of students
                students.add(tempStudent);
            }

            return students;
        }
        finally {
            // clean up JDBC objects
            close(myConn, myRs, myStmt);
        }
    }
}
