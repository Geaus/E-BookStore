package com.example.mainservice.Repository;

import com.example.mainservice.Entity.User;
import com.example.mainservice.Entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthRepository extends JpaRepository<UserAuth, Integer> {

    UserAuth findUserAuthById(int index);

    UserAuth findTopByUsername(String name);


}