package com.example.springsecurity.controller;

import com.example.springsecurity.config.security.SecurityUtils;
import com.example.springsecurity.entity.address;
import com.example.springsecurity.entity.goods;
import com.example.springsecurity.entity.impl.LoginUser;
import com.example.springsecurity.entity.user;
import com.example.springsecurity.service.AddressService;
import com.example.springsecurity.service.GoodsService;
import com.example.springsecurity.service.UserService;
import com.example.springsecurity.utils.CitySelect;
import com.example.springsecurity.utils.ResultUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 描述
 *
 * @Author zheng
 * @Date 2023/04/20 20:43:00
 * @Version 1.0
 */
@RestController
@RequestMapping("/address")
public class addressController {

    @Autowired
    private AddressService addressService;

    /**
     * 获取用户收货地址
     * @return
     */
    @RequestMapping("/getAddressByUser")
    public ResultUtil getAddressByUser() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        user user=loginUser.getUser();
        List<address> addressList=addressService.findCAddressByUserID(user.getId());
        return ResultUtil.ok().data("addressList",addressList);

    }

    /**
     * 获取全部省
     * @param request
     * @return
     */
    @RequestMapping("/getProvince")
    public ResultUtil getProvince(HttpServletRequest request) {
        String countryName=request.getParameter("countryName");
        CitySelect citySelect=CitySelect.getInstance();
        List<String> provinceList=citySelect.getProvinces(countryName);
        return ResultUtil.ok().data("provinceList",provinceList);
    }

    /**
     * 获取省下全部市
     * @param request
     * @return
     */
    @RequestMapping("/getCity")
    public ResultUtil getCity(HttpServletRequest request) {
        String countryName=request.getParameter("countryName");
        String provinceName=request.getParameter("provinceName");
        CitySelect citySelect=CitySelect.getInstance();
        List<String> cityList=citySelect.getCities(countryName,provinceName);
        return ResultUtil.ok().data("cityList",cityList);
    }

    /**
     * 获取市下全部区县
     * @param request
     * @return
     */
    @RequestMapping("/getCounty")
    public ResultUtil getCounty(HttpServletRequest request) {
        String countryName=request.getParameter("countryName");
        String provinceName=request.getParameter("provinceName");
        String city=request.getParameter("city");
        CitySelect citySelect=CitySelect.getInstance();
        List<String> countyList=citySelect.getcounty(countryName,provinceName,city);
        return ResultUtil.ok().data("countyList",countyList);
    }

    /**
     * 更新收货地址
     * @param address
     * @return
     */
    @RequestMapping("/updateAddress")
    public ResultUtil updateAddress(address address) {
        addressService.updateAddress(address);
        return ResultUtil.ok();
    }

    /**
     * 新增收货地址
     * @param address
     * @return
     */
    @RequestMapping("/createAddress")
    public ResultUtil createAddress(address address) {
        addressService.insertAddress(address);
        return ResultUtil.ok();
    }


}
