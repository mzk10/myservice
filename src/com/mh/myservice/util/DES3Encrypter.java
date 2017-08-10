package com.mh.myservice.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;



/**
 * @Copyright:Copyright (c) 2012-2015
 * @Company:zplay.cn
 * @Title:
 * @Description:
 * @Author:wangdezhen#zplay.cn
 * @Since:2015年3月23日 上午11:52:15
 * @Version:1.0
 */
public class DES3Encrypter {
	private final static String secretKey = "corn.yumimobi.com/yumi00";
	// 向量
	private final static String iv = "20160401";
	// 加解密统一使用的编码方式
	private final static String encoding = "utf-8";

	/**
	 * 3DES加密
	 * 
	 * @param plainText
	 *            普通文本
	 * @return
	 * @throws Exception
	 */
	public static String encode(String plainText) {
		try {
			Key deskey = null;
			DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
			SecretKeyFactory keyfactory = SecretKeyFactory
					.getInstance("desede");
			deskey = keyfactory.generateSecret(spec);

			Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
			IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
			byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
			return Encrypter.BASE64Encoder(encryptData);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 3DES加密
	 * 
	 * @param plainText
	 *            普通文本
	 * @return
	 * @throws Exception
	 */
	public static String encode(String plainText, byte[] b) throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);

		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(b);
		cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
		byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
		return Encrypter.BASE64Encoder(encryptData);
	}

	/**
	 * 
	 * <p>
	 * Title: decode
	 * </p>
	 * <p>
	 * Description:解密
	 * </p>
	 * 
	 * @param encryptText
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-7-4
	 * @return String
	 * @version 1.0
	 */
	public static String decode(String encryptText) throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, deskey, ips);

		byte[] decryptData = cipher.doFinal(Encrypter
				.BASE64Decoder(encryptText));
		return new String(decryptData, encoding);

	}

	/**
	 * 
	 * <p>
	 * Title: decode
	 * </p>
	 * <p>
	 * Description:解密文本
	 * </p>
	 * 
	 * @param encryptText
	 * @param b
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-7-4
	 * @return String
	 * @version 1.0
	 */
	public static String decode(String encryptText, byte[] b) throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(b);
		cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
		byte[] decryptData = cipher.doFinal(Encrypter
				.BASE64Decoder(encryptText));
		return new String(decryptData, encoding);
	}
}
