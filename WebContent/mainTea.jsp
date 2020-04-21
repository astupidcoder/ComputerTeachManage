<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教师主界面</title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.5.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.5.2/themes/default/easyui.css">
	<script type="text/javascript" src="jquery-easyui-1.5.5.2/jquery.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.5.5.2/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.5.5.2/locale/easyui-lang-zh_CN.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
<link href="${pageContext.request.contextPath }/style/mainTea.css" rel="stylesheet">
 <script>
  function logout(){
	  $.messager.confirm("系统提示","您确定要退出系统吗？",function(r){
		  if(r){
			  window.location.href="login.jsp";
		  }
	  });
  }
</script>
</head>
<body>
<div class="container">
	<div class="navbar navbar-inverse" style="background-color:#6699FF;height:15%">
	   <h1 style="color:#FFFFFF;padding:40px 40px" align=center>计算机系课程设计管理系统
	   <div style="float:right;">
	   <font color=red>${currentUser.name }</font>,欢迎您
	   <a class="easyui-linkbutton" onclick="logout()">退出系统</a>
	   </div>
	   </h1>
	</div>
	<div style="float:left;width:15%">
	<ul>
	<li><a href="homeworkCount.jsp" target="content">统计提交作业信息</a></li>
	<li><a href="studentList.jsp" target="content">查看学生信息</a></li>
	<li><a href="TeaInfoView.jsp" target="content">查看个人信息</a>
	<li></li>
	</ul>
	</div>	
	
	<iframe src="" name="content" style="float:right;width:80%;height:450px;" frameborder="0"></iframe>
</div>








</body>
</html>