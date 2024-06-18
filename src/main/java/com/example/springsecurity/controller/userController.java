package com.example.springsecurity.controller;

import com.example.springsecurity.config.redis.redisUtil;
import com.example.springsecurity.config.security.SecurityUtils;
import com.example.springsecurity.entity.impl.LoginUser;
import com.example.springsecurity.entity.user;
import com.example.springsecurity.service.UserService;
import com.example.springsecurity.service.impl.LoginService;
import com.example.springsecurity.utils.FileUploadUtils;
import com.example.springsecurity.utils.ResultUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class userController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private UserService userservice;
    @Autowired
    private redisUtil redisCache;

    @RequestMapping("/login")
    public ResultUtil login(HttpServletRequest request) {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        Map<String,Object> map=new HashMap<>();
        map = loginService.login(username, password);

        return ResultUtil.ok().data(map);

    }

    @RequestMapping("/currentUser")
    public ResultUtil currentUser(HttpServletRequest request, Map<String,Object> map){
        // 获取当前的用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        user SecurityUser=loginUser.getUser();
        user user=userservice.findUserById(SecurityUser.getId());
        user.setAvatar("http://localhost:8084/"+user.getAvatar());
        map.put("currentUser",user);
        return ResultUtil.ok().data(map);
    }



    @RequestMapping("/getuser")
    public ResultUtil getuser(user user) {
        List<user> userList= userservice.findAlluser(user);
        Map<String,Object> map=new HashMap<>();

        map.put("userList",userList);
        return ResultUtil.ok().data(map);

    }

    @RequestMapping("/updateAvatar")
    public ResultUtil updateAvatar(@RequestParam("file") MultipartFile imgFile) {
        Map map=FileUploadUtils.uploadImage(imgFile);
        LoginUser loginUser = SecurityUtils.getLoginUser();
        user user=loginUser.getUser();
        String fileName=map.get("fileName").toString();
        user.setAvatar(map.get("fileName").toString());
        userservice.updateUser(user);
        return ResultUtil.ok();
    }

    @RequestMapping("/updateUser")
    public ResultUtil updateUser(user user) {
        userservice.updateUser(user);
        return ResultUtil.ok();
    }

    /**
     * 验证码是否正确
     * @param request
     * @return
     */
    @RequestMapping("/changeMobile")
    public ResultUtil changeMobile(HttpServletRequest request, Map<String,Object> map) {
        String id=request.getParameter("id");
        String mobile=request.getParameter("mobile");
        String code=request.getParameter("code");
        Map<String,String> msgMap=redisCache.getCacheMap(id);
        if(code.equals(msgMap.get("code"))  && mobile.equals(msgMap.get("phone"))){
            return ResultUtil.ok(true,200,"验证通过");
        }else {
            return ResultUtil.error(false,500,"手机号或验证码错误！");
        }

    }

    /**
     * 确认更换手机
     * @param request
     * @return
     */
    @RequestMapping("/changeSubmit")
    public ResultUtil changeSubmit(HttpServletRequest request, user user) {
        String id=request.getParameter("id");
        String mobile=request.getParameter("mobile");
        String code=request.getParameter("code");
        Map<String,Object> msgMap=redisCache.getCacheMap(id);
        if(code.equals(msgMap.get("code"))&&mobile.equals(msgMap.get("phone"))){
            user.setId(Integer.parseInt(id));
            user.setMobile(mobile);
            userservice.updateUser(user);
            return ResultUtil.ok(true,200,"验证通过");
        }else {
            return ResultUtil.error(false,500,"手机号或验证码错误！");
        }

    }


    /**
     * 修改密码保存
     * @param request
     * @return
     */
    @RequestMapping("/savePwd")
    public ResultUtil savePwd(HttpServletRequest request, user user) {
        String id=request.getParameter("id");
        String mobile=request.getParameter("mobile");
        String password=request.getParameter("password");
        String password_confirmation=request.getParameter("password_confirmation");
        String code=request.getParameter("code");
        Map<String,Object> msgMap=redisCache.getCacheMap(id);
        if(code.equals(msgMap.get("code"))&&mobile.equals(msgMap.get("phone"))&&password.equals(password_confirmation)){
            user.setId(Integer.parseInt(id));
            SecurityUtils.encryptPassword(password);
            user.setPassword("{bcrypt}"+SecurityUtils.encryptPassword(password));
            userservice.updateUser(user);
            return ResultUtil.ok(true,200,"验证通过");
        }else if(!password.equals(password_confirmation)){

            return ResultUtil.error(false,500,"两次密码输入不一致！");
        }else {
            return ResultUtil.error(false,500,"手机号或验证码错误！");
        }

    }


}



