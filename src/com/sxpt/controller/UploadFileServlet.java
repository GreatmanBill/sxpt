package com.sxpt.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
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

public class UploadFileServlet extends HttpServlet{
	SpaceTeaModule spaceTea = null;
	public UploadFileServlet(){
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
		}catch(NullPointerException e){
			upload(req, resp);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void upload(HttpServletRequest req, HttpServletResponse resp){
		
		// 新建一个SmartUpload对象
		SmartUpload su = new SmartUpload();
		// 上传初始化
		
		//资源分类id
		int cclassid = 0;
		//资源分类名
		String class_name  = "";
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
			double size = file.getSize() * 1.0 / 1024;
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
			
			//10、获得class_name
			class_name = su.getRequest().getParameter("class_name");
			
			class_name = URLEncoder.encode(URLEncoder.encode(class_name));
			
			//10、获得class_name
			cclassid = Integer.parseInt(su.getRequest().getParameter("cclassid"));

			//5、得到上传作者
			String rsuser = "";
			HashMap<String, Object> user = (HashMap<String, Object>)req.getSession().getAttribute("user");
			
			if(user == null){
				
				req.getSession().setAttribute("message", "添加资源失败,请登录");
				resp.sendRedirect("admin/viewRsClass.jsp?classid="+cclassid+"&class_name="+class_name);
				return ;
			}
			System.out.println(user.toString());
			if(user.get("type").toString().equals("0")){
				Student stu = (Student)user.get("student");
				rsuser = stu.getSname();
				
			} else {//教师或管理员
				Teacher tea = (Teacher)user.get("teacher");
				rsuser = tea.getTname();
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
				 req.getSession().setAttribute("message", "添加资源成功");
				 req.getSession().setAttribute("url", "admin/viewRsClass.jsp?classid="+cclassid+"&class_name="+class_name);
				 resp.sendRedirect("success.jsp");
			 } else {
				 req.getSession().setAttribute("message", "添加资源失败");
				 resp.sendRedirect("admin/resManage.jsp?class_name="+class_name);
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			req.getSession().setAttribute("message", "添加资源失败");
			try {
				resp.sendRedirect("admin/viewRsClass.jsp?classid="+cclassid+"&class_name="+class_name);
			} catch (IOException e1) {
				// TODO Auto-generated catch block 
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}
