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
public class InSumServlet extends HttpServlet{
	SpaceTeaModule spacetM = null;
	public InSumServlet(){
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

			try {
				spacetM.InSumServlet();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				
			}
	
	}
}
