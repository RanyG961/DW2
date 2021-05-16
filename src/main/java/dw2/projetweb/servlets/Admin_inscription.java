package dw2.projetweb.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import dw2.projetweb.beans.User;
import dw2.projetweb.forms.FormAdmin;

@WebServlet("/Admin_inscription")
public class Admin_inscription extends HttpServlet
{
    private FormAdmin formIA = new FormAdmin();

    public Admin_inscription()
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
        try
        {
            if(formIA.tableExists())
            {
                if(!formIA.adminExists())
                {
                    this.getServletContext().getRequestDispatcher("/WEB-INF/Admin/admin_inscription.jsp").forward(req, resp);
                }
                else
                {
                    this.getServletContext().getRequestDispatcher("/WEB-INF/Admin/admin_connexion.jsp").forward(req, resp);
                }
            }
            else
            {
                formIA.createTable(req);
                this.getServletContext().getRequestDispatcher("/WEB-INF/Admin/admin_inscription.jsp").forward(req, resp);
            }

        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
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
        try
        {
            u = formIA.inscrireUser(req);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }

        req.setAttribute("formIA", formIA);
        req.setAttribute("User", u);

        if(formIA.getErreurs().isEmpty())
        {
            this.getServletContext().getRequestDispatcher("/WEB-INF/Admin/admin_connexion.jsp").forward(req, resp);
        }
        else
        {
            this.getServletContext().getRequestDispatcher("/WEB-INF/Admin/admin_inscription.jsp").forward(req, resp);

        }
    }
}
