package com.example.springsecurity.controller;

import com.example.springsecurity.config.redis.redisUtil;
import com.example.springsecurity.service.msgService;
import com.example.springsecurity.utils.RandomUtil;
import com.example.springsecurity.utils.ResultUtil;
import com.example.springsecurity.utils.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 发送手机验证码
 *
 * @Author zheng
 * @Date 2023/04/26 14:38:45
 * @Version 1.0
 */
@RestController
@RequestMapping("/msg")
public class msgController {

    @Autowired
    private msgService msgService;

    @Autowired
    private redisUtil redisCache;

    @RequestMapping("/sendMsg")
    public ResultUtil sendMsg(HttpServletRequest request, Map<String,Object> map){
        String UserId=request.getParameter("id");
        String phone=request.getParameter("mobile");
        Map<String,String> msgMap=redisCache.getCacheMap(UserId);
//        String phoneCode=redisCache.getCacheObject("phoneCode");
        String code= RandomUtil.getFourBitRandom();
        map.put("code",code);
        map.put("phone",phone);
        if(StringUtils.isEmpty(msgMap)){
            boolean b=msgService.send(map,phone);
            if (b){
                //如果发送成功，就把验证码存到redis里，设置5分钟有效时间
                redisCache.setCacheMap(UserId,map);
                redisCache.expire(UserId,5,TimeUnit.MINUTES);
                System.out.println("验证码为"+code);
                return ResultUtil.ok(true,200,"发送成功");
            }else {
                System.out.println("短息发送失败");
                return ResultUtil.error(false,500,"发送失败");
            }
        }else if(!phone.equals(msgMap.get("phone"))){
            redisCache.deleteObject(UserId);
            boolean b=msgService.send(map,phone);
            if (b){
                //如果发送成功，就把验证码存到redis里，设置5分钟有效时间
                redisCache.setCacheMap(UserId,map);
                redisCache.expire(UserId,5,TimeUnit.MINUTES);
                System.out.println("验证码为"+code);
                return ResultUtil.ok(true,200,"发送成功");
            }else {
                System.out.println("短息发送失败");
                return ResultUtil.error(false,500,"发送失败");
            }
        }else {
            System.out.println("尊敬的用户请您5分钟后再发送验证码");
            return ResultUtil.error(false,500,"尊敬的用户请您5分钟后再尝试");
        }

    }

}
