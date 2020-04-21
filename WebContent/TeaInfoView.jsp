<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教师个人信息</title>
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.5.2/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.5.2/themes/default/easyui.css">
	<script type="text/javascript" src="jquery-easyui-1.5.5.2/jquery.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.5.5.2/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.5.5.2/locale/easyui-lang-zh_CN.js"></script>
<script>
function teacherModify(){
	var tel=${currentUser.tel};
	var tel1=$("#tel").textbox('getValue');
    var name="${currentUser.name}";
	var name1=$("#name").textbox('getValue');
	var email1=$("#email").textbox('getValue');
    var email="${currentUser.email}";
    if(name1==name&&tel1==tel&&email1==email){
    	$.messager.alert("系统提示","您的信息没有改变！！");
    }else{
    	$("#fm").form("submit",{
    	success:function(result){
    		var data=eval('('+result+')');
    		if(data.success){
    			$.messager.alert("友情提示","您的信息修改成功！！！");
    		}else{
    			$.messager.alert("友情提示","您的信息修改失败！！！");
    		}
    	}	
    	});
    }
	
}

function resetValue(){
	var tel=${currentUser.tel};
    var name="${currentUser.name}";
    var email="${currentUser.email}";
   // alert(typeof(tel));alert(typeof(name));alert(typeof(email));
	 $("#name").textbox('setValue',name);
	$("#email").textbox('setValue',email);
	 $("#tel").numberbox('setValue',tel);  
}
</script>
</head>
<body>
  <div class="easyui-panel" title="个人信息" style="width:100% max-width:400px;padding:30px 60px" >
  <form id="fm" name="fm" method="post" action="teacherInfoModify">
  <div style="margin-bottom:20px">
    <input class="easyui-textbox" id="name" name="name" style="width:30%" data-options="label:'姓名:'" value="${currentUser.name }" required=true editable="false"><br><br>
    <input class="easyui-textbox" id="userId" name="userId" style="width:30%" data-options="label:'工号:' " value="${currentUser.userId }" editable="false"><br><br>
   邮箱:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input class="easyui-textbox" id="email" name="email" style="width:20%" data-options="validType:'email',validateOnBlur:true" value="${currentUser.email }"><br><br>
    <input class="easyui-numberbox" id="tel" name="tel" style="width:30%" data-options="label:'电话:'" value="${currentUser.tel }"><br>
    <a href="javascript:teacherModify()" class="easyui-linkbutton">保存</a>
    <a href="javascript:resetValue()" class="easyui-linkbutton">还原</a>
   </div>
   </form>
  </div>
  
</body>
</html>