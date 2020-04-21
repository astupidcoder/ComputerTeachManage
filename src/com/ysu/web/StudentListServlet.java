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
import com.ysu.util.JsonUtil;
import com.ysu.util.PageBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class StudentListServlet extends HttpServlet{

	StuDao stuDao=new StuDao();
	DbUtil dbUtil=new DbUtil();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StudentListServlet() {
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
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Connection con=null;
		try {
			con=dbUtil.getCon();
			JSONArray jsonArray=JsonUtil.formatRsToJsonArray(stuDao.studentList(con, pageBean));
			int total=stuDao.studentCount(con);
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("rows",jsonArray);
			jsonObject.put("total",total);
			out.println(jsonObject.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
