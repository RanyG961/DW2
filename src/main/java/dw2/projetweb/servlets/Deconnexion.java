package dw2.projetweb.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Deconnexion")
public class Deconnexion extends HttpServlet
{
    public Deconnexion()
    {
        super();
    }

    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        HttpSession session = req.getSession();

        session.invalidate();
//        System.out.println(req.getContextPath());
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
}
