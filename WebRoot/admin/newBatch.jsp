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
    
    <title>新建批次</title>
    
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
		
		#newBatch{
			padding: 60px 0 0 40px;
		}
		form{
			margin:20px 0 0 0;
		}
		
		form label{
			display:inline-block;
			width:60px;
			height:25px;
		}
		
		.bname{
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
  	<div id="newBatch">
  		<% 
  			if(session.getAttribute("message") != null){
					out.print("<div>"+session.getAttribute("message")+"</div>");
					session.setAttribute("message", null);
			}
  		%>
		<h2>新建实训批次</h2>
		<form action="newBatch" method="post">
			<p><label>批次名</label><input class="bname" type="text" name="bname"/></p>
			<p><input class = "sub" type="submit" name="sub" value="新建"/></p>
		</form>
	</div>
  </body>
</html>
