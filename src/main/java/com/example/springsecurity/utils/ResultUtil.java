package com.example.springsecurity.utils;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class ResultUtil {
    private Boolean success;

    private Integer code;

    private String message;

    private Map<String, Object> data = new HashMap<>();

    /**
     * 默认私有构造器
     */
    private ResultUtil() {
    }

    /**
     * 私有自定义构造器
     *
     * @param success 响应是否成功
     * @param code    响应状态码
     * @param message 响应消息
     */
    private ResultUtil(Boolean success, Integer code, String message) {
        this.setSuccess(success);
        this.setCode(code);
        this.setMessage(message);
    }

    /**
     * 返回一个默认的 成功操作 的结果，默认响应状态码 200
     *
     * @return 成功操作的实例对象
     */
    public static ResultUtil ok() {
        return new ResultUtil(true, HttpStatus.OK.value(), "操作成功");
    }

    /**
     * 返回一个自定义 成功操作 的结果
     *
     * @param success 响应是否成功
     * @param code    响应状态码
     * @param message 响应消息
     * @return 成功操作的实例对象
     */
    public static ResultUtil ok(Boolean success, Integer code, String message) {
        return new ResultUtil(success, code, message);
    }

    /**
     * 返回一个默认的 失败操作 的结果，默认响应状态码为 500
     *
     * @return 失败操作的实例对象
     */
    public static ResultUtil error() {
        return new ResultUtil(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "操作失败");
    }

    /**
     * 返回一个自定义 失败操作 的结果
     *
     * @param success 响应是否成功
     * @param code    响应状态码
     * @param message 相应消息
     * @return 失败操作的实例对象
     */
    public static ResultUtil error(Boolean success, Integer code, String message) {
        return new ResultUtil(success, code, message);
    }

    /**
     * 自定义响应是否成功
     *
     * @param success 响应是否成功
     * @return 当前实例对象
     */
    public ResultUtil success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    /**
     * 自定义响应状态码
     *
     * @param code 响应状态码
     * @return 当前实例对象
     */
    public ResultUtil code(Integer code) {
        this.setCode(code);
        return this;
    }

    /**
     * 自定义响应消息
     *
     * @param message 响应消息
     * @return 当前实例对象
     */
    public ResultUtil message(String message) {
        this.setMessage(message);
        return this;
    }

    /**
     * 自定义响应数据，一次设置一个 map 集合
     *
     * @param map 响应数据
     * @return 当前实例对象
     */
    public ResultUtil data(Map<String, Object> map) {
        this.getData().putAll(map);
        return this;
    }

    /**
     * 通用设置响应数据，一次设置一个 key - value 键值对
     *
     * @param key   键
     * @param value 数据
     * @return 当前实例对象
     */
    public ResultUtil data(String key, Object value) {
        this.getData().put(key, value);
        return this;
    }

    /**
     * 响应是否成功，true 为成功，false 为失败
     */
    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /**
     * 响应状态码， 200 成功，500 系统异常
     */
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * 响应消息
     */
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 响应数据
     */
    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
