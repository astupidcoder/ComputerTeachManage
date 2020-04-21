package com.ysu.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ysu.dao.StuDao;
import com.ysu.model.StuInfo;
import com.ysu.util.DbUtil;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		String userId=request.getParameter("userId");
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		String tel=request.getParameter("tel");
		StuInfo stu=new StuInfo(Integer.parseInt(userId),userName,password,tel,email);
		StuDao stuDao=new StuDao();
		DbUtil dbUtil=new DbUtil();
		Connection con=null;
				try{
					con=dbUtil.getCon();
				}catch(Exception e) {
					e.printStackTrace();
				}
		try {
			if(stuDao.isExist(con,Integer.parseInt(userId),userName)!=null)
			{
				if(stuDao.isRegistered(con, Integer.parseInt(userId))) {
					out.println("<script language='javaScript'>alert('您已经注册过，请直接登陆!');</script>");
					response.setHeader("refresh","1;url=login.jsp");
				}else {
						if(stuDao.stuUpdate(con, stu)) {
							out.println("<script language='javaScript'>alert('恭喜您，注册成功!');</script>");
							response.setHeader("refresh","1;url=login.jsp");
						}else {
							out.println("<script language='javaScript'>alert('对不起，注册失败!');</script>");
						}
				     }
			}else {
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("success", false);
				HttpSession session=request.getSession();
				session.setAttribute("stu", stu);
				out.println("<script language='javaScript'>alert('对不起，您不是计算机系的学生！无法注册!');</script>");
				response.setHeader("refresh","1;url=register.jsp");
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
