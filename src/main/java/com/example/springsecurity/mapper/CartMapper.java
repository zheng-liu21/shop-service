package com.example.springsecurity.mapper;

import com.example.springsecurity.entity.cart;
import org.apache.ibatis.annotations.Mapper;

/**
 * 描述
 *
 * @Author zheng
 * @Date 2023/04/19 16:45:00
 * @Version 1.0
 */
@Mapper
public interface CartMapper {

    public int insertCart(cart cart);

    public int deleteCart(int userId,int goodsId);

    public cart findCartByID(cart cart);

    public int updateCart(cart cart);

}
