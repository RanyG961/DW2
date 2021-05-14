package dw2.projetweb.clientlourd.swing;

import javax.swing.*;
import java.awt.*;

public class Page_accueil extends JFrame{
    private JPanel page_accueil;
    private JPanel editeur;
    private JPanel header;
    private JPanel connexion;
    private JPanel messagerie;
    private JPanel social;
    private JButton Connexion;
    private JButton Inscription;
    private JScrollPane Menu;
    private JPanel creer_projet;
    private JButton btn_creer_projet;
    private JButton ouvrirUnProjetButton;
    private JButton menuButton;
    private JLabel txt_editeur;

    public Page_accueil(){
        super("Gestionnaire de documents");
    }

    public static void main(String[] args) {
        JFrame frame = new Page_accueil();
        frame.setContentPane(new Page_accueil().page_accueil);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.pack();
        frame.setVisible(true);
    }
}
