package com.example.demo.enums;

public enum DESType {
	DES("DES",56,"DES加密方式"),
	DES3("DESede",168,"3DES加密方式");
	
	private String value;
	private int keySize;
	private String text;
	private DESType(String value, int keySize,String text) {
		this.value = value;
		this.keySize = keySize;
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
	public int getKeySize() {
		return keySize;
	}
	public void setKeySize(int keySize) {
		this.keySize = keySize;
	}
	/**
	 * 返回false是合法值
	 * @param value
	 * @return
	 */
	public static boolean isErrorValue(String value) {
		for (DESType hmacType:DESType.values()) {
			if(hmacType.value.equals(value)) {
				return false;
			}
		}
		return true;
	}
	
}
