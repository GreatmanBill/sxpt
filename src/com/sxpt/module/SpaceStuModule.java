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
}
