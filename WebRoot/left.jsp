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

		
		d.add(0,-1,'管理中心');
		d.add(1,0,'资源管理','example01.html');
		d.add(2,1,'课程管理','example01.html');
		d.add(3,1,'项目管理','example01.html');
		d.add(4,1,'实训方案管理','example01.html');
		
		d.add(5,0,'实训组织与评估','example01.html');
		d.add(6,5,'实训报名管理','admin/signManage.jsp','','right');
		
		d.add(7,5,'实训实施管理','example01.html');
		d.add(8,5,'成绩单模板管理','example01.html');
		d.add(9,5,'实训综合统计','example01.html');
		d.add(10,5,'实训总结管理','example01.html');
		
		d.add(11,0,'用户管理','example01.html','Pictures I\'ve taken over the years','','','images/imgfolder.gif');
		d.add(12,11,'教师账号管理','leadTea.jsp','Pictures of Gullfoss and Geysir','right');
		d.add(13,11,'学生账号管理','leadStu.jsp','Pictures of Gullfoss and Geysir','right');
		d.add(14,11,'密码初始化','example01.html','Pictures of Gullfoss and Geysir');
		
		d.add(15,0,'信息管理','example01.html');
		d.add(16,15,'栏目管理','example01.html','','','images/trash.gif');
		d.add(17,15,'信息发布','newsDeploy.jsp','','right','images/trash.gif');

		document.write(d);

		//-->
	</script>

</div>
</body>

</html>