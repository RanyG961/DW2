package dw2.projetweb.servlets;

import dw2.projetweb.beans.User;
import dw2.projetweb.forms.FormAdmin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Admin_connexion")
public class Admin_connexion extends HttpServlet
{
    private FormAdmin formIA = new FormAdmin();

    public Admin_connexion()
    {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        this.getServletContext().getRequestDispatcher("/WEB-INF/Admin/admin_connexion.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        User u = new User();

        try
        {
            u = formIA.connexion(req);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        req.setAttribute("formIA", formIA);
        req.setAttribute("User", u);

        HttpSession session = req.getSession();

        if (formIA.getErreurs().isEmpty())
        {
            session.setAttribute("sessionU", u);
            this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
        } else
        {
            session.setAttribute("sessionU", null);
            this.getServletContext().getRequestDispatcher("/WEB-INF/Admin/admin_connexion.jsp").forward(req, resp);
        }
    }
}
