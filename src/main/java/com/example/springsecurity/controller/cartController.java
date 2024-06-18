package com.example.springsecurity.controller;

import com.example.springsecurity.config.security.SecurityUtils;
import com.example.springsecurity.entity.cart;
import com.example.springsecurity.entity.goods;
import com.example.springsecurity.entity.impl.LoginUser;
import com.example.springsecurity.entity.user;
import com.example.springsecurity.service.CartService;
import com.example.springsecurity.service.GoodsService;
import com.example.springsecurity.utils.ResultUtil;
import com.example.springsecurity.utils.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 描述
 *
 * @Author zheng
 * @Date 2023/05/02 12:51:40
 * @Version 1.0
 */
@RestController
@RequestMapping("/cart")
public class cartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private GoodsService goodsService;

    /**
     * @Author zheng
     * @Description 添加商品到购物车
     * @Date  2023/5/2 13:00
     * @Param [request]
     * @return com.example.springsecurity.utils.ResultUtil
     **/
    @RequestMapping("/addGoods")
    public ResultUtil addGoods(HttpServletRequest request,cart cart){
        int userId= Integer.parseInt(request.getParameter("userId"));
        int goodsId= Integer.parseInt(request.getParameter("goodsId"));
        cart.setGoods(goodsId);
        cart.setUser(userId);
        cart ResCart=cartService.findCartByID(cart);
        if(StringUtils.isNull(ResCart)){
            ResCart.setGoods(goodsId);
            ResCart.setUser(userId);
            cartService.insertCart(ResCart);
        }else {
            ResCart.setNumber(ResCart.getNumber()+1);
            cartService.updateCart(ResCart);
        }
        return ResultUtil.ok();
    }

    /**
     * @Author zheng
     * @Description 删除商品到购物车
     * @Date  2023/5/2 13:00
     * @Param [request]
     * @return com.example.springsecurity.utils.ResultUtil
     **/
    @RequestMapping("/deleteCartGoods")
    public ResultUtil deleteCartGoods(HttpServletRequest request){
        int userId= Integer.parseInt(request.getParameter("userId"));
        int goodsId= Integer.parseInt(request.getParameter("goodsId"));
        cartService.deleteCart(userId,goodsId);
        return ResultUtil.ok();
    }


    /**
     * @Author zheng
     * @Description 修改购物车商品数量
     * @Date  2023/5/2 13:00
     * @Param [request]
     * @return com.example.springsecurity.utils.ResultUtil
     **/
    @RequestMapping("/updateGoodsNumber")
    public ResultUtil updateGoodsNumber(HttpServletRequest request,cart cart){
        int userId= Integer.parseInt(request.getParameter("userId"));
        int goodsId= Integer.parseInt(request.getParameter("goodsId"));
        int number=Integer.parseInt(request.getParameter("number"));
        cart.setGoods(goodsId);
        cart.setUser(userId);
        cart ResCart=cartService.findCartByID(cart);
        if(number==0){
            cartService.deleteCart(userId,goodsId);
        }else {
            ResCart.setNumber(number);
            cartService.updateCart(ResCart);
        }
        return ResultUtil.ok();
    }


    /**
     * @Author zheng
     * @Description 修改购物车商品状态
     * @Date  2023/5/2 13:00
     * @Param [request]
     * @return com.example.springsecurity.utils.ResultUtil
     **/
    @RequestMapping("/checkedCartGoods")
    public ResultUtil checkedCartGoods(HttpServletRequest request,cart cart){
        int userId= Integer.parseInt(request.getParameter("userId"));
        int goodsId= Integer.parseInt(request.getParameter("goodsId"));
        int is_checked=Integer.parseInt(request.getParameter("is_checked"));
        cart.setGoods(goodsId);
        cart.setUser(userId);
        cart ResCart=cartService.findCartByID(cart);
        ResCart.setIs_checked(is_checked);
        cartService.updateCart(ResCart);
        return ResultUtil.ok();
    }


    /**
     * @Author zheng
     * @Description 获取购物车中选中的商品列表
     * @Date  2023/5/2 13:00
     * @Param [request]
     * @return com.example.springsecurity.utils.ResultUtil
     **/
    @RequestMapping("/getCartGoods")
    public ResultUtil getCartGoods(HttpServletRequest request){

        int userId= Integer.parseInt(request.getParameter("userId"));
        List<goods> goodsList=goodsService.findCartChecked(userId);
        for(goods good : goodsList){
            good.setCover("http://localhost:8084/"+good.getCover());
        }
        return ResultUtil.ok().data("goodsList",goodsList);
    }

}
