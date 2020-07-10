
package com.dxc.dao;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dxc.pojo.Customers;


public class AdminDao {
	private static Connection conn;
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("driver loaded..");
					
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/reethu1?","root","pass");
			System.out.println("connection established..");
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public boolean login(int adminid,int password)
	{
			try {
			
				 Statement stmt=conn.createStatement();	  
				  ResultSet rset = stmt.executeQuery("select * from Admin1");

				while (rset.next()) {
					if ( adminid == rset.getInt(1)  &&   password == rset.getInt(2))
					{
						return true;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}

		
public boolean adduser(int id,int password,String name,int accno,int balance)
{
	try
	{
		PreparedStatement pstmt=conn.prepareStatement("insert into Customers values(?,?,?,?,?)");
		
		pstmt.setInt(1, id);
		pstmt.setInt(2, password);
		pstmt.setString(3, name);
		pstmt.setInt(4, accno);
		pstmt.setInt(5, balance);
		
		pstmt.execute();
		return true;
	}
	 catch (Exception e) {
			e.printStackTrace();
		}
	return false;
}
public boolean finduser(int id)
{
	try
	{
		Statement stmt=conn.createStatement();
		ResultSet rset=stmt.executeQuery("select * from Customers");
		while(rset.next())
		{
			if(id==rset.getInt(1))
			{
				return true;
			}
		}
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	return false;
		}

public void updateuser(Customers c)
{
	try
	{
		PreparedStatement pstmt=conn.prepareStatement("update Customers set password=?,name=?,accno=?,balance=? where id=?");
		
		pstmt.setInt(1,c.getPassword());
		pstmt.setString(2,c.getName());
		pstmt.setInt(3,c.getAccno());
		pstmt.setInt(4,c.getBalance());
		pstmt.setInt(5, c.getId());
		pstmt.executeUpdate();
	}catch(Exception e)
	{
		e.printStackTrace();
	}
}

public List<Customers> displayuser(int accno) 
{
	List<Customers> list=new ArrayList<>();
	try
	{
	     PreparedStatement pstmt=conn.prepareStatement("select * from Customers where accno=?");
	     pstmt.setInt(1,accno);
	     ResultSet rset=pstmt.executeQuery();
	     
	     while(rset.next())
	     {
	    list.add(new Customers(rset.getInt(1),rset.getInt(2),rset.getString(3),rset.getInt(4),rset.getInt(5)));
	     }
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	
	return list;
	
}

public List<Integer> getbalance(int accno)
{
	List<Integer> list=new ArrayList<>();
		int balance=0;
	try {
		PreparedStatement pstmt=conn.prepareStatement("select balance from Customers where accno=?");
		pstmt.setInt(1,accno);
		ResultSet rset=pstmt.executeQuery();
		
		while (rset.next()) 
		{
			balance = rset.getInt(1);
			list.add(balance);
			
			}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
	
}
	
	public boolean deleteUsers(int id)
	{
		boolean removestatus=false;
		try
		{
			PreparedStatement pstmt=conn.prepareStatement("delete from Customers where id=?");
			pstmt.setInt(1, id);
			pstmt.execute();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
       return !removestatus;
	}

	public List<Customers> getUsers()
	{
			 List<Customers> list=new ArrayList<>();
	
	try
	{
	     Statement stmt=conn.createStatement();	  
	     ResultSet rset=stmt.executeQuery("select * from Customers");
	     while(rset.next())
	     {
	    list.add(new Customers(rset.getInt(1),rset.getInt(2),rset.getString(3),rset.getInt(4),rset.getInt(5)));
	     }
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	return list;
		
}
	


	
}


		