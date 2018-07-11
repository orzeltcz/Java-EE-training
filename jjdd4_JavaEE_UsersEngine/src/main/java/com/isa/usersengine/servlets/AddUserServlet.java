package com.isa.usersengine.servlets;

import com.isa.usersengine.dao.UsersRepositoryDao;
import com.isa.usersengine.dao.UsersRepositoryDaoBean;
import com.isa.usersengine.domain.User;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add-user")
public class AddUserServlet extends HttpServlet {
    @EJB
   private UsersRepositoryDao usersRepositoryDao;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

           String name = request.getParameter("name");
           String id = request.getParameter("id");
           String login = request.getParameter("login");
           String password = request.getParameter("password");
           String age = request.getParameter("age");

        if(!isParamValid(name,id,login,password,age)){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return;
        }

           User user = new User();
           user.setId(Integer.parseInt(id));
           user.setLogin(login);
           user.setName(name);
           user.setPassword(password);
           user.setAge(Integer.parseInt(age));
           usersRepositoryDao.addUser(user);

        }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private boolean isParamValid(String... params){
        for (String param: params) {
            if(param==null || param.isEmpty()) return false;
        }
        return true;
    }

}
