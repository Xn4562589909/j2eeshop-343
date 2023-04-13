package com.iweb.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author Yang
 * @date 2023/4/1 22:52
 */
public class OrderCodeUtil {
    /**
     * 生成订单id方法
     */
    public static String getOrderId(int userId){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        int length = 5;
        String userNum = String.valueOf(userId);
        if (userNum.length()>=length){
            userNum = userNum.substring(0,5);
            String str = sdf.format(new Date())+getNumber()+userNum;
            return str;
        } else {
            StringBuilder str = new StringBuilder(sdf.format(new Date())+getNumber());
            for (int i = 0; i <length-userNum.length() ; i++) {
                str.append('0');
            }
            str.append(userNum);
            return str.toString();
        }
    }

    /**
     * 生成3位随机数
     */
    private static String getNumber(){
        String txt = "1234567890";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            sb.append(txt.charAt(new Random().nextInt(txt.length())));
        }
        return sb.toString();
    }
}
