<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.sxpt.classes.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
	<head>
		<title>导航</title>
		<link rel="stylesheet" type="text/css" href="css/space.css">
		<link rel="stylesheet" type="text/css" href="css/top.css">
	</head>
	<body>
		<div id="nav-top">
			<h2>软件实训平台</h2>
			<div id="nav-main">
				<ul>
					<span>|</span><li><a href="space/left.jsp" target="left">个人空间</a></li>
					<span>|</span><li><a href="">课程实训</a></li>
					<span>|</span><li><a href="">项目实训</a></li>
					<span>|</span><li><a href="">管理中心</a></li>
					<span>|</span>
				</ul>
			</div>
			<%
				String identity = "学生";
				String username = "小兵";
				if(session.getAttribute("user") !=null){
					HashMap<String, Object> user = (HashMap<String, Object>)session.getAttribute("user");
					int type = Integer.parseInt(user.get("type").toString());
					if(type == 0){
						identity = "学生";
					} else if(type == 1){
						identity = "教师";
					} else if(type == 2){
						identity = "负责人";
					}
					
					
					if(type == 0){
						Student stu = (Student)user.get("student");
						username = stu.getSname();
					} else {
						Teacher tea = (Teacher)user.get("teacher");
						username = tea.getTname();
					}
				} %>
			<div id="user">
				当前用户:<span><%=username %></span>　<span><%=identity %></span>
				
			</div>
		</div>
	</body>
</html>