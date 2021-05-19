package dw2.projetweb.clientlourd.swing;

import javax.swing.*;
import java.awt.event.*;

public class Espace_utilisateur extends JFrame {
    private JPanel panel1;
    private JTextField nom;
    private JTextField prenom;
    private JTextField mail;
    private JTextField pseudo;
    private JButton editeurButton;
    private JPanel messagerie;
    private JButton socialButton;
    private JButton confirmerLesChangementsButton;
    private JTextField messagesTextField;

    public Espace_utilisateur() {
        super("Espace utilisateur");


        editeurButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WindowListener l = new WindowAdapter() {
                    public void windowClosing(WindowEvent e){
                        System.exit(0);
                    }
                };
            }
        });

        confirmerLesChangementsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

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

    public JPanel getPanel1() {
        return panel1;
    }
}
