package com.example.springsecurity.mapper;

import com.example.springsecurity.entity.indexImage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 描述
 *
 * @Author zheng
 * @Date 2023/04/28 18:57:23
 * @Version 1.0
 */
@Mapper
public interface IndexImageMapper {

    public List<indexImage> findAllImage();
}
