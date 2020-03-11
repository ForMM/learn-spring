package com.example.demo.common;

import com.example.demo.enums.ResultCode;
import lombok.Data;

@Data
public class Result<T> {
	private Integer code;
	private String msg;
	private T data;

	public Result() {
	}

	public Result(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Result(ResultCode resultCode, T data){
		this.code=resultCode.getCode();
		this.msg =resultCode.getMsg();
		this.data = data;
	}

	public static Result success(){
		Result result = new Result();
		result.setCode(ResultCode.SUCCESS.getCode());
		result.setMsg(ResultCode.SUCCESS.getMsg());
		return result;
	}

	public static Result fail(){
		Result result = new Result();
		result.setCode(ResultCode.FAIL.getCode());
		result.setMsg(ResultCode.FAIL.getMsg());
		return result;
	}

}
