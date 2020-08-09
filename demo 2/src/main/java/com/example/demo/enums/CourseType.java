package com.example.demo.enums;

public enum CourseType {
	AA("1","核心课程"),
	BB("2","PBL课程"),
	CC("3","精品课程");

	private String value;
	private String text;
	private CourseType(String value, String text) {
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
		for (CourseType hmacType: CourseType.values()) {
			if(hmacType.value.equals(value)) {
				return false;
			}
		}
		return true;
	}

	public static String getTypeValue(String type) {
		for (CourseType hmacType: CourseType.values()) {
			if(hmacType.getValue().equals(type)) {
				return hmacType.getText();
			}
		}
		return null;
	}
	
}
