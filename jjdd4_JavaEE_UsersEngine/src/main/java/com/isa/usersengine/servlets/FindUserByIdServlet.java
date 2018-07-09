package com.isa.usersengine.servlets;

import com.isa.usersengine.dao.UsersRepositoryDao;
import com.isa.usersengine.dao.UsersRepositoryDaoBean;
import com.isa.usersengine.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

@WebServlet("/finduser")
public class FindUserByIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pr = response.getWriter();
            UsersRepositoryDao usdb = new UsersRepositoryDaoBean();
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            User ur = usdb.getUserById(id);
            pr.println("<html><head><body><h2>");
            pr.println(ur.getName() + " " + ur.getLogin());
        }catch (NumberFormatException | NullPointerException  ex){
            pr.println(response.SC_BAD_REQUEST);
        }

        pr.close();
    }
}
