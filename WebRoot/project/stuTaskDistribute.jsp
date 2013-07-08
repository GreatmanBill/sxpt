<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.sxpt.module.*" %>
<%@ page import="java.net.*" %>
<%@ page import="com.sxpt.classes.*" %>
<jsp:include page="../validate.jsp" flush="true" />

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	int sid = 0;
	int mem_gid = 0;
	MemManageModule memMana = new MemManageModule();
	
	//成员的option
	String memberOption = "";
	
	//模块的option
	String partOption = ""; 
	
	try{
		HashMap<String, Object> user = (HashMap<String, Object>)session.getAttribute("user");
		Student stu = (Student)user.get("student");
		sid = stu.getSid();
		HashMap<String, String> memGroup = memMana.getMemGroupBySid(sid);
		mem_gid = Integer.parseInt(memGroup.get("mem_gid"));
	}catch(Exception e){
		e.printStackTrace();
	}
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
	<link rel="stylesheet" type="text/css" href="css/taskDistribute.css">
  </head>
  
  <body>
		
		<div id="upload">
			<h1>项目任务分配</h1>
			<table border="1" cellspacing="0" cellpadding="0" id="course">
				<caption>项目功能模块</caption>
				<col width="30%"/>
				<col width="30%" />
				<col width="30%" />
				<tr class="head"><th>功能点</th><th>开发人</th><th>计划时间(天)</th></tr>
				<%
				
					ArrayList<HashMap<String, String>> partSAndFuns = memMana.getPartsAndFunsByMemgid(mem_gid);
				
					System.out.println(partSAndFuns);
					try{
						int partid = 0;
						System.out.println("size:"+partSAndFuns.size());
						HashMap<String, String> temp = null;
						for(int i = 0;i < partSAndFuns.size();i++){
							temp = partSAndFuns.get(i);
							int nPartid = Integer.parseInt(temp.get("partid"));
							String part_name = temp.get("part_name");
							int funid = Integer.parseInt(temp.get("funid"));
							String fun_name = temp.get("fun_name");
							int day = Integer.parseInt(temp.get("day"));
							int ssid = Integer.parseInt(temp.get("sid"));
							String sname = temp.get("sname");	
							//输出一个头
							if(partid == 0){
								out.print("<tr class='cname' style='background:#eee;'><td class='first' colspan='3'>"+part_name+"</td></tr>");
								partid = nPartid;
							}
							
							//当时下一模块时又输出一个头
							if(partid != nPartid){
								out.print("<tr class='cname' style='background:#eee;'><td class='first' colspan='3'>"+part_name+"</td></tr>");
								partid = nPartid;
							}
							System.out.println("22222222222222222222222222");
							out.print("<tr class='cname' style='line-height:18px;'><td class='first'>"+fun_name+"</td><td>"+sname+"</td><td>"+day+"</td></tr>");
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				%>
				<!--  
				<tr class="cname" style="background:#eee;"><td class="first" colspan="3">用户模块 </td></tr>
				<tr class="cname"><td class="first">登录</td><td>小兵</td><td>20</td></tr>
				-->
			</table>
		</div>
  </body>
</html>
