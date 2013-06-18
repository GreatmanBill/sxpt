<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.text.*" %>
<%@ page import="com.sxpt.module.*" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	int infoid = 0;
	
	int info_uid = 0;
	String info_name = "";
	String info_con = "";
	String info_title = "";
	String day = "";
	try{
		infoid = Integer.parseInt(request.getParameter("infoid"));
		
		SxptModule sxptM = new SxptModule();
		HashMap<String, Object> news = sxptM.getNewsById(infoid);
		
		info_name = news.get("info_name").toString();
		info_con = news.get("info_con").toString();
		info_title = news.get("info_title").toString();
		
		SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		day = format.format(Long.parseLong(news.get("ctime").toString()));
		
		
	}catch(Exception e){}

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'view.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/space.css">
	<link rel="stylesheet" type="text/css" href="css/view.css">
	

  </head>
  
  <body>
  	<div id="view">
  		<div id = "title">
  			<h2>标题:<%=info_title %></h2>
  		</div>
  		<div id = "user">
  			<span>发布人:<%=info_name %></span>&nbsp;&nbsp;&nbsp;&nbsp;<span>时间：<%=day %></span>
  		</div>
  		<p>
  			<%=info_con %>
  		</p>
  	
  	</div>
  </body>
</html>
