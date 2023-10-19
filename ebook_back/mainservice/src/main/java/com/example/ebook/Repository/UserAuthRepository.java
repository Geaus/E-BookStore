package com.example.ebook.Repository;

import com.example.ebook.Entity.User;
import com.example.ebook.Entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthRepository extends JpaRepository<UserAuth, Integer> {

    UserAuth findUserAuthById(int index);

    UserAuth findTopByUsername(String name);


}