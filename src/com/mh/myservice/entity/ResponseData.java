package com.mh.myservice.entity;

import com.mh.myservice.util.NameValues;


public class ResponseData {

	private ResponseData(){}

	public static ResponseData create(int code){
		return create(code, null);
	}

	public static ResponseData create(int code,Object data){
		ResponseData result = new ResponseData();
		result.code = code;
		result.info = NameValues.getCodeInfo(code);
		result.data = data;
		return result;
	}
	
	private int code;
	private String info;
	private Object data;
	
	public int getCode() {
		return code;
	}
	public String getInfo() {
		return info;
	}
	public Object getData() {
		return data;
	}
	
}
