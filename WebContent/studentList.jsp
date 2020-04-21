<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生主界面</title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.5.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.5.2/themes/default/easyui.css">
	<script type="text/javascript" src="jquery-easyui-1.5.5.2/jquery.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.5.5.2/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.5.5.2/locale/easyui-lang-zh_CN.js"></script>
<script>
  //下载作业按钮的点击事件
function getIds(){
	 var selectedRows=$('#dg').datagrid('getSelections');
	 if(selectedRows.length==0){
		 $.messager.alert("系统提醒","请选择要下载作业的学生单元");
		 return;
	 }
	 var strIds=[];
	 for(var i=0;i<selectedRows.length;i++){
		 strIds.push(selectedRows[i].userId);
	 }
	 var ids=strIds.join(",");
	 alert(strIds);
	 document.getElementById("downloadBtn").setAttribute("href","download?Ids="+ids);
}
 
  
  function openScoreModifyDialog(){
	  var selectedRows=$('#dg').datagrid('getSelections');
	  if(selectedRows.length>1){
		 $.messager.alert("警告","只能选择一条对其进行修改！！！");return;
	  }
	  if(selectedRows.length<1){
			 $.messager.alert("警告","请选择要修改的学生记录！！！");return;
		  }
	  var row=selectedRows[0];
	  $('#fm').form('load',row);
	  $("#dlg").dialog('open');
  }
  
  function closeScoreDialog(){
	  $("#dlg").dialog('close');
  }
  
  function saveScore(){
	  $("#fm").form("submit",{
		  url:"saveScore",
		  onSubmit:function(){
			 return $(this).form("validate");},
		  success:function(result){
			  if(result.errorMsg){
				  $.messager.alert("系统提示",result.errorMsg);
				  return;
			  }else{
				  $.messager.alert("系统提示","成绩录入成功！");
				  $("#dlg").dialog("close");
				  $("#dg").datagrid("reload");
			       }
		  					}
		  }
	  );
  }
 
</script>
</head>
<body>
<table id="dg" title="学生信息管理" class="easyui-datagrid" fitColumns="true" pagination="true" fit="true"
	rownumbers="true" url="studentList" toolbar="#dgBtns">
	<thead>
	<tr>
		<th field="db" checkbox="true"></th>
		<th field="userId" width="50" align="center">学号</th>
		<th field="name" width="50" align="center">姓名</th>
		<th field="gradeId" width="50" align="center">班级</th>
		<th field="status" width="50" align="center">作业状态</th>
		<th field="score" width="50" align="center">成绩</th>
	</tr>
    </thead>
 </table>
	
	 <div id="dlg" title="成绩修改" class="easyui-dialog" closed=true
	style="width:400px;height:280px;padding:10px 20px" buttons="#dlg-btns">
	<form id="fm" method="post">
	<table>
		<tr> 
			<td>学生姓名:</td>
			<td><input type="text" id="name" name="name" class="easyui-textbox" editable=false></td>
		</tr>
		<tr>
		    <td>学号:</td>
			<td><input type="text" id="userId" name="userId" class="easyui-textbox" editable=false></td>
		</tr>
	    <tr><td>成绩:</td>
	        <td><select id="score" name="score" class="easyui-combobox" required=true 
	             data-options="panelHeight:'auto'">
	             <option value="">请选择</option>
	             <option value="A">A</option>
	             <option value="B">B</option>
	             <option value="C">C</option>
	             <option value="D">D</option>
	             <option value="F">F</option>
	          </select></td>
		</tr>
		<tr>
	</table>
	</form>
	</div>
	 	<div id="dgBtns">
			<a id="downloadBtn" href="#" class="easyui-linkbutton" iconCls="icon-ok" onClick="getIds()">下载作业</a>
			<a href="javascript:openScoreModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">录入成绩</a>
		</div>
		<div id="dlg-btns">
			<a href="javascript:saveScore()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
			<a href="javascript:closeScoreDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
		</div>
</body>
</html>