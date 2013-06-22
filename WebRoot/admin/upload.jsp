<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.sxpt.module.*" %>
<%@ page import="java.net.*" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>资料上传</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/space.css">
	
	<link rel="stylesheet" type="text/css" href="css/rsManage.css">
	<link rel="stylesheet" type="text/css" href="css/table.css">
	<link rel="stylesheet" type="text/css" href="css/upload.css">
  </head>
  
  <body>
		
		<div id="upload">
			<h1>资源分类</h1>
			<table border="1" cellspacing="0" cellpadding="0" id="course">
				<caption>资源信息</caption>
				<col width="80%"/>
				<col width="10%" />
				<col width="10%" />
				<tr class="head"><th>资源分类名称</th><th colspan='2'>操作</th></tr>
				<%
					SpaceTeaModule spaceTM = new SpaceTeaModule();
					ArrayList<HashMap<String, Object>> resourse_classes = spaceTM.getAllRsClass();
					try{
						String class_name = "";
						int classid = 0;
						HashMap<String, Object> temp = null;
						for(int i = 0;i < resourse_classes.size();i++){
							temp = resourse_classes.get(i);
							class_name = temp.get("class_name").toString();
							String tClass_name = URLEncoder.encode(URLEncoder.encode(class_name));
							classid = Integer.parseInt(temp.get("classid").toString());
							out.print("<tr class='cname'><td class='first'>"+class_name+"</td><td><a href='admin/viewRsClass.jsp?classid="+classid+"&class_name="+tClass_name+"'>查看</a></td><td>删除</td></tr>");
						}
					}catch(Exception e){}
				
				%>
				<!--  
				<tr class="cname"><td class="first">java资源分类</td><td>查看</td><td>删除</td></tr>
				<tr class="cname"><td class="first">c#资源分类</td><td>查看</td><td>删除</td></tr>
				-->
			</table>
			
			<div id="newRsClass">
				<fieldset>
					<legend>添加资源分类</legend>
					<form action="newRsClass" method="post">
						<% 
							if(session.getAttribute("message") != null){
								out.print("<p style='color:red'>"+session.getAttribute("message")+"</p>");
								session.setAttribute("message", null);
							}
						%>
						<p><label>资料分类名</label><input type="text" name="class_name" /></p>
						<p><input  class='but' type="submit" name="sub" value="添加" /></p>
					</form>
				</fieldset>
			</div>
		</div>
  </body>
</html>
