package com.example.demo.common;

public class Result<T> {
	private Integer status;
	private String msg;
	private T data;
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	public Result() {
		super();
	}
	
	public Result(Integer status, String msg) {
		super();
		this.status = status;
		this.msg = msg;
	}
	
	public Result(Integer status, String msg, T data) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "Result [status=" + status + ", msg=" + msg + ", data=" + data + "]";
	}
	
}
