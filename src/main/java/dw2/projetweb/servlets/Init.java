package dw2.projetweb.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <b>Init est la servlet qui s'occupe de la gestion de la page d'accueil du site.</b>
 * <p>
 * 		Sur la page d'accueil,on peut :
 * <ul>
 * 		<li>Un monstre.</li>
 * 		<li>Une héro.</li>
 * </ul>
 *
 * @see Admin_connexion
 * @see Admin_inscription
 * @see User_connexion
 * @see User_inscription
 *
 */

@WebServlet("/Init")
public class Init extends HttpServlet
{
    /**
     *
     */
    public Init()
    {
        super();
    }

    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        super.doGet(req, resp);
    }
}
