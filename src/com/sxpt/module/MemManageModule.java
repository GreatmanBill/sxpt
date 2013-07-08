package com.sxpt.module;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.sxpt.classes.DBConnection;

public class MemManageModule {

	private Statement statement;
	private Connection con = null;
	public MemManageModule(){
		//连接数据库
		DBConnection dbCon = new DBConnection();
		this.statement = dbCon.getStatement();
		this.con = dbCon.getCon();
	}
	
	/**
	 * 新建一个成员分组
	 * @param mem_gname		成员分组名
	 * @param tid			所属教师
	 * @param did			所属方向
	 * @return
	 */
	public int newMemGroup(String mem_gname, int tid, int did){
		int result = 0;
		String sql = "insert into mem_manage(mem_gname, tid, did) value (" +
				"'"+mem_gname+"',"+tid+","+did+")";
		System.out.println("newMemGroup: "+sql);
		
		try {
			result = this.statement.executeUpdate(sql);
			
			sql = "select max(mem_gid) as mem_gid from mem_manage";

			ResultSet rs = this.statement.executeQuery(sql);
			int mem_gid = 0;
			if(rs.next()){
				mem_gid = rs.getInt("mem_gid");
			}
				
			if(result != 0){
				result = mem_gid;
			}
				
		} catch (SQLException e) {
				e.printStackTrace();
		}
			
		return result;
	}
	
	/**
	 * 根据教师id获得其下的所有成员分组
	 * @param tid
	 * @return
	 */
	public ArrayList<HashMap<String, String>> getMemGroupsByTid(int tid){
		
		String sql = "select * from mem_manage order by mem_gid desc";
		
		System.out.println(sql);
		ArrayList<HashMap<String, String>> memGroups = null;
		HashMap<String, String> temp = null;
		try {
			ResultSet rs = this.statement.executeQuery(sql);
			memGroups = new ArrayList<HashMap<String, String>>();
			
			while(rs.next()){
				temp = new HashMap<String, String>();
				temp.put("mem_gid", rs.getInt("mem_gid")+"");
				temp.put("mem_gname", rs.getString("mem_gname"));
				temp.put("tid", tid+"");
				temp.put("member", rs.getString("member"));
				temp.put("did", rs.getInt("did")+"");
				memGroups.add(temp);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return memGroups;
	}
	
	public ArrayList<HashMap<String, String>> getAllPartsByMemgid(int mem_gid){
		ArrayList<HashMap<String, String>> parts = null;
		String sql = "select * from part where mem_gid = "+mem_gid;
		try{
			ResultSet rs = this.statement.executeQuery(sql);
			parts = new  ArrayList<HashMap<String, String>>();
			HashMap<String, String> temp = null;
			while(rs.next()){
				temp = new HashMap<String, String>();
				temp.put("partid", rs.getInt("partid")+"");
				temp.put("part_name", rs.getString("part_name"));
				temp.put("mem_gid", mem_gid+"");
				parts.add(temp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return parts;
	}
	
	/**
	 * 通过分组id来获得改组
	 * @param mem_gid
	 * @return
	 */
	public HashMap<String,String> getMemGroupByMemgid(int mem_gid){
		String sql = "select * from mem_manange where mem_gid = "+mem_gid;
		HashMap<String,String> memGroup = null;
		try{
			ResultSet rs = this.statement.executeQuery(sql);
			if(rs.next()){
				memGroup = new HashMap<String,String>();
				memGroup.put("mem_gid", mem_gid+"");
				memGroup.put("mem_gname", rs.getString("mem_gname"));
				memGroup.put("tid", rs.getInt("tid")+"");
				memGroup.put("member", rs.getString("member"));
				memGroup.put("did", rs.getInt("did")+"");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return memGroup;
	}
	
	/**
	 * 根据学生的id来获取其所在的组
	 * @param sid
	 */
	public HashMap<String,String> getMemgroupBySid(int sid){
		String sql = "select * from mem_manage where member regexp '(,"+sid+",|,"+sid+"$)'";
		HashMap<String,String> memGroup = null;
		try{
			ResultSet rs = this.statement.executeQuery(sql);
			if(rs.next()){
				memGroup = new HashMap<String,String>();
				memGroup.put("mem_gid", rs.getInt("mem_gid")+"");
				memGroup.put("mem_gname", rs.getString("mem_gname"));
				memGroup.put("tid", rs.getInt("tid")+"");
				memGroup.put("member", rs.getString("member"));
				memGroup.put("did", rs.getInt("did")+"");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return memGroup;
	}
	
	/**
	 * 根据教师id获取其下的所有学生姓名和id
	 * @param tid
	 * @return
	 */
	public ArrayList<HashMap<String, String>> getAllStusByTid(int tid){
		String sql = "select * from student where tid = "+tid;
		
		System.out.println(sql);
		ArrayList<HashMap<String, String>> students = null;
		HashMap<String, String> temp = null;
		try {
			ResultSet rs = this.statement.executeQuery(sql);
			students = new ArrayList<HashMap<String, String>>();
			
			while(rs.next()){
				temp = new HashMap<String, String>();
				temp.put("sid", rs.getInt("sid")+"");
				temp.put("sname", rs.getString("sname"));
				temp.put("sno", rs.getString("sno"));
				temp.put("role", rs.getInt("role")+"");
				students.add(temp);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return students;
		
	}
	
	/**
	 * 根据学生成员分组id获得改组下的所有成员
	 * @param mem_gid
	 */
	public ArrayList<HashMap<String, String>> getAllStusByMemgid(int mem_gid){
		ArrayList<HashMap<String, String>> students = null;
		String sql = "select member from mem_manage where mem_gid=" + mem_gid;
		System.out.println("getAllStusByMemgid: "+sql);
		 
		try {
			ResultSet rs = this.statement.executeQuery(sql);
			 
			String member = "";	
			if(rs.next()){
				member = rs.getString("member");
			}
		 
				
			//根据分组成员id集合member，查出所有属于该组的成员
			sql = "select * from student where sid in ("+member+")";
			
			System.out.println(sql);
			
			HashMap<String, String> temp = null;
			rs = this.statement.executeQuery(sql);
			students = new ArrayList<HashMap<String, String>>();
			
			while(rs.next()){
				temp = new HashMap<String, String>();
				temp.put("sid", rs.getInt("sid")+"");
				temp.put("sname", rs.getString("sname"));
				temp.put("sno", rs.getString("sno"));
				temp.put("role", rs.getInt("role")+"");
				students.add(temp);
			}
				 
		} catch (SQLException e) {
			e.printStackTrace();
		}
			 
		return students;
	}
	
	/**
	 * 添加成员到分组中
	 * @param mem_gid	成员分组id
	 * @param sid		成员id，学生id
	 */
	public int addMem2Group(int mem_gid, int sid){
		int result = 0;
		String sql = "select member from mem_manage where mem_gid=" + mem_gid;
		System.out.println("addMem2Group: "+sql);
		 
		try {
			ResultSet rs = this.statement.executeQuery(sql);
			 
			String member = "";	
			if(rs.next()){
				member = rs.getString("member");
			}
		 
			//将字符串以逗号分开为数组
			String[] sids = member.split(",");
			System.out.println(member+" "+sid+" "+sids.toString());
			if(sids != null && sids.length > 0){
				member = "";
				for(int i = 0;i < sids.length;i++){
					if(sids[i].equals(sid + "")) continue;   //该成员以加入
					
					if(i == 0){
						member += sids[i].trim();
					} else {
						member += ","+sids[i].trim();
					}					 
				}
				//加上当前这个资源
				member += ","+sid;
				sql = "update mem_manage set member = '"+member+"' where mem_gid="+mem_gid;
				System.out.println(sql);
				result = this.statement.executeUpdate(sql);
			}
				 
		} catch (SQLException e) {
			e.printStackTrace();
		}
			 
		return result;
	}
	
	
	public int setLeader(int mem_gid, int sid){
		int result = 0;
		String sql = "update student set role = 1 where sid = "+sid;
		
		System.out.println(sql);
		
		try{
			result = this.statement.executeUpdate(sql);
			
			//如果更新成功，则把同组的其他人设为组员
			if(result > 0){
				//得到该做的所有成员
				sql = "select member from mem_manage where mem_gid = "+mem_gid;
				ResultSet rs = this.statement.executeQuery(sql);
				String sids[] = null;
				if(rs.next()){
					
					sids = rs.getString("member").split(",");
				}
				System.out.println(rs.getString("member"));
				
				//如果只有两个，那么一个是0，一个是 当前的组长id，不用更新
				if(sids != null && sids.length == 2){
					return 1;
				}
				if(sids != null && sids.length > 0){
					String member = "";
					for(int i = 0;i < sids.length;i++){
						if(sids[i].equals(sid + "")) continue;   //过滤掉当前的组长
						if(i == 0){
							member += sids[i].trim();
						} else {
							member += ","+sids[i].trim();
						}					 
					}
					
					//更新其他的成员为组员
					sql = "update student set role = 0 where sid in("+member+")";
					
					System.out.println("setLeader: "+sql);
					result = this.statement.executeUpdate(sql);
					
					return result;
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 根据学士id来获取成员分组id
	 * sql查询采用正则表达式 regexp '(,13,|,13$)';
	 * @param sid
	 */
	public HashMap<String,String> getMemGroupBySid(int sid){
		String sql = "select * from mem_manage where member regexp '(,"+sid+",|,"+sid+"$)'";
		HashMap<String,String> memGroup = null;
		try{
			ResultSet rs = this.statement.executeQuery(sql);
			if(rs.next()){
				memGroup = new HashMap<String,String>();
				memGroup.put("mem_gid", rs.getInt("mem_gid")+"");
				memGroup.put("mem_gname", rs.getString("mem_gname"));
				memGroup.put("tid", rs.getInt("tid")+"");
				memGroup.put("member", rs.getString("member"));
				memGroup.put("did", rs.getInt("did")+"");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return memGroup;
	}
	
	/**
	 * 新建功能模块
	 * @param part_name
	 * @param mem_gid 模块所属分组
	 */
	public int newPart(String part_name, int mem_gid){
		int result = 0;
		String sql = "insert into part(part_name, mem_gid) value ('"+part_name+"',"+mem_gid+")";
		System.out.println("newPart: "+sql);
		
		try {
			result = this.statement.executeUpdate(sql);
			
			sql = "select max(partid) as partid from part";

			ResultSet rs = this.statement.executeQuery(sql);
			int partid = 0;
			if(rs.next()){
				partid = rs.getInt("partid");
			}
				
			if(result != 0){
				result = partid;
			}
				
		} catch (SQLException e) {
				e.printStackTrace();
		}
			
		return result;
	}
	
	/**
	 * 新建一个功能点
	 * @param fun_name
	 * @param partid
	 * @param day
	 * @param sid
	 * @return
	 */
	public int newFun(String fun_name, int partid, int day, int sid){
		int result = 0;
		String sql = "insert into function(fun_name, partid, day, sid)" +
				" value ('"+fun_name+"',"+partid+","+day+","+sid+")";
		System.out.println("newFun: "+sql);
		
		try {
			result = this.statement.executeUpdate(sql);
			
			sql = "select max(funid) as funid from function";

			ResultSet rs = this.statement.executeQuery(sql);
			int funid = 0;
			if(rs.next()){
				funid = rs.getInt("funid");
			}
				
			if(result != 0){
				result = funid;
			}
				
		} catch (SQLException e) {
				e.printStackTrace();
		}
			
		return result;
	}
	
	/**
	 * 根据member字符串，学生id以逗号隔开，获得所有学生信息
	 * @param member
	 */
	public ArrayList<HashMap<String, String>> getAllMemsByMember(String member){
		String sql = "select sid, sname from student where sid in ("+member+")";
		ArrayList<HashMap<String, String>> students = null;;;
		try{
			ResultSet rs = this.statement.executeQuery(sql);
			HashMap<String, String> temp = null;
			students = new ArrayList<HashMap<String, String>>(); 
			while(rs.next()){
				temp = new HashMap<String, String>();
				temp.put("sid", rs.getInt("sid")+"");
				temp.put("sname", rs.getString("sname"));
				students.add(temp);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return students;
	}
	
	/**
	 * 根据成员组id来获取所分配的模块和功能点
	 * @param mem_gid
	 */
	public ArrayList<HashMap<String, String>> getPartsAndFunsByMemgid(int mem_gid){
		ArrayList<HashMap<String, String>> partSAndFuns = null;
		String sql = "select p.part_name, f.*,s.sname " +
				"from part p,student s, function f " +
				"where mem_gid = "+mem_gid+" and p.partid = f.partid and f.sid = s.sid order by p.partid";
		try{
			ResultSet rs = this.statement.executeQuery(sql);
			HashMap<String, String> temp = null;
			partSAndFuns = new ArrayList<HashMap<String, String>>(); 
			while(rs.next()){
				temp = new HashMap<String, String>();
				temp.put("sid", rs.getInt("sid")+"");
				temp.put("sname", rs.getString("sname"));
				temp.put("partid", rs.getInt("partid")+"");
				temp.put("part_name", rs.getString("part_name"));
				temp.put("funid", rs.getInt("funid")+"");
				temp.put("fun_name", rs.getString("fun_name"));
				temp.put("day", rs.getInt("day")+"");
				partSAndFuns.add(temp);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return partSAndFuns;
	}
}
