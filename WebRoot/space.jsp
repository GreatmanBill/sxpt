<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="content-type" content="text/html;charset=utf-8" />
		<title>软件实训——个人空间</title>
	
		<link rel="stylesheet" type="text/css" href="css/space.css">
	</head>
	
		<frameset rows="100,*">
			<frame name="top" src="top.jsp" scrolling="no"/> 
			<frameset cols="250,*">
				<frame name="left" src="space/space.jsp"/>
				<frame name="right" src="right.jsp" name="right"/> 
			</frameset> 
		</frameset>
	
</html>
  