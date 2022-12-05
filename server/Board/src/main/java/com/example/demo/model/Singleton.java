package com.example.demo.model;

public class Singleton {
    private static Singleton instance = new Singleton();
    public int AutoIncresePdId;
    private Singleton(){

    }
    public static synchronized Singleton getInstance(){
        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }

}
