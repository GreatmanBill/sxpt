<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.sxpt.module.*" %>
<%@ page import="java.net.*" %>
<%@ page import="com.sxpt.classes.*" %>
<jsp:include page="../validate.jsp" flush="true" />

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	int mem_gid = 0;		//成员分组id
	String mem_gname = "";	//分组名
	int tid = 0;			//教师id
	int did = 0;			//教师方向id
	String stuOption = "";
	MemManageModule memMan = new MemManageModule();
	try{
		mem_gid = Integer.parseInt(request.getParameter("mem_gid"));
		mem_gname = request.getParameter("mem_gname");
		byte[] b = mem_gname.getBytes("ISO-8859-1");
		mem_gname = new String(b, "UTF-8");
		
		HashMap<String, Object> user = (HashMap<String, Object>)session.getAttribute("user");
		Teacher tea = (Teacher)user.get("teacher");
		tid = tea.getTid();
		String t_direct = tea.getT_direct();

		ArrayList<HashMap<String,String>> students = memMan.getAllStusByTid(tid);
		
		HashMap<String,String> temp = null;
		
		int sid = 0;
		String sname = "";
		for(int i = 0; i < students.size();i++){
			temp = students.get(i);
			sid = Integer.parseInt(temp.get("sid"));
			sname = temp.get("sname");
			
			stuOption += "<option value='"+sid+"'>"+sname+"</option>";
		}
		
		
	}catch(Exception  e){
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
				<tr class="head"><th>学号</th><th>姓名 </th><th>角色</th><th>java1组</th><th colspan="3">操作</th></tr>
				<%
				
					MemManageModule memMana = new MemManageModule();
					ArrayList<HashMap<String, String>> members = memMana.getAllStusByMemgid(mem_gid);
					try{
						
						int sid = 0;
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
							out.print("<tr class='cname'><td class='first'>"+sno+"</td><td>"+sname+"</td><td>"+roleString+"</td><td>"+mem_gname+"</td><td><a href='setLeader?sid="+sid+"&mem_gid="+mem_gid+"&mem_gname="+mem_gname+"'>设为组长</a></td><td>取消组长</td><td>删除</td></tr>");
						}
					}catch(Exception e){}
				
				%>
				<!-- 
				<tr class="cname"><td class="first">100343094</td><td>姓名 </td><td>角色</td><td>java1组</td><td>设为组长</td><td>取消组长</td><td>删除</td></tr>
				-->
				
			</table>
			
			<div id="newRsClass">
				<fieldset>
					<legend>添加成员到分组</legend>
					<form action="addMemToGroup" method="post">
						<% 
							if(session.getAttribute("message") != null){
								out.print("<p style='color:red'>"+session.getAttribute("message")+"</p>");
								session.setAttribute("message", null);
							}
						%>
						<input type="hidden" name="mem_gid" value="<%=mem_gid %>"/>
						<input type="hidden" name="did" value="<%=did %>"/>
						<input type="hidden" name="mem_gname" value="<%=mem_gname %>"/>
						<p><label>学生姓名</label><select style="display:inline-block; width:100px" name="sid"><%=stuOption %></select></p>
						<p><input style="margin-left:-95px;" class='but' type="submit" name="sub" value="添加" /></p>
					</form>
				</fieldset>
			</div>
		</div>
  </body>
</html>
