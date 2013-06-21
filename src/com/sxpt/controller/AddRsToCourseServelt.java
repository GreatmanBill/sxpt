package com.sxpt.controller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxpt.classes.SmartUpload;
import com.sxpt.module.SpaceTeaModule;


public class AddRsToCourseServelt extends HttpServlet{
	SpaceTeaModule spaceTea = null;
	public AddRsToCourseServelt(){
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
			
//			int bid = Integer.parseInt(req.getParameter("bid"));
//			if(bid == -1){
//				req.getSession().setAttribute("message", "请选择批次");
//				resp.sendRedirect("leadStu.jsp");
//			}
//			String sno = req.getParameter("sno");
//			b = sno.getBytes("ISO-8859-1");
//			sno = new String(b,"utf-8");
//			
//			String sname = req.getParameter("sname");
//			b = sname.getBytes("ISO-8859-1");
//			sname = new String(b,"utf-8");
//			
//			String sclass = req.getParameter("sclass");
//			b = sclass.getBytes("ISO-8859-1");
//			sclass = new String(b,"utf-8");
//			String spsw = req.getParameter("spsw");
//			b = spsw.getBytes("ISO-8859-1");
//			spsw = new String(b,"utf-8");
//			
//			//组装学生信息为(sno,sname, sclass, staPsw, bid)格式
//			String stu = "('"+sno+"','"+sname+"','"+sclass+"','"+spsw+"',"+bid+")";
//			int insertid = this.spaceTea.leadInStu(stu);
//			if(insertid > 0){
//				 req.getSession().setAttribute("message", "学生信息成功");
//				 req.getSession().setAttribute("url", "leadStu.jsp");
//				 resp.sendRedirect("success.jsp");
//			 } else if(insertid == -10) {
//				 req.getSession().setAttribute("message", "请检查，学号不能重复");
//				 resp.sendRedirect("leadStu.jsp");
//			 } else {
//				 req.getSession().setAttribute("message", "插入学生信息失败");
//				 resp.sendRedirect("leadStu.jsp");
//			 }
//			System.out.println(sub + " "+sno +" "+sname+" "+sclass+" "+spsw); 
		}catch(NullPointerException e){
			upload(req, resp);
		}
	}
	
	public void upload(HttpServletRequest req, HttpServletResponse resp){
		
		// 新建一个SmartUpload对象
		SmartUpload su = new SmartUpload();
		// 上传初始化
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
			//file.saveAs("/upload/" + myFile.getFileName());//TODO
			String str  = null; 
			
			//输入流和用utf-8格式读取
			FileInputStream in = new FileInputStream(fileName);
			BufferedReader br=new BufferedReader(new InputStreamReader(in, "UTF-8"));
			System.out.println(req.getParameter("sub"));
			//for(int i = 0;i < req.getParameterNames().)
			int bid = Integer.parseInt(su.getRequest().getParameter("bid"));
			
			//组装学生信息为(sno,sname, sclass, staPsw, bid)格式
						
			StringBuffer strbuf = new StringBuffer();
			boolean flag = true;
			br.readLine();//第一条是头，跳过
			String value = null;
			while((str = br.readLine()) != null){
				String[] stu = str.split(",");
			    System.out.println(stu[0]+stu[1]+stu[2]);
				if(flag){
					flag = false;
					value = "('"+stu[0]+"','"+stu[1]+"','"+stu[2]+"','123456',"+bid+")";
					strbuf.append(value);
					continue;
				}
				
			    value = ",('"+stu[0]+"','"+stu[1]+"','"+stu[2]+"','123456',"+bid+")";
			    strbuf.append(value);
			}
			
			String students = new String(strbuf);
			 System.out.println(students);
			 int insertid = this.spaceTea.leadInStu(students);
			 if(insertid > 0){
				 req.getSession().setAttribute("message", "导入学生信息成功");
				 req.getSession().setAttribute("url", "leadStu.jsp");
				 resp.sendRedirect("success.jsp");
			 } else if(insertid == -10) {
				 req.getSession().setAttribute("message", "请检查，学号不能重复");
				 resp.sendRedirect("leadStu.jsp");
			 } else {
				 req.getSession().setAttribute("message", "文件格式错误");
				 resp.sendRedirect("leadStu.jsp");
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			req.getSession().setAttribute("message", "文件格式错误");
			try {
				resp.sendRedirect("leadStu.jsp");
			} catch (IOException e1) {
				// TODO Auto-generated catch block 
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}
