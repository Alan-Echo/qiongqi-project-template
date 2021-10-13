package com.qiongqi.utils.File;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class FileUtils {

    public static InputStream ReadFile(String filePath){
        //获取容器资源解析器
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        // 获取远程服务器IP和端口
        try {
            //获取所有匹配的文件
            Resource[] resources = resolver.getResources(filePath);
            for(Resource resource : resources) {
                //获得文件流，因为在jar文件中，不能直接通过文件资源路径拿到文件，但是可以在jar包中拿到文件流
                return resource.getInputStream();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void copyInputStreamToFile(InputStream inputStream, File file) throws IOException {
        byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);
        OutputStream outStream = new FileOutputStream(file);
        outStream.write(buffer);
    }


    /**
     * 压缩图片
     * @param multipartFile
     */
    public static InputStream compressImage(MultipartFile multipartFile){
        //*************对不是jpg格式的图片转换成jpg格式***************

        //获取文件名后缀，判断其格式
        int begin = multipartFile.getOriginalFilename().lastIndexOf(".");
        int last = multipartFile.getOriginalFilename().length();
        //获得文件后缀名
        String houzuiFileName = multipartFile.getOriginalFilename().substring(begin, last);

        //创建临时文件
        File tempFile = new File(multipartFile.getOriginalFilename());
        //写入临时File文件 tempFile，将multipartFile转换成File
        try {
            //import org.apache.commons.io.FileUtils;
            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), tempFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //如果文件不是jpg格式,转换其格式
        if (!"jpg".equalsIgnoreCase(houzuiFileName)){
            //ImageUtils是一个工具类，下面给出
            //将png格式转换成jpg，输出到tempFile
            ImageUtils.convert(multipartFile.getOriginalFilename(), "jpg", tempFile.getAbsolutePath());//测试OK
//*************对不是jpg格式的图片转换成jpg格式***************
        }
        try {
            BufferedImage sourceImg = ImageIO.read(new FileInputStream(tempFile));
            //压缩图片
            BufferedImage bufferedImage = Thumbnails.of(tempFile)
                    .size(sourceImg.getWidth(), sourceImg.getHeight())//指定压缩之后的图片尺寸
                    .outputQuality(0.8f)//图片压缩质量
                    .asBufferedImage();
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", os);
            InputStream inputStream = new ByteArrayInputStream(os.toByteArray());
//            System.out.println(inputStream);
            return inputStream;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
//             会在本地产生临时文件，用完后需要删除
            if (tempFile.exists()) {
                tempFile.delete();
            }
        }
        return null;
    }



    /**
     * <p>将文件转成base64 字符串</p>
     * @param file 文件
     * @return
     * @throws Exception
     */
    public static String FileToBase64(File file) throws Exception {
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int)file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return new BASE64Encoder().encode(buffer);
    }
    /**
     * <p>将base64字符解码保存文件</p>
     * @param base64Code
     * @param file
     * @throws Exception
     */
    public static void Base64ToFile(String base64Code,File file) throws Exception {
        byte[] buffer = new BASE64Decoder().decodeBuffer(base64Code);
        FileOutputStream out = new FileOutputStream(file);
        out.write(buffer);
        out.close();
    }

    public static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while( (len=inStream.read(buffer)) != -1 ){
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }

}
