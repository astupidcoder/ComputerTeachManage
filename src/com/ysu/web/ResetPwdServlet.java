package com.ysu.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/**
 * 对验证码进行验证，找回密码
 * @author lyj
 *
 */
public class ResetPwdServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResetPwdServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		String num=request.getParameter("num");
		String verify=request.getParameter("verify");
		JSONObject jsonObject=new JSONObject();
		if(num.equals(verify)) {
			jsonObject.put("success",true);
		}else {
			jsonObject.put("success",false);
		}
		out.println(jsonObject);
		out.flush();
		out.close();
		System.out.println("num:"+num);
		System.out.println("verify:"+verify);
	}

}
