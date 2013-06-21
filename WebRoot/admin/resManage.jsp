<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	int classid = 0;
	String class_name = "";
	int cid = 0;
	String cname = "";
	try{
		classid = Integer.parseInt(request.getParameter("classid"));	
		class_name	= request.getParameter("class_name");	
		cid = Integer.parseInt(request.getParameter("cid"));	
		cname	= request.getParameter("cname");	
		
	}catch(Exception e){}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>资源管理</title>
	<link rel="stylesheet" type="text/css" href="css/space.css">
	<link rel="stylesheet" type="text/css" href="css/table.css">
	<link rel="stylesheet" type="text/css" href="css/rsManage.css">
  </head>
  
  <body>
		<div id="rsManage">
			<a class="back" href="admin/viewCourseClass.jsp?<% out.print("classid="+classid+"&class_name="+class_name);%>">返回</a>
			<h2>【课程名】J2EE</h2>
			<table border="1" cellspacing="0" cellpadding="0" id="course">
				<caption>资源信息</caption>
				<col width="50%"/>
				<col width="15%" />
				<col width="15%" />
				<col width="10%" />
				<col width="10%" />
				<tr class="head"><th>资源名称</th><th>创建人</th><th>创建时间</th><th colspan='2'>操作</th></tr>
				<tr class="cname"><td class="first">【资源】java基础知识.zip</td><td>admin</td><td>2013-01-13</td><td>下载</td><td>删除</td></tr>
				<tr class="profile" ><td colspan="5" class="first"><p>【文件大小】：12.35kb</p><p>【资源描述】jav知识简介</p></td></tr>
				
				<tr class="cname"><td class="first">【资源】java基础知识.zip</td><td>admin</td><td>2013-01-13</td><td>下载</td><td>删除</td></tr>
				<tr class="profile" ><td colspan="5" class="first"><p>【文件大小】：12.35kb</p><p>【资源描述】jav知识简介</p></td></tr>
			</table>
			
			<div id="uploadRs">
				<fieldset>
					<legend>添加资源</legend>
				<form action="addRsToCourse" method="post" enctype="multipart/form-data">
					<input type="hidden" name="cid" value="<%=cid %>"/>
					<p><label>资源名称</label><input type="text" name="rsname" /></p>
					<br/>
					<p><label>资源描述</label><textarea name="rsprofile"cols="80" rows="5"></textarea></p>
					<br/>
					<p><label>上传文件</label><input type="file" name="rsfile" value="浏览" /></p>
					<br/>
					<p><input type="checkbox" checked="checked" name="add2course"/><label style="width:200px; margin:0 0 0 10px;">添加到该课程</label></p>
					<br/>
					<p><input class='but' type="submit" name="sub" value="确定" /><input class='but  res'  type="reset" name="res" value="重置" /></p>
				</form>
				</fieldset>
			<div>
		</div>
  </body>
</html>
