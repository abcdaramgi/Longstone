package hello.hellospring.controller;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import hello.hellospring.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
    //안드로이드 요청(id, pw 받아서 db연동)
//    @GetMapping(path = "/android")
//    @RequestMapping(value="/android", method = {RequestMethod.POST})
//    public String androidPage(HttpServletRequest request, Model model){
//        System.out.println("서버에서 안드로이드 접속 요청함");
//        try{
//            String androidName = request.getParameter("name");
//            String androidEmail = request.getParameter("email");
//            System.out.println("안드로이드에서 받아온 id : " + androidName);
//            System.out.println("안드로이드에서 받아온 pw : " + androidEmail);
//            model.addAttribute("android", androidName);
//            return "goodjob";
//        }catch(Exception e){
//            e.printStackTrace();
//            return "null";
//        }
//    }

//    @PostMapping("/android")
//    public void requsetBodyJson(@RequestBody User user){
//        System.out.println(user.name);
//        System.out.println(user.email);
//    }
}
