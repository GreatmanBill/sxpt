package com.sxpt.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxpt.module.SpaceTeaModule;

public class NewRsClassServlet extends HttpServlet {
	SpaceTeaModule spaceTM = null;
	public NewRsClassServlet(){
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
			
			int insertid = this.spaceTM.newRsClass(class_name);
			
			if(insertid > 0){
				 req.getSession().setAttribute("message", "新建资源分类成功");
				 req.getSession().setAttribute("url", "admin/upload.jsp");
				 resp.sendRedirect("success.jsp");
			 } else {
				 req.getSession().setAttribute("message", "新建资源分类失败");
				 resp.sendRedirect("admin/upload.jsp");
			 }
		}catch(Exception e){
			 req.getSession().setAttribute("message", "新建资源分类失败");
			 resp.sendRedirect("admin/upload.jsp");
		}
	}
}
