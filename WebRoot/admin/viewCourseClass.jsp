<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.sxpt.module.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
	int classid = 0;
	String class_name = "";
	try{
		classid = Integer.parseInt(request.getParameter("classid"));	
		class_name	= request.getParameter("class_name");	
	}catch(Exception e){}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>课程分类信息</title>
    
	<link rel="stylesheet" type="text/css" href="css/space.css"/>
	<link rel="stylesheet" type="text/css" href="css/viewCourseClass.css"/>
  </head>
  
  <body>
		<div id="viewCourseClass">
			<a class="back" href="admin/courseManage.jsp">返回</a>
			<h2>【课程分类】<%=class_name %></h2>
			
			<table border="1" cellspacing="0" cellpadding="0" id="course">
				<caption>课程信息</caption>
				<col width="85%"/>
				<col width="15%" />
				<tr class="head"><th>课程名称</th><th>操作</th></tr>
				
				<%
					try{
						SpaceTeaModule spaceTM = new SpaceTeaModule();
						ArrayList<HashMap<String, Object>> courses = spaceTM.getCoursesByClassid(classid);
						
						HashMap<String, Object> temp = null;
						int cid = 0;
						String cname = "";
						String cprofile = "";
						String cresourse = "";
						//System.out.println(courses.toString());
						for(int i = 0;i < courses.size();i++){
						 	
							temp = courses.get(i);
							cid = Integer.parseInt(temp.get("cid").toString());
							
							cname = temp.get("cname").toString();
							cprofile = temp.get("cprofile").toString();
							cresourse = temp.get("cresourse").toString();
							out.print("<tr class='cname'><td class='first'>【课程】"+cname+"</td><td><a href='admin/resManage.jsp?cid="+cid+"&cname="+cname+"&classid="+classid+"&class_name="+class_name+"'>资源管理</a></td></tr>");
							out.print("<tr class='profile' ><td colspan='2' class='first'>【简介】："+cprofile+"</td></tr>");
						}
					}catch(Exception e){}
				%>
				<!--  
				<tr class="cname"><td class="first">【课程】java基础知识</td><td>资源管理</td></tr>
				<tr class="profile" ><td colspan="2" class="first">【简介】：j识简介</td></tr>
				<tr class="cname"><td class="first">【课程】java基础知识</td><td>资源管理</td></tr>
				<tr class="profile" ><td colspan="2" class="first">【简介】：java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介jav知识简介</td></tr>
				-->
			</table>
			
			<div id="newCourse">
				<form action="newCourse" method="post">
					<fieldset>
					<legend>新建课程</legend>
					<input type="hidden" name="classid" value="<%=classid %>"/>
					<% 
						if(session.getAttribute("message") != null){
							out.print("<p>"+session.getAttribute("message")+"</p>");
							session.setAttribute("message", null);
						}
					%>
					<p class="cname">
						<label>课程名</label><input type="text" name="cname"/>
					</p>
					<br/>
					<p>
						<label>课程简介</label><textarea  name="cprofile" cols="80" rows="5"></textarea>
					</p>
					<br/>
					<p><input class="sub" type="submit" name="sub" value="添加"/><input class="res" type="reset" name="sub" value="重置"/></p>
					<br/>
				</fieldset>
				</form>
			</div>
			
			
		</div>
  </body>
</html>
