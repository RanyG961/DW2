package dw2.projetweb.servlets;

import dw2.projetweb.beans.Fichier;
import dw2.projetweb.beans.User;
import dw2.projetweb.forms.FormFichier;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.stream.Stream;

@WebServlet("/CreerFichier")
public class CreerFichier extends HttpServlet
{
    public static final String VUE = "/WEB-INF/Site/espaceUtilisateur.jsp";
    public static final String VUE2 = "/WEB-INF/Site/editeurP.jsp";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        this.getServletContext().getRequestDispatcher(VUE).forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        Path path = Paths.get(getServletContext().getRealPath("/WEB-INF/documents"));
        System.out.println(path);
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("sessionU");


        FormFichier f = new FormFichier();
        Fichier file = null;
        try
        {
            file = f.creerFichier(req, path, u);
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

        req.setAttribute("form", f);
        req.setAttribute("fichier", file);

        this.getServletContext().getRequestDispatcher(VUE2).forward(req, res);
    }
}
