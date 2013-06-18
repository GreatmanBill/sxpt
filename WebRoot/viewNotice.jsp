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
    
    <title>通知通告信息管理</title>
    
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
		<h2>通知通告信息管理</h2>
		<p class="addLink"><a href="addNews.jsp?type=0">添加新闻文章</a></p>
		<table>
			<tr class="tabelHead"><th>文章标题</th><th class="do" colspan="3">操作</th></tr>
			<%
				SxptModule sxptM = new SxptModule();
				//得到通知通告全部文章
				HashMap<String, Object> news = sxptM.getNews(0,0);
				ArrayList<HashMap<String,Object>> newsList = (ArrayList<HashMap<String,Object>>)news.get("notice");
				int infoid = 0;
				String info_title = "";
				for(int i = 0;i < newsList.size();i++){
					news = newsList.get(i);
					infoid = Integer.parseInt(news.get("infoid").toString());
					info_title = news.get("info_title").toString();
					out.print("<tr class='tableCon notice'><td class='first'>"+info_title+"</td><td><a href='view.jsp?"+infoid+"'>查看</a></td><td>修改</td><td>删除</td></tr>");
				}
			 %>
			
			<!-- <tr class="tableCon news"><td class="first">实训要闻</td><td>查看</td><td>修改</td><td>删除</td></tr> -->
		</table>
  	</div>
  </body>
</html>
