package com.qiongqi.video.guaikavideo.utils.ImgCode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;

/**
 * @description: 说明
 * @author: ※狗尾巴草
 * @date: 2020-08-20 23:24
 **/
public class CaptchaUtil {


    /**
     * 随机产生数字与字母组合的字符串
     */
    private static final String randString = "0123456789abcdefghijklmnopqrstuvwxyz";

    /**
     * 图片宽
     */
    private static int width = 95;

    /**
     * 图片高
     */
    private static int height = 25;

    /**
     * 干扰线数量
     */
    private static int lineSize = 40;

    /**
     * 随机产生字符数量
     */
    private static int stringNum = 4;

    private static Random random = new Random();

    /**
     * 获得字体
     */
    private static Font getFont() {
        return new Font("Fixedsys", Font.CENTER_BASELINE, 22);
    }

    /**
     * 获得颜色
     */
    private static Color getRandColor(int fc, int bc) {
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }

    public static CaptchaDto getRandCode() {
        CaptchaDto instance = CaptchaDto.getInstance();
        // BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        //产生Image对象的Graphics对象,该对象可以在图像上进行各种绘制操作
        Graphics2D g = image.createGraphics();
        //图片大小
        g.fillRect(0, 0, width, height);
        //字体大小
        g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 22));
        //字体颜色
        g.setColor(getRandColor(110, 133));
        // 绘制干扰线
        for (int i = 0; i <= lineSize; i++) {
            drowLine(g);
        }
        // 绘制随机字符
        String randomString = "";
        for (int i = 1; i <= stringNum; i++) {
            randomString = drowString(g, randomString, i);
            instance.setValue(randomString);
        }
        g.dispose();
        ByteArrayOutputStream bos = null;
        try {
            // 将内存中的图片通过流动形式输出到客户端
            bos = new ByteArrayOutputStream();
            ImageIO.write(image, "JPEG", bos);
            String imgSrc = Base64.getEncoder().encodeToString(bos.toByteArray());
            instance.setBase64Str(imgSrc);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return instance;
    }

    /**
     * 绘制字符串
     */
    private static String drowString(Graphics g, String randomString, int i) {
        g.setFont(getFont());
        g.setColor(new Color(random.nextInt(101), random.nextInt(111), random.nextInt(121)));
        String rand = getRandomString(random.nextInt(randString.length()));
        randomString += rand;
        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(rand, 13 * i, 16);
        return randomString;
    }

    /**
     * 绘制干扰线
     */
    private static void drowLine(Graphics g) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x + xl, y + yl);
    }

    /**
     * 获取随机的字符
     */
    public static String getRandomString(int num) {
        return String.valueOf(randString.charAt(num));
    }

}
