package com.dxc.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.tomcat.dbcp.dbcp2.SQLExceptionList;
import com.dxc.pojo.Customers;
import com.dxc.pojo.Transaction;
import java.sql.Statement;

public class CustomerDao {
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
	public boolean login(int id,int password)
	{
			try {
			
				 Statement stmt=conn.createStatement();	  
				  ResultSet rset = stmt.executeQuery("select * from Customers");

				while (rset.next()) {
					if ( id == rset.getInt(1)  &&   password == rset.getInt(2))
					{
						return true;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}


	public int deposit(int accno,int bal)
	{
		int balance1=0;
		String s="deposit";
		try
		{
			PreparedStatement pstmt=conn.prepareStatement("select * from Customers where accno=?");
			pstmt.setInt(1, accno);
			ResultSet rset=pstmt.executeQuery();
			
			rset.next();
			balance1=rset.getInt(5);
			System.out.println("aaa");
			balance1=balance1+bal;
			System.out.println(balance1);

			System.out.println("...");
				PreparedStatement pstmt1=conn.prepareStatement("update Customers set balance=? where accno=?");
				pstmt1.setInt(1, balance1);
				pstmt1.setInt(2, accno);
				pstmt1.executeUpdate();
				System.out.println("bbb");
				
			PreparedStatement pstmt2=conn.prepareStatement("insert into Transactions values (?,?,?)");
			pstmt2.setInt(1,accno);
			pstmt2.setString(2, s);
			pstmt2.setInt(3, bal);
			pstmt2.executeUpdate();
											}
		catch(SQLException e)
		{
		e.printStackTrace();
	}
			
		return balance1;
}
	
	public boolean withdraw(int accno,int bal)
	{
		int balance=0;
		String s="withdraw";
		try
		{
			PreparedStatement pstmt=conn.prepareStatement("select * from Customers where accno=?");
			pstmt.setInt(1, accno);
			ResultSet rset1=pstmt.executeQuery();
			
			while(rset1.next())
			{
				balance=rset1.getInt(5);
				
			if(balance>bal)
			{
				balance=balance - bal;
				System.out.println(balance);
				
	
			}else
			{
				return false;
			}
			}
			
			PreparedStatement pstmt1=conn.prepareStatement("update Customers set balance=? where accno=?");
			pstmt1.setInt(1,balance );
			pstmt1.setInt(2, accno);
			pstmt1.executeUpdate();
			System.out.println("ddd");
			
			PreparedStatement pstmt2=conn.prepareStatement("insert into Transactions values (?,?,?)");
			pstmt2.setInt(1,accno);
			pstmt2.setString(2, s);
			pstmt2.setInt(3, bal);
			pstmt2.executeUpdate();
			
		
	}catch(Exception e)
		{
		e.printStackTrace();
	}
		return true;
	}
	
	public int transfer1(int accno,int balance)
	{
		int bal=0;
		String s="withdraw";
		try
		{
			PreparedStatement pstmt=conn.prepareStatement("select * from Customers where accno=?");
			pstmt.setInt(1, accno);
			ResultSet rset=pstmt.executeQuery();
			
			rset.next();
			bal=rset.getInt(5);
			bal=bal - balance;
			System.out.println(bal);
			
			PreparedStatement pstmt1=conn.prepareStatement("update Customers set balance=? where accno=?");
			pstmt1.setInt(1, bal);
			pstmt1.setInt(2, accno);
			pstmt1.execute();
			
			PreparedStatement pstmt2=conn.prepareStatement("insert into Transactions values (?,?,?)");
			pstmt2.setInt(1,accno);
			pstmt2.setString(2, s);
			pstmt2.setInt(3, bal);
			pstmt2.executeUpdate();
			
		
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return bal;
	}
	public int transfer(int accno,int balance)
	{
		int bal1=0;
		String s="deposit";
	try {
		    System.out.println(accno);
			PreparedStatement pstmt2=conn.prepareStatement("select * from Customers where accno=?");
			pstmt2.setInt(1, accno);
			ResultSet rset2=pstmt2.executeQuery();
			System.out.println("sss");
			
			rset2.next();
			bal1=rset2.getInt(5);
			bal1=bal1+ balance;
			System.out.println(bal1);
			
			PreparedStatement pstmt3=conn.prepareStatement("update Customers set balance=? where accno=?");
			pstmt3.setInt(1, bal1);
			pstmt3.setInt(2, accno);
			pstmt3.execute();
			System.out.println("bbb");
			
			PreparedStatement pstmt=conn.prepareStatement("insert into Transactions values (?,?,?)");
			pstmt.setInt(1,accno);
			pstmt.setString(2, s);
			pstmt.setInt(3, bal1);
			pstmt.executeUpdate();
			
		
			}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return bal1;
	}
	public List<Transaction> printStatement(int accno)
	{
		List<Transaction> list=new ArrayList<>();
		try
		{
		
			PreparedStatement pstmt=conn.prepareStatement("select * from Transactions where accno=?");
			pstmt.setInt(1,accno);
			ResultSet rset=pstmt.executeQuery();
			while(rset.next())
			{
				list.add(new Transaction(rset.getInt(1),rset.getString(2),rset.getInt(3)));
			}
			
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Integer> checkbalance(int accno)
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
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}
	public boolean changefind(int id)
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
				System.out.println("aaa");
			}
				
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	public void changepwd(int id,int password)
	{
		try {
			
		PreparedStatement pstmt=conn.prepareStatement("update Customers set password=? where id=?");
		pstmt.setInt(1,password);
		pstmt.setInt(2, id);
		
		pstmt.execute();
		System.out.println("bbb");
		
			} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
}



