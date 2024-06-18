package com.example.springsecurity.controller;

import com.alibaba.fastjson2.JSON;
import com.example.springsecurity.entity.goods;
import com.example.springsecurity.entity.order;
import com.example.springsecurity.entity.orderGoods;
import com.example.springsecurity.service.OrderGoodsService;
import com.example.springsecurity.service.OrderService;
import com.example.springsecurity.utils.ResultUtil;
import com.example.springsecurity.utils.orderNoUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 描述
 *
 * @Author zheng
 * @Date 2023/05/03 20:47:49
 * @Version 1.0
 */
@RestController
@RequestMapping("/order")
public class orderController {
    @Autowired
    private OrderGoodsService orderGoodsService;

    @Autowired
    private OrderService orderService;

    /***
     * @Author zheng
     * @Description 创建订单
     * @Date  2023/5/4 18:18
     * @Param [order, goodsList]
     * @return com.example.springsecurity.utils.ResultUtil
     **/
    @RequestMapping("/createOrder")
    public ResultUtil createOrder(@RequestBody Map<String,Object> map){
        String orderNo= orderNoUtils.getOrderCode((Integer) map.get("user"));//生成订单编号
        order order=new order();
        order.setUser((Integer) map.get("user"));
        order.setAddress((String) map.get("address"));
        Double amount = Double.parseDouble(map.get("amount").toString());
        order.setAmount(amount);
        order.setStatus((Integer) map.get("status"));
        order.setOrder_no(orderNo);
        order.setCreate_time(new Date());
        order.setUpdate_time(new Date());
        orderService.insertOrder(order);
        order order1=orderService.findOrderByNo(orderNo);
        List<LinkedHashMap> goodsList= (List<LinkedHashMap>) map.get("goodsList");
        orderGoods orderGoods=new orderGoods();
        for(LinkedHashMap<String,Object> hashMap :goodsList){
            goods goods = JSON.parseObject(JSON.toJSONString(hashMap), goods.class);
            orderGoods.setOrder(order1.getId());
            orderGoods.setGoods(goods.getId());
            orderGoods.setPrice(goods.getPrice());
            orderGoods.setNumber(goods.getNumber());
            orderGoods.setCreate_time(new Date());
            orderGoods.setUpdate_time(new Date());
            orderGoodsService.insertOrderGoods(orderGoods);
        }
        return ResultUtil.ok().data("orderId",order1.getId());
    }


    /**
     * @Author zheng
     * @Description 获取订单
     * @Date  2023/5/2 13:00
     * @Param [request]
     * @return com.example.springsecurity.utils.ResultUtil
     **/
    @RequestMapping("/getOrderInfo")
    public ResultUtil getOrderInfo(HttpServletRequest request){

        int orderID= Integer.parseInt(request.getParameter("orderID"));
        order order=orderService.findOrderById(orderID);

        return ResultUtil.ok().data("order",order);
    }

}
