package com.example.springsecurity.mapper;

import com.example.springsecurity.entity.user;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * UserMapper
 *
 * @Author zheng
 * @Date 2023/04/17 15:16:24
 * @Version 1.0
 */
@Mapper
public interface UserMapper {
    public user findUserByName(String user_name);
    public List<user> findAlluser(user user);
    public int updateUser(user user);
    public int insertUser(user user);
    public int deleteUserByid(int id);
    public user findUserById(int id);

}
