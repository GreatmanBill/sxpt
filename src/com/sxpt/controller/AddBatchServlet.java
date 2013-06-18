package com.sxpt.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxpt.module.SpaceTeaModule;

public class AddBatchServlet extends HttpServlet {
	
	SpaceTeaModule spaceTM = null;
	public AddBatchServlet(){
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
			String bname = req.getParameter("bname");
			b = bname.getBytes("ISO-8859-1");
			bname = new String(b, "UTF-8");
			
			int insertid = this.spaceTM.newBatch(bname);
			
			if(insertid > 0){
				 req.getSession().setAttribute("message", "新建批次成功");
				 req.getSession().setAttribute("url", "admin/newBatch.jsp");
				 resp.sendRedirect("success.jsp");
			 } else {
				 req.getSession().setAttribute("message", "添加教师信息失败");
				 resp.sendRedirect("admin/newBatch.jsp");
			 }
		}catch(Exception e){
			 req.getSession().setAttribute("message", "添加教师信息失败");
			 resp.sendRedirect("admin/newBatch.jsp");
		}
	}
   
}
