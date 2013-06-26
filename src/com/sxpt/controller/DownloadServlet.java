package com.sxpt.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxpt.module.SpaceTeaModule;

public class DownloadServlet extends HttpServlet {

	SpaceTeaModule spaceTea = null;

	public DownloadServlet(){
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
		String rsurl = req.getParameter("rsurl");
		String filename = "/upload/"+rsurl;
		
	    String rsname = this.spaceTea.getRsnameByRsurl(rsurl);//下载文件时显示的文件保存名称
	    String filedisplay = URLEncoder.encode(rsname,"UTF-8");
	    resp.setContentType("application/x-download");//设置为下载application/x-download
	    resp.addHeader("Content-Disposition","attachment;filename=" + filedisplay);
	    
	    try
	    {
	        RequestDispatcher dis = req.getRequestDispatcher(filename);
	        if(dis!= null)
	        {
	            dis.forward(req,resp);
	        }
	        resp.flushBuffer();
	    }
	    catch(Exception e)
	    {
	        e.printStackTrace();
	    }
	    finally
	    {
	    
	    }
		
	}
	
}
