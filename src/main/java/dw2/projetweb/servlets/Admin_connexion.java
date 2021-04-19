package dw2.projetweb.servlets;

import dw2.projetweb.forms.formAdmin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/Admin_connexion")
public class Admin_connexion extends HttpServlet
{
    private formAdmin formIA = new formAdmin();

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    {
        try
        {
            if(formIA.verifAccount(req))
            {
                this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
            }
            else
            {
                this.getServletContext().getRequestDispatcher("/WEB-INF/Admin/admin_connexion.jsp").forward(req, resp);
            }
        } catch (Exception throwables)
        {
            throwables.printStackTrace();
        }
    }
}
