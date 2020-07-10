package com.dxc.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dxc.service.AdminService;
import com.dxc.pojo.Customers;

@WebServlet("/Admin")
public class AdminServlet extends HttpServlet {
	
	AdminService adminservice=new AdminService();
		
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//response.getWriter().println("aaaa");
	
	String message;
	String action="";
	String temp=request.getParameter("btn");
	if(temp!=null)
		action=request.getParameter("btn");
	HttpSession session=request.getSession();
	
	if(action.equals("Adminlogin"))
	{
		int adminid=Integer.parseInt(request.getParameter("adminid"));
		int password=Integer.parseInt(request.getParameter("password"));
		
		boolean value=adminservice.login(adminid,password);
		session.setAttribute("adminid", adminid);
		session.setAttribute("password", password);
		
		System.out.println(value);
		
		if(value==true)
		{
		   response.sendRedirect("AdminLogin.jsp");
		}
		else
		{
		     message="invalid login deatails";
		     session.setAttribute("message", message);
             response.sendRedirect("view.jsp");
		}
	}
		
	
	else if(action.equals("Adduser"))
	{
		int id=Integer.parseInt(request.getParameter("id"));
		int password=Integer.parseInt(request.getParameter("password"));
		String name=request.getParameter("name");
		int accno=Integer.parseInt(request.getParameter("accno"));
		int balance=Integer.parseInt(request.getParameter("balance"));
		
		//Customers c=new Customers(id,password,name,accno,balance);
		adminservice.adduser(id,password,name,accno,balance);
		message="customer added succesfully...";
		session.setAttribute("message", message);
		response.sendRedirect("view.jsp");
	}
	
	else if(action.equals("displayuser"))
	{
		int accno=Integer.parseInt(request.getParameter("accno"));
		List<Customers> list=adminservice.displayuser(accno);
		session.setAttribute("list", list);
		response.sendRedirect("Display.jsp");
	
	}
	else if(action.equals("find"))
	{
		int id = Integer.parseInt(request.getParameter("id"));
		boolean findStatus=adminservice.finduser(id);
		session.setAttribute("id",id);
		if(findStatus==true)
		{
			response.sendRedirect("Updateuser1.jsp");
		}
		else
		{
		message="student not found!!";
		session.setAttribute("message",message);
		response.sendRedirect("view.jsp");
	}
	}
	else if(action.equals("Updateuser"))
	{
	    int id=(int)session.getAttribute("id");	
	    int password=Integer.parseInt(request.getParameter("password"));
		String name=request.getParameter("name");
		int accno=Integer.parseInt(request.getParameter("accno"));
		int balance=Integer.parseInt(request.getParameter("balance"));
		
		Customers c=new Customers(id,password,name,accno,balance);
				adminservice.updateuser(c);
				message="customer updated succesfully...";
				session.setAttribute("message", message);
				response.sendRedirect("view.jsp");
	}
	
	else if(action.equals("Balance"))
	{
		int accno=Integer.parseInt(request.getParameter("accno"));
		List<Integer> list=adminservice.getbalance(accno);
		session.setAttribute("list", list);
		response.sendRedirect("Displaybalance.jsp");
		
	}
	else if(action.equals("Delete"))
	{
		int id=Integer.parseInt(request.getParameter("id"));
		boolean removeStatus=adminservice.deleteUsers(id);
		if(removeStatus==true)
		{
			 message="user deleted succesfully..";
		}
		else
		{
			 message="user not found";
		}
		
	    message="user deleted succesfully..";
	    session.setAttribute("message",message);
	    response.sendRedirect("view.jsp");
	}
	
	else
	{
		List<Customers> list=adminservice.getUsers();
		session.setAttribute("list", list);
		response.sendRedirect("DisplayallUsers.jsp");
	}
	}
}
