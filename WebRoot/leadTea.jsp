<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'leadTea.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
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
    		<form action="leadTea" enctype="multipart/form-data" method="post">
    			<label for="teachers">导入教师</label><input class="fileInput" type="file" name="teachers" id="teachers" value="选择文件" />
    			<input class="fileSub" type="submit" name = "sub" value="提交"/>
    		</form>
    	</div>
    	<div class = 'manualLead'>
    		<form action="leadTea" method="post">
    		<!--教师姓名 实训方向 性别 -->
    			<p><label for="tname">教师姓名</label><input class="text" type="text" name="tname" id="tname"/></p>
    			<p><label for="t_direct">实训方向</label><input  class="text" type="text" name="t_direct" id="t_direct"/></p>
    			<p><label for="tsex">性别</label>
    				<select name="tsex" id="tsex">
    					<option vlaue="男" selected="selected">男</option>
    					<option vlaue="女">女</option>
    				</select>
    			</p>
    			<p><label for="tpsw">初始密码</label><input  class="text" type="text" name="tpsw" id="tpsw" value="123456"/></p>
    			<p class="lastLead"><input class = 'sub' type="submit" name="sub" value="添加"/> <input class="res"type="reset" value="重填"/></p>
    		</form>
    	</div>
    </div>
  </body>
</html>
