package com.example.springsecurity.service;


import com.example.springsecurity.entity.indexImage;
import com.example.springsecurity.mapper.IndexImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述
 *
 * @Author zheng
 * @Date 2023/04/28 19:03:52
 * @Version 1.0
 */
@Service
public class indexImageService {

    @Autowired(required=false)
    public IndexImageMapper indexImageMapper;


    public List<indexImage> findAllImage(){
        return indexImageMapper.findAllImage();
    }
}
