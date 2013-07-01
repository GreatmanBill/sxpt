<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.sxpt.module.*" %>
<jsp:include page="../validate.jsp" flush="true" />
<%
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
		SpaceTeaModule spaceTM = new SpaceTeaModule();
		String courseClassOption = "";
		String coursesOption = "";
		String itemClassOption = "";
		String itemsOption = "";
		
		try{
			//获得所有的课程分类
			ArrayList<HashMap<String, Object>> course_classes = spaceTM.getAllCourseClass();
			int selectedClassid = 0;
			int classid = 0;
			String class_name = "";
			System.out.println(course_classes);
			HashMap<String, Object> temp = null;
			for(int i = 0;i < course_classes.size();i++){
				temp = course_classes.get(i);
				classid = Integer.parseInt(temp.get("classid").toString());
				class_name = temp.get("class_name").toString();
				if(i == 0){
					selectedClassid = classid;
					courseClassOption += "<option selected='selected' value='"+classid+"'>"+class_name+"</option>";
				}else{
					courseClassOption += "<option value='"+classid+"'>"+class_name+"</option>";
				}			
			}
			
			//根据课程分类id来获得该课程分类下的所有课程 
			
			ArrayList<HashMap<String, Object>> courses = spaceTM.getCoursesByClassid(selectedClassid);
			
			for(int i = 0;i < courses.size();i++){
				temp = courses.get(i);
				int cid = Integer.parseInt(temp.get("cid").toString());
				String cname = temp.get("cname").toString();
				coursesOption += "<option value='"+cid+"'>"+cname+"</option>";
			}
			
			//获取所有的项目分类
			ArrayList<HashMap<String, Object>> item_classes = spaceTM.getAllItemClass();
			
			for(int i = 0;i < item_classes.size();i++){
				temp = item_classes.get(i);
				classid = Integer.parseInt(temp.get("classid").toString());
				class_name = temp.get("class_name").toString();
				if(i == 0){
					selectedClassid = classid;
					itemClassOption += "<option selected='selected' value='"+classid+"'>"+class_name+"</option>";
				}else{
					itemClassOption += "<option value='"+classid+"'>"+class_name+"</option>";
				}
			}
			
			//获取选中的分类的下面的所有项目
			ArrayList<HashMap<String, Object>> items = spaceTM.getItemesByClassid(selectedClassid);
			for(int i = 0;i < items.size();i++){
				temp = courses.get(i);
				int itemid = Integer.parseInt(temp.get("itemid").toString());
				String item_name = temp.get("item_name").toString();
				itemsOption += "<option value='"+itemid+"'>"+item_name+"</option>";
			}
		}catch(Exception e){}  
		
		int did = Integer.parseInt(request.getParameter("did"));
		int trainid = Integer.parseInt(request.getParameter("trainid"));
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>实训方案</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/space.css">
	<link rel="stylesheet" type="text/css" href="css/viewPlan.css">
	<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
	<script type="text/javascript">
		$(function(){
		
			$("#classid").change( function() {
				//alert($(this).val());
				var classid = 0;
				classid = $(this).val();
				$.ajax({
					type: "POST",
					url: "/sxpt/makePlan?action=getCourses",
					data: "classid="+classid,
					success: function(data){						
						$("#cid").html(data);
					}
				});
			});
			
			$("#item_classid").change( function() {
				//alert($(this).val());
				var item_classid = 0;
				item_classid = $(this).val();
				$.ajax({
					type: "POST",
					url: "/sxpt/addItemToPlan?action=getCourses",
					data: "classid="+item_classid,
					success: function(data){						
						$("#itemid").html(data);
					}
				});
			});
		});
	</script>

  </head>
  
  <body>
	<div id="viewPlan">
		<a class="back" href="admin/planManage.jsp">返回</a>
		<h3>【实训方案】2013年春季学期实训方案</h3>
		<% 
			String courseHTML = "";
			String itemHTML = "";
			try{
				HashMap<String, Object> res = spaceTM.getCoursesAndItemByTrainid(trainid);
				ArrayList<HashMap<String, Object>> courses = (ArrayList<HashMap<String, Object>>)res.get("courses");
				HashMap<String, Object> temp = null;
				for(int i = 0;i < courses.size();i++){
					temp = courses.get(i);
					int cid = Integer.parseInt(temp.get("cid").toString());
					String cname = temp.get("cname").toString();
					String cprofile = temp.get("cprofile").toString();
					courseHTML += "<tr class='cname'><td class='first'>【课程】"+cname+"</td><td>删除</td></tr>";
					courseHTML += "<tr class='profile' ><td colspan='2' class='first'>【简介】："+cprofile+"</td></tr>";
				}
				
				//TODO 项目
			}catch(Exception e){}
		%>
		<table border="1" cellspacing="0" cellpadding="0" id="course">
			<caption>课程信息</caption>
			<col width="90%"/>
			<col width="10%" />
			<tr class="head"><th>课程名称</th><th>操作</th></tr>
			
			<%=courseHTML %>
			<!--  
			<tr class="cname"><td class="first">【课程】java基础知识</td><td>删除</td></tr>
			<tr class="profile" ><td colspan="2" class="first">【简介】：java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介jav知识简介</td></tr>
			<tr class="cname"><td class="first">【课程】java基础知识</td><td>删除</td></tr>
			<tr class="profile" ><td colspan="2" class="first">【简介】：java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介jav知识简介</td></tr>
			-->
		</table>
		
		<table border="1" cellspacing="0" cellpadding="0" id="course">
			<caption>项目信息</caption>
			<col width="90%"/>
			<col width="10%" />
			<tr class="head"><th>项目名称</th><th>操作</th></tr>
			<tr class="cname"><td class="first">【项目】java基础知识</td><td>删除</td></tr>
			<tr class="profile" ><td colspan="2" class="first">【简介】：java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介jav知识简介</td></tr>
			<tr class="cname"><td class="first">【项目】java基础知识</td><td>删除</td></tr>
			<tr class="profile" ><td colspan="2" class="first">【简介】：java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介java基础知识简介jav知识简介</td></tr>
		</table>
		
		<div	 class="addUnit">
			
			<form action="makePlan?action=addTo" method="post">
				<fieldset>
					<legend>添加课程信息</legend>
					<input type="hidden" name="trainid" value="<%=trainid %>"/>
					<input type="hidden" name="did" value="<%=did %>"/>
					<%
						if(session.getAttribute("message") != null){
							out.print("<p>"+session.getAttribute("message")+"</p>");
							session.setAttribute("message", null);
						}
					%>
					<p>
						<label>课程分类</label>
						<select id="classid" name="classid"><%=courseClassOption %></select>
					</p>
					
					<p>
						<label>课程名称</label>
						<select id="cid" name="cid"><%=coursesOption %></select>
					</p>
					
					<p><input class="sub" type="submit" name="sub" value="添加"/></p>
					<br/>
				</fieldset>
			</form>
		</div>
		
		<div class="addUnit">
			
			<form action="addItemToPlan?action=addTo" method="post">
				<fieldset>
					<legend>添加项目信息</legend>
	  				<input type="hidden" name="trainid" value="<%=trainid %>"/>
					<input type="hidden" name="did" value="<%=did %>"/>
					<p>
						<label>项目分类</label>
						<select id="item_classid" name="classid"><%=itemClassOption %></select>
					</p>	
					<p>
						<label>项目名称</label>
						<select id= "itemid" name="itemid"><%=itemsOption %></select>
					</p>				
					<p><input class="sub" type="submit" name="sub" value="添加"/></p>
					<br/>
				</fieldset>
			</form>
		</div>
		
	</div>
  </body>
</html>
