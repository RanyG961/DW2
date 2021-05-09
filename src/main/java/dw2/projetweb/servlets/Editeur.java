package dw2.projetweb.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Editeur")
public class Editeur extends HttpServlet
{
    @Override
    public void init() throws ServletException
    {
        super.init();
    }

    public Editeur()
    {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException
    {
        System.out.println("Salut Editeur");
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException
    {
        super.doPost(httpServletRequest, httpServletResponse);
    }
}
