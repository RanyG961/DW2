package dw2.projetweb.beans;

import java.util.Date;

/**
 * Bean  Fichier
 */
public class Fichier
{
    private Long id;
    private String path;
    private int proprietaire;
    private Date dateCreation;
    private Date dateUpdate;
    private boolean isPublic;

    /**
     * Constructeur
     */
    public Fichier()
    {
        super();
    }

    /* -------------------------------------GETTERS AND SETTERS---------------------------------------------- */

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    public int getProprietaire()
    {
        return proprietaire;
    }

    public void setProprietaire(int proprietaire)
    {
        this.proprietaire = proprietaire;
    }

    public Date getDateCreation()
    {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation)
    {
        this.dateCreation = dateCreation;
    }

    public Date getDateUpdate()
    {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate)
    {
        this.dateUpdate = dateUpdate;
    }

    public boolean isPublic()
    {
        return isPublic;
    }

    public void setPublic(boolean aPublic)
    {
        isPublic = aPublic;
    }
}
