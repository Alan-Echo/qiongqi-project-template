package com.qiongqi.video.guaikavideo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @description: 工具类
 * @author: ※狗尾巴草
 * @date: 2021-01-18 22:26
 **/
public class Utils {


    /** *
     * @description:  设置睡眠时间
     * @param t
     * @return: void
     * @author: 小谭
     * @time: 2019/12/26 23:18
     */
    public static void sleep(int t){
        try {
            TimeUnit.SECONDS.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * @description:  签到过期时间
     * @param
     * @return:
     * @author: 小谭
     * @time: 2021-01-19 19:34
     */
    public static long getClockExpreTime(){
        //两天后 零点时间
        Calendar calendar2 = Calendar.getInstance();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        calendar2.add(Calendar.DATE, 2);
        String days_after = sdf2.format(calendar2.getTime());
        String[] afters = days_after.split("-");
        String end = afters[0]+"-"+afters[1]+"-"+afters[2]+" 00:00:01";
        long endStamp = dateToStamp(end);
        //当前时间
        long startStamp = System.currentTimeMillis();
        return  (endStamp - startStamp) / 1000;
    }


    /**
     * 日期格式字符串转换成时间戳
     * @param date_str 字符串日期
     * @return
     */
    public static long dateToStamp(String date_str)  {
        Date date = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = simpleDateFormat.parse(date_str);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * @description:  判断是否是今天
     * @param timestr 时间字符串
     * @return:
     * @author: 小谭
     * @time: 2021-01-19 21:09
     */
    public static boolean isToday(String timestr) {
        String[] split = timestr.split("-");
        String[] Nowstr = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).split("-");
        if(split[0].equals(Nowstr[0]) && split[1].equals(Nowstr[1])  && split[2].equals(Nowstr[2]) ){
            return true;
        }
        return false;
    }

    public static String getNowStr(String format) {
       return new SimpleDateFormat(format).format(new Date());
    }
}
