<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.sxpt.classes.*" %>
<%@ page import="com.sxpt.module.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'personalTinfo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body style="padding:40px 0 0 60px;">

		<%	
				String bname = "";
				String tname = "";
				String identity = "";
				String username = "";
				String sno = "";
				String smail = "";
				int bid = 0;			//批次
				int tid = 0;			//所属教师id
				String t_direct = "";	//实训方向
				int type = 0;			//类别，学生为0
				
				SpaceStuModule spaceSM = new SpaceStuModule();	//学生数据库对象
				SpaceTeaModule spaceTM = new SpaceTeaModule();	//教师数据库对象
				if(session.getAttribute("user") !=null){
					HashMap<String, Object> user = (HashMap<String, Object>)session.getAttribute("user");
					type = Integer.parseInt(user.get("type").toString());
					if(type == 0){
						System.out.println("type++++"+type);
						identity = "学生";
					} else if(type == 1){
						System.out.println("type++++"+type);
						identity = "教师";
					} else if(type == 2){
						System.out.println("type++++"+type);
						identity = "负责人";
					}
					
					
					if(type == 0){
						Student stu = (Student)user.get("student");
						username = stu.getSname();
						sno = stu.getSno();
						bid = stu.getBid();
						tid = stu.getTid();
						t_direct = stu.getT_direct();
						type = stu.getType();
						smail = stu.getSmail();
						
					} else {
						Teacher tea = (Teacher)user.get("teacher");
						username = tea.getTname();
						System.out.println("type++++"+type);
						System.out.println("username++++"+username);
						tid = tea.getTid();
						t_direct = tea.getT_direct();
						type = tea.getType();
					}
				}
				 bname = spaceSM.selectbname(bid);
				 tname = spaceTM.selectTname(tid); 	
					
	%>
	当前用户:<span><%=username %></span>　<span><%=identity %></span></br></br>
	实训方向:<span><%= t_direct %></span></br>
	<form action = "personalTinfo" method = "post">
		<input type = "hidden" name = "tid" value = <%=tid %>></br>
		<label>新密码：</label><input name = "tpsw" type = "password"/></br>		
		<input style="display:inline-block;width:60px;height:25px;font-size:16px;line-height:25px;" name = "submit" type = "submit" value = "修改"/>
	</form>
	
  </body>
</html>
