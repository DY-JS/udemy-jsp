package servletdemo;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/student-from-form")
public class StudentFromFormServlet extends HttpServlet {
    private  static final long serialVersionUID = 1L;

    public StudentFromFormServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //1. set the content type
        resp.setContentType("text/html");
        //2. get the printwriter
        PrintWriter out = resp.getWriter();
        //3. generate html content
        out.println("<html><body>");
        out.println("The student is confirmed:" + req.getParameter("firstName")
        + " " + req.getParameter("lastName"));
        out.println("<hr>");
        out.println("Time on server is: " + new java.util.Date());
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
