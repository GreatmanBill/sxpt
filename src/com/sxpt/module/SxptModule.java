package com.sxpt.module;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.sxpt.classes.DBConnection;
import com.sxpt.classes.Student;
import com.sxpt.classes.Teacher;
/**
 * 该类用于对实训平台首页的数据进行管理
 * @author zhang
 *
 */
public class SxptModule {
	private Statement statement;
	private Connection con = null;
	public SxptModule(){
		//连接数据库
		DBConnection dbCon = new DBConnection();
		this.statement = dbCon.getStatement();
		this.con = (Connection) dbCon.getCon();
	}
	
	/**
	 * 该方法用于验证登录，当登录成功后，返回一个HashMap，里面存了用户的类别和
	 * 该用户的所有属性,使用时，应先判断type来使用
	 * @param account
	 * @param psw
	 * @param type
	 * @return	HashMap
	 */
	public HashMap<String ,Object> login(String account, String psw, int type){
		String sql = "";
		ResultSet rs = null;
		HashMap<String ,Object> user = null;
		if(type == 0){//学生
			sql = "select * from student where sno = '"+account+"' " +
					"and spsw = '"+psw+"'" +
					"and type = "+type;
			try {
				rs = this.statement.executeQuery(sql);
				
				
				Student stu = null;
				while (rs.next()) {
					stu = new Student();
	                //= rs.getString(1);
					stu.setSid(rs.getInt("sid"));
					stu.setSno(rs.getString("sno"));
					stu.setSname(rs.getString("sname"));
					stu.setSpsw(rs.getString("spsw"));
					stu.setSsex(rs.getString("ssex"));
					stu.setSmail(rs.getString("smail"));
					stu.setSclass(rs.getString("sclass"));
					stu.setT_direct(rs.getString("t_direct"));
					stu.setBid(rs.getInt("bid"));
					stu.setTid(rs.getInt("tid"));
					stu.setType(0);
					user = new HashMap<String, Object>();
					user.put("type", 0);
					user.put("student", stu);
					break;
	            }
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(type == 1 || type == 2){//教师 , 负责人
			sql = "select * from teacher where tname = '"+account+"' " +
			"and tpsw = '"+psw+"'" +
			"and type = "+type;
			try {
				rs = this.statement.executeQuery(sql);
				Teacher tea = null;
				while(rs.next()){
					tea = new Teacher();
					tea.setTid(rs.getInt("tid"));
					tea.setTname(rs.getString("tname"));
					tea.setTsex(rs.getString("tsex"));
					tea.setT_direct(rs.getString("t_direct"));
					tea.setType(rs.getInt("type"));
					
					user = new HashMap<String, Object>();
					user.put("type", type);
					user.put("teacher", tea);
					break;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return user;
	}
	
	/**
	 * 返回新闻数据当type= -1时，返回两类信息，type = 0时，返回公告，1时返回要闻
	 * limit = 0时，返回全部该类信息，等于4时，返回该类4条最新信息
	 * @param type 
	 * @param limit 
	 * @return
	 */
	public HashMap<String, Object> getNews(int type, int limit){
		HashMap<String, Object> news = null;
		ArrayList<HashMap<String, Object>> notice = null;
		ArrayList<HashMap<String, Object>> outline = null;
		String sql = null;
		ResultSet rs = null;
		try {
			if(type == -1){
				if(limit == 4){
					sql = "select * from info_deploy where type = 0 order by infoid limit 4";
					
					rs = this.statement.executeQuery(sql);
					notice = this.convertToList(rs);
					
					sql = "select * from info_deploy where type = 1 order by infoid limit 4";
					
					rs = this.statement.executeQuery(sql);
					outline = this.convertToList(rs);
					
					news = new HashMap<String,Object>();
					news.put("notice", notice);
					news.put("outline", outline);
				} else {
					
					sql = "select * from info_deploy where type = 0 order by infoid";
					
					rs = this.statement.executeQuery(sql);
					notice = this.convertToList(rs);
					
					sql = "select * from info_deploy where type = 1 order by infoid";
					
					rs = this.statement.executeQuery(sql);
					outline = this.convertToList(rs);
					
					news = new HashMap<String,Object>();
					news.put("notice", notice);
					news.put("outline", outline);
				}
			} else {
				sql = "select * from info_deploy where type = "+type+" order by infoid desc";
				rs = this.statement.executeQuery(sql);
				if(type == 0){
					notice = this.convertToList(rs);
					news = new HashMap<String,Object>();
					news.put("notice", notice);
				} else {
					outline = this.convertToList(rs);
					news = new HashMap<String,Object>();
					news.put("outline", outline);
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return news;
	}
	
	private ArrayList<HashMap<String, Object>> convertToList(ResultSet rs){
		
		ArrayList<HashMap<String, Object>> news = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> temp = null;
		
		try {
			while(rs.next()){
				temp = new HashMap<String, Object>();
				temp.put("infoid", rs.getInt("infoid"));
				temp.put("info_uid", rs.getInt("info_uid"));
				temp.put("info_name", rs.getString("info_name"));
				temp.put("info_title", rs.getString("info_title"));
				temp.put("info_con", rs.getString("info_con"));
				temp.put("ctime", rs.getLong("ctime"));
				temp.put("type", rs.getInt("type"));
				news.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return news;
	}
	
	public HashMap<String, Object> getNewsById(int infoid){
		
		HashMap<String, Object> news = null;
		
		ResultSet rs = null;
		
		String sql = "select * from info_deploy where infoid="+infoid;
		try {
			rs = this.statement.executeQuery(sql);
			while(rs.next()){
				news = new HashMap<String, Object>();
				news.put("infoid", rs.getInt("infoid"));
				news.put("info_uid", rs.getInt("info_uid"));
				news.put("info_name", rs.getString("info_name"));
				news.put("info_title", rs.getString("info_title"));
				news.put("info_con", rs.getString("info_con"));
				news.put("ctime", rs.getLong("ctime"));
				news.put("type", rs.getInt("type"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return news;
	}
	
}
