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
 * 该类用于修改学生个人信息
 * @author zhanglz
 *
 */
public class UpdateStuInfo extends HttpServlet{
	String sql;
	SpaceStuModule spaceSM = null;
	public UpdateStuInfo(){
		this.spaceSM = new SpaceStuModule();

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
		
		String sno = req.getParameter("sno");
		b = sno.getBytes("ISO-8859-1");
		sno = new String(b);
		
		String spsw = req.getParameter("spsw");
		b = spsw.getBytes("ISO-8859-1");
		spsw = new String(b);
	
		String smail = req.getParameter("smail");
		b = smail.getBytes("ISO-8859-1");
		smail = new String(b);
		
		System.out.println("spsw:"+spsw + " smail:"+smail );		
		try {
			int result = this.spaceSM.modifyStuInfo(sno,spsw, smail);
			System.out.println("result+++:"+result);
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
