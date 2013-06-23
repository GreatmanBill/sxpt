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

public class MakePlanServlet extends HttpServlet {
	SpaceTeaModule spaceTea = null;

	public MakePlanServlet(){
		this.spaceTea = new SpaceTeaModule();
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
			String action = req.getParameter("action");
			if(action.equals("addTo")){
				int cid = Integer.parseInt(req.getParameter("cid"));
				int did = Integer.parseInt(req.getParameter("did"));
				int trainid = Integer.parseInt(req.getParameter("trainid"));
				//将该资源添加到该课程中
				int result = this.spaceTea.addCourse2Plan(trainid, cid);
				if(result > 0){
					req.getSession().setAttribute("message", "添加课程成功");
					req.getSession().setAttribute("url", "admin/viewPlan.jsp?trainid="+trainid+"&did="+did);
					resp.sendRedirect("success.jsp");
				} else {
					req.getSession().setAttribute("message", "添加课程失败");
					resp.sendRedirect("admin/viewPlan.jsp?trainid="+trainid+"&did="+did);
				}
			} else { //ajax异步请求
				int classid = 0;
				classid = Integer.parseInt(req.getParameter("classid"));
				ArrayList<HashMap<String, Object>> resources = this.spaceTea.getCoursesByClassid(classid);
				String courseOption = "";
				try{
					String cname = "";
					int cid = 0;
					HashMap<String, Object> temp = null;
					for(int i = 0;i < resources.size();i++){
						temp = resources.get(i);
						cname = temp.get("cname").toString();
						cid = Integer.parseInt(temp.get("cid").toString());
						courseOption += "<option value='"+cid+"'>"+cname+"</option>";
					}
					resp.setContentType("text/html; charset=utf-8");
					PrintWriter out = resp.getWriter(); 
					out.print(courseOption);
					System.out.println(courseOption);
				}catch(Exception e){}
			}
		}catch(NullPointerException e){
			e.printStackTrace();
		}
	}	
}
