package dw2.projetweb.forms;

import dw2.projetweb.bdd.Connexion;
import dw2.projetweb.bdd.DbServer;
import dw2.projetweb.beans.User;
import org.apache.commons.lang3.StringUtils;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.text.StringEscapeUtils.escapeHtml4;

public class Form
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
    private String cIdentifiant = "identifiant";
    private boolean cAdmin;

    private boolean resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();

    /**
     *
     * @param request
     * @param admin
     * @return
     * @throws ParseException
     */
    public User inscrireUser(HttpServletRequest request, boolean admin) throws ParseException
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

        if (erreurs.isEmpty())
        {
            resultat = true;
            try
            {
                createAccount(nom, prenom, dateNaiss, email, pwd, pwdConfirm, nickname, admin);
            } catch (Exception e)
            {
                setErreur(cNom, e.getMessage());
            }
            u.setNom(nom);
            u.setPrenom(prenom);
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateNaiss);
            u.setDateNaissance(date);
        } else
        {
            resultat = false;
            System.out.println("Resultat false");
        }
//        s.disconnectDB();
        return u;
    }

    /**
     *
     * @param nom
     * @param prenom
     * @param dateNaiss
     * @param email
     * @param pwd
     * @param pwdConfirm
     * @param nickname
     * @param admin
     * @return
     * @throws Exception
     */
    public boolean createAccount(String nom, String prenom, String dateNaiss, String email, String pwd, String pwdConfirm, String nickname, Boolean admin) throws Exception
    {
        String salt = BCrypt.gensalt(12);
        String req;
        nom = escapeHtml4(nom);
        prenom = escapeHtml4(prenom);
        dateNaiss = escapeHtml4(dateNaiss);
        email = escapeHtml4(email);
        pwd = escapeHtml4(pwd);
        pwd = BCrypt.hashpw(pwd, salt);
        pwdConfirm = escapeHtml4(pwdConfirm);
        nickname = escapeHtml4(nickname);

        if (nom.isEmpty() || prenom.isEmpty() || dateNaiss.isEmpty() || email.isEmpty() || pwd.isEmpty() || pwdConfirm.isEmpty() || nickname.isEmpty())
        {
            throw new Exception("Veuillez remplir toutes les cases");
        } else
        {

            req = "INSERT INTO users(first_name, last_name, birthdate, password, nickname, mail, is_admin) VALUES('" + prenom + "', '" + nom + "', '" + dateNaiss + "', '" + pwd + "', '" + nickname + "', '" + email + "', " + admin + ");";

            System.out.println(req);
            String res = s.requete(req);

            if (Integer.parseInt(res) != 1)
            {
                throw new Exception("L'insertion a échouée");
            } else return Integer.parseInt(res) == 1;
//            System.out.println("Ca a marché");
        }
    }

    /**
     *
     * @param req
     * @return
     * @throws Exception
     */
    public User connexion(HttpServletRequest req) throws Exception
    {
        String identifiant = escapeHtml4(getValeurChamp(req, "identifiant"));
        String pwd = escapeHtml4(getValeurChamp(req, "pwd"));

        User u = new User();

        try
        {
            verifAccount(identifiant, pwd);
        } catch (Exception e)
        {
            setErreur(cIdentifiant, e.getMessage());
            setErreur(cPassword, e.getMessage());
        }


        if (erreurs.isEmpty())
        {
            if (identifiant.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)"))
            {
                u.setMail(identifiant);
            } else
            {
                u.setPseudo(identifiant);
            }
            resultat = true;
        } else
        {
            resultat = false;
        }

        return u;
    }

    /**
     *
     * @param identifiant
     * @param pwd
     * @return
     * @throws Exception
     */
    public boolean verifAccount(String identifiant, String pwd) throws Exception
    {
        String req = "SELECT password FROM users WHERE mail = '" + identifiant + "' OR nickname = '" + identifiant + "'";
        String res = s.requete(req);

        res = StringUtils.chop(res);

        if (BCrypt.checkpw(pwd, res))
        {
            return true;
        } else
        {
            throw new Exception("La vérification à échouée");
        }
    }

    /**
     *
     * @param pseudo
     * @throws Exception
     */
    public void nicknameExists(String pseudo) throws Exception
    {
        String req = "SELECT id FROM users WHERE nickname = '" + pseudo + "'";
//        System.out.println(req);
        String res = s.requete(req);

        if (!res.isEmpty())
        {
            throw new Exception("Votre pseudo existe déjà, veuillez saisir un pseudo valide.");
        } else if (!pseudo.matches("(^[a-zA-Z0-9_]+$)"))
        {
            throw new Exception("Pseudo non valide, veuillez saisir un pseudo valide.");
        }
    }

    /**
     *
     * @param email
     * @throws Exception
     */
    public void emailExists(String email) throws Exception
    {
        String req = "SELECT id FROM users WHERE mail = '" + email + "'";
//        System.out.println(req);
        String res = s.requete(req);

        if (!res.isEmpty())
        {
            throw new Exception("Votre adresse mail existe déjà, veuillez saisir une adresse mail valide.");
        } else if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)"))
        {
            throw new Exception("Adresse mail non valide, veuillez saisir une adresse mail valide.");
        }
    }

    /**
     *
     * @param password
     * @param pwdConfirm
     * @throws Exception
     */
    public void passwordExists(String password, String pwdConfirm) throws Exception
    {
        if (!password.equals(pwdConfirm))
        {
            throw new Exception("Problème dans la confirmation du mot de passe");
        } else if (password.isEmpty())
        {
            throw new Exception("Veuillez saisir un mot de passe");
        } else if (pwdConfirm.isEmpty())
        {
            throw new Exception("Veuillez saisir votre confirmation de mot de passe");
        } else if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#&()–{}:;',?/*~$^+=<>]).{6,20}$"))
        {
            throw new Exception("Problème lors de la saisie du mot de passe(Au moins une lettre minuscule, majuscule, chiffre et symbole");
        }
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     * Credit : OpenClassrooms
     */

    /**
     *
     * @param champ
     * @param message
     */
    public void setErreur(String champ, String message)
    {
        erreurs.put(champ, message);
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     * Credit : OpenClassrooms
     */

    /**
     *
     * @param request
     * @param nomChamp
     * @return
     */
    public static String getValeurChamp(HttpServletRequest request, String nomChamp)
    {
        String valeur = request.getParameter(nomChamp);
        if (valeur == null || valeur.trim().length() == 0)
        {
            return null;
        } else
        {
            return valeur.trim();
        }
    }


    // GETTERS AND SETTERS
    public Connexion getC()
    {
        return c;
    }

    public void setC(Connexion c)
    {
        this.c = c;
    }

    public DbServer getS()
    {
        return s;
    }

    public void setS(DbServer s)
    {
        this.s = s;
    }

    public String getcPwdConfirm()
    {
        return cPwdConfirm;
    }

    public void setcPwdConfirm(String cPwdConfirm)
    {
        this.cPwdConfirm = cPwdConfirm;
    }

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
