package dw2.projetweb.clientlourd.swing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Page_accueil extends JFrame{
    private JPanel page_accueil;
    private JButton menuButton;
    private JButton démarrerUnProjetButton;
    private JButton autreButton;
    private JButton connexionButton;
    private JButton socialButton;
    private JTextField editeurTextField;
    private JTextField messagesTextField;
    private JButton espaceUtilisateur = new JButton("");
    ;

    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private Scanner sc = new Scanner(System.in);

    public Page_accueil(){
        super("Gestionnaire de documents");

        // Connexion client
        try {
            clientSocket = new Socket("176.190.63.49", 8080);

            //flux pour envoyer
            out = new PrintWriter(clientSocket.getOutputStream());
            //flux pour recevoir
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String etat = in.readLine();

            if(etat != null && etat == "Connexion réussie."){
                espaceUtilisateur.setText("Espace utilisateur de " + in.readLine());
                this.remove(connexionButton);
                this.add(espaceUtilisateur);
                this.revalidate();
                this.repaint();
            }

        }catch(IOException e) {
            e.printStackTrace();
        }

        connexionButton.addActionListener(new ActionListener() {
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

        espaceUtilisateur.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Espace_utilisateur");
                frame.setContentPane(new Espace_utilisateur().getPanel1());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setSize(1280, 720);
                frame.setVisible(true);
            }
        }));

        Thread updateMessagerie = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    // Ajouter affichage de la messagerie
                }
            }
        });
        updateMessagerie.start();
    }

    public JPanel getPage_accueil() {
        return page_accueil;
    }

    public static void main(String[] args) throws IOException {

        JFrame frame = new JFrame("Page_accueil");
        Page_accueil page_accueil = new Page_accueil();

        frame.setContentPane(page_accueil.getPage_accueil());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1280, 720);
        frame.setVisible(true);



    }
}
