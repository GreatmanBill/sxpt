<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="content-type" content="text/html;charset=utf-8" />
		<title>软件实训——个人空间</title>

		<link rel="StyleSheet" href="css/dtree.css" type="text/css" />
		<script type="text/javascript" src="js/dtree.js"></script>

	</head>

<body>
<div class="dtree">

	<p><a href="javascript: d.openAll();">打开所有</a> | <a href="javascript: d.closeAll();">关闭所有</a></p>

	<script type="text/javascript">
		<!--

		d = new dTree('d');
		//根据学生或教师的方向来显示菜单
		<%
			HashMap<String ,Object> user = (HashMap<String ,Object>)session.getAttribute("user");
			String t_direct = user.get("t_direct").toString();
		 %>
		
		
		d.add(0,-1,'<%=t_direct %>');
		d.add(1,0,'Web页面技术','example01.html');
		d.add(2,1,'java基础,'example01.html');
		d.add(3,1,'Oracle','example01.html');
		d.add(4,1,'J2EE企业级开发','example01.html');

		document.write(d);

		//-->
	</script>

</div>
</body>

</html>