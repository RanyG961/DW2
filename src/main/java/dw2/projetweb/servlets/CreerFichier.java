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
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet("/CreerFichier")
public class CreerFichier extends HttpServlet
{
    public static final String VUE = "/WEB-INF/Site/espaceUtilisateur.jsp";
    public static final String VUE2 = "/espaceUtilisateur.jsp";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        this.getServletContext().getRequestDispatcher(VUE).forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        Path path = Paths.get(req.getServletContext().getRealPath("Documents"));
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("sessionU");


        FormFichier f = new FormFichier();
        Fichier file = f.creerFichier(req, path, u);

        req.setAttribute("form", f);
        req.setAttribute("fichier", file);

        this.getServletContext().getRequestDispatcher(VUE2).forward(req, res);
    }
}
