package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class plzController {

    @GetMapping("/plz")
    public String address() {
        System.out.println("제발요");

        return "plz";
    }
}
