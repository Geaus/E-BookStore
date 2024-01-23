package com.example.nginx_2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {
    @RequestMapping("/hello")
    public void sayHello(){
        System.out.println("hello");
    }
}
