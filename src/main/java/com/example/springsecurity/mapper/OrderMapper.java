package com.example.springsecurity.mapper;

import com.example.springsecurity.entity.order;
import com.example.springsecurity.entity.user;
import org.apache.ibatis.annotations.Mapper;

/**
 * 描述
 *
 * @Author zheng
 * @Date 2023/05/03 20:28:37
 * @Version 1.0
 */
@Mapper
public interface OrderMapper {
    public int insertOrder(order order);

    public order findOrderByNo(String order_no);

    public order findOrderById(int id);

}
