<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="content-type" content="text/html;charset=utf-8" />
		<title>软件实训平台</title>
	
		<link rel="stylesheet" type="text/css" href="css/common.css">
	</head>
  
	<body>
		<div id="wrap">
			<div class="line_blue"></div>
			<div id ="header">				
				<div id="logo">
					软件实训平台
				</div>
				
				<div id="banner">
					<img src="images/a.jpg" alt="banner" />
				</div>
			</div>
			
			<div id="left">
				<div id="left-top">
					<h3>系统登录</h3>
					<form action="" method="post">
						<p>
							<label for="identity">身份</label>
							<select name = "identity" id="identity">
							<option value="0">学生</option>
							<option value="1">教师</option>
							<option value="2">负责人</option>
							</select>
						</p>
						
						<p>
							<label for="username">姓名</label>
							<input name="username" id="username" type="text" />
						</p>
						
						<p>
							<label for="psw">密码</label>
							<input name="psw" id="psw" type="password"/>
						</p>
						
						<p>
							<input class= "sub" name="sub"type="submit" value="登录"/>
							<input class = "res" name="res" type="reset" value="重置"/>
						</p>
					</form>
				</div>
				
				<div id="register">
					<a class="reg" href="">我要报名</a>
					<a class="liuchen" href="">实训流程</a>
				</div>
				
				<div id="weather">
				<iframe name="weather_inc" src="http://tianqi.xixik.com/cframe/4" width="180" height="220" frameborder="0" marginwidth="0" marginheight="0" scrolling="no" ></iframe>
				</div>
			</div>
			
			
			
			<div id="right">
				<div id="override">
					<dl>
						<dt>软件实训平台简介</dt>
						<dd>
							<p>
								为了提升学生对软件项目的动手能力，学院组织了教师和学生，以导师制来辅助学生来完成项目的开发，让学生体验真实的项目，学会自主开发软件项目，并对软件工程有一定的了解，是学生具备一定的分析、设计及编码技能，为日后的工作打下坚实的基础。
							</p>
						</dd>
					</dl>
				</div>
				
				<div id="notice" class="news">
					<h4><a href="">更多&gt;&gt;</a>实训通告</h4>
					<ul>
						<li><span>2012-06-05</span><a href="">实训公告一</a></li>
						<li><span>2012-06-05</span><a href="">实训公告二</a></li>
						<li><span>2012-06-05</span><a href="">实训公告三</a></li>
						<li><span>2012-06-05</span><a href="">实训公告四</a></li>
					</ul>
				</div>
				
				<div id="import" class="news">
					<h4><a href="">更多&gt;&gt;</a>实训要闻</h4>
					<ul>
						<li><span>2012-06-05</span><a href="">实训公告一</a></li>
						<li><span>2012-06-05</span><a href="">实训公告二</a></li>
						<li><span>2012-06-05</span><a href="">实训公告三</a></li>
						<li><span>2012-06-05</span><a href="">实训公告四</a></li>
					</ul>
				</div>
			</div>		
			
			<div class="line_blue"></div>
		</div>
	</body>
</html>
