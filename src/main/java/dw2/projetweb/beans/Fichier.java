package dw2.projetweb.beans;

import java.nio.file.Path;
import java.util.Date;

/**
 * Bean  Fichier
 */
public class Fichier
{
    private Integer id;
    private Path path;
    private String name;
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

    public Integer getId()
    {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    public Path getPath()
    {
        return path;
    }

    /**
     *
     * @param path
     */
    public void setPath(Path path)
    {
        this.path = path;
    }

    public int getProprietaire()
    {
        return proprietaire;
    }

    /**
     *
     * @param proprietaire
     */
    public void setProprietaire(int proprietaire)
    {
        this.proprietaire = proprietaire;
    }

    public Date getDateCreation()
    {
        return dateCreation;
    }

    /**
     *
     * @param dateCreation
     */
    public void setDateCreation(Date dateCreation)
    {
        this.dateCreation = dateCreation;
    }

    public Date getDateUpdate()
    {
        return dateUpdate;
    }

    /**
     *
     * @param dateUpdate
     */
    public void setDateUpdate(Date dateUpdate)
    {
        this.dateUpdate = dateUpdate;
    }

    public boolean isPublic()
    {
        return isPublic;
    }

    /**
     *
     * @param aPublic
     */
    public void setPublic(boolean aPublic)
    {
        isPublic = aPublic;
    }
}
