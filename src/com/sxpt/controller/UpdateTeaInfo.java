package com.sxpt.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sxpt.module.SpaceStuModule;
import com.sxpt.module.SpaceTeaModule;
import com.sxpt.module.SxptModule;
	
/**
 * 该类用于修改教师个人信息
 * @author zhanglz
 *
 */
public class UpdateTeaInfo extends HttpServlet{
	String sql;
	SpaceTeaModule spaceTM = null;
	public UpdateTeaInfo(){
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

		byte b[];
		
		String ttid = req.getParameter("tid");
		b = ttid.getBytes("ISO-8859-1");
		ttid = new String(b);
		
		int tid = Integer.parseInt(ttid);
		
		String tpsw = req.getParameter("tpsw");
		b = tpsw.getBytes("ISO-8859-1");
		tpsw = new String(b);
			System.out.println("spsw:"+tpsw);		
		try {
			int result = this.spaceTM.modifyTeaInfo(tid, tpsw);
			if(result == 1){
				req.getSession().setAttribute("message", "添加成功");
				req.getSession().setAttribute("url", "space.jsp");
				resp.sendRedirect("success.jsp");
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
