<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.sxpt.module.*" %>
<jsp:include page="../validate.jsp" flush="true" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新建项目分类</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/space.css">
	<style type="text/css">
		body{
			background:#fff;
		}
		
		#newCourseClass{
			padding: 60px 0 0 40px;
		}
		
		.back{
			display:block;
			margin:0 0 30px 0;
			width:60px;
			line-height:25px;
			text-align:center;
			background:#4EBAF3;
		}

		form{
			margin:20px 0 0 0;
		}
		
		form label{
			display:inline-block;
			width:100px;
			height:25px;
		}
		
		.class_name{
			display:inline-block;
			width:200px;
			height:25px;
		}
		
		.sub{
			display:inline-block;
			width:60px;
			height:25px;
			font-size:16px;
			margin:20px 0 0 0;
		}
		
		
	</style>
  </head>
  
  <body>
  	<div id="newCourseClass">
		<a class="back" href="admin/itemManage.jsp">返回</a>
  		<% 
  			if(session.getAttribute("message") != null){
					out.print("<div>"+session.getAttribute("message")+"</div>");
					session.setAttribute("message", null);
			}
  		%>
		
		<h2>新建项目分类</h2>
		<form action="newItemClass" method="post">
			<p><label>项目分类名</label><input class="class_name" type="text" name="class_name"/></p>
			<p><input class = "sub" type="submit" name="sub" value="新建"/></p>
		</form>
	</div>
  </body>
</html>
