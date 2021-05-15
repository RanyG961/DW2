package dw2.projetweb.forms;

import dw2.projetweb.beans.Fichier;
import dw2.projetweb.beans.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Path;
import java.sql.Array;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class FormFichier
{
    private final Form form = new Form();

    private String cNomFichier = "nom";
    private String cFichier = "fichier";

    private Map<String, String> erreurs = form.getErreurs();

    /**
     * sauvegarde un fichier
     * @param req
     * @param chemin
     * @return le fichier sauvegarder
     */
    public Fichier saveFile(HttpServletRequest req, Path chemin)
    {
        Fichier f = new Fichier();

        String nom = form.getValeurChamp(req, cNomFichier);

        String nomF = null;
        InputStream contenuFichier = null;

        try
        {
            Part part = (req).getPart(cFichier);

            nomF = getNomFichier(part);

            if (nomF != null && !nomF.isEmpty())
            {
                contenuFichier = part.getInputStream();
            }
        } catch (ServletException e)
        {
            e.printStackTrace();
            form.setErreur(cFichier, "Type de requête non supporté");
        } catch (IOException e)
        {
            e.printStackTrace();
            form.setErreur(cFichier, "Erreur de configuration du serveur.");
        } catch (IllegalStateException e)
        {
            e.printStackTrace();
            form.setErreur(cFichier, "Les données sont trop volumineuses.");
        }

        if (erreurs.isEmpty())
        {
            try
            {
                fNameValid(cNomFichier);
            } catch (Exception e)
            {
                form.setErreur(cNomFichier, e.getMessage());
                e.printStackTrace();
            }

            try
            {
                fileIsValid(nomF, contenuFichier);
            } catch (Exception e)
            {
                form.setErreur(cFichier, e.getMessage());
                e.printStackTrace();
            }
            f.setName(cNomFichier);
        }

        if (erreurs.isEmpty())
        {
            try
            {
                ecrireFichier(contenuFichier, nomF, chemin);
            } catch (Exception e)
            {
                form.setErreur(cFichier, "Erreur lors de l'écriture du fichier");
                e.printStackTrace();
            }
        }

        if (erreurs.isEmpty())
        {
            form.setResultat(true);
        } else
        {
            form.setResultat(false);
        }

        return f;
    }

    /**
     * Crée un fichier
     * @param req
     * @param path
     * @param u
     * @return le Fichier créer
     */
    public Fichier creerFichier(HttpServletRequest req, Path path, User u)
    {
        Fichier fichier = new Fichier();

        Integer id = null;
        try
        {
            id = getUserId(u.getMail(), u.getPseudo());
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        Boolean isPublic;
        if (req.getParameter("fPublic") == null)
        {
            isPublic = false;
        } else
        {
            isPublic = true;
        }

        Date date = new Date();
//        System.out.println(date);

        Timestamp dateTS = new Timestamp(date.getTime());

        SimpleDateFormat dateFor = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");
        String d = dateFor.format(date);
        System.out.println(d);

        String nomFichier = id + "_" + d + ".txt";
        String chemin = path + "/" + nomFichier;

        fichier.setName(nomFichier);
        fichier.setProprietaire(id);
        fichier.setPath(path);
        fichier.setDateCreation(date);
        fichier.setPublic(isPublic);


        File f = new File(chemin);
        try
        {
            if (f.createNewFile())
            {
                System.out.println("Fichier créé: " + f.getName());
                try
                {
                    insertFile(chemin, id, dateTS, isPublic, nomFichier);
                    Integer documentId = getDocumentId(nomFichier);
                    linkFileUser(documentId, id, dateTS);
                } catch (Exception e)
                {
                    System.err.println("Echec a l'insertion du fichier dans la base de donnée");
                    e.printStackTrace();
                }

            } else
            {
                throw new Exception("Le fichier n'a pas pu être créé.");
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return fichier;
    }

    /**
     * Lis tout le contenu d'un fichier
     * @param documentId
     * @param fichier
     * @return le contenu du fichier
     * @throws SQLException
     * @throws IOException
     */
    public String readFile(Integer documentId, Fichier fichier) throws SQLException, IOException
    {
        String pathFile = getDocumentPath(documentId);
        String nameFile = getNomFichier(documentId);
        String res = lireFichier(pathFile);

        fichier.setPath(Path.of(pathFile));
        fichier.setName(nameFile);

        return res;
    }

    /**
     * Lis tout le contenu d'un fichier
     * @param path
     * @return Tout ce que le fichier contient
     * @throws IOException
     */
    public String lireFichier(String path) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(path));

        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        while (line != null)
        {
            sb.append(line);
            sb.append("\n");
            line = br.readLine();
        }
        return sb.toString();
    }

    /**
     * Récupère les documents accessibles selon l'utilisateur
     * @param u
     * @return Liste des documents accessibles
     * @throws Exception
     */
    public ArrayList<Integer> accessibleDocuments(User u) throws Exception
    {
        ArrayList<Integer> listRes = new ArrayList<>();
        Integer idUser = getUserId(u.getMail(), u.getPseudo());

        String req = "SELECT document_id FROM acceesDocument WHERE user_id = " + idUser + " AND droitLecture = 1 AND droitEcriture = 1";
        String res = form.getS().requete(req);

        String[] elements = res.split(" ");

        for (String s : elements)
        {
            listRes.add(Integer.parseInt(s));
        }

        return listRes;
    }

    /**
     * Insère dans la bdd le fichier
     * @param chemin
     * @param id
     * @param dateTS
     * @param isPublic
     * @param nomFichier
     * @return Vrai si l'insertion est réussi, Faux sinon
     * @throws Exception
     */
    public boolean insertFile(String chemin, Integer id, Timestamp dateTS, boolean isPublic, String nomFichier) throws Exception
    {
        String requete = "INSERT INTO document(path, user_id, dateCreation, is_public, nom) VALUES ('" + chemin +
                "', " + id + ", '" + dateTS + "', " + isPublic + ", '" + nomFichier + "');";

        System.out.println(requete);
        String res = form.getS().requete(requete);

        if (Integer.parseInt(res) != 1)
        {
            throw new Exception("L'insertion a échouée");
        } else return Integer.parseInt(res) == 1;
    }

    /**
     * Lie l'utilisateur à un document
     * @param documentId
     * @param userId
     * @param dateAccees
     * @return Vrai si la requête a été insérée, faux sinon
     * @throws Exception
     */
    public boolean linkFileUser(Integer documentId, Integer userId, Timestamp dateAccees) throws Exception
    {
        String req = "INSERT INTO acceesDocument(document_id, user_id, droitLecture, droitEcriture, dateAccees) VALUES (" + documentId
                + ", " + userId + ", " + true + ", " + true + ", '" + dateAccees + "');";

        System.out.println(req);
        String res = form.getS().requete(req);

        if (Integer.parseInt(res) != 1)
        {
            throw new Exception("L'insertion a échouée");
        } else return Integer.parseInt(res) == 1;
    }

    /**
     * Récupère le path d'un fichier
     * @param documentId
     * @return filePath
     * @throws SQLException
     */
    public String getDocumentPath(Integer documentId) throws SQLException
    {
        String req = "SELECT path FROM document WHERE id = " + documentId + ";";

        String res = form.getS().requete(req);

//
        res = res.substring(0, res.length() - 1);

        return res;
    }

    /**
     * Récupère le nom du fichier
     * @param documentId
     * @return fileName
     * @throws SQLException
     */
    public String getNomFichier(Integer documentId) throws SQLException
    {
        String req = "SELECT nom FROM document WHERE id = " + documentId + ";";

        String res = form.getS().requete(req);

        res = res.substring(0, res.length() - 1);

        return res;
    }

    /**
     * Récupère l'id du document
     * @param name
     * @return id_document
     * @throws SQLException
     */
    public Integer getDocumentId(String name) throws SQLException
    {
        String req = "SELECT id FROM document WHERE nom = '" + name + "';";

        String res = form.getS().requete(req);

        res = res.substring(0, res.length() - 1);

        Integer id = Integer.parseInt(res);

        return id;
    }

    /**
     * Récupère l'id du user
     * @param email
     * @param nickname
     * @return id_user
     * @throws Exception
     */
    public Integer getUserId(String email, String nickname) throws Exception
    {
        String req = "SELECT id FROM users WHERE mail = '" + email + "' OR nickname = '" + nickname + "';";

        String res = form.getS().requete(req);

        res = res.substring(0, res.length() - 1);

        Integer id = Integer.parseInt(res);

        return id;
    }

    /**
     * Affiche les demandes d'amis
     * @param request
     * @return ArrayList des demandes d'amis
     * @throws Exception
     */
    public ArrayList<String> demandeAmis(HttpServletRequest request) throws Exception
    {
        ArrayList<String> lDemandeAmis = new ArrayList<>();
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("sessionU");
        int user2_id = getUserId(u.getMail(), u.getPseudo());
        int idUser1 = 0;

        String req = "SELECT user1_id FROM friends WHERE demandeAcceptee = 0 AND user2_id = " + user2_id + ";";
        String res = form.getS().requete(req);

        String[] elements = res.split(" ");

        for (String s : elements)
        {
            idUser1 = Integer.parseInt(s);
            lDemandeAmis.add(getNicknameUser(idUser1));
        }

        request.setAttribute("lDemandeAmis", lDemandeAmis);

        return lDemandeAmis;
    }

    public String getNicknameUser(int idUser) throws SQLException
    {
        String req = "SELECT nickname FROM users WHERE id = " + idUser + ";";
        String res = form.getS().requete(req);

        res = res.substring(0, res.length() - 1);
        return res;
    }

    public boolean accepteAmis(HttpServletRequest request) throws Exception
    {
        String pseudoAmi = request.getParameter("pseudoAmi");
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("sessionU");
        int user1_id = getUserId(u.getMail(), u.getPseudo());
        int user2_id = getUserId(pseudoAmi, pseudoAmi);

        Date date = new Date();

        Timestamp dateTS = new Timestamp(date.getTime());

        System.out.println("user1 = " + user1_id + " user2 = " + user2_id);


        String req = "UPDATE friends SET demandeAcceptee = " + true + ", demandeUpdate = '" + dateTS +
                "' WHERE user1_id = " + user1_id + " AND user2_id = " + user2_id + ";";
        String res = form.getS().requete(req);

        if (Integer.parseInt(res) != 1)
        {
            throw new Exception("L'insertion a échouée");
        } else return Integer.parseInt(res) == 1;
    }

    /**
     * ajoute 2 personnes entre eux
     * @param request
     * @return
     * @throws Exception
     */
    public boolean ajoutAmis(HttpServletRequest request) throws Exception
    {
        String pseudoAmi = request.getParameter("pseudoAmi");
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("sessionU");
        int user1_id = getUserId(u.getMail(), u.getPseudo());
        int user2_id = getUserId(pseudoAmi, pseudoAmi);

        Date date = new Date();

        Timestamp dateTS = new Timestamp(date.getTime());

        String req = "INSERT INTO friends(user1_id, user2_id, demandeCreer) VALUES(" + user1_id + ", " + user2_id + ", '" + dateTS + "');";
        String res = form.getS().requete(req);

        if (Integer.parseInt(res) != 1)
        {
            throw new Exception("L'insertion a échouée");
        } else return Integer.parseInt(res) == 1;
    }

    /**
     * Cherche les pseudos des utilisateurs et les stocks dans une ArrayList
     * @return ArrayList de pseudo des utilisateurs
     * @throws SQLException
     */
    public ArrayList<String> getNicknameUsers(HttpServletRequest request) throws Exception
    {
        ArrayList<String> listeUsers = new ArrayList<>();
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("sessionU");
        int userId = getUserId(u.getMail(), u.getPseudo());
        String req = "SELECT nickname FROM users WHERE id != " + userId + ";";

        String res = form.getS().requete(req);

        System.out.println(res);

        String[] elements = res.split(" ");

        for (String s : elements)
        {
            listeUsers.add(s);
        }

        request.setAttribute("listeUsers", listeUsers);
        return listeUsers;
    }

    /**
     * Affiche tout les documents accessibles de l'utilisateur
     * @param req
     */
    public void getDocumentsAccessibles(HttpServletRequest req)
    {
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("sessionU");
        ArrayList<Integer> documentsId = new ArrayList<>();

        try
        {
            documentsId = accessibleDocuments(u);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        for (Integer documentId : documentsId)
        {
            System.out.println("L'id du document est : " + documentId);
        }

        req.setAttribute("documentsId", documentsId);
    }

    public ArrayList<String> listeAmis(HttpServletRequest request) throws Exception
    {
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("sessionU");
        ArrayList<String> lAmis = new ArrayList<>();
        int user_id = getUserId(u.getMail(), u.getPseudo());
        String s = new String();

        ArrayList<Integer> lAmis1, lAmis2;

        lAmis1 = listeAmis_user1(user_id);
        lAmis2 = listeAmis_user2(user_id);

        for(int idAmi : lAmis1)
        {
            s = getNicknameUser(idAmi);
            lAmis.add(s);
        }

        for(int idAmi : lAmis2)
        {
            s = getNicknameUser(idAmi);
            lAmis.add(s);
        }

        request.setAttribute("lAmis", lAmis);
        return lAmis;
    }

    public ArrayList<Integer> listeAmis_user1(int userId) throws SQLException
    {
        String req = "SELECT user2_id FROM friends WHERE demandeAcceptee " + true +
                " AND user1_id = " + userId + ";";
        return getALIntegers(req);
    }

    public ArrayList<Integer> listeAmis_user2(int userId) throws SQLException
    {
        String req = "SELECT user1_id FROM friends WHERE demandeAcceptee " + true +
                " AND user2_id = " + userId + ";";
        return getALIntegers(req);
    }

    private ArrayList<Integer> getALIntegers(String req) throws SQLException
    {
        ArrayList<Integer> lAmis_user= new ArrayList<>();
        int user_id;

        String res = form.getS().requete(req);

        System.out.println(res);

        String[] elements = res.split(" ");

        for (String s : elements)
        {
            user_id = Integer.parseInt(s);
            lAmis_user.add(user_id);
        }

        return lAmis_user;
    }


    /**
     * Vérifie le nom du fichier si il est valide ou non
     *
     * @param nom
     * @throws Exception
     */
    public void fNameValid(String nom) throws Exception
    {
        if (nom != null)
        {
            if (!nom.matches("(^[a-zA-Z0-9_]+$)"))
            {
                throw new Exception("Nom du fichier non valide (3 et 15 caractères)!");
            }
        } else
        {
            throw new Exception("Merci de saisir un nom au fichier.");
        }
    }

    /**
     * Vérifie si le fichier envoyé est valide ou non.
     *
     * @param nom
     * @param contenuFichier
     * @throws Exception
     */
    public void fileIsValid(String nom, InputStream contenuFichier) throws Exception
    {
        if (nom.isEmpty() || contenuFichier == null)
        {
            throw new Exception("Fichier non valide");
        }
    }

    /**
     * Méthode utilitaire qui a pour unique but d'analyser l'en-tête
     * "content-disposition", et de vérifier si le paramètre "filename" y est présent
     * Crédit : OpenClassrooms
     *
     * @param part
     * @return le nom si le champ est de type File sinon retourne null
     */
    private static String getNomFichier(Part part)
    {
        /* Boucle sur chacun des paramètres de l'en-tête "content-disposition". */
        for (String contentDisposition : part.getHeader("content-disposition").split(";"))
        {
            /* Recherche de l'éventuelle présence du paramètre "filename". */
            if (contentDisposition.trim().startsWith("filename"))
            {
                /*
                 * Si "filename" est présent, alors renvoi de sa valeur,
                 * c'est-à-dire du nom de fichier sans guillemets.
                 */
                return contentDisposition.substring(contentDisposition.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        /* Et pour terminer, si rien n'a été trouvé... */
        return null;
    }

    /*
     * Méthode utilitaire qui a pour but d'écrire le fichier passé en paramètre sur le disque, dans le répertoire donné et avec le nom donné.
     */

    /**
     * Méthode utilitaire qui a pour but d'écrire le fichier passé en paramètre sur le disque,
     * dans le répertoire donné et avec le nom donné.
     *
     * @param contenu
     * @param nomFichier
     * @param chemin
     * @throws Exception
     */
    private void ecrireFichier(InputStream contenu, String nomFichier, Path chemin) throws Exception
    {
        /* Prépare les flux. */
        int TAILLE_TAMPON = 10240;
        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        try
        {
            /* Ouvre les flux. */
            entree = new BufferedInputStream(contenu, TAILLE_TAMPON);
            sortie = new BufferedOutputStream(new FileOutputStream(chemin + "/" + nomFichier),
                    TAILLE_TAMPON);

            /*
             * Lit le fichier reçu et écrit son contenu dans un fichier sur le
             * disque.
             */
            byte[] tampon = new byte[TAILLE_TAMPON];
            int longueur = 0;
            while ((longueur = entree.read(tampon)) > 0)
            {
                sortie.write(tampon, 0, longueur);
            }
        } finally
        {
            try
            {
                sortie.close();
            } catch (IOException ignore)
            {
            }
            try
            {
                entree.close();
            } catch (IOException ignore)
            {
            }
        }
    }

}
