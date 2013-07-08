<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    
    <title>My JSP 'StuReport.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
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
				int insum = 0;			//课内总成绩
				int outsum = 0;			//课外总成绩
				
				SpaceStuModule spaceSM = new SpaceStuModule();	//学生数据库对象
				SpaceTeaModule spaceTM = new SpaceTeaModule();	//教师数据库对象
				
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
					}
					bname = spaceSM.selectbname(bid);
				 	tname = spaceTM.selectTname(tid);
				 	insum = spaceSM.selectStuInsum(sno);
				 	outsum = spaceSM.selectStuOutsum(sno);
				 	

 %>
 	<label>姓名：</label><%= username%></br>
 	<label>所属批次：</label><%=bname %></br>
 	<label>所属教师：</label><%=tname %></br>
 	<label>课内成绩：</label><%=insum %></br>
 	<label>课外成绩：</label><%=outsum %>
  </body>
</html>
