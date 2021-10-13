package com.qiongqi.utils.ImgCode;

import com.qiongqi.utils.response.code.ResponseCode;
import org.apache.commons.lang3.StringUtils;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class VerifyCodeUtil {

    public static final String RANDOMCODEKEY= "verCode";//放到session中的key
    private static  String randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";//随机产生数字与字母组合的字符串
    //    private static  int width = 95;// 图片宽
//    private static  int height = 47;// 图片高
    private static  int lineSize = 40;// 干扰线数量
    private static  int stringNum = 4;// 随机产生字符数量

    private static  Random random = new Random();

    /**
     * 获得字体
     */
    private static  Font getFont(int size) {
        return new Font("Fixedsys", Font.CENTER_BASELINE, size);
    }

    /**
     * 获得颜色
     */
    private static  Color getRandColor() {
        // 产生随机的颜色值，让输出的每个字符的颜色值都将不同。
        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }

    /**
     * 生成随机图片
     */
    public static void getRandcode(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        int font =25;
//       获取字体大小
        String size = request.getParameter("font");
        if(StringUtils.isNotBlank(size)){
            try {
                font = Integer.parseInt(size);
            }catch (Exception e){
                font=25;
            }
        }
        //获取图片宽高
        int width = 95;
        int height = 47;
        String w = request.getParameter("w");
        String h = request.getParameter("h");
        if(StringUtils.isNotBlank(w)){
            try{
                width=Integer.parseInt(w);
            }catch (Exception e){
                width=95;
            }
        }
        if(StringUtils.isNotBlank(h)){
            try{
                height=Integer.parseInt(h);
            }catch (Exception e){
                height=47;
            }
        }
        // BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();// 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        g.fillRect(0, 0, width, height);//图片大小
        g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, font));//字体大小
        g.setColor(getRandColor());//字体颜色
        // 绘制干扰线
        for (int i = 0; i <= lineSize; i++) {
            drowLine(g,width,height);
        }
        // 绘制随机字符
        String randomString = "";
        for (int i = 1; i <= stringNum; i++) {
            randomString = drowString(g, randomString, i,font,width,height);
        }
        //将生成的随机字符串保存到session中
        session.removeAttribute(ResponseCode.VER_CODE);
        session.setAttribute(ResponseCode.VER_CODE, randomString);
        //设置失效时间(秒)
        session.setMaxInactiveInterval(60*3);
        g.dispose();
        try {
            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
            // 将内存中的图片通过流动形式输出到客户端
            ImageIO.write(image, "JPEG", response.getOutputStream());
        } catch (Exception e) {
        }

    }

    /**
     * 绘制字符串
     */
    private  static String drowString(Graphics g, String randomString, int i,int fontSize,int width,int height) {
        g.setFont(getFont(fontSize));
        g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
        String rand = String.valueOf(getRandomString(random.nextInt(randString.length())));
        randomString += rand;
        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(rand, (width/8)*i,height/2+10);
        return randomString;
    }

    /**
     * 绘制干扰线
     */
    private static  void drowLine(Graphics g,int width,int height) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x + xl, y + yl);
    }

    /**
     * 获取随机的字符
     */
    private static  String getRandomString(int num) {
        return String.valueOf(randString.charAt(num));
    }

}
