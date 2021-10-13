package com.qiongqi.utils;

import com.qiongqi.utils.JWT.JWTHelp;
import com.qiongqi.utils.response.code.ResponseCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0
                || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0
                || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0
                || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }

// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
            // = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        //或者这样也行,对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
//return ipAddress!=null&&!"".equals(ipAddress)?ipAddress.split(",")[0]:null;
        return ipAddress;
    }

    public static Date toDate(String time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    public static boolean isMobile(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 去掉HTML代码
     *
     * @param news
     * @return
     */
    public static String removeHtml(String news) {
        String s = news.replaceAll("amp;", "").replaceAll("<", "<").replaceAll(">", ">");

        Pattern pattern = Pattern.compile("<(span)?\\sstyle.*?style>|(span)?\\sstyle=.*?>", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(s);
        String str = matcher.replaceAll("");

        Pattern pattern2 = Pattern.compile("(<[^>]+>)", Pattern.DOTALL);
        Matcher matcher2 = pattern2.matcher(str);
        String strhttp = matcher2.replaceAll(" ");

        String regEx = "(((http|https|ftp)(\\s)*((\\:)|：))(\\s)*(//|//)(\\s)*)?"
                + "([\\sa-zA-Z0-9(\\.|．)(\\s)*\\-]+((\\:)|(:)[\\sa-zA-Z0-9(\\.|．)&%\\$\\-]+)*@(\\s)*)?" + "("
                + "(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])"
                + "(\\.|．)(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)"
                + "(\\.|．)(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)"
                + "(\\.|．)(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])"
                + "|([\\sa-zA-Z0-9\\-]+(\\.|．)(\\s)*)*[\\sa-zA-Z0-9\\-]+(\\.|．)(\\s)*[\\sa-zA-Z]*" + ")"
                + "((\\s)*(\\:)|(：)(\\s)*[0-9]+)?" + "(/(\\s)*[^/][\\sa-zA-Z0-9\\.\\,\\?\\'\\\\/\\+&%\\$\\=~_\\-@]*)*";
        Pattern p1 = Pattern.compile(regEx, Pattern.DOTALL);
        Matcher matchhttp = p1.matcher(strhttp);
        String strnew = matchhttp.replaceAll("").replaceAll("(if[\\s]*\\(|else|elseif[\\s]*\\().*?;", " ");

        Pattern patterncomma = Pattern.compile("(&[^;]+;)", Pattern.DOTALL);
        Matcher matchercomma = patterncomma.matcher(strnew);
        String strout = matchercomma.replaceAll(" ");
        String answer = strout.replaceAll("[\\pP‘’“”]", " ").replaceAll("\r", " ").replaceAll("\n", " ")
                .replaceAll("\\s", " ").replaceAll("　", "").replaceAll(" ", "");

        return answer;
    }

    public static Long getUserId(HttpServletRequest request) {
        String token = request.getHeader("X-Token");
        String uid = JWTHelp.getFromToken(token, ResponseCode.ParamterUserIdKey);
        return uid != null ? Long.valueOf(uid) : null;
    }

    public static String encodePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public static boolean isPassword(String raw, String entr) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(raw, entr);
    }


    public static String getTimeStrName(long time){
        String msg = "";
        Integer lockTime = Integer.valueOf(time + "");
        int h = lockTime / 3600;
        if(h>0){
            msg+=h+"小时";
        }
        int ms = lockTime - (3600 * h);
        int m = ms / 60;
        if(m>0){
            msg+=m+"分钟";
        }
        int s = ms - (m * 60);
        if(s>0){
            msg+=s+"秒";
        }
        return msg;
    }


    /**
     * 通过时间和随机数生成订单号
     * @return
     */
    public static String getOrderNoByTime() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate=sdf.format(new Date()); //格式化时间为:20210909121212格式;对应:2021-09-09 12:12:12
        String result="";
        Random random=new Random();
        //在时间字符串后面拼接6个随机数
        for(int i=0;i<3;i++){
            result+=random.nextInt(10)+"-"+random.nextInt(20);
        }
        return newDate+result;
    }

    /**
     * 判断url是否与规则配置:
     * ? 表示单个字符
     * * 表示一层路径内的任意字符串，不可跨层级
     * ** 表示任意层路径
     *
     * @param url     匹配规则
     * @param urlPath 需要匹配的url
     * @return
     */
    public static boolean isMatch(String url, String urlPath) {
        AntPathMatcher matcher = new AntPathMatcher();
        return matcher.match(url, urlPath);
    }

    /**
     * 判断指定url地址是否匹配指定url集合中的任意一个
     *
     * @param urlPath 指定url地址
     * @param urls    需要检查的url集合
     * @return 是否匹配  匹配返回true，不匹配返回false
     */
    public static boolean matches(String urlPath, String[] urls) {
        if (StringUtils.isEmpty(urlPath) || urls.length==0) {
            return false;
        }
        for (String url : urls) {
            if (isMatch(url, urlPath)) {
                return true;
            }
        }
        return false;
    }



}
