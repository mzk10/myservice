package com.mh.myservice.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtil {

	/**
     * 获取MD5
     * @param text
     * @return
     */
    public static String md5(String text)
    {
        String str = null;
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(text.getBytes());
            byte b[] = md.digest();
            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++)
            {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            str = buf.toString().toUpperCase();
            // buf.toString()); // 32位的加密
            // buf.toString().substring(8,24)); // 16位的加密
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return str;
    }

}
