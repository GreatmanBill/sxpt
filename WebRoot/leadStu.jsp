<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.sxpt.module.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	SpaceTeaModule spaceTea = new SpaceTeaModule();	
	ArrayList<HashMap<String, Object>> batchs = spaceTea.getAllBatch();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>导入学生信息</title>
	<link rel="stylesheet" type="text/css" href="css/space.css">
	<link rel="stylesheet" type="text/css" href="css/lead.css">


  </head>
  
  <body>
    <div id="leadStu">
    	<div class = "fileLead">
    		<%
    			if(session.getAttribute("message") != null){
    				out.print("<p>"+session.getAttribute("message")+"</p>");
    				session.setAttribute("message",null); 
    			}
    		%>
    		<form action="leadStu" enctype="multipart/form-data" method="post">
    			<label for="bid">实训批次</label>
    			<select name="bid" id="bid">
    				<%
    					out.print("<option value='-1'>请选择批次</option>");
    					for(int i = 0;i < batchs.size();i++){
    						HashMap<String, Object> batch = batchs.get(i);
    						int bid = Integer.parseInt(batch.get("bid").toString());
    						String bname = batch.get("bname").toString();
    						out.print("<option value='"+bid+"'>"+bname+"</option>");
    						
    					}
    					
    				%>
    			</select>
    			<label for="students">导入学生</label><input class="fileInput" type="file" name="students" id="students" value="选择文件" />
    			<input class="fileSub" type="submit" name = "sub" value="提交"/>
    		</form>
    	</div>
    	<div class = 'manualLead'>
    		<form action="leadStu" method="post">
    		<!-- 学号，姓名，班级，初始密码 -->
    			<p><label for="bid">实训批次</label>
    			<select name="bid" id="bid">
    				<%
   						out.print("<option value='-1'>请选择批次</option>");
    					for(int i = 0;i < batchs.size();i++){
    						HashMap<String, Object> batch = batchs.get(i);
    						int bid = Integer.parseInt(batch.get("bid").toString());
    						String bname = batch.get("bname").toString();
    						out.print("<option value='"+bid+"'>"+bname+"</option>");
    						
    					}
    					
    				%>
    			</select></p>
    			<p><label for="sno">学号</label><input class="text" type="text" name="sno" id="sno"/></p>
    			<p><label for="sname">姓名</label><input  class="text" type="text" name="sname" id="sname"/></p>
    			<p><label for="sclass">班级</label><input  class="text" type="text" name="sclass" id="sclass"/></p>
    			<p><label for="spsw">初始密码</label><input  class="text" type="text" name="spsw" id="spsw" value="123456"/></p>
    			<p class="lastLead"><input class = 'sub' type="submit" name="sub" value="添加"/> <input class="res"type="reset" value="重填"/></p>
    		</form>
    	</div>
    </div>
  </body>
</html>
