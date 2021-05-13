package dw2.projetweb.websocket;

import org.json.JSONObject;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;

@ServerEndpoint(value = "/changement")
public class ServeurWebSocket
{
    static ArrayList<Session> arrSessions = new ArrayList<>();

    @OnOpen public void open(Session session)
    {
        System.err.println("OPEN");
        arrSessions.add(session);
    }

    @OnClose public void close(Session session)
    {
        System.err.println("CLOSE");
        arrSessions.remove(session);
    }

    @OnError public void onError(Throwable t)
    {
        System.err.println("ERROR");
        t.printStackTrace();
    }

    @OnMessage public void message(Session sender, String msg) throws IOException
    {
        for (Session client : arrSessions)
        {
//            JSONObject json = new JSONObject();

//            System.out.println(sender);
//
//            json.put("id", sender.getId());
//            json.put("message", msg);
//            client.getBasicRemote().sendText(json.toString());
//            System.out.println(msg);
            client.getBasicRemote().sendText(msg);
        }
    }
}
