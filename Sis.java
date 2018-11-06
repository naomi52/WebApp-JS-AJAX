package ctrl;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Engine;

/**
 * Servlet implementation class Sis
 */
@WebServlet("/Sis.do")
public class Sis extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sis() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Engine engine = Engine.getInstance();

        String prefix = request.getParameter("pfx");
        String gpa = request.getParameter("gpa");
        String sortBy = request.getParameter("opt");
       
        Writer out = response.getWriter();
        response.setContentType("text/json");
       
        try {
           
            String ans = "\"" + engine.doSis(prefix, gpa, sortBy) + "\"";
            //
            out.write("{\"status\":1, \"result\":" + ans + "}");
           
        } catch (Exception e) {
            e.printStackTrace();
            out.write("{\"status\":0, \"error\":\"" + e.getMessage() + "\"}");
        }

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}

