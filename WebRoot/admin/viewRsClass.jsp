<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.sxpt.module.*"%>
<%@ page import="java.net.*"%>
<%@ page import="java.text.*"%>
<jsp:include page="../validate.jsp" flush="true" />
<%
	
    String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
	int classid = 0;
	String class_name = "";
	SpaceTeaModule spaceTM = new SpaceTeaModule();
	try{
		classid = Integer.parseInt(request.getParameter("classid"));
		
		class_name	= request.getParameter("class_name");
		class_name = URLDecoder.decode(URLDecoder.decode(class_name));
		
		System.out.println("class_name:"+class_name);
		
		

		
	}catch(Exception e){out.println(path);}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>课程分类信息</title>
    
	<link rel="stylesheet" type="text/css" href="css/space.css"/>
	<link rel="stylesheet" type="text/css" href="css/rsManage.css">
	<link rel="stylesheet" type="text/css" href="css/table.css">
	<link rel="stylesheet" type="text/css" href="css/viewRsClass.css"/>
  </head>
  
  <body>
		<div id="viewRsClass">
			<a class="back" href="admin/upload.jsp">返回</a>
			<h2>【资源分类】<%=class_name %></h2>
			<%
				String resHTML = "";
					try{
						ArrayList<HashMap<String, Object>> resources = spaceTM.getResourceByClassid(classid);
						int i; 
						String nCname = "";
						int nCid = 0;
						int nClassid = 0;
						String nClass_name = "";
						int rsid = 0;
						String rsname = "";
						String rsprofile="";
						String rssize = "";
						String rsurl = "";
						String rsuser = "";
						long ctime = 0;
						int task = 0;
						HashMap<String, Object> temp = null;
						for(i = 0;i < resources.size();i++){
							temp = resources.get(i);
							rsid = Integer.parseInt(temp.get("rsid").toString());
							rsname = temp.get("rsname").toString();
							rsprofile = temp.get("rsprofile").toString();
							rssize = temp.get("rssize").toString();
							rsurl = temp.get("rsurl").toString();
							rsuser = temp.get("rsuser").toString();
							ctime = Long.parseLong(temp.get("ctime").toString());
							SimpleDateFormat format =   new SimpleDateFormat( "yyyy-MM-dd" );
							String day = format.format(ctime);
							task = Integer.parseInt(temp.get("task").toString());
							String taskStr = "";
							if(task == 0){
								taskStr = "下载";
								
							} else {
								taskStr = "学习";
							}
							
							resHTML += "<tr class='cname'><td>"+taskStr+"</td><td class='first'>【资源】"+rsname+"</td><td>"+rsuser+"</td><td>"+day+"</td><td><a href='download/"+rsurl+"'>下载</a></td><td>删除</td></tr>";
							resHTML += "<tr class='profile' ><td colspan='6' class='first'><p>【文件大小】："+rssize+"</p><p>【资源描述】"+rsprofile+"</p></td></tr>";
						}
					}catch(Exception e){} 
					
					
			%>
			
			
			<table border="1" cellspacing="0" cellpadding="0" id="course">
				<caption>资源信息</caption>
				<col width="10%"/>
				<col width="40%"/>
				<col width="15%" />
				<col width="15%" />
				<col width="10%" />
				<col width="10%" />
				<tr class="head"><th>类型</th><th>资源名称</th><th>创建人</th><th>创建时间</th><th colspan='2'>操作</th></tr>
				<%=resHTML %>
				<!--  
				<tr class="cname"><td>学习</td><td class="first">【资源】java基础知识.zip</td><td>admin</td><td>2013-01-13</td><td>下载</td><td>删除</td></tr>
				<tr class="profile" ><td colspan="6" class="first"><p>【文件大小】：12.35kb</p><p>【资源描述】jav知识简介</p></td></tr>
				
				<tr class="cname"><td>下载</td><td class="first">【资源】java基础知识.zip</td><td>admin</td><td>2013-01-13</td><td>下载</td><td>删除</td></tr>
				<tr class="profile" ><td colspan="6" class="first"><p>【文件大小】：12.35kb</p><p>【资源描述】jav知识简介</p></td></tr>
				-->
				
			</table>
			
			<div id="uploadRs">
				<fieldset>
					<legend>添加资源</legend>
				<form action="uploadFile" method="post" enctype="multipart/form-data">
					<input type="hidden" name="cclassid" value="<%=classid %>"/>
					<input type="hidden" name="class_name" value="<%=class_name %>"/>
					<% 
			  			if(session.getAttribute("message") != null){
							out.print("<p>"+session.getAttribute("message")+"</p>");
							session.setAttribute("message", null);
						}
						
					ArrayList<HashMap<String, Object>> resourse_classes = spaceTM.getAllRsClass();
					String optionHTML = "";
					try{
						class_name = "";
						classid = 0;
						HashMap<String, Object> temp = null;
						
						for(int i = 0;i < resourse_classes.size();i++){
							temp = resourse_classes.get(i);
							class_name = temp.get("class_name").toString();
							classid = Integer.parseInt(temp.get("classid").toString());
							optionHTML += "<option value='"+classid+"'>"+class_name+"</option>";
						}
					}catch(Exception e){}
  				%>
  					
  					
  					
					<p><label>资源分类</label><select name="classid"><%=optionHTML %></select></p>
					
					<p><label>任务类型</label><select name="task"><option value="0">下载</option><option value="1">学习</option></select></p>
					
					<p><label>资源描述</label><textarea name="rsprofile"cols="80" rows="5"></textarea></p>
					
					<p><label>上传文件</label><input type="file" name="rsfile" value="浏览" /></p>
					
					<p><input class='but' type="submit" name="sub" value="确定" /><input class='but  res'  type="reset" name="res" value="重置" /></p>
				</form>
				</fieldset>
			<div>
		</div>
  </body>
</html>
