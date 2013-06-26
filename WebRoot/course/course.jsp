<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.sxpt.classes.*"%>
<%@ page import="com.sxpt.module.*"%>
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

		
		
		<%
			int type = 0;
			String t_direct = "";
			String linkHTML = "";
			try{
				HashMap<String ,Object> user = (HashMap<String ,Object>)session.getAttribute("user");
				type = Integer.parseInt(user.get("type").toString());
				
				int bid = 0;
				if(type == 0){//学生
					Student stu = (Student)user.get("student");
					bid = stu.getBid();
					t_direct = stu.getT_direct();
					
				} else {	  //教师
					Teacher tea = (Teacher)user.get("teacher");
					t_direct = tea.getT_direct();
				}
				
				System.out.println("t_direct:"+t_direct);
				SpaceTeaModule spaceTM = new SpaceTeaModule();
				HashMap<String, Object> res = spaceTM.getCourseAndItemByDirect(t_direct);
				System.out.println(res);
				ArrayList<HashMap<String, Object>> courses = (ArrayList<HashMap<String, Object>>)res.get("courses");
				HashMap<String, Object> temp = null;
				for(int i = 0;i < courses.size();i++){
					temp = courses.get(i);
					int cid = Integer.parseInt(temp.get("cid").toString());
					String cname = temp.get("cname").toString();
					linkHTML += "d.add("+(i + 1)+",0,'"+cname+"','course/viewCourse.jsp?cid="+cid+"','','right');";
				}
				
				
				
			}catch(Exception e){
				e.printStackTrace();
			System.out.println(33333);}
			System.out.println(linkHTML+"llllllllllllllll");
		 %>
		var type = <%=type%>;
		d = new dTree('d');
		d.add(0,-1,'<%=t_direct %>');
		/*
		d.add(1,0,'Java软件工程师实训简介','example01.html');
		d.add(2,0,'Web页面技术','sdfdsf');
		d.add(3,0,'Java基础','example01.html');
		d.add(4,0,'J2EE企业级开发','example01.html');
		d.add(5,0,'Oracle','example01.html');
		d.add(6,0,'基础知识综合实例','example01.html');*/
		<%=linkHTML %>
		document.write(d);

		//-->
	</script>

</div>
</body>

</html>