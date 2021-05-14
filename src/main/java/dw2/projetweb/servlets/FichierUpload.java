package dw2.projetweb.servlets;

import dw2.projetweb.beans.Fichier;
import dw2.projetweb.forms.FormFichier;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet("/FichierUpload")
@MultipartConfig(maxFileSize = 10485760, maxRequestSize = 52428800,fileSizeThreshold = 1048576)
public class FichierUpload extends HttpServlet
{
    public static final String VUE = "/WEB-INF/Site/espaceUtilisateur.jsp";
    public static final String CHEMIN = "chemin";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        this.getServletContext().getRequestDispatcher(VUE).forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        Path path = Paths.get(req.getServletContext().getRealPath("Documents"));

        FormFichier f = new FormFichier();
        Fichier file = f.saveFile(req, path);

        req.setAttribute("form", f);
        req.setAttribute("fichier", file);

        this.getServletContext().getRequestDispatcher(VUE).forward(req, res);
    }
}
