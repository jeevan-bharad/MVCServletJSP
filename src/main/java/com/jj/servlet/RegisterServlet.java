package com.jj.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/submit")
public class RegisterServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		Connection con=null;
		PreparedStatement ps=null;
		PrintWriter out = resp.getWriter();
		
		System.out.println("we are in Register servlet");
		
		String name =req.getParameter("name");
		String email =req.getParameter("email");
		String pass =req.getParameter("password");
		String gender =req.getParameter("gender");
		String city =req.getParameter("city");

		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_db","root","root");
			 ps = con.prepareStatement("insert into register values(?,?,?,?,?)");
			 ps.setString(1, name);
			 ps.setString(2, email);
			 ps.setString(3, pass);
			 ps.setString(4, gender);
			 ps.setString(5, city);
			 int i = ps.executeUpdate();
				if(i>0) {
					System.out.println("Data inserted successfully for sign up ");
					
				
					out.print("<h1 style='color:green'>Sign in successfully go for login</h1>");
					RequestDispatcher rd = req.getRequestDispatcher("/login.html");
					rd.include(req, resp);
					
					
				}else {
					System.out.println("Data not inserted successfully");
					out.print("<h1 style='color:red'>Something went wrong while sign up</h1>");
					RequestDispatcher rd = req.getRequestDispatcher("/register.html");
					rd.include(req, resp);
				}
		} catch (SQLException e) {
			System.out.println("Data not inserted successfully");
			out.print("<h1 style='color:red'>Exception Occured </h1>"+e.getMessage());
			RequestDispatcher rd = req.getRequestDispatcher("/register.html");
			rd.include(req, resp);
		}
		
		
		
		
	}
	
	
}
