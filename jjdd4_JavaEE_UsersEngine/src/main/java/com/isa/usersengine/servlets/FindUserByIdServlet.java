package com.isa.usersengine.servlets;

import com.isa.usersengine.cdi.MaxPulseBean;
import com.isa.usersengine.dao.UsersRepositoryDao;
import com.isa.usersengine.domain.Gender;
import com.isa.usersengine.domain.User;
import com.isa.usersengine.freemarker.FreeMarkerConfig;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.ejb.EJB;
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

@WebServlet("/finduser")
public class FindUserByIdServlet extends HttpServlet {
    @EJB
    private UsersRepositoryDao usdb;
    @Inject
    MaxPulseBean maxPulseBean;
@Inject
    private FreeMarkerConfig conf;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pr = response.getWriter();
        String id = request.getParameter("id");
        Double pulse;
        Map<String, Object> respMap = new HashMap<>();
        if (id == null || id.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        Integer idInt = Integer.parseInt(id);
        if (usdb.getUserById(idInt) != null) {
            User ur = usdb.getUserById(idInt);
            if (ur.getGender().equals(Gender.MAN)) {
                pulse = maxPulseBean.maxPulsForMan(ur.getAge());
            } else {
                pulse = maxPulseBean.maxPulsForWoman(ur.getAge());
            }
            respMap.put("user", ur);
            respMap.put("pulse", pulse);
        } else{
            String errorMessage="Nie znaleziono użytkownika";
            respMap.put("errorMessage",errorMessage);
        }
        Template template = conf.getTemplate("find-user-by-id.ftlh",getServletContext());
        try {
            template.process(respMap,pr);
        } catch (TemplateException e) {
            e.printStackTrace();
        }finally {
            pr.close();
        }
    }

}
