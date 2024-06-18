package com.example.springsecurity.mapper;

import com.example.springsecurity.entity.order;
import com.example.springsecurity.entity.orderGoods;
import org.apache.ibatis.annotations.Mapper;

/**
 * 描述
 *
 * @Author zheng
 * @Date 2023/05/03 20:41:01
 * @Version 1.0
 */
@Mapper
public interface OrderGoodsMapper {
    public int insertOrderGoods(orderGoods orderGoods);

}
