package com.example.springsecurity.controller;

import com.example.springsecurity.config.security.SecurityUtils;
import com.example.springsecurity.entity.goods;
import com.example.springsecurity.entity.goodsType;
import com.example.springsecurity.entity.impl.LoginUser;
import com.example.springsecurity.entity.indexImage;
import com.example.springsecurity.entity.user;
import com.example.springsecurity.service.GoodsService;
import com.example.springsecurity.service.goodsTypeService;
import com.example.springsecurity.service.indexImageService;
import com.example.springsecurity.utils.ResultUtil;
import com.example.springsecurity.utils.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 描述
 *
 * @Author zheng
 * @Date 2023/04/19 17:46:17
 * @Version 1.0
 */
@RestController
@RequestMapping("/goods")
public class goodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private indexImageService indexImageService;

    @Autowired
    private goodsTypeService goodsTypeService;

    /**
     * @Author zheng
     * @Description 查询购物车商品
     * @Date  2023/4/20 20:47
     * @Param []
     * @return com.example.springsecurity.utils.ResultUtil
     **/
    @RequestMapping("/findCart")
    public ResultUtil findCart() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        user user=loginUser.getUser();
        List<goods> cart=goodsService.findCartByUserID(user.getId());
        for(goods goods : cart){
            goods.setCover("http://localhost:8084/"+goods.getCover());
        }
        return ResultUtil.ok().data("cart",cart);
    }
    /**
     * @Author zheng
     * @Description 查询收藏商品
     * @Date  2023/4/20 20:48
     * @Param []
     * @return com.example.springsecurity.utils.ResultUtil
     **/
    @RequestMapping("/findCollect")
    public ResultUtil findCollect() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        user user=loginUser.getUser();
        List<goods> collect=goodsService.findCollectByUserID(user.getId());
        for(goods goods : collect){
            goods.setCover("http://localhost:8084/"+goods.getCover());
        }
        return ResultUtil.ok().data("collect",collect);

    }


    /**
     * @Author zheng
     * @Description 查询收藏商品
     * @Date  2023/4/20 20:48
     * @Param []
     * @return com.example.springsecurity.utils.ResultUtil
     **/
    @RequestMapping("/getIndexData")
    public ResultUtil getIndexData(Map<String,Object> map) {
        List<indexImage> banner=indexImageService.findAllImage();
        for(indexImage Image : banner){
            Image.setImg("http://localhost:8084/"+Image.getImg());
        }
        List<goods> goods=goodsService.findIndexGoods();
        for(goods good : goods){
            good.setCover("http://localhost:8084/"+good.getCover());
        }
        List<goodsType> group=goodsTypeService.findAllType();
        for(goodsType goodstype : group){
            goodstype.setImage("http://localhost:8084/"+goodstype.getImage());
        }
        map.put("banner",banner);
        map.put("goods",goods);
        map.put("group",group);
        return ResultUtil.ok().data(map);

    }


    /**
     * @Author zheng
     * @Description 查询商品通过id
     * @Date  2023/4/20 20:47
     * @Param []
     * @return com.example.springsecurity.utils.ResultUtil
     **/
    @RequestMapping("/findGoodsById")
    public ResultUtil findGoodsById(HttpServletRequest request) {
        String id=request.getParameter("id");
        goods goods=goodsService.findGoodsById(Integer.parseInt(id));
        goods.setCover("http://localhost:8084/"+goods.getCover());
        return ResultUtil.ok().data("goods",goods);
    }

    /**
     * @Author zheng
     * @Description 查询商品通过类别
     * @Date  2023/5/2 12:30
     * @Param [request]
     * @return com.example.springsecurity.utils.ResultUtil
     **/
    @RequestMapping("/getGoodsList")
    public ResultUtil getGoodsList(HttpServletRequest request) {
        String id=request.getParameter("groupId");
        if(StringUtils.isEmpty(id)){
            List<goods> goods=goodsService.findAllGoods();
            for(goods good : goods){
                good.setCover("http://localhost:8084/"+good.getCover());
            }
            return ResultUtil.ok().data("goods",goods);
        }else{
            List<goods> goods=goodsService.findGoodsByGroupId(Integer.parseInt(id));
            for(goods good : goods){
                good.setCover("http://localhost:8084/"+good.getCover());
            }
            return ResultUtil.ok().data("goods",goods);
        }
    }


}
