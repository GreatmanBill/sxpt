<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.sxpt.module.*"%>
<%@ page import="java.net.*"%>
<jsp:include page="../validate.jsp" flush="true" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>课程管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/space.css">
	<link rel="stylesheet" type="text/css" href="css/courseManage.css">

  </head>
  
  <body>
		<div id="courseManage">
			
			<div class="newCoure"><a href="admin/newCourseClass.jsp"> 新建课程分类</a></div>
			<table border="1" cellspacing="0" cellpadding="0" id="course">
			<caption>课程分类信息</caption>
			<col width="90%"/>
			<col width="10%" />
			<tr class="head"><th>课程分类名称</th><th colspan="2">操作</th></tr>
			<%
				SpaceTeaModule spaceTM = new SpaceTeaModule();
				try{
					ArrayList<HashMap<String, Object>> course_classes = spaceTM.getAllCourseClass();
					
					int classid = 0;
					String class_name = "";
					
					HashMap<String, Object> temp = null;
					for(int i = 0;i < course_classes.size();i++){
						temp = course_classes.get(i);
						classid = Integer.parseInt(temp.get("classid").toString());
						class_name = temp.get("class_name").toString();
						String tClass_name = URLEncoder.encode(URLEncoder.encode(class_name));
						String url = "admin/viewCourseClass.jsp?classid="+classid+"&class_name="+tClass_name;
						//url = URLEncoder.encode(url);
						out.print("<tr class='cname'><td class='first'>"+class_name+"</td><td><a href='"+url+"'>查看</a></td></tr>");
					}
				}catch(Exception e){}  
			 %>
			 <!-- 
			<tr class="cname"><td class="first">java课程体系</td><td><a href="admin/courseManaDire.jsp">查看</a></td></tr>
			<tr class="cname"><td class="first">c#课程体系</td><td>查看</td></tr>
			  -->
			
		</table>
		</div>
  </body>
</html>
