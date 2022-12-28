package web.jdbc;

import model.Student;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/StudentControllerServlet")
// или @WebServlet(name = "StudentControllerServlet", value = "/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
    private StudentDBUtil studentDBUtil;
    @Resource(name="jdbc/students")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();
        //create our student db util ... and pass in the conn pool / datasource
        try {
            studentDBUtil = new StudentDBUtil(dataSource);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //get list of students from DB
        try {
            // read the "command" parameter (from add-form)
            String command = request.getParameter("command");

            //if command is missing, then default listing all students
            if (command == null) {
                command = "student_list";
            }

            //route to appropriate method (in switch case)
            switch(command) {
                case "student_list":
                    getAllStudents(request, response);
                    break;

                case "ADD":
                    addStudent(request, response);
                    break;
                    
                case "GET-BY-ID": //get student from DB
                    getStudentById(request, response);
                    break;
               
                case "UPDATE": 
                    updateStudent(request, response);

                case "DELETE":
                    deleteStudent(request, response);
                    break;

                case "SEARCH":
                    searchStudents(request, response);
                    break;

                default:
                    getAllStudents(request, response);
                    break;
            }


            getAllStudents(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
        throws  Exception {
        // read student id from data
        String studentId = request.getParameter("studentId");

        //delete student from database
        studentDBUtil.deleteStudent(studentId);

        //send them back to "list students" page
        getAllStudents(request, response);

    }

    private void searchStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // read search name from form data
        String theSearchName = request.getParameter("theSearchName");

        // search students from db util
        List<Student> students = studentDBUtil.searchStudents(theSearchName);

        // add students to the request
        request.setAttribute("student_list", students);

        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
        dispatcher.forward(request, response);
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response)
        throws Exception{
        //read student info from data
        int id = Integer.parseInt(request.getParameter("studentId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        //create a new student object
        Student student = new Student(id, firstName, lastName, email);

        //perform update on DB
        studentDBUtil.updateStudent(student);

        // send them back to the "list of students" page
        getAllStudents(request, response);
    }

    private void getStudentById(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        // read student id from data
        String studentId = request.getParameter("studentId");

        // get student from DB (db util)
        Student student = studentDBUtil.getStudent(studentId);

        //place student in the request attribute
        request.setAttribute("STUDENT", student);

        // send to jsp page: update-student-form.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/update-student-form.jsp");
        dispatcher.forward(request, response);
    }

    private void addStudent(HttpServletRequest request, HttpServletResponse response)
            throws  Exception {
        // read student from add-form
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        //create a new student object
        Student student = new Student(firstName, lastName, email);
         //add the student to the DB
        studentDBUtil.addStudent(student);
        //send back to main page (the student list)
        getAllStudents(request, response);
    }

    private void getAllStudents(HttpServletRequest request, HttpServletResponse response)
            throws  Exception {
        //get list of students from DB util
        List<Student> allStudents = studentDBUtil.getStudents();
        //add students to the request
        request.setAttribute("student_list", allStudents);
        //send to JSP page(view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("list-students.jsp");
        dispatcher.forward(request, response);
    }
}