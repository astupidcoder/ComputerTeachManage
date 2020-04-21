<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生个人信息</title>
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.5.2/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.5.2/themes/default/easyui.css">
	<script type="text/javascript" src="jquery-easyui-1.5.5.2/jquery.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.5.5.2/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.5.5.2/locale/easyui-lang-zh_CN.js"></script>
<script>
function studentInfoModify(){
	var tel=${currentUser.tel};
	var tel1=$("#tel").textbox('getValue');
    var name="${currentUser.name}";
	var name1=$("#name").textbox('getValue');
	var email1=$("#email").textbox('getValue');
    var email="${currentUser.email}";
    if(name1==name&&tel1==tel&&email1==email){
    	alert("您的信息没有改变！！");
    }else{
    	$("#fm").form("submit",{
    	success:function(result){
    		var data=eval('('+result+')');
    		if(data.success){
    			alert("您的信息修改成功！！！");
    		}else{
    			alert("对不起，信息修改失败！！！");
    		}
    	}	
    	});
    }
	
}
</script>
</head>
<body>
  <div class="easyui-panel" title="个人信息" style="width:100% max-width:400px;padding:30px 60px" >
  <form id="fm" name="fm" method="post" action="studentInfoModify">
  <div style="margin-bottom:20px">
    <input class="easyui-textbox" id="name" name="name" style="width:100%" data-options="label:'姓名:'" value="${currentUser.name }" editable="false" required=true>
    <input class="easyui-textbox" id="userId" name="userId" style="width:100%" data-options="label:'学号:' " value="${currentUser.userId }" editable='false'>
    <input class="easyui-textbox" id="email" name="email" style="width:100%" data-options="label:'邮箱:'" value="${currentUser.email }" validType='email' required=true>
    <input class="easyui-textbox" id="tel" name="tel" style="width:100%" data-options="label:'电话:'" value="${currentUser.tel }" required=true>
    <a href="javascript:studentInfoModify()" class="easyui-linkbutton">修改</a>
   </div>
   </form>
  </div>
  
</body>
</html>