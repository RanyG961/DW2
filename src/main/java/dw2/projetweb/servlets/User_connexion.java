package dw2.projetweb.servlets;

import dw2.projetweb.beans.User;
import dw2.projetweb.forms.FormUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/User_connexion")
public class User_connexion extends HttpServlet
{
    private FormUser formU = new FormUser();

    public User_connexion()
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
        this.getServletContext().getRequestDispatcher("/WEB-INF/Site/user_connexion.jsp").forward(req, resp);
    }

    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        User u = new User();
        HttpSession session = req.getSession();

        try
        {
            u = formU.connexion(req);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        req.setAttribute("formU", formU);
        req.setAttribute("User", u);

        if (formU.getErreurs().isEmpty())
        {
            session.setAttribute("sessionU", u);
            this.getServletContext().getRequestDispatcher("/WEB-INF/Site/espaceUtilisateur.jsp").forward(req, resp);
        } else
        {
            this.getServletContext().getRequestDispatcher("/WEB-INF/Site/user_connexion.jsp").forward(req, resp);
        }
    }
}
