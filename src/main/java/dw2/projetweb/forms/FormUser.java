package dw2.projetweb.forms;

import dw2.projetweb.beans.User;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public class FormUser extends Form
{
    /**
     *
     */
    private Form f = new Form();

    /**
     *
     * @param request
     * @return
     * @throws ParseException
     */
    public User inscrireUser(HttpServletRequest request) throws ParseException
    {
        return super.inscrireUser(request, false);
    }

    /**
     *
     * @param req
     * @return
     * @throws Exception
     */
    @Override
    public User connexion(HttpServletRequest req) throws Exception
    {
        return super.connexion(req);
    }
}
