package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Store {
    public String name;
    public String number;
    public String pdname;

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getPdname() {
        return pdname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPdname(String pdname) {
        this.pdname = pdname;
    }

    public Store(String name, String number, String pdname){
        this.name = name;
        this.number = number;
        this.pdname = pdname;
    }
}
