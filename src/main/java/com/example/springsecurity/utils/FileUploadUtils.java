package com.example.springsecurity.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 文件上传工具类
 *
 * @Author zheng
 * @Date 2023/04/24 20:01:48
 * @Version 1.0
 */
public class FileUploadUtils {
    //静态资源路径
    public final static String IMG_PATH_PREFIX = "F:\\HBuilderProjects\\shop-web\\static\\image";

    /**
     * 获取静态资源路径
     * @return
     */
    public static File getImgDirFile() {
        String fileDirPath = new String(IMG_PATH_PREFIX);

        File fileDir = new File(fileDirPath);
        if (!fileDir.exists()) {

            fileDir.mkdirs();
        }
        return fileDir;
    }

    /**
     * 上传图片
     * @param imgFile
     * @return
     */
    public static Map uploadImage(MultipartFile imgFile){

        // 生成唯一文件名
        String uuid = UUID.randomUUID().toString().trim();
        String filename = imgFile.getOriginalFilename();
        int index = filename.indexOf(".");
        String fileNames = uuid + filename;

        // 调用FileUploadUtils工具类将图片存放到服务器上
        File fileDir = getImgDirFile();
        Map<String, Object> map = new HashMap<>();
        try {
            // 构建真实的文件路径
            File newFile = new File(fileDir.getAbsolutePath() + File.separator + fileNames);
            imgFile.transferTo(newFile);
            map.put("fileName","profile/"+fileNames);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //返回图片路径
        return map;
    }

}
