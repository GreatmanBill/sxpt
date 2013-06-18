package com.sxpt.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxpt.module.SpaceTeaModule;

public class AddNewsServlet extends HttpServlet {
	private SpaceTeaModule spaceTM = null;
	public AddNewsServlet() {
		super();
		this.spaceTM = new SpaceTeaModule();
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		byte[] b = null;
		String info_title = request.getParameter("info_title");
		b = info_title.getBytes("ISO-8859-1");
		info_title = new String(b, "utf-8");
		
		int info_uid = Integer.parseInt(request.getParameter("info_uid"));

		String info_name = request.getParameter("info_name");
		b = info_name.getBytes("ISO-8859-1");
		info_name = new String(b, "utf-8");
		
		String info_con = request.getParameter("info_con");
		b = info_con.getBytes("ISO-8859-1");
		info_con = new String(b, "utf-8");
		

		int type = Integer.parseInt(request.getParameter("type"));
		
		long ctime = new Date().getTime();;
		
		int insertid = this.spaceTM.addNews(info_title, info_uid, info_name, info_con, ctime, type);
		
		if(insertid > 0){
			request.getSession().setAttribute("message", "添加文章成功");
			request.getSession().setAttribute("url", "addNews.jsp");
			response.sendRedirect("success.jsp");
		 } else {
			 request.getSession().setAttribute("message", "添加文章失败");
			 response.sendRedirect("addNews.jsp");
		 }
	
		
//		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
//		out
//				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
//		out.println("<HTML>");
//		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
//		out.println("  <BODY>");
//		out.print("    This is ");
//		out.print(this.getClass());
//		out.println(", using the POST method");
//		out.println("  </BODY>");
//		out.println("</HTML>");
//		out.flush();
//		out.close();
		
		
	}

}
