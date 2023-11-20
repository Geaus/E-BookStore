package com.example.ebook.Controller;
import com.example.ebook.Entity.JSON.TimerResult;
import com.example.ebook.Entity.UserAuth;
import com.example.ebook.Repository.UserAuthRepository;
import com.example.ebook.Repository.UserRepository;
import com.example.ebook.Service.ClockService;
import com.example.ebook.Service.LoginService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;

@RestController
@Scope("session")
public class LoginController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserAuthRepository userAuthRepository;

    @Autowired
    LoginService loginService;
    @Autowired
    ClockService clockService;

    @PostMapping("/login")
    public UserAuth login(@RequestParam("username") String username, @RequestParam("password") String password,HttpSession session) {

        UserAuth  result= loginService.loginCheck(username,password);

        if(result!=null){
            clockService.startClock();
            return result;
        }
        return null;
    }

    @RequestMapping("/logout")
    public TimerResult logout(HttpSession session){

        TimerResult result =clockService.endClock();
        System.out.println(result);
        return result;
    }
}

