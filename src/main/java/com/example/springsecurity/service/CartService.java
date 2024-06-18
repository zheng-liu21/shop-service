package com.example.springsecurity.service;

import com.example.springsecurity.entity.cart;
import com.example.springsecurity.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 描述
 *
 * @Author zheng
 * @Date 2023/05/02 12:42:02
 * @Version 1.0
 */
@Service
public class CartService {
    @Autowired(required=false)
    public CartMapper cartMapper;

    public int insertCart(cart cart){
        cart.setCreate_time(new Date());
        cart.setUpdate_time(new Date());
        cart.setIs_checked(0);
        cart.setNumber(1);
        return cartMapper.insertCart(cart);
    }

    public int deleteCart(int userId,int goodsId){
        return cartMapper.deleteCart(userId,goodsId);
    }

    public cart findCartByID(cart cart){
        return cartMapper.findCartByID(cart);
    }

    public int updateCart(cart cart){
        return cartMapper.updateCart(cart);
    }
}
