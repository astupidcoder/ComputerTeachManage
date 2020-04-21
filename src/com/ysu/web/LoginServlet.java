package com.ysu.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ysu.dao.StuDao;
import com.ysu.dao.TeacherDao;
import com.ysu.model.StuInfo;
import com.ysu.model.TeaInfo;
import com.ysu.util.DbUtil;
import com.ysu.util.StringUtil;

public class LoginServlet extends HttpServlet{

	
	public LoginServlet() {
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
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		String userId=request.getParameter("userId");
		String password=request.getParameter("password");
		String career=request.getParameter("career");
		String hd=request.getParameter("hd");
		if(StringUtil.isEmpty(userId)||StringUtil.isEmpty(password)) {
			StuInfo stu=new StuInfo(Integer.parseInt(userId),password);
			request.setAttribute("user", stu);
			request.setAttribute("error", "用户名或密码不能为空");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		if(hd.equals("0")) {
			out.println("<script>alert('滑块验证失败！！');</script>");
             response.setHeader("refresh","0;url=login.jsp");
			return;
		}
		if(hd.equals("")) {
			out.println("<script>alert('请先完成滑块验证！！');</script>");
            response.setHeader("refresh","0;url=login.jsp");
			return;
		}
		if(career==null) {
			StuInfo stu=new StuInfo(Integer.parseInt(userId),password);
			request.setAttribute("user", stu);
			request.setAttribute("error","请选择职业");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		DbUtil dbUtil=new DbUtil();
		Connection con=null;
		
		if(career.equals("学生")) {
		StuInfo stu=new StuInfo(Integer.parseInt(userId),password);
		StuDao stuDao=new StuDao();
		
		try {
			con=dbUtil.getCon();
			StuInfo currentStu=null;
			currentStu=stuDao.stuLogin(con, stu);
			if(currentStu!=null) {
				HttpSession session=request.getSession();
				session.setAttribute("currentUser", currentStu);
				
				Cookie cookie=new Cookie("user","student"+"-"+userId+"-"+password);
				cookie.setMaxAge(60*60*24*30);
				response.addCookie(cookie);
				
				response.sendRedirect("mainStu.jsp");
				
			}
			else {
				HttpSession session=request.getSession();
				session.setAttribute("user",stu);
		       request.setAttribute("error", "用户名或密码错误");
		       request.getRequestDispatcher("login.jsp").forward(request, response);
			   }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		                    }finally {
		                    	try {
									dbUtil.closeCon(con);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
		                    }
	}else {
		TeaInfo tea=new TeaInfo(Integer.parseInt(userId),password);
		try {
			con=dbUtil.getCon();
			TeaInfo currentTea=null;
			TeacherDao teacherDao=new TeacherDao();
			currentTea=teacherDao.teaLogin(con, tea);
			if(currentTea!=null) {
				HttpSession session=request.getSession();
				session.setAttribute("currentUser", currentTea);
				
				Cookie cookie=new Cookie("user","teacher"+"-"+userId+"-"+password);
				cookie.setMaxAge(60*60*24);
				response.addCookie(cookie);
				
				response.sendRedirect("mainTea.jsp");
			}else {
				
				request.setAttribute("user",tea);
				request.setAttribute("error","用户名或密码错误");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			    return;
			}
		}catch(Exception e) {
			e.printStackTrace();
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
	
	


}
