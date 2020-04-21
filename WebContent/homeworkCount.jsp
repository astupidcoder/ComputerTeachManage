<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ysu.dao.StuDao" 
    import="com.ysu.util.DbUtil" import="java.sql.*"
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
 DbUtil dbUtil=new DbUtil();
 StuDao stuDao=new StuDao();
 Connection con=null;
	 int[] grade=new int[5];  //已提交作业的学生数目，按班级分类
	 int[] count=new int[5];   //每个班的总学生数目
	 int total=0;//计算机系所有学生总数
	 int submitCount=0;//已经提交的学生总数
	 int scoreCount=0;//已经录入成绩的学生总数
 try{
	 con=dbUtil.getCon();
	 total=stuDao.studentCount(con);
	 submitCount=stuDao.submitCount(con);
	 scoreCount=stuDao.scoreCount(con);
	 ResultSet rs=stuDao.homeworkCount(con);
	 int i=0;
	 while(rs.next()){
		grade[i]=rs.getInt(2);
		i++;
	 }
	 ResultSet r=stuDao.studentCountByGrade(con);
	 int c=0;
	 while(r.next()){
		 count[c]=r.getInt(2);
		 c++;
	 }
	 
 }catch(Exception e){
	 e.printStackTrace();
 }
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>统计作业提交信息</title>
</head>
<body>
<table>
<tr><th width=200>班级</th><th width=200>一班</th><th width=200>二班</th><th width=200>三班</th><th width=200>四班</th><th width=200>五班</th></tr>
<tr><td align=center>已提交人数:</td><td align=center><%=grade[0] %></td><td align=center><%=grade[1] %></td><td align=center><%=grade[2] %></td><td align=center><%=grade[3] %></td><td align=center><%=grade[4] %></td></tr>
<tr><td align=center>还未提交数:</td><td align=center><%=count[0]-grade[0] %></td><td align=center><%=count[1]-grade[1] %></td><td align=center><%=count[2]-grade[2] %></td><td align=center><%=count[3]-grade[3] %></td><td align=center><%=count[4]-grade[4] %></td></tr>
</table>
<h1>总计:</h1>
<h2>计算机系学生总数:<%=total %></h2>
<h2>已提交学生数:<%=submitCount %></h2>
<h2>未提交学生数:<%=total-submitCount %></h2>
<h2>成绩已录入的学生数:<%=scoreCount %></h2>
<h2>成绩未录入的学生数:<%=total-scoreCount %></h2>
</body>
</html>