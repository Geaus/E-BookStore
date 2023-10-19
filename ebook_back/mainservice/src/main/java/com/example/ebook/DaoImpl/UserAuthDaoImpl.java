package com.example.ebook.DaoImpl;
import com.example.ebook.Dao.UserAuthDao;
import com.example.ebook.Entity.UserAuth;
import com.example.ebook.Repository.UserAuthRepository;
import com.example.ebook.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserAuthDaoImpl implements UserAuthDao {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserAuthRepository userAuthRepository;

    @Override
    public UserAuth findTopByUsername(String name){
        return userAuthRepository.findTopByUsername(name);
    }

    @Override
    public   UserAuth findUserAuthById(int index){
        return userAuthRepository.findUserAuthById(index);
    }

    @Override
    public void save(UserAuth a){
        userAuthRepository.save(a);
    }
}
