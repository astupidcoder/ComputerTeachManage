package com.ysu.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ysu.dao.StuDao;
import com.ysu.model.StuInfo;
import com.ysu.util.DbUtil;

import net.sf.json.JSONObject;

/**
 * 登陆界面，找回密码,判断所输学号和姓名是否存在
 * @author lyj
 *
 */
public class FindPwdServlet extends HttpServlet{

	StuDao stuDao=new StuDao();
	DbUtil dbUtil=new DbUtil()
;	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FindPwdServlet() {
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
		String name=request.getParameter("name");
		JSONObject jsonObject=new JSONObject();
		Connection con=null;
		try {
			con=dbUtil.getCon();
			StuInfo stu=stuDao.isExist(con, Integer.parseInt(userId), name);
			if(stu!=null) {
				if(stu.getPassword()!=null) {
					jsonObject.put("success",true);
					jsonObject.put("email",stu.getEmail());
				}else {
					out.println("<script>alert('您还没有注册，请先注册')</script>");
					response.setHeader("refresh","1;url=login.jsp");
				}
			}else {
				out.println("<script>alert('对不起，您不是计算机的学生！！！请核对您的身份信息！！！')</script>");
				response.setHeader("refresh","0;url=login.jsp");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println(jsonObject);
		out.flush();
		out.close();
	}

}
