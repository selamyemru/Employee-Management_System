package com.selam.selam;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//@WebServlet("/List")
@WebServlet("/viewDetail")

public class EmployeeListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		final String query="select id,employeeName,employeeDesignation,employeeSalary from employees";
		// get printwriter
		PrintWriter pw=resp.getWriter();
		
		//set content type
		resp.setContentType("text/html");
			//load jdbc driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		}catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
			
		}
		//generate the connection
		try(Connection con=DriverManager.getConnection("jdbc:mysql:///employee","root","");
				PreparedStatement ps=con.prepareStatement(query);){
			ResultSet rs=ps.executeQuery();
			pw.println("<table border='1' align='center' cellspacing='0'>");
			pw.println("<tr>");
			pw.println("<th>Employee Id </th>");
			pw.println("<th>Employee Name</th>");
			pw.println("<th>Employee Designation</th>");
			pw.println("<th>Employee Salary</th>");
			pw.println("<th>Edit</th>");
			pw.println("<th>Delete</th>");
			pw.println("</tr>");
			while(rs.next()) {
				
				pw.println("<tr>");
				pw.println("<td>"+rs.getInt(1)+"</td>");
				pw.println("<td>"+rs.getString(2)+"</td>");
				pw.println("<td>"+rs.getString(3)+"</td>");
				pw.println("<td>"+rs.getFloat(4)+"</td>");
				pw.println("<td><a href='editScreen?id="+rs.getInt(1)+"'>Edit</a></td>");
				pw.println("<td><a href='deleteurl?id="+rs.getInt(1)+"'>Delete</a></td>");
				pw.println("</tr>");
			
			
			}
			pw.println("</table>");
			
			
			
		}catch(SQLException se) {
			se.printStackTrace();	
			pw.println("<h1>"+se.getMessage()+"</h2");
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			pw.println("<h1>"+e.getMessage()+"</h2");
		}
		pw.println("<a href='home.html'>HOME</a>");
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
		
	}


}
