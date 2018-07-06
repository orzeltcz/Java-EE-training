package com.isa.usersengine.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/welcome-user")
public class WelcomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String name = request.getParameter("imie");
        PrintWriter pr = response.getWriter();
        pr.println("<html><head><body><h2>Witaj:");
        if(name!="" && name!=null) pr.println(name);
        else pr.println(response.SC_BAD_REQUEST);
        pr.println("</h2></body></head></html>");
        pr.close();
    }
}
