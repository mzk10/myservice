package com.mh.myservice.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {

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
    
    public static String loadTextOfFile(File file)
    {
    	if (!file.exists())
		{
			return "";
		}else
		{
			FileInputStream fis = null;
			InputStreamReader isr = null;
			try
			{
				fis = new FileInputStream(file);
				isr = new InputStreamReader(fis);
				char[] buffer = new char[2048];
				StringBuffer sb = new StringBuffer();
				int len = 0;
				while((len=isr.read(buffer))!=-1)
				{
					sb.append(buffer, 0, len);
				}
				String html = sb.toString();
				return html;
			} catch (FileNotFoundException e)
			{
				e.printStackTrace();
				return "";
			} catch (IOException e)
			{
				e.printStackTrace();
				return "";
			}finally
			{
				if (isr!=null)
				{
					try
					{
						isr.close();
					} catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			}
		}
    }
	
}
