package com.isa.usersengine.servlets;

import com.isa.usersengine.dao.UsersRepositoryDaoBean;
import com.isa.usersengine.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/finduser")
public class FindUserByIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsersRepositoryDaoBean usdb = new UsersRepositoryDaoBean();
        Integer id = Integer.parseInt(request.getParameter("id"));
        User ur = usdb.getUserById(id);
        PrintWriter pr = response.getWriter();
        pr.println("<html><head><body><h2>");
        if(ur!=null) pr.println(ur.getName()+" "+ur.getLogin());
        else pr.println(response.SC_BAD_REQUEST);
        pr.close();
    }
}
