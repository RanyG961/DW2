package dw2.projetweb.clientlourd.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;

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

    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private Scanner sc = new Scanner(System.in);//pour lire à partir du clavier

    public Page_accueil(){
        super("Gestionnaire de documents");

        try {
            clientSocket = new Socket("127.0.0.1", 3000);

            //flux pour envoyer
            out = new PrintWriter(clientSocket.getOutputStream());
            //flux pour recevoir
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        }catch(IOException e) {
            e.printStackTrace();
        }

        Connexion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Connexion");
                frame.setContentPane(new Connexion().getPage_connexion());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setSize(1280, 720);
                frame.setVisible(true);
            }
        });
    }

    public JPanel getPage_accueil() {
        return page_accueil;
    }

    public static void main(String[] args) throws IOException {

        JFrame frame = new JFrame("Page_accueil");
        frame.setContentPane(new Page_accueil().getPage_accueil());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1280, 720);
        frame.setVisible(true);

    }
}
