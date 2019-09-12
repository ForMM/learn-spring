package com.example.demo.util;

import java.util.Date;

public class DateUtil {
	private DateUtil() {}
	
	public static final  long ONE_DAY_SECONDS = 86400;

    public static final  String shortFormat = "yyyyMMdd";

    public static final  String longFormat = "yyyyMMddHHmmss";

    public static final String webFormat = "yyyy-MM-dd";

    public static final String timeFormat = "HHmmss";

    public static final String monthFormat = "yyyyMM";

    public static final String chineseDtFormat = "yyyy年MM月dd日";

    public static final String newFormat = "yyyy-MM-dd HH:mm:ss";

    public static final String newFormat2 = "yyyy-MM-dd HH:mm";

    public static final String newFormat3 = "yyyy-MM-dd HH";

    public static final String FULL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    public static long ONE_DAY_MILL_SECONDS = 86400000;
    
    /**
     * 获取当前日期
     * @return
     */
    public static Date getDate() {
    	return new Date();
    }
    
    /**
     * 
     * @param millsecond
     * @return
     */
    public static Date getDate(long millsecond) {
    	return new Date(millsecond);
    }
    
	
}
