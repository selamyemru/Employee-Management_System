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
@WebServlet("/editScreen")

public class EditScreenServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String query="select employeeName,employeeDesignation,employeeSalary from employees where id=?";
		// get printwriter
		PrintWriter pw=resp.getWriter();
		pw.println("<a href='home.html'>HOME</a>");
		pw.println("<br>");
		//set content type
		resp.setContentType("text/html");
		//get record of id
		int id=Integer.parseInt(req.getParameter("id"));
			//load jdbc driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		}catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
			
		}
		//generate the connection
		try(Connection con=DriverManager.getConnection("jdbc:mysql:///employee","root","");
				PreparedStatement ps=con.prepareStatement(query);){
			ps.setInt(1,id);
			ResultSet rs=ps.executeQuery();
			rs.next();
			pw.println("<form action='editurl?id "+id+"' mathod='post'>");
			pw.println("<table align='center'>");
			pw.println("<tr>");
			pw.println("<td>Employee Name</td>");
			pw.println("<td><input type='text' name='employeeName' value='"+rs.getString(1)+"'></td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td>Employee designation</td>");
			pw.println("<td><input type='text' name='employeeDesignation' value='"+rs.getString(2)+"'></td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td>Employee Salary</td>");
			pw.println("<td><input type='text' name='employeeSalary' value='"+rs.getFloat(3)+"'></td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td><input type='submit' value='edit' </td>");
			pw.println("<td><input type='reset'  </td>");
			pw.println("<tr>");
			
			pw.println("</tr>");
			pw.println("</table>");
			pw.println("</form>");
			
			
			
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
