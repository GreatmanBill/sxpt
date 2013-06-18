package com.sxpt.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sxpt.module.SxptModule;

/**
 * 该类用于对实训平台首页进行管理
 * @author zhang
 *
 */
public class LoginServlet extends HttpServlet{
	SxptModule sxptM = null;
	public LoginServlet(){
		this.sxptM = new SxptModule();
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
		String account = req.getParameter("username").trim();
		b = account.getBytes("ISO-8859-1");
		account = new String(b, "utf-8");
		
		String psw = req.getParameter("psw").trim();
		b = psw.getBytes("ISO-8859-1");
		psw = new String(b, "utf-8");
		
		int type = Integer.parseInt(req.getParameter("identity"));
		System.out.println("account:"+account + " psw:"+psw+" type:"+type);
		HashMap<String, Object> user = this.sxptM.login(account, psw, type);
		HttpSession session = req.getSession(true);
		if(user != null){			
			session.setAttribute("user", user);
			resp.sendRedirect("space.jsp");
		} else {
			session.setAttribute("message", "账号或密码错误");
			resp.sendRedirect("index.jsp");
		}
		
	}
}
