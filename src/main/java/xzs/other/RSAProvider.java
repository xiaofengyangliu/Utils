///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package xzs.other;
//
//import java.io.IOException;
//
//
//public class RSAProvider {
//
//    private String version = "";
//    private String cert = "";
//    private String keyEncrypt = "";
//    private String key = "";
//    private String srcEncrypt = "";
//
//    public String getCert() {
//        return cert;
//    }
//
//    public void setCert(String cert) {
//        this.cert = cert;
//    }
//    
//    public RSAProvider() {
//        this.version = "RSA.3DES.MD5";
//    }
//
//    public RSAProvider(String version) {
//        this.version = version;
//    }
//
//    public String getKey() {
//        return key;
//    }
//
//    public void setKey(String key) {
//        this.key = key;
//    }
//
//    public String getKeyEncrypt() {
//        return keyEncrypt;
//    }
//
//    public void setKeyEncrypt(String keyEncrypt) {
//        this.keyEncrypt = keyEncrypt;
//    }
//
//    public String getSrc() {
//        return src;
//    }
//
//    public void setSrc(String src) {
//        this.src = src;
//    }
//
//    public String getSrcEncrypt() {
//        return srcEncrypt;
//    }
//
//    public void setSrcEncrypt(String srcEncrypt) {
//        this.srcEncrypt = srcEncrypt;
//    }
//
//    public String getSrcSign() {
//        return srcSign;
//    }
//
//    public void setSrcSign(String srcSign) {
//        this.srcSign = srcSign;
//    }
//    private String src = "";
//    private String srcSign = "";
//
//    /** ��Կ�����㷨, ���ļ����㷨, ����ǩ���㷨
//     * 
//     * @return  RSA.3DES.MD5, RSA.3DES.MD5withRSA
//     */
//    public String getVersion() {
//        return version;
//    }
//
//    /** �Ա�����ݽ��м���ǩ��
//     *  ���ܱ������ʽ��BASE64(�汾��))��BASE64(ǩ��Կ)|BASE64(RSA(���ļ�����Կ))| BASE64(3DES(����ԭ��))| BASE64(MD5withRSA(����ԭ��))
//     * @param cert base64��Կ�������Ϊ�գ�����ɱ��ļ�����Կ��
//     * @param srcPara ����ԭ��
//     * @param keyPara ��̬��Կ�������Ϊ���Զ�������
//     * @return ���ܱ������ʽ��BASE64(�汾��))��BASE64(ǩ��Կ)|BASE64(RSA(���ļ�����Կ))| BASE64(3DES(����ԭ��))| BASE64(MD5withRSA(����ԭ��))
//     * @throws Exception 
//     */
//    public String sign(String keyPara, String srcPara, String cert) throws Exception {
//        if (Toolkit.isNullOrEmpty(keyPara)) {
//            keyPara = Toolkit.random(24);
//        }
//        this.setKey(keyPara);
//        this.setSrc(srcPara);
//
//        if (!Toolkit.isNullOrEmpty(cert)) { //��ԿΪ�գ���������Կ
//            byte[] keyEnc = RSA.encrypt64(key.getBytes("UTF-8"), cert);
//            this.setKeyEncrypt(Toolkit.base64Encode(keyEnc));
//        }
//
//        byte[] srcEnc = TripleDes.encrypt(key.getBytes("UTF-8"), src.getBytes("UTF-8"));
//        this.setSrcEncrypt(Toolkit.base64Encode(srcEnc));
//
//        if (this.getVersion().equals("RSA.3DES.MD5withRSA")) {
//            byte[] srcSigned = RSA.sign(src.getBytes("UTF-8"), Toolkit.getPropertyFromFile(Toolkit.GDYILIAN_CERT_PFX),
//                    Toolkit.getPropertyFromFile(Toolkit.GDYILIAN_CERT_PFX_PASSWD));
//            this.setSrcSign(Toolkit.base64Encode(srcSigned));
//            byte[] pub_key = RSA.getPublicKey(Toolkit.getPropertyFromFile(Toolkit.GDYILIAN_CERT_PFX), Toolkit.getPropertyFromFile(Toolkit.GDYILIAN_CERT_PFX_PASSWD)).getEncoded();
//            this.setCert(Toolkit.base64Encode(pub_key));
//        } else {
//            MD5 md5 = new MD5();
//            String strSign = md5.getMD5ofByte(src.getBytes("UTF-8"));
//            this.setSrcSign(Toolkit.base64Encode(strSign.getBytes("UTF-8")));
//        }
//
//        String tData = Toolkit.base64Encode(this.getVersion().getBytes("UTF-8"));
//        tData += "|" + this.getCert();
//        tData += "|" + this.getKeyEncrypt();
//        tData += "|" + this.getSrcEncrypt();
//        tData += "|" + this.getSrcSign();
//        return tData;
//    }
//
//    /** �Ա�����ݽ��н�����ǩ
//     *  ���ܱ������ʽ��BASE64(�汾��))��BASE64(ǩ��Կ)|BASE64(RSA(���ļ�����Կ))| BASE64(3DES(����ԭ��))| BASE64(MD5withRSA(����ԭ��))
//     * 
//     * @param sign ���ܱ���
//     * @param cert ��ǩ��Կ���汾Ϊ��RSA.3DES.MD5withRSA��ʱ��ǩʹ�ã���
//     * @param keyPara ������Կ, ���ļ�����ԿΪ��ʱʹ��
//     * @return ����ԭ��
//     * @throws Exception 
//     */
//    public String verify(String keyPara, String sign) throws Exception {
//        String[] values = sign.split("\\|");
//        this.version = new String(Toolkit.base64Decode(values[0]), "UTF-8");
//        this.setCert(values[1]);
//        this.setKeyEncrypt(values[2]);
//        this.setSrcEncrypt(values[3]);
//        this.setSrcSign(values[4]);
//        this.setKey(keyPara);
//
//        byte[] keyEnc = Toolkit.base64Decode(this.getKeyEncrypt());
//        byte[] srcEnc = Toolkit.base64Decode(this.getSrcEncrypt());
//        byte[] srcSigned = Toolkit.base64Decode(this.getSrcSign());
//
//        byte[] keyBt = null;
//
//        if (Toolkit.isNullOrEmpty(keyPara)) {
//            keyBt = this.decryptKey(keyEnc);
//            this.setKey(new String(keyBt, "UTF-8"));
//        } else {
//            keyBt = keyPara.getBytes("UTF-8");
//        }
//
//        byte[] srcBt = TripleDes.decrypt(keyBt, srcEnc);
//
//        this.setSrc(new String(srcBt, "UTF-8"));
//
//        if (this.getVersion().equals("RSA.3DES.MD5withRSA") && !Toolkit.isNullOrEmpty(cert)) {
//            if (!RSA.verify(srcSigned, cert, src)) {
//                throw new Exception("fail to verifySignedData");
//            }
//        } else {
//            MD5 md5 = new MD5();
//            String strSign = md5.getMD5ofByte(srcBt);
//            if (!strSign.equals(new String(srcSigned, "UTF-8"))) {
//                throw new Exception("fail to verifySignedData");
//            }
//        }
//
//        return src;
//    }
//
//    /** ���ܼ�����Կ
//     * 
//     * @param keyEncrypt base64
//     * @return
//     * @throws IOException 
//     */
//    public String decryptKey(String keyEncrypt) throws IOException {
//        byte[] keyBt = RSA.decrypt(Toolkit.base64Decode(keyEncrypt),
//                Toolkit.getPropertyFromFile(Toolkit.GDYILIAN_CERT_PFX),
//                Toolkit.getPropertyFromFile(Toolkit.GDYILIAN_CERT_PFX_PASSWD));
//        return new String(keyBt, "UTF-8");
//    }
//
//    public byte[] decryptKey(byte[] keyEncrypt) throws IOException {
//        byte[] keyBt = RSA.decrypt(keyEncrypt,
//                Toolkit.getPropertyFromFile(Toolkit.GDYILIAN_CERT_PFX),
//                Toolkit.getPropertyFromFile(Toolkit.GDYILIAN_CERT_PFX_PASSWD));
//        return keyBt;
//    }
//
//    public static void main(String[] args) throws Exception {
//        RSAProvider rsa = new RSAProvider();
//        String key = Toolkit.random(24);
//        String signData = rsa.sign(key, "123456����", Toolkit.getPropertyFromFile(Toolkit.GDYILIAN_CERT_PUB_64));
//        System.out.println(signData);
//        String Data = rsa.verify(key, signData);
//        System.out.println(Data);
//    }
//}
