<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.sxpt.module.*" %>
<%@ page import="java.net.*" %>
<%@ page import="com.sxpt.classes.*" %>
<jsp:include page="../validate.jsp" flush="true" />

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>项目成员</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/space.css">
	
	<link rel="stylesheet" type="text/css" href="css/rsManage.css">
	<link rel="stylesheet" type="text/css" href="css/table.css">
	<link rel="stylesheet" type="text/css" href="css/upload.css">
  </head>
  
  <body>
		
		<div id="upload">
			<h1>项目成员管理</h1>
			<table border="1" cellspacing="0" cellpadding="0" id="course">
				<caption>项目成员分组信息</caption>
				<col width="15%"/>
				<col width="15%" />
				<col width="15%" />
				<col width="25%" />
				<col width="10%" />
				<col width="10%" />
				<col width="10%" />
				<tr class="head"><th>学号</th><th>姓名 </th><th>角色</th><th>java1组</th></tr>
				<%
					try{
						HashMap<String, Object> user = (HashMap<String, Object>)session.getAttribute("user");
						Student stu = (Student)user.get("student");
						int sid  = stu.getSid();
						MemManageModule memMana = new MemManageModule();
						
						HashMap<String, String> memGroup= memMana.getMemgroupBySid(sid);
						
						
						int mem_gid = Integer.parseInt(memGroup.get("mem_gid")) ;
						String mem_gname = memGroup.get("mem_gname");
						
						ArrayList<HashMap<String, String>> members = memMana.getAllStusByMemgid(mem_gid);
					
						String sno = "";
						String sname = "";
						int role = 0;
						HashMap<String, String> temp = null;
						for(int i = 0;i < members.size();i++){
							temp = members.get(i);
							sid = Integer.parseInt(temp.get("sid"));
							sno = temp.get("sno");
							sname = temp.get("sname");
							role = Integer.parseInt(temp.get("role"));
							String roleString = "";
							if(role == 0){
								roleString = "组员";
							} else {
								roleString = "组长";
							}
							out.print("<tr class='cname'><td class='first'>"+sno+"</td><td>"+sname+"</td><td>"+roleString+"</td><td>"+mem_gname+"</td></tr>");
						}
					}catch(Exception e){}
				
				%>
				<!-- 
				<tr class="cname"><td class="first">100343094</td><td>姓名 </td><td>角色</td><td>java1组</td><td>设为组长</td><td>取消组长</td><td>删除</td></tr>
				-->
				
			</table>
			
		</div>
  </body>
</html>
