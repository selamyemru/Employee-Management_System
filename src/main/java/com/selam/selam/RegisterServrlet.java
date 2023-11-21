package com.selam.selam;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/register")
public class RegisterServrlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String query="insert into employees(employeeName,employeeDesignation,employeeSalary) value(?,?,?)";
		// get printwriter
		PrintWriter pw=resp.getWriter();
		pw.println("<a href='home.html'>HOME</a>");
		pw.println("<br>");
		pw.println("<a href='viewDetail'>View Detail</a>");
	
		//set content type
		resp.setContentType("text/html");
		//get info of boostore
		String employeeName=req.getParameter("employeeName");
		String employeeDesignation=req.getParameter("employeeDesignation");
		float employeeSalary=Float.parseFloat(req.getParameter("employeeSalary"));
		//load jdbc driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		}catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
			
		}
		//generate the connection
		try(Connection con=DriverManager.getConnection("jdbc:mysql:///employee","root","");
				PreparedStatement ps=con.prepareStatement(query);){
			ps.setString(1, employeeName);
			ps.setString(2, employeeDesignation);
			ps.setFloat(3, employeeSalary);
			int count=ps.executeUpdate();
			if(count==1) {
				pw.println("<h2>record is registered </h2>");
				
			}else {
				pw.println("<h2>record is not registered </h2>");
			}
		}catch(SQLException se) {
			se.printStackTrace();	
			pw.println("<h1>"+se.getMessage()+"</h2");
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			pw.println("<h1>"+e.getMessage()+"</h2");
		}
		}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
		
	}

}
