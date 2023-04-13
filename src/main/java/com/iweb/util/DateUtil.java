package com.iweb.util;

// 日期类型转换
// Date转成 Timestamp
// mysql中只支持datetime类型的字段
//jdbc只能通过Timestmap类去获取
import java.sql.Timestamp;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * @author Yang
 * @date 2023/4/1 17:52
 */
public class DateUtil {
    public static String getTime(Date date){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    public static Timestamp d2t(Date d){
        if(d==null){
            return null;
        }
        return new Timestamp(d.getTime());
    }
    public static Date t2d(Timestamp t){
        if(t==null){
            return null;
        }
        return new Date(t.getTime());
    }
}
