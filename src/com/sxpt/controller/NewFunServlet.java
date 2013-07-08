package com.sxpt.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxpt.module.MemManageModule;

public class NewFunServlet extends HttpServlet {
	
	MemManageModule memMana = null;
	public NewFunServlet(){
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
		String fun_name = "";
		int partid = 0;
		int day = 0;
		int sid = 0;
		try{
			fun_name = req.getParameter("fun_name");
			byte[] b = fun_name.getBytes("ISO-8859-1");
			fun_name = new String(b, "UTF-8");
			
			partid = Integer.parseInt(req.getParameter("partid"));
			day = Integer.parseInt(req.getParameter("day"));
			sid = Integer.parseInt(req.getParameter("sid"));
			
			System.out.println(partid+day+sid+fun_name);
			/**
			 * 添加成员到分组中只能检测同组的重名情况，不能检测跨组重名情况 
			 */
			int insertid = this.memMana.newFun(fun_name, partid, day, sid);
			
			if(insertid > 0){
				 req.getSession().setAttribute("message", "添加模块成功");
				 req.getSession().setAttribute("url", "project/taskDistribute.jsp");
				 resp.sendRedirect("success.jsp");
			 } else {
				 req.getSession().setAttribute("message", "添加模块失败");
				 resp.sendRedirect("project/taskDistribute.jsp");
			 }
		}catch(Exception e){
			e.printStackTrace();
			 req.getSession().setAttribute("message", "添加模块失败");
			 resp.sendRedirect("project/taskDistribute.jsp");
		}
	}
   
}
