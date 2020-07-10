package com.dxc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dxc.pojo.Customers;
import com.dxc.pojo.Transaction;
import com.dxc.service.CustomerService;

@WebServlet("/Customer")
public class CustomerServlet extends HttpServlet {

	CustomerService customerservice=new CustomerService();
       
   	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
   	{
   		String message;
   		String action="";
   		String temp=request.getParameter("btn");
   		if(temp!=null)
   			action=request.getParameter("btn");
   		HttpSession session=request.getSession();
   			
   		if(action.equals("Customerlogin"))
   		{
   			int id=Integer.parseInt(request.getParameter("id"));
   			int password=Integer.parseInt(request.getParameter("password"));
   			//int accno=Integer.parseInt(request.getParameter("accno"));
   			boolean value=customerservice.login(id,password);
   			session.setAttribute("id", id);
   			session.setAttribute("password", password);
   			//session.setAttribute("accno",accno);
   			System.out.println(value);
   			
   			if(value==true)
   			{
   			   response.sendRedirect("CustomerLogin.jsp");
   			}
   			else
   			{
   			     message="invalid login deatails!!!";
   			     session.setAttribute("message", message);
   	             response.sendRedirect("view.jsp");
   			}
   		  }
   		else if(action.equals("Deposit"))
   		{
   			int accno=Integer.parseInt(request.getParameter("accno"));
   			int balance=Integer.parseInt(request.getParameter("balance"));
   			int bal=customerservice.deposit(accno, balance);
   			
   			try
   			{
   			      PrintWriter pwt=response.getWriter();	
   			      pwt.println(" balance after deposit is="+bal);
   			}catch(Exception e)
   			{
   				e.printStackTrace();
   				
   			}
   		}
   			
   		else if(action.equals("Withdraw"))
   		{
   			int accno=Integer.parseInt(request.getParameter("accno"));
   			int balance=Integer.parseInt(request.getParameter("balance"));
   			
	        boolean bal=customerservice.withdraw(accno, balance);
	        System.out.println(bal);
	        System.out.println("aaa");
	        if(bal==true)
	        {
	        	System.out.println("bbb");
	        	message="amount withdraw susscesfull...";
				 session.setAttribute("message",message);
				 response.sendRedirect("view.jsp");
	   	
	        }else
	        {
	        	System.out.println("ccc");
	        	message="Insuffient balance !!!";
				 session.setAttribute("message",message);
				 response.sendRedirect("view.jsp");
	           	
	        }
   			
   			   		}
   		else if(action.equals("transfer1"))
   		{
   		    int accno=Integer.parseInt(request.getParameter("accno")) ;
   		    int balance=Integer.parseInt(request.getParameter("balance")) ;
   		    session.setAttribute("balance", balance);
   		    int bal=customerservice.transfer1(accno,balance);
   		    System.out.println("aaa");
   		    
             response.sendRedirect("Transferamount.jsp");
   		}
   		else if(action.equals("transfer"))
   		{
   			int accno=Integer.parseInt(request.getParameter("accno")) ;	
   			int balance=(int)session.getAttribute("balance");
   		    int bal=customerservice.transfer(accno,balance);
   		    System.out.println("mmm");
    		 message="amount transfered susscesfully...";
			 session.setAttribute("message",message);
			 response.sendRedirect("view.jsp");
   		}
   		
   		
   		
   		
  		else if(action.equals("Balance"))
  		{
   		  			
   			int accno=Integer.parseInt(request.getParameter("accno"));
   			session.setAttribute("accno", accno);
   			List<Integer> list=customerservice.checkbalance(accno);
   			session.setAttribute("list", list);
   			response.sendRedirect("Checkbalance.jsp");
   			
   		}
   	
   		else if(action.equals("Changepass"))
   		{
   			int id=Integer.parseInt(request.getParameter("id"));
   			boolean find=customerservice.changefind(id);
   			if(find==true)
   			{
   				response.sendRedirect("Changepwd1.jsp");
   			}
   			else
   			{
   				message="user not found";
   			 session.setAttribute("message",message);
   			 response.sendRedirect("view.jsp");
   			}
   		}
   		
   			else if(action.equals("Change"))
   		{
   		      	 int id=(int) session.getAttribute("id");
   		      	 int password=Integer.parseInt(request.getParameter("password"));
   		         customerservice.changepwd(id,password);
   		         message="password updated succesfully...";
   				session.setAttribute("message", message);
   				response.sendRedirect("view.jsp");
   		}
   		
   	   			else if(action.equals("statement"))
   	   	   		{
   	   	   			int accno=Integer.parseInt(request.getParameter(("accno")));
   	   	   			List<Transaction> list=customerservice.printStatement(accno);
   	   	   			System.out.println(list);
   	   	   			session.setAttribute("list", list);
   	   	   			response.sendRedirect("Statement1.jsp");
   	   	   			
   	   	   		}
   	   	   		
   	   	   	
   		 		   	
   	}
}
