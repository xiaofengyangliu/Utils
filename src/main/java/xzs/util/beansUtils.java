package xzs.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by jj on 2018/6/21.
 */
public class beansUtils {

    /**
     *
     * @param object
     * @return
     */
    public static String bean2StringByAttribute(Object object) {
        StringBuffer sbf = new StringBuffer();
        try {
            Class<?> clazz = object.getClass();
            for (Field field : clazz.getDeclaredFields()) {
				field.setAccessible(true);
                String fieldName = field.getName();
                Object value = field.get(object);
                if (!sbf.toString().equals("")) {
                    sbf.append("&");
                }
                sbf.append(fieldName);
                sbf.append("=");
                sbf.append(value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sbf.toString();
    }

    public static String bean2StringByMethod(Object object) {
        if(object==null){
           return "";
        }
        StringBuffer sbf = new StringBuffer();
        try {
            Class<?> clazz = object.getClass();
            for (Field field : clazz.getDeclaredFields()) {
                String fieldName = field.getName();
                Method method = getGetMethod(object.getClass(), fieldName);
                if(method==null){
                    continue;
                }
                Object value = method.invoke(object, new Object[0]);
                if(value==null||value.equals("")){
                    continue;
                }
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
    /**
     * java反射bean的get方法
     *
     * @param objectClass
     * @param fieldName
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Method getGetMethod(Class objectClass, String fieldName) {
        StringBuffer sb = new StringBuffer();
        sb.append("get");
        sb.append(fieldName.substring(0, 1).toUpperCase());
        sb.append(fieldName.substring(1));
        try {
            return objectClass.getMethod(sb.toString());
        } catch (Exception e) {
        }
        return null;
    }
    public static void main(String[] args) {
        StringBuffer sbf = new StringBuffer();
        String s = sbf.toString();
        System.out.println(s.equals(""));
        System.out.println("2313213212");
    }
}
