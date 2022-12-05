package com.example.applicationtest.Singleton;

public class UserSingleton {
    private static UserSingleton instance;

    public String userId;

    private UserSingleton(){

    }

    public static synchronized UserSingleton getInstance(){
        if(instance == null){
            instance = new UserSingleton();
        }
        return instance;
    }
}
