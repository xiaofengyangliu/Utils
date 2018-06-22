package xzs.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
	
	public static String sendDate(String urlStr,String sendData, int connectiontimeout, int readtimeout) throws Exception{
		HttpURLConnection urlConn = null;
		OutputStreamWriter dops =null;
		try {
			URL url = new URL(urlStr);
			urlConn = (HttpURLConnection)url.openConnection();
			urlConn.setDoInput(true);
			urlConn.setDoOutput(true);
			urlConn.setRequestMethod("POST");
			urlConn.setUseCaches(false);
			urlConn.setInstanceFollowRedirects(true);
			urlConn.setRequestProperty("Content-Type", "application/json");
			urlConn.setConnectTimeout(connectiontimeout);
			urlConn.setReadTimeout(readtimeout);
			urlConn.connect();
			
			dops = new OutputStreamWriter(urlConn.getOutputStream(),"utf-8");
			dops.write(sendData);
			dops.flush();
			return prinResponseData(urlConn);
		} catch (Exception e) {
			throw e;
		}finally{
			if(dops!=null){
				try {
					dops.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			urlConn.disconnect();
		}
	}
	private static String prinResponseData(HttpURLConnection urlConn){
		BufferedReader reader = null;
		String resultData = null;
		try {
			reader = new BufferedReader(new InputStreamReader(
					urlConn.getInputStream(), "utf-8"));
			String inputLine = null;
			resultData = "";
			while ((inputLine = reader.readLine()) != null) {
				resultData += inputLine + "\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(reader!=null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			urlConn.disconnect();
		}
		return resultData;
	}
}
