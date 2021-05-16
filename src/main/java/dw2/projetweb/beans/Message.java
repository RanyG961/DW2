package dw2.projetweb.beans;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Calendar;
import java.util.Locale;

public class Message
{
    private final int NUM_MESSAGE;
    private static int nbMessages = 0;
    private String textMessage;
    private String user;
    private String date;

    public Message(String textMessage, String user, String date)
    {
        this.NUM_MESSAGE = ++nbMessages;
        this.textMessage = textMessage;
        this.user = user;
        this.date = date;
    }

    public String getTextMessage()
    {
        return textMessage;
    }

    public int getNUM_MESSAGE()
    {
        return NUM_MESSAGE;
    }

    public String getUser()
    {
        return user;
    }

    public String getDate()
    {
        return date;
    }
}