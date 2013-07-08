<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.sxpt.module.*"%>
<%@ page import="java.net.*"%>
<jsp:include page="../validate.jsp" flush="true" />
<%
	
    String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
	
	int itemid = 0;
	String item_name = "";
	int classid = 0;
	String class_name = "";
	String tClass_name = "";
	try{
		classid = Integer.parseInt(request.getParameter("classid"));
		System.out.println(request.getQueryString());
		class_name	= request.getParameter("class_name");
		class_name = URLDecoder.decode(URLDecoder.decode(class_name));
		System.out.println("class_name:"+class_name);
		tClass_name = URLEncoder.encode(URLEncoder.encode(class_name));
		
		itemid = Integer.parseInt(request.getParameter("itemid"));
		System.out.println(request.getQueryString());
		item_name	= request.getParameter("item_name");
		byte[] b = item_name.getBytes("ISO-8859-1");
		item_name = new String(b,"utf-8");
		System.out.println("item_name:"+item_name);
		
	}catch(Exception e){out.println(path);}
	
	SpaceTeaModule spaceTM = new SpaceTeaModule();
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
			<a class="back" href="<% out.print("admin/viewItemClass.jsp?classid="+classid+"&class_name="+tClass_name+"&itemid="+itemid+"&item_name="+item_name+""); %>">返回</a>
			<h2>【项目名称】<%=item_name %></h2>
			
			<table border="1" cellspacing="0" cellpadding="0" id="course">
				<caption>项目信息</caption>
				<col width="85%"/>
				<col width="15%" />
				<tr class="head"><th>项目名称</th><th>操作</th></tr>
				
				<%
					try{
						
						ArrayList<HashMap<String, Object>> stages = spaceTM.getStagesByItemid(itemid);
						
						HashMap<String, Object> temp = null;
						int staid = 0;
						String sta_name = "";
						String sta_profile = "";
						String sta_resc = "";
						//String tClass_name = URLEncoder.encode(URLEncoder.encode(class_name));
						//System.out.println(courses.toString());
						for(int i = 0;i < stages.size();i++){
						 	
							temp = stages.get(i);
							staid = Integer.parseInt(temp.get("staid").toString());
							
							sta_name = temp.get("sta_name").toString();
							sta_profile = temp.get("sta_profile").toString();
							sta_resc = temp.get("sta_resc").toString();
							
							out.print("<tr class='cname'><td class='first'>【阶段】"+sta_name+"</td><td><a href='admin/staResManage.jsp?staid="+staid+"&sta_name="+sta_name+"&classid="+classid+"&class_name="+tClass_name+"&itemid="+itemid+"&item_name="+item_name+"'>资源管理</a></td></tr>");
							out.print("<tr class='profile' ><td colspan='2' class='first'>【简介】："+sta_profile+"</td></tr>");
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
				<form action="newStage" method="post">
					<fieldset>
					<legend>新建项目阶段</legend>
					<input type="hidden" name="classid" value="<%=classid %>"/>
					<input type="hidden" name="class_name" value="<%=class_name %>"/>
					<input type="hidden" name="itemid" value="<%=itemid %>"/>
					<input type="hidden" name="item_name" value="<%=item_name %>"/>
					<% 
						if(session.getAttribute("message") != null){
							out.print("<p>"+session.getAttribute("message")+"</p>");
							session.setAttribute("message", null);
						}
					%>
					<p class="cname">
						<label style="width:90px;">项目阶段名</label><input type="text" name="sta_name"/>
					</p>
					<br/>
					<p>
						<label style="width:90px;">项目阶段简介</label><textarea  name="sta_profile" cols="80" rows="5"></textarea>
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
