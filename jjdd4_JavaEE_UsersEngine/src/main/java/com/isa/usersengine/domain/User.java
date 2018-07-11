package com.isa.usersengine.domain;

import lombok.Data;
@Data
public class User {
    private int id;
    private String name;
    private String login;
    private String password;
    private int age;
    private Gender gender;
}
