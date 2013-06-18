<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.sxpt.classes.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addNews.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/space.css">
	<link rel="stylesheet" type="text/css" href="css/addNews.css">

  </head>
  
  <body>
  	<div id="addNews">
  		<form action="addNews" method="post">
  			<%
  				if(session.getAttribute("message") != null){
    				out.print("<p>"+session.getAttribute("message")+"</p>");
    				session.setAttribute("message",null); 
    			}
  			 %>
	  		<p> 
	  			<label>文章标题</label>&nbsp;&nbsp;<input type="text" name="info_title" />
	  		</p>
	  		<p>
				<%
					int info_uid = 0;
					String info_name = "";
					try{
						HashMap<String, Object> user = (HashMap<String, Object>)session.getAttribute("user");
						
						Teacher tea = (Teacher)user.get("teacher");
						info_name = tea.getTname();
						info_uid = tea.getTid();
					}catch(NullPointerException e){
						
					}
				%>
	  			<input type="hidden" name="info_uid" value="<%=info_uid %>"/><input type="hidden" name="info_name" value="<%=info_name %>"/>
	  		</p>
	  		<p>
	  			<%
	  				int type = 0;
	  				try{
	  					type = Integer.parseInt(request.getParameter("type"));
	  				}catch(Exception e){
					}
	  			 %>
	  			<label>信息类别</label>&nbsp;&nbsp;<select name="type">
	  				<option value="0">通知公告</option>
	  				<option value="1" <% if(type == 1) out.print("selected = 'selected'"); %>>实训要闻</option>
	  			</select>
	  		</p>
	  		<p>
	  			<textarea rows="30" cols="80" name="info_con"></textarea>
	  		</p>
	  		<p>
	  			<input class ="sub" type = "submit" name="sub" value="添加文章"/>
	  		</p>
	  	</form>
  	</div>
  </body>
</html>
