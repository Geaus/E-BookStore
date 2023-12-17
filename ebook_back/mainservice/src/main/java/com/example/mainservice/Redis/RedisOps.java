package com.example.mainservice.Redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisOps {
    @Autowired
    RedisTemplate redisTemplate;

    public Object get(String key){
        try{
            return redisTemplate.opsForValue().get(key);

        }catch (Exception e){
//            e.printStackTrace();
            System.out.println("Redis 连接出现异常");
            return null;
        }
    }

    public void set(String key,Object value){
        try{
            redisTemplate.opsForValue().set(key,value);

        }catch (Exception e){
//            e.printStackTrace();
            System.out.println("Redis 连接出现异常");
            return;
        }
    }

    public void delete(String key){
        try{
            redisTemplate.delete(key);

        }catch (Exception e){
//            e.printStackTrace();
            System.out.println("Redis 连接出现异常");
            return;
        }
    }

}
