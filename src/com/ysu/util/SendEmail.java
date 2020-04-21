package com.ysu.util;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;
public class SendEmail {

	public SendEmail() {

// TODO Auto-generated constructor stub
	}
	private String to;
	int num;
	String from = "1945304983@qq.com";
	String host = "smtp.qq.com";  //QQ 邮件服务器
    public void setTo(String to) {
    	this.to=to;
    }
    public int getNum() {
    	return num;
    }
    public int createRandom() {
    	Random ran=new Random();
    	num=ran.nextInt(10000);
    	return num;
    }
    public boolean send() throws Exception{
    	// 获取系统属性
	      Properties properties = System.getProperties();
	 
	      // 设置邮件服务器
	      properties.setProperty("mail.smtp.host", host);
	 
	      properties.put("mail.smtp.auth", "true");
	        MailSSLSocketFactory sf = new MailSSLSocketFactory();
	        sf.setTrustAllHosts(true);
	        properties.put("mail.smtp.ssl.enable", "true");
	        properties.put("mail.smtp.ssl.socketFactory", sf);
	      // 获取默认session对象
	      Session session = Session.getInstance(properties,new Authenticator(){
	        public PasswordAuthentication getPasswordAuthentication()
	        {
	         return new PasswordAuthentication("1945304983@qq.com", "cnqfuccumcdxebbd"); //发件人邮件用户名、密码
	        }
	       });
	      
	      
	      try{
		         // 创建默认的 MimeMessage 对象
		         MimeMessage message = new MimeMessage(session);
		 
		         // Set From: 头部头字段
		         message.setFrom(new InternetAddress(from));
		 
		         // Set To: 头部头字段
		         message.addRecipient(Message.RecipientType.TO,
		                                  new InternetAddress(to));
		 
		         // Set Subject: 头部头字段
		         message.setSubject("邮箱验证!来自课程设计教学管理系统");
		 
		         // 设置消息体
		         message.setText("本次您的验证码为:"+Integer.toString(createRandom()));
		 
		         // 发送消息
		         Transport.send(message);
		         System.out.println("Sent message successfully....from 课程设计管理系统");
		         return true;
		      }catch (MessagingException mex) {
		         mex.printStackTrace();
		         return false;
		      }
		   }
    
	// 需要用户名密码邮件发送实例
	//文件名 SendEmail2.java
	//本实例以QQ邮箱为例，你需要在qq后台设置
 
	
	   public static void main(String [] args) throws Exception
	   {
	      /*// 收件人电子邮箱
	      String to = "15028562592@163.com";
	 
	      // 发件人电子邮箱
	      String from = "1945304983@qq.com";
	 
	      // 指定发送邮件的主机为 smtp.qq.com
	      String host = "smtp.qq.com";  //QQ 邮件服务器
	 
	      // 获取系统属性
	      Properties properties = System.getProperties();
	 
	      // 设置邮件服务器
	      properties.setProperty("mail.smtp.host", host);
	 
	      properties.put("mail.smtp.auth", "true");
	      // 获取默认session对象
	      Session session = Session.getInstance(properties,new Authenticator(){
	        public PasswordAuthentication getPasswordAuthentication()
	        {
	         return new PasswordAuthentication("1945304983@qq.com", "qamobpliswanegcf"); //发件人邮件用户名、密码
	        }
	       });
	 
	      try{
	         // 创建默认的 MimeMessage 对象
	         MimeMessage message = new MimeMessage(session);
	 
	         // Set From: 头部头字段
	         message.setFrom(new InternetAddress(from));
	 
	         // Set To: 头部头字段
	         message.addRecipient(Message.RecipientType.TO,
	                                  new InternetAddress(to));
	 
	         // Set Subject: 头部头字段
	         message.setSubject("邮箱验证!来自课程设计教学管理系统");
	 
	         // 设置消息体
	         message.setText("验证码:15235");
	 
	         // 发送消息
	         Transport.send(message);
	         System.out.println("Sent message successfully....from 课程设计管理系统");
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }*/
		   SendEmail s=new SendEmail();
		   s.setTo("15028562592@163.com");
		   if(s.send())
			   System.out.println("成功");;
	   }
	}


