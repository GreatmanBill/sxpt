package com.sxpt.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxpt.module.SpaceTeaModule;

public class NewDirectServlet extends HttpServlet{

	SpaceTeaModule spaceTM = null;
	public NewDirectServlet(){
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
		int bid = Integer.parseInt(req.getParameter("bid"));
		
		String bname = req.getParameter("bname");
		b = bname.getBytes("ISO-8859-1");
		bname = new String(b, "UTF-8");
		try{
			String dname = req.getParameter("dname");
			b = dname.getBytes("ISO-8859-1");
			dname = new String(b, "UTF-8");
			
			String dprofile = req.getParameter("dprofile");
			
			b = dprofile.getBytes("ISO-8859-1");
			
			dprofile = new String(b, "UTF-8");
			
			int insertid = this.spaceTM.newTrain_dr(bid, dname, dprofile);
			
			if(insertid > 0){
				 req.getSession().setAttribute("message", "新建实训方向成功");
				 req.getSession().setAttribute("url", "admin/newDirect.jsp?bid="+bid+"&bname="+bname);
				 resp.sendRedirect("success.jsp");
			 } else {
				 req.getSession().setAttribute("message", "新建实训方向失败");
				 resp.sendRedirect("admin/newDirect.jsp?bid="+bid+"&bname="+bname);
			 }
		}catch(Exception e){
			 req.getSession().setAttribute("message", "新建实训方向失败");
			 resp.sendRedirect("admin/newDirect.jsp?bid="+bid+"&bname="+bname);
		}
		
	}

}
