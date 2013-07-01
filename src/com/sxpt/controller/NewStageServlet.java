package com.sxpt.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxpt.module.SpaceTeaModule;

public class NewStageServlet extends HttpServlet {
	SpaceTeaModule spaceTM = null;
	public NewStageServlet(){
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
		int itemid = 0;
		String item_name = "";
		int classid = 0;
		String class_name = "";
		try{
			
			classid = Integer.parseInt(req.getParameter("classid"));
			
			class_name = req.getParameter("class_name");
			
			itemid = Integer.parseInt(req.getParameter("itemid"));
			
			item_name = req.getParameter("item_name");
			b = item_name.getBytes("ISO-8859-1");
			item_name = new String(b, "UTF-8");
			System.out.println("s item_name:"+item_name);
			
			String sta_name = req.getParameter("sta_name");
			b = sta_name.getBytes("ISO-8859-1");
			sta_name = new String(b, "UTF-8");
			
			String sta_profile = req.getParameter("sta_profile");
			b = sta_profile.getBytes("ISO-8859-1");
			sta_profile = new String(b, "UTF-8");
			
			int insertid = this.spaceTM.newStage(sta_name, sta_profile);
			
			
			if(insertid > 0){
				//将该项目阶段id添加到项目中去
				int res = this.spaceTM.addSta2Item(itemid, insertid);
				if(res > 0){
					req.getSession().setAttribute("message", "新建项目阶段成功");
					req.getSession().setAttribute("url", "admin/viewItem.jsp?itemid="+itemid+"&item_name="+item_name+"&classid="+classid+"&class_name="+class_name);
					resp.sendRedirect("success.jsp");
					return ;
				}
			 }
			
			 req.getSession().setAttribute("message", "新建项目阶段失败");
			 resp.sendRedirect("admin/viewItem.jsp?itemid="+itemid+"&item_name="+item_name+"&classid="+classid+"&class_name="+class_name);
		 
		}catch(Exception e){
			 req.getSession().setAttribute("message", "新建项目阶段失败");
			 resp.sendRedirect("admin/viewItem.jsp?itemid="+itemid+"&item_name="+item_name+"&classid="+classid+"&class_name="+class_name);
		}
	}
}
