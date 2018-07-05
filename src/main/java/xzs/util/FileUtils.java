package xzs.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by jj on 2018/7/5.
 */
public class FileUtils {
    public static File base64TOFile(String base64){

        File file = new File(" ");
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            OutputStream output = new FileOutputStream(file);

            BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);
            byte byt[] = decoder.decodeBuffer(base64);
            bufferedOutput.write(byt);
        }catch(Exception e){
            e.printStackTrace();
        }
        return file;
    }



    public static void main(String[] args) {
        String path = "D:/1111.jpg";
        File imageBase = new File(path);
        String base64 = ImageUtils.getImageStr(path);
        File file = base64TOFile(base64);
        System.out.println(file.compareTo(imageBase));
    }
}
