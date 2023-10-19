package com.example.ebook.Dao;

import com.example.ebook.Entity.UserAuth;

public interface UserAuthDao {

    UserAuth findTopByUsername(String name);

    UserAuth findUserAuthById(int index);

    void save(UserAuth a);
}
