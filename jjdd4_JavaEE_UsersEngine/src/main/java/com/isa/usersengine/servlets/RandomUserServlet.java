package com.isa.usersengine.servlets;

import com.isa.usersengine.cdi.RandomUserCDIApplicationDao;
import com.isa.usersengine.cdi.RandomUserCDIRequestDao;
import com.isa.usersengine.cdi.RandomUserCDISessionDao;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/random")
public class RandomUserServlet extends HttpServlet {
    @Inject
    private RandomUserCDIApplicationDao randomUserCDIApplicationDao;
    @Inject
    private RandomUserCDIRequestDao randomUserCDIRequestDao;
    @Inject
    private RandomUserCDISessionDao randomUserCDISessionDao;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pr = response.getWriter();
        pr.println("Aplication : " + randomUserCDIApplicationDao.getRandomUser().getName());
        pr.println("Session : "+ randomUserCDISessionDao.getRandomUser().getName());
        pr.println("Request : "+ randomUserCDIRequestDao.getRandomUser().getName());
    }
}
