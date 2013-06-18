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
			
			sql = "select max(bid) from batch";
			
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
			temp = new HashMap<String, Object>();
			while(rs.next()){
				temp.put("bid", rs.getInt("bid"));
				temp.put("bname", rs.getString("bname"));
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
	 * @return	新建实训方向成功后返回方向id-》did
	 */
	public int newTrain_dr(int bid,  String dname){
		int result = 0;
		String sql = "insert into train_dr (bid, dname) value ("+bid+",'"+dname+"')";

		System.out.println("newTrain_dr: "+sql);
		
		try {
			result = this.statement.executeUpdate(sql);
			
			sql = "select max(did) from train_dr";
			
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
			
			sql = "select max(rid) from report";

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
}
