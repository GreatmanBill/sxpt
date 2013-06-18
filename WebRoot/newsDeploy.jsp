<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.sxpt.module.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>信息发布管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/space.css">
	<link rel="stylesheet" type="text/css" href="css/newsDeploy.css">

  </head>
  
  <body>
  	<div id="newsDeploy">
		<h2>信息发布管理</h2>
		<table>
			<tr class="tabelHead"><th>栏目标题</th><th>发布文章数</th><th>总文章数</th><th>操作</th></tr>
			<%
				SxptModule sxptM = new  SxptModule();
				HashMap<String,Object> news = sxptM.getNews(-1,0);
				ArrayList<HashMap<String,Object>> notice = (ArrayList<HashMap<String,Object>>)news.get("notice");
				ArrayList<HashMap<String,Object>> outline = (ArrayList<HashMap<String,Object>>)news.get("outline");
				int noticeSize = notice.size();
				int outlineSize = outline.size();
				
			 %>
			<tr class="tableCon notice"><td class="first">通知通告</td><td><%=noticeSize %></td><td><%=noticeSize %></td><td><a href="viewNotice.jsp">查看</a></td></tr>
			<tr class="tableCon news"><td class="first">实训要闻</td><td><%=outlineSize %></td><td><%=outlineSize %></td><td><a href="viewOutline.jsp">查看</a></td></tr>
		</table>
  	</div>
  </body>
</html>
