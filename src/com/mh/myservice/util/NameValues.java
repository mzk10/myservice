package com.mh.myservice.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONException;
import org.json.JSONObject;

public class NameValues {
	
	public static String getCodeInfo(int code){
		return getValue(code, "/code.val");
	}
	
	public static String getConfig(String name){
		return getValue(name, "/config.val");
	}
	
	private static String getValue(int key, String file){
		return getValue(String.valueOf(key), file);
	}
	private static String getValue(String key, String file){
		try {
			NameValues nameValues = new NameValues();
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
