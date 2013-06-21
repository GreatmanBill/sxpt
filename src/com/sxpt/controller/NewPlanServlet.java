package com.sxpt.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sxpt.module.SpaceTeaModule;

public class NewPlanServlet extends HttpServlet{
	SpaceTeaModule spaceTM = null;
	public NewPlanServlet(){
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
		
		try{
			byte b[];
			String train_name = req.getParameter("train_name").trim();
			b = train_name.getBytes("ISO-8859-1");
			train_name = new String(b, "utf-8");
			System.out.println("train_name:"+train_name);
			int insertid = this.spaceTM.newPlan(train_name);
			
			if(insertid > 0){
				 req.getSession().setAttribute("message", "新建实训方案成功");
				 req.getSession().setAttribute("url", "admin/newPlan.jsp");
				 resp.sendRedirect("success.jsp");
			 } else {
				 req.getSession().setAttribute("message", "新建实训方案失败");
				 resp.sendRedirect("admin/newPlan.jsp");
			 }
		}catch(Exception e){
			 req.getSession().setAttribute("message", "新建实训方案失败");
			 resp.sendRedirect("admin/newPlan.jsp");
		}
		
	}
}
