package servletdemo;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/testparam")
public class TestParamServlet extends HttpServlet {
    private  static final long serialVersionUID = 1L;

    public TestParamServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //1. set the content type
        resp.setContentType("text/html");
        //2. get the printwriter
        PrintWriter out = resp.getWriter();
        //3. read configuration params
        ServletContext context = getServletContext();
        String maxCartSize = context.getInitParameter("max-shopping-cart-size");
        String teamName = context.getInitParameter("project-team-name");
        //4. generate HTML content
        out.println("<html><body>");
        out.println("Max cart:" + maxCartSize);
        out.println("<br>");
        out.println("Team name:" + teamName);
        out.println("<hr>");
        out.println("Time on server is: " + new java.util.Date());
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
