package dw2.projetweb.clientlourd.swing;

import javax.swing.*;

public class Inscription {
    private JPanel inscription;

    public Inscription() {

    }

    public JPanel getInscription() {
        return inscription;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Inscription");
        frame.setContentPane(new Inscription().getInscription());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1280, 720);
        frame.setVisible(true);
    }
}
