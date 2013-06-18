package com.sxpt.classes;

import java.io.Serializable;

public class Teacher implements Serializable {
	private int tid;
	private String tname;
	private String tsex;
	private String t_direct;
	private int type; 			//教师为1 负责人为2
	
	public Teacher(){}
	
	public Teacher(int tid, String tname, String tsex,
			String t_direct, int type){
		this.tid = tid;
		this.tname = tname;
		this.tsex = tsex;
		this.t_direct = t_direct;
		this.type = type;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getTsex() {
		return tsex;
	}

	public void setTsex(String tsex) {
		this.tsex = tsex;
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
