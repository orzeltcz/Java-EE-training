package com.isa.usersengine.servlets;

import com.isa.usersengine.cdi.FileUploadProcessorDao;
import com.isa.usersengine.dao.UsersRepositoryDao;
import com.isa.usersengine.domain.User;
import com.isa.usersengine.exceptions.UserImageNotFound;
import com.isa.usersengine.freemarker.FreeMarkerConfig;
import freemarker.template.Template;
import freemarker.template.TemplateException;

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
import java.util.HashMap;
import java.util.Map;

@WebServlet("/user")
@MultipartConfig
public class AddUserServlet extends HttpServlet {
    @EJB
   private UsersRepositoryDao usersRepositoryDao;
    @Inject
    FileUploadProcessorDao fileUploadProcessorDao;
    @Inject
    FreeMarkerConfig freeMarkerConfig;

    private Integer id;

    private Map<String, String> editValues;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

           String name = request.getParameter("name");
           String login = request.getParameter("login");
           String password = request.getParameter("password");
           String age = request.getParameter("age");
        User user = new User();
        user.setId(usersRepositoryDao.getUsersList().size()+1);
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
        if(!isParamValid(name,login,password,age)){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return;
        }
        response.sendRedirect("/allUsers");
        }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user =  usersRepositoryDao.getUserById(id);
        for (String key: editValues.keySet()) {
            switch (key){
                case "id":{
                    user.setId(Integer.parseInt(editValues.get(key)));
                    break;
                }
                case "name":{
                    user.setName(editValues.get(key));
                }
                case "login":{
                    user.setLogin(editValues.get(key));
                    break;
                }
                case "password":{
                    user.setPassword(editValues.get(key));
                    break;
                }
                case "age":{
                    user.setAge(Integer.parseInt(editValues.get(key)));
                    break;
                }
            }
        }

        resp.sendRedirect("/allUsers");
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String,Object> model = new HashMap<>();
        Map<String,String[]> parametes = request.getParameterMap();

        if(isOneOrMoreParametersNotNull(parametes)){
            doPut(request,response);
            return;
        }
        Template template = freeMarkerConfig.getTemplate("user.ftlh",getServletContext());
        if(request.getParameter("id")==null){
            model.put("method","post");
        }else{
            model.put("method","get");
            id=Integer.parseInt(request.getParameter("id"));
            model.put("User",usersRepositoryDao.getUserById(id));
        }
        try {
            template.process(model,response.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }


    private boolean isParamValid(String... params){
        for (String param: params) {
            if(param==null || param.isEmpty()) return false;
        }
        return true;
    }

    private boolean isOneOrMoreParametersNotNull(Map<String,String[]> params){
        editValues = new HashMap<>();
        for(String paramName : params.keySet()){
            String[] paramsValues = params.get(paramName);
            if(paramsValues[0]!=null && !paramsValues[0].isEmpty()){
                editValues.put(paramName,paramsValues[0]);
            }
        }
        if(!editValues.containsKey("id") && editValues.values().size()>0){
            return true;
        }
        return false;
    }

}
