package com.example.springsecurity.service;

import com.example.springsecurity.entity.user;
import com.example.springsecurity.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * UserService
 *
 * @Author zheng
 * @Date 2023/04/17 15:41:58
 * @Version 1.0
 */

@Service
public class UserService {
    @Autowired(required=false)
    public UserMapper userMapper;

    public user findUserByName(String user_name){return userMapper.findUserByName(user_name);}

    public List<user> findAlluser(user user){return userMapper.findAlluser(user);};

    public int updateUser(user user){
        user.setUpdate_time(new Date());
        return userMapper.updateUser(user);
    }

    public int insertUser(user user){
        user.setCreate_time(new Date());
        user.setUpdate_time(new Date());
        return userMapper.insertUser(user);
    }

    public int deleteUserByid(int id){
        return userMapper.deleteUserByid(id);
    }

    public user findUserById(int id){
        return userMapper.findUserById(id);
    }
}
