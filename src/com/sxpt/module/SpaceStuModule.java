package com.sxpt.module;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;
import com.sxpt.classes.DBConnection;

/**
 * 该类用于对个人空间数据进行管理
 * @author zhang
 *
 */
public class SpaceStuModule {
	private Statement statement;
	private Connection con = null;
	public SpaceStuModule(){
		//连接数据库
		DBConnection dbCon = new DBConnection();
		this.statement = dbCon.getStatement();
		this.con = (Connection) dbCon.getCon();
	}
	/**
	 * 查询学生实训批次名
	 * @author ZLZ
	 * @throws SQLException 
	 * @param bid  批次id
	 * return bname 批次名
	 */
	public String selectbname(int bid) throws SQLException{
		String result = "wo";		
		System.out.println("bid+++++"+bid);
		String sql = "select Bname from batch where bid = '"+bid+"'";
		System.out.println("sql+++++"+sql);
		ResultSet rs = this.statement.executeQuery(sql);
		System.out.println("rs+++++"+rs);
		if(rs.next()){
			
			result = rs.getString("Bname");
		}
		System.out.println(result);
		return result;		
	}
	/**
	 * 修改学生信息
	 * @author zhanglz
	 * @param sno
	 * @param spsw
	 * @param smail
	 * @return
	 * @throws SQLException
	 */
	public int modifyStuInfo(String sno,String spsw,String smail) throws SQLException{
		int result = 0;
		System.out.println("modifyInfo");
		System.out.println("spsw:  "+spsw+"smail:   "+smail);
		String sql = "select Sid from student where sno = '"+sno+"'";
		System.out.println("sql++++"+sql);
		ResultSet rs = this.statement.executeQuery(sql);
		int sid = 0;
		if(rs.next()){
			sid = rs.getInt("sid");
			System.out.println(sid);
		}

		 String sql2 = "update student set spsw = "+spsw+", smail = '"+smail+"'" +
	 		"where sid = "+sid;
		 result = this.statement.executeUpdate(sql2);
		 System.out.println(result);
		 
		 return result; 
		 
	 }
}
