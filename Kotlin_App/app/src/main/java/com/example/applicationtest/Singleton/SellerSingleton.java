package com.example.applicationtest.Singleton;

public class SellerSingleton {
    private static SellerSingleton instance;

    public String sellerId;

    private SellerSingleton(){

    }

    public static synchronized SellerSingleton getInstance(){
        if(instance == null){
            instance = new SellerSingleton();
        }
        return instance;
    }
}
