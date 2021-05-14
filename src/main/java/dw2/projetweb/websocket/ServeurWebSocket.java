package dw2.projetweb.websocket;

import com.google.gson.Gson;
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
    private final Path pathFichier = Path.of("/home/ranyg961/MEGAsync/S6/DW2/hesscode/target/projetweb-1.0-SNAPSHOT/documents");
    static ArrayList<Session> arrSessions = new ArrayList<>();
    static HashMap<String, JSONArray> mapMsg = new HashMap<>();


    @OnOpen
    public void open(Session session) throws IOException
    {


        System.err.println("OPEN");
        arrSessions.add(session);
    }

    @OnClose
    public void close(Session session) throws IOException
    {
        System.err.println("CLOSE");
        arrSessions.remove(session);
    }

    @OnError
    public void onError(Throwable t)
    {
        System.err.println("ERROR");
        t.printStackTrace();
    }

    @OnMessage
    public void message(Session sender, String msg, @PathParam("user") String user, @PathParam("fichier") String fichier) throws IOException
    {
        for (Session client : arrSessions)
        {
            JSONObject obj = new JSONObject(msg);
            System.out.println(obj);

//            String userId = obj.getString("user");
            JSONArray texte = (JSONArray) obj.get("delta");

            String aEnvoyer = texte.toString();
            aEnvoyer = aEnvoyer.substring(1, aEnvoyer.length() - 1);
            System.out.println(aEnvoyer);



            if(client.getPathParameters().containsValue(fichier))
            {
                FileWriter ecrit = new FileWriter(pathFichier + "/" + fichier, true);
                ecrit.write( aEnvoyer + ",");
                ecrit.flush();
                ecrit.close();
                client.getBasicRemote().sendText(msg);
            }
        }
    }
}
