package com.example.applicationtest.DTO;

public class MemberDTO {
    private String id;
    private String pw;
    private String name;
    private String email;
    private String phone;
    private String birth;

    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }

    public String getPw(){
        return pw;
    }
    public void setPw(String pw){
        this.pw = pw;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
            this.email = email;
        }

    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getBirth(){
        return birth;
    }
    public void setBirth(String birth){
        this.birth = birth;
    }
}
