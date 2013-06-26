<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="../validate.jsp" flush="true" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新建实训方向</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/space.css">
	<link rel="stylesheet" type="text/css" href="css/newDirect.css">

  </head>
  
  <body>
		<div id="newDirect">
			
			<%
				int bid = Integer.parseInt(request.getParameter("bid"));
				String bname = request.getParameter("bname");
				byte[] b = null;
				b = bname.getBytes("ISO-8859-1");
				bname = new String(b,"utf-8");
				
				if(session.getAttribute("message") != null){
					out.print("<div>"+session.getAttribute("message")+"</div>");
					session.setAttribute("message", null);
				}
			 %>
			<h2>新建实训方向</h2>
			<h3>【实训批次】<%=bname %></h3>
			<form action="newDirect" method="post">
				<input type="hidden" name="bid" value="<%=bid %>"/>
				<input type="hidden" name="bname" value="<%=bname %>"/>
				<p>
					<label>方向名</label><input type="text" name="dname"/>
				</p>
				<p>
					<label>方向简介</label><textarea name="dprofile" cols="80" rows="10"></textarea>
				</p>
				<p><input class= "sub"type="submit" name="sub" value="新建"/></p>
			</form>
		</div>
  </body>
</html>
