<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="../validate.jsp" flush="true" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新建实训方案</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/space.css">
	<link rel="stylesheet" type="text/css" href="css/newPlan.css">
  </head>
  
  <body>
		<div id= "newPlan">
			<h1>新建实训方案</h1>
			<% 
	  			if(session.getAttribute("message") != null){
						out.print("<div>"+session.getAttribute("message")+"</div>");
						session.setAttribute("message", null);
				}
  			%>
			<form action="newPlan" method="post">
				<p><label>方案名称</label><input  class="text" type="text" name="train_name" /> </p>
				<p><input class="sub" type="submit" name="sub" value="新建"/> </p>
			</form>
		</div>
  </body>
</html>
