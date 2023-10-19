package com.example.ebook.Dao;

import com.example.ebook.Entity.User;

import java.util.List;

public interface UserDao {

//    User getUserById(int uid);
//    List<User> getUsers();
//
//    int newUser(String username,String password,String email);
//
//    consume statistic(@RequestParam int uid, @RequestParam String start, @RequestParam String end) throws ParseException;

    //////////////////////
    User findUserByIdIs(int index);

    List<User> findUsersByUserAuth_UserType(Integer index);

    void save(User u);

    List<User> findAll();
}
