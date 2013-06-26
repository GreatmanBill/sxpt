<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.sxpt.module.*" %>
<%@ page import="java.text.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	int classid = 0;
	String class_name = "";
	int cid = 0;
	String cname = "";
	try{
		cid = Integer.parseInt(request.getParameter("cid"));			
	}catch(Exception e){}
	
	SpaceTeaModule spaceTM = new SpaceTeaModule();
	String cprofile = "";
	try{
		HashMap<String,Object> course = spaceTM.getCourseByCid(cid);
		cprofile = course.get("cprofile").toString();
	}catch(Exception e){}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>查看课程</title>
	<link rel="stylesheet" type="text/css" href="css/space.css">
	<link rel="stylesheet" type="text/css" href="css/table.css">
	<link rel="stylesheet" type="text/css" href="css/rsManage.css">
  </head>
  
  <body>
		<div id="rsManage">
			<h2>【课程名】J2EE</h2>
			<p class="cprofile" style="margin:20px 0 0 0;">【课程简介】<%=cprofile %>></p>
			<%
				String downloadHTML = "";
				String studyHTML = "";
					try{
						ArrayList<HashMap<String, Object>> resources = spaceTM.getResourceByCid(cid);
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
								downloadHTML += "<tr class='cname'><td>"+taskStr+"</td><td class='first'>【资源】"+rsname+"</td><td>"+rsuser+"</td><td>"+day+"</td><td><a href='download?rsurl="+rsurl+"'>下载</a></td><td>删除</td></tr>";
								downloadHTML += "<tr class='profile' ><td colspan='6' class='first'><p>【文件大小】："+rssize+"</p><p>【资源描述】"+rsprofile+"</p></td></tr>";
							} else {
								taskStr = "学习";
								studyHTML += "<tr class='cname'><td>"+taskStr+"</td><td class='first'>【资源】"+rsname+"</td><td>"+rsuser+"</td><td>"+day+"</td><td><a href='download?rsurl="+rsurl+"'>下载</a></td><td>删除</td></tr>";
								studyHTML += "<tr class='profile' ><td colspan='6' class='first'><p>【文件大小】："+rssize+"</p><p>【资源描述】"+rsprofile+"</p></td></tr>";
							}
						}
					}catch(Exception e){}
			%>
			<table border="1" cellspacing="0" cellpadding="0" id="course">
				<caption> 任务信息</caption>
				<col width="10%"/>
				<col width="40%"/>
				<col width="15%" />
				<col width="15%" />
				<col width="10%" />
				<col width="10%" />
				<tr class="head"><th>类型</th><th>资源名称</th><th>创建人</th><th>创建时间</th><th colspan='2'>操作</th></tr>
				<%=studyHTML %>
				<!--  
				<tr class="cname"><td>学习</td><td class="first">【资源】java基础知识.zip</td><td>admin</td><td>2013-01-13</td><td>下载</td><td>删除</td></tr>
				<tr class="profile" ><td colspan="6" class="first"><p>【文件大小】：12.35kb</p><p>【资源描述】jav知识简介</p></td></tr>
				
				<tr class="cname"><td>下载</td><td class="first">【资源】java基础知识.zip</td><td>admin</td><td>2013-01-13</td><td>下载</td><td>删除</td></tr>
				<tr class="profile" ><td colspan="6" class="first"><p>【文件大小】：12.35kb</p><p>【资源描述】jav知识简介</p></td></tr>
				-->
			</table>
			
			<table border="1" cellspacing="0" cellpadding="0" id="course">
				<caption>资源信息</caption>
				<col width="10%"/>
				<col width="40%"/>
				<col width="15%" />
				<col width="15%" />
				<col width="10%" />
				<col width="10%" />
				<tr class="head"><th>类型</th><th>资源名称</th><th>创建人</th><th>创建时间</th><th colspan='2'>操作</th></tr>
				<%=downloadHTML %>
				<!--  
				<tr class="cname"><td>学习</td><td class="first">【资源】java基础知识.zip</td><td>admin</td><td>2013-01-13</td><td>下载</td><td>删除</td></tr>
				<tr class="profile" ><td colspan="6" class="first"><p>【文件大小】：12.35kb</p><p>【资源描述】jav知识简介</p></td></tr>
				
				<tr class="cname"><td>下载</td><td class="first">【资源】java基础知识.zip</td><td>admin</td><td>2013-01-13</td><td>下载</td><td>删除</td></tr>
				<tr class="profile" ><td colspan="6" class="first"><p>【文件大小】：12.35kb</p><p>【资源描述】jav知识简介</p></td></tr>
				-->
			</table>
		</div>
  </body>
</html>
