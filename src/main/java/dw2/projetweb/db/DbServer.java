package dw2.projetweb.db;

import java.sql.*;

public class DbServer
{
    Connexion c = null;


    private Statement stmt = null;
    private PreparedStatement pr_stmt = null;
    private ResultSet rs = null;
    private ResultSetMetaData rsmd = null;
    private int res = 0;

    public DbServer(Connexion c)
    {
        stmt = c.getStmt();
        pr_stmt = c.getPr_stmt();
        ResultSet rs = c.getRs();
        ResultSetMetaData rsmd = c.getRsmd();
        res = c.getRes();
    }

    public void requete(String query) throws SQLException
    {
        String[] premierMot = query.split(" ");

        if  (premierMot[0].equalsIgnoreCase("select"))
        {
            rs = stmt.executeQuery(query);
            rsmd = rs.getMetaData();

            while(rs.next())
            {
                for (int i = 1; i <= rsmd.getColumnCount(); i++)
                {
                    System.out.println(rs.getString(i) + " ");
                }
                System.out.println();
            }
        }
        else
        {
            res = stmt.executeUpdate(query);

            System.out.println("Nombre de ligne modifiees " + res);
        }
    }

    public void createDB() throws SQLException
    {
        requete("CREATE DATABASE "  + c.getJDBC_DBNAME());
    }

    // Getters and Setters

    public Connexion getC()
    {
        return c;
    }

    public void setC(Connexion c)
    {
        this.c = c;
    }

    public Statement getStmt()
    {
        return stmt;
    }

    public void setStmt(Statement stmt)
    {
        this.stmt = stmt;
    }

    public PreparedStatement getPr_stmt()
    {
        return pr_stmt;
    }

    public void setPr_stmt(PreparedStatement pr_stmt)
    {
        this.pr_stmt = pr_stmt;
    }

    public ResultSet getRs()
    {
        return rs;
    }

    public void setRs(ResultSet rs)
    {
        this.rs = rs;
    }

    public ResultSetMetaData getRsmd()
    {
        return rsmd;
    }

    public void setRsmd(ResultSetMetaData rsmd)
    {
        this.rsmd = rsmd;
    }

    public int getRes()
    {
        return res;
    }

    public void setRes(int res)
    {
        this.res = res;
    }
}
