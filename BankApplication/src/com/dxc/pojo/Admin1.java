package com.dxc.pojo;

public class Admin1 {
	private int adminid;
	private int password;
	
	public int getAdminid() {
		return adminid;
	}
	public void setAdminid(int adminid) {
		this.adminid = adminid;
	}
	public int getPassword() {
		return password;
	}
	public void setPassword(int password) {
		this.password = password;
	}
	public Admin1(int adminid, int password) {
		super();
		this.adminid = adminid;
		this.password = password;
	}
	public Admin1() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Admin1 [adminid=" + adminid + ", password=" + password + "]";
	}

}
