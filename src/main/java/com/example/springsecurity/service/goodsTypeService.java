package com.example.springsecurity.service;

import com.example.springsecurity.entity.goodsType;
import com.example.springsecurity.mapper.GoodsMapper;
import com.example.springsecurity.mapper.goodsTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述
 *
 * @Author zheng
 * @Date 2023/04/28 19:36:07
 * @Version 1.0
 */
@Service
public class goodsTypeService {
    @Autowired(required=false)
    public goodsTypeMapper goodsTypeMapper;
    public List<goodsType> findAllType(){
        return goodsTypeMapper.findAllType();
    }
}
