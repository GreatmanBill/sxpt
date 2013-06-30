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
		
		<%
			HashMap<String ,Object> user = (HashMap<String ,Object>)session.getAttribute("user");
			int type = Integer.parseInt(user.get("type").toString());
		 %>
		var type = <%=type%>;
		
		//学生
		if(type == 0){
			d.add(0,-1,'个人空间');
			d.add(1,0,'我的首页','example01.html');
			d.add(2,1,'个人信息','space/personalSinfo.jsp');
			d.add(3,1,'成绩单','example01.html');
			d.add(4,1,'实训总结','example01.html');
		}
		
		//教师
		if(type > 0){
			d.add(0,-1,'个人空间');
			d.add(1,0,'我的首页','example01.html');
			d.add(2,1,'个人信息','space/personalTinfo.jsp');
			d.add(3,1,'成绩单','example01.html');
			d.add(4,1,'实训总结','example01.html');
		}
		document.write(d);

		//-->
	</script>

</div>
</body>

</html>