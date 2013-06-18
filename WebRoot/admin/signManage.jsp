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
    
    <title>实训报名管理</title>
    
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
			<div id="newBatch"><a href="admin/newBatch.jsp">新建实训批次</a></div>
			<table border="1" cellspacing="0" cellpadding="0">
					<caption>实训报名信息</caption>
					<tr class="head"><th>批次名称</th><th>是否开放报名</th><th colspan="5">操作</th></tr>
					<%
						SpaceTeaModule spaceTM = new SpaceTeaModule();
						 ArrayList<HashMap<String, Object>> batchs = spaceTM.getAllBatch();
						 
						 HashMap<String,Object> batch = null;
						 String bname = "";
						 int bid = 0;
						 int open = 0;
						 String isOpen = "";
						 String isOpenStyle = "";
						 
						 String openStr = "";
						 String openStrStyle = "";
						 
						 String closeStr = "";
						 String closeStrStyle = "";
						 for(int i = 0;i < batchs.size();i++){
						 	batch = batchs.get(i);
						 	bname = batch.get("bname").toString();
						 	bid = Integer.parseInt(batch.get("bid").toString());
						 	open = Integer.parseInt(batch.get("open").toString());
						 	if(open == 0){
						 		isOpen = "关闭";
						 		openStr = "<a color='yello' href=''>开放报名</a>";
						 		closeStr = "关闭报名";
						 		isOpenStyle = "red";
						 		openStrStyle = "yello";
						 		closeStrStyle = "#000";
						 	}else {
						 		openStr = "开放报名";
						 		closeStr = "<a color='yello' href=''>关闭报名</a>";
						 		isOpen = "开放";
						 		isOpenStyle = "blue";
						 		openStrStyle = "#000";
						 		closeStrStyle = "yello";
						 	}
						 	if(i == 0){
						 		out.print("<tr class='has'><td class='bname'>"+bname+"</td><td style='color:"+isOpenStyle+";'>"+isOpen+"</td><td><a href='admin/viewDirect.jsp?bid="+bid+"&bname="+bname+"'>实训方向</a></td><td style='color:"+openStrStyle+";'>"+openStr+"</td><td style='color:"+closeStrStyle+";'>"+closeStr+"</td><td>修改</td><td>删除</td></tr>");
						 	} else {
						 		out.print("<tr><td colspan='7' style='text-indent:-9999px;'>隐藏</td></tr>");
						 		out.print("<tr class='has'><td class='bname'>"+bname+"</td><td style='color:"+isOpenStyle+";'>"+isOpen+"</td><td><a href='admin/viewDirect.jsp?bid="+bid+"&bname="+bname+"'>实训方向</a></td><td style='color:"+openStrStyle+";'>"+openStr+"</td><td style='color:"+closeStrStyle+";'>"+closeStr+"</td><td>修改</td><td>删除</td></tr>");
						 	}
						 }
					 %>
					 <!-- 
					<tr class="has"><td class="bname">2013年春季学期实训</td><td>关闭</td><td>实训方向</td><td>开放报名</td><td>关闭报名</td><td>修改</td><td>删除</td></tr>
					<tr><td colspan="7" style="text-indent:-9999px;">隐藏</td></tr>
					<tr class="has"><td class="bname">2012年春季学期实训</td><td>关闭</td><td>实训方向</td><td>开放报名</td><td>关闭报名</td><td>修改</td><td>删除</td></tr>
					 -->
			</table>
			
		</div>
  </body>
</html>
