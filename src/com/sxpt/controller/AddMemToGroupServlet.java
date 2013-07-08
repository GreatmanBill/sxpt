package com.sxpt.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxpt.module.MemManageModule;

public class AddMemToGroupServlet extends HttpServlet {
	
	MemManageModule memMana = null;
	public AddMemToGroupServlet(){
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
		String mem_gname = "";
		try{
			mem_gid = 0;		//成员分组id
			int sid = 0;		//成员id
			
			mem_gid = Integer.parseInt(req.getParameter("mem_gid"));
			sid = Integer.parseInt(req.getParameter("sid"));
			mem_gname = req.getParameter("mem_gname");
			byte[] b = mem_gname.getBytes("ISO-8859-1");
			mem_gname = new String(b, "UTF-8");
			
			/**
			 * 添加成员到分组中只能检测同组的重名情况，不能检测跨组重名情况 
			 */
			int insertid = this.memMana.addMem2Group(mem_gid, sid);
			
			if(insertid > 0){
				 req.getSession().setAttribute("message", "添加成员成功");
				 req.getSession().setAttribute("url", "project/viewMemGroup.jsp?mem_gid="+mem_gid+"&mem_gname="+mem_gname);
				 resp.sendRedirect("success.jsp");
			 } else {
				 req.getSession().setAttribute("message", "添加成员失败");
				 resp.sendRedirect("project/viewMemGroup.jsp?mem_gid="+mem_gid+"&mem_gname="+mem_gname);
			 }
		}catch(Exception e){
			 req.getSession().setAttribute("message", "添加成员失败");
			 resp.sendRedirect("project/viewMemGroup.jsp?mem_gid="+mem_gid+"&mem_gname="+mem_gname);
		}
	}
   
}
