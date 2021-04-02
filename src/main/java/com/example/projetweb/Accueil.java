package com.example.projetweb;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "Accueil", value = "/Accueil")
public class Accueil extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        this.getServletContext().getRequestDispatcher("/webapp/index.jsp").forward(request, response);
    }

    public void destroy() {

    }
}