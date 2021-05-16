package dw2.projetweb.servlets;

import dw2.projetweb.forms.FormFichier;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DonnerDroit")
public class DonnerDroit extends HttpServlet
{
    public static final String VUE = "/WEB-INF/Site/espaceUtilisateur.jsp";
    public static final String SERVLET = "/EspaceUtilisateur";
    private static final FormFichier f = new FormFichier();

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
        this.getServletContext().getRequestDispatcher(SERVLET).forward(req, res);
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
        String lUserDoc = req.getParameter("listeUtilisateur");
        String[] lUserDocs = lUserDoc.split(",");
        String nomAmi = lUserDocs[0];
        int documentId = Integer.parseInt(lUserDocs[1]);

        try
        {
            f.donnerDroit(nomAmi, documentId);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        this.getServletContext().getRequestDispatcher(SERVLET).forward(req, res);

    }
}
