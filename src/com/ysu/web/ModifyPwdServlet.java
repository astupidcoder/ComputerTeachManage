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

public class ModifyPwdServlet extends HttpServlet{
DbUtil dbUtil=new DbUtil();
StuDao userDao=new StuDao();
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ModifyPwdServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		int userId=Integer.parseInt(request.getParameter("userId"));
        String password=request.getParameter("p1");
        Connection con=null;
		try {
			con=dbUtil.getCon();
			JSONObject jsonObject=new JSONObject();
			 boolean result=userDao.pwdUpdate(con, password, userId);		
			if(result) {
				jsonObject.put("success", true);
			}else {
				jsonObject.put("success", true);
			}
			out.println(jsonObject);
			out.flush();
			out.close();
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
}
	}

