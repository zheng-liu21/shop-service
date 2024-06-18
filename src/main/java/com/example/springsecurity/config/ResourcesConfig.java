package com.example.springsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourcesConfig implements WebMvcConfigurer {
    /*虚拟路径映射本地路径
     *addResourceHandler:访问映射路径
     *addResourceLocations:资源绝对路径
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/profile/**").addResourceLocations("file:F:/HBuilderProjects/shop-web/static/image/");
    }
}
