package com.sxpt.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 * 该类用于对数据库进行连接
 * @author zhang
 *
 */
public class DBConnection {
	public Connection con = null;
	public Statement statement= null;
	public String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public String username = "root",password = "123456";
	static String dbname = "jdbc:mysql://localhost/sxpt_DB";
	public DBConnection(){
		this.Connect();
	}
	
    public  DBConnection(String userd , String passd){
        this.password = passd;
        this.username= userd;
        this.Connect();
    }

    public void Connect() {
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(dbname,username,password);
            statement=  con.createStatement();
//            System.out.println ("Database connection established");
//            System.out.println("capturing from database");
//            ResultSet rs=statement.executeQuery(sql);
//            while (rs.next()) {
//                dbtime = rs.getString(1);
//                System.out.println(dbtime);
//            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,"not connected"+e.getMessage());
        }
    }

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}
    
    
}
