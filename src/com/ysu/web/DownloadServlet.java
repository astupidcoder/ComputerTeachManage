package com.ysu.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ysu.dao.StuDao;
import com.ysu.util.DbUtil;

/**
 * Servlet implementation class DownloadServlet
 */
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       DbUtil dbUtil=new DbUtil();
       StuDao stuDao=new StuDao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadServlet() {
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
		//request.setCharacterEncoding("utf-8");
    		String Ids=request.getParameter("Ids");
    		String str[]=Ids.split(",");
    		if(str.length==1) {
    			Connection con=null;
				try {
					con = dbUtil.getCon();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		try {       //先判断该学生是否已提交作业
				if(stuDao.isSubmited(con, Integer.parseInt(Ids))) {
				String dirpath="C:\\Users\\lyj\\eclipse-workspace\\ComputerTeachManage\\WebContent\\files\\";
				File f1=new File(dirpath);   //查找对应学号的文件
				String s[]=f1.list();
				String filedownload="";
				String filedisplay="";
				for(int i=0;i<s.length;i++) {
					if(s[i].split("-")[0].equals(Ids))
					{
						System.out.println(s[i]);
						 filedownload=dirpath+s[i];
						 filedisplay=s[i];
						 System.out.println("文件名："+filedisplay);break;
					}
				}
  	  response.setContentType("application/x-download");
  	  System.out.println("文件："+filedisplay);
 // response.addHeader("Content-Disposition","attachment;filename="+filedisplay);
  response.setHeader("Content-Disposition", "attachment;filename=" + new String(filedisplay.getBytes("utf-8"),"ISO8859_1"));
  InputStream is=null;
  OutputStream os=null;
  BufferedInputStream bis=null;
  BufferedOutputStream bos=null;
  is=new FileInputStream(new File(filedownload));
  bis=new BufferedInputStream(is);
  os=response.getOutputStream();
  bos=new BufferedOutputStream(os);
  byte[] b=new byte[1024];
  int len=0;
  while((len=bis.read(b))!=-1) {
				  bos.write(b,0,len);
  }
  bis.close();
  is.close();
  bos.close();
  os.close();
				}else {
					PrintWriter out=response.getWriter();
					out.println("<html><body><h1  align=center>sorry,this student doesn't submit homework</h1></body></html>");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	  
	}else {        //批量下载
		
		response.setContentType("application/x-msdownload");
		response.setHeader("Content-Disposition", "attachment;filename="+new String("作业.zip".getBytes("utf-8"),"ISO8859_1")); //filename参数指定下载的文件名
		String dirpath="C:\\Users\\lyj\\eclipse-workspace\\ComputerTeachManage\\WebContent\\files\\";
		File f1=new File(dirpath);   //查找对应学号的文件
		String s[]=f1.list();
		String filename="";
		ZipOutputStream zos = new ZipOutputStream(response.getOutputStream()); 
		for(int j=0;j<str.length;j++) {
		for(int i=0;i<s.length;i++) {
			if(s[i].split("-")[0].equals(str[j]))
			{
				filename=s[i];
				File file = new File(dirpath + filename);
				zos.putNextEntry(new ZipEntry(filename));
				FileInputStream fis = new FileInputStream(file);
				try
				{
					byte b[] = new byte[1024];
					int n = 0;
					while((n = fis.read(b)) != -1){
						zos.write(b, 0, n);
					}
				}finally
				{
					zos.flush();
					fis.close();
				}
				 break;
			}
		}
		}
		zos.flush();
		zos.close();
	}
	}
  		}
	

