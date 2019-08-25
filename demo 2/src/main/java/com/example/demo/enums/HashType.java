package com.example.demo.enums;

public enum HashType {
	MD5("MD5","MD5加密方式"),
	SHA1("SHA1","SHA1加密方式"),
	SHA256("SHA-256","SHA256加密方式");
	
	private String value;
	private String text;
	private HashType(String value, String text) {
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
		for (HashType hmacType:HashType.values()) {
			if(hmacType.value.equals(value)) {
				return false;
			}
		}
		return true;
	}
	
}
