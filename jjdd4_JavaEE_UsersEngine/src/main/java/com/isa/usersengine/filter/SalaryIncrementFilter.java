package com.isa.usersengine.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(
        filterName = "Salary",
        urlPatterns = "/welcome-user",
        initParams = {
                @WebInitParam(name="defaultSalary",value = "root")
        }
)
public class SalaryIncrementFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Double salary = Double.parseDouble(request.getParameter("salary"));
        if(salary<100){
            salary=100.0;
        }
        request.setAttribute("filteredSalary",salary);
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
