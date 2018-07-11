package com.isa.usersengine.cdi;

import com.isa.usersengine.domain.Gender;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class MaxPulseBean {



    public double maxPulsForMan(int age){
        return 202-(0.55*age);
    }
    public double maxPulsForWoman(int age){
        return 216-(1.09*age);
    }
}
