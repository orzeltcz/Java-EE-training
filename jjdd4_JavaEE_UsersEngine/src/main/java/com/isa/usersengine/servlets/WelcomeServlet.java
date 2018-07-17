package com.isa.usersengine.servlets;

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
import java.util.HashMap;
import java.util.Map;

@WebServlet("/welcome-user")
public class WelcomeServlet extends HttpServlet {
    @Inject
    private FreeMarkerConfig conf;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String name = request.getParameter("imie");
       String salary = request.getAttribute("filteredSalary").toString();
        PrintWriter pr = response.getWriter();
        if(name=="" || name==null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        Template template = conf.getTemplate("welcome-user.ftlh",getServletContext());
        Map<String,Object> model = new HashMap<>();
        model.put("name",name);
        model.put("salary",salary);
        try {
            template.process(model,pr);
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        pr.close();
    }
}
