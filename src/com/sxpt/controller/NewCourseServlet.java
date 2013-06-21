package com.sxpt.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxpt.module.SpaceTeaModule;

public class NewCourseServlet extends HttpServlet {
	SpaceTeaModule spaceTM = null;
	public NewCourseServlet(){
		this.spaceTM = new SpaceTeaModule();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		byte[] b = null; 
		//课程分类id
		int classid = Integer.parseInt(req.getParameter("classid"));
		try{
			
			String cname = req.getParameter("cname");
			b = cname.getBytes("ISO-8859-1");
			cname = new String(b, "UTF-8");
			
			String cprofile = req.getParameter("cprofile");
			b = cprofile.getBytes("ISO-8859-1");
			cprofile = new String(b, "UTF-8");
			
			
			int insertid = this.spaceTM.newCourse(classid, cname, cprofile);
			
			if(insertid > 0){
				 req.getSession().setAttribute("message", "新建课程成功");
				 req.getSession().setAttribute("url", "admin/viewCourseClass.jsp?classid="+classid);
				 resp.sendRedirect("success.jsp");
			 } else {
				 req.getSession().setAttribute("message", "新建课程失败");
				 resp.sendRedirect("admin/viewCourseClass.jsp?classid="+classid);
			 }
		}catch(Exception e){
			 req.getSession().setAttribute("message", "新建课程失败");
			 resp.sendRedirect("admin/viewCourseClass.jsp?classid="+classid);
		}
	}
}
