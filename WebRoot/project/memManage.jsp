<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.sxpt.module.*" %>
<%@ page import="java.net.*" %>
<%@ page import="com.sxpt.classes.*" %>
<jsp:include page="../validate.jsp" flush="true" />

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	int did = 0;
	int tid = 0;
	try{
		HashMap<String, Object> user = (HashMap<String, Object>)session.getAttribute("user");
		Teacher tea = (Teacher)user.get("teacher");
		String t_direct = tea.getT_direct();
		tid = tea.getTid();
		
		SxptModule sxptM = new SxptModule();
		did = sxptM.getDidByDname(t_direct);
	}catch(Exception e){
		e.printStackTrace();
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>项目成员</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/space.css">
	
	<link rel="stylesheet" type="text/css" href="css/rsManage.css">
	<link rel="stylesheet" type="text/css" href="css/table.css">
	<link rel="stylesheet" type="text/css" href="css/upload.css">
  </head>
  
  <body>
		
		<div id="upload">
			<h1>项目成员管理</h1>
			<table border="1" cellspacing="0" cellpadding="0" id="course">
				<caption>项目成员分组信息</caption>
				<col width="70%"/>
				<col width="10%" />
				<col width="10%" />
				<col width="10%" />
				<tr class="head"><th>项目成员分组名称</th><th colspan='3'>操作</th></tr>
				<%
					MemManageModule memMana = new MemManageModule();
					ArrayList<HashMap<String, String>> memGroups = memMana.getMemGroupsByTid(tid);
					try{
						String mem_gname = "";
						int mem_gid = 0;
						
						HashMap<String, String> temp = null;
						for(int i = 0;i < memGroups.size();i++){
							temp = memGroups.get(i);
							mem_gname = temp.get("mem_gname");
							mem_gid = Integer.parseInt(temp.get("mem_gid"));
							out.print("<tr class='cname'><td class='first'>"+mem_gname+"</td><td><a href='project/viewMemGroup.jsp?mem_gid="+mem_gid+"&mem_gname="+mem_gname+"'>查看</a></td><td>修改</td></td><td>删除</td></tr>");
						}
					}catch(Exception e){}
				
				%>
				<!--  
				<tr class="cname"><td class="first">java资源分类</td><td>查看</td><td>删除</td></tr>
				<tr class="cname"><td class="first">c#资源分类</td><td>查看</td><td>删除</td></tr>
				-->
			</table>
			
			<div id="newRsClass">
				<fieldset>
					<legend>添加项目成员分组</legend>
					<form action="newMemGroup" method="post">
						<% 
							if(session.getAttribute("message") != null){
								out.print("<p style='color:red'>"+session.getAttribute("message")+"</p>");
								session.setAttribute("message", null);
							}
						%>
						<input type="hidden" name="did" value="<%=did %>"/>
						<input type="hidden" name="tid" value="<%=tid %>"/>
						<p><label>项目成员分组名</label><input type="text" name="mem_gname" /></p>
						<p><input  class='but' type="submit" name="sub" value="添加" /></p>
					</form>
				</fieldset>
			</div>
		</div>
  </body>
</html>
