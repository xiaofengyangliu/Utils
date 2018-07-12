package xzs.util;



import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import data.command.spring.global.code.GlobalCodePropertyConfigurer;
import data.command.util.ReturnCodeUtil;
public class HttpsUtil {
	private static final Logger logger = LoggerFactory.getLogger(HttpsUtil.class);
	private static class TrustAnyHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}
	public static String Https(String keystorePath, String p12Path, String keyPassword, String sendUrl, String data,String rquestsn,int connecttime,int readtime){
		String str_return = ReturnCodeUtil.SYSTEM_OTHER_ERROR;
		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			try{
				sc.init(SSLUtil.getKeyManager(p12Path), SSLUtil.getTrustManager(keystorePath), new SecureRandom());
			}catch(KeyManagementException e){
				logger.error("{}|{}", rquestsn,e.getMessage());
			} catch (KeyStoreException e) {
				logger.error("{}|{}", rquestsn,e.getMessage());
			} catch (CertificateException e) {
				logger.error("e{}|{}",rquestsn, e.getMessage());
			} catch (UnrecoverableKeyException e) {
				logger.error("{}|{}",rquestsn, e.getMessage());
			} catch(Exception e){
				logger.error("{}|{}",rquestsn, e.getMessage());
			}
			HttpsURLConnection conn = (HttpsURLConnection) (new URL(sendUrl)).openConnection();
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setConnectTimeout(connecttime);
			conn.setReadTimeout(readtime); 
			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			conn.setRequestProperty("Content-Length", String.valueOf(data.getBytes().length));
			conn.setRequestProperty("Content-Type", "text/xml;charset=utf-8");
			conn.getOutputStream().write(data.getBytes("utf-8"));
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "GBK"));
			String line;
			String retinfo="";
			while ((line = in.readLine()) != null) {
				retinfo = retinfo + line;
			}
			str_return=retinfo;
		}catch (ConnectException e) {
			logger.error("{}|{}", rquestsn,e.getMessage());
			str_return = ReturnCodeUtil.SYSTEM_TRANS_TIMEOUT;
		} catch (SocketTimeoutException e) {
			logger.error("{}|{}", rquestsn,e.getMessage());
			str_return = ReturnCodeUtil.SYSTEM_TRANS_TIMEOUT;
		} catch (IOException e) {
			logger.error("{}|{}", rquestsn,e.getMessage());
			str_return = ReturnCodeUtil.SYSTEM_TRANS_TIMEOUT;
		} catch (NoSuchAlgorithmException e) {
			logger.error("{}|{}", rquestsn,e.getMessage());
		}
		return str_return;
	}
	public static String sendHttpsConnection(String keystorePath, String p12Path, String keyPassword, String sendUrl, String reqData,String rquestsn,int connecttime,int readtime) {
		HttpClient httpClient = new DefaultHttpClient();
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(readtime).setConnectTimeout(connecttime).setConnectionRequestTimeout(readtime).build();
 		String respData = "";
		try {
			SSLContext sslcontext = SSLContext.getInstance("TLS");
			sslcontext.init(SSLUtil.getKeyManager(p12Path), SSLUtil.getTrustManager(keystorePath),new SecureRandom());

			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" },
					null, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
			HttpPost httpPost = new HttpPost(sendUrl);
			httpPost.setHeader("Content-Type", "application/xml;charset=GBK");
			StringEntity reqEntity = new StringEntity(reqData);
			reqEntity.setContentType("application/x-www-form-urlencoded");
			httpPost.setConfig(requestConfig);
			httpPost.setEntity(new StringEntity(reqData == null ? "" : reqData, "GBK"));
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (entity != null) respData = EntityUtils.toString(entity, "GBK");
		} catch (ConnectTimeoutException cte) {
			cte.printStackTrace();
		} catch (SocketTimeoutException ste) {
			ste.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return respData;
	}
}
