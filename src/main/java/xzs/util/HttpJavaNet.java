package xzs.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HttpJavaNet {
	
	public static String sendJson(String jsonStr,String urlPath){
		HttpURLConnection con = null;
		OutputStream out = null;
		InputStream ins = null;
		try {
			URL url = new URL(urlPath);
			con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setDoOutput(true);
			con.connect();
			out = con.getOutputStream();
			out.write(jsonStr.getBytes());
			ins = con.getInputStream();
			StringBuffer sbf = new StringBuffer();
			byte[] data = new byte[1024];
			
			while(ins.read()!=-1){
				ins.read(data);
				sbf.append(new String(data));
			}
			return sbf.toString();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(con!=null){
					con.disconnect();
				}
				if(out!=null){
					out.close();
				}
				if(ins!=null){
					ins.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "";
	}
	public static void main(String[] args) {
		String url = "http://test.ifml.me:7001/service/bank/check3.do";
		String jsonStr = "{\"appId\":\"00002\",\"sign\":\"E03573131F5C0B76FBD5408280002132\",\"v\":\"1\",\"data\":{\"bizNo\":\"xxxxx000001\",\"idNo\":\"430725198505080031\",\"realName\":\"陈小明\",\"cardNo\":\"6230520080005048674\"}}";
		String result = sendJson(jsonStr, url);
		System.out.println(result);
	}
} 
