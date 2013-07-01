<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.sxpt.module.*"%>
<jsp:include page="../validate.jsp" flush="true" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>分配实训方案</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/space.css">
	<link rel="stylesheet" type="text/css" href="css/distriPlan.css">
	<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
	<script type="text/javascript">
		$(function(){
		
			$("#bid").change( function() {
				//alert($(this).val());
				var bid = 0;
				bid = $(this).val();
				$.ajax({
					type: "POST",
					url: "/sxpt/distriPlan?action=getDirects",
					data: "bid="+bid,
					success: function(data){
						//alert(data);
						
						$("#did").html(data);
					}
				});
			});
			
		});
	</script>

  </head>
  
  <body>
		<div id="distriPlan">
			<a class="back" href="admin/planManage.jsp">返回</a>
			<h1>分配实训方案</h1>
			<%
				if(session.getAttribute("message") != null){
						out.print("<div>"+session.getAttribute("message")+"</div>");
						session.setAttribute("message", null);
				}
				
				//出入的方向id
				int fDid = Integer.parseInt(request.getParameter("did"));
				//传入的实训方案id
				int trainid = Integer.parseInt(request.getParameter("trainid"));
				
				SpaceTeaModule spaceTM = new SpaceTeaModule();
				
				ArrayList<HashMap<String, Object>> batchs = spaceTM.getAllBatch();
				ArrayList<HashMap<String, Object>> t_directs = spaceTM.getAllDirects();
				
				String batchOption = "";
				String directOption = "";
				int i = 0;
				HashMap<String,Object> temp = null;
				int bid = 0;
				String bname = "";
				int did = 0;
				String dname = "";
				int selectedBid = 0;
				
				for(i = 0; i < t_directs.size();i++){
					temp = t_directs.get(i);
					did = Integer.parseInt(temp.get("did").toString());
					bid = Integer.parseInt(temp.get("bid").toString());
					//如果该方向id与传入的id相等,找到要显示的批次
					if(did == fDid){
						selectedBid = bid;
						break;
					}
					
					if(fDid == 0){
						selectedBid = bid;
						break;
					}					
				}
				
				for(i = 0; i < t_directs.size();i++){
					temp = t_directs.get(i);
					bid = Integer.parseInt(temp.get("bid").toString());
					//只显示当前批次的实训方向
					if(bid == selectedBid){
						did = Integer.parseInt(temp.get("did").toString());
						dname = temp.get("dname").toString();
						//当前选中的方向显示
						if(did == fDid){
							directOption += "<option selected='selected' value='"+did+"'>"+dname+"</option>";
						} else {
							directOption += "<option value='"+did+"'>"+dname+"</option>";
						}
					}
				}
				
				for(i = 0; i < batchs.size();i++){
					temp = batchs.get(i);
					bid = Integer.parseInt(temp.get("bid").toString());
					bname = temp.get("bname").toString();
					//选中的实训批次显示
					if(bid == selectedBid){
						batchOption += "<option selected = 'selected' value='"+bid+"'>"+bname+"</option>";
					} else {
						batchOption += "<option value='"+bid+"'>"+bname+"</option>";
					}
				}
				 
				 System.out.println("selectedbid:"+selectedBid);
				
			%>
			<form action="distriPlan">
				<input type="hidden" name="trainid" value="<%=trainid %>"/>
				<input type="hidden" name="action" value="distri"/>
				<p>
					<label>实训批次</label>
					<select id="bid" name="bid">
					<%=batchOption %>
					<!-- 
						<option value="">实训批次一</option>
						<option value="">实训批次一</option>
						<option value="">实训批次一</option>
						<option value="">实训批次一</option>
						<option value="">实训批次一</option>
					-->
					</select>
				</p>
				
				<p>
					<label>实训方向</label>
					<select id = "did"name="did">
						<%=directOption %>
						<!-- 
						<option value="">实训方向一</option>
						<option value="">实训方向一</option>
						<option value="">实训方向一</option>
						<option value="">实训方向一</option>
						<option value="">实训方向一</option>
						-->
					</select>
				</p>
				
				<p><input class="sub" type="submit" name="sub" value="确定"/></p>
			</form>
		</div>
  </body>
</html>
