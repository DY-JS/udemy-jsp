package servletdemo;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/mvcdemo")
public class MvcDemoServlet extends HttpServlet {
    private  static final long serialVersionUID = 1L;

    public MvcDemoServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //0. add data
        String[] students = {"Susi", "Anil", "Misha", "Roma"};
        req.setAttribute("student_list", students);
        //1. get request dispatcher
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view_students.jsp");
        //3. forward the request to jsp
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
