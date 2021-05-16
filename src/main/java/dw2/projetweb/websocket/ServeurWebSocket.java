package dw2.projetweb.websocket;

import com.mysql.cj.xdevapi.JsonParser;
import dw2.projetweb.beans.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONWriter;


import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@ServerEndpoint(value = "/changement/{user}/{fichier}")
public class ServeurWebSocket
{
//    private final Path pathFichier = Path.of("/home/ranyg961/MEGAsync/S6/DW2/hesscode/target/projetweb-1.0-SNAPSHOT/WEB-INF/documents");
    private final Path pathFichier = Path.of(this.getClass().getClassLoader().getResource("").getPath().split("/classes/")[0]+"/documents/");
    static ArrayList<Session> arrSessions = new ArrayList<>();
    static HashMap<String, JSONArray> mapMsg = new HashMap<>();

    /**
     *
     * @param session
     * @throws IOException
     */
    @OnOpen
    public void open(Session session) throws IOException
    {


        System.err.println("OPEN");
        arrSessions.add(session);
    }

    /**
     *
     * @param session
     * @throws IOException
     */
    @OnClose
    public void close(Session session) throws IOException
    {
        System.err.println("CLOSE");
        arrSessions.remove(session);
    }

    /**
     *
     * @param t
     */
    @OnError
    public void onError(Throwable t)
    {
        System.err.println("ERROR");
        t.printStackTrace();
    }

    /**
     *
     * @param sender
     * @param msg
     * @param user
     * @param fichier
     * @throws IOException
     */
    @OnMessage
    public void message(Session sender, String msg, @PathParam("user") String user, @PathParam("fichier") String fichier) throws IOException
    {
        for (Session client : arrSessions)
        {
            JSONObject obj = new JSONObject(msg);
            JSONArray texte = (JSONArray) obj.get("delta");

            JSONObject test = new JSONObject();
            String aEnvoyer = texte.toString();

            System.out.println(aEnvoyer);

//            if(texte.length() == 1)
//            {
//                test = texte.getJSONObject(0);
//                aEnvoyer = test.toString();
//            }
            if(texte.length() == 2)
            {
                test = texte.getJSONObject(1);
                System.out.println("Test " + test);
                aEnvoyer = test.toString();
            }
            else
            {
                aEnvoyer = aEnvoyer.substring(1, aEnvoyer.length() - 1);
            }
//            else
//            {
//                aEnvoyer = texte.toString().substring(1, aEnvoyer.length() - 1);
//            }

//            System.out.println("Test : " + test);
//            System.out.println("Texte : " + texte);
//            System.out.println("A envoyer : " + aEnvoyer);



            System.out.println(aEnvoyer);
            if (client.getPathParameters().containsValue(fichier))
            {
                FileWriter ecrit = new FileWriter(pathFichier + "/" + fichier, true);
                ecrit.write(aEnvoyer + ",");
                ecrit.flush();
                ecrit.close();
                client.getBasicRemote().sendText(msg);
            }
        }
    }
}
