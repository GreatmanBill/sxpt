package com.sxpt.classes;

import java.io.Serializable;

public class Student implements Serializable {
	private int sid;
	private String sno;
	private String sname;
	private String spsw;
	private String ssex;
	private String smail;
	private String sclass;
	private int bid;			//批次
	private int tid;			//所属教师id
	private String t_direct;	//实训方向
	private int type;			//类别，学生为0
	
	public Student(){}
	
	public Student(int sid, String sno, String sname, String spsw,
			String ssex, String smail, String sclass, int bid,
			int tid, String t_direct, int type){
		this.sid = sid;
		this.sno = sno;
		this.sname = sname;
		this.spsw = spsw;
		this.ssex = ssex;
		this.smail = smail;
		this.sclass = sclass;
		this.bid = bid;
		this.tid = tid;
		this.t_direct = t_direct;
		this.type = type;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSpsw() {
		return spsw;
	}

	public void setSpsw(String spsw) {
		this.spsw = spsw;
	}

	public String getSsex() {
		return ssex;
	}

	public void setSsex(String ssex) {
		this.ssex = ssex;
	}

	public String getSmail() {
		return smail;
	}

	public void setSmail(String smail) {
		this.smail = smail;
	}

	public String getSclass() {
		return sclass;
	}

	public void setSclass(String sclass) {
		this.sclass = sclass;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getT_direct() {
		return t_direct;
	}

	public void setT_direct(String tDirect) {
		t_direct = tDirect;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
}
