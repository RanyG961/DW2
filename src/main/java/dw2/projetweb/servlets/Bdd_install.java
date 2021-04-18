package dw2.projetweb.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import dw2.projetweb.beans.User;
import dw2.projetweb.forms.formInstallAdmin;

@WebServlet("/Bdd_install")
public class Bdd_install extends HttpServlet
{
    public Bdd_install()
    {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // appel la jsp
       this.getServletContext().getRequestDispatcher("/WEB-INF/Admin/bdd_install.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        formInstallAdmin formIA = new formInstallAdmin();

        System.out.println("Salut");

        try
        {
            if (!formIA.adminExists())
            {
//                formIA.createTable(req);

                User u = formIA.inscrireUser(req);

                req.setAttribute("formIA", formIA);
                req.setAttribute("User", u);

                this.getServletContext().getRequestDispatcher("/WEB-INF/Admin/bdd_install.jsp").forward(req, resp);
            }
            else
            {
                System.out.println("Je suis la");
                this.getServletContext().getRequestDispatcher("/WEB-INF/Admin/Connexion.jsp").forward(req, resp);
            }
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }
}
