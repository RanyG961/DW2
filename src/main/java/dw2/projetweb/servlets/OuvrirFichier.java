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
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/OuvrirFichier")
public class OuvrirFichier extends HttpServlet
{

    public static final String VUE = "/WEB-INF/Site/espaceUtilisateur.jsp";
    public static final String VUE2 = "/WEB-INF/Site/editeurP.jsp";

    /**
     *
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
//        this.getServletContext().getRequestDispatcher(VUE).forward(req, res);
        Integer documentId = Integer.parseInt(req.getParameter("documentId"));

        FormFichier f = new FormFichier();
        Fichier fichier = new Fichier();
        try
        {
            String contenuFichier = f.readFile(documentId, fichier);
            req.setAttribute("form", f);
            req.setAttribute("contenuFichier", contenuFichier);
            req.setAttribute("fichier", fichier);
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

        this.getServletContext().getRequestDispatcher(VUE2).forward(req, res);
    }

    /**
     *
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
//            Path path = Paths.get(getServletContext().getRealPath("/WEB-INF/documents"));
//            System.out.println(path);
//            HttpSession session = req.getSession();
//            User u = (User) session.getAttribute("sessionU");
//
//
//            FormFichier f = new FormFichier();
//            Fichier file = f.creerFichier(req, path, u);
//
//            req.setAttribute("form", f);
//            req.setAttribute("fichier", file);
//
//            this.getServletContext().getRequestDispatcher(VUE2).forward(req, res);

        this.getServletContext().getRequestDispatcher(VUE).forward(req, res);
    }
}
