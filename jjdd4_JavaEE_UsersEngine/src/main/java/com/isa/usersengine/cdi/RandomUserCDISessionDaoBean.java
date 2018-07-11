package com.isa.usersengine.cdi;

import com.isa.usersengine.dao.UsersRepositoryDao;
import com.isa.usersengine.domain.User;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Random;

@SessionScoped
public class RandomUserCDISessionDaoBean implements RandomUserCDISessionDao,Serializable {
    int index = Math.abs(new Random().nextInt(3)+1);
    @EJB
    UsersRepositoryDao urd;
    @Override
    public User getRandomUser() {
        return urd.getUserById(index);
    }
}
