package dw2.projetweb.servlets;

import dw2.projetweb.beans.User;
import dw2.projetweb.forms.FormUser;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;

@WebServlet("/User_inscription")
public class User_inscription extends HttpServlet
{
    private FormUser formU = new FormUser();

    public User_inscription()
    {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        this.getServletContext().getRequestDispatcher("/WEB-INF/Site/user_inscription.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        User u = new User();
        HttpSession session = req.getSession();

        try
        {
            u = formU.inscrireUser(req);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }

        req.setAttribute("formU", formU);
        req.setAttribute("User", u);

        if(formU.getErreurs().isEmpty())
        {
            session.setAttribute("sessionU", u);
            this.getServletContext().getRequestDispatcher("/WEB-INF/Site/espaceUtilisateur.jsp").forward(req, resp);
        }
        else
        {
            this.getServletContext().getRequestDispatcher("/WEB-INF/Site/user_inscription.jsp").forward(req, resp);
        }

    }
}
