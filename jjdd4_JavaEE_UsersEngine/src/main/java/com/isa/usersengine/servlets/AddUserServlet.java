package com.isa.usersengine.servlets;

import com.isa.usersengine.cdi.FileUploadProcessorDao;
import com.isa.usersengine.dao.UsersRepositoryDao;
import com.isa.usersengine.dao.UsersRepositoryDaoBean;
import com.isa.usersengine.domain.User;
import com.isa.usersengine.exceptions.UserImageNotFound;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet("/add-user")
@MultipartConfig
public class AddUserServlet extends HttpServlet {
    @EJB
   private UsersRepositoryDao usersRepositoryDao;
    @Inject
    FileUploadProcessorDao fileUploadProcessorDao;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

           String name = request.getParameter("name");
           String id = request.getParameter("id");
           String login = request.getParameter("login");
           String password = request.getParameter("password");
           String age = request.getParameter("age");
        User user = new User();
        user.setId(Integer.parseInt(id));
        user.setLogin(login);
        user.setName(name);
        user.setPassword(password);
        user.setAge(Integer.parseInt(age));

        try {
            File file = fileUploadProcessorDao.uploadImageFile(request.getPart("image"));
            String imageURL = "/images/"+file.getName();
            user.setImageUrl(imageURL);
        } catch (UserImageNotFound userImageNotFound) {
            userImageNotFound.printStackTrace();
        }
        usersRepositoryDao.addUser(user);
        if(!isParamValid(name,id,login,password,age)){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return;
        }


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
