package com.example.springsecurity.mapper;

import com.example.springsecurity.entity.goodsType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 描述
 *
 * @Author zheng
 * @Date 2023/04/28 19:35:42
 * @Version 1.0
 */
@Mapper
public interface goodsTypeMapper {
    public List<goodsType> findAllType();
}
