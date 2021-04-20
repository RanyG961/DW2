package dw2.projetweb.servlets;

import dw2.projetweb.forms.FormUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/User_connexion")
public class User_connexion extends HttpServlet
{
    private FormUser formU = new FormUser();

    public User_connexion()
    {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        this.getServletContext().getRequestDispatcher("/WEB-INF/Site/user_connexion.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        try
        {
            if(formU.verifAccount(req))
            {
                this.getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
            }
            else
            {
                this.getServletContext().getRequestDispatcher("/WEB-INF/Site/user_connexion.jsp").forward(req, resp);
            }
        } catch (Exception throwables)
        {
            throwables.printStackTrace();
        }
    }
}
