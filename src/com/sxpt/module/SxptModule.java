package com.sxpt.module;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.mysql.jdbc.Connection;
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
			
				
				Student stu = new Student();
				while (rs.next()) {
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
					break;
	            }
				user.put("type", 0);
				user.put("student", stu);
				
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
				Teacher tea = new Teacher();
				while(rs.next()){
					tea.setTid(rs.getInt("tid"));
					tea.setTname(rs.getString("tname"));
					tea.setTsex(rs.getString("tsex"));
					tea.setT_direct(rs.getString("t_direct"));
					tea.setType(rs.getInt("type"));
					break;
				}
				user.put("type", type);
				user.put("teacher", tea);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return user;
	}
	
	
	
	
}
