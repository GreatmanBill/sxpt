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

import com.sxpt.module.SpaceTeaModule;
import com.sxpt.module.SxptModule;
	
/**
 * 该类用于教师针对学生课内实训的成绩的添加
 * @author zhanglz
 *
 */
public class InReportServlet extends HttpServlet{
	String sqlstr;
	SpaceTeaModule spacetM = null;
	public InReportServlet(){
		this.spacetM = new SpaceTeaModule();

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
		String sname = req.getParameter("sname");
		b = sname.getBytes("ISO-8859-1");
		sname = new String(b);
	
		String sno = req.getParameter("sno");
		b = sno.getBytes("ISO-8859-1");
		sno = new String(b);
		
		String intimes = req.getParameter("In_times");
		b = intimes.getBytes("ISO-8859-1");
		intimes = new String(b);
		
		String ingrade = req.getParameter("In_grade");
		b = ingrade.getBytes("ISO-8859-1");
		ingrade = new String(b);
		
		System.out.println("sname:"+sname + " sno:"+sno+" intimes:"+intimes+" ingrade:"+ingrade );
		
		System.out.println("intimes");
		int times = Integer.parseInt(intimes);
		int grade = Integer.parseInt(ingrade);
		System.out.println("intimes"+times+"ingrade"+grade);
		try {
			int result = this.spacetM.modifyReport(sname, sno, times, grade);
			System.out.println("result+++:"+result);
			if(result == 1){
				req.getSession().setAttribute("message", "添加成功");
				req.getSession().setAttribute("url", "updateInReport.jsp");
				resp.sendRedirect("success.jsp");
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
//		RequestDispatcher dispatcher = req.getRequestDispatcher("");
//		dispatcher.forward(req,resp);
//			sqlstr = 
//			
//			if(user != null){			
//				session.setAttribute("user", user);
//				resp.sendRedirect("space.jsp");
//			} else {
//				session.setAttribute("message", "账号或密码错误");
//				resp.sendRedirect("index.jsp");
//			}
		
	}
}
