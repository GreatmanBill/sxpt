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

public class LeadStuServlet extends HttpServlet {
	SpaceTeaModule spaceTea = null;
	public LeadStuServlet(){
		this.spaceTea = new SpaceTeaModule();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("lai guo ");
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		upload(req, resp);
		//MultipartRequest mulit = new MultipartRequest(request,filePath,fileMaxSize,"UTF-8",rfrp);
//		byte[] b = null;
//		String sub = req.getParameter("sub");
//		System.out.println(sub); 
		
		/*String psw = req.getParameter("psw");
		b = psw.getBytes("ISO-8859-1");
		psw = new String(b);
		
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
		*/
	}
	
	public String leadStuFile(String path) throws IOException{
		File file=new File(path);
		if(!file.exists()||file.isDirectory())
		throw new FileNotFoundException();
		
		BufferedReader br=new BufferedReader(new FileReader(file));
		String temp=null;
		StringBuffer sb=new StringBuffer();
		temp=br.readLine();
		while(temp!=null){
			sb.append(temp+" ");
			temp=br.readLine();
		}
		return sb.toString();
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
			System.out.println(req.getParameter("sub"));
			//for(int i = 0;i < req.getParameterNames().)
			int bid = Integer.parseInt(su.getRequest().getParameter("bid").toString());
			
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
		// 设定上传限制
		// 1.限制每个上传文件的最大长度。
		// su.setMaxFileSize(10000);
		// 2.限制总上传数据的长度。
		// su.setTotalMaxFileSize(20000);
		// 3.设定允许上传的文件（通过扩展名限制）,仅允许doc,txt文件。
		// su.setAllowedFilesList("doc,txt");
		// 4.设定禁止上传的文件（通过扩展名限制）,禁止上传带有exe,bat,jsp,htm,html扩展名的文件和没有
		//扩展名的文件。
		// su.setDeniedFilesList("exe,bat,jsp,htm,html,,");
//		// 上传文件
//		su.upload();
//		// 将上传文件全部保存到指定目录，需要先在在Web应用的根目录下，创建一个upload目录
//		int count = su.save("/upload");
		//out.println(count+"个文件上传成功！<br>");
		// 利用Request对象获取参数之值
		//out.println("TEST="+su.getRequest().getParameter("TEST")
		//+"<BR><BR>");
		// 逐一提取上传文件信息，同时可保存文件。
//		for (int i=0;i<su.getFiles().getCount();i++)
//		{
//		com.sxpt.classes.File file = su.getFiles().getFile(i);
//		// 若文件不存在则继续
//		if (file.isMissing()) continue;
		// 显示当前文件信息

//		out.println("<TABLE BORDER=1>");
//		out.println("<TR><TD>表单项名（FieldName）</TD><TD>"
//		+ file.getFieldName() + "ooo</TD></TR>");
//		out.println("<TR><TD>文件长度（Size）</TD><TD>" +
//		file.getSize() + "</TD></TR>");
//		out.println("<TR><TD>文件名（FileName）</TD><TD>"
//		+ file.getFileName() + "</TD></TR>");
//		out.println("<TR><TD>文件扩展名（FileExt）</TD><TD>"
//		+ file.getFileExt() + "</TD></TR>");
//		out.println("<TR><TD>文件全名（FilePathName）</TD><TD>"
//		+ file.getFilePathName() + "</TD></TR>");
//		out.println("</TABLE><BR>");
		// 将文件另存
		// file.saveAs("/upload/" + myFile.getFileName());
		// 另存到以WEB应用程序的根目录为文件根目录的目录下
		// file.saveAs("/upload/" + myFile.getFileName(),su.SAVE_VIRTUAL);
		// 另存到操作系统的根目录为文件根目录的目录下
		// file.saveAs("c:\\temp\\" + myFile.getFileName(),su.SAVE_PHYSICAL);
		//}
		
	}
}
