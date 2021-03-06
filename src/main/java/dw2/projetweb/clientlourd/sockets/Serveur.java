package dw2.projetweb.clientlourd.sockets;

import dw2.projetweb.beans.User;
import dw2.projetweb.forms.FormUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Serveur {

    public static void main(String[] test) {

        final ServerSocket serveurSocket  ;
        final Socket clientSocket ;
        final BufferedReader in;
        final PrintWriter out;
        final Scanner sc=new Scanner(System.in);
        final FormUser[] formUser = new FormUser[10];

        try {
            serveurSocket = new ServerSocket(8080);
            clientSocket = serveurSocket.accept();

            out = new PrintWriter(clientSocket.getOutputStream());
            in = new BufferedReader (new InputStreamReader (clientSocket.getInputStream()));

            Thread envoi = new Thread(new Runnable() {
                String msg;
                @Override
                public void run() {
                    while(true){
                        msg = sc.nextLine();
                        out.println(msg);
                        out.flush();
                    }
                }
            });
            envoi.start();

            Thread recevoir = new Thread(new Runnable() {
                String msg ;
                @Override
                public void run() {
                    try {
                        msg = in.readLine();
                        User u = new User();
                        //tant que le client est connecté
                        while(msg!=null){
                            // On regarde si c'est une connexion utilisateur
                            if(in.readLine() == "Connexion_user") {
                                String pseudo = in.readLine();
                                String mdp = in.readLine();

                                FormUser form = new FormUser();

                                form.setcPseudo(pseudo);
                                form.setcPassword(mdp);

                                if(form.verifAccount(form.getcPseudo(), form.getcPassword())){
                                    formUser[0] = form;
                                    out.println("Connexion réussie.");
                                    out.println(form.getcPseudo());
                                    out.flush();
                                }


                                formUser[0] = form;
                                out.println("Connexion ok");
                            }
                        }
                        //sortir de la boucle si le client a déconecté
                        System.out.println(u);
                        //fermer le flux et la session socket
                        out.close();
                        clientSocket.close();
                        serveurSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            });
            recevoir.start();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}

