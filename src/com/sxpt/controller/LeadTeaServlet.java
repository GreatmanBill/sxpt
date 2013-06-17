package com.sxpt.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxpt.classes.SmartUpload;
import com.sxpt.module.SpaceTeaModule;

public class LeadTeaServlet extends HttpServlet {
	
	SpaceTeaModule spaceTea = null;
	public LeadTeaServlet(){
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
			String sub = req.getParameter("sub");
			byte[] b = null;
			b = sub.getBytes("ISO-8859-1");
			sub = new String(b, "utf-8");

			String tname = req.getParameter("tname");
			b = tname.getBytes("ISO-8859-1");
			tname = new String(b,"utf-8");

			String tpsw = req.getParameter("tpsw");
			b = tpsw.getBytes("ISO-8859-1");
			tpsw = new String(b,"utf-8");

			String t_direct = req.getParameter("t_direct");
			b = t_direct.getBytes("ISO-8859-1");
			t_direct = new String(b,"utf-8");
			
			String tsex = req.getParameter("tsex");
			b = tsex.getBytes("ISO-8859-1");
			tsex = new String(b,"utf-8");
			
			//teachers 由（tname, staPsw, t_direct, tsex）多个组成
			String tea = "('"+tname+"','"+tpsw+"','"+t_direct+"','"+tsex+"')";
			int insertid = this.spaceTea.leadInTea(tea);
			if(insertid > 0){
				 req.getSession().setAttribute("message", "教师信息成功");
				 req.getSession().setAttribute("url", "leadTea.jsp");
				 resp.sendRedirect("success.jsp");
			 } else if(insertid == -10) {
				 req.getSession().setAttribute("message", "请检查，姓名不能重复");
				 resp.sendRedirect("leadTea.jsp");
			 } else {
				 req.getSession().setAttribute("message", "添加教师信息失败");
				 resp.sendRedirect("leadTea.jsp");
			 }
			System.out.println(sub + " "+tname +" "+tpsw+" "+t_direct+" "+tsex); 
		}catch(NullPointerException e){
			upload(req, resp);
		}
	}

public void upload(HttpServletRequest req, HttpServletResponse resp){
		
		// 新建一个SmartUpload对象
		SmartUpload su = new SmartUpload();
		// 上传初始化
		//ServletConfig config = new ServletConfig();
		try {			
			su.initialize(this.getServletConfig(), req, resp);
			// 上传文件
			su.upload();
			// 将上传文件全部保存到指定目录，需要先在在Web应用的根目录下，创建一个upload目录
			su.save("/upload");
			com.sxpt.classes.File file = su.getFiles().getFile(0);
			
			//得到文件名和文件所在的路径
			String fileName = file.getFileName();			
			fileName = req.getSession().getServletContext().getRealPath("") + "/upload/" + fileName; 
			
			System.out.println(fileName);
			
			String str  = null; 
			
			//输入流和用utf-8格式读取
			FileInputStream in = new FileInputStream(fileName);
			BufferedReader br=new BufferedReader(new InputStreamReader(in, "UTF-8"));
			//teachers 由（tname, staPsw, t_direct, tsex）多个组成
						
			StringBuffer strbuf = new StringBuffer();
			boolean flag = true;
			br.readLine();//第一条是头，跳过
			String value = null;
			while((str = br.readLine()) != null){
				String[] tea = str.split(",");
			    System.out.println(tea[0]+tea[1]+tea[2]);
				if(flag){
					flag = false;
					value = "('"+tea[0]+"','123456','"+tea[1]+"','"+tea[2]+"')";
					strbuf.append(value);
					continue;
				}
				
			    value = ",('"+tea[0]+"','123456','"+tea[1]+"','"+tea[2]+"')";
			    strbuf.append(value);
			}
			
			String teachers = new String(strbuf);
			 System.out.println(teachers);
			 int insertid = this.spaceTea.leadInTea(teachers);
			 if(insertid > 0){
				 req.getSession().setAttribute("message", "导入教师信息成功");
				 req.getSession().setAttribute("url", "leadTea.jsp");
				 resp.sendRedirect("leadTea.jsp");
			 } else if(insertid == -10) {
				 req.getSession().setAttribute("message", "请检查，姓名不能重复");
				 resp.sendRedirect("leadTea.jsp");
			 } else {
				 req.getSession().setAttribute("message", "文件格式错误");
				 resp.sendRedirect("leadTea.jsp");
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			req.getSession().setAttribute("message", "文件格式错误");
			try {
				resp.sendRedirect("leadTea.jsp");
			} catch (IOException e1) {
				// TODO Auto-generated catch block 
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
	}
}
