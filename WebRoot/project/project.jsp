<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.sxpt.classes.*"%>
<%@ page import="com.sxpt.module.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="content-type" content="text/html;charset=utf-8" />
		<title>软件实训——项目实训</title>

		<link rel="StyleSheet" href="css/dtree.css" type="text/css" />
		<script type="text/javascript" src="js/dtree.js"></script>

	</head>

<body>
<div class="dtree">

	<p><a href="javascript: d.openAll();">打开所有</a> | <a href="javascript: d.closeAll();">关闭所有</a></p>

	<script type="text/javascript">
		<!--

		d = new dTree('d');
		
		<%
			int type = 0;
			String t_direct = "";
			String linkHTML = "";
			
			int itemid = 0;
			String item_name = "";
			String item_profile = "";
			try{
				HashMap<String ,Object> user = (HashMap<String ,Object>)session.getAttribute("user");
				type = Integer.parseInt(user.get("type").toString());
				
				int bid = 0;
				if(type == 0){//学生
					Student stu = (Student)user.get("student");
					bid = stu.getBid();
					t_direct = stu.getT_direct();
					
				} else {	  //教师
					Teacher tea = (Teacher)user.get("teacher");
					t_direct = tea.getT_direct();
				}
				
				System.out.println("t_direct:"+t_direct);
				SpaceTeaModule spaceTM = new SpaceTeaModule();
				HashMap<String, Object> res = spaceTM.getCourseAndItemByDirect(t_direct);
				
				System.out.println(res);
				
				HashMap<String, String> item = (HashMap<String, String>)res.get("item");
				
				itemid = Integer.parseInt(item.get("itemid"));
				item_name = item.get("item_name");
				item_profile = item.get("item_profile");
				
				ArrayList<HashMap<String, Object>> stages = (ArrayList<HashMap<String, Object>>)res.get("stages");
				HashMap<String, Object> temp = null;
				System.out.println("size:"+stages.size());
				int i = 0;
				for(;i < stages.size();i++){
					temp = stages.get(i);
					int staid = Integer.parseInt(temp.get("staid").toString());
					String sta_name = temp.get("sta_name").toString();
					linkHTML += "d.add("+(i + 3)+",1,'"+sta_name+"','project/viewStage.jsp?staid="+staid+"','','right');";
				}
				if(type == 1){
					linkHTML += "d.add("+(i + 3)+",0,'项目成员','project/memManage.jsp','','right');";
				} else if(type == 0){
					linkHTML += "d.add("+(i + 3)+",0,'项目成员','project/member.jsp','','right');";
				}
				i++;
				if(type == 1){
		 			linkHTML += "d.add("+(i + 3)+",0,'任务分配','project/taskDistribute.jsp','','right');";
		 		} else {
		 			linkHTML += "d.add("+(i + 3)+",0,'任务分配','project/stuTaskDistribute.jsp','','right');";
		 		}
				
			}catch(Exception e){
				e.printStackTrace();
			}
		 %>
		
		//学生
		d.add(0,-1,'<%=t_direct %>');
		d.add(1,0,'<%=item_name %>',"<% out.print("project/itemIndex.jsp?itemid="+itemid); %>",'','right');
		d.add(2,1,"项目首页","<% out.print("project/itemIndex.jsp?itemid="+itemid); %>",'','right');	
		<%=linkHTML %>
		document.write(d);

		//-->
	</script>

</div>
</body>

</html>