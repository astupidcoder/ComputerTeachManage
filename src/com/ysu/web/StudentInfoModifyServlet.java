package com.ysu.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ysu.dao.StuDao;
import com.ysu.model.StuInfo;
import com.ysu.util.DbUtil;

import net.sf.json.JSONObject;

public class StudentInfoModifyServlet extends HttpServlet{

	DbUtil dbUtil=new DbUtil();
	StuDao stuDao=new StuDao();
	
	public StudentInfoModifyServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("response");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		String userId=request.getParameter("userId");
		String tel=request.getParameter("tel");
		String email=request.getParameter("email");
		StuInfo stu=new StuInfo(Integer.parseInt(userId),tel,email);
		Connection con=null;
		try {
			con=dbUtil.getCon();
			JSONObject jsonObject=new JSONObject();
			if(stuDao.stuModify(con, stu)) {
				jsonObject.put("success",true);
				HttpSession session=request.getSession();
				StuInfo stu1=stuDao.getStuById(con, Integer.parseInt(userId));
				session.setAttribute("currentUser",stu1);  //更新session中的值
			}else{
				jsonObject.put("success",false);
			};
			out.println(jsonObject);
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
