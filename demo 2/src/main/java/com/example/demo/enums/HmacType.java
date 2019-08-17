package com.example.demo.enums;

public enum HmacType {
	HMACMD5("HmacMD5","MD5加密方式"),
	HMACSHA1("HmacSHA1","SHA1加密方式"),
	HMACSHA256("HmacSHA256","SHA256加密方式");
	
	private String value;
	private String text;
	private HmacType(String value, String text) {
		this.value = value;
		this.text = text;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * 返回false是合法值
	 * @param value
	 * @return
	 */
	public static boolean isErrorValue(String value) {
		for (HmacType hmacType:HmacType.values()) {
			if(hmacType.value.equals(value)) {
				return false;
			}
		}
		return true;
	}
	
}
