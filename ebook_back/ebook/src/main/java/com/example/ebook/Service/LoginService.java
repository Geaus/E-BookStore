package com.example.ebook.Service;

import com.example.ebook.Entity.UserAuth;

public interface LoginService {

    UserAuth checkUser(String username, String password);
}
