package com.isa.usersengine;

public class Main {
    public static void main(String[] args) {
        String s = "";
        System.out.println(isEmpty(s));
    }
    static boolean isEmpty(String s){
        if(s.isEmpty()) return true;
        else return false;
    }
}
