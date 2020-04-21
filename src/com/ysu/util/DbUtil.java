package com.ysu.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {
	private String dbUrl="jdbc:mysql://localhost:3306/db_computermanage?useSSL=false&serverTimezone=GMT";
	private String dbUserName="root";
	private String dbPassword="000";
	private String jdbcName="com.mysql.cj.jdbc.Driver";
	public DbUtil() {
		// TODO Auto-generated constructor stub
	}
  public Connection getCon()throws Exception{
	  Class.forName(jdbcName);
	  Connection con=DriverManager.getConnection(dbUrl, dbUserName,dbPassword);
	  return con;
  }
  
  public void closeCon(Connection con)throws Exception{
	  if(con!=null) {
		  con.close();
	  }
  }
  public static void main(String[] args) {
	  DbUtil dbUtil=new DbUtil();
	  try {
		dbUtil.getCon();
		System.out.println("哈啊哈成功了");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
  }
}
