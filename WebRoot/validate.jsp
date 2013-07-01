<%@ page import="java.util.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	if(session.getAttribute("user") == null){
		out.print("<script>alert('请登录');window.location='/sxpt/index.jsp'</script>");
	}	
%>