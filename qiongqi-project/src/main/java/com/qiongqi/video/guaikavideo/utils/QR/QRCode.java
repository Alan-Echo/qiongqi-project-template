package com.qiongqi.video.guaikavideo.utils.QR;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.qiongqi.video.guaikavideo.utils.File.FileUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class QRCode {
    /** 定义二维码图片的宽度 */
    private static final int WIDTH = 250;
    /** 定义二维码图片的高度 */
    private static final int HEIGHT = 250;

    /** 定义LOGO图片的宽度 */
    private static final int LOGO_WIDTH = 50;
    /** 定义LOGO图片的高度 */
    private static final int LOGO_HEIGHT = 50;


    public void getQR(String contents, HttpServletResponse response) throws Exception{

        /** 判断二维码中URL */
        if (contents == null || "".equals(contents)){
            contents = "二维码生成失败";
        }



        /** 定义Map集合封装二维码配置信息 */
        Map<EncodeHintType, Object> hints = new HashMap<>();
        /** 设置二维码图片的内容编码 */
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        /** 设置二维码图片的上、下、左、右间隙 */
        hints.put(EncodeHintType.MARGIN, 1);
        /** 设置二维码的纠错级别 */
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        /**
         * 创建二维码字节转换对象
         * 第一个参数：二维码图片中的内容
         * 第二个参数：二维码格式器
         * 第三个参数：生成二维码图片的宽度
         * 第四个参数：生成二维码图片的高度
         * 第五个参数：生成二维码需要配置信息
         *  */
        BitMatrix matrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hints);

        /** 获取二维码图片真正的宽度  */
        int matrix_width = matrix.getWidth();
        /** 获取二维码图片真正的高度  */
        int matrix_height = matrix.getHeight();
        /** 定义一张空白的缓冲流图片 */
        BufferedImage image = new BufferedImage(matrix_width, matrix_height, BufferedImage.TYPE_INT_RGB);
        /** 把二维码字节转换对象 转化 到缓冲流图片上 */
        for (int x = 0; x < matrix_width; x++){
            for (int y = 0; y < matrix_height; y++){
                /** 通过x、y坐标获取一点的颜色 true: 黑色  false: 白色 */
                int rgb = matrix.get(x, y) ? 0x000000 : 0xFFFFFF;
                image.setRGB(x, y, rgb);
            }
        }

        /** 获取公司logo图片 */
//        File file = ResourceUtils.getFile("classpath:static/images/logo.png");  //这种方法在linux下无法工作
//        Resource resource = new ClassPathResource("/static/images/logo.png");
//        System.out.println(resource);
//        File file =  resource.getFile();
        InputStream inputStream = FileUtils.ReadFile("/static/images/logo.png");
        BufferedImage logo = ImageIO.read(inputStream);
//        BufferedImage logo = ImageIO.read(new File(this.getClass().getResource("/static/images/logo.png").getPath()));
        /** 获取缓冲流图片的画笔 */
        Graphics2D g = (Graphics2D)image.getGraphics();
        /** 在二维码图片中间绘制公司logo */
        g.drawImage(logo, (matrix_width - LOGO_WIDTH) / 2,(matrix_height - LOGO_HEIGHT) / 2, LOGO_WIDTH, LOGO_HEIGHT, null);

        /** 设置画笔的颜色 */
        g.setColor(Color.WHITE);
        /** 设置画笔的粗细 */
        g.setStroke(new BasicStroke(5.0f));
        /** 设置消除锯齿 */
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        /** 绘制圆角矩形 */
        g.drawRoundRect((matrix_width - LOGO_WIDTH) / 2, (matrix_height - LOGO_HEIGHT) / 2, LOGO_WIDTH, LOGO_HEIGHT, 10, 10);

        /** 向浏览器输出二维码 */
        //MatrixToImageWriter.writeToStream(matrix, "png", response.getOutputStream());
        ImageIO.write(image, "png", response.getOutputStream());
    }

}
