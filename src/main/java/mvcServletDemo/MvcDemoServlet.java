package mvcServletDemo;

import model.Student;
import utils.StudentDataUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/mvcdemoservlet")
public class MvcDemoServlet extends HttpServlet {
    public MvcDemoServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //1. get data from helper class(model)
        List<Student> students = StudentDataUtil.getStudents();
        //2 add students to request object
        req.setAttribute("student_list", students);
        //3 get request dispatcher
        RequestDispatcher dispatcher = req.getRequestDispatcher("/mvc-students.jsp");
        //4. forward the request to JSP
        dispatcher.forward(req, resp);
    }

}
