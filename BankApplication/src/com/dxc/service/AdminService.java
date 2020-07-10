package com.dxc.service;

import java.util.List;

import com.dxc.dao.AdminDao;
import com.dxc.pojo.Customers;


public class AdminService {
	
	AdminDao admindao=new AdminDao();
	
	public boolean login(int adminid,int password)
	{
		return admindao.login(adminid,password);
	}
	public boolean adduser(int id,int password,String name,int accno,int balance)
	{
		 return admindao.adduser(id, password, name, accno, balance); 
	}
	public boolean finduser(int id)
	{
		return admindao.finduser(id);
	}
	
	public void updateuser(Customers c)
	{
		admindao.updateuser(c); 
	}
	public List<Customers> displayuser(int accno)
	{
		return admindao.displayuser(accno);
	}
	
	public boolean deleteUsers(int id)
	{
		return admindao.deleteUsers(id);
	}
	public List<Integer> getbalance(int  accno) 
	{
	 return admindao.getbalance(accno);
	}
	public List<Customers> getUsers()
	{
		return admindao.getUsers();
	}

}
