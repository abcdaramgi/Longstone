package dohyung.membermanagement.controller;

import dohyung.membermanagement.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


//스프링이 뜰 때 생성함
@Controller
public class MemberController {
    private final MemberService memberService;


    //멤버 서비스를 스프링컨테이너에 있는 멤버서비스를 가져다가 연결해줌
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


}
