package com.qiongqi.video.guaikavideo.utils.File;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

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
}
