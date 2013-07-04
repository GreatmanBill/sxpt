<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.sxpt.module.*"%>
<%@ page import="java.net.*"%>
<jsp:include page="../validate.jsp" flush="true" />
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	int itemid = 0;
	try{
		itemid = Integer.parseInt(request.getParameter("itemid"));			
	}catch(Exception e){}
	
	SpaceTeaModule spaceTM = new SpaceTeaModule();
	String item_name = "";
	String item_profile = "";
	
	try{
		HashMap<String,String> item = spaceTM.getItemByItemid(itemid);
		item_name = item.get("item_name");
		item_profile = item.get("item_profile");
	}catch(Exception e){}
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
			<h2>【项目名称】<%=item_name %></h2>
			
			<p style="margin:20px 0 0 0; width:85%;">
				<span>【项目简介】</span><%=item_profile %>
			</p>
			
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
						for(int i = 0;i < stages.size();i++){
						 	
							temp = stages.get(i);
							staid = Integer.parseInt(temp.get("staid").toString());
							
							sta_name = temp.get("sta_name").toString();
							sta_profile = temp.get("sta_profile").toString();
							sta_resc = temp.get("sta_resc").toString();
							
							out.print("<tr class='cname'><td class='first'>【阶段】"+sta_name+"</td><td><a href='project/viewStage.jsp?staid="+staid+"'>资源管理</a></td></tr>");
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
		</div>
  </body>
</html>
