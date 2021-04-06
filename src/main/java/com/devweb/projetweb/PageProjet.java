package com.devweb.projetweb;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "project_page", value = "/project_page")
public class PageProjet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        this.getServletContext().getRequestDispatcher("/webapp/projectPage.jsp").forward(request, response);
    }

    public void destroy() {

    }
}
