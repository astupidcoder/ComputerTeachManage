package com.ysu.util;

public class StringUtil {

	public StringUtil() {
		// TODO Auto-generated constructor stub
	}
 
	public static boolean isEmpty(String str){
		if(str.trim().equals("")||str==null)
		return true;
		else return false;
		
	}
	
	public static boolean isNotEmpty(String str) {
		if(str.trim().equals("")||str==null)
			return false;
		else
			return true;
	}
}
