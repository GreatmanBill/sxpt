<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>实训方案</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/space.css">
	<link rel="stylesheet" type="text/css" href="css/viewPlan.css">

  </head>
  
  <body>
	<div id="viewPlan">
		<a class="back" href="admin/planManage.jsp">返回</a>
		<h3>【实训方案】2013年春季学期实训方案</h3>
		<div	 id="addUnit">
			
			<form action="" method="post">
				<fieldset>
					<legend>添加课程和项目信息</legend>
	  
					<p>
						<label>课程</label>
						<select name="">
							<option value="sdf">java基础</option>
							<option value="sdf">java基础</option>
							<option value="sdf">java基础</option>
							<option value="sdf">java基础</option>
							<option value="sdf">java基础</option>						
						</select>
					</p>
					
					<p>
						<label>项目</label>
						<select name="">
							<option value="sdf">实训平台项目</option>
							<option value="sdf">图书管理系统</option>			
						</select>
					</p>
					
					<p><input class="sub" type="submit" name="sub" value="添加"/></p>
					<br/>
				</fieldset>
			</form>
		</div>
		
		<table border="1" cellspacing="0" cellpadding="0" id="course">
			<caption>课程信息</caption>
			<col width="90%"/>
			<col width="10%" />
			<tr class="head"><th>课程名称</th><th>操作</th></tr>
			<tr class="cname"><td class="first">【课程】java基础知识</td><td>删除</td></tr>
			<tr class="profile" ><td colspan="2" class="first">【简介】：java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介jav知识简介</td></tr>
			<tr class="cname"><td class="first">【课程】java基础知识</td><td>删除</td></tr>
			<tr class="profile" ><td colspan="2" class="first">【简介】：java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介jav知识简介</td></tr>
		</table>
		
		<table border="1" cellspacing="0" cellpadding="0" id="course">
			<caption>项目信息</caption>
			<col width="90%"/>
			<col width="10%" />
			<tr class="head"><th>项目名称</th><th>操作</th></tr>
			<tr class="cname"><td class="first">【项目】java基础知识</td><td>删除</td></tr>
			<tr class="profile" ><td colspan="2" class="first">【简介】：java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介jav知识简介</td></tr>
			<tr class="cname"><td class="first">【项目】java基础知识</td><td>删除</td></tr>
			<tr class="profile" ><td colspan="2" class="first">【简介】：java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介jav知识简介</td></tr>
		</table>
		
	</div>
  </body>
</html>
