package com.sxpt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxpt.module.SpaceTeaModule;

public class DistriPlanServlet extends HttpServlet {
	SpaceTeaModule spaceTM = null;
	public DistriPlanServlet(){
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
		String action = req.getParameter("action");
		//ajax异步请求该批次下的方向信息
		if(action.equals("getDirects")){
			 int bid = Integer.parseInt(req.getParameter("bid"));
			 ArrayList<HashMap<String, Object>> t_directs = this.spaceTM.getDirectssByBid(bid);
			 
			 String output = "";
			 
			 HashMap<String, Object> temp = null;
			 for(int i = 0;i < t_directs.size();i++){
				 temp = t_directs.get(i);
				 int did = Integer.parseInt(temp.get("did").toString());
				 String dname = temp.get("dname").toString();
				 output += "<option value='"+did+"'>"+dname+"</option>";					 
			 }
			 
			 if(t_directs.size() == 0){
				 output = "<option value='-1'>没有实训方向</option>";
				 
			 }
			 resp.setContentType("text/html; charset=utf-8");
			 
			 PrintWriter out = resp.getWriter(); 
			 
			 System.out.println(t_directs.toString());
			 System.out.println(output);
			 
			 out.print(output);
		} else { // 分配方案
			int did = Integer.parseInt(req.getParameter("did"));
			int trainid = Integer.parseInt(req.getParameter("trainid"));
			
			int affectLines = this.spaceTM.updateDidOfPlan(did, trainid);
			
			if(affectLines > 0){
				 req.getSession().setAttribute("message", "分配方案成功");
				 req.getSession().setAttribute("url", "admin/distriPlan.jsp?trainid="+trainid+"&did="+did);
				 resp.sendRedirect("success.jsp");
			 } else {
				 req.getSession().setAttribute("message", "分配方案失败");
				 resp.sendRedirect("admin/distriPlan.jsp?trainid="+trainid+"&did="+did);
			 }
		}
	}
}
