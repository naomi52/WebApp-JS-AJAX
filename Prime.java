package ctrl;

import java.io.IOException;
import java.io.Writer;
import java.math.BigInteger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Engine;

/**
 * Servlet implementation class Prime
 */
@WebServlet("/Prime.do")
public class Prime extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Prime() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Engine brain = Engine.getInstance();

        String min = request.getParameter("min");
        String max = request.getParameter("max");
        String prv = request.getParameter("prv");
        String opt = request.getParameter("opt");
        /*
        System.out.println("min= " + min);
        System.out.println("max= " +max);
        System.out.println("prv= " +prv);
        System.out.println("opt= " +opt);*/
       
        Writer out = response.getWriter();
        response.setContentType("text/json");

        try {

            if (opt.equals("find")) {

                BigInteger ans = brain.doPrime(min, max);
                out.write("{\"status\":1, \"result\":" + ans + "}");
            }
            else {// Next button is pressed

                BigInteger ans = brain.doPrime(prv, max);
                out.write("{\"status\":1, \"result\":" + ans + "}");

            }

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