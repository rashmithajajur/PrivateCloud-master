package com.privatecloud.users.dto;

import java.io.Serializable;

public class ResponseObject implements Serializable {

	private static final long serialVersionUID = 2721149645386346602L;
	
	boolean flag;
	String msg;
	
	public ResponseObject(){}
	
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
