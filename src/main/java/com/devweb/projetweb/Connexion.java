package com.devweb.projetweb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.*;

//@WebServlet(name = "connexion", value = "/connexion")
public class Connexion
{
    final String JDBC_CONNEXION = "jdbc:mysql://localhost:3306/hesscode";
    final String JDBC_USER = "root";
    final String JDBC_PWD = "dzer56Hr";

    public Connection cxn = null;
    public Statement stmt = null;
    public PreparedStatement pr_stmt = null;
    public ResultSet rs = null;
    public ResultSetMetaData rsmd = null;

    private int res;

    public Connexion()
    {
        try
        {
            cxn = DriverManager.getConnection(JDBC_CONNEXION, JDBC_USER, JDBC_PWD);
            System.out.println("Connexion OK !");

            stmt = cxn.createStatement();
        } catch (SQLException throwables)
        {
            System.err.println("Failed to connect");
            throwables.printStackTrace();
            System.exit(0);
        }
    }

    public Connexion(String query)
    {
        try
        {
            cxn = DriverManager.getConnection(JDBC_CONNEXION, JDBC_USER, JDBC_PWD);
            System.out.println("Connexion OK !");
            pr_stmt = cxn.prepareStatement(query);
        } catch (SQLException throwables)
        {
            System.err.println("Failed to connect");
            throwables.printStackTrace();
            System.exit(0);
        }
    }

    public void deconnexion()
    {
        try
        {
            if(rs != null)
                rs.close();
            else if(stmt != null)
                stmt.close();
            else if(pr_stmt != null)
                pr_stmt.close();

            cxn.close();
            System.out.println("Bye !");
        } catch (SQLException throwables)
        {
            System.err.println("Failed to disconnect");
            throwables.printStackTrace();
        }
    }

    // Getters and Setters

    public String getJDBC_CONNEXION()
    {
        return JDBC_CONNEXION;
    }

    public String getJDBC_USER()
    {
        return JDBC_USER;
    }

    public String getJDBC_PWD()
    {
        return JDBC_PWD;
    }

    public Connection getCxn()
    {
        return cxn;
    }

    public void setCxn(Connection cxn)
    {
        this.cxn = cxn;
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
