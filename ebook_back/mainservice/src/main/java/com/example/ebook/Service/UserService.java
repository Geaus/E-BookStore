package com.example.ebook.Service;

import com.example.ebook.Entity.User;
import com.example.ebook.Entity.JSON.consume;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.List;

public interface UserService {
    User getUser(int uid);

    List<User> getUsers();

   int newUser(String username,String password,String email);

    consume statistic(@RequestParam int uid, @RequestParam String start, @RequestParam String end) throws ParseException;
}
