package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("HELLO")
    public String hello(Model model){
        model.addAttribute("data", "Hello!");
        return "hello";
    }
}
