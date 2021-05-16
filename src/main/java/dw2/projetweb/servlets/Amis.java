package dw2.projetweb.servlets;

import dw2.projetweb.beans.User;
import dw2.projetweb.forms.FormFichier;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.io.IOException;

@WebServlet("/Amis")
public class Amis extends HttpServlet
{
    private static final FormFichier f = new FormFichier();
    public static final String VUE = "/WEB-INF/Site/espaceUtilisateur.jsp";
    public static final String SERVLET = "/EspaceUtilisateur";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        System.out.println("GET");
        this.getServletContext().getRequestDispatcher(SERVLET).forward(req, res);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        try
        {
            f.accepteAmis(req);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        this.getServletContext().getRequestDispatcher(SERVLET).forward(req, res);

    }
}
