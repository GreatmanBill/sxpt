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

public class AddItemToPlanServlet extends HttpServlet {
	SpaceTeaModule spaceTea = null;

	public AddItemToPlanServlet(){
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
				int itemid = Integer.parseInt(req.getParameter("itemid"));
				int did = Integer.parseInt(req.getParameter("did"));
				int trainid = Integer.parseInt(req.getParameter("trainid"));
				//将该资源添加到该课程中
				int result = this.spaceTea.addItem2Plan(trainid, itemid);
				if(result > 0){
					req.getSession().setAttribute("message", "添加项目成功");
					req.getSession().setAttribute("url", "admin/viewPlan.jsp?trainid="+trainid+"&did="+did);
					resp.sendRedirect("success.jsp");
				} else {
					req.getSession().setAttribute("message", "添加项目失败");
					resp.sendRedirect("admin/viewPlan.jsp?trainid="+trainid+"&did="+did);
				}
			} else { //ajax异步请求
				int classid = 0;
				classid = Integer.parseInt(req.getParameter("classid"));
				ArrayList<HashMap<String, Object>> items = this.spaceTea.getItemesByClassid(classid);
				String itemsOption = "";
				try{
					String item_name = "";
					int itemid = 0;
					HashMap<String, Object> temp = null;
					for(int i = 0;i < items.size();i++){
						temp = items.get(i);
						item_name = temp.get("item_name").toString();
						itemid = Integer.parseInt(temp.get("itemid").toString());
						itemsOption += "<option value='"+itemid+"'>"+item_name+"</option>";
					}
					resp.setContentType("text/html; charset=utf-8");
					PrintWriter out = resp.getWriter(); 
					out.print(itemsOption);
					System.out.println(itemsOption);
				}catch(Exception e){}
			}
		}catch(NullPointerException e){
			e.printStackTrace();
		}
	}	
}
