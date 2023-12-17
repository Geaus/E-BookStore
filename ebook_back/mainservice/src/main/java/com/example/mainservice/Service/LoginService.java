package com.example.mainservice.Service;

import com.example.mainservice.Entity.UserAuth;

public interface LoginService {

    UserAuth loginCheck(String username, String password);

}
