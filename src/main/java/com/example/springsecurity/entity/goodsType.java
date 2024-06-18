package com.example.springsecurity.entity;

/**
 * 描述
 *
 * @Author zheng
 * @Date 2023/04/28 19:34:01
 * @Version 1.0
 */
public class goodsType {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatusbo() {
        return statusbo;
    }

    public void setStatusbo(String statusbo) {
        this.statusbo = statusbo;
    }

    private int id;
    private String name;
    private String image;
    private String statusbo;
}
