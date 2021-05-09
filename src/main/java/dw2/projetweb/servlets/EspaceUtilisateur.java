package dw2.projetweb.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/EspaceUtilisateur")
public class EspaceUtilisateur extends HttpServlet
{
    public EspaceUtilisateur()
    {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException
    {
        System.out.println("Salut Espace Utilisateur");
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException
    {
        super.doPost(httpServletRequest, httpServletResponse);
    }
}
