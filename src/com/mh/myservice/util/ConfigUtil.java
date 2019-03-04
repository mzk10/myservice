package com.mh.myservice.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConfigUtil {
	
	public static String getCodeInfo(int code){
		return getValue(code, "/code.val");
	}
	
	public static String getStringConfig(String name){
		return getValue(name, "/config.val");
	}

	public static boolean getBooleanConfig(String name){
        String stringConfig = getStringConfig(name);
        try {
            Boolean aBoolean = Boolean.valueOf(stringConfig);
            return aBoolean;
        }catch (Exception e){
        }
        return false;
    }

	public static int getIntConfig(String name){
        String stringConfig = getStringConfig(name);
        try {
            int i = Integer.parseInt(stringConfig);
            return i;
        }catch (Exception e){
        }
        return -1;
	}

	private static String getValue(int key, String file){
		return getValue(String.valueOf(key), file);
	}
	private static String getValue(String key, String file){
		try {
			ConfigUtil nameValues = new ConfigUtil();
			InputStream is = nameValues.getClass().getResourceAsStream(file);
			InputStreamReader isr = new InputStreamReader(is,"UTF-8");
			BufferedReader br = new BufferedReader(isr);
			StringBuffer sb = new StringBuffer();
			while(br.ready()){
				sb.append(br.readLine());
			}
			String json = sb.toString();
			JSONObject obj = new JSONObject(json);
			String info = obj.getString(key);
			return info;
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static final String DEFAULT_UPLOAD_PATH = "defaultUploadPath";
	
}
