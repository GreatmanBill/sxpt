<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'updateInSum.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  	<script type="text/javascript">
	//	function updateInSum(){
	//        String sql = "select rid ,SUM(in_grade) as Insum from in_report GROUP BY rid;
	//		ResultSet rs = this.statement.executeQuery(sql);
	//		int rid = 0;
	//		int insum = 0;
	//		if(rs.next()){
	//			rid = rs.getInt("rid");
	//			insum = rs.getInt("Insum");
	//		}
	//		System.out.println("rid     :"+rid);
	//		System.out.println("Insum    :"+Insum);
//			 String sql2 = "update report set out_sum = '"+outsum+"'"+"where rid = '"+rid+"'";
//			 result = this.statement.executeUpdate(sql2);
//   		 System.out.println(result);
//			 return result; 
//		}
//	</script>
</head>

  <body>
   <a href = "inSumServlet.java">updateInSum</a>
  </body>
</html>
