package com.sxpt.module;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.sxpt.classes.DBConnection;

public class SpaceTeaModule {

	private Statement statement;
	private Connection con = null;
	public SpaceTeaModule(){
		//连接数据库
		DBConnection dbCon = new DBConnection();
		this.statement = dbCon.getStatement();
		this.con = dbCon.getCon();
	}
	
	/**
	 * 导入学生账号
	 * 学号，姓名，班级，初始密码，所属批次
	 * String sno, String sname, String sclass, String staPsw, int bid
	 * @param students 这个参数的组成是(sno,sname, sclass, staPsw, bid)多个
	 * 
	 * @return
	 */
	public int leadInStu(String students){
		
		int result = 0;
		
		String sql = "insert into student(sno, sname, sclass, spsw, bid) " +
				"values "+students;
		
		System.out.println("leadInStu: "+sql);
		
		try {
			try{
				result = this.statement.executeUpdate(sql);
			} catch(MySQLIntegrityConstraintViolationException e){//插入重复异常
				return -10;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	/**
	 * 叙述：负责人导入全体参加实训教师信息，包括:姓名，初始密码, 实训方向，性别，
	 * @param teachers 由（tname, staPsw, t_direct, tsex）多个组成
	 * @return
	 */
	public int leadInTea(String teachers){
		int result = 0;
		String sql = "insert into teacher(tname, tpsw, t_direct, tsex) " +
		"values "+teachers;

		System.out.println("leadInTea: "+sql);
		
		try {
			try{
				result = this.statement.executeUpdate(sql);
			} catch(MySQLIntegrityConstraintViolationException e){
				return -10;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 新建批次
	 * @param bname
	 * @return  更新成功后返回插入的id
	 */
	public int newBatch(String bname){
		int result = 0;
		String sql = "insert into batch (bname) value ('"+bname+"')";

		System.out.println("newBatch: "+sql);
		
		try {
			result = this.statement.executeUpdate(sql);
			
			sql = "select max(bid) as bid from batch";
			
			ResultSet rs = this.statement.executeQuery(sql);
			int bid = 0;
			if(rs.next()){
				bid = rs.getInt("bid");
			}
			
			if(result != 0){
					
				result = bid;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ArrayList<HashMap<String, Object>> getAllBatch(){
		
		int result = 0;
		
		String sql = "select * from batch order by bid desc";
		
		System.out.println(sql);
		ArrayList<HashMap<String, Object>> batchs = null;
		HashMap<String, Object> temp = null;
		try {
			ResultSet rs = this.statement.executeQuery(sql);
			batchs = new ArrayList<HashMap<String, Object>>();
			
			while(rs.next()){
				temp = new HashMap<String, Object>();
				temp.put("bid", rs.getInt("bid"));
				temp.put("bname", rs.getString("bname"));
				temp.put("open", rs.getInt("open"));
				batchs.add(temp);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return batchs;
	}
	
	/**
	 * 新建实训方向
	 * @param bid 批次
	 * @param dname	方向名称
	 * @param dprofile 方向简介
	 * @return	新建实训方向成功后返回方向id-》did
	 */
	public int newTrain_dr(int bid,  String dname, String dprofile){
		int result = 0;
		String sql = "insert into train_dr (bid, dname, dprofile) value ("+bid+",'"+dname+"','"+dprofile+"')";

		System.out.println("newTrain_dr: "+sql);
		
		try {
			result = this.statement.executeUpdate(sql);
			
			sql = "select max(did) as did from train_dr";
			
			ResultSet rs = this.statement.executeQuery(sql);
			int did = 0;
			if(rs.next()){
				did = rs.getInt("did");
			}
			
			if(result != 0){
				result = did;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	/**
	 * 叙述：负责人导入完学生账号后，自动的为之创建一个成绩单，包括：学号，姓名，班级，实训批次
	 * 注:实训方向在报名后更新，直接用一个SQL语句更新
	 * @param bid 批次
	 * @param return  更新成功后返回插入的id->rid
	 */
	 public int  newReport(String sno, String sname, String sclass, int bid ){
		 int result = 0;
		 String sql = "insert into report(sno,sname, sclass, bid) " +
		 				"value ('"+sno+"','"+sname+"','"+sclass+"',"+bid+")";
		
		 System.out.println("newReport: "+sql);
		
		 try {
			result = this.statement.executeUpdate(sql);
			
			sql = "select max(rid) as rid from report";

			ResultSet rs = this.statement.executeQuery(sql);
			int rid = 0;
			if(rs.next()){
				rid = rs.getInt("rid");
			}
				
			if(result != 0){
				result = rid;
			}
				
		} catch (SQLException e) {
				e.printStackTrace();
		}
			
		return result;
	 }
	 
	 /**
	  * 学号，选择教师id，实训方向
	  * @param sno
	  * @param tid
	  * @param t_direct
	  * @return
	  */
	 public int register(String sno, int tid, String t_direct){
		 int result = 0;
		 String sql = "update student set tid = "+tid+", t_direct = '"+t_direct+"'" +
		 		"where sno = '"+sno+"'";
		 
		 System.out.println("register: "+sql);
			
		 try {
			result = this.statement.executeUpdate(sql);
		} catch (SQLException e) {
				e.printStackTrace();
		}  
			
		return result;
		 							
	 }
	 
	 /**
	  * 叙述：修改课内实训成绩
	  * 学生姓名，学生学号，实训次数，实训成绩
	 * @throws SQLException 
	  * @param sname
	  * @param sno
	  * @param in_times
	  * @param in_grade
	  * 
	  * return result
	  */
	 public int modifyReport(String sname,String sno,int intimes,int ingrade ) throws SQLException{
		int result = 0;
		System.out.println("modifyreport");
		System.out.println("sname:  "+sname+"sno:   "+sno);
		String sql = "select rid from report where sname = '"+sname+"' and sno = '"+sno+"'";
		ResultSet rs = this.statement.executeQuery(sql);
		int rid = 0;
		if(rs.next()){
			rid = rs.getInt("rid");
			System.out.println(rid);
		}

		 String sql2 = "update in_report set in_times = "+intimes+", in_grade = '"+ingrade+"'" +
	 		"where rid = "+rid;
		 result = this.statement.executeUpdate(sql2);
		 System.out.println(result);
//		 if(rs2.next()){
//			 
//			 result = 1;
//		 }
		 return result; 
		 
	 }
	  /** 信息标题， 信息发布人uid， 发布人姓名， 发布信息内容，发布信息时间，发布信息类型	
	  * @return
	  */
	 public int addNews(String info_title, int info_uid, String info_name, String info_con, long ctime, int type){
		 int result = 0;
		 
		 String sql = "insert into info_deploy(info_title, info_uid, info_name, info_con, ctime, type) value " +
		 		"('"+info_title+"',"+info_uid+",'"+info_name+"','"+info_con+"',"+ctime+","+type+")";
		 try {
				result = this.statement.executeUpdate(sql);
				
				sql = "select max(infoid) as infoid from info_deploy";

				ResultSet rs = this.statement.executeQuery(sql);
				int infoid = 0;
				if(rs.next()){
					infoid = rs.getInt("infoid");
				}
					
				if(result != 0){
					result = infoid;
				}
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 return result;

	 }

	 /**
	  * 叙述：修改课外实训成绩
	  * 学生姓名，学生学号，课外成绩
	  * @param sname
	  * @param sno
	  * @param outsum
	  * 
	  * return result
	  */
	 public int modifyOutSum(String sname,String sno,int outsum ) throws SQLException{
			int result = 0;
			System.out.println("modifyoutsum");
			System.out.println("sname:  "+sname+"sno:   "+sno);
			String sql = "select rid from report where sname = '"+sname+"' and sno = '"+sno+"'";
			ResultSet rs = this.statement.executeQuery(sql);
			int rid = 0;
			if(rs.next()){
				rid = rs.getInt("rid");
				System.out.println("rid     "+rid);
			}

			 String sql2 = "update report set out_sum = '"+outsum+"'"+"where rid = '"+rid+"'";
			 result = this.statement.executeUpdate(sql2);
			 System.out.println(result);
			 return result; 
			 
		 }
	 
	 /**
	  * 获得所有的实训方向
	  * @return
	  */
	 public ArrayList<HashMap<String, Object>> getAllDirects(){

			int result = 0;
			
			String sql = "select * from train_dr order by did desc";
			
			System.out.println(sql);
			ArrayList<HashMap<String, Object>> t_directs  = null;
			HashMap<String, Object> temp = null;
			try {
				ResultSet rs = this.statement.executeQuery(sql);
				t_directs = new ArrayList<HashMap<String, Object>>();
				
				while(rs.next()){
					temp = new HashMap<String, Object>();
					temp.put("did", rs.getInt("did"));
					temp.put("dname", rs.getString("dname"));
					temp.put("bid", rs.getInt("bid"));
					temp.put("dprofile", rs.getString("dprofile"));
					t_directs.add(temp);
				}
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return t_directs;
		 
	 }
	 
	 /**
	  * 新建实训方案,新建成功返回insertid
	  * @param train_name	实训方案名称
	  * @return
	  */
	 public int newPlan(String train_name){
		int result = 0;
		String sql = "insert into train_plan (train_name) value ('"+train_name+"')";

		System.out.println("newPlan: "+sql);
		
		try {
			result = this.statement.executeUpdate(sql);
			
			sql = "select max(trainid) as trainid from train_plan";
			
			ResultSet rs = this.statement.executeQuery(sql);
			int trainid = 0;
			if(rs.next()){
				trainid = rs.getInt("trainid");
			}
			
			if(result != 0){
					
				result = trainid;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	 }
	 
	 /**
	  * 得到所有的实训方案
	  * @return
	  */
	 public ArrayList<HashMap<String, Object>> getAllPlan(){
		int result = 0;
			
		String sql = "select trainid, train_name, train_plan.did, dname from train_plan " +
				"left join train_dr on train_plan.did = train_dr.did " +
				"order by trainid desc";
		System.out.println("getAllPlan: "+sql);
		ArrayList<HashMap<String, Object>> plans  = null;
		HashMap<String, Object> temp = null;
		try {
			ResultSet rs = this.statement.executeQuery(sql);
			plans = new ArrayList<HashMap<String, Object>>();
			
			while(rs.next()){
				temp = new HashMap<String, Object>();
				temp.put("trainid", rs.getInt("trainid"));
				temp.put("train_name", rs.getString("train_name"));
				temp.put("did", rs.getInt("did"));
				temp.put("dname", rs.getString("dname"));
				plans.add(temp);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return plans;
		 
	 }
	 
	 /**
	  * 根据传入的批次id来获得该批次下的所有方向
	  * @param bid
	  * @return
	  */
	 public ArrayList<HashMap<String, Object>> getDirectssByBid(int bid){
		 int result = 0;
			
		 String sql = "select * from train_dr where bid = "+bid;
		 System.out.println("getDirectssByBid: "+sql);
		 ArrayList<HashMap<String, Object>> t_directs  = null;
		 HashMap<String, Object> temp = null;
		 try {
			 ResultSet rs = this.statement.executeQuery(sql);
			 t_directs = new ArrayList<HashMap<String, Object>>();
				
			 while(rs.next()){
				temp = new HashMap<String, Object>();
				temp.put("did", rs.getInt("did"));
				temp.put("dname", rs.getString("dname"));
				temp.put("bid", rs.getInt("bid"));
				temp.put("dprofile", rs.getString("dprofile"));
				t_directs.add(temp);
			 }
		
		 } catch (SQLException e) {
			 e.printStackTrace();
		 }
		
		 return t_directs;
	 }
	 
	 /**
	  * 更新实训方案的实训方向
	  * @param did		实训方向id
	  * @param trainid 	实训方案id
	  * @return
	  */
	 public int updateDidOfPlan(int did, int trainid){
		int result = 0;
		String sql = "update train_plan set did = "+did+" where trainid = "+trainid;
		
		System.out.println("updateDidOfPlan: "+sql);
		
		try {
			
			result = this.statement.executeUpdate(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		 
	 }
	 
	 /**
	  * 新建项目分类
	  * @param class_name	项目分类名
	  * @return
	  */
	 public int newItemClass(String class_name){
		 
		int result = 0;
		String sql = "insert into item_class (class_name) value ('"+class_name+"')";

		System.out.println("newItemClass: "+sql);
		
		try {
			result = this.statement.executeUpdate(sql);
			
			sql = "select max(classid) as classid from item_class";
			
			ResultSet rs = this.statement.executeQuery(sql);
			int classid = 0;
			if(rs.next()){
				classid = rs.getInt("classid");
			}
			
			if(result != 0){
					
				result = classid;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	 }
	 
	 
	 /**
	  * 新建项目
	  * @param item_name 	项目名
	  * @param item_profile 项目简介
	  * @param classid 		项目分类id
	  * @return
	  */
	 public int newItem(String item_name, String item_profile, int classid){
		 
		int result = 0;
		String sql = "insert into train_item (item_name, item_profile, classid) " +
				"value ('"+item_name+"','"+item_profile+"',"+classid+")";

		System.out.println("newItem: "+sql);
		
		try {
			result = this.statement.executeUpdate(sql);
			
			sql = "select max(itemid) as itemid from train_item";
			
			ResultSet rs = this.statement.executeQuery(sql);
			int itemid = 0;
			if(rs.next()){
				itemid = rs.getInt("itemid");
			}
			
			if(result != 0){
					
				result = itemid;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	 }
	 
	 /**
	  * 新建项目分类
	  * @param class_name	课程项目名
	  * @return
	  */
	 public int newCourseClass(String class_name){
		 
		int result = 0;
		String sql = "insert into course_class (class_name) value ('"+class_name+"')";

		System.out.println("newCourseClass: "+sql);
		
		try {
			result = this.statement.executeUpdate(sql);
			
			sql = "select max(classid) as classid from course_class";
			
			ResultSet rs = this.statement.executeQuery(sql);
			int classid = 0;
			if(rs.next()){
				classid = rs.getInt("classid");
			}
			
			if(result != 0){
					
				result = classid;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	 }
	 
	 /**
	  *  获得所有的课程分类
	  * @return
	  */
	 public ArrayList<HashMap<String, Object>> getAllCourseClass(){
		 
		int result = 0;
			
		String sql = "select * from course_class order by classid desc";
		
		System.out.println(sql);
		ArrayList<HashMap<String, Object>> course_classes = null;
		HashMap<String, Object> temp = null;
		try {
			ResultSet rs = this.statement.executeQuery(sql);
			course_classes = new ArrayList<HashMap<String, Object>>();
			
			while(rs.next()){
				temp = new HashMap<String, Object>();
				temp.put("classid", rs.getInt("classid"));
				temp.put("class_name", rs.getString("class_name"));
				course_classes.add(temp);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return course_classes;
	 }
	 
	 /**
	  *  获得所有的项目分类
	  * @return
	  */
	 public ArrayList<HashMap<String, Object>> getAllItemClass(){
		 
		int result = 0;
			
		String sql = "select * from item_class order by classid desc";
		
		System.out.println(sql);
		ArrayList<HashMap<String, Object>> item_classes = null;
		HashMap<String, Object> temp = null;
		try {
			ResultSet rs = this.statement.executeQuery(sql);
			item_classes = new ArrayList<HashMap<String, Object>>();
			
			while(rs.next()){
				temp = new HashMap<String, Object>();
				temp.put("classid", rs.getInt("classid"));
				temp.put("class_name", rs.getString("class_name"));
				item_classes.add(temp);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return item_classes;
	 }
	 
	 /**
	  * 新建课程
	  * @param classid		课程分类id
	  * @param cname		课程名
	  * @param cprofile		课程简介
	  * @return
	  */
	 public int newCourse(int classid, String cname, String cprofile){
		int result = 0;
		String sql = "insert into course_unit(classid, cname, cprofile) value(" +
				""+classid+",'"+cname+"', '"+cprofile+"')";

		System.out.println("newCourse: "+sql);
		
		try {
			result = this.statement.executeUpdate(sql);
			
			sql = "select max(cid) as cid from course_unit";
			
			ResultSet rs = this.statement.executeQuery(sql);
			int cid = 0;
			if(rs.next()){
				cid = rs.getInt("cid");
			}
			
			if(result != 0){
					
				result = cid;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	 }
	 
	 public ArrayList<HashMap<String, Object>> getCoursesByClassid(int classid){
		 
		 int result = 0;
			
		 String sql = "select * from course_unit where classid = "+classid+" order by cid desc";
		 System.out.println("getCoursesByClassid: "+sql);
		 ArrayList<HashMap<String, Object>> courses  = null;
		 HashMap<String, Object> temp = null;
		 try {
			 ResultSet rs = this.statement.executeQuery(sql);
			 courses = new ArrayList<HashMap<String, Object>>();
				
			 while(rs.next()){
				temp = new HashMap<String, Object>();
				temp.put("cid", rs.getInt("cid"));
				temp.put("cname", rs.getString("cname"));
				temp.put("classid", rs.getInt("classid"));
				temp.put("cprofile", rs.getString("cprofile"));
				temp.put("cresourse", rs.getString("cresourse"));
				courses.add(temp);
			 }
		
		 } catch (SQLException e) {
			 e.printStackTrace();
		 }
		
		 return courses;
	 }
	 
	 /**
	  * 根据项目分类id返回其下的项目
	  * @param classid
	  * @return
	  */
	 public ArrayList<HashMap<String, Object>> getItemesByClassid(int classid){
		 
		 int result = 0;
			
		 String sql = "select * from train_item where classid = "+classid+" order by itemid desc";
		 System.out.println("getItemesByClassid: "+sql);
		 ArrayList<HashMap<String, Object>> items  = null;
		 HashMap<String, Object> temp = null;
		 try {
			 ResultSet rs = this.statement.executeQuery(sql);
			 items = new ArrayList<HashMap<String, Object>>();
				
			 while(rs.next()){
				temp = new HashMap<String, Object>();
				temp.put("itemid", rs.getInt("itemid"));
				temp.put("item_name", rs.getString("item_name"));
				temp.put("classid", rs.getInt("classid"));
				temp.put("item_profile", rs.getString("item_profile"));
				temp.put("stageid", rs.getString("stageid"));
				items.add(temp);
			 }
		
		 } catch (SQLException e) {
			 e.printStackTrace();
		 }
		
		 return items;
	 }
	 
	 /**
	  * 得到一个项目
	  * @param itemid	项目id
	  * @return
	  */
	 public HashMap<String, String> getItemByItemid(int itemid){
		String sql = "select * from train_item where itemid = "+itemid;
		HashMap<String, String> item = null;
		try{
			ResultSet rs = this.statement.executeQuery(sql);
			
			if(rs.next()){
				item = new HashMap<String, String>();
				item.put("itemid", itemid+"");
				item.put("item_name", rs.getString("item_name"));
				item.put("item_profile", rs.getString("item_profile"));
				item.put("stageid", rs.getString("stageid"));
				item.put("classid",rs.getInt("classid")+"");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return item;
	 }
	 
	 
	 /**
	  * 添加资源
	  * @param classid		资源分类id
	  * @param rsname		资源名
	  * @param rsurl		资源url，用时间生成
	  * @param rsprofile	资源简介
	  * @param rssize		资源大小
	  * @param rsuser		上传资源用户
	  * @param ctime		创建资源时间
	  * @param task			任务是学习或下载
	  * @return
	  */
	 public int addResource(int classid, String rsname, String rsurl, String rsprofile,
			 String rssize, String rsuser, long ctime, int task){
		int result = 0;
		String sql = "insert into resource (classid, rsname, rsurl, rsprofile, rssize,rsuser,ctime,task) value " +
				"("+classid+",'"+rsname+"','"+rsurl+"','"+rsprofile+"','"+rssize+"','"+rsuser+"',"+ctime+","+task+")";

		System.out.println("addResource: "+sql);
		
		try {
			result = this.statement.executeUpdate(sql);
			
			sql = "select max(rsid) as rsid from resource";
			
			ResultSet rs = this.statement.executeQuery(sql);
			int rsid = 0;
			if(rs.next()){
				rsid = rs.getInt("rsid");
			}
			
			if(result != 0){
					
				result = rsid;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
		 
	 }
	 
	 public int addRs2Course(int rsid, int cid){
		 int result = 0;
		 String sql = "select cresourse from course_unit where cid="+cid;
		 System.out.println("addRs2Course: "+sql);
		 
		 try {
			 ResultSet rs = this.statement.executeQuery(sql);
			 
			 String cresourse = "";	
			 if(rs.next()){
				cresourse = rs.getString("cresourse");
			 }
			 
			 //将字符串以逗号分开为数组
			 String[] resids = cresourse.split(",");
			 System.out.println(cresourse);
//			 for(int j = 0;j < resids.length;j++){
//				 System.out.println(resids[j]);
//			 }
//			 String ss = "2324,343,35345,234";
//			 String[] aa = ss.split(",");
//			 for(int j = 0;j < aa.length;j++){
//				 System.out.println(aa[j]);
//			 }
			 
			 if(resids != null && resids.length > 0){
				 cresourse = "";
				 for(int i = 0;i < resids.length;i++){
					 if(resids[i].equals(rsid + "")) continue;   //已经上传了就不再上传了
					 if(i == 0){
						 cresourse += resids[i].trim();
					 } else {
						 cresourse += ","+resids[i].trim();
					 }					 
				 }
				 //加上当前这个资源
				 cresourse += ","+rsid;
				 sql = "update course_unit set cresourse = '"+cresourse+"' where cid="+cid;
				 result = this.statement.executeUpdate(sql);
			 }
			 
		 } catch (SQLException e) {
			 e.printStackTrace();
		 }
		 
		 return result;
		 
	 }
	 
	 /**
	  * 通过课程的id来得到该课程的所有资源
	  * @param cid
	  * @return
	  */
	 public ArrayList<HashMap<String, Object>> getResourceByCid(int cid){
		int result = 0;
		
		ArrayList<HashMap<String, Object>> resources  = null;
		HashMap<String, Object> temp = null;
		try {
			
			//获得资源的所有id	
			String sql = "select cresourse from course_unit where cid = "+cid;
			System.out.println("getResourceByCid: "+sql);
			ResultSet rs = this.statement.executeQuery(sql);
			String[] rsids  = null;
			if(rs.next()){
				rsids = rs.getString("cresourse").split(",");
			}
			
			String rsidIn = "";
			int i;
			for(i = 0;i < rsids.length;i++){
				if(i == 0){
					rsidIn += rsids[i];
				} else {					
					rsidIn += ","+rsids[i];
				}
			}
			
			sql = "select * from resource where rsid in ("+rsidIn+")";
			System.out.println("getResourceByCid: "+sql);
			rs = this.statement.executeQuery(sql);
			
			resources = new ArrayList<HashMap<String, Object>>();
			
			while(rs.next()){
				temp = new HashMap<String, Object>();
				temp.put("rsid", rs.getInt("rsid"));
				temp.put("rsname", rs.getString("rsname"));
				temp.put("rsprofile", rs.getString("rsprofile"));
				temp.put("rssize", rs.getString("rssize"));
				temp.put("rsurl", rs.getString("rsurl"));
				temp.put("rsuser", rs.getString("rsuser"));
				temp.put("ctime", rs.getLong("ctime"));
				temp.put("classid", rs.getInt("classid"));
				temp.put("task", rs.getInt("task"));
				resources.add(temp);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resources;
	 }
	 
	 /**
	  * 通过资源分类id来获得其分类下的所有资源
	  * @param classid
	  * @return
	  */
	 public ArrayList<HashMap<String, Object>> getResourceByClassid(int classid){
		 int result = 0;
			
			ArrayList<HashMap<String, Object>> resources  = null;
			HashMap<String, Object> temp = null;
			try {
				
				//获得资源的所有id	
				String sql = "select * from resource where classid="+classid;
				System.out.println("getResourceByClassid: "+sql);
				ResultSet rs = this.statement.executeQuery(sql);
				
				resources = new ArrayList<HashMap<String, Object>>();
				
				while(rs.next()){
					temp = new HashMap<String, Object>();
					temp.put("rsid", rs.getInt("rsid"));
					temp.put("rsname", rs.getString("rsname"));
					temp.put("rsprofile", rs.getString("rsprofile"));
					temp.put("rssize", rs.getString("rssize"));
					temp.put("rsurl", rs.getString("rsurl"));
					temp.put("rsuser", rs.getString("rsuser"));
					temp.put("ctime", rs.getLong("ctime"));
					temp.put("classid", rs.getInt("classid"));
					temp.put("task", rs.getInt("task"));
					resources.add(temp);
				}
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return resources;
	 }
	 

	 /**
	  * 新建资源分类
	  * @param class_name	资源分类名
	  * @return
	  */
	 public int newRsClass(String class_name){
		 
		int result = 0;
		String sql = "insert into resourse_class (class_name) value ('"+class_name+"')";

		System.out.println("newRsClass: "+sql);
		
		try {
			result = this.statement.executeUpdate(sql);
			
			sql = "select max(classid) as classid from resourse_class";
			
			ResultSet rs = this.statement.executeQuery(sql);
			int classid = 0;
			if(rs.next()){
				classid = rs.getInt("classid");
			}
			
			if(result != 0){
					
				result = classid;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	 }
	 
	 /**
	  * 获得所有的资源分类
	  * @return
	  */
	 public ArrayList<HashMap<String, Object>> getAllRsClass(){
		 int result = 0;
			
			String sql = "select * from resourse_class order by classid desc";
			
			System.out.println("getAllRsClass: "+sql);
			ArrayList<HashMap<String, Object>> resourse_classes = null;
			HashMap<String, Object> temp = null;
			try {
				ResultSet rs = this.statement.executeQuery(sql);
				resourse_classes = new ArrayList<HashMap<String, Object>>();
				
				while(rs.next()){
					temp = new HashMap<String, Object>();
					temp.put("classid", rs.getInt("classid"));
					temp.put("class_name", rs.getString("class_name"));
					resourse_classes.add(temp);
				}
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return resourse_classes;
	 }
	 
	 /**
	  * 添加课程到实训方案中
	  * @param trainid		方案id
	  * @param cid			课程id
	  * @return
	  */
	 public int addCourse2Plan(int trainid , int cid){
		 int result = 0;
		 String sql = "select courseId from train_plan where trainid = "+trainid;
		 System.out.println("addCourse2Plan: "+sql);
		 
		 try {
			 ResultSet rs = this.statement.executeQuery(sql);
			 
			 String courseId = "";	
			 if(rs.next()){
				 courseId = rs.getString("courseId");
			 }
			 
			 //将字符串以逗号分开为数组
			 String[] cids = courseId.split(",");
			 System.out.println(courseId);
			 
			 if(cids != null && cids.length > 0){
				 courseId = "";
				 for(int i = 0;i < cids.length;i++){
					 if(cids[i].trim().equals(cid + "")) continue;   //已经添加的课程就不能再添加了
					 if(cids[i].equals("'0'")){
						 cids[i] = "0";
					 }
					 if(i == 0){
						 courseId += cids[i].trim();
					 } else {
						 courseId += ","+cids[i].trim();
					 }					 
				 }
				 //加上当前这个资源
				 courseId += ","+cid;
				 sql = "update train_plan set courseId = '"+courseId+"' where trainid="+trainid;
				 System.out.println("addCourse2Plan: "+sql);
				 result = this.statement.executeUpdate(sql);
			 }
			 
		 } catch (SQLException e) {
			 e.printStackTrace();
		 }
		 
		 return result;
		 
	 }
	 
	 /**
	  * 添加项目到实训方案中
	  * @param trainid		方案id
	  * @param itemid			项目id
	  * @return
	  */
	 public int addItem2Plan(int trainid , int itemid){
		 int result = 0;
		 
		 try {
				 String sql = "update train_plan set itemid = '"+itemid+"' where trainid="+trainid;
				 System.out.println("addItem2Plan: "+sql);
				 result = this.statement.executeUpdate(sql);
				 
		 } catch (SQLException e) {
			 e.printStackTrace();
		 }
		 
		 return result;
		 
	 }
	 
	 /**
	  * 根据trainid来获取该方案下的所有课程及项目
	  * @param trainid
	  */
	 public HashMap<String, Object> getCoursesAndItemByTrainid(int trainid){
	 	int result = 0;
		
	 	HashMap<String, Object> res  = null;
		HashMap<String, Object> temp = null;
		try {
			
			//获得资源的所有id	
			String sql = "select courseId,itemid from train_plan where trainid = "+trainid;
			System.out.println("getCoursesByTrainid: "+sql);
			ResultSet rs = this.statement.executeQuery(sql);
			String[] cids  = null;
			String[] itemids  = null;
			if(rs.next()){
				cids = rs.getString("courseId").split(",");
				itemids = rs.getString("itemid").split(",");
			}
			
			//查课程
			String courseId = "";
			int i;
			for(i = 0;i < cids.length;i++){
				if(i == 0){
					courseId += cids[i];
				} else {					
					courseId += ","+cids[i];
				}
			}
			
			sql = "select * from course_unit where cid in ("+courseId+")";
			System.out.println("getCoursesByTrainid: "+sql);
			rs = this.statement.executeQuery(sql);
			
			ArrayList<HashMap<String, Object>> courses = new ArrayList<HashMap<String, Object>>();
			
			while(rs.next()){
				temp = new HashMap<String, Object>();
				temp.put("cid", rs.getInt("cid"));
				temp.put("cname", rs.getString("cname"));
				temp.put("cprofile", rs.getString("cprofile"));
				temp.put("cresourse", rs.getString("cresourse"));
				temp.put("classid", rs.getInt("classid"));
				courses.add(temp);
			}
			
			res = new HashMap<String, Object>();
			res.put("courses", courses);
			//TODO查项目
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
		 
	 }
	 
	 /**
	  * 通过传入方向名来获取该方向所分配的方案的所有课程和项目
	  * @param t_direct
	  * @return
	  */
	 public HashMap<String, Object> getCourseAndItemByDirect(String t_direct){
	 	HashMap<String, Object> res  = null;
		HashMap<String, Object> temp = null;
		try {
			
			//获得资源的所有id	
			String sql = "select courseId,itemid from train_plan where did = ( select max(did) as did from train_dr where dname = '"+t_direct+"' )";
			System.out.println("getCourseByDirect: "+sql);
			ResultSet rs = this.statement.executeQuery(sql);
			String[] cids  = null;
			int itemid = 0;
			
			if(rs.next()){
				cids = rs.getString("courseId").split(",");
				System.out.println(rs.getString("courseId"));
				itemid = rs.getInt("itemid");
			}
			
			//查课程
			String courseId = "";
			int i;
			for(i = 0;i < cids.length;i++){
				if(i == 0){
					courseId += cids[i];
				} else {					
					courseId += ","+cids[i];
				}
			}
			
			sql = "select * from course_unit where cid in ("+courseId+")";
			System.out.println("getCourseByDirect: "+sql);
			rs = this.statement.executeQuery(sql);
			
			ArrayList<HashMap<String, Object>> courses = new ArrayList<HashMap<String, Object>>();
			
			while(rs.next()){
				temp = new HashMap<String, Object>();
				temp.put("cid", rs.getInt("cid"));
				temp.put("cname", rs.getString("cname"));
				temp.put("cprofile", rs.getString("cprofile"));
				temp.put("cresourse", rs.getString("cresourse"));
				temp.put("classid", rs.getInt("classid"));
				courses.add(temp);
			}
			
			res = new HashMap<String, Object>();
			res.put("courses", courses);
			//TODO查项目
			
			sql = "select * from train_item where itemid = "+itemid;
			rs = this.statement.executeQuery(sql);
			String[] staids = null;
			HashMap<String, String> item = null;
			if(rs.next()){
				item = new HashMap<String, String>();
				item.put("itemid", rs.getInt("itemid")+"");
				item.put("item_name",rs.getString("item_name"));
				item.put("item_profile", rs.getString("item_profile"));
				staids = rs.getString("stageid").split(",");
			}
			String stageid = "";
			if(staids != null && staids.length > 0){
				for(i = 0;i < staids.length;i++){
					if(i == 0){
						stageid += staids[i];
					} else {					
						stageid += ","+staids[i];
					}
				}
			}
			
			sql = "select * from item_stage where staid in ("+stageid+")";
			rs = this.statement.executeQuery(sql);
			
			ArrayList<HashMap<String, Object>> stages = new ArrayList<HashMap<String, Object>>();
			
			while(rs.next()){
				temp = new HashMap<String, Object>();
				temp.put("staid", rs.getInt("staid"));
				temp.put("sta_name", rs.getString("sta_name"));
				temp.put("sta_profile", rs.getString("sta_profile"));
				temp.put("sta_resc", rs.getString("sta_resc"));
				stages.add(temp);
			}
			
			//添加该项目到HashMap中
			res.put("item", item);
			//添加该项目阶段到HashMap中
			res.put("stages", stages);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
		 
	 }
	 /**
	  * 查询老师名字
	  * @author zhanglz
	  * @param tid
	  * @return
	  * @throws SQLException
	  */
	 public String selectTname(int tid) throws SQLException{
			String result = "ss";		
			System.out.println("tid+++++"+tid);
			String sql = "select Tname from teacher where tid = '"+tid+"'";
			System.out.println("sql+++++"+sql);
			ResultSet rs = this.statement.executeQuery(sql);
			System.out.println("rs+++++"+rs);
			if(rs.next()){
				
				result = rs.getString("Tname");
			}
			System.out.println(result);
			return result;		
		}
	 /**
	  * 修改教师信息
	  * @author zhanglz
	  * @param tid
	  * @param tpsw
	  * @return
	  * @throws SQLException
	  */
	 public int modifyTeaInfo(int tid,String tpsw) throws SQLException{
			int result = 0;
			System.out.println("modifyInfo");
	
			 String sql2 = "update teacher set tpsw = '"+tpsw+"'" +
		 		"where tid = "+tid;
			 result = this.statement.executeUpdate(sql2);
			 System.out.println(result);			 
			 return result; 
			 
		 }
	 /**
	  * 通过cid获得该课程
	  * @param cid
	  * @return
	  */
	 public HashMap<String, Object> getCourseByCid(int cid){
		 String sql = "select * from course_unit where cid = "+cid;
		 System.out.println("getCourseByCid: "+sql);
		 HashMap<String,Object> course = null;
		 try {
			 ResultSet rs = this.statement.executeQuery(sql);

			 course = new HashMap<String,Object>();
			 if(rs.next()){
				 course.put("cid", cid);
				 course.put("cname", rs.getString("cname"));
				 course.put("cprofile", rs.getString("cprofile"));
				 course.put("cresourse", rs.getString("cresourse"));
				 course.put("classid", rs.getString("classid"));
			 }
			 
		 } catch (SQLException e) {
			 e.printStackTrace();
		 }
		 
		 return course;
		 
	 }
	 
	 /**
	  * 通过rsurl得到该资源的文件名
	  * @param rsurl
	  * @return
	  */
	 public String getRsnameByRsurl(String rsurl){
		 String sql = "select rsname  from resource where rsurl = '"+rsurl+"'";
		 
		 String rsname = "";
		 
		 try{
			 ResultSet rs = this.statement.executeQuery(sql);
			 
			 if(rs.next()){
				 rsname = rs.getString("rsname");
			 }
			 
		 }catch(Exception e){}
		 
		 return rsname;
	 }
	 
	 /**
	  * 新建一个项目阶段
	  * @param sta_name		阶段名
	  * @param sta_profile	阶段简介
	  * @return
	  */
	 public int newStage(String sta_name, String sta_profile){
		 
		 int result = 0;
		 String sql = "insert into item_stage (sta_name, sta_profile) value ('"+sta_name+"','"+sta_profile+"')";

		 System.out.println("newStage: "+sql);
		
		 try {
			 result = this.statement.executeUpdate(sql);
			
			 sql = "select max(staid) as staid from item_stage";
			
			 ResultSet rs = this.statement.executeQuery(sql);
			 int staid = 0;
			 if(rs.next()){
				 staid = rs.getInt("staid");
			 }
			
			 if(result != 0){
					
				 result = staid;
			 }
			
		 } catch (SQLException e) {
			 e.printStackTrace();
		 }
		
		 return result;
	 }
	 
	 public int addSta2Item(int itemid, int staid){
		 int result = 0;
		 String sql = "select stageid from train_item where itemid="+itemid;
		 System.out.println("addSta2Item: "+sql);
		 
		 try {
			 ResultSet rs = this.statement.executeQuery(sql);
			 
			 String stageid = "";	
			 if(rs.next()){
				 stageid = rs.getString("stageid");
			 }
			 
			 //将字符串以逗号分开为数组
			 String[] staids = stageid.split(",");
			 System.out.println(stageid);
			 
			 if(staids != null && staids.length > 0){
				 stageid = "";
				 for(int i = 0;i < staids.length;i++){
					 if(staids[i].equals(staid + "")) continue;   //已经上传了就不再上传了
					 if(i == 0){
						 stageid += staids[i].trim();
					 } else {
						 stageid += ","+staids[i].trim();
					 }					 
				 }
				 //加上当前这个资源
				 stageid += ","+staid;
				 sql = "update train_item set stageid = '"+stageid+"' where itemid="+itemid;
				 result = this.statement.executeUpdate(sql);
			 }
			 
		 } catch (SQLException e) {
			 e.printStackTrace();
		 }
		 
		 return result;
		 
	 }
	 
	 /**
	  * 根据itemid 返回该项目下的所有项目阶段
	  * @param itemid
	  */
	 public ArrayList<HashMap<String, Object>> getStagesByItemid(int itemid){
		ArrayList<HashMap<String, Object>> stages  = null;
		HashMap<String, Object> temp = null;
		try {
			
			//获得资源的所有id	
			String sql = "select stageid from train_item where itemid = "+itemid;
			System.out.println("getStagesByItemid: "+sql);
			ResultSet rs = this.statement.executeQuery(sql);
			String[] staids  = null;
			if(rs.next()){
				staids = rs.getString("stageid").split(",");
			}
			
			String staidIn = "";
			//如果项目阶段id为空，则返回null
			if(!(staids != null && staids.length > 0)){
				return null;
			}
			int i;
			for(i = 0;i < staids.length;i++){
				if(i == 0){
					staidIn += staids[i];
				} else {					
					staidIn += ","+staids[i];
				}
			}
			
			sql = "select * from item_stage where staid in ("+staidIn+")";
			System.out.println("getStagesByItemid: "+sql);
			rs = this.statement.executeQuery(sql);
			
			stages = new ArrayList<HashMap<String, Object>>();
			
			while(rs.next()){
				temp = new HashMap<String, Object>();
				temp.put("staid", rs.getInt("staid"));
				temp.put("sta_name", rs.getString("sta_name"));
				temp.put("sta_profile", rs.getString("sta_profile"));
				temp.put("sta_resc", rs.getString("sta_resc"));
				stages.add(temp);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return stages;
	 
	 }
	 
	 /**
	  * 根据staid 返回该阶段
	  * @param staid	项目阶段id
	  * @return
	  */
	 public HashMap<String,String> getStageByStaid(int staid){
		 String sql = "select * from item_stage where staid = "+staid;
		 HashMap<String, String> stage = null;
		 try{
			 ResultSet rs = this.statement.executeQuery(sql);
			 if(rs.next()){
				 stage = new HashMap<String, String>();
				 stage.put("staid", staid+"");
				 stage.put("sta_name", rs.getString("sta_name"));
				 stage.put("sta_profile", rs.getString("sta_profile"));
				 stage.put("sta_resc", rs.getString("sta_resc"));
			 }
			 
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 
		 return stage;
	 }
	 
	 /**
	  * 添加资源到项目阶段中
	  * @param rsid
	  * @param staid
	  * @return
	  */
	 public int addRs2Stage(int rsid, int staid){
		 int result = 0;
		 String sql = "select sta_resc from item_stage where staid="+staid;
		 System.out.println("addRs2Stage: "+sql);
		 
		 try {
			 ResultSet rs = this.statement.executeQuery(sql);
			 
			 String sta_resc = "";	
			 if(rs.next()){
				 sta_resc = rs.getString("sta_resc");
			 }
			 
			 //将字符串以逗号分开为数组
			 String[] resids = sta_resc.split(",");
			 System.out.println(sta_resc);
			 
			 if(resids != null && resids.length > 0){
				 sta_resc = "";
				 for(int i = 0;i < resids.length;i++){
					 if(resids[i].equals(rsid + "")) continue;   //已经上传了就不再上传了
					 if(i == 0){
						 sta_resc += resids[i].trim();
					 } else {
						 sta_resc += ","+resids[i].trim();
					 }					 
				 }
				 //加上当前这个资源
				 sta_resc += ","+rsid;
				 sql = "update item_stage set sta_resc = '"+sta_resc+"' where staid="+staid;
				 result = this.statement.executeUpdate(sql);
			 }
			 
		 } catch (SQLException e) {
			 e.printStackTrace();
		 }
		 
		 return result;
		 
	 }
	 
	 /**
	  * 根据staid，返回该项目阶段下的所有资源
	  * @param staid
	  */
	 public ArrayList<HashMap<String, Object>> getResourceByStaid(int staid){
		ArrayList<HashMap<String, Object>> resources  = null;
		HashMap<String, Object> temp = null;
		try {
			
			//获得资源的所有id	
			String sql = "select sta_resc from item_stage where staid = "+staid;
			System.out.println("getResourceByCid: "+sql);
			ResultSet rs = this.statement.executeQuery(sql);
			String[] rsids  = null;
			if(rs.next()){
				rsids = rs.getString("sta_resc").split(",");
			}
			
			String rsidIn = "";
			int i;
			for(i = 0;i < rsids.length;i++){
				if(i == 0){
					rsidIn += rsids[i];
				} else {					
					rsidIn += ","+rsids[i];
				}
			}
			
			sql = "select * from resource where rsid in ("+rsidIn+")";
			System.out.println("getResourceByStaid: "+sql);
			rs = this.statement.executeQuery(sql);
			
			resources = new ArrayList<HashMap<String, Object>>();
			
			while(rs.next()){
				temp = new HashMap<String, Object>();
				temp.put("rsid", rs.getInt("rsid"));
				temp.put("rsname", rs.getString("rsname"));
				temp.put("rsprofile", rs.getString("rsprofile"));
				temp.put("rssize", rs.getString("rssize"));
				temp.put("rsurl", rs.getString("rsurl"));
				temp.put("rsuser", rs.getString("rsuser"));
				temp.put("ctime", rs.getLong("ctime"));
				temp.put("classid", rs.getInt("classid"));
				temp.put("task", rs.getInt("task"));
				resources.add(temp);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resources;

	 }
	 /**
	  * 课内成绩求和
	  * @author zhanglz
	  * @throws SQLException
	  */
	 public void InSumServlet() throws SQLException{
			int rid = 0;
			int insum = 0;
		 	String sql = "select rid ,SUM(in_grade) as Insum from in_report GROUP BY rid";
			ResultSet rs = this.statement.executeQuery(sql);
			if(rs.next()){
				rid = rs.getInt("rid");
				insum = rs.getInt("Insum");
			}
			System.out.println("rid     :"+rid);
			System.out.println("Insum    :"+insum);	
			sql = "update report set in_sum = '"+insum+"' where rid="+rid;
			int rs1 = this.statement.executeUpdate(sql);
	}
}
