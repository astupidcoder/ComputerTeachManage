<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生主界面</title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.5.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.5.2/themes/default/easyui.css">
	<script type="text/javascript" src="jquery-easyui-1.5.5.2/jquery.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.5.5.2/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.5.5.2/locale/easyui-lang-zh_CN.js"></script>
<script>
   $(function(){
	   var treeData=[{
		   text:"导航栏",
		   children:[{
			   id:1,
			   text:"个人信息管理",
			   children:[{
				   id:11,
				   text:"查看个人信息",
				   attributes:{
					   url:"stuInfoView.jsp"
				   }
			   },{id:12,
				   text:"重置密码",
				   attributes:{
					   url:"findpwd.jsp?email=${currentUser.email}&userId=${currentUser.userId}"
				   }
			   }]
		   },{
			   id:2,
			   text:"作业管理",
			  children:[{
				  id:21,
				  text:"作业查看"
			  }]
		   },{
			   id:3,
			   text:"退出系统"
		   }]
	   }];
	   
	  $("#tree").tree({
		  data:treeData,
		  onClick:function(node){
			  if(node.attributes){
				  openTabs(node.text,node.attributes.url);
			  }
			  if(node.id==21){
				  $("#filedg").dialog('open');
			  }
			  if(node.id==3){
				  logout();
			  }
		  }
	  } );
	   
	   function openTabs(text,url){
		   if($("#tabs").tabs('exists',text)){
			   $("#tabs").tabs('select',text);
		   }else{
			   var content="<iframe framborder='0' scrolling='auto' style='height:100%;width:100%'src="+url+"></iframe>";
			   $("#tabs").tabs('add',{
				   content:content,
				   title:text,
				   closable:true
			   });
		   }
	   }
   })
   
   //退出系统
   function logout(){
		$.messager.confirm('系统提示',"确定要退出系统吗？",function(x){
			if(x){
				<%-- session.invalidate();--%> 
				window.location.href="login.jsp";
			}
		});
	}
   
   //上传提交表单按钮
   function upload(){
   $("#uploadForm").form("submit",{
		onSubmit:function(){
			return $(this).form('validate');
		},
		success:function(result){
			var data=eval('('+result+')');
			if(data.upload){
				$.messager.alert({
					title:"系统提示",
					msg:"作业上传成功!!",
					fn:function(){
							$("#filedg").dialog('close');
					}
					});
				}
			}
		})
	}
   
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north'" style="height:80px">
		<div style="padding-top:30px;"> 当前用户:<font color="red" size=5>&nbsp;${currentUser.name }</font>&nbsp;&nbsp;&nbsp;&nbsp;学号:&nbsp;<font color=red size=5>${currentUser.userId }</font> </div>
	</div>
<div data-options="region:'south'" style="height:50px"></div>
  <div data-options="region:'west'" style="width:150px" split=true title="导航菜单">
	  <ul id="tree">
	   
	  </ul>
  </div>
 
<div data-options="region:'center'">
  <div id="tabs" class="easyui-tabs" fit="true">
  <div title="首页" align=center><font size=10 color=red>欢迎使用计算机系课设管理系统</font></div>
  </div>
中</div>
<div class="easyui-dialog" name="fileDialog" id="filedg" title="上传作业"
style="width:500px;height:300px;padding:20px 20px" closed=true>
	<font size=5>作业状态:</font>&nbsp;&nbsp;&nbsp;<font color=red size=10>${currentUser.status }</font>
	&nbsp;&nbsp;&nbsp;&nbsp;<font size=5>成绩:</font><font color=red size=10>${currentUser.score }</font>
	<div>
	<font size=5>上传作业:</font><br><br>
		<form id="uploadForm" action="upload?userId=${currentUser.userId }&name=${currentUser.name }" method="post" enctype="multipart/form-data">
			<div align=center><input type="file" name="上传文件"> <br><br>
			<a class="easyui-linkbutton" href="javascript:upload()">上传</a>
			</div>
		</form> 
	</div>
</div>
</body>
</html>