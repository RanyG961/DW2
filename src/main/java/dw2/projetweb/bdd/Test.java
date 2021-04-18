package dw2.projetweb.bdd;

import dw2.projetweb.forms.formInstallAdmin;
import java.sql.*;

public class Test
{
    public static void main(String[] args) throws SQLException
    {
//        Connexion c = new Connexion();
//
//        DbServer dbS = new DbServer(c);

//        dbS.requete("INSERT INTO user (first_name, last_name, password, nickname, mail, birthdate, is_admin) values('Rany', 'Ghazzawi', 'RanyG961', 'test', 't', '1996-08-11',  1)");
//        dbS.requete("SELECT * FROM user;");

        formInstallAdmin fA = new formInstallAdmin();

//        boolean estAdmin = fA.adminExists();
//        fA.createTable();

//        System.out.println(estAdmin);

        fA.deconnexion();
    }
}
