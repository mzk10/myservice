package com.mh.myservice.util;

import java.io.UnsupportedEncodingException;


public class DES3Decoder
{
    
    public static String decode(String code)
    {
        try
		{
        	code = DES3Encrypter.decode(code);
        	return code;
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
			return null;
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
    }
    
    
}
