package com.sxpt.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxpt.classes.Student;
import com.sxpt.classes.Teacher;
import com.sxpt.module.MemManageModule;

public class NewMemGroupServlet extends HttpServlet{
	MemManageModule memMana = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try{
			int did = 0;
			int tid = 0;
			String mem_gname = "";
			
			did = Integer.parseInt(req.getParameter("did"));
			tid = Integer.parseInt(req.getParameter("tid"));
			mem_gname = req.getParameter("mem_gname");
			byte[] b = mem_gname.getBytes("ISO-8859-1");
			mem_gname = new String(b, "UTF-8");
			
			this.memMana = new MemManageModule();
			
			int insertid = this.memMana.newMemGroup(mem_gname, tid, did);
			
			if(insertid > 0){				 
				 req.getSession().setAttribute("message", "添加分组成功");
				 req.getSession().setAttribute("url", "project/memManage.jsp");
				 resp.sendRedirect("success.jsp");
			 } else {
				 req.getSession().setAttribute("message", "添加分组失败");
				 resp.sendRedirect("project/memManage.jsp");
			 }
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
