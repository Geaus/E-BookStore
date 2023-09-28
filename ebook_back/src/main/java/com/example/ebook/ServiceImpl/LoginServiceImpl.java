package com.example.ebook.ServiceImpl;

import com.example.ebook.Dao.UserAuthDao;
import com.example.ebook.Entity.UserAuth;
import com.example.ebook.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    UserAuthDao userAuthDao;

    @Override
    public UserAuth loginCheck(String username, String password){

        UserAuth u= userAuthDao.findTopByUsername(username);

        if (password.equals(u.getPassword())) {
            // 如果验证通过，则返回ui
            return u;
        } else {
            // 如果验证不通过，则抛出异常
            throw new RuntimeException("用户名或密码错误");
        }
    }


}
