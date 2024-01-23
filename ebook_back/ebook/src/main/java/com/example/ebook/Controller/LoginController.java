package com.example.ebook.Controller;
import com.example.ebook.Entity.UserAuth;
import com.example.ebook.Repository.UserAuthRepository;
import com.example.ebook.Repository.UserRepository;
import com.example.ebook.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserAuthRepository userAuthRepository;

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public UserAuth login(@RequestParam("username") String username, @RequestParam("password") String password) {

       return  loginService.checkUser(username,password);

    }
}

