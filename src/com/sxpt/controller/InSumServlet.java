package com.sxpt.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Statement;

public class InSumServlet extends HttpServlet{
	private Statement statement;
	
	public void InSumServlet() throws SQLException{
		int Insum = 0;
	 	String sql = "select rid ,SUM(in_grade) as Insum from in_report GROUP BY rid";
		ResultSet rs = this.statement.executeQuery(sql);
		int rid = 0;
		int insum = 0;
		if(rs.next()){
			rid = rs.getInt("rid");
			insum = rs.getInt("Insum");
		}
		System.out.println("rid     :"+rid);
		System.out.println("Insum    :"+Insum);

		
		}
}
