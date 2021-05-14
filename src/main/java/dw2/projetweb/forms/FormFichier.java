package dw2.projetweb.forms;

import dw2.projetweb.beans.Fichier;
import dw2.projetweb.beans.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Path;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FormFichier
{
    private final Form form = new Form();

    private String cNomFichier = "nom";
    private String cFichier = "fichier";

    private Map<String, String> erreurs = form.getErreurs();

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

    public Fichier creerFichier(HttpServletRequest req, Path path, User u)
    {
        Fichier fichier = new Fichier();

        Integer id = null;
        try
        {
            id = getSqlId(u.getMail());
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
        System.out.println(date);

        Timestamp dateTS = new Timestamp(date.getTime());

        SimpleDateFormat dateFor = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");
        String d = dateFor.format(date);
        System.out.println(d);

        String nomFichier = id + "_" + d + ".json";
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
                }
                catch (Exception e)
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

    public Integer getSqlId(String email) throws Exception
    {
        String req = "SELECT id FROM users WHERE mail = '" + email + "'";
        String res = form.getS().requete(req);

        res = res.substring(0, res.length() - 1);

        Integer id = Integer.parseInt(res);

        return id;
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
