package com.example.springsecurity.mapper;

import com.example.springsecurity.entity.goods;
import com.example.springsecurity.entity.user;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 描述GoodsMapper
 *
 * @Author zheng
 * @Date 2023/04/19 17:15:42
 * @Version 1.0
 */
@Mapper
public interface GoodsMapper {
    public goods findGoodsById(int id);
    public List<goods> findCartByUserID(int userId);
    public List<goods> findCollectByUserID(int userId);
    public List<goods> findIndexGoods();
    public List<goods> findGoodsByGroupId(int goodsId);
    public List<goods> findCartChecked(int userID);
    public int updateGoods(goods goods);
    public int insertGoods(goods goods);
    public List<goods> findAllGoods();
    public int deleteGoodsByid(int id);
}
