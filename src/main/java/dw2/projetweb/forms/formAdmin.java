package dw2.projetweb.forms;
import dw2.projetweb.beans.User;
import dw2.projetweb.bdd.Connexion;
import dw2.projetweb.bdd.DbServer;
import org.mindrot.jbcrypt.BCrypt;
import static org.apache.commons.text.StringEscapeUtils.escapeHtml4;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FormAdmin extends Form
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
        return super.inscrireUser(request, true);
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

    /**
     * Vérifie si il existe un admin ou non
     * @return true si il existe un admin et false sinon
     * @throws SQLException
     */
    public boolean adminExists() throws SQLException
    {
        String admin = f.getS().requete("SELECT * FROM users WHERE is_admin = TRUE");

       return !admin.isEmpty();
    }

    /**
     * Vérifie si les tables existent ou non
     * @return true si elles existent pas
     * @throws SQLException
     */
    public boolean tableExists() throws SQLException
    {
        String nomBDD = f.getC().getJDBC_DBNAME();
        String req = "SHOW TABLES IN " + nomBDD;
        String res = f.getS().requete(req);

       if(Integer.parseInt(res) == 0)
           return false;
       return true;
    }

    /**
     * Crée les tables de la bdd
     * @param req
     */
    public void createTable(HttpServletRequest req)
    {
        Path path = Paths.get(req.getServletContext().getRealPath("bdd/bdd_init.sql"));
        try {
            String content = Files.readString(path);
            f.getS().requete(content);
        }catch (IOException | SQLException e){
            e.printStackTrace();
        }
    }
}
