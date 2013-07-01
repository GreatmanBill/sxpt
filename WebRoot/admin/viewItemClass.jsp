<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.sxpt.module.*"%>
<%@ page import="java.net.*"%>
<jsp:include page="../validate.jsp" flush="true" />
<%
	
    String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
	
	int classid = 0;
	String class_name = "";
	SpaceTeaModule spaceTM = new SpaceTeaModule();
	try{
		classid = Integer.parseInt(request.getParameter("classid"));
		System.out.println(request.getQueryString());
		class_name	= request.getParameter("class_name");
		class_name = URLDecoder.decode(URLDecoder.decode(class_name));
		System.out.println("class_name:"+class_name);
		
	}catch(Exception e){out.println(path);}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>项目分类信息</title>
    
	<link rel="stylesheet" type="text/css" href="css/space.css"/>
	<link rel="stylesheet" type="text/css" href="css/viewCourseClass.css"/>
  </head>
  
  <body>
		<div id="viewCourseClass">
			<a class="back" href="admin/itemManage.jsp">返回</a>
			<h2>【项目分类】<%=class_name %></h2>
			
			<table border="1" cellspacing="0" cellpadding="0" id="course">
				<caption>项目信息</caption>
				<col width="80%"/>
				<col width="10%" />
				<col width="10%" />
				<tr class="head"><th>项目名称</th><th colspan='2'>操作</th></tr>
				
				<%
					try{
						
						ArrayList<HashMap<String, Object>> courses = spaceTM.getItemesByClassid(classid);
						
						HashMap<String, Object> temp = null;
						int itemid = 0;
						String item_name = "";
						String item_profile = "";
						String stageid = "";
						String tClass_name = URLEncoder.encode(URLEncoder.encode(class_name));
						//System.out.println(courses.toString());
						for(int i = 0;i < courses.size();i++){
						 	
							temp = courses.get(i);
							itemid = Integer.parseInt(temp.get("itemid").toString());
							
							item_name = temp.get("item_name").toString();
							item_profile = temp.get("item_profile").toString();
							stageid = temp.get("stageid").toString();
							
							out.print("<tr class='cname'><td class='first'>【项目】"+item_name+"</td><td><a href='admin/viewItem.jsp?itemid="+itemid+"&item_name="+item_name+"&classid="+classid+"&class_name="+tClass_name+"'>查看</a></td><td>删除</td></tr>");
							out.print("<tr class='profile' ><td colspan='3' class='first'>【简介】："+item_profile+"</td></tr>");
						}
					}catch(Exception e){}
				%>
				<!--  
				<tr class="cname"><td class="first">【项目】java基础知识</td><td>资源管理</td></tr>
				<tr class="profile" ><td colspan="2" class="first">【简介】：j识简介</td></tr>
				<tr class="cname"><td class="first">【项目】java基础知识</td><td>资源管理</td></tr>
				<tr class="profile" ><td colspan="2" class="first">【简介】：java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介jav知识简介</td></tr>
				-->
			</table>
			
			<div id="newCourse">
				<form action="newItem" method="post">
					<fieldset>
					<legend>新建项目</legend>
					<input type="hidden" name="classid" value="<%=classid %>"/>
					<input type="hidden" name="class_name" value="<%=request.getParameter("class_name") %>"/>
					<% 
						if(session.getAttribute("message") != null){
							out.print("<p>"+session.getAttribute("message")+"</p>");
							session.setAttribute("message", null);
						}
					%>
					<p class="cname">
						<label>项目名</label><input type="text" name="item_name"/>
					</p>
					<br/>
					<p>
						<label>项目简介</label><textarea  name="item_profile" cols="80" rows="5"></textarea>
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
