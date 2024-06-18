package com.example.springsecurity.service;

import com.example.springsecurity.entity.order;
import com.example.springsecurity.mapper.GoodsMapper;
import com.example.springsecurity.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述
 *
 * @Author zheng
 * @Date 2023/05/03 20:28:56
 * @Version 1.0
 */
@Service
public class OrderService {
    @Autowired(required=false)
    public OrderMapper orderMapper;

    public int insertOrder(order order){
        return orderMapper.insertOrder(order);
    }
    public order findOrderByNo(String order_no){
        return orderMapper.findOrderByNo(order_no);
    }

    public order findOrderById(int id){
        return orderMapper.findOrderById(id);
    }
}
