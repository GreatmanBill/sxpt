package com.sxpt.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sxpt.classes.SmartUpload;
import com.sxpt.classes.Student;
import com.sxpt.classes.Teacher;
import com.sxpt.module.SpaceTeaModule;


public class AddRsToCourseServelt extends HttpServlet{
	SpaceTeaModule spaceTea = null;
	//页面传入的课程分类id
	int cclassid = 0;
	int cid = 0;
	//页面传入的课程分类名
	String class_name  = "";
	String cname = "";
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
			String action = req.getParameter("action");
			if(action.equals("addTo")){
				byte[] b = null;
				cid = Integer.parseInt(req.getParameter("cid"));
				cname = req.getParameter("cname");
				b = cname.getBytes("ISO-8859-1");
				cname = new String(b, "utf-8");
				
				cclassid = Integer.parseInt(req.getParameter("cclassid"));
				class_name = req.getParameter("class_name");
				b = class_name.getBytes("ISO-8859-1");
				class_name = new String(b, "utf-8");
				
				int rsid = Integer.parseInt(req.getParameter("rsid"));
				
				//将该资源添加到该课程中
				int result = this.spaceTea.addRs2Course(rsid, cid);
				if(result > 0){
					req.getSession().setAttribute("message", "添加资源成功");
					req.getSession().setAttribute("url", "admin/resManage.jsp?cid="+cid+"&cname="+cname+"&classid="+cclassid+"&class_name="+class_name);
					resp.sendRedirect("success.jsp");
				} else {
					req.getSession().setAttribute("message", "添加资源失败");
					resp.sendRedirect("admin/resManage.jsp?cid="+cid+"&cname="+cname+"&classid="+cclassid+"&class_name="+class_name);
				}
			} else { //ajax异步请求
				int classid = 0;
				classid = Integer.parseInt(req.getParameter("classid"));
				ArrayList<HashMap<String, Object>> resources = this.spaceTea.getResourceByClassid(classid);
				String rsOption = "";
				try{
					String trsname = "";
					int trsid = 0;
					HashMap<String, Object> temp = null;
					for(int i = 0;i < resources.size();i++){
						temp = resources.get(i);
						trsname = temp.get("rsname").toString();
						trsid = Integer.parseInt(temp.get("rsid").toString());
						rsOption += "<option value='"+trsid+"'>"+trsname+"</option>";
					}
					resp.setContentType("text/html; charset=utf-8");
					PrintWriter out = resp.getWriter(); 
					out.print(rsOption);
				}catch(Exception e){}
			}
		}catch(NullPointerException e){
			upload(req, resp);
		}
	}
	
	@SuppressWarnings("unchecked")
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
			
			//1 、得到文件名和文件所在的路径
			String fileName = file.getFileName();
			
			//2 、 重命名文件
			long ctime = new Date().getTime();
			SimpleDateFormat format =   new SimpleDateFormat( "yyyyMMddHHmmss" );
	      	String day = format.format(ctime);
			String fileExt = file.getFileExt(); 
			
			//3 、 得到url
			String rsurl = day+"."+fileExt;
			
			//4 、 得到文件大小
			String rsSize = "";			
			double size = file.getSize() * 0.1 / 1024;
			BigDecimal bg  = null;
			if(size < 1024){//kb
				bg  = new BigDecimal(size);
				size = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				rsSize = size+" kb";
			}else{
				size = size / 1024;
				bg  = new BigDecimal(size);
				size = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				rsSize = size + " Mb";
			}
			
			
			
			//6、得到资源描述
			String rsprofile = su.getRequest().getParameter("rsprofile");
			
			//7、得到资源分类
			int classid = Integer.parseInt(su.getRequest().getParameter("classid"));
			
			//8、获得任务类别
			int task = Integer.parseInt(su.getRequest().getParameter("task"));
			
			//9、获得当前的课程cid
			cid = Integer.parseInt(su.getRequest().getParameter("cid"));
			
			//10、获得class_name
			class_name = su.getRequest().getParameter("class_name");
			
			//10、获得class_name
			cclassid = Integer.parseInt(su.getRequest().getParameter("cclassid"));
			
			//11、得到cname
			cname = su.getRequest().getParameter("cname");
			
			//5、得到上传作者
			String rsuser = "";
			HashMap<String, Object> user = (HashMap<String, Object>)req.getSession().getAttribute("user");
			
			if(user == null){
				
				req.getSession().setAttribute("message", "添加资源失败,请登录");
				resp.sendRedirect("admin/resManage.jsp?cid="+cid+"&cname="+cname+"&classid="+cclassid+"&class_name="+class_name);
				return ;
			}
			System.out.println(user.toString());
			if(user.get("type").toString().equals("1")){//教师或管理员
				Teacher tea = (Teacher)user.get("teacher");
				rsuser = tea.getTname();
			} else {
				Student stu = (Student)user.get("student");
				rsuser = stu.getSname();
			}
			System.out.println("fileName:"+fileName+" rsurl:"+rsurl+" rsSize:"+rsSize+" rsuser:"+rsuser+" rsprofile:"+rsprofile);
			
			
			
			System.out.println("classid:"+classid+" task:"+task+" saveas:/upload/" + rsurl);
			file.saveAs("/upload/" + rsurl);
			
			//删除文件
			String path = req.getSession().getServletContext().getRealPath("") + "/upload/" + fileName;
			file.delete(path);
			
			//把资源添加到resource中
			 int insertid = this.spaceTea.addResource(classid, fileName, rsurl, rsprofile, rsSize, rsuser, ctime, task);
			 
			 if(insertid > 0){
				 //将该资源添加到该课程中
				 int result = this.spaceTea.addRs2Course(insertid, cid);
				 if(result > 0){
					 //admin/resManage.jsp?cid=10&cname=J2EE&classid=2&class_name=C
					 req.getSession().setAttribute("message", "添加资源成功");
					 req.getSession().setAttribute("url", "admin/resManage.jsp?cid="+cid+"&cname="+cname+"&classid="+cclassid+"&class_name="+class_name);
					 resp.sendRedirect("success.jsp");
				 } else {
					 req.getSession().setAttribute("message", "添加资源失败");
					 resp.sendRedirect("admin/resManage.jsp?cid="+cid+"&cname="+cname+"&classid="+cclassid+"&class_name="+class_name);
				 }
			 } else {
				 req.getSession().setAttribute("message", "添加资源失败");
				 resp.sendRedirect("admin/resManage.jsp?cid="+cid+"&cname="+cname+"&classid="+cclassid+"&class_name="+class_name);
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			req.getSession().setAttribute("message", "添加资源失败");
			try {
				resp.sendRedirect("admin/resManage.jsp?cid="+cid+"&cname="+cname+"&classid="+cclassid+"&class_name="+class_name);
			} catch (IOException e1) {
				// TODO Auto-generated catch block 
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}
