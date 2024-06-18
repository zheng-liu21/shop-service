package com.example.springsecurity.entity;

/**
 * 描述
 *
 * @Author zheng
 * @Date 2023/04/20 20:14:06
 * @Version 1.0
 */
public class collect {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getGoods() {
        return goods;
    }

    public void setGoods(int goods) {
        this.goods = goods;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //用户id
    private int user;
    //用户名称
    private String username;
    //商品名称
    private int goods;
    //商品名称
    private String title;
}
