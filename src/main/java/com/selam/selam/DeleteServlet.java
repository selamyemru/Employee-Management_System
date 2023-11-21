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
@WebServlet("/deleteurl")

public class DeleteServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String query="delete from employees   where id=?";
		// get printwriter
		PrintWriter pw=resp.getWriter();
	
	
		//set content type
		resp.setContentType("text/html");
		//get record of id
		int id =Integer.parseInt(req.getParameter("id"));
	
		//load jdbc driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		}catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
			
		}
	
		//generate the connection
		try(Connection con=DriverManager.getConnection("jdbc:mysql:///employee","root","");
				PreparedStatement ps=con.prepareStatement(query);){
			ps.setInt(1, id);
			
			int count=ps.executeUpdate();
			if (count==1) {
				pw.println("<h2>record is deleted sucessfully</h2>");
			}else {
				pw.println("<h2>record is not deleted sucessfully</h2>");
			}
//			ResultSet rs=ps.executeQuery();
//			rs.next();			
			
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
