package com.example.projetweb;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "user_page", value = "/user_page")
public class PageUtilisateur extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        this.getServletContext().getRequestDispatcher("/webapp/user_page.jsp").forward(request, response);
    }

    public void destroy() {

    }
}
