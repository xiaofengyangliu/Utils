package xzs.util;

import java.io.FileInputStream;
import java.security.KeyStore;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

public class SSLUtil {
	public static KeyManager[] getKeyManager(String path) throws Exception {
		KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
		// keystore的类型，默认是jks
		keystore.load(new FileInputStream(path), "000000".toCharArray());
		// 创建jkd密钥访问库 123456是keystore密码。
		KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
		kmf.init(keystore, "000000".toCharArray());
		// asdfgh是key密码。
		// 创建管理jks密钥库的x509密钥管理器，用来管理密钥，需要key的密码
		return kmf.getKeyManagers();
	}

	public static TrustManager[] getTrustManager(String path) throws Exception {
		KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType()); // 创建一个keystore来管理密钥库
		keystore.load(new FileInputStream(path), "000000".toCharArray());
		// 创建jkd密钥访问库
		TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
		tmf.init(keystore); // 验证数据，可以不传入key密码
		// 创建TrustManagerFactory,管理授权证书
		return tmf.getTrustManagers();
	}
}
