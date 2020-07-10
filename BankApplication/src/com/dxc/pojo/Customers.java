package com.dxc.pojo;

public class Customers {
	private int id;
	private int password;
	private String name;
	private int accno;
	private int balance;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPassword() {
		return password;
	}
	public void setPassword(int password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAccno() {
		return accno;
	}
	public void setAccno(int accno) {
		this.accno = accno;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public Customers(int id, int password, String name, int accno, int balance) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.accno = accno;
		this.balance = balance;
	}
	public Customers() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Customers [id=" + id + ", password=" + password + ", name=" + name + ", accno=" + accno + ", balance="
				+ balance + "]";
	}

}
