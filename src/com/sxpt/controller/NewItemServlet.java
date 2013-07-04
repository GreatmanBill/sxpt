package com.sxpt.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxpt.module.SpaceTeaModule;

public class NewItemServlet extends HttpServlet {
	SpaceTeaModule spaceTM = null;
	public NewItemServlet(){
		this.spaceTM = new SpaceTeaModule();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		byte[] b = null; 
		int classid = 0;
		String class_name = "";
		try{
			classid = Integer.parseInt(req.getParameter("classid"));
			
			class_name = req.getParameter("class_name");
			b = class_name.getBytes("ISO-8859-1");
			class_name = new String(b, "UTF-8");
			System.out.println("s class_name:"+class_name);
			class_name = URLEncoder.encode(URLEncoder.encode(class_name));
			
			String item_name = req.getParameter("item_name");
			b = item_name.getBytes("ISO-8859-1");
			item_name = new String(b, "UTF-8");
			
			String item_profile = req.getParameter("item_profile");
			b = item_profile.getBytes("ISO-8859-1");
			item_profile = new String(b, "UTF-8");
			
			int insertid = this.spaceTM.newItem(item_name, item_profile, classid);
			
			if(insertid > 0){//http://localhost:8080/sxpt/admin/viewItemClass.jsp?classid=1&class_name=java%25CA%25B5%25D1%25B5%25CF%25EE%25C4%25BF
				 req.getSession().setAttribute("message", "新建项目成功");
				 req.getSession().setAttribute("url", "admin/viewItemClass.jsp?classid="+classid+"&class_name="+class_name);
				 resp.sendRedirect("success.jsp");
			 } else {
				 req.getSession().setAttribute("message", "新建项目失败");
				 resp.sendRedirect("admin/viewItemClass.jsp?classid="+classid+"&class_name="+class_name);
			 }
		}catch(Exception e){
			 req.getSession().setAttribute("message", "新建项目失败");
			 resp.sendRedirect("admin/viewItemClass.jsp?classid="+classid+"&class_name="+class_name);
		}
	}
}
