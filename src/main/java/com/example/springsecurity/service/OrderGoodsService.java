package com.example.springsecurity.service;

import com.example.springsecurity.entity.orderGoods;
import com.example.springsecurity.mapper.OrderGoodsMapper;
import com.example.springsecurity.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述
 *
 * @Author zheng
 * @Date 2023/05/03 20:41:29
 * @Version 1.0
 */
@Service
public class OrderGoodsService {
    @Autowired(required=false)
    public OrderGoodsMapper orderGoodsMapper;

    public int insertOrderGoods(orderGoods orderGoods){
        return orderGoodsMapper.insertOrderGoods(orderGoods);
    }
}
