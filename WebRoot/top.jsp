<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
					<span>|</span><li><a href="">个人空间</a></li>
					<span>|</span><li><a href="">课程实训</a></li>
					<span>|</span><li><a href="">项目实训</a></li>
					<span>|</span><li><a href="">管理中心</a></li>
					<span>|</span>
				</ul>
			</div>
			
			<div id="user">
				当前用户:<span>Bill gates</span>　<span>学生</span>
				
			</div>
		</div>
	</body>
</html>