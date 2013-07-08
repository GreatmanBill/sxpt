package com.sxpt.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxpt.module.MemManageModule;

public class NewPartServlet extends HttpServlet {
	
	MemManageModule memMana = null;
	public NewPartServlet(){
		this.memMana = new MemManageModule();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int mem_gid = 0;
		String part_name = "";
		try{
			mem_gid = Integer.parseInt(req.getParameter("mem_gid"));
			part_name = req.getParameter("part_name");
			byte[] b = part_name.getBytes("ISO-8859-1");
			part_name = new String(b, "UTF-8");
			
			/**
			 * 添加成员到分组中只能检测同组的重名情况，不能检测跨组重名情况 
			 */
			int insertid = this.memMana.newPart(part_name, mem_gid);
			
			if(insertid > 0){
				 req.getSession().setAttribute("message", "添加模块成功");
				 req.getSession().setAttribute("url", "project/taskDistribute.jsp");
				 resp.sendRedirect("success.jsp");
			 } else {
				 req.getSession().setAttribute("message", "添加模块失败");
				 resp.sendRedirect("project/taskDistribute.jsp");
			 }
		}catch(Exception e){
			 req.getSession().setAttribute("message", "添加模块失败");
			 resp.sendRedirect("project/taskDistribute.jsp");
		}
	}
   
}
