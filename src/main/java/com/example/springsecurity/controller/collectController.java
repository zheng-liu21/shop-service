package com.example.springsecurity.controller;

import com.example.springsecurity.entity.collect;
import com.example.springsecurity.service.CollectService;
import com.example.springsecurity.utils.ResultUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述
 *
 * @Author zheng
 * @Date 2023/05/02 21:12:10
 * @Version 1.0
 */
@RestController
@RequestMapping("/collect")
public class collectController {
    @Autowired
    private CollectService collectService;

    /**
     * @Author zheng
     * @Description 取消收藏
     * @Date  2023/5/2 21:26
     * @Param [request]
     * @return com.example.springsecurity.utils.ResultUtil
     **/
    @RequestMapping("/delCollect")
    public ResultUtil delCollect(HttpServletRequest request){
        int userId= Integer.parseInt(request.getParameter("userId"));
        int goodsId= Integer.parseInt(request.getParameter("goodsId"));
        collectService.deleteCollect(userId,goodsId);
        return ResultUtil.ok();
    }

    /**
     * @Author zheng
     * @Description 添加收藏
     * @Date  2023/5/2 21:26
     * @Param [request]
     * @return com.example.springsecurity.utils.ResultUtil
     **/
    @RequestMapping("/addCollect")
    public ResultUtil addCollect(HttpServletRequest request, collect collect){
        int userId= Integer.parseInt(request.getParameter("userId"));
        int goodsId= Integer.parseInt(request.getParameter("goodsId"));
        collect.setUser(userId);
        collect.setGoods(goodsId);
        collectService.insertCollect(collect);
        return ResultUtil.ok();
    }


}
