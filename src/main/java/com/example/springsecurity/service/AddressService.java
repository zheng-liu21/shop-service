package com.example.springsecurity.service;

import com.example.springsecurity.entity.address;
import com.example.springsecurity.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述
 *
 * @Author zheng
 * @Date 2023/04/20 20:35:05
 * @Version 1.0
 */
@Service
public class AddressService {

    @Autowired(required=false)
    public AddressMapper addressMapper;
    public List<address> findCAddressByUserID(int id){
        return addressMapper.findCAddressByUserID(id);
    }

    public int updateAddress(address address){
        return addressMapper.updateAddress(address);
    }

    public int insertAddress(address address){
        return addressMapper.insertAddress(address);
    }
}
