//package xzs.other;
//
//import java.io.IOException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.ResourceBundle;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//
//import data.ybsrsa.controller.PlaceOrderController;
//import data.ybsrsa.util.GlobalCodePropertyConfigurer;
//
//public class Toolkit {
//
//	public static final String GDYILIAN_CERT_PFX = "GDYILIAN_CERT_PFX";
//	public static final String GDYILIAN_CERT_PFX_PASSWD = "GDYILIAN_CERT_PFX_PASSWD";
//	public static final String GDYILIAN_CERT_PUB_64 = "GDYILIAN_CERT_PUB_64";
//	public static final String GDYILIAN_CERT_METHOD = "GDYILIAN_CERT_METHOD";
//	
//	public static boolean isNullOrEmpty(Object o){
//		return o == null || "".equals(o.toString().trim());
//	}
//	private static SimpleDateFormat HHmmss = null;
//    public static String HHmmss(Date date) {
//        if (HHmmss == null) {
//        	HHmmss = new SimpleDateFormat("HHmmss");
//        }
//        return HHmmss.format(date);
//    }
//    
//	public static String yyyyMMdd(Date time) {
//		SimpleDateFormat    yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
//		return yyyyMMdd.format(time);
//	}
//    
//	private static SimpleDateFormat MMddHHmmss = null;
//    public static String MMddHHmmss(Date date) {
//        if (MMddHHmmss == null) {
//            MMddHHmmss = new SimpleDateFormat("MMddHHmmss");
//        }
//        return MMddHHmmss.format(date);
//    }
//	private static SimpleDateFormat yyyyMMddHHmmss = null;
//	public static String yyyyMMddHHmmss(Date time) {
//	    if (yyyyMMddHHmmss == null) {
//	        yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
//	    }
//	    return yyyyMMddHHmmss.format(time);
//	}
//	
//	public static String getString(String src) {
//		return (Toolkit.isNullOrEmpty(src) ? "" : (" " + src.trim())).toUpperCase();
//	}
//	
//    public static String padLeft(String input, char c, int length) {
//        String output = input;
//        while (output.length() < length) {
//            output = c + output;
//        }
//        return output;
//    }
//
//    public static String padRight(String input, char c, int length) {
//        String output = input;
//        while (output.length() < length) {
//            output = output + c;
//        }
//        return output;
//    }
//
//    public static String padRight(String input, int length) {
//        return padRight(input, ' ', length);
//    }
//    
//    public static String padLeft(String input, int length) {
//        return padLeft(input, '0', length);
//    }
//	
//    public static String toString(Object o) {
//        if (o == null) {
//            return "";
//        }
//        return o.toString().trim();
//    }
//    
//    public static String bytePadLeft(String input, char c, int length) {
//        String output = input;
//        while (output.getBytes().length < length) {
//            output = c + output;
//        }
//        return output;
//    }
//
//    public static String bytePadRight(String input, char c, int length) {
//        String output = input;
//        while (output.getBytes().length < length) {
//            output = output + c;
//        }
//        return output;
//    }
//    
//    public static byte[] str2Bcd(String str) {
//		return str2Bcd(str,true);
//	}
//	/**
//	 * 
//	 * 10���ƴ�תΪBCD��(�ֽ�����)�����룺0123456789�������00000001 00100011 01000101 01100111 10001001
//	 * ����ı�����0-9 a-f A-F��22���ַ�
//	 * ������󲹡�0���������Ҳ���F��
//	 */
//	public static byte[] str2Bcd(String str,boolean leftAdd0) {
//
//		if(leftAdd0) {
//			while(str.length()%2 != 0) str = "0" + str;
//		} else {
//			while(str.length()%2 != 0) str += "F";
//		}
//
//		byte[] temp = str.getBytes();
//		byte[] result = new byte[temp.length/2];
//
//		for(int i = 0;i < result.length;i++) {
//
//			byte h = byte2OByte(temp[2*i]);
//			byte l = byte2OByte(temp[2*i+1]);
//
//			result[i] = (byte)((h << 4) + l);
//		}
//
//		return result;
//	}
//	
//	private static byte byte2OByte(byte c) {
//
//		if(c >= '0' && c <= '9') {
//			c = (byte)(c - '0');
//		} else if(c >= 'a' && c <= 'z') {
//			c = (byte)(c - 'a' + 0x0A);
//		} else if(c >= 'A' && c <= 'Z') {
//			c = (byte)(c - 'A' + 0x0A);
//		}
//
//		return c;
//	}
//    
//    public static byte[] base64Decode(String data) throws IOException {
//        if(Toolkit.isNullOrEmpty(data))
//            return null;
//        return new sun.misc.BASE64Decoder().decodeBuffer(data);
//    }
//
//    public static String base64Encode(byte[] data) throws IOException {
//        if(data == null)
//            return "";
//        return new sun.misc.BASE64Encoder().encode(data);
//    }
//    
//    public static String random(int len) {
//        String str = "";
//        java.util.Random rander = new java.util.Random(System.currentTimeMillis());
//        for(int i=0; i<len; i++) {
//            str+= HEXCHAR[rander.nextInt(16)];
//        }
//        return str;
//    }
//    
//    private static char[] HEXCHAR = {'0', '1', '2', '3', '4', '5', '6', '7',
//        '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
//    
//    public static String getElementValue(String elemName, Document doc) {
//        String elemValue = "";
//        if (null != doc) {
//            Element elem = null;
//            elem = (Element) doc.getElementsByTagName(elemName).item(0);
//            if (null != elem && null != elem.getFirstChild()) {
//                elemValue = elem.getFirstChild().getNodeValue();
//            }
//        }
//        return elemValue;
//    }
//    
//    public static SimpleDateFormat DateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    public static SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
//
//    /**
//     * @param logLevel      ��־�ȼ������磺InfoLevel.info
//     * @param processName   ��־��ƣ�һ��������ã� this.getClass().getPackage().getName()   ���� this.getClass().getName()����μ� log4j.properties
//     * @param threadName    �߳���ƣ� �����ȡ��Thread.currentThread().getName()
//     * @param eventMessage  ��������ص���Ϣ
//     */
//    public synchronized static void writeLog(LogLevel logLevel, String processName,
//        String threadName, String eventMessage)
//    {
//        System.out.println(processName + " " + threadName + " " + eventMessage);
//
//        Logger logger = LoggerFactory.getLogger(Toolkit.class);
//        if (processName != null)
//            processName = processName.replace(" ", ",");
//        if (threadName != null)
//            threadName = threadName.replace(" ", ",");
//
//        eventMessage = threadName + " " + eventMessage;
//
//        switch (logLevel)
//        {
//            case DEBUG:
//                logger.debug(eventMessage);
//                break;
//            case INFO:
//                logger.info(eventMessage);
//                break;
//            case WARN:
//                logger.warn(eventMessage);
//                break;
//            case EXCEPTION:
//                logger.error(eventMessage);
//                break;
//            case ERROR:
//               // logger.error(eventMessage);
//                break;
//            default:
//                logger.info(eventMessage);
//        }
//    }
//
//    public synchronized static void writeLog(String processName,
//        String threadName, String message, Exception eventMessage)
//    {
//        String exStr = "";
//        if (eventMessage != null)
//        {
//            exStr = "" + eventMessage.getMessage();
//            for (StackTraceElement ste : eventMessage.getStackTrace())
//                exStr = exStr + " " + ste.toString();
//        }
//        writeLog(LogLevel.EXCEPTION, processName, threadName, exStr);
//    }
//
//    public synchronized static void writeLog(String processName, String message,
//        Exception eventMessage)
//    {
//        String exStr = "";
//        if (eventMessage != null)
//        {
//            exStr = "" + eventMessage.getMessage();
//            for (StackTraceElement ste : eventMessage.getStackTrace())
//                exStr = exStr + " " + ste.toString();
//        }
//        writeLog(LogLevel.EXCEPTION, processName, Thread.currentThread().getName(), exStr);
//    }
//
//    public synchronized static void writeLog(LogLevel logLevel, String processName, String eventMessage)
//    {
//        writeLog(logLevel, processName, Thread.currentThread().getName(), eventMessage);
//    }
//
//    public synchronized static void writeLog(String processName, String eventMessage)
//    {
//        writeLog(LogLevel.INFO, processName, Thread.currentThread().getName(), eventMessage);
//    }
//
//    public synchronized static void writeLog(String processName, String threadName, String eventMessage)
//    {
//        writeLog(LogLevel.INFO, processName, threadName, eventMessage);
//    }
//
//    public static Integer toInt(LogLevel level)
//    {
//        if (level == LogLevel.DEBUG)
//            return 0;
//        else if (level == LogLevel.INFO)
//            return 1;
//        else if (level == LogLevel.WARN)
//            return 2;
//        else if (level == LogLevel.EXCEPTION)
//            return 3;
//        else
//            return 4;
//
//
//    }
//
//    public synchronized static String getPropertyFromFile(String filename, String key)
//    {
//        ResourceBundle rb = ResourceBundle.getBundle(filename);
//        return rb.getString(key).trim();
//    }
//
//    public synchronized static String getPropertyFromFile(String key)
//    {
////        ResourceBundle rb = ResourceBundle.getBundle("systemsetting");
////        return rb.getString(key).trim();
//    	 return GlobalCodePropertyConfigurer.getGlobalCode(key);
//    }
//    
//
//	public static Date yyyyMMdd(String date) {
//        try {
//    		if (isNullOrEmpty(date)) {
//                return null;
//            }
//            SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
//			return yyyyMMdd.parse(date);
//		} catch (ParseException e) {
//			return null;
//		}
//	}
//}
