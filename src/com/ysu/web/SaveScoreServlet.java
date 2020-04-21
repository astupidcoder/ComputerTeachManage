package com.ysu.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ysu.dao.StuDao;
import com.ysu.util.DbUtil;

import net.sf.json.JSONObject;

public class SaveScoreServlet extends HttpServlet{

	DbUtil dbUtil=new DbUtil();
	StuDao stuDao=new StuDao();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SaveScoreServlet() {
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
		String userId=request.getParameter("userId");
		String score=request.getParameter("score");
		Connection con=null;
		try {
			con=dbUtil.getCon();
			JSONObject result=new JSONObject();
			if(stuDao.scoreSave(con, Integer.parseInt(userId), score)) {
				result.put("sucess",true);
			}else {
				result.put("success",false);
				result.put("errorMsg","成绩录入失败！！！！");
			}
			out.println(result);
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
