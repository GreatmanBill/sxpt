<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.sxpt.module.*" %>
<jsp:include page="../validate.jsp" flush="true" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>实训方案管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/space.css">
	<link rel="stylesheet" type="text/css" href="css/planManage.css">

  </head>
  
  <body>
		<div id="planManage">
			<h1>实训方案管理</h1>
			
			<div id="newPlan"><a href="admin/newPlan.jsp">新建实训方案</a></div>
			
			<table border='1' cellspacing='0' cellpadding='0'>
				<col width="40%"/>
				<col width="20%"/>
				<col width="10%"/>
				<col width="10%"/>
				<col width="10%"/>
				<col width="10%"/>
				<caption>实训方案管理信息</caption>
				<tr class="head"><th>实训方案管理名称</th><th>所属方向</th><th colspan="4">操作</th></tr>
				
				<%
					try{
						SpaceTeaModule spaceTM = new SpaceTeaModule();
						ArrayList<HashMap<String, Object>> plans = spaceTM.getAllPlan();
						
						HashMap<String, Object> temp = null;
						int trainid = 0;
						String train_name = "";
						int did = 0;
						String dname = "";
						System.out.println(plans.toString());
						for(int i = 0;i < plans.size();i++){
							temp = plans.get(i);
							
							trainid = Integer.parseInt(temp.get("trainid").toString());
							train_name = temp.get("train_name").toString();
							did = Integer.parseInt(temp.get("did").toString());
							
							if(did != 0){
								try{
								dname = temp.get("dname").toString();
								}catch(Exception e){
									did = 0;
									dname = "未分配";
								}
							} else {
								dname = "未分配";
							}
							out.print("<tr><td class='first'>"+train_name+"</td><td>"+dname+"</td><td><a href='admin/viewPlan.jsp?trainid="+trainid+"&did="+did+"'>查看</a></td><td>修改名称</td><td><a href='admin/distriPlan.jsp?trainid="+trainid+"&did="+did+"'>分配方案</a></td><td>删除</td></tr>");
							did = 0;
						}
					}catch(Exception e){
					}
				 %>
				
				
				
				<!-- <tr><td class="first">嵌入式方向实训方案</td><td>未分配</td><td>查看</td><td>修改名称</td><td>分配方案</td><td>删除</td></tr>-->
			</table>
		
		</div>
  </body>
</html>
