package com.example.demo.model;

public class OrderPost {
    private String uid;
    private Integer pid;
    private String pname;
    private Long price;
    private Long pcount;


    public String getPname() {
        return pname;
    }

    public Long getPrice() {
        return price;
    }

    public Long getPcount() {
        return pcount;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setPcount(Long pcount) {
        this.pcount = pcount;
    }

    public Integer getPid() {
        return pid;
    }

    public String getUid() {
        return uid;
    }
}