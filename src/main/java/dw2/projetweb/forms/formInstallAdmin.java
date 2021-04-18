package dw2.projetweb.forms;
import dw2.projetweb.beans.User;
import dw2.projetweb.bdd.Connexion;
import dw2.projetweb.bdd.DbServer;
import org.mindrot.jbcrypt.BCrypt;
import static org.apache.commons.text.StringEscapeUtils.escapeHtml4;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class formInstallAdmin
{
    private Connexion c = new Connexion();
    private DbServer s = new DbServer(c);

    private int cId;
    private String cNom = "nom";
    private String cPrenom = "prenom";
    private String cDateNaissance = "birthdate";
    private String cPseudo = "pseudo";
    private String cPassword = "pwd";
    private String cPwdConfirm = "pwdConfirm";
    private String cMail = "email";
    private boolean cAdmin;

    private boolean resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();



    /**
     * Vérifie si il existe un admin ou non
     * @return true si il existe un admin et false sinon
     * @throws SQLException
     */
    public boolean adminExists() throws SQLException
    {
       String admin = s.requete("SELECT * FROM users WHERE is_admin = TRUE");

       return !admin.isEmpty();
    }

    public void createTable(HttpServletRequest req) throws IOException
    {
        Path path = Paths.get(req.getServletContext().getRealPath("bdd/bdd_init.sql"));
        try {
            String content = Files.readString(path);
//            System.out.println(content);
            s.requete(content);
        }catch (IOException | SQLException e){
            e.printStackTrace();
        }
    }

    public void deconnexion()
    {
        c.deconnexion();
    }

    public User inscrireUser(HttpServletRequest request)
    {
        String nom = getValeurChamp(request, cNom);
        String prenom = getValeurChamp(request, cPrenom);
        String dateNaiss = getValeurChamp(request, cDateNaissance);
        String email = getValeurChamp(request, cMail);
        String pwd = getValeurChamp(request, cPassword);
        String pwdConfirm = getValeurChamp(request, cPwdConfirm);
        String nickname = getValeurChamp(request, cPseudo);


        User u = new User();

        try
        {
            passwordExists(pwd, pwdConfirm);
        } catch (Exception e)
        {
            setErreur(cPassword, e.getMessage());
            setErreur(cPwdConfirm, null);
        }
        u.setPassword(pwd);

        try
        {
            emailExists(email);
        } catch (Exception e)
        {
            setErreur(cMail, e.getMessage());
        }
        u.setMail(email);

        try
        {
            nicknameExists(nickname);
        } catch (Exception e)
        {
            setErreur(cPseudo, e.getMessage());
        }
        u.setPseudo(nickname);

        if(erreurs.isEmpty())
        {
            resultat = true;
            try
            {
                createAccount(nom, prenom, dateNaiss, email, pwd, pwdConfirm, nickname);
            }
            catch (Exception e)
            {
                setErreur(cNom, e.getMessage());
            }
            u.setNom(nom);
            u.setPrenom(prenom);
//            u.setDateNaissance(dateNaiss);
        }
        else
        {
            resultat = false;
            System.out.println("Resultat false");
        }
//        s.disconnectDB();
        return u;
    }

    public void passwordExists(String password, String pwdConfirm) throws Exception
    {
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());

        System.out.println(hashed);

        if(BCrypt.checkpw("Ranyleboss@961", hashed))
        {
            System.out.println("It matches");
        }
        else
        {
            System.out.println("It doesn't match");
        }
        if(!password.equals(pwdConfirm))
        {
            throw new Exception("Problème dans la confirmation du mot de passe");
        }
        else if(password.isEmpty())
        {
            throw new Exception("Veuillez saisir un mot de passe");
        }
        else if(pwdConfirm.isEmpty())
        {
            throw new Exception("Veuillez saisir votre confirmation de mot de passe");
        }
        else if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{6,20}$"))
        {
            throw new Exception("Problème lors de la saisie du mot de passe(Au moins une lettre minuscule, majuscule, chiffre et symbole");
        }
    }

    public void emailExists(String email) throws Exception
    {
        String req = "SELECT id FROM users WHERE mail = '" + email + "'";
//        System.out.println(req);
        String res = s.requete(req);

        if(!res.isEmpty())
        {
            throw new Exception("Votre adresse mail existe déjà, veuillez saisir une adresse mail valide.");
        }
        else if(!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)"))
        {
            throw new Exception("Adresse mail non valide, veuillez saisir une adresse mail valide.");
        }
    }

    public void nicknameExists(String pseudo) throws Exception
    {
        String req = "SELECT id FROM users WHERE nickname = '" + pseudo +"'";
//        System.out.println(req);
        String res = s.requete(req);

        if(!res.isEmpty())
        {
            throw new Exception("Votre pseudo existe déjà, veuillez saisir un pseudo valide.");
        }
        else if(!pseudo.matches("(^[a-zA-Z0-9_]+$)"))
        {
            throw new Exception("Pseudo non valide, veuillez saisir un pseudo valide.");
        }
    }

    public void createAccount(String nom, String prenom, String dateNaiss, String email, String pwd, String pwdConfirm, String nickname) throws Exception
    {
        nom = escapeHtml4(nom);
        prenom = escapeHtml4(prenom);
        dateNaiss = escapeHtml4(dateNaiss);
        email = escapeHtml4(email);
        pwd = escapeHtml4(pwd);
        pwd = BCrypt.hashpw(pwd, BCrypt.gensalt());
        pwdConfirm = escapeHtml4(pwdConfirm);
        nickname = escapeHtml4(nickname);


        if(nom.isEmpty() || prenom.isEmpty() || dateNaiss.isEmpty() || email.isEmpty() || pwd.isEmpty() || pwdConfirm.isEmpty() || nickname.isEmpty())
        {
            throw new Exception("Veuillez remplir toutes les cases");
        }
        else
        {
            String req = "INSERT INTO users(first_name, last_name, birthdate, password, nickname, mail, is_admin) VALUES('" + prenom + "', '" + nom + "', '" + dateNaiss + "', '" + pwd + "', '" + nickname + "', '" + email + "'," + 1 + ");";
//            System.out.println(req);
            String res = s.requete(req);

            if(Integer.valueOf(res) != 1)
            {
                throw new Exception("L'insertion a échouée");
            }

//            System.out.println("Ca a marché");
        }
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     * Credit : OpenClassrooms
     */
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     * Credit : OpenClassrooms
     */
    private static String getValeurChamp(HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur.trim();
        }
    }

    // GETTERS AND SETTERS
    public int getcId()
    {
        return cId;
    }

    public void setcId(int cId)
    {
        this.cId = cId;
    }

    public String getcNom()
    {
        return cNom;
    }

    public void setcNom(String cNom)
    {
        this.cNom = cNom;
    }

    public String getcPrenom()
    {
        return cPrenom;
    }

    public void setcPrenom(String cPrenom)
    {
        this.cPrenom = cPrenom;
    }

    public String getcDateNaissance()
    {
        return cDateNaissance;
    }

    public void setcDateNaissance(String cDateNaissance)
    {
        this.cDateNaissance = cDateNaissance;
    }

    public String getcPseudo()
    {
        return cPseudo;
    }

    public void setcPseudo(String cPseudo)
    {
        this.cPseudo = cPseudo;
    }

    public String getcPassword()
    {
        return cPassword;
    }

    public void setcPassword(String cPassword)
    {
        this.cPassword = cPassword;
    }

    public String getcMail()
    {
        return cMail;
    }

    public void setcMail(String cMail)
    {
        this.cMail = cMail;
    }

    public boolean iscAdmin()
    {
        return cAdmin;
    }

    public void setcAdmin(boolean cAdmin)
    {
        this.cAdmin = cAdmin;
    }

    public boolean isResultat()
    {
        return resultat;
    }

    public void setResultat(boolean resultat)
    {
        this.resultat = resultat;
    }

    public Map<String, String> getErreurs()
    {
        return erreurs;
    }

    public void setErreurs(Map<String, String> erreurs)
    {
        this.erreurs = erreurs;
    }
}
