package dw2.projetweb.servlets;

import dw2.projetweb.beans.User;
import dw2.projetweb.forms.FormFichier;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/EspaceUtilisateur")
public class EspaceUtilisateur extends HttpServlet
{
    private static final FormFichier f = new FormFichier();
    public static final String VUE = "/WEB-INF/Site/espaceUtilisateur.jsp";
    public EspaceUtilisateur()
    {
        super();
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        try
        {
            f.ajoutAmis(req);
            f.accepteAmis(req);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/Site/espaceUtilisateur.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        f.getDocumentsAccessibles(req);
        try
        {
            f.getNicknameUsers(req);
            f.demandeAmis(req);
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/Site/espaceUtilisateur.jsp").forward(req, res);
    }
}
