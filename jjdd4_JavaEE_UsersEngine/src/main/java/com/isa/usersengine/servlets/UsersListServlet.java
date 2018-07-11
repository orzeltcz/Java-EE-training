package com.isa.usersengine.servlets;

import com.isa.usersengine.dao.UsersRepositoryDao;
import com.isa.usersengine.freemarker.FreeMarkerConfig;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet("/allUsers")
public class UsersListServlet extends HttpServlet {
    @Inject
    private UsersRepositoryDao usersRepositoryDao;
    @Inject
    private FreeMarkerConfig conf;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pr = response.getWriter();
        Map<String,Object> responseMap = new HashMap<>();
        responseMap.put("users",usersRepositoryDao.getUsersList());
        Template template = conf.getTemplate("users-list.ftlh",getServletContext());
        try {
            template.process(responseMap,pr);
        } catch (TemplateException e) {
            e.printStackTrace();
        }finally {
            pr.close();
        }
    }
}
