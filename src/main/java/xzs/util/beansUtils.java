package xzs.util;

import org.apache.commons.beanutils.PropertyUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by jj on 2018/6/21.
 */
public class beansUtils {
    public static String bean2String(Objects object) {
        StringBuffer sbf = new StringBuffer();
        try {
            Class<?> clazz = object.getClass();
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object value = field.get(object);
                if(!sbf.toString().equals("")){
                    sbf.append("&");
                }
                sbf.append(fieldName);
                sbf.append("=");
                sbf.append(value);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return sbf.toString();
    }

    public static void main(String[] args) {
        StringBuffer sbf = new StringBuffer();
        String s = sbf.toString();
        System.out.println(s.equals(""));
        System.out.println("2313213212");
    }
}
