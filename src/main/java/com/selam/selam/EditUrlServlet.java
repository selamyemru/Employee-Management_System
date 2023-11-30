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
@WebServlet("/editurl")

public class EditUrlServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String query="update  employees set employeeName=?,employeeDesignation=?,employeeSalary=? where id=?";
		// get printwriter
		PrintWriter pw=resp.getWriter();
		
		//set content type
		resp.setContentType("text/html");
		//get record of id
		int id=Integer.parseInt(req.getParameter("id"));
		//get edite record we want to  update
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
			
			ps.setString(1,employeeName);
			ps.setString(2,employeeDesignation);
			ps.setFloat(3,employeeSalary);
			ps.setInt(1,id);
			int count =ps.executeUpdate();
			if(count==1) {
				pw.println("record is edited");
			}else {
				pw.println("record is not edited");
			}
				
			
		}catch(SQLException se) {
			se.printStackTrace();	
			pw.println("<h1>"+se.getMessage()+"</h2");
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			pw.println("<h1>"+e.getMessage()+"</h2");
		}
		pw.println("<a href='home.html'>HOME</a>");
		pw.println("<br>");
		pw.println("<a href='viewDetail'>View Detail</a>");
		
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
		
	}

}
