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
 * 该类用于教师针对学生课外实训的成绩的添加
 * @author zhanglz
 *
 */
public class OutReportServlet extends HttpServlet{
	String sqlstr;
	SpaceTeaModule spacetM = null;
	public OutReportServlet(){
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
		
		String outsum = req.getParameter("outsum");
		b = outsum.getBytes("ISO-8859-1");
		outsum = new String(b);
		
		System.out.println("sname:"+sname + " sno:"+sno+" outsum:"+outsum);

		int sum = Integer.parseInt(outsum);
		
		System.out.println("outsum:   "+outsum);
		
		try {
			int result = this.spacetM.modifyOutSum(sname, sno, sum);
			System.out.println("result+++:"+result);
			if(result == 1){
				req.getSession().setAttribute("message", "添加成功");
				req.getSession().setAttribute("url", "updateOutSum.jsp");
				resp.sendRedirect("success.jsp");
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
