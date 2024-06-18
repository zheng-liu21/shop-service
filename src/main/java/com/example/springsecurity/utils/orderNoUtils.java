package com.example.springsecurity.utils;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
/**
 * 描述订单编码码生成器，生成32位数字编码，
 * @生成规则 1位单号类型+17位时间戳+14位(用户id加密&随机数)
 *
 * @Author zheng
 * @Date 2023/05/04 18:06:37
 * @Version 1.0
 */
public class orderNoUtils {


    /** 订单类别头 */
    private static final String ORDER_CODE = "O";
    /** 退货类别头 */
    private static final String RETURN_ORDER = "T";
    /** 退款类别头 */
    private static final String REFUND_ORDER = "M";
    /** 未付款重新支付别头 */
    private static final String AGAIN_ORDER = "W";
    /** 随即编码 */
    private static final int[] r = new int[]{7, 9, 6, 2, 8 , 1, 3, 0, 5, 4};
    /** 用户id和随机数总长度 */
    private static final int maxLength = 14;

    /**
     * 更具id进行加密+加随机数组成固定长度编码
     */
    private static String toCode(int id) {
        String idStr = String.valueOf(id);
        StringBuilder idsbs = new StringBuilder();
        for (int i = idStr.length() - 1 ; i >= 0; i--) {
            idsbs.append(r[idStr.charAt(i)-'0']);
        }
        return idsbs.append(getRandom(maxLength - idStr.length())).toString();
    }

    /**
     * 生成时间戳
     */
    private static String getDateTime(){
        DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(new Date());
    }

    /**
     * 生成固定长度随机码
     * @param n    长度
     */
    private static long getRandom(long n) {
        long min = 1,max = 9;
        for (int i = 1; i < n; i++) {
            min *= 10;
            max *= 10;
        }
        long rangeLong = (((long) (new Random().nextDouble() * (max - min)))) + min ;
        return rangeLong;
    }

    /**
     * 生成不带类别标头的编码
     * @param userId
     */
    private static synchronized String getCode(int userId){

        return getDateTime() + toCode(userId);
    }

    /**
     * 生成订单单号编码
     * @param userId
     */
    public static String getOrderCode(int userId){
        return ORDER_CODE + getCode(userId);
    }

    /**
     * 生成退货单号编码
     * @param userId
     */
    public static String getReturnCode(int userId){
        return RETURN_ORDER + getCode(userId);
    }

    /**
     * 生成退款单号编码
     * @param userId
     */
    public static String getRefundCode(int userId){
        return REFUND_ORDER + getCode(userId);
    }

    /**
     * 未付款重新支付
     * @param userId
     */
    public static String getAgainCode(int userId){
        return AGAIN_ORDER + getCode(userId);
    }
}

