package com.isa.usersengine.interceptors;

import com.isa.usersengine.domain.Gender;
import com.isa.usersengine.domain.User;
import org.omg.PortableInterceptor.Interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class GenderInterceptor {

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {

        Object[] parameters = context.getParameters();
        for (Object parameter : parameters) {
            User user = (User) parameter;
            if (user.getGender() == null) {
                if (user.getName().endsWith("a")) {
                    user.setGender(Gender.WOMAN);
                } else {
                    user.setGender(Gender.MAN);
                }
            }
        }
        context.setParameters(parameters);
        return context.proceed();
    }
}
