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
    
    <title>实训方向管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/space.css">
	<link rel="stylesheet" type="text/css" href="css/signManage.css">

  </head>
  
  <body>
		<div id = "signManage">
			<%
				int bid = Integer.parseInt(request.getParameter("bid"));
				String bname = request.getParameter("bname");
				byte[] b = null;
				b = bname.getBytes("ISO-8859-1");
				bname = new String(b,"utf-8");
			 %>
			<div id="newBatch"><a href="admin/newDirect.jsp?bid=<%=bid %>&bname=<%=bname %>">新建实训方向</a></div>
			
			<div style="margin:10px 0;">【实训批次】<%=bname %></div>
			<table border="1" cellspacing="0" cellpadding="0">
					<caption>实训方向信息</caption>
					<col width="60%"/>
					<col width="10%"/>
					<col width="10%"/>
					<tr class="head"><th>方向名称</th><th colspan="2">操作</th></tr>
					<%
						SpaceTeaModule spaceTM = new SpaceTeaModule();
				
						ArrayList<HashMap<String, Object>> t_directs  = spaceTM.getDirectssByBid(bid);
						
						try{
							int did = 0;
							String dname = "";
							bid = 0;
							String dprofile = "";
							HashMap<String, Object> temp = null;
							for(int i = 0 ;i < t_directs.size();i++){
								temp = t_directs.get(i);
								did = Integer.parseInt(temp.get("did").toString());
								dname = temp.get("dname").toString();
								bid = Integer.parseInt(temp.get("bid").toString());
								dprofile = temp.get("dprofile").toString();
								out.print("<tr class='has'><td class='bname'>"+dname+"</td><td>修改</td><td>删除</td></tr>");
								out.print("<tr><td class='bname' style='font-size:14px;' colspan='3'>【简介】："+dprofile+"</td></tr>");
							}
						}catch(Exception e){}
					 %>
					 <!-- 
					<tr class="has"><td class="bname">2013年春季学期实训</td><td>修改</td><td>删除</td></tr>
					<tr><td class="bname" colspan="3">方向简介</td></tr>
					<tr class="has"><td class="bname">2012年春季学期实训</td><td>修改</td><td>删除</td></tr>
					<tr><td class="bname" colspan="3">方向简介</td></tr>
					-->
			</table>
			
		</div>
  </body>
</html>
