package dw2.projetweb.beans;

import java.util.Date;

/**
 * Bean utilisateur
 */
public class User
{
    private Long id;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String pseudo;
    private String password;
    private String mail;
    private boolean admin;

    /**
     *
     */
    public User()
    {
        super();
    }

    /**
     * Récupère l'id
     * @return
     */
    public Long getId()
    {
        return id;
    }

    /**
     * Récupère le nom
     * @return nom
     */
    public String getNom()
    {
        return nom;
    }

    /**
     * Set le nom
     * @param nom
     */
    public void setNom(String nom)
    {
        this.nom = nom;
    }

    /**
     * Récupère le prénom
     * @return prenom
     */
    public String getPrenom()
    {
        return prenom;
    }

    /**
     * Set le prénom
     * @param prenom
     */
    public void setPrenom(String prenom)
    {
        this.prenom = prenom;
    }

    /**
     * Récupère la date de naissance
     * @return la date de naissance
     */
    public Date getDateNaissance()
    {
        return dateNaissance;
    }

    /**
     * Set la date de naissance
     * @param dateNaissance
     */
    public void setDateNaissance(Date dateNaissance)
    {
        this.dateNaissance = dateNaissance;
    }

    /**
     * Récupère le pseudo
     * @return pseudo
     */
    public String getPseudo()
    {
        return pseudo;
    }

    public void setPseudo(String pseudo)
    {
        this.pseudo = pseudo;
    }

    /**
     * Récupère le pseudo
     * @return pseudo
     */
    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * Récupère le mail
     * @return mail
     */
    public String getMail()
    {
        return mail;
    }

    /**
     * SEt le mail
     * @param mail
     */
    public void setMail(String mail)
    {
        this.mail = mail;
    }

    /**
     * Renvoie si l'utilisateur est un admin ou non
     * @return true si il est admin et false sinon
     */
    public boolean isAdmin()
    {
        return admin;
    }
}
