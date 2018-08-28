package org.spring.springboot.Util;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * describle
 *
 * @author Administrator
 * @date 2018-8-22.
 */
public class FileUtil {
    public static void uploadFile(MultipartFile file, String fileName) throws Exception {
        File targetFile = new File("E:\\uploading");
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        BufferedInputStream    inputStream = new BufferedInputStream(file.getInputStream());
        BufferedOutputStream   outputStream = new BufferedOutputStream(new FileOutputStream( new File(targetFile+File.separator+fileName)));
        byte[] buf= new byte[1024*8];
        int leght=0;
        while ((leght = inputStream.read(buf))!=-1){
            outputStream.write(buf);
        }
        inputStream.close();
        outputStream.close();
    }
}
