package com.example.demo.model;

public class User {
    public String id;
    public String pw;
    public String nickname;
    public String name;
    public String email;
    public String phone;
    public String birth;

    public String getId(){ return id;}
    public void setId(String id){
        this.id = id;
    }

    public String getPw(){
        return pw;
    }
    public void setPw(String pw){
        this.pw = pw;
    }

    public String getNickname(){return nickname;}
    public void setNickname(){this.nickname = nickname;}
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){ return email;}
    public void setEmail(String email){ this.email = email; }

    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getBirth(){
        return birth;
    }
    public void setBirth(String birth){this.birth = birth;}

    public User(){
        this.name = name;
        this.email = email;
    }
    public User(String name, String email){
        this.name = name;
        this.email = email;
    }
}
