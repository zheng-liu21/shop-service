package com.example.springsecurity.mapper;

import com.example.springsecurity.entity.collect;
import org.apache.ibatis.annotations.Mapper;

/**
 * 描述
 *
 * @Author zheng
 * @Date 2023/04/20 20:21:36
 * @Version 1.0
 */
@Mapper
public interface CollectMapper {

    public int deleteCollect(int userId,int goodsId);
    public int insertCollect(collect collect);
}
