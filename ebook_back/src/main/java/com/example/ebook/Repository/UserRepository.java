package com.example.ebook.Repository;

import com.example.ebook.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByIdIs(int index);

    List<User> findUsersByUserAuth_UserType(Integer index);

    List<User> findAll();


}