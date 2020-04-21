package com.ysu.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public DateUtil() {
		// TODO Auto-generated constructor stub
	}
 public static String formatDate(Date date,String format) {
	 String result="";
	 SimpleDateFormat sdf=new SimpleDateFormat(format);
	 if(date!=null) {
		 result=sdf.format(date);
	 }
	 return result;
 }
 public static Date formatString(String str,String format)throws Exception{
	 SimpleDateFormat sdf=new SimpleDateFormat(format);
			return sdf.parse(str);
 }
 
 public static String getCurrentDateStr() {
	 Date date=new Date();
	 SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
	 return sdf.format(date);
 }
 public static void main(String[] args) {
	System.out.println(DateUtil.getCurrentDateStr());
}
}
