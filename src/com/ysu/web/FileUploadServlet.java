package com.ysu.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.ysu.dao.StuDao;
import com.ysu.model.StuInfo;
import com.ysu.util.DateUtil;
import com.ysu.util.DbUtil;

import net.sf.json.JSONObject;
/**
 * 文件上传
 * @author lyj
 *
 */
public class FileUploadServlet extends HttpServlet{

	DbUtil dbUtil=new DbUtil();
	StuDao stuDao=new StuDao();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileUploadServlet() {
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
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		String userId=request.getParameter("userId");
		//String name=request.getParameter("name");
		//中文乱码问题解决
		String name=new String(request.getParameter("name").getBytes("ISO8859-1"),"UTF-8");
		FileItemFactory factory=new DiskFileItemFactory();
		ServletFileUpload upload=new ServletFileUpload(factory);
		try {
			List<FileItem> list=upload.parseRequest(request);
			for(FileItem fileItem:list) {
				try {
					String fileName=DateUtil.getCurrentDateStr();
					String filePath="C:\\Users\\lyj\\eclipse-workspace\\ComputerTeachManage\\WebContent\\files\\";
					File file=new File(filePath+userId+"-"+name+"-"+fileName+"."+fileItem.getName().split("\\.")[1]);
					fileItem.write(file);
					Connection con=dbUtil.getCon();
					if(stuDao.statusChange(con, Integer.parseInt(userId))) {
						JSONObject jsonObject=new JSONObject();
						jsonObject.put("upload", true);
						HttpSession session=request.getSession();
						StuInfo stu=stuDao.getStuById(con, Integer.parseInt(userId));
						session.setAttribute("currentUser",stu);  //更新session中status的值
						out.print(jsonObject);
						out.flush();
						out.close();
					};
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
