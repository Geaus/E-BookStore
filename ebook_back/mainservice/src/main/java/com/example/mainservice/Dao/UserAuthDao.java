package com.example.mainservice.Dao;

import com.example.mainservice.Entity.UserAuth;

public interface UserAuthDao {

    UserAuth findTopByUsername(String name);

    UserAuth findUserAuthById(int index);

    void save(UserAuth a);
}
