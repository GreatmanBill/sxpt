package com.sxpt.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxpt.module.SpaceTeaModule;

public class NewItemClassServlet extends HttpServlet {
	SpaceTeaModule spaceTM = null;
	public NewItemClassServlet(){
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
		try{
			String class_name = req.getParameter("class_name");
			b = class_name.getBytes("ISO-8859-1");
			class_name = new String(b, "UTF-8");
			
			int insertid = this.spaceTM.newItemClass(class_name);
			
			if(insertid > 0){
				 req.getSession().setAttribute("message", "新建课程分类成功");
				 req.getSession().setAttribute("url", "admin/newItemClass.jsp");
				 resp.sendRedirect("success.jsp");
			 } else {
				 req.getSession().setAttribute("message", "新建项目分类失败");
				 resp.sendRedirect("admin/newItemClass.jsp");
			 }
		}catch(Exception e){
			 req.getSession().setAttribute("message", "新建项目分类失败");
			 resp.sendRedirect("admin/newCourseClass.jsp");
		}
	}
}
