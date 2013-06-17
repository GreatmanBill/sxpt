<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'updateInReport.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <form action = "updateInReport" method = "post">
    	<label>学生姓名</label><input name = "sname" id = "sname" type = "text"/></br>
    	<label>学生学号</label><input name = "sno" id = "sno" type = "text"/></br>
    	<label>课内次数</label><input name = "In_times" type = "text"/></br>
    	<label>课内成绩</label><input name = "In_grade" type = "text"/></br>
    	<input name = "submit" type = "submit" value = "submit"/>
    </form>
  </body>
</html>
