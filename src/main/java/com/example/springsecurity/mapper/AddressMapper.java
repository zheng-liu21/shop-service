package com.example.springsecurity.mapper;

import com.example.springsecurity.entity.address;
import com.example.springsecurity.entity.user;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 描述
 *
 * @Author zheng
 * @Date 2023/04/20 20:35:27
 * @Version 1.0
 */
@Mapper
public interface AddressMapper {

    public List<address> findCAddressByUserID(int id);
    public int updateAddress(address address);
    public int insertAddress(address address);


}
