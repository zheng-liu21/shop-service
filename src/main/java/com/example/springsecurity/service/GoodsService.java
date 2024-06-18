package com.example.springsecurity.service;

import com.example.springsecurity.entity.goods;
import com.example.springsecurity.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述
 *
 * @Author zheng
 * @Date 2023/04/19 17:42:16
 * @Version 1.0
 */
@Service
public class GoodsService {
    @Autowired(required=false)
    public GoodsMapper goodsMapper;

    public goods findGoodsById(int id){
        return goodsMapper.findGoodsById(id);
    }

    public List<goods> findGoodsByGroupId(int goodsId){
        return goodsMapper.findGoodsByGroupId(goodsId);
    }
    public List<goods> findCartByUserID(int userId){
        return goodsMapper.findCartByUserID(userId);
    }
    public List<goods> findIndexGoods(){
        return goodsMapper.findIndexGoods();
    }
    public List<goods> findAllGoods(){
        return goodsMapper.findAllGoods();
    }
    public List<goods> findCollectByUserID(int userId){
        return goodsMapper.findCollectByUserID(userId);
    }

    public int updateGoods(goods goods){
        return goodsMapper.updateGoods(goods);
    }

    public int insertGoods(goods goods){
        return goodsMapper.insertGoods(goods);
    }

    public int deleteGoodsByid(int id){
        return deleteGoodsByid(id);
    }

    public List<goods> findCartChecked(int userId){
        return goodsMapper.findCartChecked(userId);
    }
}
