package com.dxc.service;

import java.util.List;

import com.dxc.dao.CustomerDao;
import com.dxc.pojo.Customers;
import com.dxc.pojo.Transaction;

public class CustomerService {
	
	CustomerDao customerdao=new CustomerDao();
	public boolean login(int id,int password)
	{
		return customerdao.login(id,password);
	}
	
	public int deposit(int accno,int balance)
	{
		return customerdao.deposit(accno, balance);
	}
	public boolean withdraw(int accno,int bal)
	{
		return customerdao.withdraw(accno, bal);
	}
	public int transfer1(int accno,int balance)
	{
		return customerdao.transfer1(accno,balance);
	}
	public int transfer(int accno,int balance)
	{
		return customerdao.transfer(accno,balance);
	}
	public List<Transaction> printStatement(int accno)
	{
		return customerdao.printStatement(accno);
	}
	
	public List<Integer> checkbalance(int  accno) 
	{
	 return customerdao.checkbalance(accno);
	}
	public boolean changefind(int id)
	{
		return true;
	}
   public void changepwd(int id,int password)
    {
 	customerdao.changepwd(id,password);
    }
}
