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
import dw2.projetweb.forms.formAdmin;

@WebServlet("/Admin_install")
public class Admin_install extends HttpServlet
{
    private formAdmin formIA = new formAdmin();

    public Admin_install()
    {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        try
        {
            if(formIA.tableExists())
            {
                if(!formIA.adminExists())
                {
                    this.getServletContext().getRequestDispatcher("/WEB-INF/Admin/admin_install.jsp").forward(req, resp);
                }
                else
                {
                    this.getServletContext().getRequestDispatcher("/WEB-INF/Admin/admin_connexion.jsp").forward(req, resp);
                }
            }
            else
            {
                formIA.createTable(req);
                this.getServletContext().getRequestDispatcher("/WEB-INF/Admin/admin_install.jsp").forward(req, resp);
            }

        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        User u = null;
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
            this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
        }
        else
        {
            this.getServletContext().getRequestDispatcher("/WEB-INF/Admin/admin_install.jsp").forward(req, resp);

        }
    }
}
