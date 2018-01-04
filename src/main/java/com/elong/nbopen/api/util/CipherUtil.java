/**   
 * @(#)CipherUtil.java	2017年4月12日	下午6:19:18	   
 *     
 * Copyrights (C) 2017艺龙旅行网保留所有权利
 */
package com.elong.nbopen.api.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * (类型功能说明描述)
 *
 * <p>
 * 修改历史:											<br>  
 * 修改日期    		修改人员   	版本	 		修改内容<br>  
 * -------------------------------------------------<br>  
 * 2017年4月12日 下午6:19:18   user     1.0    	初始化创建<br>
 * </p> 
 *
 * @author		zhangyang.zhu  
 * @version		1.0  
 * @since		JDK1.7
 */
public class CipherUtil {
	 public static final String CHARSET = "UTF-8";
	 public static final String KEY_DES = "DES";
	 static final String CIPHER_DES = "DES/CBC/PKCS5Padding";
	 
	 /**
	     * DES对称加密
	     *
	     * @param content
	     * @param password 对称加密的key
	     * @return
	     * @throws Exception
	     */
	 public static String desEncrypt(String content,String password) throws Exception{
		 SecretKeySpec secretKey=new SecretKeySpec(password.getBytes(CHARSET), KEY_DES);
		 Cipher cipher=Cipher.getInstance(CIPHER_DES);
		 byte[] byteContent=content.getBytes(CHARSET);
		 IvParameterSpec iv=new IvParameterSpec(password.getBytes(CHARSET));
		 cipher.init(Cipher.ENCRYPT_MODE, secretKey,iv);
		 byte[] result=cipher.doFinal(byteContent);
		 return HexUtil.toHexString(result);
	 }
	 /**
	     * DES对称解密
	     *
	     * @param content
	     * @param password 对称加密的key
	     * @return
	     * @throws Exception
	     */
	    public static String desDecrypt(String content, String password) throws Exception {
	        byte[] bytes = HexUtil.toByteArray(content);
	        Cipher cipher = Cipher.getInstance(CIPHER_DES);
	        DESKeySpec desKeySpec = new DESKeySpec(password.getBytes(CHARSET));
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_DES);
	        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
	        IvParameterSpec iv = new IvParameterSpec(password.getBytes(CHARSET));
	        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
	        byte[] retByte = cipher.doFinal(bytes);
	        return new String(retByte);
	    }
}
