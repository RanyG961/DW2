package dw2.projetweb.clientlourd.swing;

import dw2.projetweb.forms.FormUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Connexion extends JFrame {
    private JPanel page_connexion;
    private JTextField Pseudo;
    private JTextField mot_de_passe;
    private JButton connexionButton;
    private JLabel mdp_oublié;

    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private Scanner sc = new Scanner(System.in);//pour lire à partir du clavier

    public Connexion() {
        super("Connexion utlisateur");

        try {
            clientSocket = new Socket("127.0.0.1", 3000);

            //flux pour envoyer
            out = new PrintWriter(clientSocket.getOutputStream());
            //flux pour recevoir
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        }catch(IOException e) {
            e.printStackTrace();
        }

        connexionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!Pseudo.getText().isEmpty() && !mot_de_passe.getText().isEmpty()){
                    out.println("Connexion_user");
                    out.println(Pseudo.getText());
                    out.println(mot_de_passe.getText());

                    out.flush();

                    try {
                        String msg = in.readLine();

                        while(msg != null) {
                            if(msg == "Connexion réussie.") {

                            }
                        }
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });
    }

    public JPanel getPage_connexion() {
        return page_connexion;
    }
}
