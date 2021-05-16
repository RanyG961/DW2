package dw2.projetweb.bdd;

import java.sql.*;

public class DbServer
{
    Connexion c = null;


    private Statement stmt = null;
    private PreparedStatement pr_stmt = null;
    private ResultSet rs = null;
    private ResultSetMetaData rsmd = null;
    private int res = 0;

    /**
     *
     * @param c
     */
    public DbServer(Connexion c)
    {
        stmt = c.getStmt();
        pr_stmt = c.getPr_stmt();
        ResultSet rs = c.getRs();
        ResultSetMetaData rsmd = c.getRsmd();
        res = c.getRes();
    }

    /**
     *
     * @param query
     * @return
     * @throws SQLException
     */
    public String requete(String query) throws SQLException
    {
        String[] premierMot = query.split(" ");
        String resultat = null;

        if  (premierMot[0].equalsIgnoreCase("select"))
        {
            resultat = "";
            rs = stmt.executeQuery(query);
            rsmd = rs.getMetaData();

            while(rs.next())
            {
                for (int i = 1; i <= rsmd.getColumnCount(); i++)
                {
//                    System.out.println(rs.getString(i) + " ");
                    resultat += (rs.getString(i) + " ");

                }
//                System.out.println();
            }
        }
        else
        {
            res = stmt.executeUpdate(query);

            System.out.println("Nombre de ligne modifiees " + res);

            resultat = String.valueOf(res);
        }

        return resultat;
    }

    /**
     *
     * @throws SQLException
     */
    public void createDB() throws SQLException
    {
        requete("CREATE DATABASE "  + c.getJDBC_DBNAME());
    }

    public void disconnectDB()
    {
        c.deconnexion();
    }

    // Getters and Setters

    public Connexion getC()
    {
        return c;
    }

    /**
     *
     * @param c
     */
    public void setC(Connexion c)
    {
        this.c = c;
    }

    public Statement getStmt()
    {
        return stmt;
    }

    /**
     *
     * @param stmt
     */
    public void setStmt(Statement stmt)
    {
        this.stmt = stmt;
    }

    public PreparedStatement getPr_stmt()
    {
        return pr_stmt;
    }

    /**
     *
     * @param pr_stmt
     */
    public void setPr_stmt(PreparedStatement pr_stmt)
    {
        this.pr_stmt = pr_stmt;
    }

    public ResultSet getRs()
    {
        return rs;
    }

    /**
     *
     * @param rs
     */
    public void setRs(ResultSet rs)
    {
        this.rs = rs;
    }

    public ResultSetMetaData getRsmd()
    {
        return rsmd;
    }

    /**
     *
     * @param rsmd
     */
    public void setRsmd(ResultSetMetaData rsmd)
    {
        this.rsmd = rsmd;
    }

    public int getRes()
    {
        return res;
    }

    /**
     *
     * @param res
     */
    public void setRes(int res)
    {
        this.res = res;
    }
}
