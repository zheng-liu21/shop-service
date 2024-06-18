package com.example.springsecurity.service;

import com.example.springsecurity.entity.collect;
import com.example.springsecurity.mapper.CollectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述
 *
 * @Author zheng
 * @Date 2023/04/20 20:22:01
 * @Version 1.0
 */
@Service
public class CollectService {
    @Autowired(required = false)
    public CollectMapper collectMapper;
    public int deleteCollect(int userId,int goodsId){
        return collectMapper.deleteCollect(userId,goodsId);
    }
    public int insertCollect(collect collect){
        return collectMapper.insertCollect(collect);
    }
}
